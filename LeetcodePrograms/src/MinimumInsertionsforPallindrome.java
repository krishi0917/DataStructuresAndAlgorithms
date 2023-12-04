package LeetcodePrograms.src;

import java.util.*;

/**
 * Created by rkhurana on 10/20/18.
 */

//not sure if this will work or not
public class MinimumInsertionsforPallindrome {


     // Minimum insertions to form a palindrome #GoodQuestion
     // Base Case For Recursive Solution : if l > h (we crossed pointers) return INT_MAX
     // if l == h return 0; (only one character, which is already palindrome, 0 insertion is required to make it palindrome)
     // if l == h-1 and if str[l] == str[h] return 0; (if two length string example “aa or ab” and both characters are same ie
     // : “aa” , its already palindrome,  so return 0)
     // if l == h-1 and if str[l] != str[h] return 1; (if two length string is there example “ab” and both characters are
     // different , we need 1 insertion to make this string a palindrome one ie “bab” or “aba“
     // very well explained. https://www.youtube.com/watch?v=DOnK40BvazI
     static int findMinInsertionsRec(char str[], int l, int h) {
         if (l > h)
             return Integer.MAX_VALUE;
         if (l == h)
             return 0; // only 1 character
         if (l == h - 1)
             return (str[l] == str[h]) ? 0 : 1;
         // this is for aa , ab type of strings when there is only 2 characters

         return (str[l] == str[h]) ? findMinInsertionsRec(str, l + 1, h - 1)
                 : (Math.min(findMinInsertionsRec(str, l, h - 1), findMinInsertionsRec(str, l + 1, h)) + 1);
     }

    static int findMinInsertionsDP(char str[], int n) {
         int[][] table = new int[n][n];
         int l, h, len;
         // memset(table, 0, sizeof(table));
         Arrays.fill(table, 0);
         for (len = 1; len < n; len++) // ++gap for length of the string as 1,2,3,4..n
             for (l = 0, h = len; h < n; l++, h++) // it was ++l,++h
                 table[l][h] = (str[l] == str[h]) ? table[l + 1][h - 1]
                         : (Math.min(table[l][h - 1], table[l + 1][h]) + 1);
         return table[0][n - 1];
     }


    public static int minInsertions2(String s) {
        return  findMinInsertionsDP(s.toCharArray(),s.length());

    }
    static int findMinInsertionsDP2(char str[], int n) {
        int[][] table = new int[n][n];
        int l, h, len;
        // memset(table, 0, sizeof(table));
        Arrays.fill(table, 0);
        for (len = 1; len < n; len++) // ++gap for length of the string as 1,2,3,4..n
            for (l = 0, h = len; h < n; l++, h++) // it was ++l,++h
                table[l][h] = (str[l] == str[h]) ? table[l + 1][h - 1]
                        : (Math.min(table[l][h - 1], table[l + 1][h]) + 1);
        return table[0][n - 1];
    }

    public static int minInsertions(String s) {
        MinimumInsertionsforPallindrome minimumInsertionsforPallindrome = new MinimumInsertionsforPallindrome();
        int i =  findMinInsertionsRec(s.toCharArray(),0,s.length()-1);
        return -1;
    }


 public static void main(String []args){
     int min = minInsertions2("zzazz");


 }
}
