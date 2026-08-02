package com.info.demo.service.common;

import com.info.demo.entity.ExchangeHouseProperty;

import java.util.List;
import java.util.Optional;

public interface ExchangeHousePropertyService {

    List<ExchangeHouseProperty> findAll();

    Optional<ExchangeHouseProperty> findByExchangeCodeAndKeyLabel(String exchangeCode, String keyLabel);

    List<ExchangeHouseProperty> findAllByExchangeCode(String exchangeCode);

    ExchangeHouseProperty findByKeyLabelEquals(String keyLabel);

    public void loadBulkLoadData();

}
