package LeetcodePrograms.src.LinkedinTop10;

/*
Approach
Initialization:

Create an array exclusiveTime to store the exclusive time for each function.
Initialize an empty stack to keep track of function calls.
Processing Logs:

Iterate through each log entry.
Parse the log entry to extract the function ID, whether it is a "start" or "end" event, and the timestamp.
Handling "start" Event:

If the current log entry is a "start" event:
If the stack is not empty, it means another function was executing. Update the exclusive time of the function at the
top of the stack by adding the time from the last recorded timestamp to the current timestamp.
Push the new function's ID and its start timestamp onto the stack.
Handling "end" Event:

If the current log entry is an "end" event:
Pop the function ID and start timestamp from the stack.
Calculate the duration the function ran by subtracting the start timestamp from the current end timestamp and
adding 1 (since the end timestamp is inclusive).
Add this duration to the exclusive time of the function.
If the stack is not empty, update the start time of the new top function on the stack (if any) to be the current end timestamp + 1,
because the top function will resume execution right after the current function ends.
Return Result:

After processing all logs, the exclusiveTime array contains the exclusive times for all functions, which can be returned as the result.
Time Complexity: O(n+m), where n is the number of functions and m is the number of log entries.
Space Complexity: O(n+m), where n is the size of the exclusiveTime array and m is the maximum size of the stack.

Given the example input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]

Step-by-Step Execution
Initialization:
exclusiveTime = [0, 0]
stack = []

Processing Logs:
Log: "0:start:0"
Function 0 starts at timestamp 0.
Update stack: stack.push(new int[]{0, 0})
stack = [[0, 0]]

Log: "1:start:2"
Function 1 starts at timestamp 2.
Update exclusive time of function 0: exclusiveTime[0] += 2 - 0
exclusiveTime = [2, 0]
Update stack: stack.push(new int[]{1, 2})
stack = [[0, 0], [1, 2]]

Log: "1:end:5"
Function 1 ends at timestamp 5.
Pop function 1: stack.pop()
Calculate duration: exclusiveTime[1] += 5 - 2 + 1
exclusiveTime = [2, 4]
Update start time of function 0: stack.peek()[1] = 6
stack = [[0, 6]]

Log: "0:end:6"
Function 0 ends at timestamp 6.
Pop function 0: stack.pop()
Calculate duration: exclusiveTime[0] += 6 - 6 + 1
exclusiveTime = [3, 4]
stack = []
Return Result:

exclusiveTime = [3, 4]

Time Complexity: O(n+m), where n is the number of functions and m is the number of log entries.
Space Complexity: O(n+m), where n is the size of the exclusiveTime array and m is the maximum size of the stack.

*/

import java.util.*;

public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] exclusiveTime = new int[n];
        Stack<int[]> stack = new Stack<>();

        for (String log : logs) {
            String[] parts = log.split(":");
            int funcId = Integer.parseInt(parts[0]);
            String action = parts[1];
            int timestamp = Integer.parseInt(parts[2]);

            if (action.equals("start")) {
                if (!stack.isEmpty()) {
                    int[] top = stack.peek();
                    exclusiveTime[top[0]] += timestamp - top[1];
                }
                stack.push(new int[]{funcId, timestamp});
            } else {
                int[] top = stack.pop();
                exclusiveTime[top[0]] += timestamp - top[1] + 1;
                if (!stack.isEmpty()) {
                    stack.peek()[1] = timestamp + 1;
                }
            }
        }

        return exclusiveTime;
    }

    public static void main(String[] args) {
        ExclusiveTimeOfFunctions solution = new ExclusiveTimeOfFunctions();
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        int[] result = solution.exclusiveTime(n, logs);
        System.out.println(Arrays.toString(result));  // Output: [3, 4]
    }
}
