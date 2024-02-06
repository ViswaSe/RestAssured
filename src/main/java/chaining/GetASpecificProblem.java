package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetASpecificProblem extends BaseRequests{
	
	@Test(dependsOnMethods = {"chaining.CreateANewProblem.createNewProblem"})
	public void getSpecificProblem() { 
		RequestSpecification reqSpec = RestAssured.given().log().all()
									.accept(ContentType.JSON)
									.pathParam("sys_id", sys_id)
									.queryParam("sysparm_fields", "number,sys_id,short_description,description,category");
		
		Response response = reqSpec.get("problem/{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
	}
}
