package examples;

import parse.JsonParse;
import types.JsonArray;
import types.JsonMap;
import types.JsonObject;

public class Querying {

	public static void main(String[] args) {
		String jsonstr = "[ " + "{\"make\": \"Toyota\", \"model\": \"Corolla\", \"year\": 2018, \"price\": 15000}, "
				+ "{\"make\": \"Honda\", \"model\": \"Civic\", \"year\": 2020, \"price\": 20000}, "
				+ "{\"make\": \"Ford\", \"model\": \"Mustang\", \"year\": 2015, \"price\": 25000}, "
				+ "{\"make\": \"Tesla\", \"model\": \"Model S\", \"year\": 2021, \"price\": 50000} ]";
		JsonObject json = new JsonParse(jsonstr).get();
		System.out.println("input:  " + json.nice());
		// Find cars with price <= 25000.
		JsonArray result = new JsonArray();
		for (JsonObject o : ((JsonArray) json).value()) {
			if (o instanceof JsonMap) {
				Double price = (double) (o.get("price").value());
				if (price <= 25000) {
					result.add(o);
				}
			}
		}
		System.out.println("query result: " + result.nice());
	}

}
