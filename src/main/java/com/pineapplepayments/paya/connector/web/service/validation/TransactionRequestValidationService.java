
package com.pineapplepayments.paya.connector.web.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.pineapplepayments.paya.connector.contants.ACHContants;
import com.pineapplepayments.paya.connector.contants.Contants;
import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionInformation;
import com.pineapplepayments.paya.connector.persistence.service.TerminalSettingsService;

@Service
public class TransactionRequestValidationService {

    @Autowired
    private TerminalSettingsService terminalSettingsService;

    public Boolean validateRequest(TransactionInformation transactionInformation, BindingResult bindingResult) {
        Boolean isValid = true;
        TerminalSettings terminalSettings = terminalSettingsService
                .getActiveTerminalDetailsByTerminalId(transactionInformation.getTerminalId());

        if (terminalSettings == null || StringUtils.isEmpty(terminalSettings.getTerminalId())) {
            bindingResult.rejectValue("terminalId", "400", "Invalid terminalId. No master data for given terminalId");
            isValid = false;
        }

        return isValid;
    }

    private static boolean validate(String field) {
        return !StringUtils.isEmpty(field);
    }

    private static boolean validateWithEqualToOrLessThanLength(String field, int length) {
        return validate(field) && field.length() <= length;
    }

    private static boolean validateWithEqualToOrLessThanWithRegEx(String field, int length, String regEx) {
        return validateWithEqualToOrLessThanLength(field, length) && field.matches(regEx);
    }

    private static boolean validateWithEqualLength(String field, int length) {
        return validate(field) && field.length() == length;
    }

    private static boolean validateWithEqualLengthWithRegEx(String field, int length, String regEx) {
        return validateWithEqualLength(field, length) && field.matches(regEx);
    }

    // validating MerchantId
    public static boolean validateMerchantId(String merchantId) {
        return validateWithEqualToOrLessThanWithRegEx(merchantId, ACHContants.MERCHANT_ID_LENGTH, Contants.RegEx.ALPHA_NUMERIC);
    }

    // Validating TerminalId
    public static boolean validateTerminalId(String terminalId) {
        return validateWithEqualToOrLessThanWithRegEx(terminalId, ACHContants.TERMINAL_ID_LENGTH, Contants.RegEx.NUMERIC_ONLY);
    }

    // Validating MerchantName
    public static boolean validateMerchantName(String merchantName) {
        return validateWithEqualToOrLessThanWithRegEx(merchantName, ACHContants.MERCHANT_NAME_LENGTH, Contants.RegEx.ALPHA_NAME);
    }

    // Validating City
    public static boolean validateMerchantCity(String merchantCity) {
        return validateWithEqualToOrLessThanWithRegEx(merchantCity, ACHContants.MERCHANT_CITY_LENGTH,
                Contants.RegEx.ALPHA_NUMERIC);
    }

    // Validating state
    public static boolean validateMerchantState(String merchantState) {
        return validateWithEqualLengthWithRegEx(merchantState, ACHContants.MERCHANT_STATE_LENGTH, Contants.RegEx.ALPHA_STATE_US);
    }

    // Validating zipCode
    public static boolean validateMerchantZipcode(String zipcode) {
        return validateWithEqualToOrLessThanWithRegEx(zipcode, ACHContants.MERCHANT_ZIPCODE_LENGTH, Contants.RegEx.NUMERIC_ONLY);
    }

}
