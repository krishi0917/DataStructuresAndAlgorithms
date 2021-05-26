package LeetcodePrograms;

/**
 * Created by rkhurana on 10/25/18.
 */
public class MultiplyString {
    public static String multiply(String num1, String num2) {
        int[] sum = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int tmp = (sum[i + j + 1]) + (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                sum[i + j + 1] = tmp % 10;
                carry = tmp / 10;
            }
            sum[i] += carry;
        }

        // below lines are only for trimming zero in the beginning of the number
        StringBuilder sb = new StringBuilder();
        for (int i : sum)
            if (sb.length() > 0 || i > 0)
                sb.append(i);
        return (sb.length() == 0) ? "0" : sb.toString();
    }
    public static void main(String []args){
        System.out.println(multiply("9","99"));
    }
}
