package LeetcodePrograms;

public class itoa {
	private static String convertToString(int a) {

		int c;
		char m;
		StringBuilder ans = new StringBuilder();
		// convert the String to int
		while (a > 0) {
			c = a % 10;
			a = a / 10;
			// m = (char) ('0' + c);
			ans.append(c);
		}
		return ans.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(convertToString(1234));
	}

}
