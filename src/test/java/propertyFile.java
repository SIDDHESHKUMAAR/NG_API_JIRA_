import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class propertyFile {
	
	
	@Test(priority=1)

	void postMethod() throws IOException {
		String placeid;
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	String Response=given().log().all().queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\ssasikumar\\Desktop\\Rest API -Udemy\\PropertJson.txt"))))
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)"
).extract().response().asString();
	//System.out.println(Response);
	
	JsonPath js=reusableMethods.rawtojson(Response);
	 placeid=js.getString("place_id");
	System.out.println("plac id:::"+placeid);
	String newaddress="Chennai Tamil Nadu India";
	}
	
}
