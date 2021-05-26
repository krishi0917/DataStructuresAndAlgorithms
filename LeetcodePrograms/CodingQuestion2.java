package LeetcodePrograms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import java.util.Stack;
import java.util.regex.*;

/**
 * @author Rishi Khurana
 */
public class CodingQuestion2 {

    public static int CodingQuestion21(String[] input , String query){
            Map<String, String> graph = new HashMap<>();
            for(String temp : input) {
                String []tempArr = temp.split("=");
                graph.put(tempArr[0] , tempArr[1]);
            }
            Map<String, Integer>  actualValues = new HashMap<>();
            Queue<String[]> queue = new LinkedList<>();
            for(Map.Entry<String,String> entry : graph.entrySet()){
                String value  = entry.getValue();
                if(checkForNumber(value)) { //its a number only
                    actualValues.put(entry.getKey() , Integer.parseInt(entry.getValue()));
                }else{
                    queue.offer(new String[]{entry.getKey(),entry.getValue()});
                }
            }

            while(!queue.isEmpty()){
                String[] entry =  queue.poll();
                if(actualValues.containsKey(entry[1])){
                    int value = actualValues.get(entry[1]);
                    actualValues.put(entry[0],value);
                }else{
                    // you have to parse the complete file and calculate
                    int value = calculate(entry[1],actualValues);
                    if(value == -1){
                        queue.offer(entry);
                        continue;
                    }
                    actualValues.put(entry[0],value);
                }
            }
            return actualValues.get(query);
    }

    public static int calculate(String s , Map<String,Integer> actualValues){
        int num = 0 ;
        char sign ='+';
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0 ; i < s.length() ; i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if(actualValues.get(String.valueOf(s.charAt(i))) != null){
                    num = actualValues.get(String.valueOf(s.charAt(i)));
                    // stack.push(actualValues.get(String.valueOf(s.charAt(i))));
            }
            if(s.charAt(i) =='-' || s.charAt(i)== '+' || s.charAt(i) == '/' || s.charAt(i) == '*' ||  i==s.length()-1){
                if(sign=='-'){
                    stack.push(-num);
                }
                if(sign=='+'){
                    stack.push(num);
                }
                if(sign=='*'){
                    stack.push(stack.pop()*num);
                }
                if(sign=='/'){
                    stack.push(stack.pop()/num);
                }
                sign = s.charAt(i);
                num = 0;
            }else{
                if(num == 0) // an element which is not yet processed
                    return -1;
            }
        }

        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }

    private static boolean checkForNumber(String value){
        String regex = "[0-9]+";

        Pattern p = Pattern.compile(regex);

        if (value == null) {
            return false;
        }

        Matcher m = p.matcher(value);

        return m.matches();

    }

    public static void main(String []args){
        String []expression = {"a=b+3" , "b=2"  };
        String []expression1 = { "f=d+g","d=a+e","a=b+c","b=2" ,"c=5","g=7","e=2"  };
        String[] expression3 = {"Z1=10"};
        System.out.println(CodingQuestion21(expression , "a"));
        System.out.println(CodingQuestion21(expression1 , "f"));
    }

}
