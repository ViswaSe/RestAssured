package chaining;

import java.io.File;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateANewProblem extends BaseRequests {
	
	@DataProvider(name="fetchData")
	public String[] getData() { 
		String[] data = new String[2];
		
		data[0] = "./data/CreateProblem1.json";
		data[1] = "./data/CreateProblem2.json";
		
		return data;
	}
	
	@Test(dataProvider = "fetchData")
	public void createNewProblem(String filePath) { 
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.queryParam("sysparm_fields", "number,short_description,description,category,sys_id")
										.accept(ContentType.JSON)
										.contentType(ContentType.JSON)
										.body(new File(filePath));
		
		Response response = reqSpec.post("problem");
		
		response = response.then().assertThat()
		.statusCode(201)
		.body("result.short_description",containsString("POST calls"))
		.body("result.category",equalToIgnoringCase("Software"))
		.contentType(ContentType.JSON).extract().response();
		
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
		String short_desc = jsonPath.get("result.short_description");
		System.out.println(short_desc);
	
		sys_id = jsonPath.get("result.sys_id");
	}

}
