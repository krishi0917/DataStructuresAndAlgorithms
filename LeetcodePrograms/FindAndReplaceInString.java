package LeetcodePrograms;
import java.util.*;
/**
 * have to think a little more ..after office work
 *
 * @author Rishi Khurana
 * 833. Find And Replace in String

 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not
 * necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is
 * that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If
 * not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then
 * because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well
 * as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the
 * original string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for
 * example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 * Example 1:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 */
public class FindAndReplaceInString {
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i=0; i<indexes.length; i++) {
            // if a match is found in the original string, record it
            if (S.startsWith(sources[i], indexes[i])) {
                table.put(indexes[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<S.length(); ) {
            if (table.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[table.get(i)]);
                i+=sources[table.get(i)].length();
            } else {
                // if no replacement happened at this index
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static String findReplaceStringMySol(String S, int[] indexes, String[] sources, String[] targets) {
        int strIndex = 0;
        int i=0;
        StringBuilder sb = new StringBuilder();
        Arrays.sort(indexes);
        HashMap<Integer,String> map = new HashMap<>();
        int counter = 0;

        // creating a hashmap and putting all those values that needs to be put
        for(int j : indexes){
            if(sources[counter].equals(S.substring( j+sources[i].length()))){
                map.put(j,targets[counter]);
            }
            counter++;
        }

        while(strIndex < S.length()){

            if( i < sources.length && strIndex == indexes[i] ){ // index is matched now check if the string matches.
                // if it does append it to stringbuilder
                // if there is a match do the below thing
                if(sources[i].equals(S.substring(strIndex,strIndex+sources[i].length()))) {
                    sb.append(targets[i]);
                    strIndex +=sources[i].length();
                    i++;

                    //Number of indices to be increased

                }else{
                    sb.append(S.charAt(strIndex));
                    strIndex++;
                    i++;
                }
            }
            else{
                sb.append(S.charAt(strIndex));
                strIndex++;
            }
        }
        return sb.toString();
    }

        public static void main(String []args){
        String S = "vmokgggqzp";
        int []indexes = {3,5,1};

        String []sources = {"kg","ggq","mo"};
        String []targets = {"s","so","bfr"};

//            Input:
//            ""
//                    [3,5,1]
//["kg","ggq","mo"]
//["s","so","bfr"]



//            Output:
//            "vmossozp"
//            Expected:
//            "vbfrssozp"


//            Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
        System.out.println(findReplaceString3(S,indexes,sources,targets));
    }

    public static String findReplaceString3(String S, int[] indexes, String[] sources, String[] targets) {
        List<int[]> sorted = new ArrayList<>();
        for (int i = 0 ; i < indexes.length; i++)
            sorted.add(new int[]{indexes[i], i});
        Collections.sort(sorted, Comparator.comparing(i -> -i[0]));
        for (int[] ind: sorted) {
            int i = ind[0], j = ind[1];
            String s = sources[j], t = targets[j];
            if (S.substring(i, i + s.length()).equals(s)) S = S.substring(0, i) + t + S.substring(i + s.length());
        }
        return S;
    }
}
