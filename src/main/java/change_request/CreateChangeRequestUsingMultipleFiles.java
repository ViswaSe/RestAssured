package change_request;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateChangeRequestUsingMultipleFiles {
	
	@DataProvider(name="fetchData")
	public String[] getFileData() { 
		String[] data = new String[2];
		data[0] = "./data/createchangereq1.json";
		data[1] = "./data/createchangereq2.json";
		return data;
	}

	@Test(dataProvider = "fetchData")
	public void createChangeRequestUsingFile(String filePath) { 
		RestAssured.baseURI = "https://dev66179.service-now.com/api/now/table/change_request";
		
		String username = "admin";
		String password = "1bE9K*MOxnp%";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
		.queryParam("sysparm_fields", "number,sys_id,category,type,description,short_description")
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(new File(filePath));
		
		Response response = reqSpec.post();
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		String description = jsonPath.getString("result.description");
		System.out.println(number);
		System.out.println(description);
		
	}
}
