package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VALIDATION_ERROR")
@XmlAccessorType(XmlAccessType.FIELD)
public class VALIDATION_ERROR {
    @XmlAttribute(name = "LINE_NUMBER")
    private String LINE_NUMBER;
    @XmlAttribute(name = "LINE_POSITION")
    private String LINE_POSITION;
    private String SEVERITY;
    private String MESSAGE;

    public String getLINE_NUMBER() {
        return LINE_NUMBER;
    }

    public void setLINE_NUMBER(String lINE_NUMBER) {
        LINE_NUMBER = lINE_NUMBER;
    }

    public String getSEVERITY() {
        return SEVERITY;
    }

    public void setSEVERITY(String sEVERITY) {
        SEVERITY = sEVERITY;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        MESSAGE = mESSAGE;
    }

}
