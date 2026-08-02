package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BenePersonalInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5577422615129519106L;
   
	@JsonProperty("BeneFirstName")
	private String beneFirstName;
	
	@JsonProperty("BeneMiddleName")
	private String beneMiddleName;
	
	@JsonProperty("BeneLastName")
	private String beneLastName;
	
	@JsonProperty("BeneLastName2")
	private String beneLastName2;
	
	@JsonProperty("BeneNationality")
	private String beneNationality;
	
	@JsonProperty("BeneDateOfBirth")
	private String beneDateOfBirth;
	
	@JsonProperty("BeneFatherName")
	private String beneFatherName;
	
	@JsonProperty("BeneMotherName")
	private String beneMotherName;

	public String getBeneFirstName() {
		return beneFirstName;
	}

	public void setBeneFirstName(String beneFirstName) {
		this.beneFirstName = beneFirstName;
	}

	public String getBeneMiddleName() {
		return beneMiddleName;
	}

	public void setBeneMiddleName(String beneMiddleName) {
		this.beneMiddleName = beneMiddleName;
	}

	public String getBeneLastName() {
		return beneLastName;
	}

	public void setBeneLastName(String beneLastName) {
		this.beneLastName = beneLastName;
	}

	public String getBeneLastName2() {
		return beneLastName2;
	}

	public void setBeneLastName2(String beneLastName2) {
		this.beneLastName2 = beneLastName2;
	}

	public String getBeneNationality() {
		return beneNationality;
	}

	public void setBeneNationality(String beneNationality) {
		this.beneNationality = beneNationality;
	}

	public String getBeneDateOfBirth() {
		return beneDateOfBirth;
	}

	public void setBeneDateOfBirth(String beneDateOfBirth) {
		this.beneDateOfBirth = beneDateOfBirth;
	}

	public String getBeneFatherName() {
		return beneFatherName;
	}

	public void setBeneFatherName(String beneFatherName) {
		this.beneFatherName = beneFatherName;
	}

	public String getBeneMotherName() {
		return beneMotherName;
	}

	public void setBeneMotherName(String beneMotherName) {
		this.beneMotherName = beneMotherName;
	}
	
	
}
