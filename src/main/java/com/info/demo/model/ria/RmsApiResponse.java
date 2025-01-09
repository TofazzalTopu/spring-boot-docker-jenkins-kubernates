package com.info.demo.model.ria;

import java.util.List;

public class RmsApiResponse {
	private List<RemittanceData> remittanceDataList;
	private String processStatus;
	private String errorMessage;
	
	public List<RemittanceData> getRemittanceDataList() {
		return remittanceDataList;
	}

	public void setRemittanceDataList(List<RemittanceData> remittanceDataList) {
		this.remittanceDataList = remittanceDataList;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
