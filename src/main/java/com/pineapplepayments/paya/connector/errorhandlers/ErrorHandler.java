package com.pineapplepayments.paya.connector.errorhandlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
public class ErrorHandler {

    public ResponseEntity<ErrorResponse> getErrorResponseEntityForBindingErrors(BindingResult bindingResult) {
        Error error = new Error();
        error.setCode(400);
        error.setMessage("Binding Error");
        error.setDescription("Error while binding request object");

        if (bindingResult.hasFieldErrors())
            for (FieldError fieldError : bindingResult.getFieldErrors())
                if (fieldError.getField().contains("Date")) {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String errorDate = dateFormat.format(fieldError.getRejectedValue());
                    error.getFieldErrors().add(new FieldValidationError(fieldError.getObjectName(), fieldError.getField(),
                            errorDate, fieldError.getDefaultMessage()));
                } else
                    error.getFieldErrors().add(new FieldValidationError(fieldError.getObjectName(), fieldError.getField(),
                            fieldError.getRejectedValue(), fieldError.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(error);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
