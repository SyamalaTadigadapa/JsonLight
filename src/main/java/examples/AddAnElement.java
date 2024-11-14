package examples;

import parse.JsonParse;
import types.JsonMap;
import types.JsonObject;
import types.JsonString;

public class AddAnElement {
	
	public static void main(String[] args) {
		String jsonString = "{\"model\": \"Model X\", \"year\": 2022}";
		JsonObject json = new JsonParse(jsonString).get();
		System.out.println("before:  "+ json.nice());
		if (json instanceof JsonMap) {
			((JsonMap)json).add(new JsonString("color"), (JsonObject)(new JsonString("red")));
		}
		System.out.println("after:   " + json.nice());
	}

}
