package com.zettamine.boot.models;


public enum MaterialType {
	
	RAW_MATERIAL("RAW MATERIAL"),
	SEMI_FINISHED_GOODS("SEMI FINISHED GOODS"),
	CONSUMABLE("CONSUMABLE");
	
	
	private final String materialTypeName;

	private MaterialType(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	
	 public String getMatrTypeName() {
	        return materialTypeName;
	    }
	    
	    public static MaterialType[] getAllMatrTypes() {
	        return values();
	    }



}
