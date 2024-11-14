package examples;

import parse.JsonParse;
import types.JsonMap;
import types.JsonNumber;
import types.JsonObject;
import types.JsonString;

public class UpdateAnElement {

	public static void main(String[] args) {
		String jsonstr = "{\"model\": \"Model X\", \"year\": 2022}";
		JsonObject json = new JsonParse(jsonstr).get();
		System.out.println("before:  " + json.nice());
		if (json instanceof JsonMap) {
			// Beware of updating an non existing key which creates a new entry!
			((JsonMap) json).add(new JsonString("year"), new JsonNumber(2023));
		}
		System.out.println("after:   " + json.nice());
	}

}
