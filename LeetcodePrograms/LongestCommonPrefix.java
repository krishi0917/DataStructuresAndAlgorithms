package LeetcodePrograms;

import java.util.Arrays;

/**
 * Created by rkhurana on 10/11/18.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs != null && strs.length > 0) {
            Arrays.sort(strs);
            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();
            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
            return result.toString();
        }
        return result.toString();
    }


 //    or horizontal scaling

//    what is the time complexity.
//    m is the lenght of the longest string
//    n is the length of the strs
//    so O(n* m* mm) because indexOf is mm, the substring is O(m) am I right ?
//
//    I think ...
//
//    O(n * m * (m + m)), which is O(n * m * 2m), which is O(n * m^2)
//    Because for n strings it does m times indexOf (which is O(m)) and m times substring (which is O(m))


    public static String longestCommonPrefix4(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) { // so indexOf checks if prefix starts with strs[i] starting index 0
                // now when prefix is flow and strs[i] is flower..you see flower starts with flow that means the
                // value of indexOf will be 0 and thus it wont go inside of that and will go for the next iteration.
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    // time complexity for the below algorith m is O(n * m) .. n number of string and m is the length of the longest
    // string
    // Vertical Scaling
    // like checking the first character of every string from the first string
    public static String longestCommonPrefix5(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    // first check condition means that the element which we are iterating might be smaller than the
                    // first element and second condition means character matches so we have to continue moving it to
                    // the other strings
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String [] args){
        String [] str = {"flow","flower","flight"};
        System.out.println(longestCommonPrefix4(str));
    }
}
