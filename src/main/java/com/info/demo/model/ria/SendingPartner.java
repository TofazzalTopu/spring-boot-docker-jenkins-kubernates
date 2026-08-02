package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendingPartner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118577617776939297L;
   
	@JsonProperty("SendingCorrespBranchNo")
	private String sendingCorrespBranchNo;
	
	@JsonProperty("SendingCorrespID")
	private String sendingCorrespID;
	
	@JsonProperty("SendingCorrespName")
	private String sendingCorrespName;
	
	@JsonProperty("SendingCorrespSequenceID")
	private String sendingCorrespSequenceID;

	public String getSendingCorrespBranchNo() {
		return sendingCorrespBranchNo;
	}

	public void setSendingCorrespBranchNo(String sendingCorrespBranchNo) {
		this.sendingCorrespBranchNo = sendingCorrespBranchNo;
	}

	public String getSendingCorrespID() {
		return sendingCorrespID;
	}

	public void setSendingCorrespID(String sendingCorrespID) {
		this.sendingCorrespID = sendingCorrespID;
	}

	public String getSendingCorrespName() {
		return sendingCorrespName;
	}

	public void setSendingCorrespName(String sendingCorrespName) {
		this.sendingCorrespName = sendingCorrespName;
	}

	public String getSendingCorrespSequenceID() {
		return sendingCorrespSequenceID;
	}

	public void setSendingCorrespSequenceID(String sendingCorrespSequenceID) {
		this.sendingCorrespSequenceID = sendingCorrespSequenceID;
	}
	
}
