package examples;

import parse.JsonParse;
import types.JsonObject;

public class ReadingJson {
	// Ref: https://www.zyte.com/blog/json-parsing-with-python/
	
	private static String inputJson 
		= "{ \"make\": \"Tesla\", \"model\": \"Model 3\", \"year\": 2022, \"color\": \"Red\" }";
	public static void main(String[]args) {	
		JsonObject json = new JsonParse(inputJson).get();
		System.out.println(json.nice());
		// Expected output:
		// {"color":"Red","make":"Tesla","model":"Model 3","year":2022}
	}

}
