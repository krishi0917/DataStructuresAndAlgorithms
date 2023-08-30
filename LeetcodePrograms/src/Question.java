package LeetcodePrograms.src;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Rishi Khurana
 */
public class Question {
    public static void main(String []args){
        String s1 = "Welcome";
        String s2 = new String("Welcome");
        String s3= s2;
       // System.out.println(s3.equals(s1));


        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(2,3);
        treeMap.put(1,2);
        Integer i = treeMap.firstKey();
        Map.Entry<Integer,Integer> firstEntry = treeMap.firstEntry();
        System.out.println("first entry" + firstEntry + "  firstkey " + i) ;

    }

}

