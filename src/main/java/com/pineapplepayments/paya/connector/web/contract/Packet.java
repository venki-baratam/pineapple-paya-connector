package com.pineapplepayments.paya.connector.web.contract;

public class Packet {
    private String identifier;
    private String controlChar;
    private boolean verificationOnly;
    private Account account;
    private Consumer consumer;
    private Check check;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getControlChar() {
        return controlChar;
    }

    public void setControlChar(String controlChar) {
        this.controlChar = controlChar;
    }

    public boolean isVerificationOnly() {
        return verificationOnly;
    }

    public void setVerificationOnly(boolean verificationOnly) {
        this.verificationOnly = verificationOnly;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
