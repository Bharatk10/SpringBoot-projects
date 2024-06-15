package com.zettamine.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ISP_ACT",
uniqueConstraints = @UniqueConstraint(columnNames = {"ch_id", "lot_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InspectionActualsId.class)
public class InspectionActuals {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "lot_id", referencedColumnName = "lotId")
	@ToString.Exclude
	private InspectionLot inspectionLot;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ch_id", referencedColumnName = "channelId")
	@ToString.Exclude
	private MaterialInspection matInsp;
	
	private Float maxMesurment;
	private Float minMesurment;
	
	
	public InspectionActuals(InspectionLot inspectionLot, MaterialInspection matInsp) {
		
		this.inspectionLot = inspectionLot;
		this.matInsp = matInsp;
	}
	
	

}
