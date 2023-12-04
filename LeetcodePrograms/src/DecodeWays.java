package LeetcodePrograms.src;

public class DecodeWays {
/*    part 1 -> When a number between 1 and 9 is added , the number of ways remains the same as it was before when considered alone.
    part 2 -> check if the number can occur as second digit
            if it cannot act as the second digit, answer is returned just as part 1
            if it can, then*/
    // https://www.youtube.com/watch?v=o1i7JYWbwOE&ab_channel=KnowledgeCenter
    // Q91 Decode Ways #TopInterviewQuestion
    // A message containing letters from A-Z is being encoded to numbers using
    // the following mapping:
    // 'A' -> 1
    // 'B' -> 2
    // ...bk
    // 'Z' -> 26
    // Given an encoded message containing digits, determine the total number of ways to decode it.
    // For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).




    // Solution
// whenever you add one char in the existing string you check
    // if its valid alone, if its valid then the number of decoding ways will be same as before
    //  if that char and the char before are valid , then add the sum of the remaining valid with the above line valid numbers and that will be the solution
    /*explanation
    for example 1423 has 4 valid ways
            1,4,2,1
            14,2,1
            1,4,21
            14,21

    now when you add 1 more character to it, we check if that can be considered a valid alone character or not
            if now the string becomes 14231 -> then the result will be same ie 4
            1,4,2,1,3
            14,2,1,3
            1,4,21,3
            14,21,3
    but this is just a part of solution, another part is to check the last 2 characters and see if that is a valid one
    check if 13 is a valid character., if it is then whatever the valid ways we have for the remaining string, will also be added
            1,4,2
            14,2
    and adding 13 as one solution in the above one
    1,4,2,13
    14,2,3

    so answer becomes 4 + 2 = 6

Note 02 != 2 and ) can occur only as a second character in the string
     */
    public static int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0')
            return 0;
        if (n == 1)
            return 1;
        int m1 = 1, m2 = 1, num = 0;
        for (int i = 1; i < n; i++) {
            num = 0;
            if (!isValid(s.charAt(i)) && !isValid(s.charAt(i - 1), s.charAt(i)))
                return 0;
            if (isValid(s.charAt(i)))
                num = m1;
            if (isValid(s.charAt(i - 1), s.charAt(i)))
                num += m2;
            m2 = m1;
            m1 = num;
        }
        return num;
    }

    static boolean  isValid(char a, char b) {
        return a == '1' || (a == '2' && b <= '6');
    }

    static boolean  isValid(char a) {
        return a != '0';
    }

    public static void main(String [] args){
        System.out.println(numDecodings("28"));
    }
}
