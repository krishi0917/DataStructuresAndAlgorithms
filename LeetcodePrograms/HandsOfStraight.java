package LeetcodePrograms;

import java.util.*;

/**
 * @author Rishi Khurana
 * 846. Hand of Straights
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * Return true if and only if she can.
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 */
public class HandsOfStraight {
    // firstIndex = element fetched from the arraylist .. elements can be deleted
    // secondIndex = location in which element is to be added in the templist

    public static boolean isNStraightHand(int[] hand, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < hand.length; i++)
            nums.add(hand[i]);
        Collections.sort(nums);
        Map<Integer, Integer> map = new TreeMap<>();
        if (nums.size() % k != 0)
            return false;

        int firstIndex = 0;
        for (int i = 0; i < nums.size(); i++)
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
        while (0 != nums.size()) {
            int secondIndex =0;
            List<Integer> tempList = new ArrayList<>();
            int prevElement =-1;
            while(secondIndex < k){
                if(nums.size()== firstIndex) // to avoid the null pointer exception we get when list is 1,1,2,2,3,3
                    return false;
                int element = nums.get(firstIndex);
                if(secondIndex == 0){
                    //first element in this list
                    tempList.add(element);
                    map.put(element , map.get(element) - 1 );
                    if(map.get(element)== 0 )
                        map.remove(element);
                    nums.remove(0);
                    secondIndex++;
                    prevElement = element;
                }else if(element - prevElement == 1){
                    tempList.add(nums.get(firstIndex));
                    map.put(element , map.get(element) - 1 );
                    if(map.get(element)== 0 )
                        map.remove(element);
                    nums.remove(firstIndex);
                    secondIndex++;
                    prevElement = element;
                }else if ((element - prevElement) == 0 ){
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
        int hand[] = {1,1,2,2,3,3};
        int k =2;
        System.out.println(isNStraightHand(hand,k));

    }
}
