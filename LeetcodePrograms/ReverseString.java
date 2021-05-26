package LeetcodePrograms;

public class ReverseString {

	public static String reverseString(String s) {

		if (s.length() == 0)

			return s;

		return reverseString(s.substring(1)) + s.charAt(0);

	}

//yM name is
// yM eman is
// yM eman si
// is name My

	public static char[] reverseWordsInAString(String n) {
		char []s = n.toCharArray();
		int i = 0;
		for (int j = 0; j < s.length; j++) {
			if (s[j] == ' ') {
				reverse(s, i, j - 1);
				i = j + 1;
			}
		}
		reverse(s, i, s.length - 1);

		reverse(s, 0, s.length - 1);
		return s;
	}

	private static void reverse(char[] s, int i, int j) {
		while (i < j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}


	public static void main(String[] args) {

		String input = "rishi";

//		String output = reverseString(input);

//		System.out.println(output);

		System.out.println(reverseWordsInAString("My name is Rishi"));

	}

}