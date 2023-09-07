package api.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetTest {
	Pet pet;
	Pet updatePet;
	ObjectMapper mapper;
	public Logger logger;
	@BeforeClass
	public  void setupDdata() throws FileNotFoundException, IOException, ParseException {

		mapper = new ObjectMapper();
		pet = mapper.readValue(new File("pet.json"), Pet.class);
		System.out.println(pet.id);
		
		updatePet = mapper.readValue(new File("petUpdate.json"), Pet.class);
		System.out.println(updatePet.name);
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debugging.....");
		
	}
	
	@Test(priority = 1)
	public void testPostPet() {
		logger.info("*********** Creating Pet Info ************");
		Response response = PetEndPoints.createPet(pet);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		
		logger.info("*********** Pet Info is Created ************");
	}
	
	@Test(priority = 2)
	public void testGetPetByID() throws JsonMappingException, JsonProcessingException {
		logger.info("*********** Reading Pet Info ************");
		
		Response response = PetEndPoints.readPet(this.pet.id);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		String body = response.getBody().asString();
		Pet responsePet = mapper.readValue(body,Pet.class);
		Assert.assertTrue(body.contains(this.pet.getName()));
		Assert.assertEquals(pet.getPhotoUrls(), responsePet.getPhotoUrls());
		
		logger.info("*********** Pet info is displayed ************");
	}
	
	@Test(priority = 3)
	public void testUpdatePet() {
		logger.info("*********** Updating Pet Info ************");
		Response response = PetEndPoints.updatePet(updatePet);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		Assert .assertNotEquals(pet.getName(), updatePet.getName());
		Assert.assertNotEquals(pet.getStatus(), updatePet.getStatus());
		logger.info("*********** Pet Info is Updated ************");
	}
	
	@Test(priority = 4)
	public void testDeletePet() {
		logger.info("*********** Deleting Pet info ************");
		Response response = PetEndPoints.deletePet(this.pet.id);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** Pet Info is Deleted ************");
		
	}

	
}
