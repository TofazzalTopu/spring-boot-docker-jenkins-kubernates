package com.info.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "IC_CASH_REMITTANCE_DATA")
public class ICCashRemittanceData implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String INVALID = "INVALID";
	public static final String VALID = "VALID";
	public static final String POSTED = "POSTED";
	public static final String COMPLETED = "COMPLETED";
	public static final String OPEN = "OPEN";
	public static final String UNLOCK = "UNLOCK";
	public static final String UNLOCK_REQUESTED = "UNLOCK_REQUESTED";
	public static final String UNLOCK_NOTIFIED = "UNLOCK_NOTIFIED";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CASH_REMITTANCE_DATA_GEN")
	@SequenceGenerator(name = "CASH_REMITTANCE_DATA_GEN", sequenceName = "CASH_REMITTANCE_DATA_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "REMITTANCE_MSG_TYPE ")
	private String remittanceMessageType;

	@Column(name = "BANK_CODE")
	private String bankCode;

	@Column(name = "BANK_NAME")
	private String bankName;

	@Column(name = "BRANCH_ROUNTING_NO")
	private String branchRoutingNumber;

	@Column(name = "BRANCH_NAME")
	private String branchName;

	@Column(name = "CREDITOR_ACCOUNT_NO")
	private String creditorAccountNo;

	@Column(name = "CREDITOR_NAME")
	private String creditorName;

	@Column(name = "PROCESS_DATE")
	@Temporal(TemporalType.DATE)
	private Date processDate;

	@Column(name = "REFERENCE_NO")
	private String referenceNo;

	@Column(name = "REFERENCE_DATE")
	@Temporal(TemporalType.DATE)
	private Date referenceDate;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "EXCHANGE_HOUSE_CODE")
	private String exchangeCode;

	@Column(name = "EXCHANGE_HOUSE_NAME")
	private String exchangeName;

	@Column(name = "SECURITY_CODE")
	private String securityCode;

	@Column(name = "ID_NO")
	private String idNo;

	@Column(name = "SENDER_ID_TYPE")
	private String senderIdType;

	@Column(name = "PHONE_NO")
	private String phoneNo;

	@Column(name = "COUNTRY_ORIGINATE")
	private String countryOriginate;

	@Column(name = "SENDER_PHONE")
	private String senderPhone;

	@Column(name = "SENDER_NAME")
	private String senderName;

	@Column(name = "SENDER_ADDRESS")
	private String senderAddress;

	@Column(name = "RECIEVER_CITY_DISTRICT")
	private String cityDistrict;

	@Column(name = "CURRENCY_ORIGINATE")
	private String currencyOriginate;

	@Column(name = "AMOUNT_ORIGINATE")
	private BigDecimal amountOriginate;

	@Column(name = "EX_TRANSACTION_NO")
	private String exchangeTransactionNo;

	@Column(name = "RECEIVER_ADDRESS")
	private String receiverAddress;

	@Column(name = "EX_TRANSACTION_DATE")
	private String exchangeTransactionDate;

	@Column(name = "PURPOSE")
	private String purpose;

	@Column(name = "PAID_BRANCH")
	private String paidBranch;

	@Column(name = "PAID_BY")
	private String paidBy;

	@Column(name = "CHARGES")
	private BigDecimal charges;

	@Column(name = "SOURCE_TYPE")
	private String sourceType;

	@Column(name = "SENDER_DOC")
	private String senderDoc;

	@Column(name = "API_TRY_COUNT")
	private long tryCount;

	@Column(name = "API_RESPONSE")
	private String apiResponse;

	@ManyToOne
	@JoinColumn(name = "REMITTANCE_PROCESS_ID")
	private RemittanceProcessMaster remittanceProcessMaster;

	@Column(name = "ENTEREDBY")
	private String enteredBy;

	@Column(name = "AUTHORIZE_BY_USER")
	private String authorizedBy;

	@Column(name = "TRANSACTION_BATCH")
	private Integer transactionBatchNo;

	@Column(name = "TRANSACTION_BRANCH")
	private Integer transactionBranch;

	@Column(name = "TRANSACTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	@Column(name = "TRANSACTION_BY_USER")
	private String transactionBy;

	@Column(name = "PROCCESS_STATUS")
	private String processStatus;

	@Column(name = "REASON_FOR_INVALID")
	private String reasonForInvalid;

	@Column(name = "OWN_BRANCH_CODE")
	private Integer ownBranchCode;

	@Column(name = "ORG_BANK_CODE")
	private String orgBankCode;

	@Column(name = "ORG_ROUNTING_NO")
	private String orgRoutingNo;

	@Column(name = "ORG_SECURITY_CODE")
	private String orgSecurityCode;

	@Column(name = "MODIFIED_USER")
	private String modifiedUser;

	@Column(name = "EFT_AUTOCREDIT")
	private String eftAutoCredit;

	@Column(name = "FINAL_STATUS")
	private String finalStatus;

	@Column(name = "MIDDLE_WARE_PUSH")
	private Integer middlewarePush;

	@Column(name = "MIDDLE_WARE_ID")
	private Long middlewareId;

	@Column(name = "BRN_PAYMENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date brnPaymentDate;

	@Column(name = "EXCHANGE_RATE")
	private BigDecimal exchangeRate;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "STOP_PAYREASON")
	private String stopPayReason;

	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	@Column(name = "BRANCH_CODE")
	private Integer branchCode;

	@Column(name = "RETURNED_MSG")
	private Integer returnedMsg;

	@Column(name = "RETURNED_DATE")
	@Temporal(TemporalType.DATE)
	private Date returnedDate;

	@Column(name = "RETURNED_BY")
	private String returnedBy;

	@Column(name = "CURRENT_STATE")
	private String currentState;

	@Column(name = "FC_AMOUNT")
	private BigDecimal fcAmount;

	@Column(name = "INCENTIVE_AMOUNT")
	private BigDecimal incentiveAmount;

	@Column(name = "INCENTIVE_GIVEN")
	private String incentiveGiven;

	@Column(name = "SENDER_GENDER")
	private String senderGender;

	@Column(name = "RECEIVER_GENDER")
	private String receiverGender;

	@Column(name = "SENDER_OCCUPATION")
	private String senderOccupation;

	@Column(name = "RECEIVER_OCCUPATION")
	private String receiverOccupation;

	@Transient
	private boolean selected;

	@Transient
	private boolean eligibleForReturnOrCredit;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ICCashRemittanceData that = (ICCashRemittanceData) o;
		return Objects.equals(bankCode, that.bankCode) && Objects.equals(processDate, that.processDate) && Objects.equals(referenceNo, that.referenceNo) && Objects.equals(amount, that.amount) && Objects.equals(exchangeCode, that.exchangeCode);
	}

	public ICCashRemittanceData(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankCode, processDate, referenceNo, amount, exchangeCode);
	}


}