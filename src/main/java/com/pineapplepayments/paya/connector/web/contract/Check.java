package com.pineapplepayments.paya.connector.web.contract;

public class Check {
    private double checkAmount;
    private byte[] checkImageFront;
    private byte[] checkImageBack;
    private String ecodeOptions;

    public double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public byte[] getCheckImageFront() {
        return checkImageFront;
    }

    public void setCheckImageFront(byte[] checkImageFront) {
        this.checkImageFront = checkImageFront;
    }

    public byte[] getCheckImageBack() {
        return checkImageBack;
    }

    public void setCheckImageBack(byte[] checkImageBack) {
        this.checkImageBack = checkImageBack;
    }

    public String getEcodeOptions() {
        return ecodeOptions;
    }

    public void setEcodeOptions(String ecodeOptions) {
        this.ecodeOptions = ecodeOptions;
    }

}
