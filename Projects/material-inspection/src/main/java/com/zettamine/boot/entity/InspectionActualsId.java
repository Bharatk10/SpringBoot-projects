package com.zettamine.boot.entity;

import java.io.Serializable; 
import java.util.Objects;


public class InspectionActualsId implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private InspectionLot inspectionLot;
    private MaterialInspection matInsp;
	@Override
	public int hashCode() {
		return Objects.hash(inspectionLot, matInsp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InspectionActualsId other = (InspectionActualsId) obj;
		return Objects.equals(inspectionLot, other.inspectionLot) && Objects.equals(matInsp, other.matInsp);
	}
    
    

   
}
