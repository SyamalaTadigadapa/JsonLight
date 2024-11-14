package types;

public class JsonString extends JsonObject implements Comparable<JsonString> {

	private String value;

	public JsonString(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return '"' + value + '"';
	}
	
	public String nice() {
		return toString();
	}
	
	public String nice(int indent) {
		return toString();
	}

	@Override
	public int compareTo(JsonString o) {
		if (this != o) {
			return value.compareTo(o.value);
		}
		return 0;
	}

}
