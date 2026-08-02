package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelorderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 495984996444973031L;

	@JsonProperty("SCOrderNo")
	private String scorderNo;

	public String getScorderNo() {
		return scorderNo;
	}

	public void setScorderNo(String scorderNo) {
		this.scorderNo = scorderNo;
	}

	
	
}
