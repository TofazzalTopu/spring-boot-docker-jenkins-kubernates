package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1074501763866462002L;
	
	@JsonProperty("ProductID")
	private String productID;
	
	@JsonProperty("ProductItemID")
	private String productItemID;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductItemID() {
		return productItemID;
	}

	public void setProductItemID(String productItemID) {
		this.productItemID = productItemID;
	}

	@Override
	public String toString() {
		return "Ria [productID=" + productID + ", productItemID=" + productItemID + "]";
	}

	
}
