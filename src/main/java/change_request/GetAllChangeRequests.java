package change_request;

import java.util.List;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllChangeRequests {
	
	@Test
	public void getAllChangeRequests() { 
		//Step1: get the end point 
		RestAssured.baseURI = "https://dev56184.service-now.com/api/now/table/change_request";

		//Step2: add authentication
		String username = "admin";
		String password = "fu88K+AH^wyJ";
		RestAssured.authentication = RestAssured.basic(username, password);

		//Step3: add headers like accept, query param, pathparam etc.. 
		RequestSpecification reqSpecification = RestAssured.given()
				.accept(ContentType.JSON)
				.queryParam("sysparm_fields","number,sys_id,short_description,description,category,type");

		//Step4: Add the request type and get the response to print it in the console. 
		Response response = reqSpecification.get();
		
		//Step5: Adding an assertion of body request and status code. 
		
		response = response.then().assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.body("result.number", hasItem("CHG0040004"))
		.body("result.short_description",hasItem("Add network switch to cabinet"))
		.extract().response();
		
		response.prettyPrint();
		
		//Step6: Print the response status code and print the number of all the sys_ids
		System.out.println("Status Code: "+response.statusCode());
		JsonPath jsonPath = response.jsonPath();
		List<String> result_List = jsonPath.getList("result.number");

		for(int i=0;i<result_List.size();i++) { 
			System.out.println(result_List.get(i));
		}
	}
}
