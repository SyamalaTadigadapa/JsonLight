package types;

public class JsonBoolean extends JsonObject {

	private Boolean value;

	public JsonBoolean(boolean value) {
		this.value = value;
	}

	@Override
	public Boolean value() {
		return value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
	
	public String nice() {
		return toString();
	}
	
	public String nice(int indent) {
		return toString();
	}

}
