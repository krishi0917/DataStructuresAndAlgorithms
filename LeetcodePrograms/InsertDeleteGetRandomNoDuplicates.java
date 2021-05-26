package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 2/21/19.
 */
public class InsertDeleteGetRandomNoDuplicates {
    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomNoDuplicates() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (contain)
            return false;
        map.put(val, list.size());

        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = map.containsKey(val);
        if (!contain)
            return false;
        int location = map.get(val);
        // below lines will copy the data from the last element and override it to the index where it needs to be swapped
        // and then remove the last index element
        if (location < list.size() - 1) { // if it is not the last one than swap the last one with this val
            int lastone = list.get(list.size() - 1); // this is the actual element
            list.set(location, lastone); //element that needs to be deleted is been overridden by the last element
            map.put(lastone, location);
        }
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String []args){
        InsertDeleteGetRandomNoDuplicates o = new InsertDeleteGetRandomNoDuplicates();
        o.insert(10);
        o.insert(11);
        o.insert(12);
        o.insert(13);
        o.insert(14);
        o.remove(13);
        o.insert(15);

    }
}
