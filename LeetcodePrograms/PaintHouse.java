package LeetcodePrograms;

/**
 * Created by rkhurana on 2/25/19.
 * 256. Paint House
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
 * costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with
 * color green, and so on... Find the minimum cost to paint all houses
 *
 * Example 1:
 *
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if(costs.length==0)
            return 0;
        int[] pre = new int[3];
        int[] cur = new int[3];
        for(int j=0; j<3; j++)
        {
            pre[j] = costs[0][j];
        }

        for(int i=1; i<costs.length; i++)
        {
            cur[0] = costs[i][0] + Math.min(pre[1], pre[2]);
            cur[1] = costs[i][1] + Math.min(pre[0], pre[2]);
            cur[2] = costs[i][2] + Math.min(pre[0], pre[1]);

            int[] temp = pre;
            pre = cur;
            cur = temp;
        }
        return Math.min(pre[0], Math.min(pre[1], pre[2]));
    }

    public static void main(String []args){
        PaintHouse ph = new PaintHouse();
        int [][]costs ={{17,2,17},{16,16,5},{14,3,19}};
        System.out.print(ph.minCost(costs));
    }
}
