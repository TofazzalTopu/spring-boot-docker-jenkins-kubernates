package com.info.demo.service.instantCash;

import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;

import java.util.List;

public interface ICOutstandingRemittanceService {

    @Deprecated
    List<RemittanceData> fetchICOutstandingRemittance(ExchangeHouseProperty exchangeHouseProperty, final String key, final String agentId,  final String password);
    List<RemittanceData> fetchICOutstandingRemittance(ICExchangePropertyDTO icExchangePropertyDTO);


}
