package LeetcodePrograms.src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
/**
 * 1235. Maximum Profit in Job Scheduling
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of
 * profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are
 * no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.

 * Example 1:
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 */
public class MaximumProfitInJobScheduling {


    class Job {
        int start, finish, profit;
        Job(int start, int finish, int profit) {
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }


    class FinishTimeComparator implements Comparator<Job>{

        @Override
        public int compare(Job arg0, Job arg1) {
            if(arg0.finish <= arg1.finish){
                return -1;
            }else{
                return 1;
            }
        }

    }

    /**
     * Tushar roy solution.. use this one.. dp approach
     * #Approach Sort the jobs by finish time.
     * For every job find the first job which does not overlap with this job
     * and see if this job profit plus profit till last non overlapping job is greater
     * than profit till last job.
     * @param jobs
     * @return
     */
    public int maximum(Job[] jobs){
        int T[] = new int[jobs.length];
        FinishTimeComparator comparator = new FinishTimeComparator();
        Arrays.sort(jobs, comparator);

        T[0] = jobs[0].profit;
        for(int i=1; i < jobs.length; i++){
            T[i] = Math.max(jobs[i].profit, T[i-1]);
            for(int j=i-1; j >=0; j--){
                if(jobs[j].finish <= jobs[i].start){
                    T[i] = Math.max(T[i], jobs[i].profit + T[j]);
                    break;
                }
            }
        }
        int maxVal = Integer.MIN_VALUE;
        for (int val : T) {
            if (maxVal < val) {
                maxVal = val;
            }
        }
        return maxVal;
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i=0;i<n;i++) {
            jobs[i] = new Job(startTime[i],endTime[i],profit[i]);
        }
        return scheduleApt(jobs);
    }

    private int scheduleApt(Job[] jobs) {
        // Sort jobs according to finish time
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.finish));
        // dp[i] stores the profit for jobs till jobs[i]
        // (including jobs[i])
        int n = jobs.length;
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for (int i=1; i<n; i++) {
            // Profit including the current job
            int profit = jobs[i].profit;
            int l = search(jobs, i);
            if (l != -1)
                profit += dp[l];
            // Store maximum of including and excluding
            dp[i] = Math.max(profit, dp[i-1]);
        }

        return dp[n-1];
    }

    private int search(Job[] jobs, int index) {
        int start = 0, end = index - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (jobs[mid].finish <= jobs[index].start) {
                if (jobs[mid + 1].finish <= jobs[index].start)
                    start = mid + 1;
                else
                    return mid;
            }
            else
                end = mid - 1;
        }
        return -1;
    }

    public static void main(String []args){

        MaximumProfitInJobScheduling m = new MaximumProfitInJobScheduling();
        int[]startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
        System.out.println(m.jobScheduling1(startTime , endTime , profit));
    }
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}
