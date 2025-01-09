package com.info.demo.service.impl.instantCash;

import com.info.demo.entity.ICCashRemittanceData;
import com.info.demo.repository.ICCashRemittanceDataRepository;
import com.info.demo.service.instantCash.ICCashRemittanceDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ICCashRemittanceDataServiceImpl implements ICCashRemittanceDataService {

    private final ICCashRemittanceDataRepository icCashRemittanceDataRepository;

    public ICCashRemittanceDataServiceImpl(ICCashRemittanceDataRepository icCashRemittanceDataRepository) {
        this.icCashRemittanceDataRepository = icCashRemittanceDataRepository;
    }

    @Override
    public ICCashRemittanceData save(ICCashRemittanceData remittanceData) {
        return icCashRemittanceDataRepository.save(remittanceData);
    }

    @Override
    public Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNoAndProcessStatus(String exchangeCode, String referenceNo, Integer middlewarePush, String processStatus) {
        return icCashRemittanceDataRepository.findByExchangeCodeAndReferenceNoAndMiddlewarePushAndProcessStatus(exchangeCode, referenceNo, middlewarePush, processStatus);
    }

    @Override
    public Optional<ICCashRemittanceData> findByExchangeCodeAndReferenceNo(String exchangeCode, String referenceNo) {
        return icCashRemittanceDataRepository.findByExchangeCodeAndReferenceNo(exchangeCode, referenceNo);
    }


}
