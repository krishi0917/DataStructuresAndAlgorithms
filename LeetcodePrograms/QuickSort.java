package LeetcodePrograms;

public class QuickSort {
    public static int QuickSortUtil(int A[], int start , int end ){
        int pivot = A[end];
        int pIndex = start;
        for (int i= start ; i < end ; i++){
            if(A[i] >= pivot){
                swap(A ,  pIndex , i);
                pIndex++;
            }
        }
        swap( A ,pIndex , end);
        return pIndex;
    }

    public static void QuickSortAlgo(int A[], int start , int end){
        if(start < end){
            int pIndex = QuickSortUtil(A,start,end);
            QuickSortAlgo(A,start , pIndex-1  );
            QuickSortAlgo(A,pIndex+1 , end );
        }
    }
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    public static void main(String []args){
        int A[] = {10, 80, 30, 90, 40, 50, 70};
        QuickSortAlgo(A,0,A.length-1);
        for(int i=0 ; i <A.length ; i++)
            System.out.print(A[i]  + "  ");
    }
}
