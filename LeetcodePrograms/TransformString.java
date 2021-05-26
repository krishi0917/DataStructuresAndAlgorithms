package LeetcodePrograms;

import java.util.*;
//https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
// not working solution
/**
 * Created by rkhurana on 3/19/19.
 * Rearrange characters in a string such that no two adjacent are same
 */
public class TransformString {
    public void transformString(String str){
        HashMap<Character,Integer> map = new HashMap<>();

        for(int i =0 ; i < str.length() ; i++ ){
            char ch = str.charAt(i);
            //map.getOrDefault(ch , map.get(ch) +1);
            if (map.containsKey(ch))
                map.put(ch , map.get(ch)+1);
             else
                 map.put(ch,1);
        }

        List<Map.Entry> list = new ArrayList(map.entrySet());

    // Collections.sort(list, new Comparator<GenericHashMap.Entry<String, Integer>>() {
    // public int compare(GenericHashMap.Entry<String, Integer> a,
    // GenericHashMap.Entry<String, Integer> b) {
    // return b.getValue() - a.getValue();
    // }
    // });

		Collections.sort(list, new Comparator() {
        public int compare(Object o1, Object o2) {
            Integer v1 = (Integer) ((Map.Entry) o1).getValue();
            Integer v2 = (Integer) ((Map.Entry) o2).getValue();
            return v2.compareTo(v1);
        }
    });
		if(map.size()<2) return;
		StringBuilder sb = new StringBuilder();
        int i=0,j=i+1;
		while(j < str.length()){
                Map.Entry highestElement = list.get(i);
                Map.Entry secondHighestElement = list.get(j);
                sb.append(highestElement.getKey());
                sb.append(secondHighestElement.getKey());
                int firstelementValue = (int) list.get(i).getValue();
                firstelementValue--;
                if(firstelementValue == 0)
                       i++;j++;
            }

//            System.out.print("GetKey " + entry.getKey());
//            System.out.println(" GetValue " + entry.getValue());
        }

    public static void main(String[] args){
        TransformString ts = new TransformString();
        ts.transformString("aabcbbbdddddddda");
    }
}
