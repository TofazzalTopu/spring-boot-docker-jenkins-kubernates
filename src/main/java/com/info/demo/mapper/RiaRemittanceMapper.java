package com.info.demo.mapper;

import com.info.demo.constants.RemittanceDataStatus;
import com.info.demo.entity.ApiTrace;
import com.info.demo.entity.Branch;
import com.info.demo.entity.MbkBrn;
import com.info.demo.entity.RemittanceData;
import com.info.demo.model.ria.DownloadableOrderDTO;
import com.info.demo.model.ria.DownloadableResponse;
import com.info.demo.repository.CommonRepository;
import com.info.demo.service.common.BranchService;
import com.info.demo.service.common.MbkBrnService;
import com.info.demo.service.common.RemittanceDataService;
import com.info.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RiaRemittanceMapper {
    private static final Logger logger = LoggerFactory.getLogger(RiaRemittanceMapper.class);

    @Value("#{${bank.code}}")
    private String bankCode;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private BranchService branchService;

    @Autowired
    private MbkBrnService mbkBrnService;

    @Autowired
    private RemittanceDataService remittanceDataService;

    public List<RemittanceData> preparingRemittanceDataFromResponse(ApiTrace trace, String exchangeCode, DownloadableResponse downloadableResponse, String bankCode) {
        logger.info("Enter into preparingRemittanceDataFromResponse, traceId = " + trace.getId());
        List<RemittanceData> list = new ArrayList<>();

        try {
            List<Branch> branchList = branchService.findAll();
            List<MbkBrn> mbkBrnList = mbkBrnService.findAll();
            DateFormat dt = new SimpleDateFormat("yyyyMMdd");
            List<String> newReferences = downloadableResponse.getOrder().stream().map(o -> o.getTransaction().getOrderNo()).collect(Collectors.toList());
            List<String> existingReferences = remittanceDataService.findAllByExchangeCodeAndReferenceNumbers(exchangeCode, newReferences);

            for (DownloadableOrderDTO r : downloadableResponse.getOrder()) {
                RemittanceData rem = new RemittanceData();
                if (Objects.nonNull(r.getTransaction()) && existingReferences.contains(r.getTransaction().getOrderNo())) {
                    rem.setDuplicate(true);
                }

                rem.setAmount(new BigDecimal(Objects.nonNull(r.getQuotation()) && !r.getQuotation().getPaymentAmount().isEmpty() ? r.getQuotation().getPaymentAmount() : r.getQuotation().getBeneAmount()));

                if (Objects.nonNull(r.getBeneficiary())) {

                    if (Objects.nonNull(r.getBeneficiary().getResidence())) {
                        rem.setCityDistrict(r.getBeneficiary().getResidence().getBeneCity());
                    }

                    if (Objects.nonNull(r.getBeneficiary().getBankAccount())) {
                        rem.setCreditorAccountNo(r.getBeneficiary().getBankAccount().getBankAccountNo());
                    }

                    String recieverName = "";
                    if (Objects.nonNull(r.getBeneficiary().getBenePersonalInfo())) {

                        if (Objects.nonNull(r.getBeneficiary().getBenePersonalInfo().getBeneFirstName()))
                            recieverName = r.getBeneficiary().getBenePersonalInfo().getBeneFirstName() + " ";

                        if (Objects.nonNull(r.getBeneficiary().getBenePersonalInfo().getBeneMiddleName()))
                            recieverName += r.getBeneficiary().getBenePersonalInfo().getBeneMiddleName() + " ";

                        if (Objects.nonNull(r.getBeneficiary().getBenePersonalInfo().getBeneLastName()))
                            recieverName += r.getBeneficiary().getBenePersonalInfo().getBeneLastName() + " ";

                    }
                    rem.setCreditorName(recieverName);

                    if (Objects.nonNull(r.getBeneficiary().getBeneContactDetails())) {
                        rem.setPhoneNo(r.getBeneficiary().getBeneContactDetails().getBenePhoneNo());
                    }

                    if (Objects.nonNull(r.getBeneficiary().getResidence())) {
                        rem.setReceiverAddress(r.getBeneficiary().getResidence().getBeneAddress());
                    }
                }

                rem.setExchangeCode(exchangeCode);
                rem.setExchangeName(Constants.RIA_EXCHANGE_HOUSE);

                if (Objects.nonNull(r.getTransaction())) {
                    rem.setExchangeTransactionDate(r.getTransaction().getOrderDate());
                    rem.setExchangeTransactionNo(r.getTransaction().getOrderNo());
                    rem.setCountryOriginate(r.getTransaction().getCountryFrom());
                    rem.setPurpose(r.getTransaction().getTransferReason());
                    rem.setReferenceDate(dt.parse(r.getTransaction().getOrderDate()));
                    rem.setReferenceNo(r.getTransaction().getOrderNo());
                    rem.setSecurityCode(r.getTransaction().getPin());
                }

                if (Objects.nonNull(r.getCustomer())) {
                    if (Objects.nonNull(r.getCustomer().getContactDetails())) {
                        rem.setSenderPhone(r.getCustomer().getContactDetails().getCustPhoneNo());
                    }
                    if (Objects.nonNull(r.getCustomer().getIdentityDocument())) {
                        rem.setIdNo(r.getCustomer().getIdentityDocument().getCustID1No());
                        rem.setSenderIdType(r.getCustomer().getIdentityDocument().getCustID1Type());
                    }

                    String senderName = "";
                    if (Objects.nonNull(r.getCustomer().getPersonalInfo())) {
                        senderName = r.getCustomer().getPersonalInfo().getCustFirstName() + " ";

                        if (Objects.nonNull(r.getCustomer().getPersonalInfo().getCustMiddleName())) {
                            senderName += r.getCustomer().getPersonalInfo().getCustMiddleName() + " ";
                        }

                        if (Objects.nonNull(r.getCustomer().getPersonalInfo().getCustLastName())) {
                            senderName += r.getCustomer().getPersonalInfo().getCustLastName();
                        }
                    }
                    rem.setSenderName(senderName);

                    if (Objects.nonNull(r.getCustomer().getPersonalInfo())) {
                        rem.setSenderOccupation(r.getCustomer().getPersonalInfo().getCustOccupation());
                    }
                }

                if (Objects.nonNull(r.getQuotation())) {
                    rem.setCurrencyOriginate(r.getQuotation().getCustChargeCurrency());
                }

                String routingNumber = "";
                if (Objects.nonNull(r.getPayoutPartner()) && Objects.nonNull(r.getPayoutPartner().getBankDeposit())) {
                    rem.setBankName(r.getPayoutPartner().getBankDeposit().getBankName());
                    routingNumber = r.getPayoutPartner().getBankDeposit().getBankBranchNo();
                    rem.setBranchName(r.getPayoutPartner().getBankDeposit().getBankBranchName());
                }
                rem.setBranchRoutingNumber(routingNumber);
                if (!rem.isDuplicate()) {
                    rem.setProcessStatus(RemittanceDataStatus.OPEN);

                    Predicate<String> isValid = routing -> Objects.nonNull(routing) && routing.length() > 2 && bankCode.equals(routing.substring(0, 3));
                    if (isValid.test(routingNumber)) {
                        rem.setRemittanceMessageType(RemittanceDataStatus.EFT);
                        rem.setBankCode(bankCode);
                        rem.setBranchCode(Integer.parseInt(routingNumber.substring(3)));
                        if (routingNumber.length() > 3)
                            rem.setOwnBranchCode(Integer.parseInt(routingNumber.substring(3)));
                    } else {
                        rem.setRemittanceMessageType(RemittanceDataStatus.BEFTN);
                    }

                    String digits = "\\d*";
                    Predicate<String> isInValid = routing -> Objects.isNull(routing) || routing.isEmpty() || !routing.matches(digits) || !isValidateRoutingNumber(routing)
                            || !isValidRouting(branchList, mbkBrnList, routing.substring(0, 3), routing.substring(3), rem.getRemittanceMessageType());
                    if (isInValid.test(routingNumber)) {
                        rem.setProcessStatus(RemittanceDataStatus.REJECTED);
                        rem.setStopPayReason(Constants.WRONG_ROUTING_OR_WRONG_BRANCH);
                    }
                    if (!rem.getProcessStatus().equals(RemittanceDataStatus.REJECTED)) {
                        String characters = "^[a-zA-Z.-]+$";
                        String creditorName = rem.getCreditorName().replace(" ", "");
                        Predicate<String> eftAccountNumberValid = accountNumber -> !accountNumber.matches(digits) && rem.getRemittanceMessageType().equals(RemittanceDataStatus.EFT) && accountNumber.length() != 13;
                        Predicate<String> beftnAccountNumberValid = accountNumber -> !accountNumber.matches(digits) && rem.getRemittanceMessageType().equals(RemittanceDataStatus.BEFTN) && accountNumber.length() > 17;

                        if (eftAccountNumberValid.or(beftnAccountNumberValid).test(rem.getCreditorAccountNo())) {
                            rem.setProcessStatus(RemittanceDataStatus.REJECTED);
                            rem.setStopPayReason(Constants.ACCOUNT_NUMBER_INVALID);
                        } else if (Objects.isNull(rem.getCreditorName()) || rem.getCreditorName().isEmpty() || !creditorName.matches(characters)) {
                            rem.setProcessStatus(RemittanceDataStatus.REJECTED);
                            rem.setStopPayReason(Constants.ACCOUNT_NAME_IS_EMPTY);
                        }
                    }
                }

                rem.setProcessDate(trace.getCbsDate());
                rem.setMiddlewareId(trace.getId());
                rem.setMiddlewarePush(Constants.MIDDLEWARE_PUSH_UNDONE);
                rem.setSourceType(Constants.API_SOURCE_TYPE);
                list.add(rem);
            }
        } catch (Exception e) {
            logger.error("Error in preparingRemittanceDataFromResponse for TraceID: " + trace.getId(), e);
        }
        return list;
    }

    private boolean isValidateRoutingNumber(String routingNumber) {
        boolean isValid = false;
        try {
            if (Objects.isNull(routingNumber) || routingNumber.length() <= 3) {
                isValid = false;
            } else if (routingNumber.length() == 9) {
                isValid = true;
            }
            Integer.parseInt(routingNumber);
            isValid = true;
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    public String mapStopReason(String stopPayReason, String finalStatus, String accountNumber, String remittanceMessageType) {
        if (stopPayReason.toLowerCase().contains("account")) {
            if (Objects.nonNull(finalStatus) && (finalStatus.equals("04") || finalStatus.equals("05"))) {
                stopPayReason = Constants.BENEFICIARY_ACCOUNT_IS_NOT_ACTIVE_OR_CLOSED;
            } else if (Objects.nonNull(remittanceMessageType) && remittanceMessageType.equals("BEFTN") && stopPayReason.toLowerCase().contains("invalid")) {
                stopPayReason = Constants.BENEFICIARY_ACCOUNT_IS_NOT_CORRECT;
            } else if (stopPayReason.toLowerCase().contains("invalid") && accountNumber.length() != 13) {
                stopPayReason = Constants.BENEFICIARY_ACCOUNT_IS_NOT_CORRECT;
            } else if (stopPayReason.toLowerCase().contains("name") && stopPayReason.toLowerCase().contains("empty")) {
                stopPayReason = Constants.ACCOUNT_NAME_IS_EMPTY_OR_NOT_VALID;
            } else {
                stopPayReason = Constants.BENEFICIARY_NAME_DOES_NOT_MATCH;
            }
        } else if (stopPayReason.toLowerCase().contains("branch") || stopPayReason.toLowerCase().contains("wrong")) {
            stopPayReason = Constants.INCORRECT_ROUTING_OR_WRONG_BRANCH;
        } else {
            stopPayReason = Constants.ORDER_CAN_NOT_BE_PAID + ", due to " + stopPayReason.toLowerCase();
        }
        return stopPayReason;
    }

    public boolean isMbkBrnBranchRoutingExist(List<MbkBrn> mbkBrnList, String bankCode, String routingNumber) {
        Predicate<MbkBrn> isSelectedBranch = mbkBrn -> mbkBrn.getMbkbrnKey().getBankCode().equals(bankCode) && mbkBrn.getMbkbrnKey().getBranchRouting().equals(routingNumber);
        return mbkBrnList.stream().filter(isSelectedBranch).count() == 1;
    }

    public boolean isBranchRoutingExist(List<Branch> branchList, String routingNumber) {
        Predicate<Branch> isSelectedBranch = branch -> branch.getRoutingNumber().equals(Integer.parseInt(routingNumber));
        return branchList.stream().filter(isSelectedBranch).count() == 1;
    }


    public boolean isValidRouting(List<Branch> branchList, List<MbkBrn> mbkBrnList, String bankCode, String routingNumber, String messageType) {
        if (messageType.equals(RemittanceDataStatus.EFT) && isBranchRoutingExist(branchList, routingNumber)) return true;
        if (messageType.equals(RemittanceDataStatus.BEFTN) && isMbkBrnBranchRoutingExist(mbkBrnList, bankCode, routingNumber))
            return true;
        return false;
    }


}
