package problem;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteSpecificProblem {
	
	@Test
	public void deleteSpecificProblem() { 
		RestAssured.baseURI = "https://dev197796.service-now.com/api/now/table";
		
		String username = "admin";
		String password = "KA+qoSE5o+8m";
		RestAssured.authentication = RestAssured.basic(username, password);
		
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.pathParam("sys_id", "3e80c3a99348021015e2376efaba1069")
										.accept(ContentType.JSON);
		
		Response response = reqSpec.delete("problem/{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
