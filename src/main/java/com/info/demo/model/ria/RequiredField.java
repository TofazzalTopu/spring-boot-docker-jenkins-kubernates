package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequiredField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8212566366981987097L;

	@JsonProperty("RequiredField")
	private List<RequiredFieldDTO> requiredFieldDTO;

	public List<RequiredFieldDTO> getRequiredFieldDTO() {
		return requiredFieldDTO;
	}

	public void setRequiredFieldDTO(List<RequiredFieldDTO> requiredFieldDTO) {
		this.requiredFieldDTO = requiredFieldDTO;
	}

}
