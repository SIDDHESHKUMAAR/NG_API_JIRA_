import io.restassured.path.json.JsonPath;

public class reusableMethods {
	public static JsonPath rawtojson(String response) {
		JsonPath js2= new JsonPath(response);
		return js2;
	}

}
