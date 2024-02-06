package chaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequests {
	
	public static String sys_id="";
	public String username = "admin"; 
	public String password = "fu88K+AH^wyJ";
	
	@BeforeSuite
	public void startApp() { 
		RestAssured.baseURI = "https://dev56184.service-now.com/api/now/table";
		RestAssured.authentication = RestAssured.basic(username, password);
	}
}
