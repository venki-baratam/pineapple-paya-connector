package com.pineapplepayments.paya.connector.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pineapplepayments.paya.connector.contants.TransactionTypes;
import com.pineapplepayments.paya.connector.errorhandlers.Error;
import com.pineapplepayments.paya.connector.soap.contract.RESPONSE;

@SequenceGenerator(name = TransactionResponse.SEQ_TRANSACTION_RESPONSE, sequenceName = TransactionResponse.SEQ_TRANSACTION_RESPONSE, allocationSize = 1)
@Entity(name = "transaction_response")
public class TransactionResponse extends AbstractAuditable {

	private static final long serialVersionUID = -7139069204430880832L;

	public static final String SEQ_TRANSACTION_RESPONSE = "SEQ_TRANSACTION_RESPONSE";

	@Id
	@GeneratedValue(generator = SEQ_TRANSACTION_RESPONSE, strategy = GenerationType.SEQUENCE)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "trasaction_info_id", nullable = false)
	private TransactionInformation transactionInfoId;

	@NotNull
	@Column(name = "transaction_guid")
	private String transactionGuid;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "transaction_type")
	private TransactionTypes transactionType;

	@NotNull
	@Column(name = "request_id")
	private String requestId;

	@NotNull
	@Column(name = "sec_code")
	private String secCode;

	@Column(name = "response_Type")
	private String responseType;

	@Column(name = "response_type_text")
	private String responseTypeText;

	@Column(name = "result_code")
	private Integer resultCode;

	@Column(name = "type_code")
	private Integer typeCode;

	@Column(name = "code")
	private String code;

	@Column(name = "message")
	private String message;

	@Column(name = "tx_status")
	private Boolean status = false;

	@Transient
	private Error error;

	public TransactionResponse(RESPONSE processSingleResponse) {
		transactionGuid = processSingleResponse.getAUTHORIZATION_MESSAGE().getTRANSACTION_ID();
		requestId = processSingleResponse.getREQUEST_ID();
		responseType = processSingleResponse.getAUTHORIZATION_MESSAGE().getRESPONSE_TYPE();
		responseTypeText = processSingleResponse.getAUTHORIZATION_MESSAGE().getRESPONSE_TYPE_TEXT();
		resultCode = Integer.parseInt(processSingleResponse.getAUTHORIZATION_MESSAGE().getRESULT_CODE());
		typeCode = Integer.parseInt(processSingleResponse.getAUTHORIZATION_MESSAGE().getTYPE_CODE());
		code = processSingleResponse.getAUTHORIZATION_MESSAGE().getCODE();
		message = processSingleResponse.getAUTHORIZATION_MESSAGE().getMESSAGE();

	}

	public TransactionResponse() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	protected void setId(Long id) {

	}

	public TransactionInformation getTransactionInfoId() {
		return transactionInfoId;
	}

	public void setTransactionInfoId(TransactionInformation transactionInfoId) {
		this.transactionInfoId = transactionInfoId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getResponseTypeText() {
		return responseTypeText;
	}

	public void setResponseTypeText(String responseTypeText) {
		this.responseTypeText = responseTypeText;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public TransactionTypes getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypes transactionType) {
		this.transactionType = transactionType;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTransactionGuid() {
		return transactionGuid;
	}

	public void setTransactionGuid(String transactionGuid) {
		this.transactionGuid = transactionGuid;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}
}
