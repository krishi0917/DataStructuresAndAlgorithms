package LeetcodePrograms.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MergeInterval {
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1)
			return intervals;
		// sort intervals by using self-defined Comparator
		Collections.sort(intervals, new IntervalComparator());

		ArrayList<Interval> result = new ArrayList<Interval>();
		Interval prev = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (prev.end >= curr.start) {
				// merged case
				Interval merged = new Interval(prev.start, Math.max(prev.end,
						curr.end));
				prev = merged;
			} else {
				result.add(prev);
				prev = curr;
			}
		}
		result.add(prev);
		return result;
	}

	public static void main(String[] args) {
		// Given [1,3],[2,6],[8,10],[15,18],
		// return [1,6],[8,10],[15,18].
	}
	class IntervalComparator implements Comparator<Interval> {
		public int compare(Interval i1, Interval i2) {
			return i1.start - i2.start;


		}
	}
}

