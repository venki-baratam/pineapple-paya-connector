package com.pineapplepayments.paya.connector.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pineapplepayments.paya.connector.persistence.entity.TransactionInformation;

@Repository
public interface TransactionInformationRepository extends JpaRepository<TransactionInformation, Long> {

}
