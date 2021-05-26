package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkhurana on 7/26/18.
 */
public class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        dfs(s,0,list,res);
        return res;
    }

    public static void dfs(String s, int pos, List<String> list, List<List<String>> res){
        if(pos==s.length())
            res.add(new ArrayList<String>(list));
        else{
            for(int i=pos;i<s.length();i++){
                if(isPal(s,pos,i)){
                    list.add(s.substring(pos,i+1));
                    dfs(s,i+1,list,res);
                    list.remove(list.size()-1);
                }
            }
        }
    }

    public static boolean isPal(String s, int low, int high){
        while(low<high) if(s.charAt(low++)!=s.charAt(high--))
            return false;
        return true;
    }

    public static void main(String []args){
       System.out.println(partition("aab"));
    }
}
