package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelDownloadResponse implements Serializable {
	private static final long serialVersionUID = 4770768256194760343L;

	@JsonProperty("Response")
	private List<CancelorderDTO> response;

	public List<CancelorderDTO> getResponse() {
		return response;
	}

	public void setResponse(List<CancelorderDTO> response) {
		this.response = response;
	}

}
