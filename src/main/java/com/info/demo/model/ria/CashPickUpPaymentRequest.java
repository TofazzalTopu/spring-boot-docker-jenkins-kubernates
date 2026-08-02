package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpPaymentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6580404876724349925L;

	@JsonProperty("DateTimeLocal")
	private String dateTimeLocal;

	@JsonProperty("DateTimeUTC")
	private String dateTimeUTC;

	@JsonProperty("VerifyOrderTransRefID")
	private String verifyOrderTransRefID;

	@JsonProperty("OrderNo")
	private String orderNo;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("BeneCurrency")
	private String beneCurrency;

	@JsonProperty("BeneAmount")
	private String beneAmount;

	@JsonProperty("PaidDateTimeLocal")
	private String paidDateTimeLocal;

	@JsonProperty("PaidDateTimeUTC")
	private String paidDateTimeUTC;

	@JsonProperty("BeneIDType")
	private String beneIDType;

	@JsonProperty("BeneIDNumber")
	private String beneIDNumber;

	@JsonProperty("BeneIDIssuedBy")
	private String beneIDIssuedBy;

	@JsonProperty("BeneIDIssuedByCountry")
	private String beneIDIssuedByCountry;

	@JsonProperty("BeneIDIssuedByState")
	private String beneIDIssuedByState;

	@JsonProperty("BeneIDIssueDate")
	private String beneIDIssueDate;

	@JsonProperty("BeneIDExpirationDate")
	private String beneIDExpirationDate;

	@JsonProperty("CorrespLocID")
	private String correspLocID;

	@JsonProperty("CorrespLocName")
	private String correspLocName;

	@JsonProperty("CorrespLocAddress")
	private String correspLocAddress;

	@JsonProperty("CorrespLocCity")
	private String correspLocCity;

	@JsonProperty("CorrespLocState")
	private String correspLocState;

	@JsonProperty("CorrespLocPostalCode")
	private String correspLocPostalCode;

	@JsonProperty("CorrespLocCountry")
	private String correspLocCountry;

	@JsonProperty("BeneTelNo")
	private String beneTelNo;

	@JsonProperty("BeneAddress")
	private String beneAddress;

	@JsonProperty("BeneCity")
	private String beneCity;

	@JsonProperty("BeneCounty")
	private String beneCounty;

	@JsonProperty("BeneState")
	private String beneState;

	@JsonProperty("BenePostalCode")
	private String benePostalCode;

	@JsonProperty("BeneCountry")
	private String beneCountry;

	@JsonProperty("BeneNationality")
	private String beneNationality;

	@JsonProperty("BeneCountryOfResidence")
	private String beneCountryOfResidence;

	@JsonProperty("BeneDateOfBirth")
	private String beneDateOfBirth;

	@JsonProperty("BeneCountryOfBirth")
	private String beneCountryOfBirth;

	@JsonProperty("BeneStateOfBirth")
	private String beneStateOfBirth;

	@JsonProperty("BeneCityOfBirth")
	private String beneCityOfBirth;

	@JsonProperty("BeneOccupation")
	private String beneOccupation;

	@JsonProperty("BeneGender")
	private String beneGender;

	@JsonProperty("BeneTaxID")
	private String beneTaxID;

	@JsonProperty("BeneCustRelationship")
	private String beneCustRelationship;

	@JsonProperty("BeneDistrict")
	private String beneDistrict;

	@JsonProperty("BeneIdentityCode")
	private String beneIdentityCode;

	@JsonProperty("BeneCURPNumber")
	private String beneCURPNumber;

	@JsonProperty("TransferReason")
	private String transferReason;

	@JsonProperty("OnBehalfOf")
	private String onBehalfOf;

	public String getDateTimeLocal() {
		return dateTimeLocal;
	}

	public void setDateTimeLocal(String dateTimeLocal) {
		this.dateTimeLocal = dateTimeLocal;
	}

	public String getDateTimeUTC() {
		return dateTimeUTC;
	}

	public void setDateTimeUTC(String dateTimeUTC) {
		this.dateTimeUTC = dateTimeUTC;
	}

	public String getVerifyOrderTransRefID() {
		return verifyOrderTransRefID;
	}

	public void setVerifyOrderTransRefID(String verifyOrderTransRefID) {
		this.verifyOrderTransRefID = verifyOrderTransRefID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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

	public String getPaidDateTimeLocal() {
		return paidDateTimeLocal;
	}

	public void setPaidDateTimeLocal(String paidDateTimeLocal) {
		this.paidDateTimeLocal = paidDateTimeLocal;
	}

	public String getPaidDateTimeUTC() {
		return paidDateTimeUTC;
	}

	public void setPaidDateTimeUTC(String paidDateTimeUTC) {
		this.paidDateTimeUTC = paidDateTimeUTC;
	}

	public String getBeneIDType() {
		return beneIDType;
	}

	public void setBeneIDType(String beneIDType) {
		this.beneIDType = beneIDType;
	}

	public String getBeneIDNumber() {
		return beneIDNumber;
	}

	public void setBeneIDNumber(String beneIDNumber) {
		this.beneIDNumber = beneIDNumber;
	}

	public String getBeneIDIssuedBy() {
		return beneIDIssuedBy;
	}

	public void setBeneIDIssuedBy(String beneIDIssuedBy) {
		this.beneIDIssuedBy = beneIDIssuedBy;
	}

	public String getBeneIDIssuedByCountry() {
		return beneIDIssuedByCountry;
	}

	public void setBeneIDIssuedByCountry(String beneIDIssuedByCountry) {
		this.beneIDIssuedByCountry = beneIDIssuedByCountry;
	}

	public String getBeneIDIssuedByState() {
		return beneIDIssuedByState;
	}

	public void setBeneIDIssuedByState(String beneIDIssuedByState) {
		this.beneIDIssuedByState = beneIDIssuedByState;
	}

	public String getBeneIDIssueDate() {
		return beneIDIssueDate;
	}

	public void setBeneIDIssueDate(String beneIDIssueDate) {
		this.beneIDIssueDate = beneIDIssueDate;
	}

	public String getBeneIDExpirationDate() {
		return beneIDExpirationDate;
	}

	public void setBeneIDExpirationDate(String beneIDExpirationDate) {
		this.beneIDExpirationDate = beneIDExpirationDate;
	}

	public String getCorrespLocID() {
		return correspLocID;
	}

	public void setCorrespLocID(String correspLocID) {
		this.correspLocID = correspLocID;
	}

	public String getCorrespLocName() {
		return correspLocName;
	}

	public void setCorrespLocName(String correspLocName) {
		this.correspLocName = correspLocName;
	}

	public String getCorrespLocAddress() {
		return correspLocAddress;
	}

	public void setCorrespLocAddress(String correspLocAddress) {
		this.correspLocAddress = correspLocAddress;
	}

	public String getCorrespLocCity() {
		return correspLocCity;
	}

	public void setCorrespLocCity(String correspLocCity) {
		this.correspLocCity = correspLocCity;
	}

	public String getCorrespLocState() {
		return correspLocState;
	}

	public void setCorrespLocState(String correspLocState) {
		this.correspLocState = correspLocState;
	}

	public String getCorrespLocPostalCode() {
		return correspLocPostalCode;
	}

	public void setCorrespLocPostalCode(String correspLocPostalCode) {
		this.correspLocPostalCode = correspLocPostalCode;
	}

	public String getCorrespLocCountry() {
		return correspLocCountry;
	}

	public void setCorrespLocCountry(String correspLocCountry) {
		this.correspLocCountry = correspLocCountry;
	}

	public String getBeneTelNo() {
		return beneTelNo;
	}

	public void setBeneTelNo(String beneTelNo) {
		this.beneTelNo = beneTelNo;
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

	public String getBeneCounty() {
		return beneCounty;
	}

	public void setBeneCounty(String beneCounty) {
		this.beneCounty = beneCounty;
	}

	public String getBeneState() {
		return beneState;
	}

	public void setBeneState(String beneState) {
		this.beneState = beneState;
	}

	public String getBenePostalCode() {
		return benePostalCode;
	}

	public void setBenePostalCode(String benePostalCode) {
		this.benePostalCode = benePostalCode;
	}

	public String getBeneCountry() {
		return beneCountry;
	}

	public void setBeneCountry(String beneCountry) {
		this.beneCountry = beneCountry;
	}

	public String getBeneNationality() {
		return beneNationality;
	}

	public void setBeneNationality(String beneNationality) {
		this.beneNationality = beneNationality;
	}

	public String getBeneCountryOfResidence() {
		return beneCountryOfResidence;
	}

	public void setBeneCountryOfResidence(String beneCountryOfResidence) {
		this.beneCountryOfResidence = beneCountryOfResidence;
	}

	public String getBeneDateOfBirth() {
		return beneDateOfBirth;
	}

	public void setBeneDateOfBirth(String beneDateOfBirth) {
		this.beneDateOfBirth = beneDateOfBirth;
	}

	public String getBeneCountryOfBirth() {
		return beneCountryOfBirth;
	}

	public void setBeneCountryOfBirth(String beneCountryOfBirth) {
		this.beneCountryOfBirth = beneCountryOfBirth;
	}

	public String getBeneStateOfBirth() {
		return beneStateOfBirth;
	}

	public void setBeneStateOfBirth(String beneStateOfBirth) {
		this.beneStateOfBirth = beneStateOfBirth;
	}

	public String getBeneCityOfBirth() {
		return beneCityOfBirth;
	}

	public void setBeneCityOfBirth(String beneCityOfBirth) {
		this.beneCityOfBirth = beneCityOfBirth;
	}

	public String getBeneOccupation() {
		return beneOccupation;
	}

	public void setBeneOccupation(String beneOccupation) {
		this.beneOccupation = beneOccupation;
	}

	public String getBeneGender() {
		return beneGender;
	}

	public void setBeneGender(String beneGender) {
		this.beneGender = beneGender;
	}

	public String getBeneTaxID() {
		return beneTaxID;
	}

	public void setBeneTaxID(String beneTaxID) {
		this.beneTaxID = beneTaxID;
	}

	public String getBeneCustRelationship() {
		return beneCustRelationship;
	}

	public void setBeneCustRelationship(String beneCustRelationship) {
		this.beneCustRelationship = beneCustRelationship;
	}

	public String getBeneDistrict() {
		return beneDistrict;
	}

	public void setBeneDistrict(String beneDistrict) {
		this.beneDistrict = beneDistrict;
	}

	public String getBeneIdentityCode() {
		return beneIdentityCode;
	}

	public void setBeneIdentityCode(String beneIdentityCode) {
		this.beneIdentityCode = beneIdentityCode;
	}

	public String getBeneCURPNumber() {
		return beneCURPNumber;
	}

	public void setBeneCURPNumber(String beneCURPNumber) {
		this.beneCURPNumber = beneCURPNumber;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	public String getOnBehalfOf() {
		return onBehalfOf;
	}

	public void setOnBehalfOf(String onBehalfOf) {
		this.onBehalfOf = onBehalfOf;
	}

	@Override
	public String toString() {
		return "CashPickUpPaymentRequest [dateTimeLocal=" + dateTimeLocal + ", dateTimeUTC=" + dateTimeUTC
				+ ", verifyOrderTransRefID=" + verifyOrderTransRefID + ", orderNo=" + orderNo + ", pin=" + pin
				+ ", beneCurrency=" + beneCurrency + ", beneAmount=" + beneAmount + ", paidDateTimeLocal="
				+ paidDateTimeLocal + ", paidDateTimeUTC=" + paidDateTimeUTC + ", beneIDType=" + beneIDType
				+ ", beneIDNumber=" + beneIDNumber + ", beneIDIssuedBy=" + beneIDIssuedBy + ", beneIDIssuedByCountry="
				+ beneIDIssuedByCountry + ", beneIDIssuedByState=" + beneIDIssuedByState + ", beneIDIssueDate="
				+ beneIDIssueDate + ", beneIDExpirationDate=" + beneIDExpirationDate + ", correspLocID=" + correspLocID
				+ ", correspLocName=" + correspLocName + ", correspLocAddress=" + correspLocAddress
				+ ", correspLocCity=" + correspLocCity + ", correspLocState=" + correspLocState
				+ ", correspLocPostalCode=" + correspLocPostalCode + ", correspLocCountry=" + correspLocCountry
				+ ", beneTelNo=" + beneTelNo + ", beneAddress=" + beneAddress + ", beneCity=" + beneCity
				+ ", beneCounty=" + beneCounty + ", beneState=" + beneState + ", benePostalCode=" + benePostalCode
				+ ", beneCountry=" + beneCountry + ", beneNationality=" + beneNationality + ", beneCountryOfResidence="
				+ beneCountryOfResidence + ", beneDateOfBirth=" + beneDateOfBirth + ", beneCountryOfBirth="
				+ beneCountryOfBirth + ", beneStateOfBirth=" + beneStateOfBirth + ", beneCityOfBirth=" + beneCityOfBirth
				+ ", beneOccupation=" + beneOccupation + ", beneGender=" + beneGender + ", beneTaxID=" + beneTaxID
				+ ", beneCustRelationship=" + beneCustRelationship + ", beneDistrict=" + beneDistrict
				+ ", beneIdentityCode=" + beneIdentityCode + ", beneCURPNumber=" + beneCURPNumber + ", transferReason="
				+ transferReason + ", onBehalfOf=" + onBehalfOf + "]";
	}

}
