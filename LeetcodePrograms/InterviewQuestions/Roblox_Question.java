package LeetcodePrograms.InterviewQuestions;/*
Problem
For many internet services, we need to differentiate between humans and bots.
Assume we are given a sign-in attempts log with 2 columns, `time` and `IP`,
where time is seconds since unix epoch and IP is a IPv4 string, and we are
asked to implement a simple algorithm to detect bots.
Our algorithm is as follows: an IP is defined as a bot if it occurs equal or
more than `count` times.
Function signature in python
def find_bot(log: str, count) -> list[str]:
...
With the following sample file (given to you as a string),
ts,IP
2,52.0.1.2
3,23.5.0.9
3,23.5.0.9
3,23.5.0.9
3,52.0.1.2
5,52.0.1.7
5,1.1.2.2
find_bot(log, count=2) should return [52.0.1.2, 23.5.0.9]

""" PART 2
With the help of data scientists, we have improved our algorithm!
Now a bot is defined as any IP that occurs equal or more than `count` times
within `window` seconds.
Also, instead of a log file, we are a service and have to answer live queries;
in python code
class BotIpService:
def __init__(self, count, window_sec):
...
def is_bot(self, ts, ip) -> bool:
...
As a concrete example,
svc = BotIpService(count=3, window_sec=3)
# assume current time is 0
svc.is_bot(0, "127.0.0.1") returns False
# assume current time is 1
svc.is_bot(1, "127.0.0.1") returns False
# assume current time is 1
svc.is_bot(1, "127.0.0.1") returns True
# assume current time is 4
svc.is_bot(4, "9.9.9.9") returns False
# assume current time is 4
svc.is_bot(4, "127.0.0.1") returns False
# assume current time is 5
svc.is_bot(5, "127.0.0.1") returns False
A few things to notice
1) the window is left exclusive and right inclusive; that is, (] in math notation; ex if current time is 5 and window is 3, the window is [3, 4, 5]
2) our service only cares about the window relative to `ts`, so a positive label for an IP might become negative
"""

count = 3
window_suze = 2
when new record comes,  remove any extra records
current ts = 5
5-2=  3,5
then check current number of records, if acceptable say yes else sy false
127.0.0.1 --> 0, 1,1,2

*/
import java.util.*;


// Main class should be named 'Solution' and should not be public.
 class Roblox_Question {
     Map<String,List<Integer>> ipToTimeMap = new HashMap<>();

    public boolean findBotss(String log,int count, int window_sec){
        Set<String> result = new HashSet<>();
        if(log == null)
        {
            return false;
        }
        String logArr[] = log.split(",");
        if(logArr.length !=2) { return false;}
        if(ipToTimeMap.containsKey(logArr[1])){
            List<Integer> tempList = ipToTimeMap.get(logArr[1]);
            String currentTimestamp = logArr[0];
            Iterator itr = tempList.iterator();
            int acceptableStartWindow = Integer.parseInt(currentTimestamp) - window_sec;
            while(itr.hasNext()){
                int listTimestamp = (Integer)itr.next();
                if(listTimestamp <= acceptableStartWindow){
                    itr.remove();
                }
            }
            tempList.add(Integer.parseInt(logArr[0]));
            ipToTimeMap.put(logArr[1],tempList);

            if(tempList.size() >= count){
                return true;
            }else{

                return false;
            }

        }
        else{
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(logArr[0]));
            ipToTimeMap.put(logArr[1],list);
            return false;
        }

    }
    /*
    Map<String,Integer> ipTofreqMap = new HashMap<>();
    public  Set<String> findBots (List<String> logs, int count){

        if(logs == null || logs.size() == 0){
            return new HashSet<>();
        }
        Set<String> result = new HashSet<>();
        for(String log : logs){
            String logArr[] = log.split(",");
            if(ipTofreqMap.containsKey(logArr[1])){
                ipTofreqMap.put(logArr[1] , ipTofreqMap.get(logArr[1])+1);
                if(ipTofreqMap.get(logArr[1]) >= count){
                    result.add(logArr[1]);
                }
            }else{
                ipTofreqMap.put(logArr[1],1);
            }
        }
        return result;
    }
    */
    public static void main(String[] args) {
        String log1 = "0,127.0.0.1";
        String log2 = "1,127.0.0.1";
        String log3 = "1,127.0.0.1";
        String log4 = "4,9.9.9.9";
        String log5 = "4,127.0.0.1";
        String log6 = "5,127.0.0.1";

        Roblox_Question s = new Roblox_Question();

        System.out.println(s.findBotss(log1, 3,3));
        System.out.println(s.findBotss(log2, 3,3));
        System.out.println(s.findBotss(log3, 3,3));
        System.out.println(s.findBotss(log4, 3,3));
        System.out.println(s.findBotss(log5, 3,3));
        System.out.println(s.findBotss(log6, 3,3));

    }
}
