package LeetcodePrograms;
import java.util.ArrayList;
import java.util.List;

// 57. Insert Interval
// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
// You may assume that the intervals were initially sorted according to their start times.
// Example 1:
// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> result = new ArrayList<>();
        int i = 0;
        int start = newInterval.start;
        int end = newInterval.end;


        while (i < intervals.size() && intervals.get(i).end < start) {
            result.add(intervals.get(i++));
        }

        while (i < intervals.size() && intervals.get(i).start <= end) {
            start = Math.min(start, intervals.get(i).start);
            end = Math.max(end, intervals.get(i).end);
            i++;
        }

        result.add(new Interval(start, end));

        while (i < intervals.size())
            result.add(intervals.get(i++));
        return result;
    }
    public static void main(String []args){
        InsertInterval insertInterval = new InsertInterval();
        Interval i1 = new Interval(1,2);
        Interval i2 = new Interval(3,5);
        Interval i3 = new Interval(6,7);
        Interval i4 = new Interval(8,10);
        Interval i5 = new Interval(12,16);
        Interval newInterval = new Interval(4,8);
        List<Interval> list = new ArrayList<>();
        list.add(i1);list.add(i2);list.add(i3);list.add(i4);list.add(i5);

        List<Interval> result = insertInterval.insert(list,newInterval);
        for(Interval i : result){
            System.out.println(i.start +  "  "  + i.end);
        }
    }
}
