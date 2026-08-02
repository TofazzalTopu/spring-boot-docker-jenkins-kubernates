package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpCancelResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -842624611772923438L;

	@JsonProperty("Response")
	private CashPickUpCancelDTO response;

	@JsonProperty("Errors")
	private Errors errors;

	public CashPickUpCancelDTO getResponse() {
		return response;
	}

	public void setResponse(CashPickUpCancelDTO response) {
		this.response = response;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

}
