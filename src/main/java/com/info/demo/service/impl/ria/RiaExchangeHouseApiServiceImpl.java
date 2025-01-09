package com.info.demo.service.impl.ria;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.RemittanceData;
import com.info.demo.entity.StopRemittanceData;
import com.info.demo.mapper.RiaRemittanceMapper;
import com.info.demo.model.ria.*;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.CommonService;
import com.info.demo.service.ria.RiaExchangeHouseApiService;
import com.info.demo.util.ApiUtil;
import com.info.demo.util.Constants;
import com.info.demo.util.DateUtil;
import com.info.demo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.info.demo.util.ObjectConverter.convertObjectToString;

@Service
public class RiaExchangeHouseApiServiceImpl implements RiaExchangeHouseApiService {

    private static final Logger logger = LoggerFactory.getLogger(RiaExchangeHouseApiServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApiTraceService apiTraceService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RiaRemittanceMapper riaRemittanceMapper;

    @Value("#{${bank.code}}")
    private String bankCode;

    @Value("${RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID:HOST}")
    private String RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID;

    @Value("${RIA_EXCHANGE_HOUSE_BRANCH_USER:CBSRMS}")
    private String RIA_EXCHANGE_HOUSE_BRANCH_USER;

    @Override
    public SearchApiResponse searchRemittance(Long apiTraceId, SearchApiRequest searchApiRequest) {
        logger.info("Enter into searchRemittance() ");
        SearchApiResponse response = new SearchApiResponse();
        CashPickUpSearchResponse cashPickUpSearchResponse = new CashPickUpSearchResponse();
        CashPickUpSearchRequest cashPickUpRequestBody = new CashPickUpSearchRequest();
        RiaExchangePropertyDTO riaExchangeProperties = ApiUtil.getRiaExchangePropertyDTO();
        try {
            String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");
            String dateTimeUTC = DateUtil.currentDateTimeInUTC("yyyyMMddHHmmss");
            String exchangeCode = searchApiRequest.getExchcode();
            cashPickUpRequestBody.setPin(searchApiRequest.getPinno());
            cashPickUpRequestBody.setCorrespLocID(StringUtil.padLeftZeros(searchApiRequest.getBrcode(), 4));
            cashPickUpRequestBody.setBeneAmount("0");
            cashPickUpRequestBody.setDateTimeLocal(dateTime);
            cashPickUpRequestBody.setDateTimeUTC(dateTimeUTC);

            HttpEntity<CashPickUpSearchRequest> httpEntity = new HttpEntity<CashPickUpSearchRequest>(cashPickUpRequestBody, ApiUtil.createRiaHeader(apiTraceId, searchApiRequest.getBruserid(), searchApiRequest.getIpAddress(), dateTime, riaExchangeProperties));

            cashPickUpSearchResponse = restTemplate.postForObject(riaExchangeProperties.getSearchUrl(), httpEntity, CashPickUpSearchResponse.class);
            if (cashPickUpSearchResponse != null) {
                logger.info("response is not empty....");
                if (cashPickUpSearchResponse.getErrors() == null && cashPickUpSearchResponse.getResponse() != null) {
                    logger.info("response = " + cashPickUpSearchResponse.getResponse().toString());
                    CashPickUpSearchDTO cashPickUpSearchDTO = cashPickUpSearchResponse.getResponse();
                    if (cashPickUpSearchDTO != null) {

                        if (!"1000".equals(cashPickUpSearchDTO.getResponseCode())) {
                            response.setPayoutStatusDetails("");
                            response.setPayoutStatus(null);
                            response.setErrorMessage(cashPickUpSearchDTO.getResponseText());
                            response.setApiStatus(Constants.API_STATUS_INVALID);
                        } else {
                            response.setPayoutStatusDetails(cashPickUpSearchDTO.getResponseText());
                            response.setPayoutStatus(cashPickUpSearchDTO.getResponseCode());
                            response.setErrorMessage("");
                            response.setApiStatus(Constants.API_STATUS_VALID);
                        }

                        String beneficiaryName = "";
                        if (cashPickUpSearchDTO.getBeneNameFirst() != null) {
                            beneficiaryName += " " + cashPickUpSearchDTO.getBeneNameFirst();
                        }
                        if (cashPickUpSearchDTO.getBeneNameMiddle() != null) {
                            beneficiaryName += " " + cashPickUpSearchDTO.getBeneNameMiddle();
                        }
                        if (cashPickUpSearchDTO.getBeneNameLast1() != null) {
                            beneficiaryName += " " + cashPickUpSearchDTO.getBeneNameLast1();
                        }
                        if (cashPickUpSearchDTO.getBeneNameLast2() != null) {
                            beneficiaryName += " " + cashPickUpSearchDTO.getBeneNameLast2();
                        }
                        response.setBenfFirstName(beneficiaryName != null ? beneficiaryName.trim() : "");
                        response.setExchCode(exchangeCode);
                        response.setExchTranId(cashPickUpSearchDTO.getTransRefID());
                        response.setFxAmount(cashPickUpSearchDTO.getBeneAmount());
                        response.setOriginalAmount(cashPickUpSearchDTO.getCustAmount());
                        response.setOriginalCurrency(cashPickUpSearchDTO.getCustCurrency());
                        response.setOrignCountryName(cashPickUpSearchDTO.getCountryFrom());
                        response.setPinno(cashPickUpSearchDTO.getPin());
                        response.setReference(cashPickUpSearchDTO.getPin());

                        String tranDate = cashPickUpSearchDTO.getOrderDate();
                        try {
                            if (tranDate != null) {
                                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                                tranDate = DateUtil.convertCalendarToString(df.getCalendar(), "yyyy-MM-dd");
                                response.setTranDate(tranDate);
                            }
                        } catch (Exception e) {
                            response.setTranDate(tranDate);
                        }

                        String remitterName = "";
                        if (cashPickUpSearchDTO.getCustNameFirst() != null) {
                            remitterName += " " + cashPickUpSearchDTO.getCustNameFirst();
                        }
                        if (cashPickUpSearchDTO.getCustNameMiddle() != null) {
                            remitterName += " " + cashPickUpSearchDTO.getCustNameMiddle();
                        }
                        if (cashPickUpSearchDTO.getCustNameLast1() != null) {
                            remitterName += " " + cashPickUpSearchDTO.getCustNameLast1();
                        }
                        if (cashPickUpSearchDTO.getCustNameLast2() != null) {
                            remitterName += " " + cashPickUpSearchDTO.getCustNameLast2();
                        }
                        response.setRemitterName(remitterName != null ? remitterName : "");
                        // response.setTranNo(cashPickUpSearchDTO.getOrderNo());
                        // response.setTranNo(apiTraceId);
                    } else {
                        logger.info("CashPickUp Search Response is Empty");
                        response.setPayoutStatus(null);
                        response.setErrorMessage("CashPickUp Search Response is Empty");
                        response.setApiStatus(Constants.API_STATUS_INVALID);
                    }
                } else {
                    logger.info("Response is empty or null....");
                    response.setPayoutStatus(null);
                    if (cashPickUpSearchResponse.getErrors().getError() != null) {
//                        response.setErrorMessage(cashPickUpSearchResponse.getErrors().getError().getErrorMsg());
                    } else {
                        response.setErrorMessage("Undefine Error");
                    }

                    response.setApiStatus(Constants.API_STATUS_INVALID);
                }
            } else {
                logger.info("Response is empty or null....");
                response.setPayoutStatus(null);
                response.setErrorMessage("Response is empty or null");
                response.setApiStatus(Constants.API_STATUS_INVALID);
            }
        } catch (Exception e) {
            logger.error("RiaClient: searchRemittance():Exception = " + e);
            response.setPayoutStatus(null);
            response.setErrorMessage("");
            response.setApiStatus(Constants.API_STATUS_ERROR);
        }

        try {
            if (Objects.nonNull(cashPickUpRequestBody))
                response.setOriginalRequest(convertObjectToString(cashPickUpRequestBody));
            if (Objects.nonNull(cashPickUpSearchResponse))
                response.setOriginalResponse(convertObjectToString(cashPickUpSearchResponse));
        } catch (Exception e) {
            logger.error("Error in request response data transforming. Error = " + e);
        }
        logger.info("Exit from searchRemittance() ");
        return response;
    }

    @Override
    public PaymentApiResponse payRemittance(Long apiTraceId, PaymentApiRequest paymentApiRequest) {
        logger.info("Enter into payRemittance() ");
        PaymentApiResponse response = new PaymentApiResponse();
        CashPickUpPaymentRequest cashPickUpPaymentRequestBody = new CashPickUpPaymentRequest();
        CashPickUpPaymentResponse cashPickUpPaymentResponse = new CashPickUpPaymentResponse();
        RiaExchangePropertyDTO riaExchangeProperties = ApiUtil.getRiaExchangePropertyDTO();
        try {
            String dateTimeLocal = DateUtil.currentDateTime("yyyyMMddHHmmss");
            String dateTimeUTC = DateUtil.currentDateTimeInUTC("yyyyMMddHHmmss");

            CashPickUpSearchDTO cashPickUpSearchDTO = new CashPickUpSearchDTO();
            Optional<ApiTrace> apiTrace = apiTraceService.findByTranNo(paymentApiRequest.getTranNo(), Constants.REQUEST_TYPE_SEARCH);

            if (apiTrace.isPresent()) {
                JsonNode actualObj = mapper.readTree(apiTrace.get().getResponseMsg());
                JsonNode jsonNode = actualObj.get("Response");
                String originalResponse = jsonNode.toPrettyString();
                cashPickUpSearchDTO = mapper.readValue(originalResponse, CashPickUpSearchDTO.class);

            } else {
                logger.info("Response is empty or null....");
                response.setPayoutStatus(null);
                response.setErrorMessage("Invalid transaction number");
                response.setApiStatus(Constants.API_STATUS_INVALID);
                return response;
            }

            cashPickUpPaymentRequestBody = makingPaymentRequestBody(dateTimeLocal, dateTimeUTC, paymentApiRequest, cashPickUpSearchDTO);
            HttpEntity<CashPickUpPaymentRequest> httpEntity = new HttpEntity<CashPickUpPaymentRequest>(cashPickUpPaymentRequestBody, ApiUtil.createRiaHeader(apiTraceId, paymentApiRequest.getBrUserId(), paymentApiRequest.getIpAddress(), dateTimeLocal, riaExchangeProperties));
            ResponseEntity<CashPickUpPaymentResponse> apiResponse = restTemplate.exchange(riaExchangeProperties.getPaymentUrl(), HttpMethod.POST, httpEntity, CashPickUpPaymentResponse.class);

            if (apiResponse.getStatusCode() == HttpStatus.REQUEST_TIMEOUT || apiResponse.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
                logger.info("API Server did not return any response. StatusCode:" + apiResponse.getStatusCode());
                response.setPayoutStatus(null);
                response.setErrorMessage("API Server did not return any response. StatusCode:" + apiResponse.getStatusCode());
                response.setApiStatus(Constants.API_STATUS_ERROR_TIMEOUT);
            } else if (apiResponse.getBody() != null) {
                logger.info("response is not empty....");

                cashPickUpPaymentResponse = apiResponse.getBody();

                if (cashPickUpPaymentResponse.getErrors() == null && cashPickUpPaymentResponse.getResponse() != null) {

                    logger.info("response = " + cashPickUpPaymentResponse.getResponse().toString());
                    CashPickUpPaymentDTO cashPickUpPaymentDTO = cashPickUpPaymentResponse.getResponse();

                    if (cashPickUpPaymentDTO != null) {
                        if (!"1000".equals(cashPickUpPaymentDTO.getResponseCode())) {
                            response.setPayoutStatus(null);
                            response.setErrorMessage(cashPickUpPaymentDTO.getResponseText());
                            response.setApiStatus(Constants.API_STATUS_INVALID);
                        } else {
                            response.setPayoutStatus(cashPickUpPaymentDTO.getResponseCode());
                            response.setErrorMessage("");
                            response.setApiStatus(Constants.API_STATUS_VALID);
                        }
                        response.setTransRefID(cashPickUpPaymentDTO.getTransRefID());
                    } else {
                        logger.info("CashPickUp Payment Response is Empty");
                        response.setPayoutStatus(null);
                        response.setErrorMessage("CashPickUp Payment Response is Empty");
                        response.setApiStatus(Constants.API_STATUS_INVALID);
                    }
                } else {
                    logger.info("Response is empty or null....");
                    response.setPayoutStatus(null);
                    if (cashPickUpPaymentResponse.getErrors().getError() != null) {
//                        response.setErrorMessage(cashPickUpPaymentResponse.getErrors().getError().getErrorMsg());
                    } else {
                        response.setErrorMessage("Undefine Error");
                    }

                    response.setApiStatus(Constants.API_STATUS_INVALID);
                }

            } else {
                logger.info("Response is empty or null....");
                response.setPayoutStatus(null);
                response.setErrorMessage("Response is empty or null");
                response.setApiStatus(Constants.API_STATUS_INVALID);
            }
        } catch (Exception e) {
            logger.error("RiaClient: payRemittance():Exception = " + e);
            response.setPayoutStatus(null);
            response.setErrorMessage("");
            response.setApiStatus(Constants.API_STATUS_ERROR);
        }
        try {
            if (Objects.nonNull(cashPickUpPaymentRequestBody))
                response.setOriginalRequest(convertObjectToString(cashPickUpPaymentRequestBody));
            if (Objects.nonNull(cashPickUpPaymentResponse))
                response.setOriginalResponse(convertObjectToString(cashPickUpPaymentResponse));
        } catch (Exception e) {
            logger.error("Error in request response data transforming. Error = " + e);
        }
        return response;
    }

    private CashPickUpPaymentRequest makingPaymentRequestBody(String dateTimeLocal, String dateTimeUTC, PaymentApiRequest paymentApiRequest, CashPickUpSearchDTO cashPickUpSearchDTO) {
        CashPickUpPaymentRequest cashPickUpPaymentRequestBody = new CashPickUpPaymentRequest();
        try {
            cashPickUpPaymentRequestBody.setDateTimeLocal(dateTimeLocal);
            cashPickUpPaymentRequestBody.setDateTimeUTC(dateTimeUTC);
            cashPickUpPaymentRequestBody.setPaidDateTimeLocal(dateTimeLocal);
            cashPickUpPaymentRequestBody.setPaidDateTimeUTC(dateTimeUTC);

            cashPickUpPaymentRequestBody.setPin(paymentApiRequest.getPinno());
            cashPickUpPaymentRequestBody.setCorrespLocID(StringUtil.padLeftZeros(paymentApiRequest.getBrCode(), 4));
            cashPickUpPaymentRequestBody.setBeneTelNo(paymentApiRequest.getMobileNo());
            cashPickUpPaymentRequestBody.setBeneAddress(paymentApiRequest.getAddress());
            cashPickUpPaymentRequestBody.setBeneCity(paymentApiRequest.getCity());
            cashPickUpPaymentRequestBody.setBenePostalCode(paymentApiRequest.getZipCode());
            cashPickUpPaymentRequestBody.setBeneDateOfBirth(paymentApiRequest.getDob()); // Expected format - YYYYMMDD
            cashPickUpPaymentRequestBody.setBeneDistrict(paymentApiRequest.getCity());
            cashPickUpPaymentRequestBody.setTransferReason(paymentApiRequest.getPurposeOfTran());
            cashPickUpPaymentRequestBody.setBeneIDNumber(paymentApiRequest.getBeneIDNumber());
            cashPickUpPaymentRequestBody.setBeneIdentityCode(paymentApiRequest.getBeneIDNumber());

            // Need to set below new field
            cashPickUpPaymentRequestBody.setBeneIDType(paymentApiRequest.getBeneIDType());
            cashPickUpPaymentRequestBody.setBeneIDIssuedBy(paymentApiRequest.getBeneIDIssuedBy());
            cashPickUpPaymentRequestBody.setBeneIDIssuedByCountry(paymentApiRequest.getBeneIDIssuedByCountry());
            cashPickUpPaymentRequestBody.setBeneIDIssuedByState(paymentApiRequest.getBeneIDIssuedByState());
            cashPickUpPaymentRequestBody.setBeneIDIssueDate(paymentApiRequest.getBeneIDIssueDate());
            cashPickUpPaymentRequestBody.setBeneIDExpirationDate(paymentApiRequest.getBeneIDExpirationDate());
            cashPickUpPaymentRequestBody.setBeneOccupation(paymentApiRequest.getBeneOccupation());
            cashPickUpPaymentRequestBody.setBeneGender(paymentApiRequest.getBeneGender());
            cashPickUpPaymentRequestBody.setBeneTaxID(paymentApiRequest.getBeneTaxID());

            cashPickUpPaymentRequestBody.setVerifyOrderTransRefID(cashPickUpSearchDTO.getTransRefID());
            cashPickUpPaymentRequestBody.setOrderNo(cashPickUpSearchDTO.getOrderNo());
            if (cashPickUpSearchDTO.getBeneAmount() != null && !cashPickUpSearchDTO.getBeneAmount().equals("null") && !cashPickUpSearchDTO.getBeneAmount().isEmpty()) {
                cashPickUpPaymentRequestBody.setBeneAmount(cashPickUpSearchDTO.getBeneAmount());
            } else {
                cashPickUpPaymentRequestBody.setBeneAmount("0");
            }

            cashPickUpPaymentRequestBody.setBeneCounty(cashPickUpSearchDTO.getBeneCountry());
            cashPickUpPaymentRequestBody.setBeneState(cashPickUpSearchDTO.getBeneState());
            cashPickUpPaymentRequestBody.setBeneCountry(cashPickUpSearchDTO.getBeneCountry());

            cashPickUpPaymentRequestBody.setCorrespLocAddress(paymentApiRequest.getAddress());
            cashPickUpPaymentRequestBody.setCorrespLocCity(paymentApiRequest.getCity());
            cashPickUpPaymentRequestBody.setCorrespLocState(paymentApiRequest.getCity());

            String beneCustRelationship = StringUtil.capitalizeFirstLetter(paymentApiRequest.getBeneCustRelationship());
            boolean isRelationFound = Arrays.stream(Constants.BENE_CUST_RELATIONSHIP).anyMatch(beneCustRelationship::equals);
            // logger.info("beneCustRelationship = " + beneCustRelationship+",
            // isRelationFound = "+isRelationFound);

            if (isRelationFound) {
                cashPickUpPaymentRequestBody.setBeneCustRelationship(beneCustRelationship);
            } else {
                cashPickUpPaymentRequestBody.setBeneCustRelationship("Other");
            }

            cashPickUpPaymentRequestBody.setCorrespLocName("");
            cashPickUpPaymentRequestBody.setBeneCURPNumber("");
            cashPickUpPaymentRequestBody.setOnBehalfOf("");
            cashPickUpPaymentRequestBody.setCorrespLocPostalCode(paymentApiRequest.getZipCode());
            cashPickUpPaymentRequestBody.setBeneCurrency(cashPickUpSearchDTO.getBeneCurrency());
            cashPickUpPaymentRequestBody.setCorrespLocCountry(cashPickUpSearchDTO.getBeneCountry());
            cashPickUpPaymentRequestBody.setBeneNationality(cashPickUpSearchDTO.getBeneCountry());
            cashPickUpPaymentRequestBody.setBeneCountryOfResidence(cashPickUpSearchDTO.getBeneCountry());
            cashPickUpPaymentRequestBody.setBeneCountryOfBirth(cashPickUpSearchDTO.getBeneCountry());
            cashPickUpPaymentRequestBody.setBeneStateOfBirth(cashPickUpSearchDTO.getBeneState());
            cashPickUpPaymentRequestBody.setBeneCityOfBirth(cashPickUpSearchDTO.getBeneState());
        } catch (Exception e) {
            logger.error("Error in makingPaymentRequestBody. Error = " + e);
            cashPickUpPaymentRequestBody = new CashPickUpPaymentRequest();
        }

        return cashPickUpPaymentRequestBody;
    }

    @Override
    public boolean cancelRemittance(ApiTrace apiTrace) {
        logger.info("Enter into cancelRemittance() ");
        boolean response = false;
        String exchangeCode = "";
        String responseData = "";
        CashPickUpCancelRequest cashPickUpCancelRequestBody = new CashPickUpCancelRequest();
        RiaExchangePropertyDTO riaExchangeProperties = ApiUtil.getRiaExchangePropertyDTO();
        try {
            String originalRequest = apiTrace.getRequestMsg();
            CashPickUpPaymentRequest cashPickUpRequest = mapper.readValue(originalRequest, CashPickUpPaymentRequest.class);
            String userId = apiTrace.getUserId();
            String ipAddress = apiTrace.getIpAddress();

            String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");
            String dateTimeUTC = DateUtil.currentDateTimeInUTC("yyyyMMddHHmmss");

            cashPickUpCancelRequestBody.setBeneAmount(cashPickUpRequest.getBeneAmount());
            cashPickUpCancelRequestBody.setBeneCurrency(cashPickUpRequest.getBeneCurrency());
            cashPickUpCancelRequestBody.setDateTimeLocal(dateTime);
            cashPickUpCancelRequestBody.setDateTimeUTC(dateTimeUTC);
            cashPickUpCancelRequestBody.setOrderNo(cashPickUpRequest.getOrderNo());
            cashPickUpCancelRequestBody.setOrderPaidTransRefID(cashPickUpRequest.getVerifyOrderTransRefID());
            cashPickUpCancelRequestBody.setPin(cashPickUpRequest.getPin());

            HttpEntity<CashPickUpCancelRequest> httpEntity = new HttpEntity<CashPickUpCancelRequest>(cashPickUpCancelRequestBody, ApiUtil.createRiaHeader(apiTrace.getId(), userId, ipAddress, dateTime, riaExchangeProperties));

            ResponseEntity<CashPickUpCancelResponse> apiResponse = restTemplate.exchange(riaExchangeProperties.getCashPickUpCancelUrl(), HttpMethod.DELETE, httpEntity, CashPickUpCancelResponse.class);

            if (Objects.nonNull(apiResponse.getBody()) && Objects.nonNull(apiResponse.getBody().getResponse())) {
                responseData = convertObjectToString(apiResponse.getBody().getResponse());
                CashPickUpCancelResponse apiResponseBody = apiResponse.getBody();
                if (apiResponseBody.getErrors() == null && apiResponseBody.getResponse() != null) {
                    CashPickUpCancelDTO cashPickUpCancelDTO = apiResponseBody.getResponse();
                    logger.info("cashPickUpCancelDTO.getResponseCode() = " + cashPickUpCancelDTO.getResponseCode());
                    logger.info("cashPickUpCancelDTO = " + cashPickUpCancelDTO.toString());
                    if ("1000".equals(cashPickUpCancelDTO.getResponseCode())) {
                        response = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("RiaClient: cancelRemittance():Exception = " + e);
        } finally {
            apiTraceService.saveRequestResponse(apiTrace.getId(), exchangeCode, convertObjectToString(cashPickUpCancelRequestBody), responseData, apiTrace.getRequestType());
            logger.info("cancelRemittance() API request data is -----------------------------> \r\n" + convertObjectToString(cashPickUpCancelRequestBody) + " \n\nResponse: \r\n" + convertObjectToString(responseData));
        }
        return response;
    }

    @Override
    public List<RemittanceData> getDownloadableRemittance(RiaExchangePropertyDTO riaProperties, Date businessDate) {
        ApiTrace apiTrace = null;
        String apiResponse = null;
        List<RemittanceData> list = new ArrayList<>();
        ResponseEntity<DownloadableOrderResponse> response = null;
        try {
            String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");
            apiTrace = apiTraceService.create(riaProperties.getExchangeCode(), Constants.REQUEST_TYPE_DOWNLOAD_REQ, businessDate);
            if (Objects.isNull(apiTrace)) {
                logger.info("ApiTrace creation error. Scheduler aborted");
                return new ArrayList<>();
            }
            HttpHeaders headers = ApiUtil.createRiaHeader(apiTrace.getId(), RIA_EXCHANGE_HOUSE_BRANCH_USER, RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID, dateTime, riaProperties);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            response = restTemplate.exchange(riaProperties.getDownloadableUrl(), HttpMethod.GET, httpEntity, DownloadableOrderResponse.class);
            logger.info("DownloadableRemittance API response status: {} ", response.getStatusCode());

            if (HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())) {
                if (Objects.nonNull(response.getBody().getResponse()) && Objects.nonNull(response.getBody().getResponse().getOrder())) {
                    list = riaRemittanceMapper.preparingRemittanceDataFromResponse(apiTrace, riaProperties.getExchangeCode(), response.getBody().getResponse(), bankCode);
                    apiResponse = convertObjectToString(response.getBody().getResponse());
                }
            } else {
                logger.error("Error Responded Downloadable data for TraceID: " + apiTrace.getId() + " - Response Code: " + response.getStatusCode());
            }
        } catch (Exception ex) {
            Long tracId = Objects.nonNull(apiTrace) ? apiTrace.getId() : null;
            logger.error("Error in processing downloadable data for TraceID: {}, Error message: {} ", tracId, ex.getMessage());
        } finally {
            if (list.isEmpty() && Objects.nonNull(apiTrace)) {
                apiTraceService.deleteById(apiTrace.getId());
            } else if (!list.isEmpty()) {
                apiTraceService.saveApiTrace(apiTrace, riaProperties.getExchangeCode(), null, apiResponse, Constants.API_STATUS_VALID, Constants.REQUEST_TYPE_DOWNLOAD_REQ, null);
            }
            logger.info("DownloadableRemittance response: {} ", (Objects.nonNull(apiResponse) ? apiResponse : "Download API Response is empty."));
        }
        return list;
    }

    @Override
    public List<StopRemittanceData> getCancelRemittance(RiaExchangePropertyDTO riaProperties) {
        String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");
        ApiTrace trace = apiTraceService.create(riaProperties.getExchangeCode(), Constants.REQUEST_TYPE_CANCEL_REQ, null);
        HttpHeaders headers = ApiUtil.createRiaHeader(trace.getId(), RIA_EXCHANGE_HOUSE_BRANCH_USER, RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID, dateTime, riaProperties);
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        List<StopRemittanceData> list = new ArrayList<StopRemittanceData>();
        try {
            ResponseEntity<CancelDownloadResponse> response = restTemplate.exchange(riaProperties.getNotifyCancelReqUrl(), HttpMethod.GET, httpEntity, CancelDownloadResponse.class);
            saveTraceForGetCancelRemittanceApi(trace.getId(), riaProperties.getExchangeCode(), response, Constants.REQUEST_TYPE_CANCEL_REQ);

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                if (response.getBody() != null && response.getBody().getResponse() != null) {
                    for (CancelorderDTO r : response.getBody().getResponse()) {
                        StopRemittanceData s = new StopRemittanceData();
                        s.setReferenceNo(r.getScorderNo());
                        s.setExchangeCode(riaProperties.getExchangeCode());
                        s.setRequestOn(new Date());
                        s.setRequestBy(RIA_EXCHANGE_HOUSE_BRANCH_USER);
                        s.setProcessStatus("PENDING");
                        list.add(s);
                    }
                }
            }
            if (list.isEmpty()) apiTraceService.deleteById(trace.getId());
        } catch (Exception ex) {
            logger.error("Error occured in getCancelRemittance() ", ex);
        }
        return list;
    }

    @Override
    public StopRemittanceData notifyCancelStatus(StopRemittanceData data, RiaExchangePropertyDTO riaProperties) {
        logger.info("Enter into notifyCancelStatus(): excode: " + data.getExchangeCode());

        ApiTrace trace = null;
        String date = DateUtil.currentDateTime("yyyyMMdd");
        String time = DateUtil.currentDateTimeInUTC("HHmmss");
        String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");
        CancelNotifyRequest request = new CancelNotifyRequest();
        request.setPcOrderNo(data.getReferenceNo());
        request.setScOrderNo(data.getReferenceNo());
        request.setResponseCode(data.getExchangeStatus());
        request.setResponseDate(date);
        request.setResponseTime(time);

        CancelNotifyResponses response = new CancelNotifyResponses();
        try {
            trace = apiTraceService.create(riaProperties.getExchangeCode(), Constants.REQUEST_TYPE_CANCEL_REQ_RES, null);
            logger.info("Notify Cancel Status request " + request);

            HttpEntity<CancelNotifyRequest> httpEntity = new HttpEntity<CancelNotifyRequest>(request, ApiUtil.createRiaHeader(trace.getId(), RIA_EXCHANGE_HOUSE_BRANCH_USER, RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID, dateTime, riaProperties));
            response = restTemplate.postForObject(riaProperties.getNotifyCancelReqUrl(), httpEntity, CancelNotifyResponses.class);

            if (Objects.nonNull(response)) {
                logger.info("response is not empty...." + response);
                if (Objects.isNull(response.getErrors()) && Objects.nonNull(response.getResponse())) {
                    logger.info("response = " + response.getResponse().toString());
                    CancelNotifyResponse dto = response.getResponse();
                    if (dto != null) {
                        if (!"1000".equals(dto.getNotificationCode())) {
                            data.setResponed("Success");
                            return data;
                        } else {
                            data.setResponed(dto.getNotificationCode());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Error orccured on calling notifyCancelStatus", ex);
        } finally {
            apiTraceService.saveApiTrace(trace, riaProperties.getExchangeCode(), convertObjectToString(request), convertObjectToString(response), null, Constants.REQUEST_TYPE_CANCEL_REQ_RES, null);
            logger.info("API request data is -----> \r\n" + convertObjectToString(request) + " \n\nResponse: \r\n" + convertObjectToString(response));
        }
        return null;
    }

    @Override
    public List<StopRemittanceData> notifyCancelStatus(RiaExchangePropertyDTO riaProperties) {
        List<StopRemittanceData> remittanceDataList = new ArrayList<>();
        List<StopRemittanceData> stopRemittanceDataList = commonService.getCancelRequestUnProcessData();
        for (StopRemittanceData data : stopRemittanceDataList) {
            StopRemittanceData updatedData = notifyCancelStatus(data, riaProperties);
            if (Objects.nonNull(updatedData)) remittanceDataList.add(data);
        }
        return remittanceDataList;
    }

    @Override
    public void notifyRemittanceReceivedStatus(List<RemittanceData> data, RiaExchangePropertyDTO riaProperties, Date businessDate, boolean notifyReceived) {
        List<ApiTrace> apiTraceList = new ArrayList<>();
        try {
            String date = DateUtil.currentDateTime("yyyyMMdd");
            String time = DateUtil.currentDateTimeInUTC("HHmmss");
            String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");

            for (RemittanceData rem : data) {
                OrderReceivedStatusRequest req = new OrderReceivedStatusRequest();
                req.setPcOrderNo(rem.getReferenceNo());
                req.setScOrderNo(rem.getReferenceNo());
                if (notifyReceived) {
                    req.setOrderStatus(RemittanceDataStatus.RECEIVED);
                } else {
                    if (rem.isDuplicate()) {
                        req.setOrderStatus(RemittanceDataStatus.REJECTED);
                        req.setReason(Constants.RIA_DUPLICATE_TRANSACTION_NOTIFY_MESSAGE);
                    } else if (rem.getProcessStatus().equals(RemittanceDataStatus.REJECTED)) {
                        String stopPayReason = Objects.nonNull(rem.getStopPayReason()) ? riaRemittanceMapper.mapStopReason(rem.getStopPayReason(), rem.getFinalStatus(), rem.getCreditorAccountNo(), rem.getRemittanceMessageType()) : null;
                        req.setOrderStatus(RemittanceDataStatus.REJECTED);
                        req.setReason(stopPayReason);
                    }
                }
                req.setStatusDate(date);
                req.setStatusTime(time);

                ApiTrace apiTrace = apiTraceService.create(rem.getExchangeCode(), Constants.REQUEST_TYPE_NOTIFY_REM_STATUS, businessDate);
                req.setNotificationID(String.valueOf(apiTrace.getId()));

                HttpEntity<OrderReceivedStatusRequest> httpEntity = new HttpEntity<>(req, ApiUtil.createRiaHeader(apiTrace.getId(), RIA_EXCHANGE_HOUSE_BRANCH_USER, RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID, dateTime, riaProperties));

                logger.info("Notify Received Status Request Body " + req);
                ResponseEntity<OrderStatusResponse> responseEntity = restTemplate.exchange(riaProperties.getNotifyRemStatusUrl(), HttpMethod.POST, httpEntity, OrderStatusResponse.class);

                apiTraceList.add(buildApiTrace(apiTrace, rem.getExchangeCode(), req, responseEntity, businessDate));

                if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                    OrderStatusResponse response = responseEntity.getBody();
                    if (Objects.nonNull(response)) {
                        logger.info("Notification Response data:" + response);
                    } else {
                        logger.error("Response body contains empty response against: " + rem.getReferenceNo());
                    }
                } else {
                    logger.error("API Responded with different status code: " + responseEntity.getStatusCode());
                }
            }
        } catch (Exception ex) {
            logger.error("Error occurred on calling notifyRemittanceStatus", ex);
        } finally {
            apiTraceService.saveAllApiTrace(apiTraceList);
        }
    }

    @Override
    public RemittanceData notifyRemittanceStatus(RemittanceData data, RiaExchangePropertyDTO riaProperties) {
        logger.info("Enter into notifyRemittanceStatus() ");
        OrderStatusRequest req = new OrderStatusRequest();
        OrderStatusResponse response = new OrderStatusResponse();
        ApiTrace apiTrace = null;
        try {
            String date = DateUtil.currentDateTime("yyyyMMdd");
            String time = DateUtil.currentDateTimeInUTC("HHmmss");
            String dateTime = DateUtil.currentDateTime("yyyyMMddHHmmss");

            req.setPcOrderNo(data.getReferenceNo());
            req.setScOrderNo(data.getReferenceNo());
            String stopPayReason = Objects.nonNull(data.getStopPayReason()) ? riaRemittanceMapper.mapStopReason(data.getStopPayReason(), data.getFinalStatus(), data.getCreditorAccountNo(), data.getRemittanceMessageType()) : null;
            List<String> finalStatuses = Arrays.asList("03", "04", "05", "07", "08", "14", "15", "22");
            if (RemittanceDataStatus.COMPLETED.equals(data.getProcessStatus())) {
                if (data.getFinalStatus().equals("02")) {
                    req.setOrderStatus(RemittanceDataStatus.PAID);
                } else if (finalStatuses.contains(data.getFinalStatus())) {
                    req.setOrderStatus(RemittanceDataStatus.REJECTED);
                    req.setReason(stopPayReason);
                }
            } else {
                req.setOrderStatus(RemittanceDataStatus.RECEIVED);
            }
            req.setStatusDate(date);
            req.setStatusTime(time);

            apiTrace = apiTraceService.create(riaProperties.getExchangeCode(), Constants.REQUEST_TYPE_NOTIFY_REM_STATUS, data.getProcessDate());
            req.setNotificationID(String.valueOf(apiTrace.getId()));
            HttpEntity<OrderStatusRequest> httpEntity = new HttpEntity<>(req, ApiUtil.createRiaHeader(apiTrace.getId(), RIA_EXCHANGE_HOUSE_BRANCH_USER, RIA_EXCHANGE_HOUSE_BRANCH_USER_DEVICE_ID, dateTime, riaProperties));

            logger.info("Request notifyRemittanceStatus...." + req);

            ResponseEntity<OrderStatusResponse> responseEntity = restTemplate.exchange(riaProperties.getNotifyRemStatusUrl(), HttpMethod.POST, httpEntity, OrderStatusResponse.class);
            response = responseEntity.getBody();
            logger.info("Notification Response notifyRemittanceStatus():\n" + response);

            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                if (Objects.nonNull(response)) {
                    if (Objects.isNull(response.getErrors()) && Objects.nonNull(response.getResponse())) {
                        OrderStatusDTO dto = response.getResponse();
                        if (Objects.nonNull(dto)) {
                            String reason = dto.getNotificationCode() + "-" + dto.getNotificationDesc();
                            data.setApiResponse(reason.length() >= 512 ? reason.substring(0, 512) : reason);
                            if ("1000".equals(dto.getNotificationCode())) {
                                data.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_DONE);
                                return data;
                            } else {
                                logger.error("Different status code responsed on Notification status: " + dto.getNotificationCode() + " For reference No: " + data.getReferenceNo());
                                if (data.getTryCount() >= Constants.TRY_COUNT) {
                                    data.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_ERROR);
                                } else {
                                    data.setTryCount(data.getTryCount() + 1);
                                }
                                return data;
                            }
                        }
                    }
                } else {
                    logger.error("Response body contains empty response against: " + data.getReferenceNo());
                }
            } else {
                logger.error("API Responded with different status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception ex) {
            logger.error("Error occurred on calling notifyRemittanceStatus", ex);
        } finally {
            apiTraceService.saveApiTrace(apiTrace, riaProperties.getExchangeCode(), convertObjectToString(req), convertObjectToString(response), Constants.REQUEST_TYPE_NOTIFY_REM_STATUS, Constants.REQUEST_TYPE_NOTIFY_REM_STATUS, null);
            logger.info("API request data is -----------> \r\n" + convertObjectToString(req) + " \n and Response: \r\n" + convertObjectToString(response));
        }
        return null;
    }

    @Override
    public List<RemittanceData> notifyRemittanceStatus(RiaExchangePropertyDTO riaProperties) {
        List<RemittanceData> notifiedRemittanceDataList = new ArrayList<>();
        List<RemittanceData> remittanceDataList = commonService.getRemittanceDataForNotification(riaProperties.getExchangeCode());
        for (RemittanceData data : remittanceDataList) {
            RemittanceData updatedData = notifyRemittanceStatus(data, riaProperties);
            if (Objects.nonNull(updatedData)) notifiedRemittanceDataList.add(updatedData);
        }
        return notifiedRemittanceDataList;
    }

    private ApiTrace buildApiTrace(ApiTrace apiTrace, String exchangeCode, OrderReceivedStatusRequest req, ResponseEntity<OrderStatusResponse> responseEntity, Date businessDate) {
        try {
            if (Objects.isNull(apiTrace)) {
                apiTrace = new ApiTrace();
            }
            apiTrace.setExchangeCode(exchangeCode);
            apiTrace.setCbsDate(businessDate);
            apiTrace.setRequestMsg(convertObjectToString(req));

            if (Objects.nonNull(responseEntity.getBody()) && Objects.nonNull(responseEntity.getBody().getResponse())) {
                apiTrace.setResponseMsg(convertObjectToString(responseEntity.getBody().getResponse()));
            }
            if (Objects.nonNull(responseEntity.getBody()) && Objects.nonNull(responseEntity.getBody().getErrors())) {
                apiTrace.setResponseMsg(convertObjectToString(responseEntity.getBody()));
            }
            apiTrace.setStatus(Constants.REQUEST_TYPE_NOTIFY_REM_STATUS);
        } catch (Exception e) {
            logger.error("Error occurred on buildApiTrace", e);
        }
        return apiTrace;
    }

    private void saveTraceForGetCancelRemittanceApi(Long traceId, String exchangeCode, ResponseEntity<CancelDownloadResponse> response, String status) {
        try {
            if (Objects.nonNull(response.getBody()) && Objects.nonNull(response.getBody().getResponse())) {
                String responseData = convertObjectToString(response.getBody().getResponse());
                if (Objects.nonNull(responseData)) {
                    logger.info("getCancelRemittance(): responseData Saved" + responseData);
                    apiTraceService.saveRequestResponse(traceId, exchangeCode, null, responseData, status);
                }
            }
        } catch (Exception e) {
            logger.error("Error in response data transforming in getCancelRemittance(). Error = " + e);
        }
    }


}