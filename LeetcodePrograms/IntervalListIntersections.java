package LeetcodePrograms;
import java.util.*;

/**
 * Created by rkhurana on 3/17/19.
 */
// Leetcode 986 Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
// Return the intersection of these two interval lists.
// #Uber #Facebook

public class IntervalListIntersections {
    public Interval[] intervalIntersection2(Interval[] A, Interval[] B) {

        List<Interval> retList = new ArrayList<Interval>();
        // Need to sort them if they are not sorted
        int i=0, j=0;
        while(i<A.length && j< B.length){
            if(A[i].end < B[j].start){    // 1.
                i++;
            } else if(B[j].end < A[i].start){    // 1.
                j++;
            }else{       //  2.
                Interval inter = new Interval(Math.max(A[i].start, B[j].start), Math.min(A[i].end, B[j].end));
                retList.add(inter);
                if(A[i].end < B[j].end){  // 3.
                    i++;
                }else
                    j++;
            }
        }
        Interval[] ret = new Interval[retList.size()];
        i=0;
        for(Interval item: retList){
            ret[i++] = item;
        }
        return ret;
    }
//    Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]

    public static void main(String []args){
        IntervalListIntersections intersections = new IntervalListIntersections();
        Interval []intervalA = new Interval[4];
        intervalA[0] = new Interval(0,2);
        intervalA[1] = new Interval(5,10);
        intervalA[2] = new Interval(13,23);
        intervalA[3] = new Interval(24,25);

        Interval []intervalB = new Interval[4];
        intervalB[0] = new Interval(1,5);
        intervalB[1] = new Interval(8,12);
        intervalB[2] = new Interval(15,24);
        intervalB[3] = new Interval(25,26);
        System.out.println(intersections.intervalIntersection2(intervalA,intervalB));


    }

}

//        int size = res.size();
//        Interval[] list = new Interval[size];
//        for (int k = 0; k < size; k++) {
//            list[k] = res.get(k);
//        }
//        return list;

