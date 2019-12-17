package com.pineapplepayments.paya.connector.persistence.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.pineapplepayments.paya.connector.contants.ACHContants;
import com.pineapplepayments.paya.connector.errorhandlers.Error;
import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionInformation;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionLog;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionResponse;
import com.pineapplepayments.paya.connector.persistence.repository.TransactionInformationRepository;
import com.pineapplepayments.paya.connector.persistence.repository.TransactionLogRepository;
import com.pineapplepayments.paya.connector.soap.client.SoapClient;
import com.pineapplepayments.paya.connector.soap.contract.ACCOUNT;
import com.pineapplepayments.paya.connector.soap.contract.AUTH_GATEWAY;
import com.pineapplepayments.paya.connector.soap.contract.CHECK;
import com.pineapplepayments.paya.connector.soap.contract.CONSUMER;
import com.pineapplepayments.paya.connector.soap.contract.MERCHANT;
import com.pineapplepayments.paya.connector.soap.contract.PACKET;
import com.pineapplepayments.paya.connector.soap.contract.RESPONSE;
import com.pineapplepayments.paya.connector.soap.contract.TRANSACTION;
import com.pineapplepayments.paya.connector.soap.model.AuthGatewayCertificationResponse;
import com.pineapplepayments.paya.connector.soap.model.ProcessSingleCertificationCheckResponse;
import com.pineapplepayments.paya.connector.web.service.validation.TransactionRequestValidationService;

