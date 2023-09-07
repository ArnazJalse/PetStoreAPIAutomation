package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
	public static Response createOrder (Store payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url2);
		
		return response;
	}
	
	public static Response readOrder(int id) {
		Response response = given()
			.pathParam("id", id)
		.when()
			.get(Routes.get_url2);
		
		return response;
	}
	
	
	public static Response deleteOrder(int id) {
		Response response = given()
			.pathParam("id", id)
		.when()
			.delete(Routes.delete_url2);
		
		return response;
	}
}
