//First k pair with smallest product
//
//Map, <String, Set<String>>
//Names, Emails
//C1 : [a@x, b@x, c@x]
//C2 : [d@x, e@x]
//C3 : [f@x]
//C4 : [e@x]
//C5 : [a@x, f@x]
//C6 : [g@x b@x]
//

//List<Set<String>>
//[
//[C1, C5, C3]
//[C2, C4]
//[C6]
//]


//treemap -> key rank , value -> List<Key>;
// map ->key value ,
// package LeetcodePrograms;
//
//import java.util.*;
//
//
// keys could be different but the rank could be same. so while evicting, evict it based on rank.
//
//
//public class RetainBestCache<K, V extends Rankable> {
//    // Add any fields you need here
//
//    class MyKeyValueStore {
//        K key;
//        V value;
//        MyKeyValueStore (K key, V value) {
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    HashMap<K, V> map = new HashMap();
//    PriorityQueue <MyKeyValueStore> queue = new PriorityQueue( new Comparator<MyKeyValueStore>() {
//        // to keep the lowest at the top
//        public int compare (kv1, kv2) {
//            Value v1 = kv1.value;
//            Value v2 = kv2.value;
//            return v1.getRank().compareTo(v2.getRank());
//        }
//    });
//    int capacity;
//    DataSourceImplementation datasource;
//    /* Constructor with a data source (assumed to be slow) and a cache size */
//    public RetainBestCache(DataSource<K, V> ds, int entriesToRetain) {
//        // Implementation here
//        this.capacity = entriesToRetain;
//        // original datasource
//        datasource = ds;
//    }
//
//    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
//     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
//     * evicting the V with lowest rank among the ones that it has available
//     * If there is a tie, the cache may choose any T with lowest rank to evict.
//     */
//    public V get(K key) {
//        // Implementation here
//        if (map.containsKey(key)) {
//            // update the rank somehow
//            return map.get(key);
//        } else {
//            // call from original datasource
//
//            V value = datasource.get(key);
//            if (value != null) {
//                // meaning found in datasource
//                queue.offer(new MyKeyValueStore(key, value));
//                updateCacheWhenMiss (key, value);;
//            }
//            return value;
//        }
//    }
//
//    private MyKeyValueStore getLowestRank () {
//        return queue.peek();
//    }
//
//    private void updateRank (K key) {
//        queue.offer(key);
//    }
//
//    // update the cache
//
//    private void updateCacheWhenMiss (K key, V value) {
//        if (map.size() < capacity) {
//            map.put(key, value);
//        } else {
//            // get the lowest rank
//            K keyToRemove = getLowestRank();
//            // remove from the queue
//            queue.poll();
//            //
//            map.remove(keyToRemove);
//            map.put(key, value);
//        }
//    }
//
//}
//
///*
// * For reference, here are the Rankable and DataSource interfaces.
// * You do not need to implement them, and should not make assumptions
// * about their implementations.
// */
//
//public interface Rankable {
//    /**
//     * Returns the Rank of this object, using some algorithm and potentially
//     * the internal state of the Rankable.
//     */
//    long getRank();
//}
//
//public interface DataSource<K, V extends Rankable> {
//    V get (K key);
//}
