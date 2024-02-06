package change_request;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateChangeRequestUsingFile {
	
	@DataProvider(name = "fetchData")
	public String[] setData() { 
		String[] data = new String[1];
		data[0] = "./data/updatechangereq.json";
		return data;
	}

	@Test(dataProvider = "fetchData")
	public void updateChangeRequest(String filePath) { 
		RestAssured.baseURI = "https://dev66179.service-now.com/api/now/table/change_request";
		
		String username = "admin";
		String password = "1bE9K*MOxnp%";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.queryParam("sysparm_fields", "number,short_description,description,sys_id,category,type")
										.pathParam("sys_id", "2756f8a347fb7910c2973714846d4361")
										.accept(ContentType.JSON)
										.contentType(ContentType.JSON)
										.body(new File(filePath));
		
		Response response = reqSpec.patch("{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
		
		JsonPath jsonPath = response.jsonPath();
		String short_description = jsonPath.get("result.short_description");
		System.out.println(short_description);
	}
}
