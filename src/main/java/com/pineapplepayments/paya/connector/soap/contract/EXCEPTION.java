package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EXCEPTION")
public class EXCEPTION {

    private String MESSAGE;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String mESSAGE) {
        MESSAGE = mESSAGE;
    }

}
