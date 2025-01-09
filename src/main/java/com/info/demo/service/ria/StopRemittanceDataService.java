package com.info.demo.service.ria;

import com.info.demo.entity.StopRemittanceData;

import java.util.List;

public interface StopRemittanceDataService {

    StopRemittanceData save(StopRemittanceData stopRemittanceData);
    List<StopRemittanceData> saveAll(List<StopRemittanceData> stopRemittanceDataList);

}
