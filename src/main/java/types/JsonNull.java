package types;

public class JsonNull extends JsonObject {

	private static Object value;

	@Override
	public Object value() {
		return value;
	}

	@Override
	public String toString() {
		return "null";
	}
	
	public String nice() {
		return toString();
	}
	
	public String nice(int indent) {
		return toString();
	}

}
