package problem;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateANewProblem {
	
	@DataProvider(name="fetchData")
	public String[] getData() { 
		String[] data = new String[2];
		
		data[0] = "./data/CreateProblem1.json";
		data[1] = "./data/CreateProblem2.json";
		
		return data;
	}
	
	@Test(dataProvider = "fetchData")
	public void createNewProblem(String filePath) { 
		RestAssured.baseURI = "https://dev197796.service-now.com/api/now/table/";

		String username = "admin";
		String password = "KA+qoSE5o+8m";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.queryParam("sysparm_fields", "number,short_description,description,category,sys_id")
										.accept(ContentType.JSON)
										.contentType(ContentType.JSON)
										.body(new File(filePath));
		
		Response response = reqSpec.post("problem");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
		String short_desc = jsonPath.get("result.short_description");
		System.out.println(short_desc);
	}

}
