package LeetcodePrograms.InterviewQuestions;
import java.util.*;
public class OverlapInterval {
    //rsavaliya@ebay.com
/*
You are given two lists of intervals, A and B.
In A, the intervals are sorted by their starting points. None of the intervals within A overlap.
Likewise, in B, the intervals are sorted by their starting points. None of the intervals within B overlap.
Return the intervals that overlap between the two lists.

Example:
A: {[0,4], [7,12]}
B: {[1,3], [5,8], [9,11]}
Return:
{[1,3], [7,8], [9,11]}
*/
        static class Interval{
            int start;
            int end;
            public Interval(int s , int e){
                start = s;
                end = e;
            }

            @Override
            public String toString(){
                return "start " + start + " end " + end;
            }
        }
        public static List<Interval> findAllOverlappingSlots(List<Interval> interval1, List<Interval> interval2,int duration){
            int length1 = interval1.size();
            int length2 = interval2.size();
            List<Interval> result = new ArrayList<>();
            int i =0 ,j=0;
            while(i < length1 && j < length2){
                if(interval1.get(i).start > interval2.get(j).end){
                    j++;
                }else if (interval2.get(j).start > interval1.get(i).end){
                    i++;
                }else{
                    int end = Math.min (interval1.get(i).end , interval2.get(j).end);
                    int start = Math.max (interval1.get(i).start , interval2.get(j).start);
                    Interval temp = new Interval(start,end);
                    result.add(temp);
                    //whichever ends first will be removed from consideration as the next interval in the series might still overlap
                    if(interval1.get(i).end < interval2.get(j).end)
                        i++;
                    else
                        j++;

                }
            }
            return result;
        }
/*
    A= [[0,4], [7,12]]
    B= [[1,3], [5,8], [9,11]]
    expected = [[1,3], [7,8], [9,11]]
*/
        public static void main(String[] args) {
            List<Interval> intervalList1 = Arrays.asList(new Interval(0,4),new Interval(7,12));
            List<Interval> intervalList2 = Arrays.asList(new Interval(1,3),new Interval(5,8),new Interval(9,11));
            System.out.println(findAllOverlappingSlots(intervalList1,intervalList2,8));
        }
    }



