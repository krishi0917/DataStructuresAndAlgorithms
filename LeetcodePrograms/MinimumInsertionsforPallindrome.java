package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 10/20/18.
 */

//not sure if this will work or not
public class MinimumInsertionsforPallindrome {

 public static int minimumInsertions(String s){
     int count =0;
     HashMap<Character,Integer> map = new HashMap<>();

     for(int i = 0 ; i < s.length() ; i++ ){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
            else
                map.put(s.charAt(i) , 1);
     }

    for(Map.Entry<Character,Integer> entry : map.entrySet() ){
        if(entry.getValue() % 2 == 0){
            //its a pallindrome..dont have to insert anything
        }
        else{
            count++;
        }
    }

    if(count > 1)
        return count - 1;

     else
         return 0;
 }
}
