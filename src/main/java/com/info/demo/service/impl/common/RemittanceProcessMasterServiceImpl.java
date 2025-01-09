package com.info.demo.service.impl.common;

import com.info.demo.entity.RemittanceProcessMaster;
import com.info.demo.repository.RemittanceProcessMasterRepository;
import com.info.demo.service.common.RemittanceProcessMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RemittanceProcessMasterServiceImpl implements RemittanceProcessMasterService {

    private static final Logger logger = LoggerFactory.getLogger(RemittanceProcessMasterServiceImpl.class);

    @Autowired
    private RemittanceProcessMasterRepository remittanceProcessMasterRepository;

    @Override
    public RemittanceProcessMaster save(RemittanceProcessMaster master) {
        return remittanceProcessMasterRepository.save(master);
    }

    @Override
    public RemittanceProcessMaster findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatus(Date date, String exchangeCode, int api_data, String processStatus) {
        return remittanceProcessMasterRepository.findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatusOrderByIdDesc(date, exchangeCode, api_data, processStatus);
    }
}
