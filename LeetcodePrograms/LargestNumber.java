package LeetcodePrograms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rkhurana on 2/16/19.
 */
public class LargestNumber {
    public static int largestNumber(int nums) {
        if ( nums == 0)
            return 50;
        String num = "5"+nums;
        // Convert int array to String array, so we can sort later on
        String[] s_num = new String[num.length()];
        for (int i = 0; i < num.length(); i++)
            s_num[i] = String.valueOf(num.charAt(i));

        Arrays.sort(s_num, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2=o2+o1;
                return s2.compareTo(s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : s_num)
            sb.append(s);
        int result = Integer.parseInt(sb.toString());
        return  result;
    }

    public static void main(String[]args){
        int []num  = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(670));
    }
}
