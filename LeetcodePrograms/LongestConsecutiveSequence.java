package LeetcodePrograms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * // Q128. Longest consecutive sequence #TopInterviewQuestion #GoodQuestion #Facebook #Uber
 * // Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * // For example, Given [100, 4, 200, 1, 3, 2],
 * // The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 */


// second approach is an interesting one
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

    // the idea is to put the elements in the set and only do the calculation when n-1 element is not present in the set.
    // in that case you are in the beginning and you can calculate the best consecutive elements.
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
                while(set.contains(m)) { // if the element is there in the set, it means time to find out the length
                    m++;
                }
                best = Math.max(best, m - n);
            }
        }
        return best;
    }
}
