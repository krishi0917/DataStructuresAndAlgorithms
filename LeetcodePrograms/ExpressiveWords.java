package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * #GoogleInterviewQuestion
 * 809. Expressive Words
 *
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In
 * these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 *
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications
 * of the following extension operation: choose a group consisting of characters c, and add some number of characters
 * c to the group so that the size of the group is 3 or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
 * "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to
 * get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two
 * extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 *
 * Given a list of query words, return the number of words that are stretchy.
 */


// Solution : We have two pointers, use i to scan S, and use j to scan each word in words.
//        c1 keeps the track of the count in String S of that particular character
//        c2 keeps the track of the count in Words[] word of that particular character
//        then check for all the condition and if something fails. return
public class ExpressiveWords {
    public static int expressiveWords(String S, String[] words) {
        if (S == null || words == null) {
            return -1;
        }
        int count = 0;
        for (String word : words) {
            if (isStretchy(S, word)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isStretchy(String S, String word) {
        int i = 0;
        int j = 0;

        while (i < S.length() && j < word.length()) {
            char ch1 = S.charAt(i);
            char ch2 = word.charAt(j);
            if (ch1 != ch2) {
                return false;
            }
            int c1 = 0;
            while (i < S.length() && S.charAt(i) == ch1) {
                c1++;
                i++;
            }
            int c2 = 0;
            while (j < word.length() && word.charAt(j) == ch2) {
                c2++;
                j++;
            }
            if (c2 > c1) {
                return false;
            }
            int diff = c1 - c2;
            if (diff != 0 && diff + c2 < 3) {
                return false;
            }

        }

        return i == S.length() && j == word.length();
    }

    public static void main(String[] args) {
        String s = "heeellooo";
        String []words = {"hello", "hi", "helo"};
        System.out.println(ExpressiveWords.expressiveWords(s,words));
    }


}
