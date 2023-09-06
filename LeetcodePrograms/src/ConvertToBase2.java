package LeetcodePrograms.src;

//    1017. Convert to Base -2
//    Given an integer n, return a binary string representing its representation in base -2.
//    Note that the returned string should not have leading zeros unless the string is "0".
//    Example 1:
//        Input: n = 2
//        Output: "110"
//        Explantion: (-2)2 + (-2)1 = 2
public class ConvertToBase2 {
//    We can slove this problem with a similar approach as base 2, but with a small twist.
//
//    When N is a postive number, for example N = 2, 2 / (-2) = -1, 2 % (-2) = 0, as 2 = (-2) * (-1) + 0, the remainder is either 0 or 1 which satisfy our problem.
//    When N is a negative number, for example N = -1, -1 / (-2) = 0, -1 % (-2) = -1, as -1 = (-2) * 0 + (-1), the remainder is -1 which doesn't satisfy our problem. We can do the following math trick to convert the negative remainder to positive.
//
//    we add -2 and substract -2, so the equation still remains accurate, but we convert negative remainder to postive.
//            -1 = (-2) * 0 + (-1)
//            -1 = (-2) * 0 + (-2) + (-1) - (-2)
//            -1 = (-2) * (0 + 1) + (-1) - (-2)

    public static String baseNeg2(int N) {
        if (N == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            int r = N % (-2);
            N = N / (-2);
            if (r < 0) {
                r += 2;
                N += 1;
            }
            sb.append(r);
        }
        return sb.reverse().toString();
    }
    public static void main(String []args){
        System.out.println(baseNeg2(2));
    }
}
