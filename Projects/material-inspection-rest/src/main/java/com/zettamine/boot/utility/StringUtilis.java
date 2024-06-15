package com.zettamine.boot.utility;

public class StringUtilis {
	
	 public static String processString(String input) {
	        
	        String processedString = input.toUpperCase().trim();
	        processedString = processedString.replaceAll("\\s+", "");
	        return processedString;
	    }
	 
	 public static String processSentance(String input) {
	        
	        String processedString = input.toUpperCase().trim();
	        processedString = processedString.replaceAll("\\s+",  " ");
	        return processedString;
	    }
	 public static String processState(String input) {
	        
	        String processedString = input.toUpperCase().trim();
	        processedString = processedString.replaceAll("_", " ");
	        return processedString;
	        
	    }

}
