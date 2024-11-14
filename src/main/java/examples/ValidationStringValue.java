package examples;

import parse.JsonParse;
import types.JsonObject;
import validator.JsonModel;

public class ValidationStringValue {
	public static void main(String[] args) throws Exception{
		
	String jsonstr = "{\n"
			+ "        \"age\": 30,\n"
			+ "        \"gender\": \"M\",\n"
			+ "        \"member\": true,\n"
			+ "        \"name\": \"subba rao\"\n"
			+ "    }";
	
	String jsonModel = "{\n"
			+ "        \"age\": 1,\n"
			+ "        \"gender\": \"[M|F]\",\n"
			+ "        \"member\": true,\n"
			+ "        \"name\": \"\"\n"
			+ "    }";
	
	
	JsonObject model = new JsonParse(jsonModel).get();
	JsonObject json = new JsonParse(jsonstr).get();
	
	new JsonModel(model).validate(json);
	// If you use anything other than "M" or "F" for gender value,
	// You should get 
	// ValidationException: Failed at "gender" invalid JsonString value.
	System.out.println("Done");
	}

}
