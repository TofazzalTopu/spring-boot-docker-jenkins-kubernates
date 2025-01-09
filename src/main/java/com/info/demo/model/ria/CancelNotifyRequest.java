package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelNotifyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6580404876724349925L;

	@JsonProperty("ResponseDate")
	private String responseDate;

	@JsonProperty("ResponseTime")
	private String responseTime;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseDesc")
	private String responseDesc;

	@JsonProperty("PCOrderNo")
	private String pcOrderNo;

	@JsonProperty("SCOrderNo")
	private String scOrderNo;

	@JsonProperty("Comments")
	private String comments;

	public String getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
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
		return "CancelNotifyRequest [responseDate=" + responseDate + ", responseTime=" + responseTime
				+ ", responseCode=" + responseCode + ", responseDesc=" + responseDesc + ", pcOrderNo=" + pcOrderNo
				+ ", scOrderNo=" + scOrderNo + ", comments=" + comments + "]";
	}
	
}
