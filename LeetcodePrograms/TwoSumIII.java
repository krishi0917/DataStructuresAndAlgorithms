package LeetcodePrograms;
import java.util.*;

/**
 * Created by rkhurana on 2/27/19.
 */
public class TwoSumIII {
    Map<Integer, Integer> map = new HashMap<>();

    /** Initialize your data structure here. */


    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(int key : map.keySet()) {
            int remain = value - key;
            if (remain == key && map.get(key) >= 2)
                return true;
            if (remain != key && map.containsKey(remain)) return true;
        }
        return false;
    }


    Set<Integer> sums = new HashSet<>();
    Set<Integer> nums = new HashSet<>();


    /** Add the number to an internal data structure.. */
    public  void addNum(int number) {
        for(int n : nums) {
            System.out.println(n);
            System.out.println(number);
            sums.add(number + n);
        }
        nums.add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean findNum(int value) {
        return sums.contains(value);
    }

    public static void main(String []args){
        TwoSumIII o = new TwoSumIII();
        o.addNum(1);
        o.addNum(2);
        o.addNum(3);
        o.addNum(4);
        o.addNum(5);
        o.addNum(6);

        System.out.println(o.findNum(4));
        System.out.println(o.findNum(7));

    }
}
