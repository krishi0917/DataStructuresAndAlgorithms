package LeetcodePrograms;
import java.util.Stack;

/**
 * Created by rkhurana on 3/26/19.
 */
public class ValidParenthesis {
    public boolean isValidParentheses(String s) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
        if (c == '(')
            stack.push(')');
        else if (c == '{')
            stack.push('}');
        else if (c == '[')
            stack.push(']');
        else if (stack.isEmpty() || stack.pop() != c)
            return false;
    }
    return stack.isEmpty();
    }
    public static void main(String []args){
        ValidParenthesis vp = new ValidParenthesis();
        System.out.println(vp.isValidParentheses("({})"));
    }
}
