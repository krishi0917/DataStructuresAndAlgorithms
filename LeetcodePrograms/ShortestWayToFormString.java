package LeetcodePrograms;

import java.util.HashSet;


/**
 * @author Rishi Khurana
 * 1055. Shortest Way to Form String
 * * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no
 *  * deletions).
 *  *
 *  * Given two strings source and target, return the minimum number of subsequences of source such that their
 *  * concatenation equals target. If the task is impossible, return -1.
 */

//Accepted Solution
public class ShortestWayToFormString {
    public static int shortestWay(String source , String target){
        int iSource =0;
        int jTarget =0;
        int count =1;
        HashSet<Character> set = new HashSet<>();

        for(char ch : source.toCharArray() )
            set.add(ch);  // just to make sure if any of the target element is not present in the source, there is no
        // way you can create the target from that source
        while(jTarget < target.length()){
            if(!set.contains(target.charAt(jTarget))) {
                //element not present
                return -1;
            }
            else if(jTarget >= target.length())
                return count;

            else if(iSource >= source.length()) {
                //reinnitialise iSource to 0
                iSource=0;
                count++;
            }
            else if(source.charAt(iSource) == target.charAt(jTarget)){
                iSource++;
                jTarget++;
            }

            else{
                iSource++;
            }

        }
        return count;
    }
    public static void main(String []args){
        System.out.println(shortestWay("abc","abcbc"));
        System.out.println(shortestWay("abc","acdbc"));
        System.out.println(shortestWay("xyz","xzyxz"));
    }
}
