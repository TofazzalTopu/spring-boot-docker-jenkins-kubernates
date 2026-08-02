package com.info.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STOP_REMITTANCE")
public class StopRemittanceData implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ACCEPT_REQUEST = "REQUEST_ACCEPTED";
	public static final String REJECT_REQUEST = "REQUEST_REJECTED";
	
	public static final String ACCEPTED = "ACCEPTED";
	
	public static final String REJECT_NOT_FOUND = "REJECT_NOT_FOUND";
	public static final String REJECT_ALREADY_PAID = "REJECT_ALREADY_PAID";
	public static final String REJECT_ALREADY_CANCELLED = "REJECT_ALREADY_CANCELLED";
	public static final String REJECT_OTHER = "REJECT_OTHER";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOP_REMITTANCE_GEN")
	@SequenceGenerator(name = "STOP_REMITTANCE_GEN", sequenceName = "STOP_REMITTANCE_SEQ", allocationSize = 1)
	private Long id;

	@Column(name = "REFERENCE_NO")
	private String referenceNo;

	@Column(name = "REFERENCE_DATE")
	@Temporal(TemporalType.DATE)
	private Date referenceDate;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "EXCHANGE_HOUSE_CODE")
	private String exchangeCode;

	@Column(name = "PROCESS_STATUS")
	private String processStatus;

	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "REQUEST_BY")
	private String requestBy;

	@Column(name = "REQUEST_ON")
	@Temporal(TemporalType.DATE)
	private Date requestOn;
	
	@Column(name = "EXCHANGE_STATUS")
	private String exchangeStatus;
	
	@Column(name = "RESPONDED")
	private String responed;

	
}