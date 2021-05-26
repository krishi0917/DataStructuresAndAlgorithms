package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 593. Valid Square
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 *
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 *
 * Example:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 */
public class ValidSquare {
// Solution: the idea is to find the square of lengths, and validate that
// There are only two equal longest lenghts. which will be diagonal
// The non longest lengths are all equal which will be the sides

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        long[] lengths = {length(p1, p2), length(p2, p3), length(p3, p4),
                length(p4, p1), length(p1, p3),length(p2, p4)}; // all 6 sides

        long max = 0, nonMax = 0;
        for(long len : lengths) {
            max = Math.max(max, len);
        }
        int count = 0;
        for(int i = 0; i < lengths.length; i++) {
            if(lengths[i] == max)
                count++;
            else nonMax = lengths[i]; // non diagonal side.
        }
        if(count != 2) return false; // diagonals lenghts have to be same.

        for(long len : lengths) {
            if(len != max && len != nonMax) return false; // sides have to be same length
        }
        return true;
    }
    private long length(int[] p1, int[] p2) {
        return (long)Math.pow(p1[0]-p2[0],2) + (long)Math.pow(p1[1]-p2[1], 2);
    }

    //using hashmap
    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] dis = new int[]{distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3)
                , distance(p2, p4), distance(p3, p4)};
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i : dis) {
            max = Math.max(max, i);
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        return map.get(max) == 2 && map.size() == 2;
    }

    public int distance(int[] p1, int[] p2) {
        return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }
}
