package LeetcodePrograms;

import java.util.HashMap;
import java.util.*;
import java.util.TreeMap;


// Main class should be named 'Solution'
class Solution {
    Map<String,String> map = new HashMap<>();
    public String get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            return null;
        }
    }
    public void set(String key , String value){
        map.put(key,value);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.get("foo"));
        s.set("foo","bar");
        System.out.println(s.get("foo"));
        s.set("foo","buzz");
        System.out.println(s.get("foo"));
    }
}


/**
 * @author Rishi Khurana
 * floorKey()-> the first greatest number <= given key;
 * ceillingKey()_> the first smaller number >=given key;
 */
public class TimeBasedKeyValueStore2 {
    private Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore2() {
        map = new HashMap<>();
    }

    // Spec: if timestamp is duplicated, original value will be covered.
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key))
            return null;
        Map.Entry<Integer,String> entry = map.get(key).floorEntry(timestamp);
        return entry == null ? "" : entry.getValue();
    }

    public String get(String key) {
        if (!map.containsKey(key))
            return null;
        TreeMap treeMap = map.get(key);
        Object[] keys = treeMap.keySet().toArray();
        String value = (String)treeMap.get(keys[0]);
        return value;
    }


    public static void main(String []args){

        TimeBasedKeyValueStore2 map = new TimeBasedKeyValueStore2();
        map.set("foo","bar2" , 2);
        map.set("foo","bar3" , 3);
        map.set("foo","bar1" , 1);
        map.set("foo","bar1" , 5);
        System.out.println(map.get("foo"));
        map.get("foo" , 4);
//        map.get("foo" , 3);
//        map.set("foo","bar2" , 4);
//        map.get("foo" , 4);
//        map.get("foo" , 5);

    }

    class Data {
        String val;
        int time;
        Data(String val, int time) {
            this.val = val;
            this.time = time;
        }
    }
    class TimeMap {

        /** Initialize your data structure here. */
        Map<String, List<Data>> map;
        public TimeMap() {
            map = new HashMap<String, List<Data>>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
            map.get(key).add(new Data(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            return binarySearch(map.get(key), timestamp);
        }

        protected String binarySearch(List<Data> list, int time) {
            int low = 0, high = list.size() - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (list.get(mid).time == time) return list.get(mid).val;
                if (list.get(mid).time < time) {
                    if (list.get(mid+1).time > time) return list.get(mid).val;
                    low = mid + 1;
                }
                else high = mid -1;
            }
            return list.get(low).time <= time ? list.get(low).val : "";
        }
    }

}
