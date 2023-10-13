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
*/

        public static int numPairsDivisibleBy60(int[] time) {
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

    public static int numPairsDivisibleBy602(int[] time) {
        int count=0;
        for(int i=0;i<time.length;i++){
            time[i]=time[i]%60;     //make all values in range 60
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<time.length;i++){
            map.computeIfAbsent(time[i],key->0);
            map.compute(time[i],(key,val)->val+1);  //add them in map
        }
        for(int i=0;i<time.length;i++){
            int num=(60-time[i])%60;
            map.compute(time[i],(key,val)->val-1);  //delete the current element
            if(map.containsKey(num)){
                count+=map.get(num);//if element present in map, frequency greater then 0 then add to count
            }
        }

        return count;
    }

        public static void main(String []args){
            int []time  = {20,20,10,50,40};
            System.out.println(numPairsDivisibleBy602(time));
        }

}
