package com.pineapplepayments.paya.connector.persistence.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapplepayments.paya.connector.persistence.entity.TransactionLog;
import com.pineapplepayments.paya.connector.persistence.entity.TransactionResponse;
import com.pineapplepayments.paya.connector.persistence.repository.TransactionLogRepository;
import com.pineapplepayments.paya.connector.persistence.repository.TransactionResponseRepository;

@Service
@Transactional(readOnly = true)
public class TransactionResponseService {

    @Autowired
    private TransactionResponseRepository transactionResponseRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    /*
     *
     */
    @Transactional
    public TransactionResponse save(TransactionResponse transactionResponse) {
        populateAuditFields(transactionResponse);
        // saving the response data in to TransactionLog table
        transactionLogRepository.save(new TransactionLog(transactionResponse));
        return transactionResponseRepository.save(transactionResponse);

    }

    /*
     *
     */
    @Transactional
    public List<TransactionResponse> save(List<TransactionResponse> transactionResponseList) {
        for (TransactionResponse tr : transactionResponseList)
            populateAuditFields(tr);
        return transactionResponseRepository.saveAll(transactionResponseList);

    }

    /*
     *
     */
    private void populateAuditFields(TransactionResponse transactionResponse) {
        String userName = System.getProperty("user.name");
        if (transactionResponse.getId() == null) {
            transactionResponse.setCreatedBy(userName);
            transactionResponse.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        }
        transactionResponse.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
        transactionResponse.setModifiedBy(userName);

    }
}
