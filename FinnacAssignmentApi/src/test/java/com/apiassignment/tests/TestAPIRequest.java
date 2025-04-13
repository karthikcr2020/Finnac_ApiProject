package com.apiassignment.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class TestAPIRequest {
	String userId;
	
	@DisplayName("create the new user")
	@Test
	@Order(1)
	void createUser() {

		String payload = "{\"name\":\"johnny3\",\"job\":\"developer3\"}";
		
		userId = given()
					  .contentType(ContentType.JSON)
					  .accept(ContentType.JSON)
					  .body(payload)
		           .when() 
		              .post("https://reqres.in/api/users")
		              
		          .then()
	                 .statusCode(201)
		             .body("name", equalTo("johnny3"))
		             .body("job", equalTo("developer3"))
		             .log().all()
		             .extract().jsonPath().getString("id");
                 System.out.println("Created User ID: " + userId);
                 assertNotNull(userId, "User ID should not be null after creation");

		             
	}
	
	@DisplayName("Fetching the new user details and validating them ")
	@Test
	@Order(2)
	void getUser() {
		          given()
//		              .pathParam("userId", Id)
                   .when()
                     .get("https://reqres.in/api/users/"+userId)
         
                   .then()
                     .statusCode(200)
                     .body("data.name", equalTo("johnny3"))
                     .body("data.job", equalTo("developer3"))
		             .log().all();
		
	}
	
	 @DisplayName("updating the name and validating the name")
	 @Test
	 @Order(3)
	 void updateUser()
	  {
    	 String payload = "{\"name\":\"John\",\"job\":\"QArole\"}";
		       
    	             given()
				       
		              .contentType(ContentType.JSON)
		              .accept(ContentType.JSON)
		              .body(payload)
		 			.when()
		 			  .put("https://reqres.in/api/users/"+userId)
           		     
          
		 			.then()
		 			  .statusCode(200)
		 			  .body("name", equalTo("John"))
		 			  .body("job", equalTo("QArole"))
		              .log().all();
		              
		 
	  }
	 

}
