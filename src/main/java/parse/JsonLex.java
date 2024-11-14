package parse;

import java.util.ArrayList;
import java.util.List;

public class JsonLex {

	private final String text;
	public List<Token> tokens;
	private int index;

	public JsonLex(String text) {
		this.text = text;
		this.tokens = new ArrayList<>();
		tokenize();
	}

	public List<Token> tokens() {
		return tokens;
	}

	private String word() {
		StringBuffer sb = new StringBuffer();
		while (index < text.length() && Character.isLetter(text.charAt(index))) {
			sb.append(text.charAt(index++));
		}
		index -= 1;
		return sb.toString();
	}

	private String string() {
		StringBuffer sb = new StringBuffer();
		index += 1;
		while (index < text.length() && text.charAt(index) != '"') {
			sb.append(text.charAt(index++));
		}
		return sb.toString();
	}

	private String number() {
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		while (flag && index < text.length()) {
			switch (text.charAt(index)) {
			case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
				sb.append(text.charAt(index++));
			}
			case '+', '-', 'e', 'E', '.' -> {
				sb.append(text.charAt(index++));
			}
			default -> {
				flag = false;
			}
			}
		}
		index -= 1;
		return sb.toString();
	}

	private void tokenize() {
		for (index = 0; index < text.length(); index++) {
			switch (text.charAt(index)) {
			case '{': {
				tokens.add(new Token(TokenType.OPENBRACE, "{"));
				break;
			}
			case '}': {
				tokens.add(new Token(TokenType.CLOSEBRACE, "}"));
				break;
			}
			case '[': {
				tokens.add(new Token(TokenType.OPENSQUARE, "["));
				break;
			}
			case ']': {
				tokens.add(new Token(TokenType.CLOSESQUARE, "]"));
				break;
			}
			case ':': {
				tokens.add(new Token(TokenType.COLON, ":"));
				break;
			}
			case ',': {
				tokens.add(new Token(TokenType.COMMA, ","));
				break;
			}
			case 'n': {
				String w = word();
				if (w.equals("null")) {
					tokens.add(new Token(TokenType.NULL, "null"));
				} else {
					System.err.println("n Illegal token " + w + " index = " + index);
					System.exit(1);
				}
				break;
			}
			case 't': {
				String w = word();
				if (w.equals("true")) {
					tokens.add(new Token(TokenType.TRUE, " true"));
				} else {
					System.err.println("t Illegal token " + w + " index = " + index);
					System.exit(1);
				}
				break;
			}
			case 'f': {
				String w = word();
				if (w.equals("false")) {
					tokens.add(new Token(TokenType.FALSE, "false"));
				} else {
					System.err.println("f Illegal token " + w + " index = " + index);
					System.exit(1);
				}
				break;
			}
			case '"': {
				// System.err.println("index = " + index);
				String str = string();
				tokens.add(new Token(TokenType.STRING, str));
				break;
			}
			case '-': {
				String str = number();
				tokens.add(new Token(TokenType.NUMBER, str));
				break;
			}
			default: {
				if (Character.isDigit(text.charAt(index))) {
					String str = number();
					tokens.add(new Token(TokenType.NUMBER, str));
					break;
				}
				if (Character.isWhitespace(text.charAt(index))) {
					break;
				}
				System.err.println("Illegal token " + text.charAt(index) + " at position " + index);
				System.exit(1);
			}
			}
		}
	}

}
