package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// * @author Rishi Khurana
// */
//@mike,T12
//@mike,T3
//@mike,T4
//@biz,T1
//@biz,T2
//@biz,T3
//@jack,T5
//@jack,T3
//@jack,T4
//@li,T4
//@li,T3
//@li,T2
public class CommonlyAskedTweets {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        Scanner s = new Scanner(System.in);
//        Scanner s2 = new Scanner(System.in);
//        System.out.println("enter the number of inputs");
//        int inputSize = s.nextInt();

//        for(int i = 0 ; i < inputSize ; i++){
//            list.add(s2.nextLine());
//        }

        list.add("@Biz,T1");
        list.add("@Jack,T7");
        list.add("@Biz,T2");
        list.add("@Biz,T5");
//        list.add("@mike,T1");
//        list.add("@mike,T3");
//        list.add("@mike,T4");
//        list.add("@biz,T1");
//        list.add("@biz,T2");
//        list.add("@biz,T3");
//        list.add("@jack,T5");
//        list.add("@jack,T3");
//        list.add("@jack,T4");
//        list.add("@li,T4");
//        list.add("@li,T3");
//        list.add("@li,T2");
//

//        while (s.hasNext()) {
//            list.add(s.nextLine());
//        }
        FindMostCommonTweetViewPath f = new FindMostCommonTweetViewPath();
        List<String> resultList = f.findMostCommonTweetViewPath(list);
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
    }
}

    class FindMostCommonTweetViewPath {
        public List<String> findMostCommonTweetViewPath(List<String> list) {
            boolean flag = false;
            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).contains(",")){
                    System.out.println("Log malformed!");
                    flag =true;
                    break;

                }
                String[] splitStr = list.get(i).split(",");
                if(splitStr[0].length() == 0 || splitStr[1].length() == 0 || !splitStr[0].contains("@")
                        || !splitStr[1].substring(0,1).equals("T") || splitStr[1].substring(1).contains("T")){

                    System.out.println("Log malformed!");
                    flag =true;
                    break;
                }
                String name = splitStr[0].substring(1);
                String tweet = splitStr[1];
                if (map.containsKey(name)) {
                    map.get(name).add(tweet);
                } else {
                    List<String> tweets = new ArrayList<>();
                    tweets.add(tweet);
                    map.put(name, tweets);
                }
            }
            Map<String, Long> tweetCounMap = new HashMap<>();
            long maxCountTweet = -1;
            for (String key : map.keySet()) {
                List<String> tweets = map.get(key);
                if (tweets.size() < 3) {
                    continue;
                } else {
                    int start = 0;
                    while (start + 3 <= tweets.size()) {
                        String tweetStr = tweets.get(start) + "," + tweets.get(start + 1) + "," + tweets.get(start + 2);
                        if (tweetCounMap.containsKey(tweetStr)) {
                            tweetCounMap.put(tweetStr, tweetCounMap.get(tweetStr) + 1);
                            if(tweetCounMap.get(tweetStr) > maxCountTweet){
                                maxCountTweet = tweetCounMap.get(tweetStr);
                            }
                        } else {
                            maxCountTweet=1;
                            tweetCounMap.put(tweetStr, (long)1);
                        }
                        start++;
                    }
                }
            }
            if(maxCountTweet == -1){
                if(!flag) {
                    System.out.println("No tweet path found!");
                }
            }

            List<String> result = new ArrayList<>();

            for (String tweetConcat : tweetCounMap.keySet()) {
                 if (tweetCounMap.get(tweetConcat) == maxCountTweet) {
                    result.add(tweetConcat);
                }
            }

            Collections.sort(result);
            return result;
        }
    }