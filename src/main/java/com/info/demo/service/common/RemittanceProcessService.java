package com.info.demo.service.common;

import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.StopRemittanceData;

import java.util.Date;
import java.util.List;

public interface RemittanceProcessService {

    List<RemittanceData> saveAllRemittanceData(List<RemittanceData> remittanceDataList);

    void processDownloadData(List<RemittanceData> remittanceDataList, String exchangeCode, String exchangeName);

    void processStopRemittanceCancelData(List<StopRemittanceData> stopRemittanceDataList, Date businessDate);

    List<StopRemittanceData> saveAllStopRemittance(List<StopRemittanceData> stopRemittanceDataList);

    void saveWebOrSpotData(RemittanceData data, String exchangeCode, String exchangeName);

}
