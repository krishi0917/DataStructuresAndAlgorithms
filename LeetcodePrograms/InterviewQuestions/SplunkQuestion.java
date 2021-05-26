package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */
public class SplunkQuestion {
    public static String solution(String S) {
        // input 0 - 22 1985--324
        // output 022-198-53-24
        int length =S.length();
        int i = 0 ;
        StringBuilder sb = new StringBuilder();
        while(i < S.length()){
            if(S.charAt(i)== ' ' || S.charAt(i) == '-'){
                i++;
                continue;
            }
            sb.append(S.charAt(i));
            i++;
        }

        StringBuilder output = new StringBuilder();
        if(sb.length() % 3 == 0 || sb.length() %3 == 2){
            int index = 0;
            int innerIndex = 0;
            while(index < sb.length()){

                output.append(sb.charAt(index));
                index++;
                innerIndex++;
                if(innerIndex == 3 ){
                    if(index != sb.length()){
                        output.append("-");
                    }
                    innerIndex=0;
                }
            }
        }else{
            int index = 0;
            int innerIndex = 0;
            while(index < sb.length()-2){

                output.append(sb.charAt(index));
                index++;
                innerIndex++;
                if(innerIndex == 3 ){
                    if(index != sb.length()){
                        output.append("-");
                    }
                    innerIndex=0;
                }
            }
            output.append("-").append(sb.charAt(index)).append(sb.charAt(index+1));
        }
        return output.toString();
    }
    public static void main(String[]args){
        System.out.println(solution("11111 2     32323  -     2     3   -----   4"));
    }
}
