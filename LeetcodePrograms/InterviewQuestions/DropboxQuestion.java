package LeetcodePrograms.InterviewQuestions;

import java.util.*;


/**
 * @author Rishi Khurana
 */
public class DropboxQuestion {
    public static String  textEditor(String[][] input) {

        String []inputStr = new String[input.length];

        for(int i = 0 ; i < input.length ; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(input[i][0]).append("_").append(input[i][1]).append("_");
            if(input[i][1].equals("APPEND")){
                sb.append(input[i][2]);
            }else if (input[i][1].equals("SELECT")){
                sb.append(input[i][2]).append("_").append(input[i][3]);
            }
            inputStr[i] = (sb.toString());
        }

        Arrays.sort(inputStr);

        Deque<String> queue = new LinkedList<>();
        for(int i = 0 ; i < inputStr.length ;i++ ){
            if(inputStr[i].contains("REDO")){
                if(queue.peekLast().contains("UNDO")){
                    queue.pollLast();
                }

            }else{
                queue.add(inputStr[i]);
            }
        }


        Deque<String> deque2 = new LinkedList<>();

        while(!queue.isEmpty()){
            String temp = queue.pop();
            //System.out.println("comingg " + te)

            if(temp.contains("UNDO")) {
                if(!deque2.isEmpty()){
                    deque2.pollLast();
                }
            }else{
                deque2.add(temp);
            }

        }

        StringBuilder result = new StringBuilder();
        int start=0 , end =0;
        while(!deque2.isEmpty()) {
            String s = deque2.pop();
            String[] arr = s.split("_");
            if (arr[1].equals("APPEND")) {
                if (start != 0 && end != 0) {
                    result.replace(start, end + 1, arr[2]);
                    start = 0;
                    end = 0;
                } else
                    result.append(arr[2]);
            } else if (arr[1].equals("BACKSPACE")) {

                if (result.length() != 0) {
                    if (start != 0 && end != 0) {
                        result.replace(start, end, "");
                        start = 0;
                        end = 0;
                    } else
                        result.deleteCharAt(result.length() - 1);
                }
            } else if (arr[1].equals("SELECT")) {
                start = Integer.valueOf(arr[2]);
                end = Integer.valueOf(arr[3]);
                if (end >= result.length()) {
                    end = result.length() - 1;
                }
            } else if (arr[1].equals("BOLD")) {

                result.insert(start, "*");
                if (end >= result.length()) {
                    end = result.length() - 1;
                }
                result.insert(end + 1, "*");
                start = 0;
                end = 0;
            }
        }
        return result.toString();

    }

    public static void main(String []args){
        String input[][]= {
                { "0","APPEND","hey"},
                {"1","APPEND","there"},
                {"5","BACKSPACE"},
                {"2","UNDO"},
                {"3","UNDO"},
                {"4","REDO"}
        };
        System.out.println(textEditor(input));
    }


}
