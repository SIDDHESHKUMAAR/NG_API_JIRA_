import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.JsonPath;
@Test

public class jsonParsing {
	public static void parsing() {
	JsonPath j1= new JsonPath(PayLoad.jsonParsin());
	///to count number of courser
int count=	j1.getInt("courses.size()");
System.out.println("Count is:"+count);
//Print Purchase amount:
int purchaseamount=j1.getInt("dashboard.purchaseAmount");
System.out.println("purchaseamount ="+ purchaseamount);
//print title of the first course:
String titlename=j1.getString("courses[0].title");
System.out.println("Name:"+titlename);
 
int prices;
for(int i=0;i<count;i++) {
	System.out.println(j1.getString("courses["+i+"].title"));
	
	prices=j1.getInt("courses["+i+"].price");
	System.out.println(prices);
	
}
///Print Number of copy by RPA:
for(int i=0;i<count;i++) {
	String coursetitle=j1.getString("courses["+i+"].title");
	
	if(coursetitle.equalsIgnoreCase("RPA")) {
		System.out.println(j1.get("courses["+i+"].copies"));
		break;
	}
	
}
//sum of all course:
int sum=0;
for(int i=0;i<count;i++) {
	int copies =j1.get("courses["+i+"].copies");
	int priceof=j1.get("courses["+i+"].price");
	int amount=copies*priceof;
	sum=sum+amount;
	
	
}
System.out.println("sum:"+sum);
Assert.assertEquals(sum,purchaseamount);
	
	}
	
}
