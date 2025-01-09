package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusDTO implements Serializable {

	private static final long serialVersionUID = 6580404876724349925L;

	@JsonProperty("ProcessDate")
	private String processDate;
	
	@JsonProperty("ProcessTime")
	private String processTime;

	@JsonProperty("NotificationCode")
	private String notificationCode;

	@JsonProperty("NotificationDesc")
	private String notificationDesc;

	@JsonProperty("PCOrderNo")
	private String pcOrderNo;

	@JsonProperty("SCOrderNo")
	private String scOrderNo;

	@JsonProperty("Comments")
	private String comments;


	
	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getProcessTime() {
		return processTime;
	}

	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}

	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	

	@Override
	public String toString() {
		return "OrderStatusDTO [processDate=" + processDate + ", processTime=" + processTime + ", notificationCode="
				+ notificationCode + ", notificationDesc=" + notificationDesc + ", pcOrderNo=" + pcOrderNo
				+ ", scOrderNo=" + scOrderNo + ", comments=" + comments + "]";
	}
}
