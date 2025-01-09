package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusRequest implements Serializable {

	private static final long serialVersionUID = 6580404876724349925L;

	@JsonProperty("NotificationID")
	private String notificationID;

	@JsonProperty("OrderStatus")
	private String orderStatus;

	@JsonProperty("StatusDate")
	private String statusDate;

	@JsonProperty("StatusTime")
	private String statusTime;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("PCOrderNo")
	private String pcOrderNo;

	@JsonProperty("SCOrderNo")
	private String scOrderNo;

	public String getNotificationID() {
		return notificationID;
	}

	public void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPcOrderNo() {
		return pcOrderNo;
	}

	public void setPcOrderNo(String pcOrderNo) {
		this.pcOrderNo = pcOrderNo;
	}

	public String getScOrderNo() {
		return scOrderNo;
	}

	public void setScOrderNo(String scOrderNo) {
		this.scOrderNo = scOrderNo;
	}
	

	@Override
	public String toString() {
		return "OrderStatusRequest [notificationID=" + notificationID + ", orderStatus=" + orderStatus + ", statusDate="
				+ statusDate + ", statusTime=" + statusTime + ", reason=" + reason + ", pcOrderNo=" + pcOrderNo + ", scOrderNo=" + scOrderNo
				+ "]";
	}

}
