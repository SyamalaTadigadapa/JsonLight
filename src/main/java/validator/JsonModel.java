package validator;

import java.nio.file.Path;

import parse.JsonParse;
import types.JsonArray;
import types.JsonArrayIndex;
import types.JsonBoolean;
import types.JsonMap;
import types.JsonNull;
import types.JsonNumber;
import types.JsonObject;
import types.JsonString;

public class JsonModel {

	private JsonObject model;
	private JsonPath path;

	public JsonModel(JsonObject model) {
		this.model = model;
		this.path = new JsonPath();
	}
		
	public JsonModel path(JsonPath arg) {
		this.path.add(arg);
		return this;
	}
	
	public JsonPath getPath() {
		return path;
	}

	public void validate(JsonObject target) throws ValidationException {
		if (model instanceof JsonMap) {
			if (target instanceof JsonMap) {
				JsonMap targetMap = (JsonMap) target;
				JsonMap modelMap = (JsonMap) (model);
				for (JsonString key : modelMap.keySet()) {
					if (targetMap.containsKey(key)) {
						JsonModel m = new JsonModel(modelMap.get(key)).path(path);
						m.path.add(key);
						m.validate(targetMap.get(key));
					} else {
						throw new ValidationException("Failed at " + getPath() + " missing key " + key.value() );
					}
				}
			} else {
				throw new ValidationException("Ffailed at " + getPath() + " expecting JsonMap value.");
			}
		} else if (model instanceof JsonArray) {
			if (target instanceof JsonArray) {
				JsonArray targetArray = (JsonArray) target;
				JsonArray modelArray = (JsonArray) model;
				for (int index = 0; index < modelArray.size(); index++) {					
					JsonModel m = new JsonModel(modelArray.get(index)).path(path);
					m.path.add(new JsonArrayIndex(index));
					m.validate(targetArray.get(index));
				}
			} else {
				throw new ValidationException("Failed at " + getPath() + " expecting JsonArray value.");
			}
		} else if (model instanceof JsonString) {
			if (!(target instanceof JsonString)) {
				throw new ValidationException("Failed at " + getPath() + " expecting JsonString value.");
			}else {
				String regex = ((JsonString)model).value();
				if (!regex.isEmpty()) {
					String tar = ((JsonString)target).value();
					if (!tar.matches(regex)) {
						throw new ValidationException("Failed at " + getPath() + " invalid JsonString value.");
					}
				}
			}
		} else if (model instanceof JsonNumber) {
			if (!(target instanceof JsonNumber)) {
				throw new ValidationException("Failed at " + getPath() +  " expecting JsonNumber value.");
			}
		} else if (model instanceof JsonBoolean) {
			if (!(target instanceof JsonBoolean)) {
				throw new ValidationException("Failed at " + getPath() + " expecting JsonBoolean value.");
			}
		} else if (model instanceof JsonNull) {
			if (!(target instanceof JsonNull)) {
				throw new ValidationException("Failed at " + getPath() + " expecting JsonNull value.");
			}
		}		
	}

}
