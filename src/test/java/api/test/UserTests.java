package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;


public class UserTests {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(8,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger= LogManager.getLogger(this.getClass());
	
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("*********Create User test ********");
	Response response=	UserEndpoints.createUser(userPayload);
	response.then().log().all();
		
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("********* User is created ********");
	}
	
	
	@Test(priority=2)
	public void testGetUser() {
		logger.info("*********Read User details ********");
	Response response=	UserEndpoints.readUser(this.userPayload.getUsername());
	response.then().log().all();
		
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("********* User details are displayed ********");
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		logger.info("*********Update  User details ********");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
	Response response=	UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
	response.then().log().body();		
	Assert.assertEquals(response.getStatusCode(), 200);
	
	//Verify after update
	Response responseAfterUpdate=	UserEndpoints.readUser(this.userPayload.getUsername());	
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	logger.info("********* User details are updated ********");
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		logger.info("*********Delete  User details ********");
	Response response=	UserEndpoints.deleteUser(this.userPayload.getUsername());
	response.then().log().all();
		
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("********* User details deleted********");
	}

}
