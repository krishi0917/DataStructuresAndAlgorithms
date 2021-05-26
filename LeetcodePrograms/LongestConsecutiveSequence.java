package LeetcodePrograms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by rkhurana on 7/26/18.
 */
public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;

                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s) of the sequence will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }

    public static void main(String []args){
        int num[] = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive2(num));


    }

    public static int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        int best = 0;
        for(int n : set) {
            if(!set.contains(n - 1)) {  // only check for one direction // above line is because if there is already
                // n-1 exist, that means the current element is already been considered before for the longest length
                // . thus we can ignore this one and can move forward
                int m = n + 1;
                while(set.contains(m)) { // if the element is there in the set, it means time to find out the
                    // length
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
}
