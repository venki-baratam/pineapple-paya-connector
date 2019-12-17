package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "ROUTING_NUMBER", "ACCOUNT_NUMBER", "CHECK_NUMBER", "ACCOUNT_TYPE" })
public class ACCOUNT {
    private String ROUTING_NUMBER;
    private String ACCOUNT_NUMBER;
    private String CHECK_NUMBER;
    private String ACCOUNT_TYPE;

    // Getter Methods

    public String getROUTING_NUMBER() {
        return ROUTING_NUMBER;
    }

    public String getACCOUNT_NUMBER() {
        return ACCOUNT_NUMBER;
    }

    public String getCHECK_NUMBER() {
        return CHECK_NUMBER;
    }

    public String getACCOUNT_TYPE() {
        return ACCOUNT_TYPE;
    }

    // Setter Methods

    public void setROUTING_NUMBER(String ROUTING_NUMBER) {
        this.ROUTING_NUMBER = ROUTING_NUMBER;
    }

    public void setACCOUNT_NUMBER(String ACCOUNT_NUMBER) {
        this.ACCOUNT_NUMBER = ACCOUNT_NUMBER;
    }

    public void setCHECK_NUMBER(String CHECK_NUMBER) {
        this.CHECK_NUMBER = CHECK_NUMBER;
    }

    public void setACCOUNT_TYPE(String ACCOUNT_TYPE) {
        this.ACCOUNT_TYPE = ACCOUNT_TYPE;
    }
}
