package types;

public class JsonArrayIndex {
	
	private int index;
	
	public JsonArrayIndex(int index) {
		this.index = index;
	}
	
	public String toString() {
		return "[" + index + "]";
	}

}
