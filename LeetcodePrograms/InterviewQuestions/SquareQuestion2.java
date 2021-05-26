package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */
public class SquareQuestion2 {
    public static String[][] matrxQuestion(String[][]input , String [][]matrix){
        int iIndex = 0 ;
        int jIndex = 0 ;
        int inputIndex=0;
        int inputIndexLength = input.length;
        while(iIndex < 8){
            while (jIndex < 5){
                if(inputIndex >= inputIndexLength){
                    matrix[iIndex][jIndex] =".";
                }else {
                    String[] inputRow = input[inputIndex];
                    if (inputRow[1].equals("small")) {
                        matrix[iIndex][jIndex] = inputRow[0];

                    } else {
                        if (jIndex <= 3) {
                            matrix[iIndex][jIndex] = inputRow[0];
                            matrix[iIndex][++jIndex] = inputRow[0];
                        } else {
                            matrix[iIndex][jIndex] = ".";
                            if (iIndex <= 6) {
                                iIndex = iIndex + 1;
                                jIndex = 0;
                                matrix[iIndex][jIndex] = inputRow[0];
                                matrix[iIndex][++jIndex] = inputRow[0];
                            }
                        }
                    }
                }
                jIndex++;
                inputIndex++;
                if(jIndex == 5) {
                    jIndex = 0;
                    break;
                }


            }
            iIndex++;
        }

        return matrix;

    }
    public static void main(String []args){
        String[][] s = {
                {"Q","small"},
                {"L","large"},
                {"A","small"},
                {"M" , "large"},
                {"X" , "large"},
                {"B","small"},
                {"C" , "large"},
                {"D" , "large"},
                {"M" , "large"},
                {"X" , "large"},
                {"B","small"},
                {"C" , "large"},
                {"D" , "large"},
                {"M" , "large"},
                {"X" , "large"},
                {"B","small"},
                {"C" , "large"},
                {"D" , "large"},
                {"M" , "large"},
                {"X" , "large"},
                {"N","large"},
                {"O" , "large"},
                {"P" , "large"},
                {"Q","small"},
                {"R" , "large"},
                {"S" , "large"},
                {"T","small"},
                {"U" , "large"},
                {"V" , "large"}
        };
        String [][]matrix = matrxQuestion(s , new String[8][5] );
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[0].length ; j++){
                System.out.print(matrix[i][j] +  "   ");
            }
            System.out.println();
        }
    }
}
