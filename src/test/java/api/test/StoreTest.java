package api.test;

import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndPoints;
import api.payload.Pet;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {
	Faker faker;
	ObjectMapper mapper;
	Store storePayLoad;
	Pet petPayLoad;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		storePayLoad = new Store();
		petPayLoad = new Pet();
		mapper = new ObjectMapper();
		
		storePayLoad.setId(faker.idNumber().hashCode());
		storePayLoad.setPetId(petPayLoad.getId());
		storePayLoad.setQuantity(1);
		storePayLoad.setShipDate(TimeZone.getTimeZone("UTC"));
		storePayLoad.setStatus("Placed");
		storePayLoad.setComplete(true);
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debugging.....");
		
		
	}
	
	@Test(priority = 1)
	public void testPostOrder() {
		logger.info("*********** Creating Pet Order ************");
		Response response = StoreEndPoints.createOrder(storePayLoad);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		logger.info("*********** Pet Order is Created ************");
	}
	
	@Test(priority = 2)
	public void testGetOrderById() throws JsonMappingException, JsonProcessingException {
		logger.info("*********** Reading Pet Order ************");
		Response response = StoreEndPoints.readOrder(this.storePayLoad.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		String body = response.getBody().asString();
		Store responseOrder = mapper.readValue(body,Store.class);
		Assert.assertTrue(body.contains(this.storePayLoad.getStatus()));
		Assert.assertEquals(storePayLoad.getComplete(), responseOrder.getComplete());
		
		logger.info("*********** Pet Order displayed ************");
		
	}

	
	@Test(priority = 3)
	public void testDeleteOrder() {
		logger.info("*********** Deleting Pet Order ************");
		Response response = StoreEndPoints.deleteOrder(this.storePayLoad.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********** Pet Order Deleted ************");
	}
}
