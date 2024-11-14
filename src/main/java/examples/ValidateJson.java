package examples;

import parse.JsonParse;
import types.JsonObject;
import validator.JsonModel;

public class ValidateJson {
	
	public static void main(String[] args) throws Exception{
	
	String jsonstr = "{\n"
			+ "        \"age\": 30,\n"
			+ "        \"gender\": \"male\",\n"
			+ "        \"member\": \"yes\",\n"
			+ "        \"name\": \"subba rao\"\n"
			+ "    }";
	
	String jsonModel = "{\n"
			+ "        \"age\": 1,\n"
			+ "        \"gender\": \"\",\n"
			+ "        \"member\": true,\n"
			+ "        \"name\": \"\"\n"
			+ "    }";
	
	
	JsonObject model = new JsonParse(jsonModel).get();
	JsonObject json = new JsonParse(jsonstr).get();
	
	new JsonModel(model).validate(json);
	// You should get 
	// ValidationException: Failed at "member" expecting JsonBoolean value.
	System.out.println("Done");
	}

}
