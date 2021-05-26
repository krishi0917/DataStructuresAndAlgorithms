package LeetcodePrograms;
import java.util.Stack;

/**
 * Created by rkhurana on 7/12/18.
 */
//digit: it should be one digit from the current number
//'+': number is over, we can add the previous number and start a new number
//'-': same as above
//'(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
//')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result
// before this pair of parenthesis. We add them together.
//Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.

public class Calculator {
    public static int calculate1(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }

    public static int calculate2(String s) {
        int len;
        if(s==null || (len = s.length())==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if((!Character.isDigit(s.charAt(i)) && ' '!=s.charAt(i)) || i==len-1){
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
            }
        }
        int re = 0;
        for(int i:stack){
            re += i;
        }
        return re;
    }

    public static void main(String [] args){

        System.out.println(calculate1 ("-24-6"));
    //   System.out.println(calculate2 ("-25+2 / 2"));

    }
}
