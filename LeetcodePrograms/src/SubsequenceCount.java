package LeetcodePrograms.src;

import java.util.ArrayList;
import java.util.List;

public class SubsequenceCount {
    /*

Given two strings, find the number of times the second string occurs in the first string, whether continuous or discontinuous.
Examples:
Input:
string a = "GeeksforGeeks"
string b = "Gks"
Output: 4
*/
    public static void main(String []args){
        pthFactor(10,3);
    }

        public static long pthFactor(long n, long p) {
            List<Long> factors = new ArrayList<>();
            int count = 0;
            long element = 0;
            for (long i = 1; i <= n/2 + 1; i++) {
                if (n % i == 0) {

                    count++;
                    factors.add(i);
                }
                if(count == p)
                    element = i;
                // if(factors.size() > (p + 2))
                //    break;
            }
            System.out.println(factors);
            // long size = factors.size();
            // if(size < p ){
            // return 0;
            //return element;
            // }
            //return factors.get((int)p-1);
            System.out.println("count" + count);
            System.out.println("element" + element);
            return element;
        }
    }

