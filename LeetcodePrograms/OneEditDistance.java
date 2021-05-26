package LeetcodePrograms;

// 161. One Edit Distance  #FacebookQuestion #UberQuestion
// Given two strings s and t, determine if they are both one edit distance apart.
// Note: There are 3 possiblities to satisify one edit distance apart:
//       Insert a character into s to get t
//       Delete a character from s to get t
//       Replace a character of s to get t
public class OneEditDistance {
    /*
 * There're 3 possibilities to satisfy one edit distance apart:
 *
 * 1) Replace 1 char:
 	  s: a B c
 	  t: a D c
 * 2) Delete 1 char from s:
	  s: a D  b c
	  t: a    b c
 * 3) Delete 1 char from t
	  s: a   b c
	  t: a D b c
 */
    // time complexity of s.substring is O(n) after java 7 before it was O(1)
    // this below method looks like O(n square complexity as substring as O(n) complexity)
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        return Math.abs(s.length() - t.length()) == 1;
    }

    public boolean isOneEditDistance2(String s, String t) {
        if(Math.abs(s.length() - t.length()) > 1)
            return false;
        int i = 0, j = 0,err = 0;
        while(i<s.length() && j<t.length())
        {
            if(s.charAt(i) != t.charAt(j))
            {
                err++;
                if(err > 1)
                    return false;
                if(s.length() > t.length())
                    j--;
                else if(s.length() < t.length())
                    i--;
            }
            i++;
            j++;
        }
        return err == 1 || (err == 0 && t.length() != s.length());
        //return (err == 1 || (err == 0 && t.length() != s.length()))? true: false;
    }

    boolean oneEditAway(String first, String second){
        if(first.length() == second.length()){
            return oneEditReplace(first,second);
        }else if(first.length() + 1 == second.length()){
            return oneEditInsert(first,second);
        }else if(first.length() - 1 == second.length()){
            return oneEditInsert(second,first);
        }
        return false;
    }
    boolean oneEditReplace(String s1 , String s2){
        boolean foundDifference = false;
        for(int i = 0 ; i < s1.length() ; i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                if(foundDifference)
                    return false;
                foundDifference = true;
            }
        }
        return true;
    }
    boolean oneEditInsert(String s1 , String s2){
        int index1 = 0;
        int index2 = 0;
        while(index2 < s2.length() && index1 < s1.length()){
            if(s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2)
                    return false;
                index2++;
            }else{
                index1++;
                index2++;
            }
        }
        return true;
    }

    public static void main(String []args){
        OneEditDistance distance = new OneEditDistance();
        String a ="ab" , b = "acb";
        System.out.println(distance.isOneEditDistance(a,b));
    }

}
