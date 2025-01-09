package com.info.demo.service.impl.instantCash;

import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.ExchangeHouseProperty;
import com.info.demo.entity.ICCashRemittanceData;
import com.info.demo.entity.RemittanceData;
import com.info.demo.mapper.ICPaymentReceiveRemittanceMapper;
import com.info.demo.model.instantCash.ICExchangePropertyDTO;
import com.info.demo.model.instantCash.ICOutstandingTransactionDTO;
import com.info.demo.model.ria.SearchApiResponse;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.CommonService;
import com.info.demo.service.instantCash.ICCashRemittanceDataService;
import com.info.demo.service.instantCash.ICPaymentReceiveService;
import com.info.demo.util.ApiUtil;
import com.info.demo.util.Constants;
import com.info.demo.util.ObjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.info.demo.util.ObjectConverter.convertObjectToString;

@Service
@Transactional
public class ICPaymentReceiveServiceImpl implements ICPaymentReceiveService {

    public static final Logger logger = LoggerFactory.getLogger(ICPaymentReceiveServiceImpl.class);

    @Value("${IC_API_FINANCIAL_ID:BD01RH}")
    public String IC_API_FINANCIAL_ID;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApiTraceService apiTraceService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ICCashRemittanceDataService icCashRemittanceDataService;

    @Autowired
    private ICPaymentReceiveRemittanceMapper mapper;

    @Value("#{${bank.code}}")
    private String bankCode;


    //    @Override
    @Deprecated
    public List<RemittanceData> paymentReceive(ExchangeHouseProperty exchangeHouseProperty, List<RemittanceData> remittanceDataList, final String key, final String password) {

        List<ApiTrace> apiTraceList = new ArrayList<>();
        try {
            List<ICOutstandingTransactionDTO> outstandingTransactionDTOList = new ArrayList<>();
            AtomicReference<String> response = new AtomicReference<>("");
            Date businessDate = apiTraceService.getCurrentBusinessDate();
            String uuid = UUID.randomUUID().toString();
            remittanceDataList.forEach(remittanceData -> {
                ApiTrace apiTrace = apiTraceService.create(exchangeHouseProperty.getExchangeCode(), Constants.REQUEST_TYPE_DOWNLOAD_REQ, businessDate);
                try {
                    if (Objects.isNull(apiTrace)) {
                        logger.info("No TraceId Found. Scheduler aborted");
                        return;
                    }
                    HttpEntity<String> httpEntity = ApiUtil.createHttpEntity("", uuid, IC_API_FINANCIAL_ID, key, password);
                    String paymentUrl = exchangeHouseProperty.getKeyValue().trim() + "?reference=" + remittanceData.getReferenceNo().trim();
                    System.out.println("paymentUrl: " + paymentUrl);
                    ResponseEntity<ICOutstandingTransactionDTO> responseEntity = restTemplate.exchange(paymentUrl, HttpMethod.GET, httpEntity, ICOutstandingTransactionDTO.class);
                    response.set(convertObjectToString(responseEntity.getBody()));

                    if ((responseEntity.getStatusCode().equals(HttpStatus.OK)) && Objects.nonNull(responseEntity.getBody())) {
//                        processRemittanceData(responseEntity, remittanceData);
                        outstandingTransactionDTOList.add(responseEntity.getBody());
                    }

                    logger.info("Execute paymentReceive for ReferenceNo: {}", remittanceData.getReferenceNo());
                } catch (Exception e) {
                    response.set(e.getMessage());
                    logger.error("Error in paymentReceive() for ReferenceNo: " + remittanceData.getReferenceNo(), apiTrace.getId(), e);
                }
                apiTraceList.add(apiTraceService.buildApiTrace(apiTrace.getId(), exchangeHouseProperty.getExchangeCode(), remittanceData.getReferenceNo(), response.get(), Constants.REQUEST_TYPE_IC_RETRIEVE_PAYMENT_STATUS_TRANSACTION));
            });
//                remittanceDataList = remittanceDataService.saveAll(remittanceDataList);

            if (!apiTraceList.isEmpty()) apiTraceService.saveAllApiTrace(apiTraceList);

        } catch (Exception e) {
            logger.error("Error in paymentReceive()", e);
        }
        return remittanceDataList;
    }

