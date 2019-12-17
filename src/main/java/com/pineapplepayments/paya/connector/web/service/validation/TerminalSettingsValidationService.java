
package com.pineapplepayments.paya.connector.web.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;
import com.pineapplepayments.paya.connector.persistence.service.TerminalSettingsService;
import com.pineapplepayments.paya.connector.web.contract.Request;

@Service
public class TerminalSettingsValidationService {

    @Autowired
    private TerminalSettingsService terminalSettingsService;

    public Boolean validate(Request request, BindingResult bindingResult, String method) {
        TerminalSettings terminalSettings;
        if ("save".equals(method)) {
            terminalSettings = terminalSettingsService.getActiveTerminalDetailsByTerminalId(request.getTerminalId());
            if (terminalSettings != null)
                bindingResult.rejectValue("terminalId", "500", "Terminal settings already exists with given terminalId");
        } else if ("remove".equals(method)) {
            terminalSettings = terminalSettingsService.getByTerminalId(request.getTerminalId());
            if (terminalSettings == null)
                bindingResult.rejectValue("terminalId", "500", "Terminal settings not exists with given terminalId");
        }

        return true;
    }

}
