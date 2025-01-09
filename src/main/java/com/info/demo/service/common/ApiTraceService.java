package com.info.demo.service.common;

import com.info.demo.entity.ApiTrace;
import com.info.demo.model.ria.PaymentApiRequest;
import com.info.demo.model.ria.PaymentApiResponse;
import com.info.demo.model.ria.SearchApiRequest;
import com.info.demo.model.ria.SearchApiResponse;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ApiTraceService {

    Long initiateApiTrace(String exchangeCode, String requestType, Date businessDate);

    ApiTrace create(String exchangeCode, String requestType, Date businessDate);

    ApiTrace updateStatus(Long apiTraceId, String status);

    void saveRequestResponse(Long apiTraceId, String exchangeCode, String request, String response, String requestType);

    List<ApiTrace> saveAllApiTrace(List<ApiTrace> apiTraceList);

    Date getCurrentBusinessDate();

    Optional<ApiTrace> findById(Long apiTraceId);

    Optional<ApiTrace> findByTranNo(String tranNo, String requestType);

    Timestamp getRefDate(String exchangeCode, String pin, Date tranDate);

    void updatePayoutStatus(String payoutStatus, List<String> spotIds);

    void updateSyncFlag(String exchangeCode, String pin, Date tranDate);

    Long getApiTranSequence();

    ApiTrace save(ApiTrace apiTrace);

    void addToApiTrace(Long apiTraceId, SearchApiResponse response, SearchApiRequest searchApiRequest);

    void addToApiTrace(Long apiTraceId, PaymentApiResponse response, PaymentApiRequest paymentApiRequest);

    void deleteById(long id);

    <T> ApiTrace buildApiTrace(Long apiTrace, String exchangeCode, String referenceNo, T response, String status);

    ApiTrace saveApiTrace(ApiTrace apiTrace, String exchangeCode, String request, String response, String status, String requestType, String correlationId);
    List<Long> getCancelIds(int tryCount);

}
