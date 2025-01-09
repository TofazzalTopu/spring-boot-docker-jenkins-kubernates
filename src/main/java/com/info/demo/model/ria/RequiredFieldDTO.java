package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequiredFieldDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7024374907225382903L;

	@JsonProperty("FieldName")
	private String fieldName;

	@JsonProperty("MinLength")
	private String minLength;

	@JsonProperty("MaxLength")
	private String maxLength;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public String toString() {
		return "{"
					+ "\"fieldName\":" +"\""+ fieldName +"\""
					+ ",\"minLength\":" +"\""+ minLength +"\""
					+ ",\"maxLength\":" +"\""+ maxLength +"\""
				+ "}";
	}

}
