package apps;

public class Test {	
	public static void main(String[] args) {
		String regex = "[{\\[\\\"-0123456789ftn]";
		String test ="nsomething";
		String sub = test.substring(0, 1);
		System.out.println("sub: "+ sub);
		if ( sub.matches(regex)){
			System.out.println("yes");
		}
	}
}
