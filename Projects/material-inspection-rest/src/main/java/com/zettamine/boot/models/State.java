package com.zettamine.boot.models;

public enum State {
	
	
	ANDHRA_PRADESH("ANDHRA PRADESH"),
    ARUNACHAL_PRADESH("ARUNACHAL PRADESH"),
    ASSAM("ASSAM"),
    BIHAR("BIHAR"),
    CHHATTISGARH("CHHATTISGARH"),
    GOA("GOA"),
    GUJARAT("GUJARAT"),
    HARYANA("HARYANA"),
    HIMACHAL_PRADESH("HIMACHAL PRADESH"),
    JHARKHAND("JHARKHAND"),
    KARNATAKA("KARNATAKA"),
    KERALA("KERALA"),
    MADHYA_PRADESH("MADHYA PRADESH"),
    MAHARASHTRA("MAHARASHTRA"),
    MANIPUR("MANIPUR"),
    MEGHALAYA("MEGHALAYA"),
    MIZORAM("MIZORAM"),
    NAGALAND("NAGALAND"),
    ODISHA("ODISHA"),
    PUNJAB("PUNJAB"),
    RAJASTHAN("RAJASTHAN"),
    SIKKIM("SIKKIM"),
    TAMIL_NADU("TAMIL NADU"),
    TELANGANA("TELANGANA"),
    TRIPURA("TRIPURA"),
    UTTARAKHAND("UTTARAKHAND"),
    UTTAR_PRADESH("UTTAR PRADESH"),
    WEST_BENGAL("WEST BENGAL");

   

    private final String stateName;

    State(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }
    
    public static State[] getAllStates() {
        return values();
    }
    
   

   
}