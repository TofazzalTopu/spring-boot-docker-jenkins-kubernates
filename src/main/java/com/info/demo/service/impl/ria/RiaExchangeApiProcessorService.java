package com.info.demo.service.impl.ria;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.model.ria.RiaExchangePropertyDTO;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceProcessService;
import com.info.demo.service.ria.RiaExchangeHouseApiService;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RiaExchangeApiProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(RiaExchangeApiProcessorService.class);

    @Autowired
    private RiaExchangeHouseApiService riaExchangeHouseApiService;

    @Autowired
    private ApiTraceService apiTraceService;

    @Autowired
    private RemittanceProcessService remittanceProcessService;


    public void downloadRemittanceAndNotify(RiaExchangePropertyDTO riaExchangeProperties) {
        logger.info("searchAndDownload() initiated.......");
        String status = "";
        long apiTraceId = 0;
        Date businessDate = apiTraceService.getCurrentBusinessDate();
        try {
            List<RemittanceData> downloadedRemittanceList = riaExchangeHouseApiService.getDownloadableRemittance(riaExchangeProperties, businessDate);
            if (!downloadedRemittanceList.isEmpty()) {
                apiTraceId = downloadedRemittanceList.get(0).getMiddlewareId();
                Predicate<RemittanceData> isDuplicateOrRejected = remittanceData -> remittanceData.isDuplicate() || remittanceData.getProcessStatus().equals(RemittanceDataStatus.REJECTED);
                List<RemittanceData> remittanceDataValidList = downloadedRemittanceList.stream().filter(isDuplicateOrRejected.negate()).collect(Collectors.toList());
                if (!remittanceDataValidList.isEmpty()) {
                    remittanceProcessService.processDownloadData(remittanceDataValidList, riaExchangeProperties.getExchangeCode(), Constants.RIA_API_DATA);
                }
                riaExchangeHouseApiService.notifyRemittanceReceivedStatus(downloadedRemittanceList, riaExchangeProperties, businessDate, true);

                List<RemittanceData> duplicateDataList = downloadedRemittanceList.stream().filter(isDuplicateOrRejected).collect(Collectors.toList());
                if (!duplicateDataList.isEmpty()) {
                    riaExchangeHouseApiService.notifyRemittanceReceivedStatus(duplicateDataList, riaExchangeProperties, businessDate, false);
                }
                status = Constants.API_STATUS_VALID;
            }
        } catch (Exception ex) {
            logger.error("Error Occurred in Saving Downloadable Data For TraceId >>>> " + apiTraceId);
            status = Constants.API_STATUS_ERROR;
        }
        try {
            if (apiTraceId > 0) apiTraceService.updateStatus(apiTraceId, status);
        } catch (Exception e) {
            logger.error("Error updating ApiTrace in searchAndDownload ", e);
        }
    }

    public void notifyRemittanceStatus(RiaExchangePropertyDTO riaProperties) {
        logger.info("Notify Remittance Scheduler started.");
        try {
            List<RemittanceData> notifiedRemittanceDataList = riaExchangeHouseApiService.notifyRemittanceStatus(riaProperties);
            if (!notifiedRemittanceDataList.isEmpty()) {
                remittanceProcessService.saveAllRemittanceData(notifiedRemittanceDataList);
            }
        } catch (Exception ex) {
            logger.error("Error in notifyRemittanceStatus()", ex);
        }
    }

    public void callRiaCancelRequestAPI(RiaExchangePropertyDTO riaProperties) {
        logger.info("Notify Remittance Cancel Request Scheduler started.");
        try {
            List<StopRemittanceData> stopRemittanceDataList = riaExchangeHouseApiService.getCancelRemittance(riaProperties);
            if (!stopRemittanceDataList.isEmpty()) {
                remittanceProcessService.processStopRemittanceCancelData(stopRemittanceDataList, apiTraceService.getCurrentBusinessDate());
            }
        } catch (Exception ex) {
            logger.error("Error in cancelRequestData()", ex);
        }
    }

    public void notifyCancelRequestResponse(RiaExchangePropertyDTO riaProperties) {
        logger.info("Notify Remittance Cancel Request Response Scheduler started.");
        try {
            List<StopRemittanceData> stopRemittanceDataList = riaExchangeHouseApiService.notifyCancelStatus(riaProperties);
            if (!stopRemittanceDataList.isEmpty()) {
                remittanceProcessService.saveAllStopRemittance(stopRemittanceDataList);
            }
        } catch (Exception ex) {
            logger.error("Error in notifyCancelRequestResponse() : ExchangeCode: " + riaProperties.getExchangeCode(), ex);
        }
    }

}
