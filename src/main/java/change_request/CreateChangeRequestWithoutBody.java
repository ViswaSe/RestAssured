package change_request;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateChangeRequestWithoutBody { 
	
	@Test
	public void createChangeRequest() { 
		RestAssured.baseURI = "https://dev66179.service-now.com/api/now/table/change_request";
		
		String username = "admin";
		String password = "1bE9K*MOxnp%";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpecification = RestAssured.given()
		.log().all()
		.queryParam("sysparm_fields", "number,sys_id,category,type,description,short_description")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON);
		
		Response response = reqSpecification.post();
		response.prettyPrint();
	}

}