package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */
public class eBayQuestion2 {

   // int output [] = {1 , 5 , 15 , 34 };

    public static int[] addElements(int [] input){
        int i = 0 ;
        int outputIndex = 0 ;
        int output[] = new int[input.length];
        int numberOfElementstoAdd=0;
         while( i < input.length) {
            int sum =0;
             numberOfElementstoAdd++;

            for(int k = 0 ; k < numberOfElementstoAdd ; k++){
                sum = sum + input[i];
                i++;
            }
            output[outputIndex] = sum;
            outputIndex++;
        }
        return output;
    }

    public static void main(String []args){
        int input []= {2,3,4 };
        int out[] = addElements(input);
        for(int i = 0 ; i < out.length ; i++){
            System.out.println(out[i]);
        }
    }
}
