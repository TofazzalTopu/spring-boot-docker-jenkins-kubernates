package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BankDeposit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -541758029006420452L;
	
	@JsonProperty("BankID")
	private String bankID;
	
	@JsonProperty("BankCode")
	private String bankCode;
	
    @JsonProperty("BankName")
	private String bankName;
	
	@JsonProperty("BankBranchName")
	private String bankBranchName;
	
	@JsonProperty("BankBranchNo")
	private String bankBranchNo;
	
	@JsonProperty("BankBranchCity")
	private String bankBranchCity;
	
	@JsonProperty("BankBranchAddress")
	private String bankBranchAddress;

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchNo() {
		return bankBranchNo;
	}

	public void setBankBranchNo(String bankBranchNo) {
		this.bankBranchNo = bankBranchNo;
	}

	public String getBankBranchCity() {
		return bankBranchCity;
	}

	public void setBankBranchCity(String bankBranchCity) {
		this.bankBranchCity = bankBranchCity;
	}

	public String getBankBranchAddress() {
		return bankBranchAddress;
	}

	public void setBankBranchAddress(String bankBranchAddress) {
		this.bankBranchAddress = bankBranchAddress;
	}
	

}
