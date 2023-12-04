package LeetcodePrograms.src;
/*
You categorize strings into three types: good, bad, or mixed. If a string has 3 consecutive vowels or 5 consecutive consonants,
or both, then it is categorized as bad. Otherwise it is categorized as good. Vowels in the English alphabet are ["a", "e", "i", "o", "u"] 
and all other letters are consonants.

The string can also contain the character ?, which can be replaced by either a vowel or a consonant. This means that the string 
"?aa"can be bad if ? is a vowel or good if it is a consonant. This kind of string is categorized as mixed.

Implement a function that takes a string s and returns its category: good, bad, or mixed.

Example

For s = "aeu", the output should be
classifyStrings(s) = "bad";

For s = "a?u", the output should be
classifyStrings(s) = "mixed";

For s = "aba", the output should be
classifyStrings(s) = "good".

Input/Output

[input] string s
A string that can contain only lowercase English letters and the character ?.
Guaranteed constraints:
1 ≤ s.length ≤ 50.

[output] string
good, bad or mixed.

aadaae
3
aad
a?a - mixed
daa
aae - 3vowel - bad => 

b???? 

5
aa
bb
*/

import java.util.HashSet;
import java.util.Set;

public class InterviewQuestion {
    //https://github.com/himanshukandwal/algorithms-design-and-analysis/blob/master/programming-practice/src/main/java/challenges/codefights/ClassifyStrings.java

    public static String _classifyStrings(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add ('a'); vowels.add ('e'); vowels.add ('i'); vowels.add ('o'); vowels.add ('u');

        int count = 0; boolean vowelFound = false, qmarkFound = false;
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt(idx);
            if (vowels.contains (ch)) {
                if (vowelFound) {
                    count ++;
                    if (count == 3)
                        return qmarkFound ? "mixed" : "bad";
                }
                else count = 1;
                qmarkFound = false; vowelFound = true;
            } else if (ch != '?') {
                if (!vowelFound) {
                    count ++;
                    if (count == 5)
                        return qmarkFound ? "mixed" : "bad";
                }
                else count = 1;
                qmarkFound = false; vowelFound = false;
            } else {
                if ((vowelFound && count == 2) || (!vowelFound && count == 4))
                    return "mixed";
                count ++; qmarkFound = true;
            }
        }
        return "good";
    }
    public static void main(String []args){
//        System.out.println(runTest(_classifyStrings("aeu"), "bad"));
//        System.out.println(runTest(_classifyStrings("a?u"), "mixed"));
//        System.out.println(runTest(_classifyStrings("aba"), "good"));
//        System.out.println(runTest(_classifyStrings("abaaa"), "bad"));
//        System.out.println(runTest(_classifyStrings("aa??bbb"), "mixed"));
        System.out.println(runTest(_classifyStrings("aa?bbbb"), "bad"));
      //  System.out.println(runTest(_classifyStrings("aa?bbb?a?bbbb"), "bad"));
    }
    public static boolean runTest(String expected, String actual){
        if(expected.equals(actual)){
            return true;
        }else{
            return false;
        }
    }
}
