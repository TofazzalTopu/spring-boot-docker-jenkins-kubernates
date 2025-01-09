package com.info.demo.model.ria;

import java.io.Serializable;

public class RmsApiRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5392947925067162370L;
	private String exchangeCode;
	private String pinNo;
	private String branchUserId;
	private String branchId;

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getBranchUserId() {
		return branchUserId;
	}

	public void setBranchUserId(String branchUserId) {
		this.branchUserId = branchUserId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
