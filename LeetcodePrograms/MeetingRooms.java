package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 10/16/18.
 */
public class MeetingRooms {
    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // it has  to be a priority queue instead of a simple queue.. for example [[0,30],[5,10],[15,20]]

        int count = 1;
        queue.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < queue.peek()) {
                count++;

            } else {
                queue.poll();
            }

            queue.offer(intervals[i].end); //this is the main point to remember
        }
        return count;
    }



    public static int minMeetingRooms2(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }


    public static void main(String []args){
        Interval []interval = new Interval[5];
//        interval[0] = new Interval(0,30);
//        interval[1] = new Interval(35,40);
//        interval[2] = new Interval(35,45);
//        1,5,6,9,10
//        8,11,12,13,14
        interval[0] = new Interval(1,8);
        interval[1] = new Interval(5,11);
        interval[2] = new Interval(6,12);

        interval[3] = new Interval(9,13);
        interval[4] = new Interval(10,14);


        //        interval[3] = new Interval(15,25);
//        interval[4] = new Interval(10,14);




        System.out.println(minMeetingRooms2(interval));
    }
}
