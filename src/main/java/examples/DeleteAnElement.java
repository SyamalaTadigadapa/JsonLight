package examples;

import parse.JsonParse;
import types.JsonMap;
import types.JsonObject;
import types.JsonString;

public class DeleteAnElement {
	
	public static void main(String[] args) {
		String jsonstr = "{\"model\": \"Model X\", \"year\": 2022}";
		JsonObject json = new JsonParse(jsonstr).get();
		System.out.println("before:  "+ json.nice());
		if (json instanceof JsonMap) {
			// Beware of removing a non existing key which will be silently ignored.
			((JsonMap)json).delete(new JsonString("year"));
		}
		System.out.println("after:  "+ json.nice());
	}

}
