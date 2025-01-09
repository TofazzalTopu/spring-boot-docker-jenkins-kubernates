package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpSearchResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819316525912256838L;

	@JsonProperty("Response")
	private CashPickUpSearchDTO response;

	@JsonProperty("Errors")
	private Errors errors;

	public CashPickUpSearchDTO getResponse() {
		return response;
	}

	public void setResponse(CashPickUpSearchDTO response) {
		this.response = response;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "CashPickUpSearchResponse [response=" + response + ", errors=" + errors + "]";
	}

}
