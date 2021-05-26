package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 /* capacity)
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

/**
 *Approach
 * We are using HashMap and LinkedHashSet
 * GET: if the map contains the key, then get from the map and remove and readd the key to the set so that it is moved ot the last
 * PUT: 1. if the map contains the key, put to the map and follow the above operation for the set
 *  2. If its a new ket and the capacity has reached, then get the head of the set and remove that head key from map as well as the set
 *  And then readd the key to the set and the new key value to the map
 *  3. If the capacity has not reached, simply add the key paair to the map and key to the set.
 */

public class LRUCache2 {

    HashMap<Integer, Integer> map = new HashMap<>();
    LinkedHashSet<Integer> set = new LinkedHashSet<>();
    int cap;
    public LRUCache2(int capacity) {
        cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            //after this get the element will move to the last of the set since it is most recently used
            int value = map.get(key);
            set.remove(key);
            set.add(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // for already added key, this is an overwrite value
            // add to the map and then add to the set as well to the last
            map.put(key, value);
            // this will move this key to the last in the set
            get(key);
            // do not go further
            return;
        }

        // In case the capacity has reached, we have to remove the first element from the set and the same from the map as well
        if (map.size() == this.cap) {
            int head = set.iterator().next();
            set.remove(head);
            map.remove(head);
        }
        map.put(key, value);
        set.add(key);
    }

}