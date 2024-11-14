package examples;

import parse.JsonParse;
import types.JsonObject;

public class ParsingJsonData {
	
	public static void main(String[] args) {
		// Ref: https://www.zyte.com/blog/json-parsing-with-python/
		String jsonString =
				"{\"numbers\": [1, 2, 3], \"car\": {\"model\": \"Model X\", \"year\": 2022}}";
		JsonObject json = new JsonParse(jsonString).get();
		System.out.println(json.nice());
		// Expect: {"car":{"model":"Model X","year":2022.0},"numbers":[1.0,2.0,3.0]}
		
		//Accessing JSON array elements using array indexing
		//print(json_data['numbers'][0]) # Output: 1
		System.out.println(json.get("numbers").get(0).value());
		// OR you can use the path.
		System.out.println(json.get("numbers.0").value());
		// Either way you should get 1.0 as output
		
		//Accessing JSON elements using keys
		System.out.println(json.get("car").get("model").value());
		// OR you can use the path.
		System.out.println(json.get("car.model").value());
		// Either way you should get Model X as output.
		
	}

}
