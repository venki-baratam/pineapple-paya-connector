package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "COMPANY_NAME", "FIRST_NAME", "LAST_NAME", "ADDRESS1", "ADDRESS2", "CITY", "STATE", "ZIP", "PHONE_NUMBER",
        "DL_STATE",
        "DL_NUMBER", "COURTESY_CARD_ID", "IDENTITY" })
public class CONSUMER {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String ADDRESS1;
    private String ADDRESS2;
    private String CITY;
    private String STATE;
    private String ZIP;
    private String PHONE_NUMBER;
    private String DL_STATE;
    private String DL_NUMBER;
    private String COURTESY_CARD_ID;
    private IDENTITY IDENTITY;
    private String COMPANY_NAME;

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String fIRST_NAME) {
        FIRST_NAME = fIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String lAST_NAME) {
        LAST_NAME = lAST_NAME;
    }

    public String getADDRESS1() {
        return ADDRESS1;
    }

    public void setADDRESS1(String aDDRESS1) {
        ADDRESS1 = aDDRESS1;
    }

    public String getADDRESS2() {
        return ADDRESS2;
    }

    public void setADDRESS2(String aDDRESS2) {
        ADDRESS2 = aDDRESS2;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String cITY) {
        CITY = cITY;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String sTATE) {
        STATE = sTATE;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String zIP) {
        ZIP = zIP;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String pHONE_NUMBER) {
        PHONE_NUMBER = pHONE_NUMBER;
    }

    public String getDL_STATE() {
        return DL_STATE;
    }

    public void setDL_STATE(String dL_STATE) {
        DL_STATE = dL_STATE;
    }

    public String getDL_NUMBER() {
        return DL_NUMBER;
    }

    public void setDL_NUMBER(String dL_NUMBER) {
        DL_NUMBER = dL_NUMBER;
    }

    public String getCOURTESY_CARD_ID() {
        return COURTESY_CARD_ID;
    }

    public void setCOURTESY_CARD_ID(String cOURTESY_CARD_ID) {
        COURTESY_CARD_ID = cOURTESY_CARD_ID;
    }

    public IDENTITY getIDENTITY() {
        return IDENTITY;
    }

    public void setIDENTITY(IDENTITY iDENTITY) {
        IDENTITY = iDENTITY;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }
}