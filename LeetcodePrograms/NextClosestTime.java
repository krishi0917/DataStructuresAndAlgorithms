package LeetcodePrograms;

import java.util.ArrayList;
import java.util.*;


/**
 * @author Rishi Khurana
 * https://www.youtube.com/watch?v=tGpJegBlgOw
 * https://www.youtube.com/watch?v=99Gw7Hezii8
 * 681. Next Closest Time
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is
 * no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9"
 * are all invalid.
 *
 * Example 1:
 *
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is
 * not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 *
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned
 * time is next day's time since it is smaller than the input time numerically.
 */
public class NextClosestTime {

    // Solution convert the time to minutes and add 1 minute to it.
    // second step is to put the Input variable that we got, put it in the hashset and check for the next valid time
    public String nextClosestTime(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60; // changing hour to minutes
        minutes += Integer.parseInt(time.substring(3));

        HashSet<Integer> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            digits.add(c - '0');
        }
        while (true) {
            minutes = (minutes + 1) % (24 * 60); // so as to make sure the conversion goes right. for example if time is
            // 23:59 it should be changed to 00:00

            //now parsing the minutes to hour:minutes
            int nextTime[] = { minutes / 60 / 10 /* to get the first number*/, minutes / 60 % 10, /*second number*/
                    minutes % 60 / 10, minutes % 60 % 10 };
            boolean isValid = true;
            for (int digit : nextTime) {
                if (!digits.contains(digit)) {
                    isValid = false;
                }
            }
            if (isValid) {
                return String.format("%02d:%02d", minutes / 60, minutes % 60);
            }
        }
    }

    public static void main(String []args){
        String input = "19:34";
        String x = nextClosestTime2(input);
    }
    private static String result = "";
    static int minDiff = Integer.MAX_VALUE;
    public static String nextClosestTime2(String time) {
        int originalMins = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3,5));
        HashSet<Character> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if(c != ':')
                digits.add(c);
        }
        List<Character> list = new ArrayList<Character>(digits);
        dfs(list,"",originalMins);
        return result;
    }

    private static void dfs(final List<Character> list,  String temp,  int originalMins) {
        if(temp.length() == 4){
            String hourString = temp.substring(0,2);
            String minString = temp.substring(2);
            int hour = Integer.parseInt(hourString);
            int min = Integer.parseInt(minString);
            if(hour > 23 || min > 59) return ;
            int totalMins = hour *60 + min ;
            totalMins += (totalMins <= originalMins ? 24*60 :0);
            //if totalMins < originalMins , we are going to add one day
            if(minDiff > (totalMins - originalMins)){
                minDiff = totalMins - originalMins;
                result = hourString + ":" + minString;
            }
            return ;
        }
        for(int i = 0 ; i < list.size() ; i++){
            temp+=list.get(i);
            dfs(list,temp,originalMins);
            temp = temp.substring(0,temp.length()-1);
        }
        return ;
    }
}