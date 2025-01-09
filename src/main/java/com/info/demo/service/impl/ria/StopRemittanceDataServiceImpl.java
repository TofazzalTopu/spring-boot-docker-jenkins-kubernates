package com.info.demo.service.impl.ria;

import com.info.demo.entity.StopRemittanceData;
import com.info.demo.repository.StopRemittanceDataRepository;
import com.info.demo.service.ria.StopRemittanceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopRemittanceDataServiceImpl implements StopRemittanceDataService {

    private static final Logger logger = LoggerFactory.getLogger(StopRemittanceDataServiceImpl.class);

    @Autowired
    private StopRemittanceDataRepository stopRemittanceDataRepository;

    @Override
    public StopRemittanceData save(StopRemittanceData stopRemittanceData) {
        return stopRemittanceDataRepository.save(stopRemittanceData);
    }

    @Override
    public List<StopRemittanceData> saveAll(List<StopRemittanceData> stopRemittanceDataList) {
        return stopRemittanceDataRepository.saveAll(stopRemittanceDataList);
    }
}
