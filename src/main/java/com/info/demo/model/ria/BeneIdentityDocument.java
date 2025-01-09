package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeneIdentityDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302326239373005901L;
	
	@JsonProperty("BeneIDNo")
	private String beneIDNo;
	
	@JsonProperty("BeneIDType")
	private String beneIDType;

	public String getBeneIDNo() {
		return beneIDNo;
	}

	public void setBeneIDNo(String beneIDNo) {
		this.beneIDNo = beneIDNo;
	}

	public String getBeneIDType() {
		return beneIDType;
	}

	public void setBeneIDType(String beneIDType) {
		this.beneIDType = beneIDType;
	}
	
	
}
