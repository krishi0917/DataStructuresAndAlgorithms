package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TimeBasedKeyValueStore {
    class TimeStamp implements Comparable<TimeStamp>{
        int ts;
        String value;

        TimeStamp(int ts, String value) {
            this.ts = ts;
            this.value = value;
        }

        public int compareTo(TimeStamp t) {
            return -Integer.compare(this.ts, t.ts);
        }
    }

    HashMap<String, HashMap<Integer, String>> map;
    HashMap<String, PriorityQueue<TimeStamp>> pqMap;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        map  = new HashMap();
        pqMap = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        PriorityQueue<TimeStamp> pq;
        if (map.containsKey(key)) {
            HashMap tsMap = map.get(key);
            // if the key is same with a new timestamp
            if (!tsMap.containsKey(timestamp)) {
                tsMap.put(timestamp, value);
            }
            pq = pqMap.get(key);
        } else {
            HashMap tsMap = new HashMap();
            tsMap.put(timestamp, value);
            map.put(key, tsMap);
            pq = new PriorityQueue(1);
        }
        TimeStamp ts = new TimeStamp(timestamp, value);
        pq.offer(ts);
        pqMap.put(key, pq);
    }

    public String get(String key, int timestamp) {
        String result = "";
        if (map.containsKey(key)) {
            HashMap tsMap = map.get(key);
            if (tsMap.containsKey(timestamp)) {
                return (String) tsMap.get(timestamp);
            } else {
                PriorityQueue<TimeStamp> pq = pqMap.get(key);
                if (pq == null) {
                    return result;
                } else {
                    List<TimeStamp> list = new ArrayList<TimeStamp>();
                    while (!pq.isEmpty()) {
                        TimeStamp ts = pq.poll();
                        list.add(ts);
                        if (ts.ts < timestamp) {
                            result = ts.value;
                            break;
                        }
                    }
                    pq.addAll(list);
                    pqMap.put(key, pq);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TimeBasedKeyValueStore tStore = new TimeBasedKeyValueStore();
        tStore.set("love", "high", 10);
        tStore.set("love", "low", 20);
        tStore.get("love", 5);
        tStore.get("love", 10);
        tStore.get("love", 15);
        tStore.get("love", 20);
        tStore.get("love", 25);
    }

}