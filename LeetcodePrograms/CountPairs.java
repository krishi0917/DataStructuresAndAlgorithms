//package LeetcodePrograms;
//
//public class CountPairs {
////    Given two arrays(of different lengths),int lower,int upper, count how many pairs have the following property
////    lower <= a[i]*a[i] +b[j]*b[j] <= higher
//
//    public int findPairsOptimized(int a[],int b[],int lo,int hi) {
//
//
//        if (lo< || or hi <0 )
//        return 0; #sum of two squares can never be less than zero
//
//        if len(a) > len(b):
//        return self.findPairsBruteForce(b, a, lo, hi)
//
//        a.sort()
//        b.sort()
//
//        count = 0
//        for i in range(len(a)):
//        loprime = lo - a[i] * a[i] if lo > a[i] * a[i] else lo
//                hiprime = hi - a[i] * a[i]
//
//        if a[i] > math.sqrt(hi) + 1 or hiprime <0 :
//        break index1 =self.findLowerBound(b, math.sqrt(loprime))
//        if index1 == -1:
//        continue index2 =self.findUpperBound(b, int(math.sqrt(hiprime)))
//        if index2 == -1 or index2 <index1:
//        continue count +=index2 - index1 + 1
//        return count
//    }
//    /*
//    Basically we have to find first element in arr that is greater than or equal to num
//    """*/
//    public int findLowerBound(int arr[], int num) {// first index: sqrt(x) <= y
//        int left = 0;
//        int right = arr.length -1;
//
//        while (left <= right) {
//            int mid = right - (right - left) / 2;
//            if (arr[mid] >= num)
//                if (mid == 0 || mid - 1 >= 0 && arr[mid - 1] < num)
//                    return mid;
//                else
//                    right = mid - 1;
//            else
//                left = mid + 1;
//        }
//        return -1;
//    }
//
//    public int findUpperBound(int arr[], int num) {
//        int left = 0 ;
//        int right = arr.length - 1;
//
//        while (left <= right){
//            int mid = right - (right - left) / 2;
//            if (arr[mid] <= num)
//            if (mid == arr.length - 1 || mid +1 < arr.length && arr[ mid + 1] >num)
//            return mid;
//            else
//            left = mid + 1;
//            else
//            right = mid - 1;
//            return -1;
//        }
//
//    }
//}
