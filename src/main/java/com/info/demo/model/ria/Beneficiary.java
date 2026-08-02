package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Beneficiary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5363013053464501740L;

	@JsonProperty("RiaInformation")
	private RiaBeneInformation riaBeneInfo;

	@JsonProperty("PersonalInformation")
	private BenePersonalInformation benePersonalInfo;

	@JsonProperty("IdentityDocument")
	private BeneIdentityDocument beneIdentotyDoc;

	@JsonProperty("Residence")
	private Residence residence;

	@JsonProperty("ContactDetails")
	private BeneContactDetails beneContactDetails;

	@JsonProperty("BankAccount")
	private BankAccount bankAccount;

	public RiaBeneInformation getBeneRiaInfo() {
		return riaBeneInfo;
	}

	public void setBeneRiaInfo(RiaBeneInformation riaBeneInfo) {
		this.riaBeneInfo = riaBeneInfo;
	}

	public BenePersonalInformation getBenePersonalInfo() {
		return benePersonalInfo;
	}

	public void setBenePersonalInfo(BenePersonalInformation benePersonalInfo) {
		this.benePersonalInfo = benePersonalInfo;
	}

	public BeneIdentityDocument getBeneIdentotyDoc() {
		return beneIdentotyDoc;
	}

	public void setBeneIdentotyDoc(BeneIdentityDocument beneIdentotyDoc) {
		this.beneIdentotyDoc = beneIdentotyDoc;
	}

	public Residence getResidence() {
		return residence;
	}

	public void setResidence(Residence residence) {
		this.residence = residence;
	}

	public BeneContactDetails getBeneContactDetails() {
		return beneContactDetails;
	}

	public void setBeneContactDetails(BeneContactDetails beneContactDetails) {
		this.beneContactDetails = beneContactDetails;
	}

	public RiaBeneInformation getRiaBeneInfo() {
		return riaBeneInfo;
	}

	public void setRiaBeneInfo(RiaBeneInformation riaBeneInfo) {
		this.riaBeneInfo = riaBeneInfo;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}
