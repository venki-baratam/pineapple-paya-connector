package com.pineapplepayments.paya.connector.web.contract;

public class Account {
    private String micrData;

    public String getMicrData() {
        return micrData;
    }

    public void setMicrData(String micrData) {
        this.micrData = micrData;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private long routingNumber;
    private long accountNumber;

    public long getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(long checkNumber) {
        this.checkNumber = checkNumber;
    }

    private long checkNumber;
    private String accountType;
}
