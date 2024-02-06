package change_request;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetASpecificChangeRequest_XML {
	
	@Test()
	public void getASpecificChangeRequest() { 
		RestAssured.baseURI = "https://dev66179.service-now.com/api/now/table/change_request/eaf5d21347c12200e0ef563dbb9a7109";
		String username = "admin"; String password = "1bE9K*MOxnp%";
		RestAssured.authentication = RestAssured.basic(username, password);
		RequestSpecification reqSpecification = RestAssured.given()
										.accept(ContentType.XML)
										.queryParam("sysparm_fields", "number,sys_id,short_description,description,type,category");
		Response response = reqSpecification.get();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		XmlPath xmlPath = response.xmlPath();
		String number = xmlPath.get("response.result.number");
		System.out.println(number);
	}

}
