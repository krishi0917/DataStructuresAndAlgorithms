package LeetcodePrograms;
import java.util.*;

//I have checked a few other solutions. None of them are implemented with strict O(logN) time. One common solution is to use PriorityQueue,
// which is a bad use case. Because in pop(), when you try to do pq.remove(elem), this step will take O(n). I also see other solution trying
// to delay the remove, which does not remove the element immediately in popMax(), but later remove it in pop() when the "deleted" element is
// visited. This solution is only average O(logN), as the worst case will be O(n) when you keep pushing ascending numbers, then keep popMax()
// until only one element remain, then call pop(). The pop() will take O(n).
// A better solution will be using TreeMap and Doubly Linked List. TreeMap acts like a priority queue, but it
// provides O(logN) write. Doubly Linked List provides O(1) push() and pop()

//This soultion provides O(logN) time for push(), as the TreeMap need to rebalance when insert. And O(logN) for pop() and popMax(), as TreeMap need to rebalance when remove an element. (Note when there are a lot of duplucate elements pushed, the time for these two operations are actually O(1) average, since the remove() is not invoked every time.)
//top() and peekMax() are obviously O(1).
public class MaxStack {
    private static class ListNode {
        public ListNode prev, next;
        public int value;

        public ListNode(int val) {
            this.value = val;
        }
    }

    private final ListNode head;
    private final TreeMap<Integer, LinkedList<ListNode>> map = new TreeMap<>();

    /** initialize your data structure here. */
    public MaxStack() {
        head = new ListNode(0);
        head.next = head.prev = head;
    }

    public void push(int x) {
        ListNode node = new ListNode(x);
        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
        map.computeIfAbsent(x, k -> new LinkedList<>()).add(node);
    }

    public int pop() {
        ListNode tail = head.prev;
        if (tail == head) {
            return 0;   // no element exist
        }
        deleteNode(tail);
        // since it's pop(), we are always sure that the last element in the map's value list will be the tail
        map.get(tail.value).removeLast();
        if (map.get(tail.value).isEmpty()) {
            map.remove(tail.value);
        }
        return tail.value;
    }

    public int top() {
        return head.prev.value;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        ListNode node = map.get(max).removeLast();
        deleteNode(node);
        if (map.get(max).isEmpty()) {
            map.remove(max);
        }
        return max;
    }

    private void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
