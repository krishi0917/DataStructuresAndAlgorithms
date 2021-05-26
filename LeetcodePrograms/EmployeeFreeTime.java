package LeetcodePrograms;
import java.util.*;
// LeetCode 759 Employee Free Time
// We are given a list schedule of employees, which represents the working time for each employee.
// Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
// Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
// understood the priority queue one solution but not the arraylist one

//there is one more method by using merge sort
/**
 * Created by rkhurana on 3/17/19.
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> result = new ArrayList<>();
        List<Interval> timeLine = new ArrayList<>();
//        avails.forEach(e -> timeLine.addAll(e));            // do either of these two lines or the below code
//        Collections.sort(timeLine, ((a, b) -> a.start - b.start));
        for(List<Interval> interval  : avails){
            timeLine.addAll(interval);
        }
        Collections.sort(timeLine, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                Integer i1 =  o1.start;
                Integer i2 = o2.start;
                return i1.compareTo(i2);
            }
        });
        Interval temp = timeLine.get(0);
        for(Interval each : timeLine) {
            if(temp.end < each.start) {
                result.add(new Interval(temp.end, each.start));
                temp = each;
            }else{
                temp = temp.end < each.end ? each : temp;
                System.out.println("current temp is " + temp.start  + "  "  + temp.end);
                System.out.println("current each is " + each.start + "   " + each.end);
            }
        }
        return result;
    }

    public List<Interval> employeeFreeTime2(List<List<Interval>> avails) {

        List<Interval> result = new ArrayList<>();

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        avails.forEach(e -> pq.addAll(e));

        Interval temp = pq.poll();
        while(!pq.isEmpty()) {
            if(temp.end < pq.peek().start) { // no intersect
                result.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll(); // becomes the next temp interval
            }else { // intersect or sub merged
                temp = temp.end < pq.peek().end ? pq.peek() : temp;
                pq.poll();
            }
        }
        return result;
    }
    public static void main(String []args){

        EmployeeFreeTime eft = new EmployeeFreeTime();
        Interval i1 = new Interval(1,2);
        Interval i2 = new Interval(5,6);
        Interval i3 = new Interval(1,3);
        Interval i4 = new Interval(4,10);
        List<Interval> list1 = new ArrayList<>();
        List<Interval> list2 = new ArrayList<>();
        List<Interval> list3 = new ArrayList<>();
        list1.add(i1);
        list1.add(i2);
        list2.add(i3);
        list3.add(i4);
        List<List<Interval>> finalList = new ArrayList<>();
        finalList.add(list1);
        finalList.add(list2);
        finalList.add(list3);
        System.out.print(eft.employeeFreeTime2(finalList));
    }
}
