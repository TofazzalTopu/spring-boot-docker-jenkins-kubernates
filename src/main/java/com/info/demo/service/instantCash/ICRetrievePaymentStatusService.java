package com.info.demo.service.instantCash;

import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.model.instantCash.ICPaymentStatusDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICRetrievePaymentStatusService {

    @Deprecated
    List<RemittanceData> retrievePaymentStatus(ExchangeHouseProperty exchangeHouseProperty, List<RemittanceData> remittanceDataList, final String key, final String password);
    ICPaymentStatusDTO retrievePaymentStatus(@NotNull String referenceNo, @NotNull ICExchangePropertyDTO dto);

}
