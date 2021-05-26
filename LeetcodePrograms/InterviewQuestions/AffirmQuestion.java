package LeetcodePrograms.InterviewQuestions;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Rishi Khurana
 */
public class AffirmQuestion {
    public HashMap<String, List<String>> pairing (List<List<String>> list) {

        Iterator iter = list.iterator();
        HashMap<String, HashMap<String, Integer>> map = new HashMap();
        while (iter.hasNext()) {
            List inner = (List<String>)iter.next();
            Iterator innerItr = inner.iterator();

            while (innerItr.hasNext()) {
                String key = (String)innerItr.next();
                populateMap (map, inner ,key);
            }
        }



        HashMap<String, List<String>> result = new HashMap();

        // Filter the less weighted ones and get result
        filterMap(map, result);
        return result;
    }




    public void filterMap (HashMap<String, HashMap<String, Integer>> map, HashMap<String, List<String>> result) {

        for (Map.Entry<String, HashMap<String, Integer>> entry : map.entrySet())
        {
            HashMap<String,  Integer> inner = entry.getValue();
            List<String> keys = inner.entrySet()
                    .stream()
                    .filter(innerEntry -> innerEntry.getValue() == Collections.max(inner.values()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            result.put(entry.getKey(), keys);
        }

    }

    public void populateMap (HashMap<String, HashMap<String, Integer>> map, List<String> strings, String key) {
        Iterator iter = strings.iterator();
        if (!map.containsKey(key)) {
            map.put(key, new HashMap<String, Integer>());
        }

        while (iter.hasNext()) {
            String str = (String)iter.next();
            if (!str.equals(key)) {
                HashMap<String, Integer> innerMap = map.get(key);
                // innermap
                if (innerMap.containsKey(str)) {
                    innerMap.put(str, innerMap.get(str) + 1);
                } else {
                    innerMap.put(str, 1);
                }
            }
        }
    }


//    1 rishi
//    2 rahul
//    3 deepak
//    4 abhi
//    5 sunny

    public static void main(String[]args){
        List<String> list1 = Arrays.asList("rishi","rahul","deepak");

        List<String> list2 =Arrays.asList("rahul","deepak","abhi","sunny");
        List<String> list3 = Arrays.asList("rishi","abhi","sunny");
        List<String> list4 = Arrays.asList("rishi","abhi");
        List<String> list5 = Arrays.asList("rishi","sunny");
        List<List<String>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(list5);
        AffirmQuestion affirmQuestion = new AffirmQuestion();
        System.out.println(affirmQuestion.pairing(list));
    }

    public static void myPairing(List<List<String>> names) {


        Map<String,HashMap<String,Integer>> map = new HashMap<>();
        Iterator itr = names.iterator();
        while(itr.hasNext()) {
            List<String> innerList = (List<String>)itr.next();
            for(String s : innerList){


            }


        }
//            List<String> innerNameList = (List<String>)itr.next();
//            Iterator innerNameIterator = innerNameList.iterator();
//            while(innerNameIterator.hasNext()){
//                String innerName = (String)innerNameIterator.next();
//                createMap(innerNameList , map , innerName);
//            }
//
//        }
    }

    public static void createMap(List<String> innerNameList , Map<String,HashMap<String,Integer>> map, String key){
        Iterator iterator = innerNameList.iterator();
        while(iterator.hasNext()){
            String s = (String) iterator.next();

        }

    }
}
