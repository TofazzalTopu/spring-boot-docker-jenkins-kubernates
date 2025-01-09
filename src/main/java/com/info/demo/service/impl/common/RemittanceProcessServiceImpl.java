package com.info.demo.service.impl.common;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.RemittanceProcessMaster;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceDataService;
import com.info.demo.service.common.RemittanceProcessMasterService;
import com.info.demo.service.common.RemittanceProcessService;
import com.info.demo.service.ria.StopRemittanceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RemittanceProcessServiceImpl implements RemittanceProcessService {

    private static final Logger logger = LoggerFactory.getLogger(RemittanceProcessServiceImpl.class);

    @Autowired
    private RemittanceProcessMasterService remittanceProcessMasterService;

    @Autowired
    private RemittanceDataService remittanceDataService;

    @Autowired
    private StopRemittanceDataService stopRemittanceDataService;

    @Autowired
    private ApiTraceService apiTraceService;

    @Value("${RIA_EXCHANGE_HOUSE_BRANCH_USER:CBSRMS}")
    private String RIA_EXCHANGE_HOUSE_BRANCH_USER;

    @Override
    public List<RemittanceData> saveAllRemittanceData(List<RemittanceData> remittanceDataList) {
        return remittanceDataService.saveAll(remittanceDataList);
    }

    @Override
    @Transactional
    public void processDownloadData(List<RemittanceData> remittanceDataList, String exchangeCode, String exchangeName) {
        if (!remittanceDataList.isEmpty()) {
            RemittanceProcessMaster master = null;
            RemittanceProcessMaster masterRecord = remittanceProcessMasterService.findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatus(remittanceDataList.get(0).getProcessDate(), exchangeCode, 1, "OPEN");
            if (masterRecord != null) {
                master = masterRecord;
            }

            long eftCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("EFT")).count();
            long beftnCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("BEFTN")).count();
            long mobileCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("MOBILE")).count();

            if (master == null) {
                master = new RemittanceProcessMaster();
                master.setCommon(true);
                master.setFileName(exchangeName);
                master.setApiData(1);
                master.setManualOpen(0);
                master.setProcessByUser(RIA_EXCHANGE_HOUSE_BRANCH_USER);
                master.setExchangeHouseCode(exchangeCode);
                master.setProcessDate(remittanceDataList.get(0).getProcessDate());
                master.setProcessStatus("OPEN");
                master.setTotalBeftn(beftnCount);
                master.setTotalEft(eftCount);
                master.setTotalMobile(mobileCount);
                master.setTotalSpot(0);
                master.setTotalWeb(0);
            } else {
                master.setTotalBeftn(master.getTotalBeftn() + beftnCount);
                master.setTotalEft(master.getTotalEft() + eftCount);
                master.setTotalMobile(master.getTotalMobile() + mobileCount);
            }
            master = remittanceProcessMasterService.save(master);

            for (RemittanceData data : remittanceDataList) {
                data.setRemittanceProcessMaster(master);
            }
            remittanceDataService.saveAll(remittanceDataList);
        }
    }

    @Override
    public void processStopRemittanceCancelData(List<StopRemittanceData> stopRemittanceDataList, Date businessDate) {
        List<StopRemittanceData> stopRemittanceList = new ArrayList<>();
        for (StopRemittanceData stopRemittanceData : stopRemittanceDataList) {
            RemittanceData remittanceData = null;
            List<RemittanceData> remDataList = remittanceDataService.findAllByExchangeCodeAndReferenceNo(stopRemittanceData.getExchangeCode(), stopRemittanceData.getReferenceNo());
            if (remDataList != null && !remDataList.isEmpty()) {
                if (remDataList.size() == 1) {
                    remittanceData = remDataList.get(0);
                } else {

                    for (RemittanceData data : remDataList) {
                        if (data.getProcessStatus() != null && (data.getProcessStatus().equals(RemittanceDataStatus.POSTED) || data.getProcessStatus().equals(RemittanceDataStatus.COMPLETED))) {
                            remittanceData = data;
                            break;
                        }
                    }
                    if (remittanceData == null) {
                        for (RemittanceData data : remDataList) {
                            if (data.getProcessStatus() != null && (data.getProcessStatus().equals(RemittanceDataStatus.VALID) || data.getProcessStatus().equals(RemittanceDataStatus.OPEN))) {
                                remittanceData = data;
                                break;
                            }
                        }
                    }

                    if (remittanceData == null) {
                        for (RemittanceData data : remDataList) {
                            if (data.getProcessStatus() != null && (data.getProcessStatus().equals(RemittanceDataStatus.INVALID))) {
                                if (!data.getReasonForInvalid().equals("Reference already exist")) {
                                    remittanceData = data;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (remittanceData == null) {
                stopRemittanceData.setProcessStatus(StopRemittanceData.REJECT_REQUEST);
                stopRemittanceData.setMessage("No Records Found!");
                stopRemittanceData.setExchangeStatus(StopRemittanceData.REJECT_NOT_FOUND);
            } else if (remittanceData.getProcessStatus().equals(RemittanceDataStatus.POSTED) || remittanceData.getProcessStatus().equals(RemittanceDataStatus.COMPLETED)) {
                stopRemittanceData.setProcessStatus(StopRemittanceData.REJECT_REQUEST);
                stopRemittanceData.setMessage("Can't be Stopped.Record already processed.");
                stopRemittanceData.setExchangeStatus(StopRemittanceData.REJECT_ALREADY_PAID);
            } else {
                remittanceData.setReturnedMsg(1);
                remittanceData.setReturnedDate(businessDate);
                remittanceData.setReturnedBy("REM_API");
                remittanceData.setStopPayReason("Stopped through API");
                remittanceData.setFinalStatus("20"); // MESSAGE_RETURN
                remittanceData.setProcessStatus(RemittanceDataStatus.COMPLETED);
                remittanceDataService.save(remittanceData);

                stopRemittanceData.setProcessStatus(StopRemittanceData.ACCEPT_REQUEST);
            }
            stopRemittanceList.add(stopRemittanceData);
        }
        stopRemittanceDataService.saveAll(stopRemittanceList);
    }

    @Override
    public List<StopRemittanceData> saveAllStopRemittance(List<StopRemittanceData> stopRemittanceDataList) {
        return stopRemittanceDataService.saveAll(stopRemittanceDataList);
    }

    @Override
    public void saveWebOrSpotData(RemittanceData data, String exchangeCode, String exchangeName) {
        logger.info("Enter into saveWebOrSpotData");
        try {
            RemittanceProcessMaster master = null;
            RemittanceProcessMaster masterRecord = remittanceProcessMasterService.findFirstByProcessDateAndExchangeHouseCodeAndApiDataAndProcessStatus(data.getProcessDate(), exchangeCode, 1, "OPEN");
            if (masterRecord != null) master = masterRecord;

            if (master == null) {
                logger.info("Enter into saveWebOrSpotData masterRecord present");
                master = new RemittanceProcessMaster();
                master.setCommon(true);
                master.setExchangeHouseCode(exchangeCode);
                master.setFileName(exchangeName);
                master.setApiData(1);
                master.setManualOpen(0);
                master.setProcessByUser(RIA_EXCHANGE_HOUSE_BRANCH_USER);
                master.setProcessDate(data.getProcessDate());
                master.setProcessStatus("OPEN");
                master.setTotalBeftn(0);
                master.setTotalEft(0);
                master.setTotalMobile(0);
                master.setTotalSpot(0);
                master.setTotalWeb(1);
            } else {
                master.setTotalWeb(master.getTotalWeb() + 1);
            }
            master = remittanceProcessMasterService.save(master);
            data.setRemittanceProcessMaster(master);
            remittanceDataService.save(data);
            apiTraceService.updateSyncFlag(exchangeCode, data.getSecurityCode(), data.getProcessDate());
        } catch (Exception e) {
            logger.info("Error in saveWebOrSpotData. Error = " + e);
        }
    }
}
