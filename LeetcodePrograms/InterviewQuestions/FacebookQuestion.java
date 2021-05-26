package LeetcodePrograms.InterviewQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author Rishi Khurana
 *  Evaluate Prefix Notation
 *             * input: “( * 3 6 ( + 2 4 ) 2 )” -> (* 3 6 6 2) -> 216 ->  ( * 3 6 12)
 *             Facebook asked me
 */
public class FacebookQuestion {

    public static int calculate(String input){
        String[] inputArr = input.split(" ");
        Stack<String> stack = new Stack<>();
        int result = 0;
        for(int i = 0 ; i < inputArr.length ; i++){
            if(!inputArr[i].equals(")")){
                stack.push(inputArr[i]);
            }else{

                List<String> temp = new ArrayList<>();
                while(!stack.peek().equals ("+") &&  !stack.peek().equals("*")) {
                    temp.add(stack.pop());
                }
                String operator = stack.pop();
                stack.pop();
                if(operator.equals("+")){
                    int sum = 0;
                    for(int j = 0 ; j < temp.size() ; j++) {
                        sum = sum + Integer.valueOf(temp.get(j));

                    }
                    result = result + sum;
                }else{
                    int sum = 1;
                    for(int j = 0 ; j < temp.size() ; j++) {
                        sum = sum * Integer.valueOf(temp.get(j));
                    }
                    result = result * sum;
                }

            }
        }
        return result;
    }

    public static void main(String[]args){
        System.out.println(calculate("( + 2 12 ( * 3 ( + 1 2 ) ) )"));
    }
}
