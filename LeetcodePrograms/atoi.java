package LeetcodePrograms;

public class atoi {
	public static int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
		str = str.trim();
		char flag = '+';
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}

		// use double to store result
		int result = 0;
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			// Character.getNumericValue(str.charAt(i))
			System.out.println(str.charAt(i));
			System.out.println(str.charAt(i) - '0');
			System.out.println("result is " + result);
			// System.out.println(Character.getNumericValue(str.charAt(i)));
			i++;
		}

		if (flag == '-')
			result = -result;

		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;

		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		return (int) result;
	}

	// ----------------------------------------------x---------------------------x----------------

	public static void main(String[] args) {
		System.out.println(atoi("1234"));
	}

}
