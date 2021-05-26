package LeetcodePrograms;
//#LinkedIn WeWorkQuestion #FacebookQuestion
import java.util.*;
/**
 * Created by rkhurana on 2/26/19.
 */

// https://www.youtube.com/watch?v=VqN4cqa3vgI
public class  ExclusiveTimeofFunctions {
    // first method is better
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prev = 0;
        String []s = logs.get(0).split(":");
        stack.push(Integer.valueOf(s[0]));
        prev = Integer.valueOf(s[2]);
        for (int i = 1 ; i < logs.size() ; i++){
            s = logs.get(i).split(":");
            int func  = Integer.valueOf(s[0]);
            int time  = Integer.valueOf(s[2]);
            if(s[1].equals("start")) {
                if (!stack.isEmpty())
                    res[stack.peek()] += time - prev;
                stack.push(func);
                prev = time;
            }
            else {
                res[stack.pop()]+=time-prev + 1;
                prev = time + 1;
            }
        }
        return res;
    }

    public static int[] exclusiveTime2(int n, List<String> logs) {
        int[] res = new int[n];
        int ptime = 0, running = 0;
        Stack<Integer> stack = new Stack<>();

        for (String log : logs) {
            String[] split = log.split(":");
            int func = Integer.parseInt(split[0]);
            boolean start = split[1].equals("start");
            int time = Integer.parseInt(split[2]);
            if (!start)
                time++;

            res[running] += (time - ptime);
            if (start) {
                stack.push(running);
                running = func;
            } else {
                running = stack.pop();
            }
            ptime = time;
        }
        return res;
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