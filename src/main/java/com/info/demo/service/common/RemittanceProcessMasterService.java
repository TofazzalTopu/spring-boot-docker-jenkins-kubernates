package com.info.demo.service.common;

import com.info.demo.entity.RemittanceProcessMaster;

import java.util.Date;

public interface RemittanceProcessMasterService {

    RemittanceProcessMaster save(RemittanceProcessMaster master);
    RemittanceProcessMaster findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatus(Date date, String exchangeCode, int api_data, String processStatus);

}
