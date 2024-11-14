package examples;

import parse.JsonParse;
import types.JsonArray;
import types.JsonMap;
import types.JsonObject;
import types.JsonString;

public class MapsAndArrays {
	
	public static void main(String[] args) {
		String jsonstr = "[\n"
				+ "    {\n"
				+ "        \"name\": \"subba rao\",\n"
				+ "        \"age\": 30,\n"
				+ "        \"gender\": \"male\",\n"
				+ "        \"member\": true\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"name\": \"lakshmi\",\n"
				+ "        \"gender\": \"female\",\n"
				+ "        \"member\": true,\n"
				+ "        \"age\": 25\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"name\": \"venkat nakka\",\n"
				+ "        \"member\": false,\n"
				+ "        \"age\": 50,\n"
				+ "        \"gender\": \"male\"\n"
				+ "    }\n"
				+ "]";
		
		JsonObject json = new JsonParse(jsonstr).get();
		// Print the json object. 
		// Notice that the keys are stored in sorted order. 
		System.out.println("input json is " + json.nice());
		for(JsonObject o: ((JsonArray)json).value()) {
			JsonMap m = ((JsonMap)o);
			for(JsonString key: m.keySet()) {
				System.out.print(key.value() + ":" + m.get(key).value()+" ");
			}
			System.out.println();
		}
		// Expect output:
		// age:30.0 gender:male member:true name:subba rao 
		// age:25.0 gender:female member:true name:lakshmi 
		// age:50.0 gender:male member:false name:venkat nakka 
		
		// Access an item directly using a path string.
		System.out.println("Name of the person at 3nd element: " + json.get("2.name").value());

	}

}
