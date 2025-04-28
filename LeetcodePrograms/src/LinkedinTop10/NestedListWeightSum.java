package LeetcodePrograms.src.LinkedinTop10;

import LeetcodePrograms.src.NestedInteger;

import java.util.*;
public class NestedListWeightSum {
    /*
  The algorithm calculates the depth-weighted sum of a nested list of integers. It uses a breadth-first search (BFS) approach with a queue to process each element level by level, keeping track of the current depth. Here's a brief explanation of how it works:
Initialization:
A queue is initialized and all elements of nestedList are added to it.
Variables depth and total are initialized to 1 and 0, respectively.
Processing the Queue:

While the queue is not empty, the algorithm processes each element in the queue at the current depth.
For each element in the queue:
If the element is an integer, it is added to the total after multiplying by the current depth.
If the element is a list, all its elements are added to the queue to be processed at the next depth level.
After processing all elements at the current depth, the depth is incremented.
Returning the Result:

After processing all levels, the accumulated total is returned as the result.

Time complexity : O(N).
Each nested element is put on the queue and removed from the queue exactly once.

Space complexity : O(N).
The worst-case for space complexity in BFS occurs where most of the elements are in a single layer, for example, a flat list such as [1, 2, 3, 4, 5]

  */
    class NestedInteger{
        public boolean isInteger() {

            return false;
        }

        public int getInteger() {

            return 0;
        }

        public List<NestedInteger> getList() {
            return null;
        }
    }

    // Iterative
//    Initialization:
//    A queue is initialized and all elements of nestedList are added to it.
//    Variables depth and total are initialized to 1 and 0, respectively.
//    Processing the Queue:
//
//    While the queue is not empty, the algorithm processes each element in the queue at the current depth.
//    For each element in the queue:
//    If the element is an integer, it is added to the total after multiplying by the current depth.
//    If the element is a list, all its elements are added to the queue to be processed at the next depth level.
//    After processing all elements at the current depth, the depth is incremented.
//    Returning the Result:
//
//    After processing all levels, the accumulated total is returned as the result.
//
//    Time complexity : O(N).
//    Each nested element is put on the queue and removed from the queue exactly once.
//
//    Space complexity : O(N).
//    The worst-case for space complexity in BFS occurs where most of the elements are in a single layer, for example, a flat list such as [1, 2, 3, 4, 5]

    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        int depth = 1;
        int total = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nested = queue.poll();
                if (nested.isInteger()) {
                    total += nested.getInteger() * depth;
                } else {
                    queue.addAll(nested.getList());
                }
            }
            depth++;
        }
        return total;
    }

    // Iterative nested list weight sum
    // time and space complexity O(n)
    public int depthSumIterative(List<NestedInteger> nestedList) {
        int level = 1, total = 0;
        while (nestedList.size() != 0) {
            List<NestedInteger> next = new LinkedList<>();
            for (NestedInteger nInt : nestedList) {
                if (nInt.isInteger())
                    total += nInt.getInteger() * level;
                else
                    next.addAll(nInt.getList());
            }
            level++;
            nestedList =  next;
        }
        return total;
    }

    // recursive
    public int depthSum2(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    public int helper(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.size() == 0)
            return 0;
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            } else {
                sum += helper(ni.getList(), depth + 1);
            }
        }
        return sum;
    }


    // NestedListWeightSum 2
/*
    We initialize a queue and enqueue all elements of the nestedList.
    We use a while loop to iterate through the elements in the queue.
    In each iteration of the loop, we process all elements at the current level by dequeuing them and summing up their integer values (if they are integers).
    For each nested list encountered, we enqueue its elements to be processed in the next iteration.
    We maintain two variables: prev and total. prev accumulates the sum of integers encountered at all levels so far, and total accumulates the sum of integers multiplied by their weights.
    At each iteration, we update prev by adding the sum of integers at the current level, and we update total by adding prev, which effectively multiplies the sum of integers at the current level by the appropriate weight and adds it to the total sum.

    Complexity Analysis

    Let N be the total number of nested elements in the input list.

    For example, the list [[[[[1]]]], 2] contains 4 nested lists and 2 nested integers (111 and 222), so N is 6,

    Each nested element is put in the queue and removed from the queue exactly once.

    Space complexity: O(N)

    The worst-case for space complexity in BFS occurs where the majority of elements are at the same depth,
    as all of the elements at that depth will be in the queue at the same time. Therefore worst-case space complexity is O(N).

*/
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
        int prev = 0;
        int total = 0;
        for (NestedInteger next: nestedList) {
            queue.offer(next);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger())
                    levelSum += current.getInteger();
                List<NestedInteger> nextList = current.getList();
                if (nextList != null) {
                    for (NestedInteger next: nextList) {
                        queue.offer(next);
                    }
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
    }
 /*
Initialize the queue with [1,[4,[6]]].

Start the while loop. The queue is not empty.

In the first iteration:
Dequeue [1,[4,[6]]].
Queue size is 2.
For each element in the queue:
Dequeue 1. It's an integer, so prevSum = 1.
Dequeue [4,[6]]. Add 4 and [6] to the queue.
total += prevSum, so total = 1.

In the second iteration:
Dequeue [4,[6]].
Queue size is 2.
For each element in the queue:
Dequeue 4. It's an integer, so prevSum = 1 + 4 = 5.
Dequeue [6]. Add 6 to the queue.
total += prevSum, so total = 1 + 5 = 6.

In the third iteration:
Dequeue [6].
Queue size is 1.
For each element in the queue:
Dequeue 6. It's an integer, so prevSum = 1 + 4 + 6 = 11.
total += prevSum, so total = 1 + 5 + 11 = 17.
End of the while loop.
Return total, which is 17.
*/
}

