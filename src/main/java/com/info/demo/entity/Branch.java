package com.info.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MBRN")
public class Branch implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected BranchKey branchKey;
	@Column(name = "MBRN_NAME", length = 50)
	private String name;

	@Column(name = "MBRN_MICR_CODE")
	private Integer routingNumber;

	@Column(name = "MBRN_NUM_OFFICERS_AVL")
	private Short numberOfOfficersAvailable;

}
