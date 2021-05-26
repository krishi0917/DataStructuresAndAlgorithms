package LeetcodePrograms;

/**
 * Created by rkhurana on 2/25/19.
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;

        int n = costs.length, k = costs[0].length; //n == houses, k == colors
        int[][] dp = new int[n][k];
        //No constraints
        for (int j = 0; j < k; j++){
            dp[0][j] = costs[0][j];
        }

        for (int i = 1; i < n; i++){
            for (int j = 0; j < k; j++){
                //j = color
                dp[i][j] = minCost(dp[i-1], j) + costs[i][j];
            }
        }

        return minCost(dp[n-1], -1);
    }

    int minCost(int[] dp, int j){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i< dp.length; i++){
            if (j == -1 || i!= j){ //Either we are on the last row or different colors
                min = Math.min(min, dp[i]);
            }
        }
        return min;
    }

    public static void main(String []args){
        PaintHouse ph = new PaintHouse();
        int [][]costs ={{17,2,17},{16,16,5},{14,3,19},{17,2,17},{16,16,5},{14,3,19}};
        System.out.print(ph.minCost(costs));
    }

}
