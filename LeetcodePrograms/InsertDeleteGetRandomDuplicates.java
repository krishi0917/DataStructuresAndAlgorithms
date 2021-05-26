package LeetcodePrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by rkhurana on 2/27/19.
 */
public class InsertDeleteGetRandomDuplicates {
    ArrayList<Integer> nums;
    HashMap<Integer, Set<Integer>> map;
    java.util.Random rand = new java.util.Random();

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomDuplicates() {
        nums = new ArrayList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
    }

    // Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (!contain)
            map.put(val, new LinkedHashSet<Integer>());
        map.get(val).add(nums.size());
        nums.add(val);
        return !contain;
    }

    // Removes a value from the collection. Returns true if the collection contained the specified element.
    public boolean remove(int val) {
        boolean contain = map.containsKey(val);
        if (!contain)
            return false;
        int loc = map.get(val).iterator().next();
        map.get(val).remove(loc);
        if (loc < nums.size() - 1) {
            int lastone = nums.get(nums.size() - 1);
            nums.set(loc, lastone);
            map.get(lastone).remove(nums.size() - 1);
            map.get(lastone).add(loc);
        }
        nums.remove(nums.size() - 1);

        if (map.get(val).isEmpty())
            map.remove(val);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }

    public static void main(String []args){
        InsertDeleteGetRandomDuplicates o = new InsertDeleteGetRandomDuplicates();
        o.insert(24);
        o.insert(27);
        o.insert(24);
        o.insert(24);
        o.insert(27);
        o.remove(27);


    }
}
