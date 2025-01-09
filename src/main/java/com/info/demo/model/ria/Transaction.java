package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7878798693758684222L;
	
	@JsonProperty("OrderStatus")
	private String orderStatus;

	@JsonProperty("OrderDate")
	private String orderDate;
	
	@JsonProperty("OrderTime")
	private String orderTime;

	@JsonProperty("OrderNo")
	private String orderNo;
	
	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("CountryFrom")
	private String countryFrom;
	
	@JsonProperty("CountryTo")
	private String countryTo;

	@JsonProperty("DeliveryMethod")
	private String deliveryMethod;
	
	@JsonProperty("TransferReason")
	private String transferReason;

	@JsonProperty("PaymentReferenceNumber")
	private String paymentReferenceNumber;
	
	@JsonProperty("CustPaymentMethodID")
	private String custPaymentMethodID;

	@JsonProperty("CustPaymentMethod")
	private String custPaymentMethod;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
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

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public String getCountryTo() {
		return countryTo;
	}

	public void setCountryTo(String countryTo) {
		this.countryTo = countryTo;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}

	public String getPaymentReferenceNumber() {
		return paymentReferenceNumber;
	}

	public void setPaymentReferenceNumber(String paymentReferenceNumber) {
		this.paymentReferenceNumber = paymentReferenceNumber;
	}

	public String getCustPaymentMethodID() {
		return custPaymentMethodID;
	}

	public void setCustPaymentMethodID(String custPaymentMethodID) {
		this.custPaymentMethodID = custPaymentMethodID;
	}

	public String getCustPaymentMethod() {
		return custPaymentMethod;
	}

	public void setCustPaymentMethod(String custPaymentMethod) {
		this.custPaymentMethod = custPaymentMethod;
	}

	@Override
	public String toString() {
		return "Transaction [orderStatus=" + orderStatus + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", orderNo=" + orderNo + ", pin=" + pin + ", countryFrom=" + countryFrom + ", countryTo=" + countryTo
				+ ", deliveryMethod=" + deliveryMethod + ", transferReason=" + transferReason
				+ ", paymentReferenceNumber=" + paymentReferenceNumber + ", custPaymentMethodID=" + custPaymentMethodID
				+ ", custPaymentMethod=" + custPaymentMethod + "]";
	}
	
	
}
