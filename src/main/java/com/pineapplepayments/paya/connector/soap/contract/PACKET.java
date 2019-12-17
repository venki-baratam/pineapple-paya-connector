package com.pineapplepayments.paya.connector.soap.contract;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "IDENTIFIER", "ACCOUNT", "CONSUMER", "CHECK" })
public class PACKET {
    private String IDENTIFIER;
    private ACCOUNT ACCOUNT;
    private CONSUMER CONSUMER;
    private CHECK CHECK;

    public String getIDENTIFIER() {
        return IDENTIFIER;
    }

    public void setIDENTIFIER(String iDENTIFIER) {
        IDENTIFIER = iDENTIFIER;
    }

    public ACCOUNT getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(ACCOUNT aCCOUNT) {
        ACCOUNT = aCCOUNT;
    }

    public CONSUMER getCONSUMER() {
        return CONSUMER;
    }

    public void setCONSUMER(CONSUMER cONSUMER) {
        CONSUMER = cONSUMER;
    }

    public CHECK getCHECK() {
        return CHECK;
    }

    public void setCHECK(CHECK cHECK) {
        CHECK = cHECK;
    }

}
