package problem;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetASpecificProblem {
	
	@Test
	public void getSpecificProblem() { 
		RestAssured.baseURI = "https://dev197796.service-now.com/api/now/table";
		
		String username = "admin";
		String password = "KA+qoSE5o+8m";
		RestAssured.authentication = RestAssured.basic(username, password);
	
		RequestSpecification reqSpec = RestAssured.given().log().all()
									.accept(ContentType.JSON)
									.pathParam("sys_id", "3e80c3a99348021015e2376efaba1069")
									.queryParam("sysparm_fields", "number,sys_id,short_description,description,category");
		
		Response response = reqSpec.get("problem/{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
	}
}
