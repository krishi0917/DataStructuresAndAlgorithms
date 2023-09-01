package LeetcodePrograms.InterviewQuestions;

/*
given a 2d array of integers, write a boolean function to search for a value in the array. This array has the following properties.
1. Integers in each row are now sorted from left to right in ascending order
2. The first integer in each row is greater than the last integer of the previous row
3. Each row might be of different lengths, but has atleast 1 element.
4. Elements in the array are unique and dont repeat
 */
public class Navan_2dSortedArray {

    // first way
    // navigating every line and doing binary search in every line.
    public boolean checkElementPresentOrNotFirstWay(int[][]matrix, int element){
        boolean foundElement = false;
        for(int i = 0 ; i < matrix.length ; i++){
            foundElement = checkForBinarySearch(matrix[i], element);
            if(foundElement){
                break;
            }
        }
        return foundElement;
    }

    public boolean checkForBinarySearch(int[]matrix, int element){
        int start = 0;
        int end = matrix.length-1;
        while(start <= end){
            int mid = (start + end )/2;
            if(matrix[mid] == element ){
                return true;
            }else if(matrix[mid] > element){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return false;
    }

    // do the column wise binary search first and then once the first element of the row is found do the binary search in that row to find if the element exist in the row or not
    // second way;
    public boolean checkElementPresentOrNotSeconWay(int[][]matrix, int element) {
        int arr[] = new int[matrix.length];
        for(int i = 0 ; i < matrix.length ; i++){
            arr[i] = matrix[i][0];
        }

        // binary search
        int start = 0;
        int end = arr.length-1;
        int mid = -1;
        while(start <= end){
            mid = (start + end )/2;
            if(arr[mid] == element ){
                System.out.println("found element within binary search");
                return true;
            }else if(arr[mid] > element){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
       int tempArr[] = arr[mid] < element ? matrix[mid] : matrix[mid-1];
            int start1 = 0;
            int end1 = tempArr.length-1;
            int mid1 = -1;
            while(start1 <= end1){
                mid1 = (start1 + end1 )/2;
                if(tempArr[mid1] == element ){
                    System.out.println("found element within binary search in second turn");
                    return true;
                }else if(tempArr[mid1] > element){
                    end1 = mid1 - 1;
                }else{
                    start1 = mid1 + 1;
                }
            }
        return false;
    }

        public static void main(String []args){
        Navan_2dSortedArray sortedArray = new Navan_2dSortedArray();
        int [][]matrix = {{10,20,30,40}, {50,60},{70,80,90}};
        int element = 85;
        System.out.println(sortedArray.checkElementPresentOrNotSeconWay(matrix, element));
    }
}
