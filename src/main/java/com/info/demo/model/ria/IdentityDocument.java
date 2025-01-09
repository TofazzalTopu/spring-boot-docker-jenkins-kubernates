package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2679868153797999066L;
	
	@JsonProperty("CustID1Type")
	private String custID1Type;
	
	@JsonProperty("CustID1No")
	private String custID1No;
	
	@JsonProperty("CustID1IssuedBy")
	private String custID1IssuedBy;
	
	@JsonProperty("CustID1IssuedByState")
	private String custID1IssuedByState;
	
	@JsonProperty("CustID1IssuedByCountry")
	private String custID1IssuedByCountry;
	
	@JsonProperty("CustID1IssuedDate")
	private String custID1IssuedDate;
	
	@JsonProperty("CustID1ExpirationDate")
	private String custID1ExpirationDate;

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

	public String getCustID1IssuedBy() {
		return custID1IssuedBy;
	}

	public void setCustID1IssuedBy(String custID1IssuedBy) {
		this.custID1IssuedBy = custID1IssuedBy;
	}

	public String getCustID1IssuedByState() {
		return custID1IssuedByState;
	}

	public void setCustID1IssuedByState(String custID1IssuedByState) {
		this.custID1IssuedByState = custID1IssuedByState;
	}

	public String getCustID1IssuedByCountry() {
		return custID1IssuedByCountry;
	}

	public void setCustID1IssuedByCountry(String custID1IssuedByCountry) {
		this.custID1IssuedByCountry = custID1IssuedByCountry;
	}

	public String getCustID1IssuedDate() {
		return custID1IssuedDate;
	}

	public void setCustID1IssuedDate(String custID1IssuedDate) {
		this.custID1IssuedDate = custID1IssuedDate;
	}

	public String getCustID1ExpirationDate() {
		return custID1ExpirationDate;
	}

	public void setCustID1ExpirationDate(String custID1ExpirationDate) {
		this.custID1ExpirationDate = custID1ExpirationDate;
	}
	
	
}
