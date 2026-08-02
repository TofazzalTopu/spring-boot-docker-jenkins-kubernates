package com.info.demo.model.ria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderReceivedStatusRequest implements Serializable {

	private static final long serialVersionUID = 6580404876724349925L;

	@JsonProperty("NotificationID")
	private String notificationID;

	@JsonProperty("OrderStatus")
	private String orderStatus;

	@JsonProperty("StatusDate")
	private String statusDate;

	@JsonProperty("StatusTime")
	private String statusTime;

	@JsonProperty("Reason")
	private String reason;

	@JsonProperty("PCOrderNo")
	private String pcOrderNo;

	@JsonProperty("SCOrderNo")
	private String scOrderNo;

	@Override
	public String toString() {
		return "OrderReceivedStatusRequest [notificationID=" + notificationID + ", orderStatus=" + orderStatus + ", statusDate="
				+ statusDate + ", statusTime=" + statusTime + ", pcOrderNo=" + pcOrderNo + ", scOrderNo=" + scOrderNo
				+ "]";
	}

}
