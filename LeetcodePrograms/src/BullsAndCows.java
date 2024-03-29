package LeetcodePrograms.src;

/**
 * @author Rishi Khurana
 * Google asked in last 6 months
 * 299. Bulls and Cows
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits
 * in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits
 * match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses
 * and hints to eventually derive the secret number.
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
 * and B to indicate the cows.
 * Please note that both secret number and friend's guess may contain duplicate digits.
 * Example 1:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 */
public class BullsAndCows {
    public static String getHint(String secret, String guess) {
            int bulls = 0;
            int[] nums1 = new int[10];
            int[] nums2 = new int[10];
            for(int i = 0; i < secret.length(); i++){
                int s = Character.getNumericValue(secret.charAt(i));
                int g = Character.getNumericValue(guess.charAt(i));
                if(s == g){
                    bulls++;
                } else{
                    nums1[s ]++;
                    nums2[g]++;
                }
            }
            int cows = 0;
            for(int i = 0; i < 10; i++){
                cows += Math.min(nums1[i], nums2[i]);
//  compare the count in for each digit, say secret has 4 'a' and guess has 2'a' , then your guess is
//                partially right which is Math.min(4, 2)= 2
            }
            String res = bulls + "A" + cows + "B";
            return res;
    }

    public static void main(String []args){
        String secret = "1123", guess = "0111";
        System.out.println(getHint(secret,guess));
    }

    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
                if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
