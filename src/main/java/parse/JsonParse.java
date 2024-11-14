package parse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import types.JsonArray;
import types.JsonBoolean;
import types.JsonMap;
import types.JsonNull;
import types.JsonNumber;
import types.JsonObject;
import types.JsonString;

public class JsonParse {

	private final List<Token> tokens;
	private final JsonObject json;
	private int index;

	public JsonParse(String text) {
		this.index = 0;
		this.tokens = new JsonLex(text).tokens();
		this.json = jsonObject();
	}

	public JsonParse(Path path) throws IOException {
		this.index = 0;
		this.tokens = new JsonLex(new String(Files.readAllBytes(path))).tokens();
		this.json = jsonObject();
	}

	public JsonObject get() {
		return json;
	}

	private boolean checkToken(TokenType t) {
		return tokens.get(index).getType() == t;
	}

	private Token getToken(TokenType t) {
		if (tokens.get(index).getType() == t) {
			return tokens.get(index++);
		}
		// RUN TIME ERROR AT THIS TOKEN.
		throw new JsonParseException("Expected token of type " + t.name() + " but got token " + tokens.get(index));
	}

	private JsonObject jsonObject() {
		if (checkToken(TokenType.STRING) == true) {
			return new JsonString(tokens.get(index++).getValue());
		}
		if (checkToken(TokenType.NUMBER) == true) {
			String str = tokens.get(index++).getValue();
			return new JsonNumber(Double.valueOf(str));
		}
		if (checkToken(TokenType.TRUE) == true) {
			index += 1;
			return new JsonBoolean(true);
		}
		if (checkToken(TokenType.FALSE) == true) {
			index += 1;
			return new JsonBoolean(false);
		}
		if (checkToken(TokenType.NULL) == true) {
			index += 1;
			return new JsonNull();
		}
		if (checkToken(TokenType.OPENBRACE) == true) {
			return getMap();
		}
		if (checkToken(TokenType.OPENSQUARE) == true) {
			return getArray();
		}
		return null;
	}

	private JsonMap getMap() {
		getToken(TokenType.OPENBRACE);
		JsonMap map = new JsonMap();
		if (!checkToken(TokenType.CLOSEBRACE)) {
			JsonString key = new JsonString(getToken(TokenType.STRING).getValue());
			getToken(TokenType.COLON);
			map.add(key, jsonObject());
			while (checkToken(TokenType.COMMA)) {
				getToken(TokenType.COMMA);
				key = new JsonString(getToken(TokenType.STRING).getValue());
				getToken(TokenType.COLON);
				map.add(key, jsonObject());
			}
		}
		getToken(TokenType.CLOSEBRACE);
		return map;
	}

	private JsonArray getArray() {
		getToken(TokenType.OPENSQUARE);
		JsonArray array = new JsonArray();
		if (!checkToken(TokenType.CLOSESQUARE)) {
			array.add(jsonObject());
			while (checkToken(TokenType.COMMA)) {
				getToken(TokenType.COMMA);
				array.add(jsonObject());
			}
		}
		getToken(TokenType.CLOSESQUARE);
		return array;
	}

}
