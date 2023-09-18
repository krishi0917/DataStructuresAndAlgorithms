package LeetcodePrograms.InterviewQuestions;

import java.util.HashMap;
// chase bank
// https://www.youtube.com/watch?v=5gHnQ4lfDko&ab_channel=NareshGupta
public class ChaseDivisible {
    /*
    First we have to check wether the reminder is 0 or not or we can say that we are checking if the number is already divisible by 60 or not.
    after that we are finding the target element. Eg: if n = 140 then remider = 140%60 = 20 it means we have 20 unit extra or we can say we need
    40(60-20) more units to get perfectly divisible by 60.
    So we find the target element in map and adds up in answer, if target element not found we store the current element(N%60) so that it can
    be used as target for others.
   But if remider is 0 we have to store that number only because it represents that the number is divisible by 60 and it needs the number that are already divisible by 60.
    Here is the Java code for the above approach
*/

        public int numPairsDivisibleBy60(int[] time) {
            int n = time.length, ans = 0;
            HashMap<Integer, Integer> len = new HashMap<>();
            for(int i  = 0; i < n; i++){
                int rem = time[i] % 60;
                int target = 60 - (rem);
                if(len.containsKey(target))
                    ans += len.get(target);
                if(rem != 0)
                    len.put(rem, len.getOrDefault(rem, 0) + 1);
                else
                    len.put(60, len.getOrDefault(60, 0) + 1);
            }
            return ans;
        }

}
