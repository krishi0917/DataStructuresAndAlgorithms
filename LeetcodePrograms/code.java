package LeetcodePrograms;

import java.util.Random;


/**
 * @author Rishi Khurana
 */
public class code {
    public static void findRandomWeghts(int []arr,double []weights){
        if(null == arr || arr.length == 0 || arr.length !=weights.length ){
            return;
        }
        double []tempArr = new double[arr.length];
        double prev = 0.0;
        for(int i = 0 ; i < weights.length ; i++  ){
            tempArr[i] = tempArr[i] + prev;
            prev = tempArr[i];
        }

        for(int i = 0 ; i < tempArr.length ; i++){
            System.out.print("temp" + tempArr[i] + " " );
        }

        double min = 0.0;
        double max = tempArr[tempArr.length-1];
        System.out.println("max" + max);
        Random r = new Random();
        double randomNum =  min + (max - min) * r.nextDouble();
        System.out.println(randomNum);
        System.out.println(randomNum);
        System.out.println(randomNum);
        System.out.println(randomNum);
        System.out.println(randomNum);

        int index = findIndex(randomNum, tempArr);

        //System.out.println("coming here " + arr[index]);



    }


    public static int findIndex(double randomNum , double[]tempArr){
        int left = 0;
        int right = tempArr.length-1;
        while(left<=right){
            int mid = (left + right)/2;
            if(randomNum == tempArr[mid] )
                return mid;
            else if(randomNum < tempArr[mid]){
                right = mid - 1;
            }else{
                left = mid +1;
            }
        }

        return left;

    }
    public static void main(String[] args) {
        int[] arr = { 1, 4, 2 };
        double[] weights = new double[] { 0.3, 0.1, 0.8 };
        findRandomWeghts(arr, weights);
    }
}
