package LeetcodePrograms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rkhurana on 8/1/18.
 */
public class MinimumArrowsToBurstBalloons {
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
//        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Integer o11 =o1[1];
                Integer o22 =o2[1];
                return o11.compareTo(o22);
            }
        });

        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }

    public static void main(String [] args){
        int [][]points = {{3,5},{3,3},  {7,12}, {10,16}};
        System.out.println(findMinArrowShots(points));
    }
}
