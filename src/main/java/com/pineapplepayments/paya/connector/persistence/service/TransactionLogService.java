package com.pineapplepayments.paya.connector.persistence.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pineapplepayments.paya.connector.persistence.entity.TransactionLog;
import com.pineapplepayments.paya.connector.persistence.repository.TransactionLogRepository;

@Service
@Transactional(readOnly = true)
public class TransactionLogService {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    /*
     *
     */
    @Transactional
    public TransactionLog save(TransactionLog transactionLog) {
        populateAuditFields(transactionLog);
        return transactionLogRepository.save(transactionLog);

    }

    /*
     *
     */
    @Transactional
    public List<TransactionLog> save(List<TransactionLog> transactionLogList) {
        for (TransactionLog tl : transactionLogList)
            populateAuditFields(tl);
        return transactionLogRepository.saveAll(transactionLogList);

    }

    /*
     *
     */
    private void populateAuditFields(TransactionLog transactionLog) {
        String userName = System.getProperty("user.name");
        if (transactionLog.getId() == null) {
            transactionLog.setCreatedBy(userName);
            transactionLog.setCreatedOn(new Date());
        }
        transactionLog.setModifiedOn(new Date());
        transactionLog.setModifiedBy(userName);

    }

}
