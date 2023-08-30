package LeetcodePrograms;

import java.util.Arrays;
import java.util.List;

public class EmployeeFreeTime2 {
//      input:  slotsA = [[10, 50], [60, 120], [140, 210]]
//              slotsB = [[0, 15], [60, 70]]  index j
//
//              duration = 8
//      First common time slot for given duration
//      output: [60, 68]
    static class Interval {
        int start;
        int end;
        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString(){
            return "start " + start + " end " + end;
        }
    }

    public static Interval findFirstCommonSlotOfGivenDuration(List<Interval> interval1, List<Interval> interval2, int duration) {
        int length1 = interval1.size();
        int length2 = interval2.size();
        int i = 0, j = 0;
        while (i < length1 && j < length2) {
            if (interval1.get(i).start > interval2.get(j).end) {
                j++;
            } else if (interval2.get(j).start > interval1.get(i).end) {
                i++;
            } else {
                int end = Math.min(interval1.get(i).end, interval2.get(j).end);
                int start = Math.max(interval1.get(i).start, interval2.get(j).start);
                if (end - start >= duration) {
                    Interval result = new Interval(start, start + duration);
                    return result;
                }
                i++;
                j++;
            }
        }
        return new Interval(-1, -1);
    }
 
    public static void main(String[] args) {
 
        Interval i1 = new Interval(10, 15);
        Interval i2 = new Interval(60, 120);
        Interval i3 = new Interval(140, 210);
        List < Interval > intervalList1 = Arrays.asList(i1,i2,i3);
        Interval i4 = new Interval(0, 50);
        Interval i5 = new Interval(60, 70);
        List < Interval > intervalList2 = Arrays.asList(i4,i5);
        System.out.println(findFirstCommonSlotOfGivenDuration(intervalList1,intervalList2,8));

    }
}
