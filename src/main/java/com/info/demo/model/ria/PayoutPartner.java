package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayoutPartner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7341483432044653913L;
	
	@JsonProperty("PayingCorrespName")
	private String payingCorrespName;
	
	@JsonProperty("PayingCorrespSequenceID")
	private String payingCorrespSequenceID;
	
    @JsonProperty("PayingCorrespID")
	private String payingCorrespID;
	
	@JsonProperty("PayingCorrespLocID")
	private String payingCorrespLocID;
	
	@JsonProperty("BankDeposit")
	private BankDeposit bankDeposit;
	
	@JsonProperty("PaymentInstruction")
	private String paymentInstruction;

	public String getPayingCorrespName() {
		return payingCorrespName;
	}

	public void setPayingCorrespName(String payingCorrespName) {
		this.payingCorrespName = payingCorrespName;
	}

	public String getPayingCorrespSequenceID() {
		return payingCorrespSequenceID;
	}

	public void setPayingCorrespSequenceID(String payingCorrespSequenceID) {
		this.payingCorrespSequenceID = payingCorrespSequenceID;
	}

	public String getPayingCorrespID() {
		return payingCorrespID;
	}

	public void setPayingCorrespID(String payingCorrespID) {
		this.payingCorrespID = payingCorrespID;
	}

	public String getPayingCorrespLocID() {
		return payingCorrespLocID;
	}

	public void setPayingCorrespLocID(String payingCorrespLocID) {
		this.payingCorrespLocID = payingCorrespLocID;
	}

	public BankDeposit getBankDeposit() {
		return bankDeposit;
	}

	public void setBankDeposit(BankDeposit bankDeposit) {
		this.bankDeposit = bankDeposit;
	}

	public String getPaymentInstruction() {
		return paymentInstruction;
	}

	public void setPaymentInstruction(String paymentInstruction) {
		this.paymentInstruction = paymentInstruction;
	}
	
	

}