    @Override
    public SearchApiResponse paymentReceive(@NotNull ICExchangePropertyDTO dto, @NotNull String referenceNo) {
        SearchApiResponse searchApiResponse = new SearchApiResponse();
        searchApiResponse.setPinno(referenceNo);
        searchApiResponse.setReference(referenceNo);
        searchApiResponse.setExchCode(dto.getExchangeCode());
        searchApiResponse.setOriginalRequest(referenceNo);

        if (icCashRemittanceDataService.findByExchangeCodeAndReferenceNo(dto.getExchangeCode(), referenceNo).isPresent()) {
            searchApiResponse.setErrorMessage(Constants.REFERENCE_NO_ALREADY_EXIST);
            return searchApiResponse;
        }

        ApiUtil.validateICExchangePropertiesBeforeProceed(dto, dto.getPaymentReceiveUrl(), Constants.EXCHANGE_HOUSE_PROPERTY_NOT_EXIST_FOR_RECEIVE_PAYMENT);

        String response = "";
        String uuid = UUID.randomUUID().toString();
        ICCashRemittanceData icCashRemittanceData = new ICCashRemittanceData();
        ApiTrace apiTrace = apiTraceService.create(dto.getExchangeCode(), Constants.REQUEST_TYPE_DOWNLOAD_REQ, null);
        Optional.ofNullable(apiTrace).orElseThrow(() -> new RuntimeException("Unable to create ApiTrace!"));

        try {
            dto.setPaymentReceiveUrl("http://localhost:8070/api/OK/paymentReceive");
            String paymentUrl = dto.getPaymentReceiveUrl().trim() + "?reference=" + referenceNo.trim();
            System.out.println("paymentUrl: " + paymentUrl);
            ResponseEntity<ICOutstandingTransactionDTO> responseEntity = restTemplate.exchange(paymentUrl, HttpMethod.GET, ApiUtil.createHttpEntity("", uuid, dto), ICOutstandingTransactionDTO.class);
            searchApiResponse.setOriginalResponse(String.valueOf(responseEntity.getBody()));

            if ((responseEntity.getStatusCode().equals(HttpStatus.OK)) && Objects.nonNull(responseEntity.getBody())) {
                response = convertObjectToString(responseEntity.getBody());
                icCashRemittanceData = mapper.prepareICCashRemittanceData(icCashRemittanceData, responseEntity.getBody(), dto.getExchangeCode(), apiTrace, true);

                if (Objects.nonNull(icCashRemittanceData)) {
                    icCashRemittanceData = icCashRemittanceDataService.save(icCashRemittanceData);
                }
                mapper.mapSearchApiResponse(searchApiResponse, icCashRemittanceData);
                apiTrace.setStatus(Constants.API_STATUS_VALID);
            } else {
                searchApiResponse.setApiStatus(Constants.API_STATUS_VALID);
                searchApiResponse.setErrorMessage(ObjectConverter.convertObjectToString(responseEntity.getBody()));
                apiTraceService.deleteById(apiTrace.getId());
                logger.info("Tracing removed because no record found, uuid: " + uuid);
                return searchApiResponse;
            }
            searchApiResponse.setPayoutStatus(String.valueOf(responseEntity.getStatusCode().value()));
            searchApiResponse.setApiStatus(String.valueOf(responseEntity.getStatusCode().value()));
            logger.info("Execute paymentReceive for ReferenceNo: {}", referenceNo);
        } catch (Exception e) {
            response = e.getMessage();
            apiTrace.setStatus(Constants.API_STATUS_ERROR);
            searchApiResponse.setApiStatus(Constants.API_STATUS_INVALID);
            searchApiResponse.setErrorMessage(ObjectConverter.convertObjectToString(response));
            logger.error("Error in paymentReceive for ReferenceNo: " + referenceNo, uuid, e);
        }

        apiTrace.setRequestMsg(referenceNo);
        apiTrace.setResponseMsg(response);
        apiTrace.setCorrelationId(uuid);
        apiTraceService.save(apiTrace);
        logger.info("paymentReceive successful for ReferenceNo: " + referenceNo, uuid);
        return searchApiResponse;
    }


}
