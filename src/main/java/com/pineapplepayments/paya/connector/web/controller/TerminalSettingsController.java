package com.pineapplepayments.paya.connector.web.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.pineapplepayments.paya.connector.errorhandlers.ErrorHandler;
import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;
import com.pineapplepayments.paya.connector.persistence.service.TerminalSettingsService;
import com.pineapplepayments.paya.connector.web.contract.Request;
import com.pineapplepayments.paya.connector.web.service.validation.TerminalSettingsValidationService;

@RestController
public class TerminalSettingsController {

    private static final Logger logger = LoggerFactory.getLogger(TerminalSettingsController.class);

    @Autowired
    private TerminalSettingsService terminalSettingsService;

    @Autowired
    private ErrorHandler errorHandler;

    @Autowired
    private TerminalSettingsValidationService terminalSettingsValidationService;

    @PostMapping("addTerminalSettings")
    public ResponseEntity<?> addTerminalSettings(@Valid @RequestBody Request request, BindingResult bindingResult) {

        terminalSettingsValidationService.validate(request, bindingResult, "save");

        if (bindingResult.hasErrors())
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);

        logger.debug("Called saveTerminalSettings endpoint for terminalId: " + request.getTerminalId());

        TerminalSettings response = terminalSettingsService.addTerminalSettings(request, bindingResult);

        if (response.getError() != null && !StringUtils.isEmpty(response.getError().getMessage()))
            return new ResponseEntity<>(response.getError(), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully saved terminal settings for terminalId:" + request.getTerminalId(),
                    HttpStatus.OK);
    }

    @PostMapping("removeTerminalSettings")
    public ResponseEntity<?> removeTerminalSettings(@Valid @RequestBody Request request, BindingResult bindingResult) {

        terminalSettingsValidationService.validate(request, bindingResult, "remove");

        if (bindingResult.hasErrors())
            return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);

        logger.debug("Called removeTerminalSettings endpoint for terminalId: " + request.getTerminalId());

        TerminalSettings response = terminalSettingsService.removeTerminalSettings(request, bindingResult);

        if (response.getError() != null && !StringUtils.isEmpty(response.getError().getMessage()))
            return new ResponseEntity<>(response.getError(), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("Successfully removed terminal settings for terminalId:" + request.getTerminalId(),
                    HttpStatus.OK);
    }

}