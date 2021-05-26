package LeetcodePrograms;//Panagram means it contains all the letters of the alphabet

public class Panagram {

	public static int isPangram(String n) {
		// n.toLowerCase()
		int val = 1;
		char abc = 97;

		for (char i = 97; i <= 122; i++) {
			System.out.println("index is " + n.indexOf(i));
			if ((n.indexOf(i) < 0)) {
				if (n.indexOf(i - 32) < 0)
					val = 0;
			}
		}
		return val;
	}

	public static boolean checkPanagram(String s) {

		int[] a = new int[26];
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
				if (a[s.charAt(i) - 65] == 0)
					count++;
				a[s.charAt(i) - 65]++;
			} else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
				if (a[s.charAt(i) - 97] == 0)
					count++;
				a[s.charAt(i) - 97]++;
			}
		}
		if (count == 26) {
			System.out.println("pangram");
			return true;
		} else {
			return false;
		}

	}

	public static void main(String[] args) {
 System.out.println(Panagram.checkPanagram("abcdef#hijklmnopqrstuvwxyz"));
		String x = "ABC";
		for (int i = 0; i < x.length(); i++) {
			int y = x.charAt(i);
			char ch2 = x.charAt(i);
			System.out.println("character coming " + y + ch2);
		}

	}

}
