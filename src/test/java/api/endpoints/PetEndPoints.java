package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	public static Response createPet (Pet payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url1);
		
		return response;
	}
	
	public static Response readPet(int id) {
		Response response = given()
			.pathParam("id", id)
		.when()
			.get(Routes.get_url1);
		
		return response;
	}
	
	public static Response updatePet( Pet payload) {
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.put(Routes.update_url1);
			
		return response;
	}
	
	public static Response deletePet(int id) {
		Response response = given()
			.pathParam("id", id)
		.when()
			.delete(Routes.delete_url1);
		return response;
	}
}
