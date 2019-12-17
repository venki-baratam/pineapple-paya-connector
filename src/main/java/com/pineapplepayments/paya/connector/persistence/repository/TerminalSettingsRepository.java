package com.pineapplepayments.paya.connector.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pineapplepayments.paya.connector.persistence.entity.TerminalSettings;

@Repository
public interface TerminalSettingsRepository extends JpaRepository<TerminalSettings, Long> {

    TerminalSettings findByTerminalId(Long terminalId);

    TerminalSettings findByTerminalIdAndActive(Long terminalId, Boolean active);

    List<TerminalSettings> findByActive(Boolean active);

    Long countByActive(Boolean active);

    @Query("select terminalId from TerminalSettings")
    List<Long> getAllTerminalIds();

}
