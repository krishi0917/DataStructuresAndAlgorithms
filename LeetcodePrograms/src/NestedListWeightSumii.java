package LeetcodePrograms.src;

import java.util.*;
import java.util.List;

// 364. Nested List Weight Sum II
// You are given a nested list of integers nestedList. Each element is either an integer or a list
// whose elements may also be integers or other lists.

// The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1]
// has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer. The weight of an integer is maxDepth - (the depth of the integer) + 1.
// Return the sum of each integer in nestedList multiplied by its weight.
// Example 1:
// Input: nestedList = [[1,1],2,[1,1]]  Output: 8
// Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
// 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8

// Example 2:
// Input: nestedList = [1,[4,[6]]]
// Output: 17
// Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
// 1*3 + 4*2 + 6*1 = 17

public class NestedListWeightSumii {

    //nested list weight sum II (its the opposite of nested list weight sum. here weight is defined from bottom to top)  #Linkedin

    // Solution of how it is done
    // Instead of multiplying by depth, add integers multiple times (by going level by level and adding the unweighted sum to the
    // weighted sum after each level).

    // For future readers, this is a key fact I used when understanding this algorithm:
    //Each integer get added one extra time for the mere existence of each one level under it.
    //The concept of weight here is implemented with repeated addition;

/*  unweighted = Running sum of all numbers
    weighted = Running sum OF above sum

    e.g.                                   unweighted        weighted
    level 1 - integers = [2]     sum = 2     2                2
    level 2 - integers = [1,1,1,1]   sum = 4     2 + 4            2 +  2+4 = 8


    second ex
    level 1 integer 1 unweighted = 1 weighted = 1
    level 2 list [4.[6]] -> unweighted 1 + 4 = 5 weighted 5 + 1 = 6
    level 3 integer 6 unweighted 5 + 6 = 11 weighte 11 + 6 = 17

# time complexity
I think it's 0(N^2) where N is the max number of elements at any level. The loop will go N times followed by the insertion addAll
operation which will again take O(N) time. Tell me if I am wrong?

i think it's 0(N^2) as well. the code looks clean tho. But I think BFS is more efficient ? (maybe i was wrong.)

It is O(N^2), where N is the amount of nested integer objects. Worst case all elements are nested within each other;
if there are N elements, then the first 'level' (1 element) is re-added N times, the second N-1 times, third N-2 etc..
N+(N-1)+(N-2)+...+1 = N(N-1)/2 = O(N^2)
 */

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }

    public int depthSumInverseIterative(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.isEmpty()) return 0;
        Queue<List<NestedInteger>> q = new LinkedList();
        int singleSum = 0;
        int sum = 0;
        q.add(nestedList);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++){
                nestedList = q.poll();
                for (NestedInteger ni : nestedList) {
                    if (ni.isInteger())
                        singleSum += ni.getInteger();
                    else
                        q.add(ni.getList());
                }
            }
            sum += singleSum; //add to sum after processing the whole layer
        }
        return sum;
    }
}
