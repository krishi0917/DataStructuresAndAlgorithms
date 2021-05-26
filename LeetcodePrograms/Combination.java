package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * You may return the answer in any order.
 * Example 1:
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList();
        combinations(1, n, k, new ArrayList(), result);
        return result;
    }

    private void combinations(int start, int n, int k, List<Integer> list, List<List<Integer>> result) {
        if(list.size() == k) {
            result.add(list);
            return;
        }

        if(start > n)
            return;

        for(int i = start; i <= n; i++) {
            List<Integer> newList = new ArrayList(list);
            newList.add(i);
            combinations(i + 1, n, k, newList, result);
        }
    }

    public static void main(String []args){
        Combination c = new Combination();
        System.out.println(c.combine(7,3));
    }
}