@Service
@Transactional(readOnly = true)
public class TransactionInformationService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionInformationService.class);

	@Autowired
	private TransactionInformationRepository transactionInformationRepository;

	@Autowired
	private TransactionRequestValidationService transactionRequestValidationService;

	@Autowired
	private SoapClient soapClient;

	@Autowired
	private AuthGatewayHeaderService authGatewayHeaderService;

	@Autowired
	private AuthGatewayService authGatewayService;

	@Autowired
	private TransactionResponseService transactionResponseService;

	@Autowired
	private TerminalSettingsService terminalSettingsService;

	@Autowired
	private TransactionLogRepository transactionLogRepository;

	/**
	 * 
	 * @param transactionInformation
	 * @param bindingResult
	 * @return
	 */
	@Transactional
	public TransactionResponse validateACHTransaction(TransactionInformation transactionInformation,
			BindingResult bindingResult) {

		transactionInformation.setRequestId(UUID.randomUUID().toString());
		transactionInformation.setTransactionGuid(UUID.randomUUID().toString());

		if (transactionRequestValidationService.validateRequest(transactionInformation, bindingResult)) {

			TerminalSettings terminalSettings = terminalSettingsService
					.getActiveTerminalDetailsByTerminalId(transactionInformation.getTerminalId());

			String dataPacket = prepareDataPacket(transactionInformation, terminalSettings);

			save(transactionInformation);

			AuthGatewayCertificationResponse authGatewayCertificationResponse = null;
			authGatewayCertificationResponse = authGatewayService.getAuthGateway(dataPacket,
					terminalSettings.getTerminalId());

			if (authGatewayCertificationResponse.getAuthGatewayCertificationResult().contains("EXCEPTION")
					|| authGatewayCertificationResponse.getAuthGatewayCertificationResult().contains("Failed")) {
				return transactionResponseService.save(populateTransactionResponseForAuthException(
						authGatewayCertificationResponse, transactionInformation, terminalSettings));
			} else {
				return transactionResponseService.save(populateTransactionResponseForAuthSuccess(
						authGatewayCertificationResponse, transactionInformation, terminalSettings));
			}

		} else {
			return null;
		}
	}

	/**
	 * Validating the request & preparing the XML to call Paya service i.e processingSingleCertiCheck & returning the response
	 * @param transactionInformation
	 * @param bindingResult
	 * @return
	 */
	@Transactional
	public TransactionResponse processACHTransaction(TransactionInformation transactionInformation,
			BindingResult bindingResult) {
		TransactionResponse authResponse = validateACHTransaction(transactionInformation, bindingResult);

		if (authResponse == null
				|| (authResponse.getError() != null && !StringUtils.isEmpty(authResponse.getError().getMessage()))) {
			return authResponse;
		} else {
			TerminalSettings terminalSettings = terminalSettingsService
					.getActiveTerminalDetailsByTerminalId(transactionInformation.getTerminalId());
			String dataPacket = prepareDataPacket(transactionInformation, terminalSettings);
			// Calcutaling the tx request time & response time
			StopWatch watch = new StopWatch();
			watch.start();
			return processingSingleCertiCheck(transactionInformation, terminalSettings, dataPacket, watch);
		}
	}

	/**
	 * Calling the Paya service processSingleCertificationCheck and returning the response
	 * @param transactionInformation
	 * @param terminalSettings
	 * @param dataPacket
	 * @param watch
	 * @return
	 */
	private TransactionResponse processingSingleCertiCheck(TransactionInformation transactionInformation,
			TerminalSettings terminalSettings, String dataPacket, StopWatch watch) {
		ProcessSingleCertificationCheckResponse singleCertificationCheckResponse;
		singleCertificationCheckResponse = soapClient.processSingleCertificationCheck(dataPacket,
				authGatewayHeaderService.prepareAuthHeader(terminalSettings.getTerminalId()));
		logger.debug("Response from paya service for processSingleCertificationCheck(): "
				+ singleCertificationCheckResponse.getProcessSingleCertificationCheckResult());
		watch.stop();
		if (!(singleCertificationCheckResponse.getProcessSingleCertificationCheckResult().contains("EXCEPTION")
				|| singleCertificationCheckResponse.getProcessSingleCertificationCheckResult().contains("Failed"))) {
			return transactionResponseService.save(populateTransactionResponse(singleCertificationCheckResponse,
					transactionInformation, terminalSettings));
		} else {
			return populateProcessSinCertChkTransResForException(singleCertificationCheckResponse,
					transactionInformation, terminalSettings);
		}
	}

	/**
	 * Preparing the data packet i.e XML body
	 * @param transactionInformation
	 * @param terminalSettings
	 * @return
	 */
	private String prepareDataPacket(TransactionInformation transactionInformation, TerminalSettings terminalSettings) {
		String dataPacket = "";
		AUTH_GATEWAY ag = new AUTH_GATEWAY();
		ag.setREQUEST_ID(transactionInformation.getRequestId());
		ag.setTRANSACTION(new TRANSACTION());
		ag.getTRANSACTION().setMERCHANT(new MERCHANT());
		ag.getTRANSACTION().getMERCHANT().setTERMINAL_ID(transactionInformation.getTerminalId().toString());
		ag.getTRANSACTION().setPACKET(new PACKET());
		ag.getTRANSACTION().getPACKET().setIDENTIFIER(transactionInformation.getIdentifier());
		ag.getTRANSACTION().getPACKET().setACCOUNT(new ACCOUNT());
		ag.getTRANSACTION().getPACKET().getACCOUNT()
				.setACCOUNT_NUMBER(transactionInformation.getAccountNumber().toString());
		ag.getTRANSACTION().getPACKET().getACCOUNT().setACCOUNT_TYPE(transactionInformation.getAccountType());
		if (terminalSettings != null && ACHContants.SEC_CODE_TEL.equals(terminalSettings.getSecCode().trim()))
			ag.getTRANSACTION().getPACKET().getACCOUNT()
					.setCHECK_NUMBER(transactionInformation.getCheckNumber().toString());
		ag.getTRANSACTION().getPACKET().getACCOUNT()
				.setROUTING_NUMBER(transactionInformation.getRountingNumber().toString());
		ag.getTRANSACTION().getPACKET().setCHECK(new CHECK());
		ag.getTRANSACTION().getPACKET().getCHECK().setCHECK_AMOUNT(transactionInformation.getCheckAmount().toString());
		ag.getTRANSACTION().getPACKET().setCONSUMER(new CONSUMER());
		if (terminalSettings != null && ACHContants.SEC_CODE_CCD.equals(terminalSettings.getSecCode().trim()))
			ag.getTRANSACTION().getPACKET().getCONSUMER()
					.setCOMPANY_NAME(transactionInformation.getCompanyName().trim());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setFIRST_NAME(transactionInformation.getFirstName());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setLAST_NAME(transactionInformation.getLastName());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setADDRESS1(transactionInformation.getAddress1());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setADDRESS2(transactionInformation.getAddress2());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setCITY(transactionInformation.getCity());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setCOURTESY_CARD_ID(transactionInformation.getCourtseyCardId());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setDL_NUMBER(transactionInformation.getDlNumber());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setDL_STATE(transactionInformation.getDlState());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setPHONE_NUMBER(transactionInformation.getPhoneNumber());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setSTATE(transactionInformation.getState());
		ag.getTRANSACTION().getPACKET().getCONSUMER().setZIP(transactionInformation.getZipCode().toString());
		try {
			JAXBContext context = JAXBContext.newInstance(AUTH_GATEWAY.class);
			Marshaller m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

			StringWriter sw = new StringWriter();
			m.marshal(ag, sw);
			dataPacket = sw.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return dataPacket;
	}

	/**
	 * Converting the string processSingleCertificationCheckResult to transaction response object
	 * @param singleCertificationCheckResponse
	 * @param transactionInformation
	 * @param terminalSettings
	 * @return
	 */
	private TransactionResponse populateTransactionResponse(
			ProcessSingleCertificationCheckResponse singleCertificationCheckResponse,
			TransactionInformation transactionInformation, TerminalSettings terminalSettings) {
		TransactionResponse transactionResponse = new TransactionResponse();
		JAXBContext jaxbContext;
		RESPONSE processSingleResponse = null;
		try {
			if (!singleCertificationCheckResponse.getProcessSingleCertificationCheckResult().contains("EXCEPTION")) {
				jaxbContext = JAXBContext.newInstance(RESPONSE.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				processSingleResponse = (RESPONSE) jaxbUnmarshaller.unmarshal(
						new StringReader(singleCertificationCheckResponse.getProcessSingleCertificationCheckResult()));
				transactionResponse = new TransactionResponse(processSingleResponse);
				transactionResponse.setTransactionGuid(transactionInformation.getTransactionGuid());
				transactionResponse.setTransactionType(transactionInformation.getTransactionType());
				transactionResponse.setTransactionInfoId(transactionInformation);
				transactionResponse.setSecCode(terminalSettings.getSecCode());
				transactionResponse.setStatus(true);
			} else {
				jaxbContext = JAXBContext.newInstance(RESPONSE.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				processSingleResponse = (RESPONSE) jaxbUnmarshaller.unmarshal(
						new StringReader(singleCertificationCheckResponse.getProcessSingleCertificationCheckResult()));
				transactionResponse
						.setError(new Error(HttpStatus.BAD_REQUEST, processSingleResponse.getEXCEPTION().getMESSAGE()));
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return transactionResponse;
	}

	/**
	 * Checking the Exception & failure cases from Paya service and returning the transaction response object
	 * @param singleCertificationCheckResponse
	 * @param transactionInformation
	 * @param terminalSettings
	 * @return
	 */
	private TransactionResponse populateProcessSinCertChkTransResForException(
			ProcessSingleCertificationCheckResponse singleCertificationCheckResponse,
			TransactionInformation transactionInformation, TerminalSettings terminalSettings) {
		TransactionResponse transactionResponse = new TransactionResponse();
		JAXBContext jaxbContext;
		RESPONSE processSingleResponse = null;
		try {
			jaxbContext = JAXBContext.newInstance(RESPONSE.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			processSingleResponse = (RESPONSE) jaxbUnmarshaller.unmarshal(
					new StringReader(singleCertificationCheckResponse.getProcessSingleCertificationCheckResult()));
			transactionResponse.setRequestId(transactionInformation.getRequestId());
			transactionResponse.setTransactionGuid(transactionInformation.getTransactionGuid());
			transactionResponse.setTransactionType(transactionInformation.getTransactionType());
			transactionResponse.setTransactionInfoId(transactionInformation);
			transactionResponse.setSecCode(terminalSettings.getSecCode());
			transactionResponse.setStatus(false);
			if (singleCertificationCheckResponse.getProcessSingleCertificationCheckResult().contains("EXCEPTION")) {
				transactionResponse
						.setError(new Error(HttpStatus.BAD_REQUEST, processSingleResponse.getEXCEPTION().getMESSAGE()));
			} else if (singleCertificationCheckResponse.getProcessSingleCertificationCheckResult().contains("Failed")) {
				transactionResponse.setError(new Error(HttpStatus.BAD_REQUEST,
						processSingleResponse.getVALIDATION_MESSAGE().getVALIDATION_ERROR().get(0).getMESSAGE()));
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return transactionResponse;
	}

	/**
	 * 
	 * @param authGatewayCertificationResponse
	 * @param transactionInformation
	 * @param terminalSettings
	 * @return
	 */
	private TransactionResponse populateTransactionResponseForAuthException(
			AuthGatewayCertificationResponse authGatewayCertificationResponse,
			TransactionInformation transactionInformation, TerminalSettings terminalSettings) {
		TransactionResponse transactionResponse = new TransactionResponse();
		JAXBContext jaxbContext;
		RESPONSE response = null;
		try {
			jaxbContext = JAXBContext.newInstance(RESPONSE.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			response = (RESPONSE) jaxbUnmarshaller
					.unmarshal(new StringReader(authGatewayCertificationResponse.getAuthGatewayCertificationResult()));
			transactionResponse.setRequestId(transactionInformation.getRequestId());
			transactionResponse.setTransactionGuid(transactionInformation.getTransactionGuid());
			transactionResponse.setTransactionType(transactionInformation.getTransactionType());
			transactionResponse.setTransactionInfoId(transactionInformation);
			transactionResponse.setSecCode(terminalSettings.getSecCode());
			transactionResponse.setStatus(false);
			if (authGatewayCertificationResponse.getAuthGatewayCertificationResult().contains("EXCEPTION")) {
				transactionResponse.setError(new Error(HttpStatus.BAD_REQUEST, response.getEXCEPTION().getMESSAGE()));
			} else if (authGatewayCertificationResponse.getAuthGatewayCertificationResult().contains("Failed")) {
				transactionResponse.setError(new Error(HttpStatus.BAD_REQUEST,
						response.getVALIDATION_MESSAGE().getVALIDATION_ERROR().get(0).getMESSAGE()));
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return transactionResponse;
	}

	/**
	 * 
	 * @param authGatewayCertificationResponse
	 * @param transactionInformation
	 * @param terminalSettings
	 * @return
	 */
	private TransactionResponse populateTransactionResponseForAuthSuccess(
			AuthGatewayCertificationResponse authGatewayCertificationResponse,
			TransactionInformation transactionInformation, TerminalSettings terminalSettings) {
		TransactionResponse transactionResponse = new TransactionResponse();
		JAXBContext jaxbContext;
		RESPONSE response = null;
		try {
			jaxbContext = JAXBContext.newInstance(RESPONSE.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			response = (RESPONSE) jaxbUnmarshaller
					.unmarshal(new StringReader(authGatewayCertificationResponse.getAuthGatewayCertificationResult()));
			transactionResponse.setRequestId(transactionInformation.getRequestId());
			transactionResponse.setTransactionGuid(transactionInformation.getTransactionGuid());
			transactionResponse.setTransactionType(transactionInformation.getTransactionType());
			transactionResponse.setTransactionInfoId(transactionInformation);
			transactionResponse.setSecCode(terminalSettings.getSecCode());
			transactionResponse.setStatus(true);
			transactionResponse.setMessage(response.getVALIDATION_MESSAGE().getRESULT());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return transactionResponse;
	}

	/**
	 * Persisting the data in to TransactionInformation & Transaction Log tables
	 * 
	 * @param transactionInformation
	 * @return
	 */
	@Transactional
	public TransactionInformation save(TransactionInformation transactionInformation) {
		populateAuditFields(transactionInformation);
		transactionLogRepository.save(new TransactionLog(transactionInformation));
		return transactionInformationRepository.save(transactionInformation);

	}

	/**
	 * Persisting the list of transaction information objects
	 * @param transactionInformationList
	 * @return
	 */
	@Transactional
	public List<TransactionInformation> save(List<TransactionInformation> transactionInformationList) {
		for (TransactionInformation ti : transactionInformationList) {
			populateAuditFields(ti);
		}
		return transactionInformationRepository.saveAll(transactionInformationList);

	}

	/**
	 * Populating the audit fields
	 * @param transactionInformation
	 */
	private void populateAuditFields(TransactionInformation transactionInformation) {
		String userName = System.getProperty("user.name");
		if (transactionInformation.getId() == null) {
			transactionInformation.setCreatedBy(userName);
			transactionInformation.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		}
		transactionInformation.setModifiedBy(userName);
		transactionInformation.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));

	}

}
