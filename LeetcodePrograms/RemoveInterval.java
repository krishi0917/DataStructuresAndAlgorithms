package LeetcodePrograms;

import java.util.*;


/**
 * @author Rishi Khurana
 * 1272. Remove Interval
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers
 * x such that a <= x < b.
 *
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 *
 * Return a sorted list of intervals after all such removals.
 * Example 1:
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 */
public class RemoveInterval {
//    time O(n) , O(1) space
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[1] <= toBeRemoved[0] || i[0] >= toBeRemoved[1]) { // no overlap.
                ans.add(Arrays.asList(i[0], i[1]));
            }else { // i[1] > toBeRemoved[0] && i[0] < toBeRemoved[1].
                if(i[0] < toBeRemoved[0]) // left end no overlap.
                    ans.add(Arrays.asList(i[0], toBeRemoved[0]));
                if (i[1] > toBeRemoved[1]) // right end no overlap.
                    ans.add(Arrays.asList(toBeRemoved[1], i[1]));
            }
        }
        return ans;
    }

    // another solution
    public List<List<Integer>> removeInterval2(int[][] intervals, int[] toBeRemoved) {
        if (intervals == null || intervals.length == 0) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();
        if (toBeRemoved == null) {
            for (int[] interval : intervals)
                result.add(new ArrayList<>(Arrays.asList(interval[0], interval[1])));
            return result;
        }

        for (int[] interval : intervals) {

            if (interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1])
                result.add(Arrays.asList(interval[0], interval[1]));

            else {
                // Find intersection
                if (interval[0] < toBeRemoved[0])
                    result.add(Arrays.asList(interval[0], toBeRemoved[0]));

                if (interval[1] > toBeRemoved[1])
                    result.add(Arrays.asList(toBeRemoved[1], interval[1]));
            }
        }
        return result;
    }

}
