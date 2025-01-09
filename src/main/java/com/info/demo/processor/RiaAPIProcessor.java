package com.info.demo.processor;

import com.info.demo.model.ria.RiaExchangePropertyDTO;
import com.info.demo.service.impl.ria.RiaExchangeApiProcessorService;
import com.info.demo.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiaAPIProcessor {


    public static final Logger logger = LoggerFactory.getLogger(RiaAPIProcessor.class);

    @Autowired
    private RiaExchangeApiProcessorService riaExchangeApiProcessorService;

    public void process() {
        try {
            RiaExchangePropertyDTO props = ApiUtil.getRiaExchangePropertyDTO();
            if (ApiUtil.validateIfRIAPropertiesIsNotExist(props)) {
                logger.info("RIA Remittance Process Scheduler started.");
                riaExchangeApiProcessorService.downloadRemittanceAndNotify(props);
                riaExchangeApiProcessorService.notifyRemittanceStatus(props);
                riaExchangeApiProcessorService.callRiaCancelRequestAPI(props);
                riaExchangeApiProcessorService.notifyCancelRequestResponse(props);
                logger.info("RIA Remittance Process Scheduler ended.");
            } else {
                logger.error("RIA Scheduler aborted! Please add RiaAPI Properties into the DB.");
            }
        } catch (Exception e) {
            logger.info("Exception occurred in RiaAPIProcessor: " + e.getMessage());
        }
    }



}
