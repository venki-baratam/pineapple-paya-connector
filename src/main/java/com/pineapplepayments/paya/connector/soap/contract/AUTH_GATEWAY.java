package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AUTH_GATEWAY")
@XmlAccessorType(XmlAccessType.FIELD)
public class AUTH_GATEWAY {

    private TRANSACTION TRANSACTION;
    @XmlAttribute(name = "REQUEST_ID")
    private String REQUEST_ID;

    // Getter Methods

    public TRANSACTION getTRANSACTION() {
        return TRANSACTION;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    // Setter Methods

    public void setTRANSACTION(TRANSACTION TRANSACTION) {
        this.TRANSACTION = TRANSACTION;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }
}