package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeneContactDetails  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7924506860836761522L;
	
	@JsonProperty("BenePhoneNo")
	private String benePhoneNo;
	
	@JsonProperty("BeneCellNo")
	private String beneCellNo;
	
    @JsonProperty("BeneEmailAddress")
	private String beneEmailAddress;
	
	@JsonProperty("BeneMessage")
	private String beneMessage;

	public String getBenePhoneNo() {
		return benePhoneNo;
	}

	public void setBenePhoneNo(String benePhoneNo) {
		this.benePhoneNo = benePhoneNo;
	}

	public String getBeneCellNo() {
		return beneCellNo;
	}

	public void setBeneCellNo(String beneCellNo) {
		this.beneCellNo = beneCellNo;
	}

	public String getBeneEmailAddress() {
		return beneEmailAddress;
	}

	public void setBeneEmailAddress(String beneEmailAddress) {
		this.beneEmailAddress = beneEmailAddress;
	}

	public String getBeneMessage() {
		return beneMessage;
	}

	public void setBeneMessage(String beneMessage) {
		this.beneMessage = beneMessage;
	}
	
	

}
