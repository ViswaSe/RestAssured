package change_request;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteChangeRequest {

	@Test
	public void deleteChangeRequest() { 
		RestAssured.baseURI = "https://dev66179.service-now.com/api/now/table/change_request";
		
		String username = "admin";
		String password = "1bE9K*MOxnp%";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.pathParam("sys_id", "5d77b8da47410200e90d87e8dee49040");
		
		Response response = reqSpec.delete("{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
				
	}
}
