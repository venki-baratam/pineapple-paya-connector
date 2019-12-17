package com.pineapplepayments.paya.connector.soap.contract;

public class AUTHORIZATION_MESSAGE {
    private String TRANSACTION_ID;
    private String RESPONSE_TYPE;
    private String RESPONSE_TYPE_TEXT;
    private String RESULT_CODE;
    private String TYPE_CODE;
    private String CODE;
    private String MESSAGE;

    // Getter Methods

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public String getRESPONSE_TYPE() {
        return RESPONSE_TYPE;
    }

    public String getRESPONSE_TYPE_TEXT() {
        return RESPONSE_TYPE_TEXT;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public String getCODE() {
        return CODE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    // Setter Methods

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public void setRESPONSE_TYPE(String RESPONSE_TYPE) {
        this.RESPONSE_TYPE = RESPONSE_TYPE;
    }

    public void setRESPONSE_TYPE_TEXT(String RESPONSE_TYPE_TEXT) {
        this.RESPONSE_TYPE_TEXT = RESPONSE_TYPE_TEXT;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }
}