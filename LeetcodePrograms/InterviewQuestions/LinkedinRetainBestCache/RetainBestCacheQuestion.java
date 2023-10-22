package LeetcodePrograms.InterviewQuestions.LinkedinRetainBestCache;
import java.util.*;
public class RetainBestCacheQuestion <K, V extends Rankable> {

    int capacity;
    DataSource dataSource;
    HashMap<K, V> cacheMap ;
    TreeMap<Long, List<K>> sortByRankMap;

    Rankable rankable;
    public RetainBestCacheQuestion(DataSource<K, V> ds,  int entriesToRetain){
        capacity = entriesToRetain;
        dataSource = ds;
        cacheMap = new HashMap();
        sortByRankMap = new TreeMap<>();
    }

    // if key is not present get it from datasource and put it into cache and evict the cache with the lowest rank
    public void getKey(K key){
        if (cacheMap.containsKey(key)) {
            cacheMap.get(key);
        }else{
            V value = (V) dataSource.get(key);
            if(cacheMap.size() == capacity){ // capacity of the cache map is full, evict the entry with the lowest rank
                // Long firstKey =  sortByRankMap.firstKey();
                // if(sortByRankMap.get(firstKey).size() == 1) // only 1 value is present for this key, remove the entry directly
                //       sortByRankMap.remove(firstKey);

                Map.Entry<Long,List<K>> tempLowestRankEntry = sortByRankMap.firstEntry();
                if(tempLowestRankEntry.getValue().size() == 1) { // only 1 value is present for this key, remove the entry directly
                    sortByRankMap.remove(tempLowestRankEntry.getKey());
                    cacheMap.remove(tempLowestRankEntry.getValue().get(0)); //since its a list having only 1 key. Removing the first one will remove
                }else{
                    List<K> tempLowestRankEntryList = sortByRankMap.firstEntry().getValue();
                    K tempKey = tempLowestRankEntryList.get(tempLowestRankEntryList.size()-1); // got the last key
                    tempLowestRankEntryList.remove(tempLowestRankEntryList.size()-1);

                    // or
                    // sortByRankMap.firstEntry().getValue().remove(tempLowestRankEntryList.get(tempLowestRankEntryList.size()-1));

                    cacheMap.remove(tempKey);
                }
                cacheMap.put(key,value);
                // upadte the sortByrankMap
                // need to create a method wihch can be used in both if and else
                // we still need to put the new element
            }else{ // capacity is not full, just update the CacheMap and SortByRankMap
                cacheMap.put(key,value);
                Long rank = rankable.getRank();
                if(sortByRankMap.containsKey(rank)){ // Elements of the same rank is present
                    List<K> tempSortedRankList = sortByRankMap.get(rank);
                    tempSortedRankList.add(key);
                    sortByRankMap.put(rank,tempSortedRankList);
                }else{ // no element of the same rank is present
                    List<K> tempNewRankList = new ArrayList<>();
                    tempNewRankList.add(key);
                    sortByRankMap.put(rank,tempNewRankList);
                }
            }
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