package org.example;

import java.util.ArrayList;
import java.util.List;

public class StripeApp {

    // compute_penalty("Y Y N Y", 0) should return 3

    public static int compute_penalty(String log, int closing_time){
        int penalty = 0 ;
        String logArr[] = log.split(" ");
        for(int i = 0 ; i < closing_time; i++){
            if(logArr[i].equals("N"))
                penalty+=1;
        }
        for(int i = closing_time ; i < logArr.length; i++){
            if(logArr[i].equals("Y"))
                penalty+=1;
        }
        return penalty;
    }

    // find_best_closing_time("Y Y N N") should return 2
    public static int find_best_closing_time(List<String> log){
       // String logArr[] = log.split(" ");

        int maxScore = 0 , score = 0 , rightTime = -1;
        for(int i = 0 ; i < log.size(); i++) {
            // int penalty = compute_penalty(log.substring(0,i) , logArr.length);
            score += log.get(i).equals("Y") ? 1 : -1 ;
            if(score > maxScore){
                maxScore = score;
                rightTime = i;
            }
        }
        return rightTime + 1;
    }
    public static List<Integer> get_best_closing_times(String log){
        String logArr[] = log.split(" ");
        boolean isBeginTrue = false;
        List<String> input= new ArrayList<>();
        List<Integer> resultList= new ArrayList<>();
        for(int i = 0 ; i < logArr.length; i++){
            if(logArr[i].equals("BEGIN") && !isBeginTrue){
                isBeginTrue = true;
            }
            // BEGIN Y Y N BEGIN
            else if(logArr[i].equals("BEGIN") && isBeginTrue){
                input.clear();
            }else if(logArr[i].equals("END") && isBeginTrue){

                int result = find_best_closing_time(input);
                resultList.add(result);
                isBeginTrue = false;
                input.clear();
            } // Y Y Y END  && isBeginTrue = false
            else if(logArr[i].equals("END")){
                input.clear();
            }
            else{
                input.add(logArr[i]);
            }
        }
        return resultList;
    }

    public static void main(String []args){
        //System.out.println(compute_penalty("Y Y N Y", 0));//  should return 3
        // System.out.println(compute_penalty("N Y N Y", 2));// should return 2
        // System.out.println(compute_penalty("Y Y N Y", 4));// should return 1
        // log = null;
        // log=  YYYYYY
        // log= NNNNN
        // log = "";
        // log = "ANYNN" // garbage data

        /*System.out.println(find_best_closing_time("Y Y N N"));
        System.out.println(find_best_closing_time("N N N N"));
        System.out.println(find_best_closing_time("Y Y Y Y"));
        System.out.println(find_best_closing_time("N N Y Y Y Y "));
*/
        System.out.println(get_best_closing_times("BEGIN Y Y END \n BEGIN N N END"));
        System.out.println(get_best_closing_times("BEGIN BEGIN \n BEGIN N N BEGIN Y Y\n END N N END"));
    }
}