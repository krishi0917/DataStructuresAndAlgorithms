package LeetcodePrograms;

public class isPalindrome {
    public static boolean isPalindrome2(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) //a good point
            // As discussed above, when x < 0, x is not a palindrome.
            // Also if the last digit of the number is 0, in order to be a palindrome,
            // the first digit of the number also needs to be 0. Only 0 satisfy this property.
            return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        System.out.println("rev is "+ rev + "and x is " + x );
        return (x == rev || x == rev / 10);
    }

    public static void main(String []args){
        System.out.println(isPalindrome2(12321));

    }
}
