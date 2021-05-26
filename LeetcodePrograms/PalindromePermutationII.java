package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/18/19.
 */
//    267. Palindrome Permutation II   #UberQuestion
//    Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if(s==null || s.length()==0) return res;

        // record freqency of each character
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

// if a character with odd freqency exits, that character must be the center of palindrome
        int odd = 0;
        char oddvalue=' ';
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            char key = entry.getKey();
            int count = entry.getValue();
            if(count%2!=0) {
                oddvalue = key;
                odd++;
            }
//if odd values larger than 1, not valid, return empty list
            if(odd>1) return res;
        }
        //no odds, the string is initialized as empty
        if(odd==0)
            getPalindromes(new HashMap<Character,Integer>(map),"",s,res);
            // one odd, the initialized string is oddvalue
        else {
            map.put(oddvalue,map.get(oddvalue)-1);
            getPalindromes(new HashMap<Character,Integer>(map),""+oddvalue,s,res);
        }
        return res;
    }
    // expand the string from center to ends

    private void getPalindromes(HashMap<Character,Integer> map, String cur,String s,List<String> res){
        if(cur.length()==s.length()){
            res.add(cur);
            return;
        }


        for(char c : map.keySet()){
            StringBuilder sb = new StringBuilder(cur);
            if(map.get(c)!=0) {
                sb.insert(0,c);
                sb.append(c);
                map.put(c,map.get(c)-2);
                getPalindromes(map,sb.toString(),s,res);
                map.put(c,map.get(c)+2);

            }
        }
    }

    public static void main(String []args){
        PalindromePermutationII permutationII = new PalindromePermutationII();
        System.out.println(permutationII.generatePalindromes("aabb"));
    }

}