package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/28/19.
 */
// 359. Logger Rate Limiter   #UberQuestion
// Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only
// if it is not printed in the last 10 seconds. Given a message and a timestamp (in seconds granularity), return true if the message
// should be printed in the given timestamp, otherwise returns false.
class LoggerRateLimiter {
    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int threshold = map.getOrDefault(message, timestamp);
        if (threshold <= timestamp) { // contains two scenarios: message hasn't occurred & timeout
            map.put(message, timestamp + 10);
            return true;
        }
        return false;
    }
}