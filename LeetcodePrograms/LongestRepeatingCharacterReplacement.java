package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        int[] count = new int[128];
        int max=0;
        int start=0;
        for(int end=0; end<s.length(); end++)
        {
            max = Math.max(max, ++count[s.charAt(end)]);
            if(max+k<=end-start)
                count[s.charAt(start++)]--;
        }
        return s.length()-start;
    }
    public static void main(String []args){
        int k=2;
        String s = "BAAAB";
        System.out.println(characterReplacement(s,k));
    }

    public static int characterReplacement2(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}
