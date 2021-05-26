package LeetcodePrograms;

public class Excel {
	public int titletoNum(String s) {
		int value = 0;
		int power = 0;
		if (s == null || s.length() == 0)// if empty or null
			return -1;

		for (int i = s.length() - 1; i >= 0; i--) {
			value = value + ((int) Math.pow(26, power))* ((int) s.charAt(i) - 64);
			power++;
		}
		return value;
	}

	public int titletoNum2(String s) {
		int result = 0;
		for(int i = 0 ; i < s.length(); i++) {
			result = result * 26 + (s.charAt(i) - 'A' + 1);
		}
		return result;


	}

	public String convertToTitle(int n) {
		StringBuilder result = new StringBuilder();
		while (n > 0) {
			n--;
			// result.insert(0, (char)('A' + n % 26)); //either insert it at the
			// 0th position or append it and later on reverse it.

			result.append((char) ('A' + n % 26));

			// e.g. if n is 25, n%26 = 25, so 'A' + 25 = 'Z'
			// e.g. if n is 26, n%26 = 0, so 'A' + 0 = 'A'.
			// e.g. if n is 27, n%26 = 1, so 'A' + 1 = 'B'
			n /= 26;


		}

		result.reverse();
		return result.toString();
	}


	public static void main(String[] args) {


//		String s = "A";
//		System.out.println((s.charAt(0)-64));


		String s  = "aIshi";
		int x = s.charAt(0) - '0';
		System.out.println("x value" + x);
		Excel ex = new Excel();

		System.out.println(ex.convertToTitle(27));

//		System.out.println(ex.convertToTitle(1));
	}
}
