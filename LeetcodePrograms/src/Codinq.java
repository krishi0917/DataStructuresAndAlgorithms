package LeetcodePrograms.src;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import java.util.Stack;
import java.util.regex.*;
/**
 * @author Rishi Khurana
 */
public class Codinq {
    public  static String expressionsSecond(String computeExp, String[] expressions) {

        Map<String,String> graph = new HashMap<>();

        for(String expression : expressions){
            String[]tempArr = expression.split("=");
            String key = tempArr[0].substring(0,tempArr[0].length()-1);
            String value = tempArr[1].substring(1);

            //  System.out.println("key"+key+"value"+value);
            graph.put(key,value);
        }
        Map<String,Integer> actualValues = new HashMap<>();
        Queue<String[]> queue = new LinkedList<>();
        for(Map.Entry<String,String> entry : graph.entrySet()){
            String value = entry.getValue();
            if(checkforNumber(value)){
                actualValues.put(entry.getKey() , Integer.parseInt(entry.getValue()));
            }else{

                queue.offer(new String[]{entry.getKey(),entry.getValue()});
            }
        }

        while(!queue.isEmpty()){
            String []entry = queue.poll();
            if(actualValues.containsKey(entry[1])){
                int value = actualValues.get(entry[1]);
                actualValues.put(entry[0],value);
            }else{
                int value = calculate(entry[0],entry[1],actualValues );
                if(value == Integer.MAX_VALUE){
                    queue.offer(entry);
                    continue;
                }
                actualValues.put(entry[0],value);
            }
        }

        for(Map.Entry<String,Integer> map : actualValues.entrySet()){
            System.out.println("key" + map.getKey() + "value"+map.getValue());

        }
        return actualValues.get(computeExp).toString();

    }
    public static int calculate(String key, String s,Map<String,Integer> actualValues ){
        // T1 + T5

        System.out.println("coming herer" + s) ;
        String sArr[] = s.split(" ");
        int first = -1, second = -1;
        if(actualValues.get(sArr[0]) != null){
            first = actualValues.get(sArr[0]);
        }else {
            return Integer.MAX_VALUE;
        }

        if(actualValues.get(sArr[2]) != null) {
            second = actualValues.get(sArr[2]);
        }else {
            return Integer.MAX_VALUE;
        }
        if(sArr[1].equals("+")){
           // actualValues.put(s, first+second);
            return first+second;
        } else if(sArr[1].equals("-")){
            //actualValues.put(key, first - second );
            return first-second;
        }

        return Integer.MAX_VALUE;
    }

    public static boolean checkforNumber(String value){
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if(value == null )
            return false;
        Matcher m = p.matcher(value);

        return m.matches();
    }

    public static void main(String []args){
        String []expressions = {"Z1 = 10" , "Z4 = Z1 - Z2", "Z2 = 11 - Z3", "Z3 = 2 - Z1"};
        String computeExpression ="Z4";
        System.out.println(expressionsSecond(computeExpression,expressions));
    }

}
