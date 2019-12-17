package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class RESPONSE {

    private VALIDATION_MESSAGE VALIDATION_MESSAGE;
    private AUTHORIZATION_MESSAGE AUTHORIZATION_MESSAGE;
    @XmlAttribute(name = "REQUEST_ID")
    private String REQUEST_ID;

    private EXCEPTION EXCEPTION;

    public EXCEPTION getEXCEPTION() {
        return EXCEPTION;
    }

    public void setEXCEPTION(EXCEPTION eXCEPTION) {
        EXCEPTION = eXCEPTION;
    }

    public VALIDATION_MESSAGE getVALIDATION_MESSAGE() {
        return VALIDATION_MESSAGE;
    }

    public void setVALIDATION_MESSAGE(VALIDATION_MESSAGE vALIDATION_MESSAGE) {
        VALIDATION_MESSAGE = vALIDATION_MESSAGE;
    }

    public AUTHORIZATION_MESSAGE getAUTHORIZATION_MESSAGE() {
        return AUTHORIZATION_MESSAGE;
    }

    public void setAUTHORIZATION_MESSAGE(AUTHORIZATION_MESSAGE aUTHORIZATION_MESSAGE) {
        AUTHORIZATION_MESSAGE = aUTHORIZATION_MESSAGE;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

}
