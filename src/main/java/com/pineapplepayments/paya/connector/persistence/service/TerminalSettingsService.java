package com.pineapplepayments.paya.connector.persistence.service;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.pineapplepayments.paya.connector.errorhandlers.Error;
import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;
import com.pineapplepayments.paya.connector.persistence.repository.TerminalSettingsRepository;
import com.pineapplepayments.paya.connector.soap.client.SoapClient;
import com.pineapplepayments.paya.connector.soap.contract.RESPONSE;
import com.pineapplepayments.paya.connector.soap.contract.TERMINAL_SETTINGS;
import com.pineapplepayments.paya.connector.soap.model.GetCertificationTerminalSettingsResponse;
import com.pineapplepayments.paya.connector.utils.FileUtils;
import com.pineapplepayments.paya.connector.web.contract.Request;

@Service
@Transactional(readOnly = true)
public class TerminalSettingsService {

    private static final Logger logger = LoggerFactory.getLogger(TerminalSettingsService.class);

    @Autowired
    private TerminalSettingsRepository terminalSettingsRepository;

    @Autowired
    private AuthGatewayHeaderService authGatewayHeaderService;

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private FileUtils fileUtils;

    @Transactional
    public TerminalSettings addTerminalSettings(Request request, BindingResult bindingResult) {

        GetCertificationTerminalSettingsResponse response = null;

        logger.debug("Called paya service for terminalId: " + request.getTerminalId());

        response = soapClient
                .getCertificationTerminalSettings(authGatewayHeaderService.prepareAuthHeader(request.getTerminalId()));

        logger.debug("Response from paya service for terminalId: " + response.getGetCertificationTerminalSettingsResult());

        TerminalSettings terminalSettings = prepareTerminalSettings(response);

        if (terminalSettings != null && terminalSettings.getTerminalId() != null) {
            populateAuditFields(terminalSettings);
            terminalSettings = terminalSettingsRepository.save(terminalSettings);
        }

        return terminalSettings;

    }

    @Transactional
    public TerminalSettings removeTerminalSettings(Request request, BindingResult bindingResult) {

        TerminalSettings terminalSettings = getByTerminalId(request.getTerminalId());

        terminalSettings.setActive(false);
        terminalSettings.setDeActivatedBy(System.getProperty("user.name"));
        terminalSettings.setDeActivatedOn(new Date());

        logger.debug("Deactivating terminalId: " + request.getTerminalId());

        terminalSettings = terminalSettingsRepository.save(terminalSettings);
        return terminalSettings;

    }

    @Transactional
    public TerminalSettings save(TerminalSettings terminalSettings) {
        return terminalSettingsRepository.save(terminalSettings);

    }

    @Transactional
    public List<TerminalSettings> save(List<TerminalSettings> terminalSettingsList) {
        return terminalSettingsRepository.saveAll(terminalSettingsList);

    }

    private void populateAuditFields(TerminalSettings terminalSettings) {
        String userName = System.getProperty("user.name");
        if (terminalSettings.getId() == null) {
            terminalSettings.setCreatedOn(new Date());
            terminalSettings.setCreatedBy(userName);
        }
        terminalSettings.setModifiedOn(new Date());
        terminalSettings.setModifiedBy(userName);

    }

    private TerminalSettings prepareTerminalSettings(GetCertificationTerminalSettingsResponse response) {

        JAXBContext jaxbContext;
        TERMINAL_SETTINGS settings = null;
        RESPONSE expResponse = null;
        TerminalSettings terminalSettings = null;
        try {

            if (response.getGetCertificationTerminalSettingsResult().contains("EXCEPTION")) {
                jaxbContext = JAXBContext.newInstance(RESPONSE.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                expResponse = (RESPONSE) jaxbUnmarshaller
                        .unmarshal(new StringReader(response.getGetCertificationTerminalSettingsResult()));
                terminalSettings = new TerminalSettings();
                terminalSettings.setError(new Error(HttpStatus.BAD_REQUEST, expResponse.getEXCEPTION().getMESSAGE()));
            } else {
                jaxbContext = JAXBContext.newInstance(TERMINAL_SETTINGS.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                settings = (TERMINAL_SETTINGS) jaxbUnmarshaller
                        .unmarshal(new StringReader(response.getGetCertificationTerminalSettingsResult()));
                terminalSettings = getByTerminalId(settings.getTERMINAL_ID());
                if (terminalSettings != null && terminalSettings.getTerminalId() != null)
                    prepareTerminalSettingsForUpdate(terminalSettings, settings);
                else
                    terminalSettings = new TerminalSettings(settings);
                terminalSettings.setSchemaFileData(fileUtils.readByURL(settings.getSCHEMA_FILE_PATH()));
                terminalSettings.setXmlTemplateData(fileUtils.readByURL(settings.getXML_TEMPLATE_PATH()));
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return terminalSettings;
    }

    private void prepareTerminalSettingsForUpdate(TerminalSettings terminalSettings, TERMINAL_SETTINGS settings) {
        terminalSettings.setActive(true);
        terminalSettings.setSecCode(settings.getSEC_CODE());
        terminalSettings.setAllowConsumerCredits(settings.getALLOW_CNSMR_CREDITS());
        terminalSettings.setDlRequired(settings.getDL_REQUIRED());
        terminalSettings.setIsGatewayTerminal(settings.getIS_GATEWAY_TERMINAL());
        terminalSettings.setRunCheckVerification(settings.getRUN_CHECK_VERIFICATION());
        terminalSettings.setRunIdentityVerification(settings.getRUN_IDENTITY_VERIFICATION());
    }

    public Optional<TerminalSettings> getById(Long id) {

        return terminalSettingsRepository.findById(id);
    }

    public TerminalSettings getByTerminalId(Long terminalId) {

        return terminalSettingsRepository.findByTerminalId(terminalId);
    }

    public TerminalSettings getActiveTerminalDetailsByTerminalId(Long terminalId) {

        return terminalSettingsRepository.findByTerminalIdAndActive(terminalId, true);
    }

    public List<TerminalSettings> getAll() {

        return terminalSettingsRepository.findAll();
    }

    public List<TerminalSettings> getByActive(Boolean active) {

        return terminalSettingsRepository.findByActive(active);
    }

    public Long getCountByActive(Boolean active) {

        return terminalSettingsRepository.countByActive(active);
    }

    public List<Long> getAllTerminalIds() {

        return terminalSettingsRepository.getAllTerminalIds();
    }

}
