package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 3/27/19.
 */
class FindMedianviaBinarySearch {
    ArrayList<Integer> nums;

    public FindMedianviaBinarySearch() {
        nums = new ArrayList<>();
    }

    public int findInsertIndex(int target) {
        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums.get(mid) >= target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }


    public void addNum(int num) {
        if (nums.size() == 0) {
            nums.add(num);
        } else {
            int index = findInsertIndex(num);
            if (index == nums.size()) {
                nums.add(num);
            } else {
                nums.add(index, num);
            }
        }
    }

    public double getMedian() {
        int mid = (nums.size() - 1) / 2;
        return nums.size() % 2 == 0 ? (nums.get(mid) + nums.get(mid + 1)) / 2.0 : nums.get(mid);
    }


    public static void main(String[] args) {
        FindMedianviaBinarySearch findMedian = new FindMedianviaBinarySearch();
        findMedian.addNum(2);
        findMedian.addNum(3);
        findMedian.addNum(1);
        findMedian.addNum(5);
        findMedian.addNum(6);
        findMedian.addNum(10);
        findMedian.addNum(9);
        System.out.println(findMedian.getMedian());
    }
}
