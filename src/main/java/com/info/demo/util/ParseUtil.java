package com.info.demo.util;

import com.info.demo.model.ria.PaymentApiRequest;
import com.info.demo.model.ria.PaymentApiResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class ParseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ParseUtil.class);

    public static PaymentApiRequest parseAndPrepareRequest(String data, String requestIp) {
        PaymentApiRequest paymentApiRequest = new PaymentApiRequest();
        try {
            JSONObject json = new JSONObject(data);

            paymentApiRequest.setIpAddress(requestIp);

            if (!json.isNull("Address"))
                paymentApiRequest.setAddress(json.getString("Address"));

            if (!json.isNull("BrCode"))
                paymentApiRequest.setBrCode(json.getString("BrCode"));

            if (!json.isNull("BrUserId"))
                paymentApiRequest.setBrUserId(json.getString("BrUserId"));

            if (!json.isNull("City"))
                paymentApiRequest.setCity(json.getString("City"));

            if (!json.isNull("DOB"))
                paymentApiRequest.setDob(json.getString("DOB"));

            if (!json.isNull("ExchCode"))
                paymentApiRequest.setExchCode(json.getString("ExchCode"));

            if (!json.isNull("MobileNo"))
                paymentApiRequest.setMobileNo(json.getString("MobileNo"));

            if (!json.isNull("BeneIDNumber"))
                paymentApiRequest.setBeneIDNumber(json.getString("BeneIDNumber"));

            if (!json.isNull("Pinno"))
                paymentApiRequest.setPinno(json.getString("Pinno"));

            if (!json.isNull("PurposeOfTran"))
                paymentApiRequest.setPurposeOfTran(json.getString("PurposeOfTran"));

            if (!json.isNull("RelationWithRemitter"))
                paymentApiRequest.setRelationWithRemitter(json.getString("RelationWithRemitter"));

            if (!json.isNull("TranNo"))
                paymentApiRequest.setTranNo(json.getString("TranNo"));

            if (!json.isNull("ZipCode"))
                paymentApiRequest.setZipCode(json.getString("ZipCode"));

            if (!json.isNull("BeneIDType"))
                paymentApiRequest.setBeneIDType(json.getString("BeneIDType"));

            if (!json.isNull("BeneIDIssuedBy"))
                paymentApiRequest.setBeneIDIssuedBy(json.getString("BeneIDIssuedBy"));

            if (!json.isNull("BeneIDIssuedByCountry"))
                paymentApiRequest.setBeneIDIssuedByCountry(json.getString("BeneIDIssuedByCountry"));

            if (!json.isNull("BeneIDIssuedByState"))
                paymentApiRequest.setBeneIDIssuedByState(json.getString("BeneIDIssuedByState"));

            if (!json.isNull("BeneIDIssueDate"))
                paymentApiRequest.setBeneIDIssueDate(json.getString("BeneIDIssueDate"));

            if (!json.isNull("BeneIDExpirationDate"))
                paymentApiRequest.setBeneIDExpirationDate(json.getString("BeneIDExpirationDate"));

            if (!json.isNull("BeneOccupation"))
                paymentApiRequest.setBeneOccupation(json.getString("BeneOccupation"));

            if (!json.isNull("BeneGender"))
                paymentApiRequest.setBeneGender(json.getString("BeneGender"));

            if (!json.isNull("BeneTaxID"))
                paymentApiRequest.setBeneTaxID(json.getString("BeneTaxID"));

            if (!json.isNull("BeneCustRelationship"))
                paymentApiRequest.setBeneCustRelationship(json.getString("BeneCustRelationship"));

        } catch (Exception e) {
            logger.error("Error in parseAndPrepareRequest: Error = " + e);
            paymentApiRequest = new PaymentApiRequest();
        }
        return paymentApiRequest;
    }

    public static PaymentApiResponse mapPaymentApiResponse(PaymentApiResponse paymentApiResponse, PaymentApiRequest paymentApiRequest) {
        paymentApiResponse.setPayoutStatus(null);

        if (Objects.isNull(paymentApiRequest)) {
            paymentApiResponse.setErrorMessage("Request Can not be empty");
        } else if (Objects.isNull(paymentApiRequest.getExchCode()) || paymentApiRequest.getExchCode().isEmpty()) {
            paymentApiResponse.setErrorMessage("Exchange Code can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getPinno()) || paymentApiRequest.getPinno().isEmpty()) {
            paymentApiResponse.setErrorMessage("Pin No can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getBrUserId()) || paymentApiRequest.getBrUserId().isEmpty()) {
            paymentApiResponse.setErrorMessage("Branch User ID can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getBrCode()) || paymentApiRequest.getBrCode().isEmpty()) {
            paymentApiResponse.setErrorMessage("Branch Code can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getBeneIDNumber()) || paymentApiRequest.getBeneIDNumber().isEmpty()) {
            paymentApiResponse.setErrorMessage("Beneficiary ID can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getDob()) || paymentApiRequest.getDob().isEmpty()) {
            paymentApiResponse.setErrorMessage("Date of Birth can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getTranNo()) || paymentApiRequest.getTranNo().isEmpty()) {
            paymentApiResponse.setErrorMessage("Tran No can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getAddress()) || paymentApiRequest.getAddress().isEmpty()) {
            paymentApiResponse.setErrorMessage("Address can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getCity()) || paymentApiRequest.getCity().isEmpty()) {
            paymentApiResponse.setErrorMessage("City can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getMobileNo()) || paymentApiRequest.getMobileNo().isEmpty()) {
            paymentApiResponse.setErrorMessage("Mobile No can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getPurposeOfTran()) || paymentApiRequest.getPurposeOfTran().isEmpty()) {
            paymentApiResponse.setErrorMessage("Purpose of Transaction can not be null or empty");
        } else if (Objects.isNull(paymentApiRequest.getRelationWithRemitter()) || paymentApiRequest.getRelationWithRemitter().isEmpty()) {
            paymentApiResponse.setErrorMessage("Relation with remitter can not be null or empty");
        }
        paymentApiResponse.setApiStatus(Constants.API_STATUS_INVALID);
        return paymentApiResponse;
    }

    public static boolean isValidPaymentRequest(PaymentApiRequest paymentApiRequest) {
        if (Objects.nonNull(paymentApiRequest)
                && Objects.nonNull(paymentApiRequest.getExchCode()) && !paymentApiRequest.getExchCode().isEmpty()
                && Objects.nonNull(paymentApiRequest.getPinno()) && !paymentApiRequest.getPinno().isEmpty()
                && Objects.nonNull(paymentApiRequest.getBrUserId()) && !paymentApiRequest.getBrUserId().isEmpty()
                && Objects.nonNull(paymentApiRequest.getBrCode()) && !paymentApiRequest.getBrCode().isEmpty()
                && Objects.nonNull(paymentApiRequest.getBeneIDNumber()) && !paymentApiRequest.getBeneIDNumber().isEmpty()
                && Objects.nonNull(paymentApiRequest.getDob()) && !paymentApiRequest.getDob().isEmpty()
                && Objects.nonNull(paymentApiRequest.getTranNo()) && !paymentApiRequest.getTranNo().isEmpty()
                && Objects.nonNull(paymentApiRequest.getAddress()) && !paymentApiRequest.getAddress().isEmpty()
                && Objects.nonNull(paymentApiRequest.getCity()) && !paymentApiRequest.getCity().isEmpty()
                //&& Objects.nonNull(paymentApiRequest.getZipCode()) && !paymentApiRequest.getZipCode().isEmpty()
                && Objects.nonNull(paymentApiRequest.getMobileNo()) && !paymentApiRequest.getMobileNo().isEmpty()
                && Objects.nonNull(paymentApiRequest.getPurposeOfTran()) && !paymentApiRequest.getPurposeOfTran().isEmpty()
                && Objects.nonNull(paymentApiRequest.getRelationWithRemitter()) && !paymentApiRequest.getRelationWithRemitter().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNullOrEmpty(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static PaymentApiResponse unauthorized(PaymentApiResponse paymentApiResponse){
        paymentApiResponse.setPayoutStatus(null);
        paymentApiResponse.setErrorMessage("Unauthorized access");
        paymentApiResponse.setApiStatus(Constants.API_STATUS_INVALID);
        return paymentApiResponse;
    }

}
