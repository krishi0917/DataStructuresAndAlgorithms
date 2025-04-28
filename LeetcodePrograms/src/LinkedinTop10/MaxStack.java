package LeetcodePrograms.src.LinkedinTop10;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// this is a good approach where every number is stored in the Linkeded List with head and tail in it
// and in a treemap with key as number and value as the node. Treemap will be sorted so pop max can pop out in O(1)
// A better solution will be using TreeMap and Doubly Linked List. TreeMap acts like a priority queue, but it provides O(logN) write.
// Doubly Linked List provides O(1) push() and pop()


// bascially you keep doubly linked list to get access directly to that node and pop becomes because treemap is O(log n) for all the operations
// time compelxity is O(log n) for all the operations except O(1)

// the below solution is the java tree map and doubly linkedin list

public class MaxStack {
    Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map;

    public MaxStack() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        map = new TreeMap<>();
    }

    // new node is adding to the tail. therefor pop will also be from the tail
    public void push(int x) {
        Node newNode = new Node(x);
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre.next = newNode;
        tail.pre = newNode;
        if (!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(newNode);
    }

    public int pop() {
        int value = tail.pre.val;
        removeNode(tail.pre);
        int listSize = map.get(value).size();
        map.get(value).remove(listSize - 1);
        if (listSize == 1)
            map.remove(value);
        return value;
    }

    public int top() {
        return tail.pre.val;
    }

    public int peekMax() {
        return map.lastKey();
    } // o(logn)

    public int popMax() {
        int maxVal = map.lastKey();
        int listSize = map.get(maxVal).size();
        Node node = map.get(maxVal).remove(listSize - 1);
        removeNode(node);
        if (listSize == 1)
            map.remove(maxVal);
        return maxVal;
    }

    private void removeNode(Node n) {
        Node preNode = n.pre;
        Node nextNode = n.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    class Node {
        Node pre;
        Node next;
        int val;

        public Node(int x) {
            this.val = x;
            this.pre = null;
            this.next = null;
        }
    }

    /*
If the popMax() operation is not called frequently, one approach to reduce its complexity while maintaining efficiency
for other operations is to defer the complexity until it's necessary. Here are a few strategies you could consider:

Lazy Deletion Approach
Instead of removing the maximum element immediately in popMax(), you can mark it for deletion and only perform
the deletion when necessary (like during a subsequent pop() operation or when the maximum value changes).
Here's how you could modify your MaxStack implementation:

Track Marked Elements: Introduce a set (Set<Integer>) or another data structure to keep track of elements that
have been marked for deletion.

Deferred Deletion: When popMax() is called, instead of immediately removing the maximum value, mark it
(add it to the set of marked elements).

Remove Marked Elements on Demand: Modify your pop() or other operations to check if the top element needs
to be removed (if it's marked). If so, remove it at that point.

Here’s a simplified example:

class MaxStack {

    private static class ListNode {
        public ListNode prev, next;
        public int value;

        public ListNode(int val) {
            this.value = val;
        }
    }

    private final ListNode head;
    private final TreeMap<Integer, LinkedList<ListNode>> map = new TreeMap<>();
    private final Set<Integer> markedForDeletion = new HashSet<>();

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
            return 0; // no element exist
        }

        deleteNode(tail);

        // Check if the tail's value is marked for deletion
        if (markedForDeletion.contains(tail.value)) {
            map.get(tail.value).removeLast();
            if (map.get(tail.value).isEmpty()) {
                map.remove(tail.value);
            }
            markedForDeletion.remove(tail.value); // Remove from marked set
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
        ListNode node = map.get(max).getLast();

        // Mark the node for deletion
        markedForDeletion.add(max);

        return max;
    }

    private void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
Explanation:
Marked Set (markedForDeletion): This set keeps track of elements that are marked for deletion. When popMax() is called, it marks the maximum element for deletion by adding it to this set.

Lazy Deletion: The actual deletion of the maximum element occurs lazily, meaning it happens during a subsequent pop() operation. In pop(), if the top element is marked for deletion, it is removed from the stack and from the map.

Efficiency: This approach defers the complexity of removing the maximum element until necessary (during a pop() operation). If popMax() is not called frequently, this can reduce the overall complexity of your MaxStack operations.

 */
}