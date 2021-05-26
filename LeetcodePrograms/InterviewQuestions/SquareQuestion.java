package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */
/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * Spades, Diamonds, Hearts, Clubs
 */

class SquareQuestion {

    public boolean isFlush1(String []str , int wildCard){

        // if(null == str || str.length != 7 ){
        //   return false;
        // }
        HashMap<String,List<Integer>> map = new HashMap<>();
        int countWildCard=0;
        for(String s : str){
            String cardNumber= s.substring(1);
            String cardType = s.substring(0,1);
            if(Integer.parseInt(cardNumber) == wildCard){
                countWildCard++;
            }
            else if(map.containsKey(cardType)){
                List<Integer> list = map.get(cardType);
                list.add(Integer.parseInt(cardNumber));
                map.put(cardType,list);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(cardNumber));
                map.put(cardType,list);
            }
        }

        for(String s : map.keySet()){
            List<Integer> list = map.get(s);
            int count = 0 ;
            Collections.sort(list);
            for(int i = 0  ; i < list.size() - 1;i++){
                if(list.get(i+1) - list.get(i) == 1 ) {
                    count ++;
                    if(count >=3){
                        return true;
                    }
                }
                else{
                    count = 0 ;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SquareQuestion s = new SquareQuestion();
        int wildCard = 7;
        String []s1 = new String[]{"s1","s2","s4" , "s7"};
        System.out.println(s.isFlush1(s1 , wildCard ));
    }
}
