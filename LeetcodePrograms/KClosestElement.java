package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/20/19.
 */
public class KClosestElement {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //-------- Main idea behind the binary search algorithm ----------//
        // 1) res will be a consecutive subarray of k size
        // 2) say if we need to pick 4 elems, now we r looking at 5 elem n1, n2, n3, n4, n5
        //    we need to compare two ends: diff(x, n1) and diff(x, n5), the number with bigger diff on the end will be eleminated
        //----------------------------------------------------------------//
        // lo and hi: range of all possible start of subarray with size k + 1, so we could compare both ends
        int lo = 0, hi = arr.length - k - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
            if (Math.abs(x - arr[mid]) > Math.abs(x - arr[mid+k])) {
                lo = mid + 1; // arr[mid] is the one further away from x, eliminate range[lo, mid]
            } else {
                hi = mid - 1; // arr[mid+k] is the one further away, all [mid, hi] will have similar situation, eliminate them
            }
        }
        // return subarray
        List<Integer> res = new ArrayList(k);
        for (int i = 0; i < k; i++) {
            res.add(arr[lo + i]);
        }
        return res;
    }

    public List<Integer> findClosestElements2(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++)
            res.add(A[left + i]);
        return res;
    }

    public static void main(String []args){
        KClosestElement kClosestElement = new KClosestElement();
        int []arr = {1,2,3,4,5,6,7,8,9,10};
        int k=2 , x=3;
        kClosestElement.findClosestElements(arr,k,x);
    }

    //easier solution but O(n) time complexity
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = lo; i <= hi; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
