package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubsetString {
    // Q78 Subsets #TopInterviewQuestion
    // Given a set of distinct integers, nums, return all possible subsets (the power set).
    // Input: nums = [1,2,3]
    // Output:
    // [
    // [3],
    // [1],
    // [2],
    // [1,2,3],
    // [1,3],
    // [2,3],
    // [1,2],
    // []
    // ]

    public static List<List<String>> subsets(String[] nums) {
        List<List<String>> list = new ArrayList<>();
//        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private  static void backtrack(List<List<String>> list, List<String> tempList, String[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1])
                continue; // for skipping duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }



    public static void main(String []args){
        String []num1 = {"cod" , "il" , "ity"};
        String []num2 = {"cod" , "ll" , "ity"};
        List<List<String>> list  = subsets(num1);
        for(int i = 0 ; i < list.size() ; i++){
            List<String> innerList = list.get(i);
            //for(int j =  ; j < innerList.)
        }
    }

    // Combination of a String and subset is the same
    static void combine(String str) {
        int length = str.length();
        StringBuffer output = new StringBuffer(length);
        combination1(str, length, output, 0);
    }

    static void combination1(String str, int length, StringBuffer output, int allowedStart) {
        for (int curr = allowedStart; curr < length; curr++) {
            output.append(str.charAt(curr));
            if (curr + 1 != length)
                combination1(str, length, output, curr + 1);
            output.deleteCharAt(output.length() - 1);
        }
    }
}
