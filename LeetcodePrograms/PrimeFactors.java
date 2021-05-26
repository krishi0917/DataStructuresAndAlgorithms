package LeetcodePrograms; /**
 * Created by rkhurana on 10/8/18.
 */

import java.util.*;
public class PrimeFactors {

    public static List<Integer> primeFactors(int numbers) {
        int n = numbers;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }

    public static int trailingZeroes(int n) {
        int r = 0;
        while (n > 0) {
            n /= 5;
            r += n;
        }
        return r;
    }

    public static void main(String [] args){
    //    System.out.println(PrimeFactors(44));
        System.out.println(trailingZeroes(50));
    }
}
