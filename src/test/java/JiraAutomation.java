import io.restassured.RestAssured;import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;
public class JiraAutomation {
	public static void main(String[] args) {		// TODO Auto-generated method stub
		RestAssured.baseURI="https://newgensoft-team-siddhesh.atlassian.net";	
		String createIssueResponse 	= given()		
				.header("Content-Type","application/json")		
				.header("Authorization","Basic c3Nhc2lrdW1hckBuZXdnZW5zb2Z0LmNvbTpBVEFUVDN4RmZHRjBZZnI1TVFWelJuOUpZN3hQM0xUWXZ4VGs2cWVYcl9DeHp0MkFaaVo0XzNrYllYMEJxbjJ2Zy1kZktnUEVrbkRoSWNqU01ZQ2o0UE5kWHo4eFVoOHBLMlFNSjdnUWk2LUdNVEFxRnk2LTdSLWQtUkNCa09UWW1vM3BsbVlWZkZRbEJra1ZDLVFUbGhtNGtMNXF3dklKRnM2cHNDSDhlR0N1NFdrRTFRZGIwODA9NEFEMTg0REUK")		
				.body("{\n"				+ "    \"fields\": {\n"				+ "       \"project\":\n"				+ "       {\n"				+ "          \"key\": \"SCRUM\"\n"				+ "       },\n"				+ "       \"summary\": \"Website items are not working- automation Rest Assured\",\n"				+ "       \"issuetype\": {\n"				+ "          \"name\": \"Bug\"\n"				+ "       }\n"				+ "   }\n"				+ "}")		
				.log().all()		
				.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).contentType("application/json")		
				.extract().response().asString();		 		 
		JsonPath js = new JsonPath(createIssueResponse);		 
		String issueId = js.getString("id");		
		System.out.println(issueId);		 		
		given()			
		.pathParam("key", issueId)		
		.header("X-Atlassian-Token","no-check")		
		.header("Authorization","Basic c3Nhc2lrdW1hckBuZXdnZW5zb2Z0LmNvbTpBVEFUVDN4RmZHRjBZZnI1TVFWelJuOUpZN3hQM0xUWXZ4VGs2cWVYcl9DeHp0MkFaaVo0XzNrYllYMEJxbjJ2Zy1kZktnUEVrbkRoSWNqU01ZQ2o0UE5kWHo4eFVoOHBLMlFNSjdnUWk2LUdNVEFxRnk2LTdSLWQtUkNCa09UWW1vM3BsbVlWZkZRbEJra1ZDLVFUbGhtNGtMNXF3dklKRnM2cHNDSDhlR0N1NFdrRTFRZGIwODA9NEFEMTg0REUK")			
		.multiPart("file",new File("/Users/rahulshetty/Downloads/IMG_0926.jpeg")).log().all()			
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);			
	}
	
}
