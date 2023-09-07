package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {
	Faker faker;
	User userPayload;
	ObjectMapper mapper;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		mapper = new ObjectMapper();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		logger.debug("Debugging.....");
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("*********** Creating User ************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		
		logger.info("*********** User is created **********");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws JsonMappingException, JsonProcessingException {
		logger.info("*********** Reading User info ************");
		//JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchema("D:\\test\\ArnazWork\\PetStoreAutomation\\src\\test\\resources\\UserJSONSchema.json");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		//response.then().assertThat().body(validator);
		Assert.assertEquals(response.getStatusCode(), 200);
		String body = response.getBody().asString();
		User responseUser = mapper.readValue(body,User.class);
		Assert.assertTrue(body.contains(this.userPayload.getUsername()));
		Assert.assertEquals(userPayload.getEmail(), responseUser.getEmail());
		
		logger.info("*********** User info is displayed ************");
		
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		
		logger.info("*********** Updating User ************");
		
		//Update the data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User is Updated ************");
		
		//Checking data AFter update
		Response responseAfetrUpdation = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfetrUpdation.then().log().all();
		
		Assert.assertEquals(responseAfetrUpdation.getStatusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("*********** Deleting User ************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** User Deleted************");
	}
	
	
	
}
