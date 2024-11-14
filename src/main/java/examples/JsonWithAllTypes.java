package examples;

import parse.JsonParse;
import types.JsonObject;

public class JsonWithAllTypes {
	// Using strings, numbers, booleans, null scalars and
	// maps and arrays as well.
	
	public static void main(String[] args) {
		String jsonstr = "{\n"
				+ "  \"name\": \"John\",\n"
				+ "  \"age\": 30,\n"
				+ "  \"married\": true,\n"
				+ "  \"divorced\": false,\n"
				+ "  \"children\": [\"Ann\",\"Billy\"],\n"
				+ "  \"pets\": null,\n"
				+ "  \"cars\": [\n"
				+ "    {\"model\": \"BMW 230\", \"mpg\": 27.5},\n"
				+ "    {\"model\": \"Ford Edge\", \"mpg\": 24.1}\n"
				+ "  ]\n"
				+ "}";		
		System.out.println("data: " + jsonstr);
		JsonObject json = new JsonParse(jsonstr).get();
		System.out.println(json.nice());
	}

}
