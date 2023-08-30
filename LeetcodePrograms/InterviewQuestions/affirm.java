package LeetcodePrograms.InterviewQuestions;//package LeetcodePrograms;
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

import java.util.*;
// coins = 400
// menu = {
//     "coffee": 100,
//     "cake": 125,
//     "popcorn": 200
// }

// ## Expected Output
// [
//     [], # 0
//     ['coffee'], # 100
//     ['cake'],   # 125
//     ['coffee', 'cake'], # 225
//     ['popcorn'], # 200
//     ['coffee', 'popcorn'], # 300
//     ['cake', 'popcorn'] # 325
// ]

public class affirm{

    public void findCombination(int totalCoins, Map<String,Integer> menu){
        List<Integer> nums = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        for(String s : menu.keySet()){
            nums.add(menu.get(s));
        }
        Collections.sort(nums);
        findCombinationUtil(list, new ArrayList<>(), totalCoins,nums,0,0);

        System.out.println("list result" + list);
    }

    private void findCombinationUtil(List<List<Integer>> resultList, List<Integer> tempList, int totalCoins, List<Integer>nums,int start , int currentSum){
        resultList.add(new ArrayList<>(tempList));
        for(int i = start ; i < nums.size() ; i++){
            if(currentSum > totalCoins)
                return ;
            currentSum = currentSum + nums.get(i);
            System.out.println("total sum" + currentSum);
            if(currentSum < totalCoins){
                tempList.add(nums.get(i));
                findCombinationUtil(resultList, tempList, totalCoins,nums,i+1,currentSum);
                currentSum = currentSum - nums.get(tempList.size()-1);
                tempList.remove(tempList.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        affirm s = new affirm();
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("coffee" ,100 );
        map.put("cake" ,125 );
        map.put("popcorn" ,200 );
        s.findCombination(400 , map);
    }
}
