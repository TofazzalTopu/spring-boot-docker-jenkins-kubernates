package com.info.demo.service.impl.ria;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.Branch;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.ria.*;
import com.info.demo.repository.BranchRepository;
import com.info.demo.service.common.ApiTraceService;
import com.info.demo.service.common.RemittanceProcessService;
import com.info.demo.service.ria.RiaCashPaymentService;
import com.info.demo.service.ria.RiaExchangeHouseApiService;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RiaCashPaymentServiceImpl implements RiaCashPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(RiaCashPaymentServiceImpl.class);


    private final ObjectMapper mapper;

    private final ApiTraceService apiTraceService;
    private final BranchRepository branchRepository;

    private final RemittanceProcessService remittanceProcessService;

    private final RiaExchangeHouseApiService riaExchangeHouseApiService;


    @Value("#{${bank.code}}")
    private String bankCode;

    @Value("${bank.name}")
    private String bankName;

    public RiaCashPaymentServiceImpl(ObjectMapper mapper, ApiTraceService apiTraceService, BranchRepository branchRepository, RemittanceProcessService remittanceProcessService, RiaExchangeHouseApiService riaExchangeHouseApiService) {
        this.mapper = mapper;
        this.apiTraceService = apiTraceService;
        this.branchRepository = branchRepository;
        this.remittanceProcessService = remittanceProcessService;
        this.riaExchangeHouseApiService = riaExchangeHouseApiService;
    }

    @Override
    public SearchApiResponse searchRemittance(SearchApiRequest searchApiRequest) {
        logger.info("Enter into searchRemittance()");
        SearchApiResponse response = new SearchApiResponse();
        Long apiTraceId = null;
        try {
            ApiTrace apiTrac = apiTraceService.create(searchApiRequest.getExchcode(), Constants.REQUEST_TYPE_SEARCH, null);
            apiTraceId = apiTrac.getId();
            response = riaExchangeHouseApiService.searchRemittance(apiTraceId, searchApiRequest);
            if (response == null) {
                response = new SearchApiResponse();
                logger.info("Error occured during fetch data.");
                response.setPayoutStatus(null);
                response.setErrorMessage("Unable to fetch data.");
            }
        } catch (Exception e) {
            logger.error("Error occured in searchRemittance(). Error = " + e);
            response.setPayoutStatus(null);
            response.setErrorMessage(e.getMessage());
        }
        if (Objects.nonNull(apiTraceId)) apiTraceService.addToApiTrace(apiTraceId, response, searchApiRequest);
        logger.info("Exit from searchRemittance()");

        return response;
    }

    @Override
    public PaymentApiResponse payRemittance(PaymentApiRequest paymentApiRequest) {
        logger.info("Enter into payRemittance()");
        PaymentApiResponse response = new PaymentApiResponse();
        Long apiTraceId = null;
        try {
            ApiTrace trace = apiTraceService.create(paymentApiRequest.getExchCode(), Constants.REQUEST_TYPE_PAYMENT, null);
            response = riaExchangeHouseApiService.payRemittance(trace.getId(), paymentApiRequest);
            apiTraceId = trace.getId();

            if (response == null) {
                logger.info("Unable to complete the payment.");
                response = new PaymentApiResponse();
                response.setPayoutStatus(null);
                response.setErrorMessage("Unable to complete the payment.");

            } else if (response.getApiStatus().equals(Constants.API_STATUS_VALID)) {
                CashPickUpSearchDTO cashPickUpSearchDTO = new CashPickUpSearchDTO();
                Optional<ApiTrace> apiTrace = apiTraceService.findByTranNo(paymentApiRequest.getTranNo(), Constants.REQUEST_TYPE_SEARCH);
                System.out.print(apiTrace);
                if (apiTrace.isPresent()) {
                    logger.info("apiTrace is present");
                    JsonNode actualObj = mapper.readTree(apiTrace.get().getResponseMsg());
                    JsonNode jsonNode = actualObj.get("Response");
                    String originalResponse = jsonNode.toPrettyString();
                    cashPickUpSearchDTO = mapper.readValue(originalResponse, CashPickUpSearchDTO.class);
                    RemittanceData data = prepareRemittanceData(Long.parseLong(paymentApiRequest.getTranNo()), cashPickUpSearchDTO, apiTrace.get().getExchangeCode(), apiTrace.get().getBranchCode(), trace.getCbsDate());
                    remittanceProcessService.saveWebOrSpotData(data, paymentApiRequest.getExchCode(), Constants.FILE_NAME);
                } else {
                    logger.info("Unable to save because the data for search reference " + paymentApiRequest.getTranNo() + " is not found");
                }

            } else {
                logger.info("API status is not valid. Error description = " + response.getErrorMessage());
            }

        } catch (Exception e) {
            logger.error("Error occured in payRemittance(). Error = " + e);
            response.setPayoutStatus(null);
            response.setErrorMessage(e.getMessage());
        }
        if (Objects.nonNull(apiTraceId)) apiTraceService.addToApiTrace(apiTraceId, response, paymentApiRequest);
        return response;
    }

    public RemittanceData prepareRemittanceData(long traceId, CashPickUpSearchDTO cashPickUpSearchDTO, String exchangeCode, int branchCode, Date currentBusinessDate) {
        logger.info("Enter into prepareRemittanceData");
        RemittanceData rem = new RemittanceData();
        try {
            rem.setAmount(new BigDecimal(cashPickUpSearchDTO.getBeneAmount()));
            rem.setCityDistrict(cashPickUpSearchDTO.getBeneCity());
            rem.setCountryOriginate(cashPickUpSearchDTO.getCountryFrom() == null ? cashPickUpSearchDTO.getCustCountry() : cashPickUpSearchDTO.getCountryFrom());
            //rem.setCreditorAccountNo(cashPickUpSearchDTO.getBankAccountNo());

            String recieverName = "";
            if (cashPickUpSearchDTO.getBeneNameFirst() != null) {
                recieverName = cashPickUpSearchDTO.getBeneNameFirst() + " ";
            }
            if (cashPickUpSearchDTO.getBeneNameMiddle() != null) {
                recieverName += cashPickUpSearchDTO.getBeneNameMiddle() + " ";
            }
            if (cashPickUpSearchDTO.getBeneNameLast1() != null) {
                recieverName += cashPickUpSearchDTO.getBeneNameLast1() + " ";
            }

            rem.setCreditorName(recieverName);
            rem.setExchangeCode(exchangeCode);
            rem.setExchangeName(Constants.RIA_EXCHANGE_HOUSE);
            rem.setExchangeTransactionDate(cashPickUpSearchDTO.getOrderDate());
            rem.setExchangeTransactionNo(cashPickUpSearchDTO.getOrderNo());

            if (cashPickUpSearchDTO.getCustID1No() != null) {
                rem.setIdNo(cashPickUpSearchDTO.getCustID1No());
            }

            if (rem.getIdNo() == null) {
                rem.setIdNo(cashPickUpSearchDTO.getCustID1No());
            }
            rem.setSenderPhone(cashPickUpSearchDTO.getCustTelNo());
            rem.setPhoneNo(cashPickUpSearchDTO.getBeneTelNo());
            rem.setPurpose(cashPickUpSearchDTO.getTransferReason());
            rem.setReceiverAddress(cashPickUpSearchDTO.getBeneAddress());

            rem.setReferenceNo(cashPickUpSearchDTO.getPin()); //cashPickUpSearchDTO.getOrderNo() -- previously stored order no. adding pin no as reference for report purpose
            rem.setSecurityCode(cashPickUpSearchDTO.getPin());

            rem.setFinalStatus("02"); // setting final status as 02 as transaction is complete

            rem.setBankName(bankName);
            rem.setBankCode(bankCode);
            rem.setBranchCode(branchCode);
            rem.setOwnBranchCode(branchCode); //New added change


            try {
                /*
                 * if (cashPickUpSearchDTO.getOrderDate() != null) {
                 * rem.setReferenceDate(dt.parse(cashPickUpSearchDTO.getOrderDate())); }
                 */
                Date referenceDate = apiTraceService.getRefDate(exchangeCode, cashPickUpSearchDTO.getPin(), currentBusinessDate);
                rem.setReferenceDate(referenceDate);

                Optional<Branch> branchOptional = branchRepository.getBranch(Short.parseShort("1"), branchCode);
                if (branchOptional.isPresent()) {
                    rem.setBranchName(branchOptional.get().getName());
                    //added bank code before branchCode
                    rem.setBranchRoutingNumber(bankCode + "" + branchOptional.get().getRoutingNumber());
                }

            } catch (Exception e) {
                logger.error("Error in prepareRemittanceData due to data. Error = " + e);
            }


            rem.setRemittanceMessageType("WEB"); // It might be SPOT also
            rem.setSenderAddress(cashPickUpSearchDTO.getCustAddress());

            String senderName = "";
            if (cashPickUpSearchDTO.getCustNameFirst() != null) {
                senderName = cashPickUpSearchDTO.getCustNameFirst() + " ";
            }
            if (cashPickUpSearchDTO.getCustNameMiddle() != null) {
                senderName += cashPickUpSearchDTO.getCustNameMiddle() + " ";
            }
            if (cashPickUpSearchDTO.getCustNameLast1() != null) {
                senderName += cashPickUpSearchDTO.getCustNameLast1() + " ";
            }

            rem.setSenderName(senderName);
            rem.setProcessStatus(RemittanceDataStatus.COMPLETED);
            rem.setProcessDate(currentBusinessDate);
            rem.setMiddlewareId(traceId);
            rem.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_UNDONE);
            rem.setSourceType(Constants.API_SOURCE_TYPE);
            rem.setCurrencyOriginate(cashPickUpSearchDTO.getCustCurrency());
            //rem.setSenderOccupation();
        } catch (Exception e) {
            rem = new RemittanceData();
            logger.error("Error in prepareRemittanceData. Error = " + e);
        }
        logger.info("Exit from prepareRemittanceData");
        return rem;
    }


}
