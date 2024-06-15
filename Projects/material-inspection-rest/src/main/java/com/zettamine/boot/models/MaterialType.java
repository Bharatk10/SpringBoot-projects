package com.zettamine.boot.models;

public enum MaterialType {
    RAW_MATERIAL("RAW MATERIAL"),
    SEMI_FINISHED_GOODS("SEMI-FINISHED GOODS"),
    CONSUMABLE("CONSUMABLE");
    

	private final String materialType;
	

    MaterialType(String materialType) {
    	this.materialType=materialType;
	}
    
    public String getMaterialType() {
        return materialType;
    }
    
    public static MaterialType[] getAllMaterialTypes() {
        return values();
    }
}

