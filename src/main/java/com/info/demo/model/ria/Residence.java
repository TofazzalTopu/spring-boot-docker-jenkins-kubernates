package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Residence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6252411704699895709L;
	

	@JsonProperty("BeneAddress")
	private String beneAddress;
	
	@JsonProperty("BeneCity")
	private String beneCity;
	
    @JsonProperty("BeneState")
	private String beneState;
	
	@JsonProperty("BeneZipCode")
	private String beneZipCode;
	
	@JsonProperty("BeneCountry")
	private String beneCountry;


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


	public String getBeneZipCode() {
		return beneZipCode;
	}


	public void setBeneZipCode(String beneZipCode) {
		this.beneZipCode = beneZipCode;
	}


	public String getBeneCountry() {
		return beneCountry;
	}


	public void setBeneCountry(String beneCountry) {
		this.beneCountry = beneCountry;
	}
	
	

}
