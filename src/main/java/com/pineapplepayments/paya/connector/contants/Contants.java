package com.pineapplepayments.paya.connector.contants;

public class Contants {
    public static class RegEx {
        public static final String NUMERIC_ONLY = "[0-9]+";
        public static final String ALPHA_NUMERIC = "[a-zA-Z0-9]+";
        public static final String ALPHA_NUMERIC_ADDRESS = "\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?";
        public static final String ALPHA_NAME = "^[\\p{L} .'-]+$";
        public static final String ALPHA_STATE_US = "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT\n"
                + "|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY";
    }
}
