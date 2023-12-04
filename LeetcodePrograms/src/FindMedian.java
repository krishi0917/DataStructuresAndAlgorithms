package LeetcodePrograms.src;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median
 * is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */


/*
Approach: I keep two heaps (or priority queues)/ Max-heap / small has the smaller half of the numbers.
          Min-heap / large has the larger half of the numbers. This gives me direct access to the one or two middle values
         (they're the tops of the heaps), so getting the median takes O(1) time. And adding a number takes O(log n) time.
         add operation is O(logn)

Why we do push pop push
The invariant of the algorithm is two heaps, small and large, each represent half of the current list.
The length of smaller half is kept to be n / 2 at all time and the length of the larger half is either n / 2 or n / 2 + 1 depend on n's parity.

This way we only need to peek the two heaps' top number to calculate median.

Any time before we add a new number, there are two scenarios, (total n numbers, k = n / 2):

(1) length of (small, large) == (k, k)
(2) length of (small, large) == (k, k + 1)
After adding the number, total (n + 1) numbers, they will become:

(1) length of (small, large) == (k, k + 1)
(2) length of (small, large) == (k + 1, k + 1)
Here we take the first scenario for example, we know the large will gain one more item and small will remain the same size,
but we cannot just push the item into large. What we should do is we push the new number into small and pop the maximum item
from small then push it into large (all the pop and push here are heappop and heappush). By doing this kind of operations for
the two scenarios we can keep our invariant.

Therefore to add a number, we have 3 O(log n) heap operations. Luckily the heapq provided us a function "heappushpop"
which saves some time by combine two into one. The document says:

Push item on the heap, then pop and return the smallest item from the heap. The combined action runs more efficiently than heappush()
followed by a separate call to heappop().

Alltogether, the add operation is O(logn), The findMedian operation is O(1).

 */
public class FindMedian {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private boolean even = true;
    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return small.peek();
    }
    public void addNum(int num) {
        if (even) {

            large.offer(num);
            int x = large.poll();
            System.out.println(x);
            small.offer(x);
        } else {
            small.offer(num);
            int x = small.poll();
            System.out.println(x);

            large.offer(x);
        }
        even = !even;
        System.out.println("Small " + small.peek());
        System.out.println("large " + large.peek());
    }

    public static void main(String [] args){
        FindMedian findMedian = new FindMedian();
        findMedian.addNum(2);
        findMedian.addNum(3);
        findMedian.addNum(1);
        findMedian.addNum(5);
        findMedian.addNum(6);
        findMedian.addNum(10);
        findMedian.addNum(9);
        System.out.println(findMedian.findMedian());
    }
}
