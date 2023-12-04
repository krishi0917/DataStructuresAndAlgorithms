package LeetcodePrograms.src;
// #LinkedIn #FacebookQuestion
import java.util.*;
/**
 * 636. Exclusive Time of Functions
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 *
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends,
 * its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a
 * function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 *
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
 * For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call
 * with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 *
 * A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice,
 * one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 *
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.
 * Example 1:
 *
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3,4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
 * Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
 * Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 */

// https://www.youtube.com/watch?v=VqN4cqa3vgI

public class  ExclusiveTimeofFunctions {
    // second method is better
    public static int[] exclusiveTime(int n, List<String> logs) {
        // separate time to several intervals, add interval to their function
        int[] result = new int[n];
        Stack<Integer> st = new Stack<>(); // store id and not timestamp
        int pre = 0;
        // pre means the start of the interval
        for(String log: logs) {
            String[] arr = log.split(":");
            if(arr[1].equals("start")) {
                if(!st.isEmpty())
                    result[st.peek()] += Integer.parseInt(arr[2]) - pre;
                // arr[2] is the start of next interval, doesn't belong to current interval.
                st.push(Integer.parseInt(arr[0]));
                pre = Integer.parseInt(arr[2]);
            } else {
                result[st.pop()] += Integer.parseInt(arr[2]) - pre + 1;
                // arr[2] is end of current interval, belong to current interval. That's why we have +1 here
                pre = Integer.parseInt(arr[2]) + 1;
                // pre means the start of next interval, so we need to +1
            }
        }
        return result;
    }

    public int[] exclusiveTime2(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                result[top.id] += (log.time - top.time + 1);
                if (!stack.isEmpty()) {
                    result[stack.peek().id] -= (log.time - top.time + 1);
                }
            }
        }

        return result;
    }

    public static class Log {
        public int id;
        public boolean isStart;
        public int time;

        public Log(String content) {
            String[] strs = content.split(":");
            id = Integer.valueOf(strs[0]);
            isStart = strs[1].equals("start");
            time = Integer.valueOf(strs[2]);
        }
    }

    public static void main(String[] args) {
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");


        int res[] = exclusiveTime(2, logs);
        for(int i = 0 ; i < res.length ; i++)
            System.out.println(res[i] + " ");
    }
}