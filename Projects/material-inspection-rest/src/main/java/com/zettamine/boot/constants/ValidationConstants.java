package com.zettamine.boot.constants;

public interface ValidationConstants {

	public static final String BLANK_ERROR_MESSAGE ="The field is mandatory. Please enter the required information.";
	
	public static final String SAVE_ID_ERROR ="The Id is Auto genertaed.Please exclude the id.";
	
	public static final String UPDATE_ID_ERROR ="The Id cannot be updated.Please pass the id.";
	
	public static final String EMAIL_ERROR="Please Enter Valid Email.";
	
	public static final String NAME_ERROR="Please Enter Valid Name.(Only characters and . are allowed)";
	
	public static final String VENDOR_ID_ERROR="Please Enter Valid Vendor Id.(Only numbers are allowed)";
	
	public static final String CHANNEL_ID_ERROR="Please Enter Valid Channel Id.(Only numbers are allowed)";
	
	public static final String LOT_ID_ERROR="Please Enter Valid Inspection Lot Id.(Only numbers are allowed)";
	
	public static final String PLANT_ID_ERROR="Please Enter Valid Plant Id.Only characters and one -(mandatory) are allowed (eg:ABC-Q***)";
	
	public static final String MATERIAL_ID_ERROR="Please Enter Valid Material Id.(eg:M-12...)";
	
	public static final String TOLLERANCE_ERROR="Please Enter Valid Tollerance Level.(eg:12.45,89.12,4,23)";
	
	public static final String TOLLERANCE_VALUE_ERROR= "Tolerance must be a number greater than or equal to 0.1";
	
	public static final String STATE_ERROR="Please Enter Valid State.";
	
	public static final String LOCATION_ERROR="Please Enter Valid Location.";
	
	public static final String PLANT_ERROR="Please pass the Plant object.";
	
	public static final String PLANT_SAVE_ERROR="the Plant Details Save Failed.";
	
	public static final String MATERIAL_ERROR="Please pass the Material object.";
	
	public static final String MAT_ID_ERROR="There is no Material associated with the Material Id: ";
	
	public static final String MATCH_ID_ERROR="There is no Material Characteristics associated with the Channel Id: ";
	
	public static final String MATER_ID_ERROR="Please pass the Material Id.It is mandatory field";
	
	public static final String CHAN_ID_ERROR="Please pass the Channel Id.It is mandatory field";
	
	public static final String MATERIAL_SAVE_ERROR="The Material Details Save Failed.";
	
	public static final String MATERIAL_CHARACTERS_SAVE_ERROR="The Material Characters Details Save Failed.";
	
	public static final String MATERIAL_UPDATE_ERROR="The Material Details Update Failed.";
	
	public static final String VENDOR_NOT_PRESENT_ID_ERROR = "No Vendor Details present with Plant Id: ";
	
	public static final String PLANT_NOT_PRESENT_ID_ERROR = "No Plant Details present with Plant Id: ";
	
	public static final String LOT_NOT_PRESENT_ID_ERROR = "No Inspection Lot Details present with Lot Id: ";
	
	public static final String LOT_INSP_STARTED = "The Inspection already started for Lot Id: ";
	
	public static final String LOT_INSP_NOT_STARTED = "The Inspection not started for Lot Id: ";
	
	public static final String VENDOR_LIST_MESSAGE ="No Vendor Details are present";
	
	public static final String PLANT_LIST_MESSAGE ="No Plant Details are present";
	
	public static final String MATERIAL_LIST_MESSAGE ="No Material Details are present";
	
	public static final String INSP_LOTS_LIST_MESSAGE ="No Inspection Lots are present for the given criteria.";
	
	public static final String INSP_APPROVE_ERROR ="The Inspection Lot is not eligible for Approval.";
	
	public static final String INSP_LOT_LIST_START_DATE_MESSAGE ="There are no Inspection Lots to start the inspection process";
	
	public static final String INSP_LOT_MATR_CHART_ERROR ="For The particular material there were no Characteristics added.Please add the material characteristics for the Material ";
	
	public static final String INSP_COMPLETED_ERROR ="the Inspection has completed for the entered lot Id %s.So You can edit the Inspection Actuals";
	
	public static final String START_DATE_BEFORE_ERROR = "The Inspection Start Date should be after lot Creation Date (Created on  ";
	
	public static final String START_DATE_TODAY_ERROR = "The Inspection Start Date should be Today or previous day but not upcoming days";
	
	
	
	public static final String CHANNEL_NAME_ERROR="Only characters and spaces are allowed";
	
	public static final String STRING_ERROR="Only characters and spaces are allowed";
	
	public static final String RESULT_ERROR="Only characters are allowed";
	
	public static final String RESULT_VALUE_ERROR="Only (PASS or FAIL) are allowed.Please try again.";
	
	public static final String ACTUALS_ERROR="All the Inspection Actuals are added for the LotId: ";
	
	public static final String ACTUALS_SAVE_ERROR="The Inspection Actual added failed for the LotId: ";
	
	public static final String CHANNEL_NAME_NOT_ERROR="The Channel Description you entered is not valid to the Material ";
	
	public static final String ACTUALS_ALREADY_ERROR=" the Inspection Actual are already added for the LotId: ";
	
	public static final String VIEW_ACTUALS_ERROR = "The inspection actuals for LotId %s are not available.";
	
	public static final String UPDATE_ACTUALS_ERROR = "The inspection actuals updation failed.";
	
	public static final String DATE_ERROR="Please Enter Valid Date.(eg:dd-mm-yyyy-->04-10-2000)";
	
	public static final String DATE_RANGE_ERROR="The difference between from date and to date is less than or equal to 90 days.";
	
	public static final String EMAIL_PATTERN="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	public static final String NAME_PATTERN="^[a-zA-Z\\s]+\\.?[a-zA-Z\\s]*$";
	
	public static final String VENDOR_ID_PATTERN = "^[0-9]*$";
	
	public static final String CHANNEL_ID_PATTERN = "^[0-9]*$";
	
	public static final String LOT_ID_PATTERN = "^[0-9]*$";
	
	public static final String PLANT_ID_PATTERN = "^[a-zA-Z]{3}-[a-zA-Z]{1,}$";
	
	public static final String MATERIAL_ID_PATTERN = "^[mM]-((0[1-9])|([1-9][0-9]+))$";
	
	public static final String TOLLERANCE_PATTERN = "^\\d*\\.?\\d+$";
	
	public static final String LOCATION_PATTERN="^[a-zA-Z]*$";
	
	public static final String RESULT_PATTERN="^[a-zA-Z]*$";
	
	public static final String STRING_PATTERN ="^[a-zA-Z ]*$";
	
	public static final String CHANNEL_NAME_PATTERN="^[a-zA-Z ]*$";
	
	public static final String DATE_PATTERN="^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$";
	
	
}
