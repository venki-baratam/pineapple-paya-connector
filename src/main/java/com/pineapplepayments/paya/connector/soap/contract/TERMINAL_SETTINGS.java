package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TERMINAL_SETTINGS")
public class TERMINAL_SETTINGS {

    private Long TERMINAL_ID;
    private String SEC_CODE;
    private Boolean IS_GATEWAY_TERMINAL;
    private Boolean ALLOW_CNSMR_CREDITS;
    private Boolean DL_REQUIRED;
    private Boolean RUN_CHECK_VERIFICATION;
    private Boolean RUN_IDENTITY_VERIFICATION;
    private String SCHEMA_FILE_PATH;
    private String XML_TEMPLATE_PATH;

    // Getter Methods

    public Long getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public String getSEC_CODE() {
        return SEC_CODE;
    }

    public Boolean getIS_GATEWAY_TERMINAL() {
        return IS_GATEWAY_TERMINAL;
    }

    public Boolean getALLOW_CNSMR_CREDITS() {
        return ALLOW_CNSMR_CREDITS;
    }

    public Boolean getDL_REQUIRED() {
        return DL_REQUIRED;
    }

    public Boolean getRUN_CHECK_VERIFICATION() {
        return RUN_CHECK_VERIFICATION;
    }

    public Boolean getRUN_IDENTITY_VERIFICATION() {
        return RUN_IDENTITY_VERIFICATION;
    }

    public String getSCHEMA_FILE_PATH() {
        return SCHEMA_FILE_PATH;
    }

    public String getXML_TEMPLATE_PATH() {
        return XML_TEMPLATE_PATH;
    }

    // Setter Methods

    public void setTERMINAL_ID(Long TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }

    public void setSEC_CODE(String SEC_CODE) {
        this.SEC_CODE = SEC_CODE;
    }

    public void setIS_GATEWAY_TERMINAL(Boolean IS_GATEWAY_TERMINAL) {
        this.IS_GATEWAY_TERMINAL = IS_GATEWAY_TERMINAL;
    }

    public void setALLOW_CNSMR_CREDITS(Boolean ALLOW_CNSMR_CREDITS) {
        this.ALLOW_CNSMR_CREDITS = ALLOW_CNSMR_CREDITS;
    }

    public void setDL_REQUIRED(Boolean DL_REQUIRED) {
        this.DL_REQUIRED = DL_REQUIRED;
    }

    public void setRUN_CHECK_VERIFICATION(Boolean RUN_CHECK_VERIFICATION) {
        this.RUN_CHECK_VERIFICATION = RUN_CHECK_VERIFICATION;
    }

    public void setRUN_IDENTITY_VERIFICATION(Boolean RUN_IDENTITY_VERIFICATION) {
        this.RUN_IDENTITY_VERIFICATION = RUN_IDENTITY_VERIFICATION;
    }

    public void setSCHEMA_FILE_PATH(String SCHEMA_FILE_PATH) {
        this.SCHEMA_FILE_PATH = SCHEMA_FILE_PATH;
    }

    public void setXML_TEMPLATE_PATH(String XML_TEMPLATE_PATH) {
        this.XML_TEMPLATE_PATH = XML_TEMPLATE_PATH;
    }
}
