package com.info.demo.service.impl.instantCash;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceDataService;
import com.info.demo.service.impl.common.RemittanceDataProcessServiceImpl;
import com.info.demo.service.instantCash.ICUnlockRemittanceService;
import com.info.demo.util.ApiUtil;
import com.info.demo.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.info.demo.util.ObjectConverter.convertObjectToString;


@Slf4j
@Service
@Transactional
public class ICUnlockRemittanceServiceImpl implements ICUnlockRemittanceService {

    public static final Logger logger = LoggerFactory.getLogger(ICUnlockRemittanceServiceImpl.class);

    public static String API_FINANCIAL_ID = "AE01BH";

    @Autowired
    private ApiTraceService apiTraceService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RemittanceDataService remittanceDataService;

    @Autowired
    private RemittanceDataProcessServiceImpl remittanceDataProcessService;

    @Override
    @Deprecated
    public List<RemittanceData> unlockICOutstandingRemittance(ExchangeHouseProperty exchangeHouseProperty, List<RemittanceData> remittanceDataList, final String key, final String password) {

        List<ApiTrace> apiTraceList = new ArrayList<>();
        String uuid = UUID.randomUUID().toString();
        if (!remittanceDataList.isEmpty()) {
            try {
                Date businessDate = apiTraceService.getCurrentBusinessDate();
                final ApiTrace apiTrace = apiTraceService.create(exchangeHouseProperty.getExchangeCode(), Constants.REQUEST_TYPE_IC_CONFIRM_TRANSACTION, businessDate);
                if (Objects.isNull(apiTrace)) {
                    logger.info("No TraceId Found. Scheduler aborted");
                    return null;
                }
                remittanceDataList.forEach(remittanceData -> {
                    try {
                        HttpEntity<String> httpEntity = ApiUtil.createHttpEntity(remittanceData.getReferenceNo(), uuid, API_FINANCIAL_ID, key, password);
                        ResponseEntity<String> responseEntity = restTemplate.exchange(exchangeHouseProperty.getKeyValue(), HttpMethod.POST, httpEntity, String.class);

                        processUnlockRemittanceData(responseEntity, remittanceData);

                        logger.error("Execute confirmICOutstandingRemittance for ReferenceNo: {}", remittanceData.getReferenceNo());
                        apiTraceList.add(apiTraceService.buildApiTrace(apiTrace.getId(), exchangeHouseProperty.getExchangeCode(), remittanceData.getReferenceNo(), responseEntity.getBody(), Constants.REQUEST_TYPE_IC_UNLOCK_TRANSACTION));
                    } catch (Exception e) {
                        logger.error("Error in confirmICOutstandingRemittance for ReferenceNo: " + remittanceData.getReferenceNo(), e);
                    }
                });
                remittanceDataList = remittanceDataService.saveAll(remittanceDataList);

                apiTraceService.saveAllApiTrace(apiTraceList);

            } catch (Exception e) {
                logger.error("Error in confirmICOutstandingRemittance for uuid: " + uuid, e);
            }
        }
        return remittanceDataList;
    }

