package LeetcodePrograms;

import java.util.Arrays;
import java.util.List;


/**
 * 459. Repeated Substring Pattern
 * @author Rishi Khurana
 * What is the algorithmic approach to check if a given string S = nT
 * (i.e to find if a given string can be represented from a substring by iterating it “n” times)?
 */
public class RepeatedSubstringPattern {
    public static boolean isValid(String s){
        if(s.length()==0) return false;
        int firstIndex=0,secondIndex=1;
        while(secondIndex<s.length()){
            if(s.charAt(firstIndex)==s.charAt(secondIndex))
                firstIndex++;
            else{
                if(firstIndex!=0)
                    secondIndex--;
                firstIndex=0;
            }
            secondIndex++;
        }

        if((secondIndex-firstIndex)!=s.length()
                && s.length()%(secondIndex-firstIndex)==0){
            String pattern="";
            for(int i=firstIndex;i<secondIndex;i++)
                pattern += s.charAt(i);
            System.out.println(pattern + " , ");
        }
        return ((secondIndex-firstIndex)!=s.length() && s.length()%(secondIndex-firstIndex)==0);
    }

        public static void main(String []args) {
            List<String> input = Arrays.asList( "ABCABCABC");

// AAAACAAAACAAAA
//                    "ABCABCABC", "ABABAB", "ABCDABCD",
//                    "AAAACAAAAC", "ABCDABC", "AABAABAABAAB",
//                    "AABBAABBAAB", "AA", "A", "ABCABABCAB" );

            for (String i : input) {
                System.out.println(i + " , " + repeatedSubstringPattern(i) );
            }
        }

// this one only tells it if it is true or false.. pattern you get it in the above solution
    public static boolean repeatedSubstringPattern(String str) {
        int l = str.length();
        for(int i=l/2;i>=1;i--) {
            if(l%i==0) {
                int m = l/i;
                String subS = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<m;j++) {
                    sb.append(subS);
                }
                if(sb.toString().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

}