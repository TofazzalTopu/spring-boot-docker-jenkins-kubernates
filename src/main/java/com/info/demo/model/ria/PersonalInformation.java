package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4822153610004982372L;
   
	@JsonProperty("CustFirstName")
	private String custFirstName;
	
	@JsonProperty("CustMiddleName")
	private String custMiddleName;
	
	@JsonProperty("CustLastName")
	private String custLastName;
	
	@JsonProperty("CustLastName2")
	private String custLastName2;
	
	@JsonProperty("CustCountryOfBirth")
	private String custCountryOfBirth;
	
	@JsonProperty("CustNationality")
	private String custNationality;
	
	@JsonProperty("CustDateOfBirth")
	private String custDateOfBirth;
	
	@JsonProperty("CustOccupation")
	private String custOccupation;
	
	@JsonProperty("CustSourceOfFunds")
	private String custSourceOfFunds;
	
	@JsonProperty("CustBeneRelationshipID")
	private String custBeneRelationshipID;
	
	@JsonProperty("CustBeneRelationship")
	private String custBeneRelationship;
	
	
	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustMiddleName() {
		return custMiddleName;
	}

	public void setCustMiddleName(String custMiddleName) {
		this.custMiddleName = custMiddleName;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCustLastName2() {
		return custLastName2;
	}

	public void setCustLastName2(String custLastName2) {
		this.custLastName2 = custLastName2;
	}

	public String getCustCountryOfBirth() {
		return custCountryOfBirth;
	}

	public void setCustCountryOfBirth(String custCountryOfBirth) {
		this.custCountryOfBirth = custCountryOfBirth;
	}

	public String getCustNationality() {
		return custNationality;
	}

	public void setCustNationality(String custNationality) {
		this.custNationality = custNationality;
	}

	public String getCustDateOfBirth() {
		return custDateOfBirth;
	}

	public void setCustDateOfBirth(String custDateOfBirth) {
		this.custDateOfBirth = custDateOfBirth;
	}

	public String getCustOccupation() {
		return custOccupation;
	}

	public void setCustOccupation(String custOccupation) {
		this.custOccupation = custOccupation;
	}

	public String getCustSourceOfFunds() {
		return custSourceOfFunds;
	}

	public void setCustSourceOfFunds(String custSourceOfFunds) {
		this.custSourceOfFunds = custSourceOfFunds;
	}

	public String getCustBeneRelationshipID() {
		return custBeneRelationshipID;
	}

	public void setCustBeneRelationshipID(String custBeneRelationshipID) {
		this.custBeneRelationshipID = custBeneRelationshipID;
	}

	public String getCustBeneRelationship() {
		return custBeneRelationship;
	}

	public void setCustBeneRelationship(String custBeneRelationship) {
		this.custBeneRelationship = custBeneRelationship;
	}

	
}
