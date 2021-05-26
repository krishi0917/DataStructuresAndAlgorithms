package LeetcodePrograms;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by rkhurana on 7/19/18.
 */
public class HappyNumber {
    public static boolean isHappy2(int n) {
        int slow = n, fast = n;
        do {	slow = next(slow);
            fast = next(next(fast));
        }while (slow != fast);

        return slow == 1 ? true : false;
    }

    private static int next(int n) {
        int sum = 0;
        while (n > 0) {
            int i = n % 10;
            n /= 10;
            sum += i * i;
        }
        return sum;
    }

    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet();
        while(n != 1){
            int sum = 0;
            while(n != 0) {
                sum += (n%10) * (n%10);
                n = n/10;
            }
            n = sum;
            if(seen.contains(n))
                return false;
            seen.add(n);
        }
        return true;
    }

    // Magic number is when you replace the number by the sum of its digits,
    public static boolean isMagicNumber(int num) {
        if(num ==1 || num == -1)
            return true;
        if(num/10 == 0 )
            return false;
        int sumOfDigits = 0;
        while(num!=0){
            sumOfDigits+=num%10;
            num = num/10;
        }
        return isMagicNumber(sumOfDigits);
    }

    public static void main(String [] args){
        System.out.println(isMagicNumber(19));
  //      System.out.println(titletoNum2("AA"));
//        System.out.println(Math.pow(-13,10));
//        System.out.println(Math.pow(-13,25));
    }
}
