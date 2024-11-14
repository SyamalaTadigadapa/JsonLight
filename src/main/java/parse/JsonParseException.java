package parse;

import java.io.Serializable;

public class JsonParseException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -4741143454686666607L;

	public JsonParseException(String message) {
		super(message);
	}

}
