package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	User userPayload;

	
	
	@Test(priority=1,dataProvider = "Data",dataProviderClass = DataProviders.class )
	void testPost(String Userid,String userName,String firstName,String lastName,String email,String password,String ph) {
		
		userPayload=new User();
		userPayload.setId(Integer.parseInt(Userid));
		userPayload.setUsername(userName);
		userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(ph);
		
	Response res=	UserEndpoints.createUser(userPayload);
	Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority=2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	
	void testGet(String userName) {	
		
		Response res=UserEndpoints.readUser(userName);
		
		Assert.assertEquals(res.getStatusCode(), 200);	
		
	}
	
	@Test(priority=3,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	
	void testDelete(String userName) {	
		
		Response res=UserEndpoints.deleteUser(userName);
		
		Assert.assertEquals(res.getStatusCode(), 200);	
		
	}

}
