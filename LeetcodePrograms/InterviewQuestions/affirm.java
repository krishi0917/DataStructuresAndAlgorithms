//package LeetcodePrograms;
//import java.util.*;
//import java.util.stream.Collectors;
///**
// * @author Rishi Khurana
// */
//public class affirm {
//    // package whatever; // don't place package name!
//
////    https://msngr.com/tkxslhcatcop
//
//
//    class Solution {
//        public HashMap<String, List<String>> pairing (List<List<Integer>> list) {
//
//            Iterator iter = list.iterator();
//            HashMap<String, HashMap<String, Integer>> map = new HashMap();
//            while (iter.hasNext()) {
//                List inner = (List<String>)iter.next();
//                Iterator innerItr = inner.iterator();
//
//                while (innerItr.hasNext()) {
//                    String key = (String)innerItr.next();
//                    populateMap (map, inner ,key);
//                }
//            }
//
//            HashMap<String, List<String>> result = new HashMap();
//
//            // Filter the less weighted ones and get result
//            filterMap(map, result);
//            return result;
//        }
//
//        public void filterMap (HashMap<String, HashMap<String, Integer>> map, HashMap<String, List<Stiring>> result) {
//
//            for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet())
//            {
//                HashMap<String,  Integer> inner = entry.getValue();
//                List<String> keys = inner.entrySet()
//                        .stream()
//                        .filter(innerEntry -> innerEntry.getValue() == Collections.max(inner.values()))
//                        .map(Map.Entry::getKey)
//                        .collect(Collectors.toList());
//
//                result.put(entry.getKey(), keys);
//            }
//        }
//
//        public void populateMap (HashMap<String, HashMap<String, Integer>> map, List<String> strings, String key) {
//            Iterator iter = strings.iterator();
//            if (!map.containsKey(key)) {
//                map.put(key, new HashMap<String, Integer>);
//            }
//
//            while (iter.hasNext()) {
//                String str = iter.next();
//                if (!str.equals(key)) {
//                    HashMap<String, Integer> innerMap = map.get(key);
//                    // innermap
//                    if (innerMap.containsKey(str)) {
//                        innerMap.put(str, innerMap.get(str) + 1);
//                    } else {
//                        innerMap.put(str, 1);
//                    }
//                }
//            }
//        }
//    }
//
//}
