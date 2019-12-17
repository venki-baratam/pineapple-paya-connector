package com.pineapplepayments.paya.connector.soap.contract;

public class TRANSACTION {
    private String TRANSACTION_ID;
    private MERCHANT MERCHANT;
    private PACKET PACKET;

    // Getter Methods

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public MERCHANT getMERCHANT() {
        return MERCHANT;
    }

    public PACKET getPACKET() {
        return PACKET;
    }

    // Setter Methods

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public void setMERCHANT(MERCHANT MERCHANT) {
        this.MERCHANT = MERCHANT;
    }

    public void setPACKET(PACKET PACKET) {
        this.PACKET = PACKET;
    }
}
