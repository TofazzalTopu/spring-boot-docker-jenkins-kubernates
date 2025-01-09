package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpCancelRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -535325948891141325L;

	@JsonProperty("DateTimeLocal")
	private String dateTimeLocal;

	@JsonProperty("DateTimeUTC")
	private String dateTimeUTC;

	@JsonProperty("OrderPaidTransRefID")
	private String orderPaidTransRefID;

	@JsonProperty("OrderNo")
	private String orderNo;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("BeneCurrency")
	private String beneCurrency;

	@JsonProperty("BeneAmount")
	private String beneAmount;

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

	public String getOrderPaidTransRefID() {
		return orderPaidTransRefID;
	}

	public void setOrderPaidTransRefID(String orderPaidTransRefID) {
		this.orderPaidTransRefID = orderPaidTransRefID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getBeneCurrency() {
		return beneCurrency;
	}

	public void setBeneCurrency(String beneCurrency) {
		this.beneCurrency = beneCurrency;
	}

	public String getBeneAmount() {
		return beneAmount;
	}

	public void setBeneAmount(String beneAmount) {
		this.beneAmount = beneAmount;
	}

}