    @Override
    public void unlockICOutstandingRemittance(ICExchangePropertyDTO icDTO) {
        if (ApiUtil.validateIfICPropertiesIsNotExist(icDTO, icDTO.getNotifyRemStatusUrl())) {
            logger.error(Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_UNLOCK_REMITTANCE);
            return;
        }

        String uuid = UUID.randomUUID().toString();
        StringBuilder requestBuilder = new StringBuilder();
        StringBuilder responseBuilder = new StringBuilder();
        StringBuilder statusBuilder = new StringBuilder();
        try {
            List<RemittanceData> remittanceDataList = remittanceDataService.findAllByExchangeCodeAndSourceTypeAndProcessStatus(icDTO.getExchangeCode(), Constants.API_SOURCE_TYPE, RemittanceDataStatus.UNLOCK_REQUESTED);
            remittanceDataList.forEach(remittanceData -> {
                try {
                    requestBuilder.append(remittanceData.getReferenceNo()).append(",\n");
                    HttpEntity<String> httpEntity = ApiUtil.createHttpEntity(remittanceData.getReferenceNo(), uuid, icDTO);
                    ResponseEntity<String> responseEntity = restTemplate.exchange(icDTO.getUnlockUrl(), HttpMethod.POST, httpEntity, String.class);

                    processUnlockRemittanceData(responseEntity, remittanceData);

                    if ((responseEntity.getStatusCode().equals(HttpStatus.OK)) && Objects.nonNull(responseEntity.getBody())) {
                        remittanceDataService.update(RemittanceDataStatus.UNLOCK, remittanceData.getReferenceNo());
                        statusBuilder.append("[").append(remittanceData.getReferenceNo()).append("-").append(Constants.API_STATUS_VALID).append("],\n");
                        responseBuilder.append(convertObjectToString(responseEntity.getBody())).append(",\n");
                    } else {
                        logger.error("Execute confirmICOutstandingRemittance for ReferenceNo: {}", remittanceData.getReferenceNo());
                    }
                } catch (Exception e) {
                    statusBuilder.append("[").append(remittanceData.getReferenceNo()).append("-").append(Constants.API_STATUS_ERROR).append("],\n");
                    logger.error("Error in confirmICOutstandingRemittance for ReferenceNo: " + remittanceData.getReferenceNo(), e);
                }
            });
        } catch (Exception e) {
            logger.error("Error in confirmICOutstandingRemittance for uuid: " + uuid, e);
        }
//        apiTraceService.saveApiTrace(icDTO.getExchangeCode(), requestBuilder.toString(), responseBuilder.toString(), statusBuilder.toString(), Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_UNLOCK_REMITTANCE, uuid);
    }

    @Override
    @Deprecated
    public String unlockICOutstandingRemittance(String referenceNo, ICExchangePropertyDTO dto) {
        ApiUtil.validateICExchangePropertiesBeforeProceed(dto, dto.getUnlockUrl(), Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_UNLOCK_REMITTANCE);

        Date businessDate = apiTraceService.getCurrentBusinessDate();
        final ApiTrace apiTrace = apiTraceService.create(dto.getExchangeCode(), Constants.REQUEST_TYPE_IC_UNLOCK_TRANSACTION, businessDate);
        String response = "";

        Optional.ofNullable(apiTrace).orElseThrow(() -> new RuntimeException(Constants.API_TRACE_CREATION_ERROR));
        String uuid = UUID.randomUUID().toString();

        try {
            HttpEntity<String> httpEntity = ApiUtil.createHttpEntity(referenceNo, uuid, dto);
            ResponseEntity<String> responseEntity = restTemplate.exchange(dto.getUnlockUrl(), HttpMethod.POST, httpEntity, String.class);

            if ((responseEntity.getStatusCode().equals(HttpStatus.OK)) && Objects.nonNull(responseEntity.getBody())) {
//                remittanceDataService.update(RemittanceData.UNLOCK, referenceNo);
                apiTrace.setStatus(Constants.API_STATUS_VALID);
                response = convertObjectToString(responseEntity.getBody());
            } else {
                apiTraceService.deleteById(apiTrace.getId());
                logger.info(Constants.TRACING_REMOVED_BECAUSE_NO_RECORD_FOUND_TRACE_ID, "unlockICOutstandingRemittance()", apiTrace.getId());
                throw new RuntimeException("Unlock not successful");
            }
        } catch (Exception e) {
            apiTrace.setStatus(Constants.API_STATUS_ERROR);
            response = e.getMessage();
            logger.error("Error in unlockICOutstandingRemittance for TraceID: " + apiTrace.getId(), e);
            throw new RuntimeException("Unlock not successful");
        }

        apiTrace.setRequestMsg(referenceNo);
        apiTrace.setResponseMsg(response);
        apiTraceService.save(apiTrace);
        return response;
    }

    private void processUnlockRemittanceData(ResponseEntity<String> responseEntity, RemittanceData remittanceData) {
        String responseData = "";
        try {
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                if (Objects.nonNull(responseEntity.getBody())) {
                    logger.info("Confirmation Response data:" + responseEntity.getBody());

                    remittanceData.setApiResponse(responseEntity.getBody());
                    remittanceData.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_DONE);
                } else {
                    remittanceData.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_ERROR);
                    logger.error("Response body contains empty response against: " + remittanceData.getReferenceNo());
                }

            } else {
                logger.error("API Responded with different status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error in processConfirmRemittanceData(). Error = " + e);
            logger.info("API request data is -----------------------------> \r\n" + responseData);
        }
    }


}
