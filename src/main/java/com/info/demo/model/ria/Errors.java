package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8682910095304397270L;

	@JsonProperty("Error")
	private java.lang.Error error;

	public java.lang.Error getError() {
		return error;
	}

	public void setError(java.lang.Error error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Errors [error=" + error + "]";
	}

}
