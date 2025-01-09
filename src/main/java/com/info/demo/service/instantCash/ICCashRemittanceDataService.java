package com.info.demo.service.instantCash;

import com.info.demo.entity.ICCashRemittanceData;

import java.util.Optional;

public interface ICCashRemittanceDataService {

    ICCashRemittanceData save(ICCashRemittanceData remittanceData);

    Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNoAndProcessStatus(String exchangeCode, String referenceNo, Integer middlewarePush, String processStatus);

    Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNo(String exchangeCode, String referenceNo);


}
