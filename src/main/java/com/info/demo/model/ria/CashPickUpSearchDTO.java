package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6058488918930600759L;

	@JsonProperty("TransRefID")
	private String transRefID;

	@JsonProperty("OrderFound")
	private String orderFound;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("OrderNo")
	private String orderNo;

	@JsonProperty("SeqIDRA")
	private String seqIDRA;

	@JsonProperty("SeqIDPA")
	private String seqIDPA;

	@JsonProperty("OrderDate")
	private String orderDate;

	@JsonProperty("CountryFrom")
	private String countryFrom;

	@JsonProperty("CustNameFirst")
	private String custNameFirst;

	@JsonProperty("CustNameMiddle")
	private String custNameMiddle;

	@JsonProperty("CustNameLast1")
	private String custNameLast1;

	@JsonProperty("CustNameLast2")
	private String custNameLast2;

	@JsonProperty("CustAddress")
	private String custAddress;

	@JsonProperty("CustCity")
	private String custCity;

	@JsonProperty("CustState")
	private String custState;

	@JsonProperty("CustCountry")
	private String custCountry;

	@JsonProperty("CustZip")
	private String custZip;

	@JsonProperty("CustTelNo")
	private String custTelNo;

	@JsonProperty("CustBeneRelationship")
	private String custBeneRelationship;

	@JsonProperty("CustCurrency")
	private String custCurrency;

	@JsonProperty("CustAmount")
	private String custAmount;

	@JsonProperty("Rate")
	private String rate;

	@JsonProperty("BeneNameFirst")
	private String beneNameFirst;

	@JsonProperty("BeneNameMiddle")
	private String beneNameMiddle;

	@JsonProperty("BeneNameLast1")
	private String beneNameLast1;

	@JsonProperty("BeneNameLast2")
	private String beneNameLast2;

	@JsonProperty("BeneAddress")
	private String beneAddress;

	@JsonProperty("BeneCity")
	private String beneCity;

	@JsonProperty("BeneState")
	private String beneState;

	@JsonProperty("BeneCountry")
	private String beneCountry;

	@JsonProperty("BeneZip")
	private String beneZip;

	@JsonProperty("BeneTelNo")
	private String beneTelNo;

	@JsonProperty("BeneCurrency")
	private String beneCurrency;

	@JsonProperty("BeneAmount")
	private String beneAmount;

	@JsonProperty("PCRate")
	private String pCRate;

	@JsonProperty("TransferReason")
	private String transferReason;

	@JsonProperty("CustID1Type")
	private String custID1Type;

	@JsonProperty("CustID1No")
	private String custID1No;

	@JsonProperty("CustID1ExpirationDate")
	private String custID1ExpirationDate;

	@JsonProperty("CustID1IssuedBy")
	private String custID1IssuedBy;

	@JsonProperty("CustID1IssuedByCountry")
	private String custID1IssuedByCountry;

	@JsonProperty("CustCountryOfBirth")
	private String custCountryOfBirth;

	@JsonProperty("CustDateOfBirth")
	private String custDateOfBirth;

	@JsonProperty("custNationality")
	private String custNationality;

	@JsonProperty("ResponseDateTimeUTC")
	private String responseDateTimeUTC;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseText")
	private String responseText;
	
	@JsonProperty("RequiredFields")
	private RequiredField requiredField;

	public String getTransRefID() {
		return transRefID;
	}

	public void setTransRefID(String transRefID) {
		this.transRefID = transRefID;
	}

	public String getOrderFound() {
		return orderFound;
	}

	public void setOrderFound(String orderFound) {
		this.orderFound = orderFound;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSeqIDRA() {
		return seqIDRA;
	}

	public void setSeqIDRA(String seqIDRA) {
		this.seqIDRA = seqIDRA;
	}

	public String getSeqIDPA() {
		return seqIDPA;
	}

	public void setSeqIDPA(String seqIDPA) {
		this.seqIDPA = seqIDPA;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public String getCustNameFirst() {
		return custNameFirst;
	}

	public void setCustNameFirst(String custNameFirst) {
		this.custNameFirst = custNameFirst;
	}

	public String getCustNameMiddle() {
		return custNameMiddle;
	}

	public void setCustNameMiddle(String custNameMiddle) {
		this.custNameMiddle = custNameMiddle;
	}

	public String getCustNameLast1() {
		return custNameLast1;
	}

	public void setCustNameLast1(String custNameLast1) {
		this.custNameLast1 = custNameLast1;
	}

	public String getCustNameLast2() {
		return custNameLast2;
	}

	public void setCustNameLast2(String custNameLast2) {
		this.custNameLast2 = custNameLast2;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustCountry() {
		return custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustZip() {
		return custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustTelNo() {
		return custTelNo;
	}

	public void setCustTelNo(String custTelNo) {
		this.custTelNo = custTelNo;
	}

	public String getCustBeneRelationship() {
		return custBeneRelationship;
	}

	public void setCustBeneRelationship(String custBeneRelationship) {
		this.custBeneRelationship = custBeneRelationship;
	}

	public String getCustCurrency() {
		return custCurrency;
	}

	public void setCustCurrency(String custCurrency) {
		this.custCurrency = custCurrency;
	}

	public String getCustAmount() {
		return custAmount;
	}

	public void setCustAmount(String custAmount) {
		this.custAmount = custAmount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBeneNameFirst() {
		return beneNameFirst;
	}

	public void setBeneNameFirst(String beneNameFirst) {
		this.beneNameFirst = beneNameFirst;
	}

	public String getBeneNameMiddle() {
		return beneNameMiddle;
	}

	public void setBeneNameMiddle(String beneNameMiddle) {
		this.beneNameMiddle = beneNameMiddle;
	}

	public String getBeneNameLast1() {
		return beneNameLast1;
	}

	public void setBeneNameLast1(String beneNameLast1) {
		this.beneNameLast1 = beneNameLast1;
	}

	public String getBeneNameLast2() {
		return beneNameLast2;
	}

	public void setBeneNameLast2(String beneNameLast2) {
		this.beneNameLast2 = beneNameLast2;
	}

	public String getBeneAddress() {
		return beneAddress;
	}

	public void setBeneAddress(String beneAddress) {
		this.beneAddress = beneAddress;
	}

	public String getBeneCity() {
		return beneCity;
	}

	public void setBeneCity(String beneCity) {
		this.beneCity = beneCity;
	}

	public String getBeneState() {
		return beneState;
	}

	public void setBeneState(String beneState) {
		this.beneState = beneState;
	}

	public String getBeneCountry() {
		return beneCountry;
	}

	public void setBeneCountry(String beneCountry) {
		this.beneCountry = beneCountry;
	}

	public String getBeneZip() {
		return beneZip;
	}

	public void setBeneZip(String beneZip) {
		this.beneZip = beneZip;
	}

	public String getBeneTelNo() {
		return beneTelNo;
	}

	public void setBeneTelNo(String beneTelNo) {
		this.beneTelNo = beneTelNo;
	}

	public String getBeneCurrency() {
		return beneCurrency;
	}

	public void setBeneCurrency(String beneCurrency) {
		this.beneCurrency = beneCurrency;
	}

	public String getBeneAmount() {
		return beneAmount;
	}

	public void setBeneAmount(String beneAmount) {
		this.beneAmount = beneAmount;
	}

	public String getpCRate() {
		return pCRate;
	}

	public void setpCRate(String pCRate) {
		this.pCRate = pCRate;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	public String getCustID1Type() {
		return custID1Type;
	}

	public void setCustID1Type(String custID1Type) {
		this.custID1Type = custID1Type;
	}

	public String getCustID1No() {
		return custID1No;
	}

	public void setCustID1No(String custID1No) {
		this.custID1No = custID1No;
	}

	public String getCustID1ExpirationDate() {
		return custID1ExpirationDate;
	}

	public void setCustID1ExpirationDate(String custID1ExpirationDate) {
		this.custID1ExpirationDate = custID1ExpirationDate;
	}

	public String getCustID1IssuedBy() {
		return custID1IssuedBy;
	}

	public void setCustID1IssuedBy(String custID1IssuedBy) {
		this.custID1IssuedBy = custID1IssuedBy;
	}

	public String getCustID1IssuedByCountry() {
		return custID1IssuedByCountry;
	}

	public void setCustID1IssuedByCountry(String custID1IssuedByCountry) {
		this.custID1IssuedByCountry = custID1IssuedByCountry;
	}

	public String getCustCountryOfBirth() {
		return custCountryOfBirth;
	}

	public void setCustCountryOfBirth(String custCountryOfBirth) {
		this.custCountryOfBirth = custCountryOfBirth;
	}

	public String getCustDateOfBirth() {
		return custDateOfBirth;
	}

	public void setCustDateOfBirth(String custDateOfBirth) {
		this.custDateOfBirth = custDateOfBirth;
	}

	public String getCustNationality() {
		return custNationality;
	}

	public void setCustNationality(String custNationality) {
		this.custNationality = custNationality;
	}

	public String getResponseDateTimeUTC() {
		return responseDateTimeUTC;
	}

	public void setResponseDateTimeUTC(String responseDateTimeUTC) {
		this.responseDateTimeUTC = responseDateTimeUTC;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public RequiredField getRequiredField() {
		return requiredField;
	}

	public void setRequiredField(RequiredField requiredField) {
		this.requiredField = requiredField;
	}

	@Override
	public String toString() {
		return "CashPickUpSearchDTO [transRefID=" + transRefID + ", orderFound=" + orderFound + ", pin=" + pin
				+ ", orderNo=" + orderNo + ", seqIDRA=" + seqIDRA + ", seqIDPA=" + seqIDPA + ", orderDate=" + orderDate
				+ ", countryFrom=" + countryFrom + ", custNameFirst=" + custNameFirst + ", custNameMiddle="
				+ custNameMiddle + ", custNameLast1=" + custNameLast1 + ", custNameLast2=" + custNameLast2
				+ ", custAddress=" + custAddress + ", custCity=" + custCity + ", custState=" + custState
				+ ", custCountry=" + custCountry + ", custZip=" + custZip + ", custTelNo=" + custTelNo
				+ ", custBeneRelationship=" + custBeneRelationship + ", custCurrency=" + custCurrency + ", custAmount="
				+ custAmount + ", rate=" + rate + ", beneNameFirst=" + beneNameFirst + ", beneNameMiddle="
				+ beneNameMiddle + ", beneNameLast1=" + beneNameLast1 + ", beneNameLast2=" + beneNameLast2
				+ ", beneAddress=" + beneAddress + ", beneCity=" + beneCity + ", beneState=" + beneState
				+ ", beneCountry=" + beneCountry + ", beneZip=" + beneZip + ", beneTelNo=" + beneTelNo
				+ ", beneCurrency=" + beneCurrency + ", beneAmount=" + beneAmount + ", pCRate=" + pCRate
				+ ", transferReason=" + transferReason + ", custID1Type=" + custID1Type + ", custID1No=" + custID1No
				+ ", custID1ExpirationDate=" + custID1ExpirationDate + ", custID1IssuedBy=" + custID1IssuedBy
				+ ", custID1IssuedByCountry=" + custID1IssuedByCountry + ", custCountryOfBirth=" + custCountryOfBirth
				+ ", custDateOfBirth=" + custDateOfBirth + ", custNationality=" + custNationality
				+ ", responseDateTimeUTC=" + responseDateTimeUTC + ", responseCode=" + responseCode + ", responseText="
				+ responseText + ", requiredField=" + requiredField + "]";
	}

	
}
