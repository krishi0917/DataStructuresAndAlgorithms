package LeetcodePrograms.src;

public class KthSmallestProductOfTwoSortedArrays {
//    https://www.youtube.com/watch?v=bZXt-mDqIZ0&t=999s&ab_channel=Pepcoding
        public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
            long lb = (long) -1e10, rb = (long) 1e10;

            while(lb <= rb){
                long dotProduct = (lb + rb) / 2;
                if(countNoOfElements(nums1,nums2,dotProduct) < k)
                    lb = dotProduct + 1;
                else
                    rb = dotProduct - 1;
            }
            return rb;
        }

        private long countNoOfElements(int[] nums1, int[] nums2, long dotProduct){
            long count = 0;
            for(int ele : nums1){
                if(ele >= 0){
                    int li = 0, ri = nums2.length;
                    while(li < ri){
                        int mid = (li + ri) / 2;
                        if((long) ele * nums2[mid] < dotProduct)
                            li = mid + 1;
                        else
                            ri = mid;
                    }

                    count += li;
                }else{
                    int li = 0, ri = nums2.length;
                    while(li < ri){
                        int mid = (li + ri) / 2;
                        if((long) ele * nums2[mid] >= dotProduct)
                            li = mid + 1;
                        else ri = mid ;
                    }

                    count += nums2.length - li;
                }
            }

            return count;
        }
    public static void main(String []args){
        KthSmallestProductOfTwoSortedArrays kthSmallestProductOfTwoSortedArrays = new KthSmallestProductOfTwoSortedArrays();
        int []nums1 = {2,5};
        int []nums2 = {3,4};
        int k = 2;
        System.out.println(kthSmallestProductOfTwoSortedArrays.kthSmallestProduct(nums1,nums2,k));
    }
}
