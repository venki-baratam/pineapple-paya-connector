package com.pineapplepayments.paya.connector.errorhandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class Error {

    private String timestamp = new Date().toString();

    private Integer code;

    private String message;

    private String description;

    private HttpStatus status;

    private List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public Error(HttpStatus status) {
        this();
        this.status = status;
        code = status.value();
    }

    public Error(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        code = status.value();
        message = "Unexpected error";
        description = ex.getLocalizedMessage();
    }

    public Error(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        code = status.value();
        this.message = message;
        description = ex.getLocalizedMessage();
    }

    public Error(HttpStatus status, String message) {
        this();
        this.status = status;
        code = status.value();
        this.message = message;
        description = message;
    }

    public Error() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
