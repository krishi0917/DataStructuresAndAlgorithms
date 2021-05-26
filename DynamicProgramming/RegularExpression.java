package DynamicProgramming;

public class RegularExpression {
	public boolean matchRegex(char[] str, char[] pattern) {
		boolean T[][] = new boolean[str.length + 1][pattern.length + 1];
		T[0][0] = true;
		// Deals with patterns like a* or a*b* or a*b*c*
		for (int i = 1; i < T[0].length; i++) {
			if (pattern[i] == '*') {
				T[0][i + 1] = T[0][i - 1];
			}
		}
		for (int i = 1; i <= str.length; i++) {
			T[i][0] = false;
		}
		for (int i = 1; i <= T.length; i++) {
			for (int j = 1; j <= T[0].length; j++) {
				if (str[i] == pattern[j] || pattern[j] == '.') {
					T[i][j] = T[i - 1][j - 1];
				}
				if (pattern[j] == '*') {
					T[i][j] = T[i][j - 2];
					if (str[i] == pattern[j - 1] || pattern[j - 1] == '.') {
						T[i][j] = T[i - 1][j];
					}
				} else {
					T[i][j] = false;
				}
			}
		}

		return T[str.length][pattern.length];
	}

	public static void main(String[] args) {
		RegularExpression rm = new RegularExpression();
		System.out.println(rm.matchRegex("Tushar".toCharArray(),
				"Tushar".toCharArray()));
		System.out.println(rm.matchRegex("Tusha".toCharArray(),
				"Tushar*a*b*".toCharArray()));
		System.out
				.println(rm.matchRegex("".toCharArray(), "a*b*".toCharArray()));
		System.out.println(rm.matchRegex("abbbbccc".toCharArray(),
				"a*ab*bbbbc*".toCharArray()));
		System.out.println(rm.matchRegex("abbbbccc".toCharArray(),
				"aa*bbb*bbbc*".toCharArray()));
		System.out.println(rm.matchRegex("abbbbccc".toCharArray(),
				".*bcc".toCharArray()));
		System.out.println(rm.matchRegex("abbbbccc".toCharArray(),
				".*bcc*".toCharArray()));
		System.out.println(rm.matchRegex("abbbbccc".toCharArray(),
				".*bcc*".toCharArray()));
		System.out.println(rm.matchRegex("aaa".toCharArray(),
				"ab*a*c*a".toCharArray()));
		System.out
				.println(rm.matchRegex("aa".toCharArray(), "a*".toCharArray()));

	}

}
