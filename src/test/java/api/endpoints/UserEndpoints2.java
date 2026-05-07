package api.endpoints;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	public static Properties prop;
	
	static{

		try {
			prop=new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static Response createUser(User payload) {
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(prop.getProperty("post_url"));
		
		return response;
		
	}
	
	public static Response readUser(String userName) {
		
		Response response=given()
				.pathParam("username", userName)
			
		.when()
		.get(prop.getProperty("get_url"));
		
		return response;
		
	}

	public static Response updateUser(String userName,User payload) {
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(prop.getProperty("update_url"));
		
		return response;
		
	}
	
	public static Response deleteUser(String userName) {
		
		Response response=given()
				.pathParam("username", userName)
			
		.when()
		.delete(prop.getProperty("delete_url"));
		
		return response;
		
	}
}
