package com.info.demo.service.impl.common;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.RemittanceProcessMaster;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.repository.CommonRepository;
import com.info.demo.repository.StopRemittanceDataRepository;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceDataService;
import com.info.demo.service.common.RemittanceProcessMasterService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RemittanceDataProcessServiceImpl {

    private final ApiTraceService apiTraceService;
    private final CommonRepository commonRepository;

    //    @Lazy
    private final RemittanceDataService remittanceDataService;

    private final StopRemittanceDataRepository stopRemittanceDataRepository;
    private final RemittanceProcessMasterService remittanceProcessMasterService;

    public RemittanceDataProcessServiceImpl(ApiTraceService apiTraceService, CommonRepository commonRepository, @Lazy RemittanceDataService remittanceDataService, StopRemittanceDataRepository stopRemittanceDataRepository, RemittanceProcessMasterService remittanceProcessMasterService) {
        this.apiTraceService = apiTraceService;
        this.commonRepository = commonRepository;
        this.remittanceDataService = remittanceDataService;
        this.stopRemittanceDataRepository = stopRemittanceDataRepository;
        this.remittanceProcessMasterService = remittanceProcessMasterService;
    }


    //    @Transactional
    public List<RemittanceData> processDownloadData(List<RemittanceData> remittanceDataList, String exchangeCode, String exchangeName) {
        List<RemittanceData> remittanceDataArrayList = new ArrayList<>();
        if (!remittanceDataList.isEmpty()) {
            RemittanceProcessMaster master = null;
            RemittanceProcessMaster masterRecord = commonRepository.getRemitProcMasterByApiData(remittanceDataList.get(0).getProcessDate(),
                    exchangeCode, 1, "OPEN");

            if (masterRecord != null) {
                master = masterRecord;
            }
            long eftCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("EFT")).count();
            long beftnCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("BEFTN")).count();
            long mobileCount = remittanceDataList.stream().filter(d -> d.getRemittanceMessageType().equals("MOBILE")).count();

            if (master == null) {
                master = new RemittanceProcessMaster();
                master.setCommon(true);
                master.setExchangeHouseCode(exchangeCode);
                master.setFileName(exchangeName);
                master.setApiData(1);
                master.setManualOpen(0);
                master.setProcessByUser("CBSRMS");
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
            long time = System.currentTimeMillis();
            System.out.println("Saving started...");
            remittanceDataArrayList = remittanceDataService.saveAll(remittanceDataList);
            long milis = System.currentTimeMillis() - time;
            System.out.println("Total Time: " + TimeUnit.MILLISECONDS.toSeconds(milis));
        }
        return remittanceDataArrayList;
    }

    @Transactional
    public void processCancelData(List<StopRemittanceData> listData) {
        for (StopRemittanceData stopRemittanceData : listData) {

            RemittanceData remittanceData = null;

            List<RemittanceData> remDataList = commonRepository.getRemittancebyReference(
                    stopRemittanceData.getReferenceNo(), stopRemittanceData.getExchangeCode());
            if (remDataList != null && !remDataList.isEmpty()) {
                if (remDataList.size() == 1) {
                    remittanceData = remDataList.get(0);
                } else {

                    for (RemittanceData data : remDataList) {
                        if (data.getProcessStatus() != null && (data.getProcessStatus().equals(RemittanceDataStatus.POSTED)
                                || data.getProcessStatus().equals(RemittanceDataStatus.COMPLETED))) {
                            remittanceData = data;
                            break;
                        }
                    }
                    if (remittanceData == null) {
                        for (RemittanceData data : remDataList) {
                            if (data.getProcessStatus() != null && (data.getProcessStatus().equals(RemittanceDataStatus.VALID)
                                    || data.getProcessStatus().equals(RemittanceDataStatus.OPEN))) {
                                remittanceData = data;
                                break;
                            }
                        }
                    }

                    if (remittanceData == null) {
                        for (RemittanceData data : remDataList) {
                            if (data.getProcessStatus() != null
                                    && (data.getProcessStatus().equals(RemittanceDataStatus.INVALID))) {
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
            } else if (remittanceData.getProcessStatus().equals(RemittanceDataStatus.POSTED)
                    || remittanceData.getProcessStatus().equals(RemittanceDataStatus.COMPLETED)) {
                stopRemittanceData.setProcessStatus(StopRemittanceData.REJECT_REQUEST);
                stopRemittanceData.setMessage("Can't be Stopped.Record already processed.");
                stopRemittanceData.setExchangeStatus(StopRemittanceData.REJECT_ALREADY_PAID);
            } else {
                remittanceData.setReturnedMsg(1);
                remittanceData.setReturnedDate(apiTraceService.getCurrentBusinessDate());
                remittanceData.setReturnedBy("REM_API");
                remittanceData.setStopPayReason("Stopped through API");
                remittanceData.setFinalStatus("20"); // MESSAGE_RETURN
                remittanceData.setProcessStatus(RemittanceDataStatus.COMPLETED);
                remittanceDataService.save(remittanceData);

                stopRemittanceData.setProcessStatus(StopRemittanceData.ACCEPT_REQUEST);
            }
            stopRemittanceDataRepository.save(stopRemittanceData);
        }
    }

    @Transactional
    public void updateCancelData(StopRemittanceData data) {
        stopRemittanceDataRepository.save(data);
    }

    @Transactional
    public void updateRemittanceData(RemittanceData data) {
        remittanceDataService.save(data);
    }

    @Transactional
    public List<RemittanceData> saveAll(List<RemittanceData> remittanceDataList){
        return remittanceDataService.saveAll(remittanceDataList);
    }



}
