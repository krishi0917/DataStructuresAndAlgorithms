package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/18/19.
 */
// 969 Pancake sorting
public class FlipPancake {
    /*
3 2 4 1
^ ^
1.find the largest
2.flip to first and flip again to the last
3.until the first one
*/
// good youtube video
// Outer while loop takes n iterations, within it, there is O(n) time complexity (find max + flip). So overall O(n^2).



    public List<Integer> pancakeSort(int[] A) {
            List<Integer> list = new ArrayList<>();
            for(int end = A.length - 1; end >= 0; end--){
                int largest = findLargest(A, end);
                if(largest == end) continue;
                swap(A, 0, largest);
                swap(A, 0, end);
                list.add(largest + 1);
                list.add(end + 1);
            }
            return list;
        }
        private int findLargest(int[]A, int lastIdx){
            int max = Integer.MIN_VALUE, index = 0;
            for(int i = 0; i <= lastIdx; i++){
                if(A[i] > max){
                    max = A[i];
                    index = i;
                }
            }
            return index;
        }
        private void swap(int[]A, int start, int end){
            while(start < end){
                int tmp = A[start];
                A[start++] = A[end];
                A[end--] = tmp;
            }
        }



    public static void main(String []args){
        int A[] ={3,2,4,1};
        FlipPancake flipPancake = new FlipPancake();
        System.out.print(flipPancake.pancakeSort(A));
    }
}
