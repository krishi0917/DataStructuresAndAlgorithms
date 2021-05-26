package LeetcodePrograms;

// time complexity O(n) space O(1)
public class NextPermutation {
    // Q31 next permutation ... #GoodQuestion
    // [6，3，4，9，8，7，1]
    //      i-1 i     k
    // (1) leftward find the first decreasing number @ index i - 1, (4)
    // (2) then nums[i:] must be rightward decreasing (9,8,7,1)
    // (3) leftward find the first number that is larger than i - 1, which is at k, (7)
    // (4) swap i - 1 with k, (6,3,7,9,8,4,1). we can see that nums[i:] will still be rightward decreasing (9,8,4,1)
    // (5) But we need them to be rightward increasing so that it's the smallest after swapping, so we reversed nums[i:],
    // which get the result (6,3,7,1,4,8,9)
    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int i = nums.length - 1;
        for (; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) { // find first number which is smaller than it's after number
                break;
            }
        }
        if (i != 0) {
            swap(nums, i - 1); // if the number exist,which means that the nums  not like{5,4,3,2,1}
            // After the above swap, array will be 6,3,7,9,8,4,1    // 7 and 4 are swapped
        }

        // now on the below line we have 7 at the right position, so swap all the elements after that
        reverse(nums, i);
    }

    public static void swap(int[] a, int i) {
        for (int j = a.length - 1; j > i; j--) {
            if (a[j] > a[i]) {
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                break;
            }
        }
    }

    private static void reverse(int[] a, int i) {// reverse the number after the number we have found
        int first = i;
        int last = a.length - 1;
        while (first < last) {
            int t = a[first];
            a[first] = a[last];
            a[last] = t;
            first++;
            last--;
        }
    }

    public static void main(String []args){
        int []nums = {6,3,4,9,8,7,1};
        nextPermutation(nums);

    }
}
