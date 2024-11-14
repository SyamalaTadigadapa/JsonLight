package types;

public abstract class JsonObject {

	public abstract Object value();

	public abstract String toString();
	public abstract String nice();
	protected abstract String nice(int indent);

	public JsonObject get(int index) {
		if (this instanceof JsonArray) {
			return ((JsonArray) this).get(index);
		} else {
			return null; // Raise exception?
		}
	}

	private JsonObject get1(String key) {
		if (this instanceof JsonMap) {
			return ((JsonMap) this).get(new JsonString(key));
		} else {
			return null; // Raise exception?
		}
	}
	
	public JsonObject get(String pathString) {
		JsonObject result = this;
		for (String arg: pathString.split("\\.")) {
			if (arg.matches("[0-9]+")) {
				result = result.get(Integer.valueOf(arg));
			}else {
				result = result.get1(arg);
			}
		}
		return result;
	}
	
	public JsonObject add(JsonObject other) {
		if (this instanceof JsonMap) {
			if (other instanceof JsonMap) {
				JsonMap m = (JsonMap)this;
				JsonMap o = (JsonMap)other;
				for(JsonString key: o.keySet()) {
					m.add(key, o.get(key));
				}
			}
		}else if (this instanceof JsonArray) {
			JsonArray a = (JsonArray)this;
			a.add(other);
		}
		return this;
	}

}
