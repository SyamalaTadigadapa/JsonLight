package types;

import java.text.DecimalFormat;

public class JsonNumber extends JsonObject {

	private double value;
	private static final DecimalFormat format = new DecimalFormat("0.#");

	public JsonNumber(Double value) {
		this.value = value;
	}
	
	public JsonNumber(int value) {
		this.value = value;
	}

	@Override
	public Double value() {
		return value;
	}

	@Override
	public String toString() {		
		return format.format(value);
	}
	
	public String nice() {
		return toString();
	}	
	
	public String nice(int indent) {
		return toString();
	}	

}
