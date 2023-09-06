package LeetcodePrograms.src;

import java.util.Stack;


// - works for / and *
// - doesnt matter for lhs stuff
// / works for none

public class Navan_RedundantParenthesis {
    public static String removeBrackets(String s){
        //code here
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();
        for (int i=0; i<n; i++) {
            char ch = str[i];
            if (ch == '(' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                st.push(i);
            } else if (ch == ')') {
                boolean add = false;
                boolean min = false;
                boolean mul = false;
                boolean div = false;
                while (!st.isEmpty() && str[st.peek()] != '(') {
                    char op=str[st.pop()]; // keep removing  characters from the stack until we encounter (
                    if (op == '+') {
                        add = true;
                    } else if (op == '-') {
                        min = true;
                    } else if (op == '*') {
                        mul = true;
                    } else {
                        div = true;
                    }
                }
                int j = st.pop();
                int prev = i++; // maintain the ith location so that if true, this can be a part of removal
                // i++ to operate the next parenthesis
                while (!st.isEmpty() && str[st.peek()]=='(' && i < n && str[i] == ')') { // this branck is for marking the redundant parenthesis
                    visited[st.pop()] = true;
                    visited[i++] = true;
                }
                i--;
                if (i+1 < n && (str[i + 1] == '*' && (add || min || div) || str[i + 1] == '/' && (add || min || mul || div))) {
                    continue;
                }
                // the above block can be divided into two parts
                // but what it means is basically when ths sign is *, acceptable sign is only * for the remaining string
                // but if it is division, no sign is acceptable and thus cannot be removed. hence continue
                if (st.isEmpty() ||
                        !add && !min && !mul && !div ||
                        str[st.peek()] == '(' ||
                        str[st.peek()] == '+' ||
                        str[st.peek()] == '-' && !add && !min ||
                        str[st.peek()] == '*' && (mul || div) && !add && !min) {
                    visited[j] = true;
                    visited[prev] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String []args){
        System.out.println(removeBrackets("(A+(B+C))"));
    }
}
