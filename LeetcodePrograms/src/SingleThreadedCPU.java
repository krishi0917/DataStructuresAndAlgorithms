package LeetcodePrograms.src;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {
    /*
    1834. Single-Threaded CPU
You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei]
means that the i task will be available to process at enqueueTime i and will take processingTime i to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

Example 1:

Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
Explanation: The events go as follows:
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
Example 2:

Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
Output: [4,3,2,0,1]
Explanation: The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.
     */

/* Approach
    First of all we should see to it that if enqueue time of a task less than or equal to currentTime, then,
    all these tasks will be added into our available list.
    So we have to choose from the available list, some tasks that have less amount of required completion time.
    Also if many tasks have same completion time we will have to take the task with least index.
    Since we will be adding task to our available list at anytime, we use a priority queue so that when the task is added, we need not sort the array once again. Add operation in priority queue only takes logN time.
            Required Preprocessing
    Since we are to consider 3 factors, i.e enqueueTime, requiredTime and index, we will add index to every tasks[i].
    Also we will sort the tasks array based on it's enqueueTime, requiredTime and index respectively. We do this so that we can get all available tasks based on its enqueueTime.

// TC = O(nlogn)
// SC = O(n)
 */
public int[] getOrder(int[][] tasks) {
    class Task {
        int idx;
        int enqueueTime;
        int processingTime;

        Task(int idx , int en , int pro){
            this.idx = idx;
            this.enqueueTime = en;
            this.processingTime = pro;
        }
    }

    int n = tasks.length;
    Task [] arr = new Task[n];
    for(int i = 0 ;i<n;i++){
        arr[i] = new Task(i, tasks[i][0],tasks[i][1]);
    }

    Arrays.sort(arr, (a, b)->{
        return Integer.compare(a.enqueueTime,b.enqueueTime);
    });

    PriorityQueue<Task> p = new PriorityQueue<>((a, b)->{
        if(a.processingTime == b.processingTime){
            return Integer.compare(a.idx,b.idx);
        }
        return Integer.compare(a.processingTime,b.processingTime);
    });

    int[] ans = new int[n];
    int ansIdx = 0;
    int taskIdx = 0;
    int curTime= 0;

    while(ansIdx < n){
        while(taskIdx < n && arr[taskIdx].enqueueTime <= curTime){
            p.offer(arr[taskIdx++]);
        }
        if(p.isEmpty()){
            curTime = arr[taskIdx].enqueueTime;
        }else{
            curTime += p.peek().processingTime;
            ans[ansIdx++] = p.poll().idx;
        }
    }
    return ans;
}

}
