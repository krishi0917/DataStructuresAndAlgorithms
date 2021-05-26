package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/13/19.
 */
public class LongestAbsoluteFIlePath {


//        public int lengthLongestPath(String input) {
//            String[] tokens = input.split("\n");
//            int result = 0;
//            int curLen = 0;
//            Stack<Integer> stack = new Stack<>();
//
//            for (String s : tokens) {
//                int level = countLevel(s);
//
//                // if current directory/file depth is lower that the top directory/file on the stack, pop from stack
//                while (stack.size() > level) {
//                    curLen -= stack.pop();
//                }
//
//                // +1 here because a "/" needs to be counted following each diretory
//                int len = s.replaceAll("\t", "").length() + 1;
//                curLen += len;
//
//                // if s contains ".", we have found a file!
//                if (s.contains(".")) {
//                    result = curLen - 1 > result ? curLen - 1 : result;
//                }
//                stack.add(len);
//            }
//            return result;
//        }
//    private int countLevel(String s) {
//    String cur = s.replaceAll("\t", "");
//    return s.length() - cur.length();
//}


    public int lengthLongestPath(String input) {
        int maxLen = 0 ,count =0 , level =1 ;
        boolean isFile = false;
        HashMap<Integer , Integer > levelLength  = new HashMap<>();

        int i =0 ;
        while (i < input.length()){
            while(input.charAt(i) == '\t'){
                ++level;
                ++i;
            }
            while( i < input.length() && input.charAt(i) != '\n'){

                if(input.charAt(i)== '.')
                    isFile = true;
                ++count;
                ++i;
            }
            if(isFile) {
                levelLength.put(level , levelLength.getOrDefault(level - 1, 0) + count);
                maxLen = Math.max(maxLen, levelLength.get(level ));
            }
            else
                levelLength.put(level , levelLength.getOrDefault(level - 1, 0 ) + count + 1);
            count = 0 ;
            level = 1;
            isFile = false;
            ++i;
        }
        return  maxLen;

    }

    public static void main(String []args){
        LongestAbsoluteFIlePath s = new LongestAbsoluteFIlePath();
        String s1 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        String s3 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String s4 = "a.txt";
        String s5 = "dir\n\t        file.txt\n\tfile2.txt";

        String s6 = "file name with  space.txt";
        System.out.println(s.lengthLongestPath(s5));
    }
}

