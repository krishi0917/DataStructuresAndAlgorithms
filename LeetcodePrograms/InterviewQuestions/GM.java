package LeetcodePrograms.InterviewQuestions;

//Write a method to perform string decompression.
//General Motors
//Examples provided:
//
//javascript
//Copy
//Edit
//abcd -> abcd
//a(bc){2} -> abcbc
//a(bc){2}(d){3}e -> abcbcdcdde
//a(bc(d){3}){2} -> abcddbcddd

import java.util.*;

    public class GM {
        public static String decompress(String s){
            Stack<StringBuilder> stringStack = new Stack<>();
            StringBuilder current = new StringBuilder();
            int i = 0;
            while(i < s.length()){
                char ch = s.charAt(i); // ch = a  current = a
                if(Character.isLetter(ch)){
                    current.append(ch);
                    i++;
                }
                // a(bc){2}
                else if(ch == '('){ // current = d
                    stringStack.push(current); // stringStack = a ,bc,
                    current = new StringBuilder();
                    i++;
                }
                else if(ch == ')'){
                    i++;
                }
                else if(ch == '{'){
                    int j = i + 1;
                    int num = 0;
                    while(s.charAt(j) != '}'){
                        num = num * 10 + (s.charAt(j) - '0');
                        j++;
                    }
                    i = j + 1;
                    StringBuilder prev = stringStack.pop(); // last string before '(' a
                    StringBuilder temp = new StringBuilder();

                    for(int k = 0 ; k < num ; k++){
                        temp.append(current); // bcbc
                    }
                    current = prev.append(temp); // current = abcbc
                }
            }
            return current.toString();
        }


        public static void main(String [] args) {
            // you can write to stdout for debugging purposes, e.g.
            System.out.println(decompress("(bc){2}a"));

            System.out.println(decompress("a(bc(d){3}a){2}"));
        }


}
