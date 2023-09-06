package LeetcodePrograms.src;
/*
833. Find And Replace in String
You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed
parallel arrays, indices, sources, and targets, all of length k.

To complete the ith replacement operation:

Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
Otherwise if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other.
The testcases will be generated such that the replacements will not overlap.

For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.

Example 1:
Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
 */

import java.util.Arrays;
// Approach:
// basically first step is finding if the all indices that needs to be replaced are valid ones or not.
// in the second method we just replace those characters with the new one
public class FindReplaceString {
    public static String findAndReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int N = s.length();
        int []match = new int[N];
        Arrays.fill(match,-1);
        for(int i = 0 ; i < indices.length ; i++){
            int ix = indices[i];
            if(
                    s.substring(ix,ix+sources[i].length()).equals(sources[i])){
                match[ix] = i;
            }
        }
//        for(int i = indices.length - 1 ; i>=0 ; i--){
//            int ix = indices[i];
//            if(match[ix] >= 0){
//                continue;
//            }
//            if(s.substring(ix,ix+sources[i].length()).equals(sources[i])){
//                match[ix] = i;
//            }
//        }
        StringBuilder answer = new StringBuilder();
        int index = 0;
        while (index < N){
            if(match[index] >= 0){
                answer.append(targets[match[index]]);
                index += sources[match[index]].length();
            }else{
                answer.append(s.charAt(index++));
            }
        }
        return answer.toString();
    }

    public static void main(String []args){
        // this below test case fails. Rest all the test cases are passing.
        String s = "abcde";
        int[] indices = {2, 2};
        String[] sources = {"cdef", "bc"};
        String []targets = {"f", "fe"};
        System.out.println(FindReplaceString.findAndReplaceString(s,indices,sources,targets));
    }
}
