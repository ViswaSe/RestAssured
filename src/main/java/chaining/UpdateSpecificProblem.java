package chaining;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateSpecificProblem extends BaseRequests {
	
	@DataProvider(name = "fetchData")
	public String[] setData() { 
		String[] data = new String[1];
		data[0] = "./data/UpdateProblem.json";
		return data;
	}
	
	@Test(dataProvider="fetchData",dependsOnMethods= {"chaining.GetASpecificProblem.getSpecificProblem"})
	public void updateSpecificProblem(String filePath)	{ 
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.accept(ContentType.JSON)
										.contentType(ContentType.JSON)
										.queryParam("sysparm_fields","number,sys_id,category,short_description,description")
										.pathParam("sys_id", sys_id)
										.body(new File(filePath));
		
		Response response = reqSpec.patch("problem/{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
		String short_description = jsonPath.get("result.short_description");
		System.out.println(short_description);
	}
}
