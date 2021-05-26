package LeetcodePrograms;



/**
 * @author Rishi Khurana
 * 482. License Key Formatting
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The
 * string is separated into N+1 groups by N dashes.
 *
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except
 * for the first group which could be shorter than K, but still must contain at least one character. Furthermore,
 * there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 *
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 * Example 1:
 * Input: S = "5F3Z-2e-9-w", K = 4
 *
 * Output: "5F3Z-2E9W"
 *
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 */
public class LicenseKeyFormatting {
    public static String licenseKeyFormatting(String S, int K) {
        // Replacing all - and converting all letters to uppercase
        String S1 = S.replace("-", "");
        S1 = S1.toUpperCase();

        // Making stringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S1.length(); i++) {
            sb.append(S1.charAt(i));
        }
        int len = sb.toString().length();
        // Inserting '-' from back at every K position
        for (int i = K; i < len; i = i + K) {
            sb.insert(len - i, '-');
        }
        return sb.toString();
    }

    // accepted solution
    public static String licenseKeyFormatting2(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int len = S.length()-1;
        while(len >=0){
            int i =K-1;
            while(i >= 0 && len >=0){
                if(S.charAt(len) == '-') {
                    len--;
                }
                else {
                    char ch = S.charAt(len);
                    sb.append(Character.toUpperCase(ch));
                    len--;i--;
                }
            }
            if(len>=0)
                sb.append("-");
        }
        if(sb.length() >= 1 && sb.charAt(sb.length()-1) == '-')
            sb.replace(sb.length()-1 , sb.length() , "");
        return sb.reverse().toString();
    }
    public static void main(String []args){
        String S1 = "5F3Z-2e-9-w"; int K1 = 4;
        String S2 = "2-5g-3-J"; int K2 = 2;
        String S3 = "------a-a-a-a--"; int K3 = 2;
        String S4 = "------"; int K4 = 2;
        System.out.println(licenseKeyFormatting2(S4,K4));
 //       System.out.println(licenseKeyFormatting2(S2,K2));
    }
}
