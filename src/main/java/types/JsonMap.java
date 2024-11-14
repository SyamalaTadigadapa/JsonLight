package types;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class JsonMap extends JsonObject {

	private Map<JsonString, JsonObject> map;

	public JsonMap() {
		this.map = new TreeMap<>();
	}

	public Map<JsonString, JsonObject> value() {
		return map;
	}

	public JsonMap add(JsonString key, JsonObject val) {
		map.put(key, val);
		return this;
	}

	public JsonMap add(String key, String val) {
		map.put(new JsonString(key), new JsonString(val));
		return this;
	}

	public JsonObject get(JsonString key) {
		return map.get(key);
	}

	public boolean containsKey(JsonString key) {
		return map.containsKey(key);
	}

	public Set<JsonString> keySet() {
		return map.keySet();
	}

	public Set<Entry<JsonString, JsonObject>> entrySet() {
		return map.entrySet();
	}

	public String toString() {
		String out = "{";
		int sz = map.size();
		for (JsonString key : map.keySet()) {
			out += key;
			out += ":";
			out += map.get(key).toString();
			out += --sz > 0 ? "," : "";
		}
		out += "}";
		return out;
	}
	
	public String nice() {
		return nice(0);
	}
	
	public String indentation(int width) {
		return  new String(new char[4*width]).replace('\0', ' ');
	}
	
	public String nice(int indent) {
		String out = "{\n";
		indent += 1;
		int sz = map.size();
		for (JsonString key : map.keySet()) {
			out += indentation(indent);
			out += key;
			out += ": ";
			out += map.get(key).nice(indent);
			out += --sz > 0 ? ",\n" : "";
		}
		out += "\n";
		indent -= 1;
		out += indentation(indent);
		out += "}";
		return out;
	}

	public JsonMap delete(JsonString key) {
		map.remove(key);
		return this;
	}

}
