package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 115. Distinct Subsequences
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 *
 * It's guaranteed the answer fits on a 32-bit signed integer.
 *
 * Example 1:
 *
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * very good explanation https://www.youtube.com/watch?v=9yV6Elqvblw
 */
public class DistinctSubsequences {
//    Solution
//    Explanation to the state transition function
//
//    dp[i][j] = dp[i-1][j] when s[i-1] != t[j-1] or dp[i][j] = dp[i-1][j] + dp[i-1][j-1] when s[i-1] == t[j-1],
//    dp[i][j] represents the number of distinct subsequences for s[0, i-1] and t[0, t-1];
//
//    We first keep in mind that s is the longer string [0, i-1], t is the shorter substring [0, j-1]. We can assume t is fixed, and s is increasing in character one by one (this is the key point).
//
//    For example:
//    t : ab--> ab --> ab --> ab
//    s: a --> ac --> acb --> acbb
//
//    The first case is easy to catch. When the new character in s, s[i-1], is not equal with the head char in t, t[j-1], we can no longer increment the number of distinct subsequences, it is the same as the situation before incrementing the s, so dp[i][j] = dp[i-1][j].
//
//    However, when the new incrementing character in s, s[i-1] is equal with t[j-1], which contains two case:
//
//    We don't match those two characters, which means that it still has original number of distinct subsequences, so dp[i][j] = dp[i-1][j].
//    We match those two characters, in this way. dp[i][j] = dp[i-1][j-1];
//    Thus, including both two case, dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
public int numDistinct(String s, String t) {
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];
    // initialize the dp value when t is an empty string, number of subsequence of an empty string should be 1
    for(int i = 0; i < m; i ++){
        dp[i][0] = 1;
    }
    for(int i = 1; i <= m; i ++){
        for(int j = 1; j <= n; j ++){
            //in both cases, the subsequence in String t should be ending with character t.charAt(j - 1)
            if(s.charAt(i - 1) == t.charAt(j - 1)){
                // when two pointers pointing to same character
                // if we take these two characters simultaneously, we should have dp[i-1][j-1] subsequences
                // otherwise if we overlook current i (moving back for one step) and keeping the current j we have another dp[i -1][j]
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }else{
                // when two pointers pointing to difference characters
                //we cannot take these two characters but we still should make j ending with pointing to current position
                // then we should move i backward
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[m][n];
}
}
