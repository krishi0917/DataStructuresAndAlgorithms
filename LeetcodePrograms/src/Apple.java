package LeetcodePrograms.src;

public class Apple {


        public String matrixCalculation(char [][]matrix){
            int i = 0 ;int j = 0;
            StringBuilder sb = new StringBuilder();
            int direction = 0;
            while( j < matrix[0].length){
                if(direction == 0){ //left +1 right +1
                    sb.append(matrix[i][j]);
                    i++;j++;
                }
                else if( direction  == 1){
                    sb.append(matrix[i][j]);
                    i-- ; j++;
                }
                if(i == matrix.length ){
                    direction = 1;
                    i=i-2;
                }else if (j==matrix[0].length){
                    direction = 0;
                    i++;j++;
                }
            }
            return sb.toString();
        }

        public static void main(String []args){

            Apple s = new Apple    ();
            char [][]matrix = {   {'a','b','c','d','e','p'},
                                  {'f','g','h','i','j','q'},
                                  {'k','l','m','n','o','r'}
            };
            System.out.println(s.matrixCalculation(matrix));

        }

}
