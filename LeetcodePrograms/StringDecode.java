package LeetcodePrograms;

import java.util.Stack;
import javax.print.DocFlavor;


/**
 * @author Rishi Khurana
 */
public class StringDecode {
    // 3[a]2[bc] = aaabcbc
    // 3[a2[c]] = accaccacc

    public String decodeString(String str){

        StringBuilder finalString = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i=0 ; i < str.length() ; i++){
            Character ch = str.charAt(i);
            if(Character.isDigit(ch)){
                stack.push(ch);
            }else if(ch == '['){
                stack.push(ch);
            }else if (ch == ']'){
                StringBuilder sb = new StringBuilder();
                while(stack.peek() != '['){
                    sb.append(stack.pop());
                }
                stack.pop(); // popping [
                int num = stack.pop() - '0';
                sb.reverse();
                for(int j = num ; j > 0 ; j--){
                    finalString.append(sb);
                }
            }
            else { //means a character
                stack.push(ch);

            }
        }
        return finalString.toString();
    }

    public static void main(String [] args){
        StringDecode s = new StringDecode() ;
        System.out.println(s.decodeString("3[a2[c]]"));
    }
}
