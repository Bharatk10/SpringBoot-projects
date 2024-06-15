package com.zettamine.boot;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaterialInspectionApplication {
	public static void main(String[] args) {
	SpringApplication.run(MaterialInspectionApplication.class, args);
		openBrowser("http://localhost:8080");

	}
	
	  private static void openBrowser(String url) { try {
	  Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+url);
	  }catch(Exception e) { e.printStackTrace(); } }
	 

}
