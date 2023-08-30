package LeetcodePrograms.InterviewQuestions.LinkedinRetainBestCache;

import java.util.*;

public class RetainBestCacheQuestion2<K, V extends Rankable> {

    int capacity;
    DataSource dataSource;
    HashMap<K, V> cacheMap ;
    class MyKeyValueStore {
        K key;
        V value;
        MyKeyValueStore(K key, V value) { // or we can create a constructor which stores the rank as well
            this.key = key;
            this.value = value;
        }

        long rankifRequired;
        MyKeyValueStore(K key, V value , Long rankable) { // or we can create a constructor which stores the rank as well
            this.key = key;
            this.value = value;
            rankifRequired = rankable;
        }
    }

    Queue<MyKeyValueStore> queue = new PriorityQueue<>(new Comparator<MyKeyValueStore>() {
        @Override
        public int compare(MyKeyValueStore o1, MyKeyValueStore o2) {
            Long l1 = o1.rankifRequired;
            Long l2 = o2.rankifRequired;
            return l1.compareTo(l2);
        }
    });

    public RetainBestCacheQuestion2(DataSource<K, V> ds, int entriesToRetain){
        capacity = entriesToRetain;
        dataSource = ds;
        cacheMap = new HashMap();
    }

    private void updateCacheIfRequired(K key , V value){

        if(cacheMap.size() == capacity){ // capacity reached find the element with the lowest rank and evict and insert this entry
            MyKeyValueStore myKeyValueStore = queue.poll(); // deleted from queue
            cacheMap.remove(myKeyValueStore.key); // deleted from map
        }

        // put new pair in queue and map
        queue.offer(new MyKeyValueStore(key,value));
        //queue.offer(new MyKeyValueStore(key,value,rankable.getRank()));

        cacheMap.put(key,value);
    }

    // if key is not present get it from datasource and put it into cache and evict the cache with the lowest rank
    public V getKey(K key){
        if (cacheMap.containsKey(key)) {
            return cacheMap.get(key);
        }else{
            V value = (V) dataSource.get(key);
            updateCacheIfRequired(key,value);
            return value;
        }
    }

    public void put(K key, V value){

    }

}
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