package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CashPickUpPaymentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8153505308092061877L;

	@JsonProperty("TransRefID")
	private String transRefID;

	@JsonProperty("OrderNo")
	private String orderNo;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("BeneCurrency")
	private String beneCurrency;

	@JsonProperty("BeneAmount")
	private String beneAmount;

	@JsonProperty("PCCommissionCurrency")
	private String pCCommissionCurrency;

	@JsonProperty("PCCommissionAmount")
	private String pCCommissionAmount;

	@JsonProperty("ResponseDateTimeUTC")
	private String responseDateTimeUTC;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseText")
	private String responseText;

	public String getTransRefID() {
		return transRefID;
	}

	public void setTransRefID(String transRefID) {
		this.transRefID = transRefID;
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

	public String getpCCommissionCurrency() {
		return pCCommissionCurrency;
	}

	public void setpCCommissionCurrency(String pCCommissionCurrency) {
		this.pCCommissionCurrency = pCCommissionCurrency;
	}

	public String getpCCommissionAmount() {
		return pCCommissionAmount;
	}

	public void setpCCommissionAmount(String pCCommissionAmount) {
		this.pCCommissionAmount = pCCommissionAmount;
	}

	public String getResponseDateTimeUTC() {
		return responseDateTimeUTC;
	}

	public void setResponseDateTimeUTC(String responseDateTimeUTC) {
		this.responseDateTimeUTC = responseDateTimeUTC;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	@Override
	public String toString() {
		return "CashPickUpPaymentDTO [transRefID=" + transRefID + ", orderNo=" + orderNo + ", pin=" + pin
				+ ", beneCurrency=" + beneCurrency + ", beneAmount=" + beneAmount + ", pCCommissionCurrency="
				+ pCCommissionCurrency + ", pCCommissionAmount=" + pCCommissionAmount + ", responseDateTimeUTC="
				+ responseDateTimeUTC + ", responseCode=" + responseCode + ", responseText=" + responseText + "]";
	}

}
