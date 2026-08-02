package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7340416205299308451L;
    
	@JsonProperty("PaymentCurrency")
	private String paymentCurrency;
	
	@JsonProperty("PaymentAmount")
	private String paymentAmount;
	
	@JsonProperty("CommissionCurrency")
	private String commissionCurrency;
	
	@JsonProperty("CommissionAmount")
	private String commissionAmount;
	
	@JsonProperty("CustChargeCurrency")
	private String custChargeCurrency;
	
	@JsonProperty("CustChargeAmount")
	private String custChargeAmount;
	
	@JsonProperty("BeneCurrency")
	private String beneCurrency;
	
	@JsonProperty("BeneAmount")
	private String beneAmount;

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getCommissionCurrency() {
		return commissionCurrency;
	}

	public void setCommissionCurrency(String commissionCurrency) {
		this.commissionCurrency = commissionCurrency;
	}

	public String getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(String commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public String getCustChargeCurrency() {
		return custChargeCurrency;
	}

	public void setCustChargeCurrency(String custChargeCurrency) {
		this.custChargeCurrency = custChargeCurrency;
	}

	public String getCustChargeAmount() {
		return custChargeAmount;
	}

	public void setCustChargeAmount(String custChargeAmount) {
		this.custChargeAmount = custChargeAmount;
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

	@Override
	public String toString() {
		return "Quotation [paymentCurrency=" + paymentCurrency + ", paymentAmount=" + paymentAmount
				+ ", commissionCurrency=" + commissionCurrency + ", commissionAmount=" + commissionAmount
				+ ", custChargeCurrency=" + custChargeCurrency + ", custChargeAmount=" + custChargeAmount
				+ ", beneCurrency=" + beneCurrency + ", beneAmount=" + beneAmount + "]";
	}
	
	
	
}
