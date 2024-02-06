package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteSpecificProblem extends BaseRequests {
	
	@Test(dependsOnMethods = {"chaining.UpdateSpecificProblem.updateSpecificProblem"})
	public void deleteSpecificProblem() { 
		RequestSpecification reqSpec = RestAssured.given().log().all()
										.pathParam("sys_id", sys_id)
										.accept(ContentType.JSON);
		
		Response response = reqSpec.delete("problem/{sys_id}");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
