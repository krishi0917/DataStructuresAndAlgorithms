package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
// #Salesforce  #OnsiteInterview
// 1296 Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into
// sets of k consecutive numbers
//        Return True if its possible otherwise return False.
//        Example 1:
//
//        Input: nums = [1,2,3,3,4,4,5,6], k = 4
//        Output: true
//        Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

public class DivideArrayInSetsOfKConsecutiveNumbers {

    // firstIndex = element fetched from the arraylist .. elements can be deleted
    // secondIndex = location in which element is to be added in the templist

    public static boolean findPossibleDivide(List<Integer> nums  , int k){
        Map<Integer, Integer> map = new TreeMap<>(); // could be hashmap
        if(nums.size()%k != 0)
            return false;

        int firstIndex = 0;
        for(int i = 0 ; i < nums.size(); i++ )
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
        while(0 != nums.size()){
            int secondIndex = 0 ;
            List<Integer> tempList = new ArrayList<>();
            int prevElement =-1;
            while(secondIndex < k ){
                int element = nums.get(firstIndex);
                if((secondIndex == 0)  ){
                    tempList.add(element);
                    map.put(element , map.get(element) - 1 );
                    if(map.get(element)== 0 )
                        map.remove(element);
                    nums.remove(0);
                    secondIndex++;
                    prevElement = element;

                }else if ((element- prevElement) == 1) {
                    tempList.add(nums.get(firstIndex));
                    map.put(element , map.get(element) - 1 );
                    if(map.get(element)== 0 )
                        map.remove(element);
                    nums.remove(firstIndex);
                    secondIndex++;
                    prevElement = element;
                }

                else if ((element - prevElement) == 0 ){
                    firstIndex++;
                    continue;
                }
                else
                    return false;
            }
            secondIndex=0;
            firstIndex=0;
        }
        return true;
    }
    public static void main(String []args){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(6);

        int k=4;
        System.out.println(findPossibleDivide(list , k ));
    }
}
