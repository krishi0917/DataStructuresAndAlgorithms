package LeetcodePrograms;

import java.util.TreeMap;
import java.util.TreeSet;


/**
 * @author Rishi Khurana
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a
 * double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common
 * to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class MyCalendarI {
    TreeMap<Integer, Integer> calendar;

    public MyCalendarI() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calendar.floorKey(start);
        if (floorKey != null && calendar.get(floorKey) > start)
            return false;
        Integer ceilingKey = calendar.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end)
            return false;

        calendar.put(start, end);
        return true;
    }

    public static void main(String []args){
        MyCalendarI MyCalendar = new MyCalendarI();
        System.out.println(MyCalendar.book2(10, 20)); // returns true
        System.out.println(MyCalendar.book2(25, 30));
        System.out.println(MyCalendar.book2(5, 10));
        System.out.println(MyCalendar.book2(20, 25)); // returns false
        System.out.println(MyCalendar.book2(25, 30));
        System.out.println(MyCalendar.book2(1, 2));
        System.out.println(MyCalendar.book2(3, 4));

    }


    TreeSet<int[]> books = new TreeSet<int[]>((int[] a, int[] b) -> a[0] - b[0]);

    public boolean book2(int s, int e) {
        int[] book = new int[] { s, e }, floor = books.floor(book), ceiling = books.ceiling(book);
        if (floor != null && s < floor[1])
            return false; // (s, e) start within floor
        if (ceiling != null && ceiling[0] < e)
            return false; // ceiling start within (s, e)
        books.add(book);
        return true;
    }
}
