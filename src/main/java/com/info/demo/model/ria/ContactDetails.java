package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4141400575802562115L;
	
	@JsonProperty("CustPhoneCountryCode")
	private String custPhoneCountryCode;
	
	@JsonProperty("CustPhoneNo")
	private String custPhoneNo;
	
	@JsonProperty("CustCellNo")
	private String custCellNo;

	public String getCustPhoneCountryCode() {
		return custPhoneCountryCode;
	}

	public void setCustPhoneCountryCode(String custPhoneCountryCode) {
		this.custPhoneCountryCode = custPhoneCountryCode;
	}

	public String getCustPhoneNo() {
		return custPhoneNo;
	}

	public void setCustPhoneNo(String custPhoneNo) {
		this.custPhoneNo = custPhoneNo;
	}

	public String getCustCellNo() {
		return custCellNo;
	}

	public void setCustCellNo(String custCellNo) {
		this.custCellNo = custCellNo;
	}
	

}
