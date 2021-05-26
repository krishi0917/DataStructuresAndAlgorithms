package LeetcodePrograms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/**
 * @author Rishi Khurana
 * 1172. Dinner Plate Stacks
 * You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks
 * has the same maximum capacity.
 *
 * Implement the DinnerPlates class:
 *
 * DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
 * void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
 * int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and
 * returns -1 if all stacks are empty.
 * int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that
 * stack, and returns -1 if the stack with that given index is empty.
 * Example:
 *
 * Input:
 * ["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop",
 * "pop","pop","pop","pop"]
 * [[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
 * Output:
 * [null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
 */
public class DinnerPlateStacks {
    List<Deque<Integer>> list = new ArrayList<>();
    int first = 0, last = 0;  //first idx to push, last idx to pop.
    //int count = 0;   //We can use count or avoid using count, though test cases shows that using count saves time.
    int capacity;
    public DinnerPlateStacks(int capacity) {
        this.capacity = capacity;
        list.add(new ArrayDeque<>());
    }

    public void push(int val) {
        while(first<=list.size()-1 && list.get(first).size()==capacity)first++;
        if(first==list.size())list.add(new ArrayDeque<>());
        list.get(first).push(val);
        last = Math.max(last, first);
        //count++;
    }

    public int pop() {
        // if(count==0)return -1;
        while(last>=0 && list.get(last).size()==0)last--;
        if(last<0)return -1;
        int val = list.get(last).pop();
        //count--;
        first = Math.min(first, last);
        return val;
    }

    public int popAtStack(int index) {
        if(index>=list.size())return -1;
        if(list.get(index).size()==0)return -1;
        int val = list.get(index).pop();
        first = Math.min(first, index);
        //count--;
        return val;
    }
}
