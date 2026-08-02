package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadableOrderResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4770768256194760343L;
    
	@JsonProperty("Errors")
	private Errors errors;
	
	@JsonProperty("Response")
	private DownloadableResponse response;

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public DownloadableResponse getResponse() {
		return response;
	}

	public void setResponse(DownloadableResponse response) {
		this.response = response;
	}
	

}
