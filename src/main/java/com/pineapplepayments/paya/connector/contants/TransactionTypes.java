package com.pineapplepayments.paya.connector.contants;

public enum TransactionTypes{
    Validate("Validate"),
    Authorization("Authorization"),
    Credit("Credit"),
    Void("Void"),
    Reverse("Reverse"),
    Recurring("Recurring"),
    Override("Override");
    
    
    private String value;
    
    TransactionTypes(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.getValue();
    }
    
    public String getValue() {
        return value;
    }
 }
