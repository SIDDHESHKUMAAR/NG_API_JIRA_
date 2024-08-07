import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath;
public class dynamicJSON {
	@Test(dataProvider = "Booksname")
	public void addbook(String isbn,String asile) {
	RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json")
		.body(PayLoad.addbook(isbn,asile)).when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.println("ID:"+id);
	}
	@DataProvider(name="Booksname")
	public Object[][] getdata() {
		return new Object[][]{{"abcd","1234"},{"efgh","5678"},{"bncd","122312"}};
	}
	

}
