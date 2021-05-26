package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Rishi Khurana
 * 1056. Confusing Number
 * Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0,
 * 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is
 * a number that when rotated 180 degrees becomes a different number with each digit valid.
 */
public class ConfusingNumber {
    public boolean confusingNumber(int N) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(6, 9);
        map.put(9, 6);
        map.put(0, 0);
        map.put(1, 1);
        map.put(8, 8);
        int newNum = 0;
        int x = N;
        while (x != 0) {
            int remainder = x % 10;
            if (!map.containsKey(remainder))
                return false;
            if(newNum > Integer.MAX_VALUE/10)
                return false;
            newNum = newNum * 10 + map.get(remainder);
            x /= 10;
        }
        return N == newNum? false: true;
    }
//    or
public boolean confusingNumber2(int N) {
    char[] rotate = {'0', '1', '$', '$', '$', '$', '9', '$', '8', '6'};
    char[] num = Integer.toString(N).toCharArray();
    boolean res = false;

    for (int l = 0, r = num.length - 1; l <= r; ++l, --r) {
        if (rotate[num[l] - '0'] == '$' || rotate[num[r] - '0'] == '$')
            return false;
        if (rotate[num[l] - '0'] != num[r])
            res = true;
    }

    return res;
}
//    or

    public boolean confusingNumber3(int N) {
        int[] rotate = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        int num = N;
        int rotated = 0;

        while (num > 0) {
            int d = num % 10;
            if (rotate[d] < 0)
                return false;
            rotated = rotated * 10 + rotate[d];
            num /= 10;
        }

        return rotated != N;
    }

    public static void main(String []args){
        ConfusingNumber CN = new ConfusingNumber();
        int n = 8;
        System.out.println(CN.confusingNumber3(n));
    }
}
