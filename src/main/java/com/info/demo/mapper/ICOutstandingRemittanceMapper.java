package com.info.demo.mapper;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.instantCash.ICOutstandingTransactionDTO;
import com.info.demo.service.common.CommonService;
import com.info.demo.util.Constants;
import com.info.demo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

@Component
public class ICOutstandingRemittanceMapper {

    public static final Logger logger = LoggerFactory.getLogger(ICOutstandingRemittanceMapper.class);

    @Autowired
    private CommonService commonService;

    @Value("#{${bank.code}}")
    private String bankCode;


    public RemittanceData prepareRemittanceData(ICOutstandingTransactionDTO icOutstandingDTO, String exchangeCode, ApiTrace trace) {
        RemittanceData remittanceData = new RemittanceData();
        try {
            DateFormat dt = new SimpleDateFormat("yyyyMMdd");
            remittanceData.setAmount(Objects.nonNull(icOutstandingDTO.getPayingAmount()) ? icOutstandingDTO.getPayingAmount() : icOutstandingDTO.getSettlementAmount());
            remittanceData.setAmountOriginate(icOutstandingDTO.getSettlementAmount());
            remittanceData.setPaidBy(icOutstandingDTO.getPayingAgentName());
            remittanceData.setCharges(icOutstandingDTO.getCommissionAmount());
            remittanceData.setPaidBranch(icOutstandingDTO.getMessagePayeeBranch());

            mapBeneficiaryInfo(remittanceData, icOutstandingDTO);

            remittanceData.setExchangeCode(exchangeCode);
            remittanceData.setExchangeName(Constants.EXCHANGE_HOUSE_INSTANT_CASH);
            remittanceData.setExchangeTransactionDate(icOutstandingDTO.getSentAt());
            remittanceData.setExchangeTransactionNo(icOutstandingDTO.getPartnerReference());
            remittanceData.setCountryOriginate(icOutstandingDTO.getOriginatingCountry());
            remittanceData.setPurpose(icOutstandingDTO.getRemittancePurpose());
            remittanceData.setReferenceDate(dt.parse(icOutstandingDTO.getSentAt()));
            remittanceData.setReferenceNo(icOutstandingDTO.getReference());
            remittanceData.setSecurityCode(icOutstandingDTO.getPartnerReference()); // data not match
            remittanceData.setCurrencyOriginate(icOutstandingDTO.getPayingCurrency());
            remittanceData.setExchangeRate(icOutstandingDTO.getSettlementRate());

            mapRemitterInfo(remittanceData, icOutstandingDTO);
            mapBankBranchInfo(remittanceData, icOutstandingDTO);

            remittanceData.setProcessStatus(RemittanceDataStatus.OPEN);
            remittanceData.setProcessDate(trace.getCbsDate());
            remittanceData.setMiddlewareId(trace.getId());
            remittanceData.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_UNDONE);
            remittanceData.setSourceType(Constants.API_SOURCE_TYPE);
        } catch (Exception e) {
            logger.error("Error in prepareRemittanceData for TraceID: " + trace.getId(), e);
        }
        return remittanceData;
    }

    private void mapBeneficiaryInfo(RemittanceData remittanceData, ICOutstandingTransactionDTO icOutstandingDTO) {
        try {
            if (Objects.nonNull(icOutstandingDTO.getBeneficiary())) {
                String beneficiaryFirstName = Objects.nonNull(icOutstandingDTO.getBeneficiary().getFirstName()) ? icOutstandingDTO.getBeneficiary().getFirstName() : "";
                String beneficiaryMiddleName = Objects.nonNull(icOutstandingDTO.getBeneficiary().getMiddleName()) ? icOutstandingDTO.getBeneficiary().getMiddleName() : "";
                String beneficiaryLastName = Objects.nonNull(icOutstandingDTO.getBeneficiary().getLastName()) ? icOutstandingDTO.getBeneficiary().getLastName() : "";

                remittanceData.setCreditorName(beneficiaryFirstName + " " + beneficiaryMiddleName + " " + beneficiaryLastName);
                String beneficiaryPhoneNo = Objects.nonNull(icOutstandingDTO.getBeneficiary().getPhoneNumber()) ? icOutstandingDTO.getBeneficiary().getPhoneNumber() : icOutstandingDTO.getBeneficiary().getMobileNumber();
                remittanceData.setPhoneNo(beneficiaryPhoneNo);

                if (Objects.nonNull(icOutstandingDTO.getBeneficiary().getAddress())) {
                    String city = Objects.nonNull(icOutstandingDTO.getBeneficiary().getAddress().getCity()) ? icOutstandingDTO.getBeneficiary().getAddress().getCity() : "";
                    String district = Objects.nonNull(icOutstandingDTO.getBeneficiary().getAddress().getDistrict()) ? icOutstandingDTO.getBeneficiary().getAddress().getDistrict() : "";
                    remittanceData.setCityDistrict(city + " " + district);
                    String address1 = Objects.nonNull(icOutstandingDTO.getBeneficiary().getAddress().getAddressLine1()) ? icOutstandingDTO.getBeneficiary().getAddress().getAddressLine1() : "";
                    String address2 = Objects.nonNull(icOutstandingDTO.getBeneficiary().getAddress().getAddressLine2()) ? icOutstandingDTO.getBeneficiary().getAddress().getAddressLine2() : "";
                    remittanceData.setReceiverAddress(address1 + " " + address2);
                }
            }
        } catch (Exception e) {
            logger.error("Error in mapBeneficiaryInfo()", e);
        }
    }

    private void mapRemitterInfo(RemittanceData remittanceData, ICOutstandingTransactionDTO icOutstandingDTO) {
        if (Objects.nonNull(icOutstandingDTO.getRemitter())) {
            remittanceData.setSenderPhone(Objects.nonNull(icOutstandingDTO.getRemitter().getPhoneNumber()) ? icOutstandingDTO.getRemitter().getPhoneNumber() : icOutstandingDTO.getRemitter().getMobileNumber());
            String senderFirstName = Objects.nonNull(icOutstandingDTO.getRemitter().getFirstName()) ? icOutstandingDTO.getRemitter().getFirstName() : "";
            String senderMiddleName = Objects.nonNull(icOutstandingDTO.getRemitter().getMiddleName()) ? icOutstandingDTO.getRemitter().getMiddleName() : "";
            String senderLastName = Objects.nonNull(icOutstandingDTO.getRemitter().getLastName()) ? icOutstandingDTO.getRemitter().getLastName() : "";
            remittanceData.setSenderName(senderFirstName + " " + senderMiddleName + " " + senderLastName);

            if (Objects.nonNull(icOutstandingDTO.getRemitter().getAddress())) {
                String address1 = Objects.nonNull(icOutstandingDTO.getRemitter().getAddress().getAddressLine1()) ? icOutstandingDTO.getRemitter().getAddress().getAddressLine1() : "";
                String address2 = Objects.nonNull(icOutstandingDTO.getRemitter().getAddress().getAddressLine2()) ? icOutstandingDTO.getRemitter().getAddress().getAddressLine2() : "";
                remittanceData.setSenderAddress(address1 + " " + address2);
            }

            if (Objects.nonNull(icOutstandingDTO.getRemitter().getIdDetails())) {
                remittanceData.setIdNo(icOutstandingDTO.getRemitter().getIdDetails().getNumber());
                remittanceData.setSenderIdType(icOutstandingDTO.getRemitter().getIdDetails().getType());
            }
        }
    }

    // if the first 3 digits of the bankCode is 185 (RUPALI Bank Head Office) then the transaction type is EFT.
    private void mapBankBranchInfo(RemittanceData remittanceData, ICOutstandingTransactionDTO icOutstandingDTO) {
        try {
            boolean isBankDetailsNonEmpty = Objects.nonNull(icOutstandingDTO.getBeneficiary()) && Objects.nonNull(icOutstandingDTO.getBeneficiary().getBankDetails());
            if (isBankDetailsNonEmpty && isEFTRemittance(icOutstandingDTO.getBeneficiary().getBankDetails().getBankCode())) {
                remittanceData.setRemittanceMessageType("EFT");
                remittanceData.setBankCode(bankCode);
                // For 13 digit account number replacing the branch information with account branch
                if (Objects.nonNull(remittanceData.getCreditorAccountNo()) && remittanceData.getCreditorAccountNo().length() == 13) {
                    Map<String, String> accountBranchInfo = commonService.getAccountBranchInfo(remittanceData.getCreditorAccountNo());
                    remittanceData.setBranchCode(Integer.parseInt(accountBranchInfo.get("branch_code")));
                    remittanceData.setBranchName(accountBranchInfo.get("branch_name"));
                    remittanceData.setBranchRoutingNumber(bankCode + StringUtil.padLeftZeros(accountBranchInfo.get("routing_no"), 6));
                    remittanceData.setOwnBranchCode(Integer.parseInt(accountBranchInfo.get("branch_code")));
                }
            } else {
                remittanceData.setRemittanceMessageType("BEFTN");
                if (isBankDetailsNonEmpty) {
                    remittanceData.setBranchRoutingNumber(icOutstandingDTO.getBeneficiary().getBankDetails().getBankCode());

                    String address1 = Objects.nonNull(icOutstandingDTO.getBeneficiary().getBankDetails().getBankAddress1()) ? icOutstandingDTO.getBeneficiary().getBankDetails().getBankAddress1() : "";
                    String address2 = Objects.nonNull(icOutstandingDTO.getBeneficiary().getBankDetails().getBankAddress2()) ? icOutstandingDTO.getBeneficiary().getBankDetails().getBankAddress2() : "";
                    remittanceData.setBankName(icOutstandingDTO.getBeneficiary().getBankDetails().getBankName() + " " + address1 + " " + address2);
                    remittanceData.setBankCode(icOutstandingDTO.getBeneficiary().getBankDetails().getBankCode());
                    remittanceData.setBranchName(icOutstandingDTO.getBeneficiary().getBankDetails().getBankName() + " " + address1 + " " + address2);
                }
            }
        } catch (Exception e) {
            logger.error("Error in mapBankBranchInfo()", e);
        }
    }

    private boolean isEFTRemittance(String rmBankCode) {
        return Objects.nonNull(rmBankCode) && rmBankCode.trim().length() >= 3 && rmBankCode.trim().substring(0, 3).equals(bankCode);
    }


}
