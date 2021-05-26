package LeetcodePrograms;
import java.util.*;

public class LongestSubstring {


    int lengthOfLongestSubstringKDistinct(String str, int k) {
        int s = 0, e = 0, maxlen = 0;
        Map<Character, Integer> dic = new HashMap<>();
        while (e < str.length()) {
            char c = str.charAt(e);
            if (dic.containsKey(c)) {
                dic.put(c, dic.get(c)+1);

            } else {
                dic.put(c, 1);
            }

            while (dic.size() > k) {
                c = str.charAt(s);
                if (dic.get(c) == 0)
                    dic.remove(c);
                else
                    dic.put(c, dic.get(c)-1);

                ++s;
            }
            maxlen = Math.max(maxlen, e - s + 1);
            ++e;
        }
        return maxlen;
    }


    public int lengthOfLongestSubstringKDistinct22(String s, int k) {
        if(k==0 || s==null || s.length()==0)
            return 0;

        if(s.length()<k)
            return s.length();

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        int maxLen=k;
        int left=0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }

            if(map.size()>k){
                maxLen=Math.max(maxLen, i-left);

                while(map.size()>k){

                    char fc = s.charAt(left);
                    if(map.get(fc)==1){
                        map.remove(fc);
                    }else{
                        map.put(fc, map.get(fc)-1);
                    }

                    left++;
                }
            }

        }

        maxLen = Math.max(maxLen, s.length()-left);

        return maxLen;
    }

    public int LongestSubstringWithoutRepeatingCharacter(String s) {
        if (s.length() == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(map.containsKey(s.charAt(i))){

                j = Math.max(j,map.get(s.charAt(i))+1); //find the old index
            }
            map.put(s.charAt(i) , i);
            max = Math.max(max , i - j + 1);
            // String str = s.substring(j,i); check if this line works
        }
        return max;
    }

    public static String LongestContinousSubstring2(String str){
        int low = 0 ;
        //List<String> list = new ArrayList<String>();
        String result="";
        if(str.length() == 0)
            return "";

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0 ; i < str.length() ; i++){
            if(map.get(str.charAt(i))!= null){
                if( i - low> result.length()){
                    result = str.substring(low,i);
                }

                low = Math.max(map.get(str.charAt(i)) +1,low);
            }
            map.put(str.charAt(i),i);
        }
        return result;

    }

//if you want all the strings (list) of the same size to be printed
public static List<String> LongestContinousSubstring(String str){
    int low = 0 ;
    List<String> list = new ArrayList<String>();

    if(str.length() == 0)
        return list;
    String result ="";
    HashMap<Character,Integer> map = new HashMap<>();
    for(int i=0 ; i < str.length() ; i++){
    if(map.get(str.charAt(i))!= null){
        if( (i) - low > result.length()){
            list.clear();
            //result = str.substring(low,i);
            list.add(str.substring(low,i));
        }
        else if((i) - low == result.length()){
            list.add(str.substring(low,i));

        }

        low = Math.max(map.get(str.charAt(i)) +1,low);
    }
    map.put(str.charAt(i),i);
 }
 return list;

 }


    public static void main(String [] args) {
           LongestSubstring o = new LongestSubstring();
            System.out.println(o.LongestContinousSubstring2("This_is_a_video_conference_room"));
    }
}

