package com.info.demo.service.impl.instantCash;

import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.model.instantCash.ICPaymentStatusDTO;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceDataService;
import com.info.demo.service.instantCash.ICRetrievePaymentStatusService;
import com.info.demo.util.ApiUtil;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.info.demo.util.ObjectConverter.convertObjectToString;

@Service
public class ICRetrieveRetrievePaymentStatusServiceImpl implements ICRetrievePaymentStatusService {

    public static final Logger logger = LoggerFactory.getLogger(ICRetrieveRetrievePaymentStatusServiceImpl.class);

    public static String API_FINANCIAL_ID = "AE01BH";

    @Autowired
    private ApiTraceService apiTraceService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RemittanceDataService remittanceDataService;

    @Override
    @Deprecated
    public List<RemittanceData> retrievePaymentStatus(ExchangeHouseProperty exchangeHouseProperty, List<RemittanceData> remittanceDataList, final String key, final String password) {

        Long traceId = null;
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
                traceId = apiTrace.getId();
                HttpEntity<String> httpEntity = ApiUtil.createHttpEntity("", uuid, API_FINANCIAL_ID, key, password);

                remittanceDataList.forEach(remittanceData -> {
                    try {
                        String retrievePaymentStatusUrl = exchangeHouseProperty.getKeyValue() + "?reference=" + remittanceData.getReferenceNo();
                        ResponseEntity<ICPaymentStatusDTO> responseEntity = restTemplate.exchange(retrievePaymentStatusUrl,
                                HttpMethod.POST, httpEntity, ICPaymentStatusDTO.class);

                        processPaymentStatus(responseEntity, remittanceData);

                        logger.error("Execute retrievePaymentStatus() for ReferenceNo: {}", remittanceData.getReferenceNo());
                        apiTraceList.add(apiTraceService.buildApiTrace(apiTrace.getId(), exchangeHouseProperty.getExchangeCode(), remittanceData.getReferenceNo(), responseEntity.getBody(), Constants.REQUEST_TYPE_IC_RECEIVE_PAYMENT_TRANSACTION));
                    } catch (Exception e) {
                        logger.error("Error in retrievePaymentStatus() for ReferenceNo: " + remittanceData.getReferenceNo(), e);
                    }
                });
                remittanceDataList = remittanceDataService.saveAll(remittanceDataList);

                apiTraceService.saveAllApiTrace(apiTraceList);

            } catch (Exception e) {
                logger.error("Error in retrievePaymentStatus() for TraceID: " + traceId, e);
            }
        }

        return remittanceDataList;
    }

    @Override
    public ICPaymentStatusDTO retrievePaymentStatus(String referenceNo, ICExchangePropertyDTO dto) {
        ApiUtil.validateICExchangePropertiesBeforeProceed(dto, dto.getStatusUrl(), Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_RETRIEVE_PAYMENT_STATUS);
        ICPaymentStatusDTO icPaymentStatusDTO = new ICPaymentStatusDTO();
//        if (ApiUtil.validateIfICPropertiesIsNotExist(dto, dto.getStatusUrl())) {
//            logger.error(Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_RETRIEVE_PAYMENT_STATUS);
//            return icPaymentStatusDTO;
//        }
        String status = "";
        String response = "";
        String uuid = UUID.randomUUID().toString();
        try {
            String retrievePaymentStatusUrl = dto.getStatusUrl() + "?reference=" + referenceNo;
            ResponseEntity<ICPaymentStatusDTO> responseEntity = restTemplate.exchange(retrievePaymentStatusUrl, HttpMethod.POST,
                    ApiUtil.createHttpEntity("", uuid, dto), ICPaymentStatusDTO.class);

            if ((responseEntity.getStatusCode().equals(HttpStatus.OK)) && Objects.nonNull(responseEntity.getBody())) {
                icPaymentStatusDTO = responseEntity.getBody();
                status = Constants.API_STATUS_VALID;
                response = convertObjectToString(responseEntity.getBody());
            }
            logger.info("Execute retrievePaymentStatus() for ReferenceNo: {}", referenceNo);
        } catch (Exception e) {
            response = e.getMessage();
            status = Constants.API_STATUS_ERROR;
            logger.error("Error in retrievePaymentStatus() for ReferenceNo: " + referenceNo, e);
//            apiTraceService.saveApiTrace(dto.getExchangeCode(), referenceNo, response, status, Constants.REQUEST_TYPE_IC_RETRIEVE_PAYMENT_STATUS_TRANSACTION, uuid);
            throw new RuntimeException(e.getMessage());
        }
//        apiTraceService.saveApiTrace(dto.getExchangeCode(), referenceNo, response, status, Constants.REQUEST_TYPE_IC_RETRIEVE_PAYMENT_STATUS_TRANSACTION, uuid);
        logger.info("retrievePaymentStatus successful for ReferenceNo: " + referenceNo, uuid);

        return icPaymentStatusDTO;
    }


    private void processPaymentStatus(ResponseEntity<ICPaymentStatusDTO> responseEntity, RemittanceData remittanceData) {
        String responseData = "";
        try {
            if (HttpStatus.OK.equals(responseEntity.getStatusCode()) && Objects.nonNull(responseEntity.getBody())) {
                logger.info("RetrievePaymentStatus Response data:" + responseEntity.getBody());
                remittanceData.setApiResponse(convertObjectToString(responseEntity.getBody()));
            } else {
                logger.error("API Responded with different status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error in processPaymentReceive(). Error = " + e);
            logger.info("API request data is -----------------------------> \r\n" + responseData);
        }
    }

}
