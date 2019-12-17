package com.pineapplepayments.paya.connector.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SequenceGenerator(name = TransactionInformation.SEQ_TRANSACTION_INFORMATION, sequenceName = TransactionInformation.SEQ_TRANSACTION_INFORMATION, allocationSize = 1)
@Entity(name = "transaction_information")
public class TransactionInformation extends AbstractAuditable {

    private static final long serialVersionUID = -3092936274229893893L;

    public static final String SEQ_TRANSACTION_INFORMATION = "SEQ_TRANSACTION_INFORMATION";

    @Id
    @GeneratedValue(generator = SEQ_TRANSACTION_INFORMATION, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "transaction_guid")
    private String transactionGuid;

    @Column(name = "transaction_type")
    private String transactionType;

    @NotNull
    @Column(name = "merchant_id")
    private String merchantId;

    @NotNull
    @Column(name = "terminal_id")
    private Long terminalId;

    @NotNull
    @Column(name = "identifier")
    private String identifier;

    @NotNull
    @Column(name = "control_char")
    private String controlChar;

    @Column(name = "verfication_only")
    private Boolean verificationOnly;

    @NotNull
    @Column(name = "routing_number")
    private Long rountingNumber;

    @NotNull
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "check_number")
    private Long checkNumber;

    @NotNull
    @Column(name = "account_type")
    @Size(min = 5, max = 120)
    private String accountType;

    @Column(name = "company_name")
    @Size(min = 10, max = 120)
    private String companyName;

    @NotNull
    @Column(name = "first_name")
    @Size(min = 3, max = 120)
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 1, max = 120)
    private String lastName;

    @NotNull
    @Column(name = "address1")
    @Size(min = 5, max = 1200)
    private String address1;

    @NotNull
    @Column(name = "address2")
    @Size(min = 5, max = 1200)
    private String address2;

    @NotNull
    @Column(name = "city")
    @Size(min = 2, max = 1200)
    private String city;

    @NotNull
    @Column(name = "state")
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @Column(name = "zip_code")
    private Integer zipCode;

    @NotNull
    @Column(name = "phone_number")
    @Size(min = 10, max = 10, message = "The phone number should 10 digit")
    private String phoneNumber;

    @NotNull
    @Column(name = "dl_state")
    @Size(min = 2, max = 2)
    private String dlState;

    @NotNull
    @Column(name = "dl_number")
    @Size(min = 4, max = 15)
    private String dlNumber;

    @NotNull
    @Column(name = "courtsey_card_id")
    @Size(min = 10, max = 120)
    private String courtseyCardId;

    @Column(name = "dob_year")
    private Integer dobYear;

    @NotNull
    @Column(name = "check_amount")
    private Double checkAmount;

    @Column(name = "micr_data")
    private String micrData;

    @Column(name = "check_image_front")
    private byte[] checkImageFront;

    @Column(name = "check_image_back")
    private byte[] checkImageBack;

    @Column(name = "ecode_Options")
    private String ecodeOptions;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTransactionGuid() {
        return transactionGuid;
    }

    public void setTransactionGuid(String transactionGuid) {
        this.transactionGuid = transactionGuid;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

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

    public Boolean isVerificationOnly() {
        return verificationOnly;
    }

    public void setVerificationOnly(Boolean verificationOnly) {
        this.verificationOnly = verificationOnly;
    }

    public Long getRountingNumber() {
        return rountingNumber;
    }

    public void setRountingNumber(Long rountingNumber) {
        this.rountingNumber = rountingNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Long checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDlState() {
        return dlState;
    }

    public void setDlState(String dlState) {
        this.dlState = dlState;
    }

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    public String getCourtseyCardId() {
        return courtseyCardId;
    }

    public void setCourtseyCardId(String courtseyCardId) {
        this.courtseyCardId = courtseyCardId;
    }

    public Integer getDobYear() {
        return dobYear;
    }

    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getMicrData() {
        return micrData;
    }

    public void setMicrData(String micrData) {
        this.micrData = micrData;
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
