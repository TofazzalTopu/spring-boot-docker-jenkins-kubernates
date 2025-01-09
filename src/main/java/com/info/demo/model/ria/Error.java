package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Error implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -684267710558661862L;

	@JsonProperty("ErrorMsg")
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "Error [errorMsg=" + errorMsg + "]";
	}

	
	
	
	
}
