package LeetcodePrograms;

import java.util.*;


/**
 * Created by rkhurana on 6/16/18.
 */
// Q149 Max Points on a line  ( maximum points on a plane )   #TopInterviewQuestion
// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

public class MaximumPoints {
    public int maxPoints(Point[] points) {
        if (points.length <= 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
            int samex = 1;
            int samep = 0;
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    if ((points[j].x == points[i].x) && (points[j].y == points[i].y)) {
                        samep++;
                    }
                    if (points[j].x == points[i].x) {
                        samex++;
                        continue;
                    }
                    double k = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);
                    if (hm.containsKey(k)) {
                        hm.put(k, hm.get(k) + 1);
                    } else {
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }

    //or
    public int maxPoints2(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        HashMap<Double, Integer> result = new HashMap<Double, Integer>();
        int max = 0;

        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;//
            int vertical = 0;
            for (int j = i + 1; j < points.length; j++) {

                //handle duplicates and vertical
                if (points[i].x == points[j].x) {
                    if (points[i].y == points[j].y) {
                        duplicate++;
                    } else {
                        vertical++;
                    }
                } else {
                    double slope = points[j].y == points[i].y ? 0.0
                            : (1.0 * (points[j].y - points[i].y))
                                    / (points[j].x - points[i].x);
                    if (result.get(slope) != null) {
                        result.put(slope, result.get(slope) + 1);
                    } else {
                        result.put(slope, 1);
                    }
                }
            }
            for (Integer count : result.values()) {
                if (count + duplicate > max) {
                    max = count + duplicate;
                }
            }
            max = Math.max(vertical + duplicate, max);
            result.clear();
        }
        return max;
    }

    //    ----------------------------------------------------------------------------

    public int maxPointsFinalSolution(int[][] points) {
        int n = points.length;
        if (n < 2) {
            return n;
        }
        Set<String> set = new HashSet<>();
        int max = 1;

        for (int i = 0; i < n && !set.contains(points[i][0] + "-" + points[i][1]); i++) {
            int[] a = points[i];
            int same = 0;
            Map<Double, Integer> map = new HashMap<>();
            int localMax = 1;

            for (int j = i + 1; j < n; j++) {
                if (isSame(a, points[j])) {
                    same++;
                    continue;
                }

                double slope = getSlope(a, points[j]);
                map.put(slope, map.getOrDefault(slope, 1) + 1);
                localMax = Math.max(localMax, map.get(slope));
            }

            set.add(a[0] + "-" + a[1]);
            max = Math.max(max, localMax + same);
        }

        return max;
    }

    private boolean isSame(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }

    private double getSlope(int[] a, int[] b) {
        if (a[0] == b[0]) {
            return Double.MAX_VALUE;
        }
        if (a[1] == b[1]) {
            return 0;
        }
        return ((double) a[0] - b[0]) / ((double) a[1] - b[1]);
    }

    public static void main(String[] args) {
        Point P1 = new Point(1, 1);
        Point P2 = new Point(3, 2);
        Point P3 = new Point(5, 3);
        Point P4 = new Point(4, 1);
        Point P5 = new Point(2, 3);
        Point P6 = new Point(1, 4);
        Point[] points = { P1, P2, P3, P4, P5, P6 };

        int[][] point = { { 1, 1 }, { 3, 2 }, { 5, 3 }, { 4, 1 }, { 2, 3 }, { 1, 4 } };
        MaximumPoints maxPoints = new MaximumPoints();
        System.out.println(maxPoints.maxPointsFinalSolution(point));

    }
}
