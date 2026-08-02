package com.info.demo.service.common;

import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.StopRemittanceData;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommonService {

    Map<String, String> getAccountBranchInfo(String accountNo);
    Date getCurrentBusinessDate();

    boolean isAuthorizedRequest(String userId, String password);
    void verifyAuthorization(String userId, String password);

    List<StopRemittanceData> getCancelRequestUnProcessData();

    List<RemittanceData> getRemittanceDataForNotification(String exchangeCode);
}
