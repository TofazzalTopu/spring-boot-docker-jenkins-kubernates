package com.info.demo.repository;

import com.info.demo.entity.ICCashRemittanceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICCashRemittanceDataRepository extends JpaRepository<ICCashRemittanceData, Long> {
    Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNoAndMiddlewarePushAndProcessStatus(String exchangeCode, String referenceNo, Integer middlewarePush, String processStatus);

    Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNo(String exchangeCode, String referenceNo);
}
