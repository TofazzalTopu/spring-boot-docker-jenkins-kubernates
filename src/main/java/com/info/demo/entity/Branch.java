package com.info.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MBRN")
public class Branch implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected BranchKey branchKey;
	@Column(name = "MBRN_NAME", length = 50)
	private String name;
	@Column(name = "MBRN_ADDR1", length = 35)
	private String address1;
	@Column(name = "MBRN_ADDR2", length = 35)
	private String address2;
	@Column(name = "MBRN_ADDR3", length = 35)
	private String address3;
	@Column(name = "MBRN_ADDR4", length = 35)
	private String address4;
	@Column(name = "MBRN_ADDR5", length = 35)
	private String address5;
	@Column(name = "MBRN_LOCN_CODE", length = 6)
	private String locationCode;
	@Column(name = "MBRN_OPENED_ON_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openDate;
	@Column(name = "MBRN_ADMIN_UNIT_TYPE", length = 6)
	private String adminUnitType;
	@Column(name = "MBRN_PARENT_ADMIN_CODE")
	private Integer parentAdminCode;
	@Column(name = "MBRN_NUM_EXTN_CTR")
	private Short numberOfExtensionCounter;
	@Column(name = "MBRN_CATEGORY", length = 2)
	private String category;
	@Column(name = "MBRN_SIZE", length = 2)
	private String size;
	@Column(name = "MBRN_AUTH_DEAL_FOREX")
	private Character authorizedForexDealer;
	@Column(name = "MBRN_AUTH_FOR_FCDEP")
	private Character authorizedForForeignCurrencyDeposit;
	@Column(name = "MBRN_MICR_CODE")
	private Integer micrCode;
	@Column(name = "MBRN_MAINBRN_IN_LOCN")
	private Character mainBranchInLocation;
	@Column(name = "MBRN_LINK_SERV_MAIN_BRN")
	private Integer linkedServiceMainBranch;
	@Column(name = "MBRN_DD_ISSUE_ALLOWED")
	private Character ddIssueAllowed;
	@Column(name = "MBRN_TT_ISSUE_ALLOWED")
	private Character ttIssueAllowed;
	@Column(name = "MBRN_SPLIT_PREMISES")
	private Character splitPremises;
	@Column(name = "MBRN_NUM_SPLIT_PREMISES")
	private Short numberOfSplitPremises;
	@Column(name = "MBRN_LOCAL_CLG_MEMBER")
	private Character localClearingMember;
	@Column(name = "MBRN_NATNL_CLG_MEMBER")
	private Character nationalClearingMember;
	@Column(name = "MBRN_HIGH_VAL_CLG_MEMBER")
	private Character highValueClearingMember;
	@Column(name = "MBRN_NUM_OFFICERS_AVL")
	private Short numberOfOfficersAvailable;
	@Column(name = "MBRN_IFSC_CODE", length = 15)
	private String ifscCode;
	@Column(name = "MBRN_CLG_BASED_ON_MICR")
	private Character clearingBasedOnMicr;
	@Column(name = "MBRN_CASH_MGMT_BRANCH")
	private Character cashManagementBranch;
	@Column(name = "MBRN_RTGS_DEP_ENABLED")
	private Character realTimeGrossSettlementDepositEnabled;
	@Column(name = "MBRN_BASE_CURR_CODE", length = 3)
	private String baseCurrencyCode;
	@Column(name = "MBRN_TEL_NO1", length = 15)
	private String telephoneNumber1;
	@Column(name = "MBRN_TEL_NO2", length = 15)
	private String telephoneNumber2;
	@Column(name = "MBRN_TEL_NO3", length = 15)
	private String telephoneNumber3;
	@Column(name = "MBRN_TEL_NO4", length = 15)
	private String telephoneNumber4;
	@Column(name = "MBRN_TEL_NO5", length = 15)
	private String telephoneNumber5;
	@Column(name = "MBRN_TEL_NO6", length = 15)
	private String telephoneNumber6;
	@Column(name = "MBRN_FAX_NO1", length = 15)
	private String faxNumber1;
	@Column(name = "MBRN_FAX_NO2", length = 15)
	private String faxNumber2;
	@Column(name = "MBRN_FAX_NO3", length = 15)
	private String faxNumber3;
	@Column(name = "MBRN_FAX_NO4", length = 15)
	private String faxNumber4;
	@Column(name = "MBRN_FAX_NO5", length = 15)
	private String faxNumber5;
	@Column(name = "MBRN_FAX_NO6", length = 15)
	private String faxNumber6;
	@Column(name = "MBRN_EMAIL_ADDR1", length = 50)
	private String emailAddress1;
	@Column(name = "MBRN_EMAIL_ADDR2", length = 50)
	private String emailAddress2;
	@Column(name = "MBRN_SWIFT_BIC_CODE", length = 12)
	private String swiftBicCode;
	@Column(name = "MBRN_WEEK_HOL_MON")
	private Character weeklyHolidayOnMonday;
	@Column(name = "MBRN_WEEK_HOL_TUE")
	private Character weeklyHolidayOnTuesday;
	@Column(name = "MBRN_WEEK_HOL_WED")
	private Character weeklyHolidayOnWednesday;
	@Column(name = "MBRN_WEEK_HOL_THU")
	private Character weeklyHolidayOnThursday;
	@Column(name = "MBRN_WEEK_HOL_FRI")
	private Character weeklyHolidayOnFriday;
	@Column(name = "MBRN_WEEK_HOL_SAT")
	private Character weeklyHolidayOnSaturday;
	@Column(name = "MBRN_WEEK_HOL_SUN")
	private Character weeklyHolidayOnSunday;
	@Column(name = "MBRN_HALF_WORK_MON")
	private Character halfWorkingDayOnMonday;
	@Column(name = "MBRN_HALF_WORK_TUE")
	private Character halfWorkingDayOnTuesday;
	@Column(name = "MBRN_HALF_WORK_WED")
	private Character halfWorkingDayOnWednesday;
	@Column(name = "MBRN_HALF_WORK_THU")
	private Character halfWorkingDayOnThursday;
	@Column(name = "MBRN_HALF_WORK_FRI")
	private Character halfWorkingDayOnFriday;
	@Column(name = "MBRN_HALF_WORK_SAT")
	private Character halfWorkingDayOnSaturday;
	@Column(name = "MBRN_HALF_WORK_SUN")
	private Character halfWorkingDayOnSunday;
	@Column(name = "MBRN_AUTH_DLR_CODE", length = 20)
	private String authorizedDealerCode;
	@Column(name = "MBRN_BSR_CODE", length = 7)
	private String bsrCode;
	@Column(name = "MBRN_STATUS_FLG")
	private Character statusFlag;
	@Column(name = "MBRN_CLOSURE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date closureDate;
	@Basic(optional = false)
	@Column(name = "MBRN_ENTD_BY", nullable = false, length = 8)
	private String enteredBy;
	@Basic(optional = false)
	@Column(name = "MBRN_ENTD_ON", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date enteredOn;
	@Column(name = "MBRN_LAST_MOD_BY", length = 8)
	private String lastModifiedBy;
	@Column(name = "MBRN_LAST_MOD_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedOn;
	@Column(name = "MBRN_AUTH_BY", length = 8)
	private String authorizedBy;
	@Column(name = "MBRN_AUTH_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date authorizedOn;
	@Column(name = "TBA_MAIN_KEY", length = 25)
	private String tbaMainKey;
	@Column(name = "MBRN_CRMS", length = 2)
	private String crmsCode;

}
