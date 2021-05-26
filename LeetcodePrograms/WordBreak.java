package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 7/19/18.
 */
public class WordBreak {
    public static boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

	/* First DP
	for(int i = 1; i <= s.length(); i++){
		for(String str: dict){
			if(str.length() <= i){
	    		if(f[i - str.length()]){
	        		if(s.substring(i-str.length(), i).equals(str)){
						f[i] = true;
	            		break;
	            	}
	            }
	        }
	    }
	}*/

// Second DP
//amazing solution
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true; // this means the word is valid
                    break;
                }
            }
        }
        return f[s.length()];
    }

    static HashMap<String,List<String>> map1 = new HashMap<String,List<String>>();
    public static List<String> wordBreak2(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map1.containsKey(s)) {
            return map1.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i);
            if(wordDict.contains(t)) {
                List<String> temp = wordBreak2(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) {
                    for(int j = 0 ; j < temp.size() ; j++) {
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map1.put(s , res);
        return res;
    }

    private static final Map<String, List<String>> cache = new HashMap<>();

    private static boolean containsSuffix(Set<String> dict, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (dict.contains(str.substring(i))) return true;
        }
        return false;
    }

    public static List<String> wordBreak3(String s, Set<String> dict) {
        if (cache.containsKey(s)) return cache.get(s);
        List<String> result = new LinkedList<>();
        if (dict.contains(s)) result.add(s);
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i), right = s.substring(i);
            if (dict.contains(left) && containsSuffix(dict, right)) {
                for (String ss : wordBreak3(right, dict)) {
                    result.add(left + " " + ss);
                }
            }
        }
        cache.put(s, result);
        return result;
    }


    static HashMap<String, List<String>> map = new HashMap<String, List<String>>();

    public static List<String> wordBreak4(String s, Set<String> wordDict) {

        List<String> result = new ArrayList<String>();

        for(int j=s.length()-1;j>=0;j--){

            if (map.containsKey(s))
                return map.get(s);

            if(wordDict.contains(s.substring(j))){
                break;
            }
            else{
                if(j==0){
                    return result;
                }
            }
        }

        for(int i=0; i < s.length() - 1; i++){

            if(wordDict.contains(s.substring(0,i+1))){

                List<String> strings = wordBreak4(s.substring(i+1, s.length()), wordDict);

                if(strings.size() != 0){
                    for(String str : strings){
                        result.add(s.substring(0, i+1) + " " + str);
                    }
                }
            }
        }

        map.put(s,result);

        if(wordDict.contains(s)){
            result.add(s);
        }
        return result;
    }


    public static void main(String []args){
        String s = "catsanddog";
        String wordDict[] = {"cat", "cats", "and", "sand", "dog"};
        String str = "applepenapple";
        Set<String> set = new HashSet<>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");

        System.out.println(wordBreak4(s,set));
    }
}
