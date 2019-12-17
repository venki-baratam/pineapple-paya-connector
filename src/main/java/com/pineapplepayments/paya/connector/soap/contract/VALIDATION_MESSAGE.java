package com.pineapplepayments.paya.connector.soap.contract;

import java.util.List;

public class VALIDATION_MESSAGE {
    private String RESULT;
    private String SCHEMA_FILE_PATH;
    private List<VALIDATION_ERROR> VALIDATION_ERROR;
    // private String MESSAGE;

    // Getter Methods

    public String getRESULT() {
        return RESULT;
    }

    public List<VALIDATION_ERROR> getVALIDATION_ERROR() {
        return VALIDATION_ERROR;
    }

    public void setVALIDATION_ERROR(List<VALIDATION_ERROR> vALIDATION_ERROR) {
        VALIDATION_ERROR = vALIDATION_ERROR;
    }

    public String getSCHEMA_FILE_PATH() {
        return SCHEMA_FILE_PATH;
    }

    // Setter Methods

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public void setSCHEMA_FILE_PATH(String SCHEMA_FILE_PATH) {
        this.SCHEMA_FILE_PATH = SCHEMA_FILE_PATH;
    }
}
