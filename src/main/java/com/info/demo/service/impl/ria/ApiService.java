package com.info.demo.service.impl.ria;

import com.info.demo.model.ria.PaymentApiRequest;
import com.info.demo.model.ria.PaymentApiResponse;
import com.info.demo.model.ria.SearchApiRequest;
import com.info.demo.model.ria.SearchApiResponse;
import com.info.demo.service.common.CommonService;
import com.info.demo.service.ria.RiaCashPaymentService;
import com.info.demo.util.Constants;
import com.info.demo.util.ParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.info.demo.util.ObjectConverter.convertObjectToString;
import static com.info.demo.util.ParseUtil.*;

@Service
@Transactional
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    private final CommonService commonService;

    private final RiaCashPaymentService riaCashPaymentService;

    public ApiService(CommonService commonService, RiaCashPaymentService riaCashPaymentService) {
        this.commonService = commonService;
        this.riaCashPaymentService = riaCashPaymentService;
    }

    public String searchRemittance(String userId, String password, SearchApiRequest apiRequest, HttpServletRequest request) {
        logger.info("Enter into ApiService: searchRemittance()");
        String responseData = "";
        SearchApiResponse searchApiResponse = new SearchApiResponse();
        try {
            if (!commonService.isAuthorizedRequest(userId, password)) {
                logger.info("Unauthorized access. userId = " + userId + ",password = " + password);
                searchApiResponse.setErrorMessage("Unauthorized access");
            } else if (isNullOrEmpty(apiRequest.getBruserid()) || isNullOrEmpty(apiRequest.getBrcode()) || isNullOrEmpty(apiRequest.getExchcode()) || isNullOrEmpty(apiRequest.getPinno())) {
                searchApiResponse.setErrorMessage("Required field should not empty (). bruserid = " + apiRequest.getBruserid()
                        + ",brcode = " + apiRequest.getBrcode() + ",exchcode = " + apiRequest.getExchcode() + ",pinno = " + apiRequest.getPinno());
            } else {
                if (Objects.nonNull(request)) {
                    String header = Objects.nonNull(request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
                    apiRequest.setIpAddress(header);
                }
                searchApiResponse = riaCashPaymentService.searchRemittance(apiRequest);
            }

            logger.info("Exit from ApiService: searchRemittance(). Success...");
        } catch (Exception e) {
            logger.error("Error in ApiService: searchRemittance(). Error = " + e);
            searchApiResponse.setErrorMessage(e.getMessage());
        }

        try {
            if (Objects.nonNull(searchApiResponse)) responseData = convertObjectToString(searchApiResponse);
        } catch (Exception e) {
            logger.error("Error in request response data transforming. Error = " + e);
        }
        return responseData;
    }

    public String payRemittance(String userId, String password, String data, HttpServletRequest request) {
        logger.info("Enter into ApiService: payRemittance()");
        PaymentApiResponse paymentApiResponse = new PaymentApiResponse();
        String responseData = "";
        try {
            logger.info("requestBody =================> " + data);
            PaymentApiRequest paymentApiRequest = parseAndPrepareRequest(data, request.getRemoteAddr());
            if (!commonService.isAuthorizedRequest(userId, password)) {
                logger.info("Unauthorized access. userId = " + userId + ",password = " + password);
                paymentApiResponse = ParseUtil.unauthorized(paymentApiResponse);
            } else if (!isValidPaymentRequest(paymentApiRequest)) {
                paymentApiResponse = mapPaymentApiResponse(paymentApiResponse, paymentApiRequest);
            } else {
                String header = Objects.nonNull(request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
                paymentApiRequest.setIpAddress(header);
                paymentApiResponse = riaCashPaymentService.payRemittance(paymentApiRequest);
            }
            logger.info("Exit from ApiService: payRemittance(). Success...");
        } catch (Exception e) {
            logger.error("Error in ApiService: payRemittance(). Error = " + e);
            paymentApiResponse.setPayoutStatus(null);
            paymentApiResponse.setErrorMessage(e.getMessage());
            paymentApiResponse.setApiStatus(Constants.API_STATUS_ERROR);
        }

        try {
            if (Objects.nonNull(paymentApiResponse)) responseData = convertObjectToString(paymentApiResponse);
        } catch (Exception e) {
            logger.error("Error in request response data transforming. Error = " + e);
        }
        return responseData;
    }


}
