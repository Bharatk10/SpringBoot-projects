package com.zettamine.boot.models;

import java.sql.Date;


import lombok.Data;

@Data
public class InspLotDisplay {
	
	private Integer lotId;
    private String vendorName;
    private String plantName;
    private String materialName;
    private Date createdOn;
	private String startDate;
	private String endDate;
	private String result;
	private String remarks;
	private String userName;
	 

}
