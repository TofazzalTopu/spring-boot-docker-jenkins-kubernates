package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -842624611772923438L;

	@JsonProperty("Response")
	private OrderStatusDTO response;

	

	@JsonProperty("Errors")
	private Errors errors;

	public OrderStatusDTO getResponse() {
		return response;
	}

	public void setResponse(OrderStatusDTO response) {
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
		return "OrderStatusResponse [response=" + response.toString() + ", errors=" + errors + "]";
	}

}
