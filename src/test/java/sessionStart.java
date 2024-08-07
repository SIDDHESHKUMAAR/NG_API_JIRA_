import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class sessionStart {
	String placeid;
	@Test(priority=1)

	void postMethod() {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	String Response=given().log().all().queryParam("key","qaclick123")
	.header("Content-Type","application/json")
	.body(PayLoad.response())
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)"
).extract().response().asString();
	//System.out.println(Response);
	
	JsonPath js=reusableMethods.rawtojson(Response);
	 placeid=js.getString("place_id");
	System.out.println("plac id:::"+placeid);
	String newaddress="Chennai Tamil Nadu India";
	
	
	
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json\r\n"
				+ "")
		.then().assertThat().statusCode(200).log().all().body("msg",equalTo("Address successfully updated"));
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String Response2=given().log().all().queryParam("key","qaclick123")
		.queryParam("place_id",placeid)
		.when().get("/maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js2= new JsonPath(Response2);
		String newaddresss=js2.get("address");
		System.out.println(newaddresss);
		Assert.assertEquals("newaddress","newaddresss");
		
	
	}
}
