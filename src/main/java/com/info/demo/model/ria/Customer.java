package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7751111776498829260L;

	@JsonProperty("RiaInformation")
	private RiaInformation riaInfo;

	@JsonProperty("PersonalInformation")
	private PersonalInformation personalInfo;

	@JsonProperty("IdentityDocument")
	private IdentityDocument identityDocument;

	@JsonProperty("TaxInformation")
	private TaxInformation taxInfo;

	@JsonProperty("ContactDetails")
	private ContactDetails contactDetails;

	public RiaInformation getRiaInfo() {
		return riaInfo;
	}

	public void setRiaInfo(RiaInformation riaInfo) {
		this.riaInfo = riaInfo;
	}

	public PersonalInformation getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInformation personalInfo) {
		this.personalInfo = personalInfo;
	}

	public IdentityDocument getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(IdentityDocument identityDocument) {
		this.identityDocument = identityDocument;
	}

	public TaxInformation getTaxInfo() {
		return taxInfo;
	}

	public void setTaxInfo(TaxInformation taxInfo) {
		this.taxInfo = taxInfo;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

}
