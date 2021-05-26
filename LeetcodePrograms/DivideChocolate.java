package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int K) {
        K=K+1; // Include yourself.
        int lo = getMin(sweetness);
        int hi = getSum(sweetness);
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2; // +1 is basically to target the high if difference is 1
            if (split(sweetness, mid) < K) {
                hi = mid - 1;
            } else {
                lo = mid; // we have already reached the target, we might have to have the right amount
            }
        }
        return lo;
    }

    private int split(int[] arr, int minSweetness) {
        int peopleCount = 0;
        int sweetness = 0;
        for (int val : arr) {
            sweetness += val;
            if (sweetness >= minSweetness) {
                peopleCount++;
                sweetness = 0;
            }
        }
        return peopleCount;
    }

    private int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int val : arr) {
            min = Math.min(min, val);
        }
        return min;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }
    public static void main(String []args){
        int []sweetness = {1,2,3,4,5,6,7,8,9};
        int k = 5;
        DivideChocolate d = new DivideChocolate();
        System.out.println(d.maximizeSweetness2(sweetness,k));
    }

    public int maximizeSweetness2(int[] sweetness, int K) {
        int low = getMin(sweetness);
        int high = getSum(sweetness);
        while(low < high) {
            int mid = low + (high - low + 1) / 2;
            int slice = 0;
            int sum = 0;
            for(int s : sweetness) {
                sum += s;
                if(sum >= mid) {
                    sum = 0;
                    slice++;
                    if(slice > K)
                        break;
                }
            }
            if(slice > K) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
