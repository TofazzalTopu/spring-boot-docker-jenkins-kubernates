package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpSearchRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7402745781974700577L;

	@JsonProperty("DateTimeLocal")
	private String dateTimeLocal;

	@JsonProperty("DateTimeUTC")
	private String dateTimeUTC;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("BeneAmount")
	private String beneAmount;

	@JsonProperty("CorrespLocID")
	private String correspLocID;

	public String getDateTimeLocal() {
		return dateTimeLocal;
	}

	public void setDateTimeLocal(String dateTimeLocal) {
		this.dateTimeLocal = dateTimeLocal;
	}

	public String getDateTimeUTC() {
		return dateTimeUTC;
	}

	public void setDateTimeUTC(String dateTimeUTC) {
		this.dateTimeUTC = dateTimeUTC;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getBeneAmount() {
		return beneAmount;
	}

	public void setBeneAmount(String beneAmount) {
		this.beneAmount = beneAmount;
	}

	public String getCorrespLocID() {
		return correspLocID;
	}

	public void setCorrespLocID(String correspLocID) {
		this.correspLocID = correspLocID;
	}

}
