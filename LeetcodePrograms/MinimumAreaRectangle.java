package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 939. Minimum Area Rectangle
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with
 * sides parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 * Example 1:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 */

// Solution
// basic idea is to find the diagonals so that if we multiply the diagonals we can get the area
// and the way we find the diagonal is by checking if the x cordinates of both the points or the y coordinate of both
// the points doesnt coincide

public class MinimumAreaRectangle {
    public static  int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    // my concern is we are iterating all the points . run time complexoty should be atleast better
                    // than this
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
    public static void main(String []args){
        int [][]input = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        System.out.println(minAreaRect(input));
    }
}
