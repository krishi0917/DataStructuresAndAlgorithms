package LeetcodePrograms;

/**
 * Created by rkhurana on 7/10/18.
 */
public class IncreasingTripletSubsequence {
    public static boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE,
                big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both ..// c1 is min seen so far (it's a candidate for 1st element)
            else if (n <= big) {  // here when x > c1, i.e. x might be either c2 or c3
                big = n;
            } // update big only if greater than small but smaller than big ..// x is better than the current c2, store it
            else return true; // return if you find a number bigger than both   // the increasing subsequence of 3 elements exists
        }
        return false;
    }
    public static void main(String [] args){
        int num[] = {3,2,3,1,4,0};
        System.out.println(increasingTriplet(num));
    }
}
