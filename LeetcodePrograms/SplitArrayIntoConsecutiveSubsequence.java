package LeetcodePrograms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * @author Rishi Khurana
 * 659. Split Array into Consecutive Subsequences
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more
 * subsequences such that each subsequence consists of consecutive integers and has length at least 3
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 */

public class SplitArrayIntoConsecutiveSubsequence {
//    https://www.youtube.com/watch?v=uJ8BAQ8lASE
// this method is better...  explanation in the abvoe link
// Solution
    // an incoming number will first try to see if it can be inserted into the existing the subsequence
    // before it tries to create one
    //hypothetical map will keep track of the element that can be added hypothetically in the existing map
    public boolean isPossible(int[] nums) {
        Map<Integer,Integer> existingSequences = new HashMap<>(); // hypothetical map
        Map<Integer,Integer> createNewSequence = new HashMap<>(); //  frequency map

        for(int i : nums){
            createNewSequence.put(i, createNewSequence.getOrDefault(i,0) +1);
        }
        for(int i : nums){
            if(createNewSequence.get(i) == 0) continue; // this takes care of all the elements who are already
            // decremented and reduced to zero in the last iteration.

            if(existingSequences.getOrDefault(i,0) > 0){ // checks if the hypothetical map has this element.
                // if it does, it automatically removes the element from hypothetical map, assuming that element is
                // inserted to the existing map and a new space is been created in hypothetical map.
                existingSequences.put(i, existingSequences.get(i)-1);
                existingSequences.put(i+1, existingSequences.getOrDefault(i+1, 0)+1);
                createNewSequence.put(i, createNewSequence.get(i)-1);
            }

            //creating a new map and checking if the next 3 values already exist in the frequency map..
            // if it does , it decrements those values and the 4th value is been added in the hypothetical map.
            else if(createNewSequence.getOrDefault(i, 0) > 0 && createNewSequence.getOrDefault(i+1, 0) > 0
                    && createNewSequence.getOrDefault(i+2, 0) > 0){
                createNewSequence.put(i, createNewSequence.get(i)-1);
                createNewSequence.put(i+1, createNewSequence.get(i+1)-1);
                createNewSequence.put(i+2, createNewSequence.get(i+2)-1);
                existingSequences.put(i+3, existingSequences.getOrDefault(i+3,0)+1);
            }
            else{
                return false;
            }
        }
        return true;
    }




     class Interval {
        int start;
        int end;
        int length;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            length = end - start + 1;
        }
    }

        public boolean isPossible2(int[] nums) {
            PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>(){
                @Override
                public int compare(Interval a, Interval b) {
                    if (a.end == b.end) {
                        return Integer.compare(a.length, b.length);
                    }
                    return Integer.compare(a.end, b.end);
                }
            });

            for (int num : nums) {
                while (!pq.isEmpty() && pq.peek().end + 1 < num) {
                    if (pq.poll().length < 3)
                        return false;
                }
                if (pq.isEmpty() || pq.peek().end == num) { //first condition meaning the iteration just begun..
                    // second condition is when a new interval is to be created because the incoming element is same
                    // as that of the last element
                    pq.add(new Interval(num, num));
                } else { // pq.peek().end + 1 == num
                    pq.add(new Interval(pq.poll().start, num));
                }
            }

            while (!pq.isEmpty()) {
                if (pq.poll().length < 3)
                    return false;
            }

            return true;
        }
    public static void main(String []args){
         int []nums = {1, 2, 3, 3, 4, 5, 7};
         SplitArrayIntoConsecutiveSubsequence s = new SplitArrayIntoConsecutiveSubsequence();
         System.out.println(s.isPossible2(nums));
     }
}
