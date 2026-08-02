package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DownloadableOrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 495984996444973031L;

	@JsonProperty("Ria")
	private Ria ria;

	@JsonProperty("Transaction")
	private Transaction transaction;

	@JsonProperty("Quotation")
	private Quotation quotation;

	@JsonProperty("Customer")
	private Customer customer;

	@JsonProperty("SendingPartner")
	private SendingPartner sendingPartner;

	@JsonProperty("Beneficiary")
	private Beneficiary beneficiary;

	@JsonProperty("PayoutPartner")
	private PayoutPartner payoutPartner;

	public Ria getRia() {
		return ria;
	}

	public void setRia(Ria ria) {
		this.ria = ria;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SendingPartner getSendingPartner() {
		return sendingPartner;
	}

	public void setSendingPartner(SendingPartner sendingPartner) {
		this.sendingPartner = sendingPartner;
	}

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public PayoutPartner getPayoutPartner() {
		return payoutPartner;
	}

	public void setPayoutPartner(PayoutPartner payoutPartner) {
		this.payoutPartner = payoutPartner;
	}

}
