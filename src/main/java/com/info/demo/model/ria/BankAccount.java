package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6848850045183717356L;
   
	
	@JsonProperty("BankAccountCountry")
	private String bankAccountCountry;
	
	@JsonProperty("BankAccountType")
	private String bankAccountType;
	
    @JsonProperty("BankAccountNo")
	private String bankAccountNo;
	
	@JsonProperty("Valuetype")
	private String valuetype;
	
	@JsonProperty("BankRoutingCode")
	private String bankRoutingCode;
	
	@JsonProperty("BankRoutingType")
	private String bankRoutingType;
	
    @JsonProperty("BIC_SWIFT")
	private String bicSWIFT;
	
	@JsonProperty("UnitaryBankAccountNo")
	private String unitaryBankAccountNo;
	
	@JsonProperty("UnitaryType")
	private String unitaryType;

	public String getBankAccountCountry() {
		return bankAccountCountry;
	}

	public void setBankAccountCountry(String bankAccountCountry) {
		this.bankAccountCountry = bankAccountCountry;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getValuetype() {
		return valuetype;
	}

	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}

	public String getBankRoutingCode() {
		return bankRoutingCode;
	}

	public void setBankRoutingCode(String bankRoutingCode) {
		this.bankRoutingCode = bankRoutingCode;
	}

	public String getBankRoutingType() {
		return bankRoutingType;
	}

	public void setBankRoutingType(String bankRoutingType) {
		this.bankRoutingType = bankRoutingType;
	}

	public String getBicSWIFT() {
		return bicSWIFT;
	}

	public void setBicSWIFT(String bicSWIFT) {
		this.bicSWIFT = bicSWIFT;
	}

	public String getUnitaryBankAccountNo() {
		return unitaryBankAccountNo;
	}

	public void setUnitaryBankAccountNo(String unitaryBankAccountNo) {
		this.unitaryBankAccountNo = unitaryBankAccountNo;
	}

	public String getUnitaryType() {
		return unitaryType;
	}

	public void setUnitaryType(String unitaryType) {
		this.unitaryType = unitaryType;
	}
	
	
	
}
