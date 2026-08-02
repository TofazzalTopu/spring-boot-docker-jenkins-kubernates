package com.info.demo.service.instantCash;

import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICUnlockRemittanceService {
    @Deprecated
    List<RemittanceData> unlockICOutstandingRemittance(ExchangeHouseProperty exchangeHouseProperty, List<RemittanceData> remittanceDataList, final String key, final String password);
    void unlockICOutstandingRemittance(ICExchangePropertyDTO icExchangePropertyDTO);
    String unlockICOutstandingRemittance(@NotNull String referenceNo, @NotNull ICExchangePropertyDTO icExchangePropertyDTO);

}
