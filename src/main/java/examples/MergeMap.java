package examples;

import parse.JsonParse;
import types.JsonObject;
import validator.JsonModel;

public class MergeMap {
	public static void main(String[] args) throws Exception{
		
	String jsonstr1 = "{\n"
			+ "        \"age\": 30,\n"
			+ "        \"gender\": \"F\",\n"
			+ "        \"member\": true,\n"
			+ "        \"name\": \"Rani\"\n"
			+ "    }";
	
	String jsonstr2 = "{\n"
			+ "        \"age\": 20,\n"
			+ "        \"gender\": \"M\",\n"
			+ "        \"member\": true,\n"
			+ "        \"city\": \"Hyderabad\",\n"
			+ "        \"name\": \"subba rao\"\n"
			+ "    }";
	
	JsonObject json1 = new JsonParse(jsonstr1).get();
	JsonObject json2 = new JsonParse(jsonstr2).get();	
	
	System.out.println("before:  "+ json1.nice());	
	json1.add(json2);
	System.out.println("after:  "+ json1.nice());
	System.out.println("Done");
	}
}
