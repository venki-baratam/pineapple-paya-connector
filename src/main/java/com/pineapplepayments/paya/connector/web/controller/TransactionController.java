package com.pineapplepayments.paya.connector.web.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pineapplepayments.paya.connector.contants.ACHContants;
import com.pineapplepayments.paya.connector.errorhandlers.ErrorHandler;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionInformation;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionResponse;
import com.pineapplepayments.paya.connector.persistence.service.TransactionInformationService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionInformationService.class);

    @Autowired
    private TransactionInformationService transactionInformationService;

    @Autowired
    private ErrorHandler errorHandler;

    @PostMapping(path = "validate")
    public ResponseEntity<?> validateACHTransaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
        }

        TransactionResponse response = transactionInformationService.validateACHTransaction(transactionInformation,
                bindingResult);

        if (bindingResult.hasErrors()) {
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
        }

        if (response.getError() != null && !StringUtils.isEmpty(response.getError().getMessage())) {
            return new ResponseEntity<>(response.getError(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @PostMapping("authorization")
    public ResponseEntity<?> processACHTransaction(@Valid @RequestBody TransactionInformation transactionInformation,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
        }

        logger.debug(transactionInformation.getMerchantId());

        TransactionResponse response = transactionInformationService.processACHTransaction(transactionInformation, bindingResult);

        if (bindingResult.hasErrors()) {
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
        }

        if (response.getError() != null && !StringUtils.isEmpty(response.getError().getMessage())) {
            return new ResponseEntity<>(response.getError(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping(path = "credit")
    public ResponseEntity<?> processCreditsTranaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionGuid(UUID.randomUUID().toString());
        response.setTransactionType(transactionInformation.getTransactionType());
        response.setRequestId(UUID.randomUUID().toString());
        response.setSecCode(ACHContants.SEC_CODE_TEL);
        response.setResponseType("Credit");
        response.setResponseTypeText("APPROVED");
        response.setTypeCode(4096);
        response.setCode("CREDIT ACCEPTED");
        response.setResultCode(8);
        response.setMessage("CREDIT ACCEPTED");
        response.setStatus(false);
        response.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        response.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        String userName = System.getProperty("user.name");
        response.setCreatedBy(userName);
        response.setModifiedBy(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "void")
    public ResponseEntity<?> voidACHTransaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionGuid(UUID.randomUUID().toString());
        response.setTransactionType(transactionInformation.getTransactionType());
        response.setRequestId(UUID.randomUUID().toString());
        response.setSecCode(ACHContants.SEC_CODE_TEL);
        response.setResponseType("A");
        response.setResponseTypeText("APPROVED");
        response.setTypeCode(5120);
        response.setCode("VOID ACCEPTED");
        response.setResultCode(0);
        response.setMessage("VOID ACCEPTED");
        response.setStatus(false);
        response.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        response.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        String userName = System.getProperty("user.name");
        response.setCreatedBy(userName);
        response.setModifiedBy(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "reverse")
    public ResponseEntity<?> reverseACHTransaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionGuid(UUID.randomUUID().toString());
        response.setTransactionType(transactionInformation.getTransactionType());
        response.setRequestId(UUID.randomUUID().toString());
        response.setSecCode(ACHContants.SEC_CODE_TEL);
        response.setResponseType("A");
        response.setResponseTypeText("APPROVED");
        response.setTypeCode(5120);
        response.setCode("REVERSAL ACCEPTED");
        response.setResultCode(0);
        response.setMessage("REVERSAL ACCEPTED");
        response.setStatus(false);
        response.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        response.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        String userName = System.getProperty("user.name");
        response.setCreatedBy(userName);
        response.setModifiedBy(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "recurring")
    public ResponseEntity<?> recurringACHTransaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionGuid(UUID.randomUUID().toString());
        response.setTransactionType(transactionInformation.getTransactionType());
        response.setRequestId(UUID.randomUUID().toString());
        response.setSecCode(ACHContants.SEC_CODE_TEL);
        response.setResponseType("Passed");
        response.setResponseTypeText("APPROVED");
        response.setTypeCode(5120);
        response.setCode("RECURRING ACCEPTED");
        response.setResultCode(0);
        response.setMessage("RECURRING ACCEPTED");
        response.setStatus(false);
        response.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        response.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        String userName = System.getProperty("user.name");
        response.setCreatedBy(userName);
        response.setModifiedBy(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "override")
    public ResponseEntity<?> overrideACHTransaction(
            @Valid @RequestBody TransactionInformation transactionInformation, BindingResult bindingResult) throws Exception {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionGuid(UUID.randomUUID().toString());
        response.setTransactionType(transactionInformation.getTransactionType());
        response.setRequestId(UUID.randomUUID().toString());
        response.setSecCode(ACHContants.SEC_CODE_TEL);
        response.setResponseType("Passed");
        response.setResponseTypeText("APPROVED");
        response.setTypeCode(5120);
        response.setCode("OVERRIDE ACCEPTED");
        response.setResultCode(0);
        response.setMessage("OVERRIDE ACCEPTED");
        response.setStatus(false);
        response.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        response.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        String userName = System.getProperty("user.name");
        response.setCreatedBy(userName);
        response.setModifiedBy(userName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}