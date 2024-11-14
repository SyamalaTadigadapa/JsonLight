package types;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonObject {

	private List<JsonObject> list;

	public JsonArray() {
		list = new ArrayList<>();
	}

	public List<JsonObject> value() {
		return list;
	}

	public JsonArray add(JsonObject o) {
		list.add(o);
		return this;
	}

	public JsonArray add(String s) {
		list.add(new JsonString(s));
		return this;
	}

	public int size() {
		return list.size();
	}

	public JsonObject get(int index) {
		return list.get(index);
	}

	@Override
	public String toString() {
		String out = "[";
		int sz = list.size();
		for (int i = 0; i < list.size(); i++) {
			out += list.get(i).toString();
			out += --sz > 0 ? "," : "";
		}
		out += "]";
		return out;
	}
	
	public String indentation(int width) {
		return  new String(new char[4*width]).replace('\0', ' ');
	}
	
	public String nice() {
		return nice(0);
	}
	
	public String nice(int indent) {
		String out = "[\n";
		indent += 1;
		int sz = list.size();
		for (int i = 0; i < list.size(); i++) {
			out += indentation(indent);
			out += list.get(i).nice(indent);
			out += --sz > 0 ? ",\n" : "";
		}		
		out += "\n";	
		indent -= 1;
		out += indentation(indent);
		out += "]";
		return out;
	}

}
