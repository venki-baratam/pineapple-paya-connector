package com.pineapplepayments.paya.connector.persistence.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.pineapplepayments.paya.connector.contants.TransactionTypes;

@SequenceGenerator(name = TransactionLog.SEQ_TRANSACTION_LOG, sequenceName = TransactionLog.SEQ_TRANSACTION_LOG, allocationSize = 1)
@Entity(name = "transaction_log")
public class TransactionLog extends AbstractAuditable {

	private static final long serialVersionUID = 4109203923290247639L;

	public static final String SEQ_TRANSACTION_LOG = "SEQ_TRANSACTION_LOG";

	@Id
	@GeneratedValue(generator = SEQ_TRANSACTION_LOG, strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "transaction_guid")
	private String transactionGuid;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "transaction_type")
	private TransactionTypes transactionType;

	@NotNull
	@Column(name = "terminal_id")
	private Long terminalId;

	@Column(name = "request_id")
	private String requestId;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_time")
	private Date transactionTime;

	@NotNull
	@Column(name = "message_type")
	private String messageType;

	@Column(name = "response_message")
	private String responseMessage;

	@Column(name = "response_code")
	private Integer responseCode;

	public TransactionLog() {

	}

	public TransactionLog(TransactionInformation transactionInformation) {
		// transactionResponseId = transactionInformation.gettr; Todo
		transactionGuid = transactionInformation.getTransactionGuid();
		transactionType = transactionInformation.getTransactionType();
		terminalId = transactionInformation.getTerminalId();
		requestId = transactionInformation.getRequestId();
		transactionTime = transactionInformation.getCreatedOn();
		messageType = "REQUEST";
		responseMessage = "";
		responseCode = 0;
		String userName = System.getProperty("user.name");
		setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
		setCreatedBy(userName);
		setModifiedBy(userName);
	}

	public TransactionLog(TransactionResponse transactionResponse) {
		transactionGuid = transactionResponse.getTransactionGuid();
		transactionType = transactionResponse.getTransactionType();
		terminalId = transactionResponse.getTransactionInfoId().getTerminalId();
		requestId = transactionResponse.getRequestId();
		transactionTime = Timestamp.valueOf(LocalDateTime.now());
		messageType = "RESPONSE";
		responseMessage = transactionResponse.getResponseTypeText();
		responseCode = transactionResponse.getResultCode();
		String userName = System.getProperty("user.name");
		setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
		setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
		setCreatedBy(userName);
		setModifiedBy(userName);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	protected void setId(Long id) {
	}

	public String getTransactionGuid() {
		return transactionGuid;
	}

	public void setTransactionGuid(String transactionGuid) {
		this.transactionGuid = transactionGuid;
	}

	public TransactionTypes getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypes transactionType) {
		this.transactionType = transactionType;
	}

	public Long getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

}
