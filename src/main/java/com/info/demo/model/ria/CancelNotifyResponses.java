package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelNotifyResponses implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -842624611772923438L;

	@JsonProperty("Response")
	private CancelNotifyResponse response;

	@JsonProperty("Errors")
	private Errors errors;

	public CancelNotifyResponse getResponse() {
		return response;
	}

	public void setResponse(CancelNotifyResponse response) {
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
		return "CancelNotifyResponses [response=" + response.toString() + ", errors=" + errors + "]";
	}

}
