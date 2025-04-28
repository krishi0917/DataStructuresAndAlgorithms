package LeetcodePrograms.src;
import java.util.*;
// go for AllOOneDataStructure4
public class All0OneDataStructure3 {

// this is the best approach

//    for the proper visualization check the link
//    https://leetcode.com/problems/all-oone-data-structure/discuss/731468/HashMap-%2B-DoublyLinkedList-strategy

// Approach : Create a node in the below format and add more node to it when the frequency of the other element changes.
// Elements having the same frequency will be in the same node and all those strings will be added in the set of keys
    class Node {
        int freq;
        Node prev;
        Node next;
        Set<String> keys;

        public Node(int freq) {
            this.freq = freq;
            keys = new HashSet<>();
        }
    }

    private Node head;
    private Node tail;
    Map<String, Node> map;

    public All0OneDataStructure3() {
        head = null;
        tail = null;
        map = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {

        if (map.containsKey(key)) {
            Node node = map.get(key);
            int freq = node.freq;
            node.keys.remove(key);
            if (node.next == null) { // condition when there is only one node and its count is increased or if it is a tail node
                Node newNode = new Node(freq + 1);
                node.next = newNode;
                newNode.prev = node;
                newNode.keys.add(key);
                map.put(key, newNode);
                tail = newNode;
            } else {
                Node next = node.next;
                if (next.freq - freq > 1) { // when the element that we moved to has the same frequency or not. If not,
                    // we create a new node and add it in between else use the existing node to update the keys
                    Node newNode = new Node(freq + 1);
                    newNode.keys.add(key);
                    node.next = newNode;
                    newNode.prev = node;
                    newNode.next = next;
                    next.prev = newNode;
                    map.put(key, newNode);
                } else {
                    next.keys.add(key);
                    map.put(key, next);
                }
            }

            if (node.keys.size() == 0) {
                removeNode(node);
            }

        } else { // map does not contains the key
            if (head == null) {
                head = new Node(1);
                head.keys.add(key);
                tail = head;
            } else {
                if (head.freq == 1) {
                    head.keys.add(key);
                } else {
                    Node newNode = new Node(1);
                    newNode.keys.add(key);
                    newNode.next = head;
                    head.prev = newNode;
                    head = newNode;
                }
            }
            map.put(key, head);
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) {
            return;
        }
        Node node = map.get(key);
        node.keys.remove(key);
        int freq = node.freq;
        if (freq == 1) {
            map.remove(key);

        } else if (node == head) {
            Node newNode = new Node(freq - 1);
            newNode.keys.add(key);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            map.put(key, head);
        } else {
            Node prev = node.prev;
            if (freq - prev.freq == 1) {
                prev.keys.add(key);
                map.put(key, prev);
            } else {
                Node newNode = new Node(freq - 1);
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = node;
                node.prev = newNode;
                newNode.keys.add(key);
                map.put(key, newNode);
            }
        }

        if (node.keys.size() == 0) {
            removeNode(node);
        }
    }


    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head == null) {
            return "";
        }
        return tail.keys.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head == null) {
            return "";
        }
        return head.keys.iterator().next();
    }


    private void removeNode(Node node) {
        if (node == head) {
            head = head.next;
            node.next = null;
            if (head != null) {
                head.prev = null;
            }
        } else if (node == tail) {
            tail = tail.prev;
            node.prev = null;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }

    public static void main(String ...args){
        All0OneDataStructure3 ds = new All0OneDataStructure3();
        ds.inc("A");
        ds.inc("A");
        ds.inc("B");
        ds.inc("D");
        ds.inc("C");
        ds.inc("A");
        ds.inc("B");
        ds.inc("B");
        ds.inc("B");
        ds.inc("C");
        ds.inc("D");
        ds.inc("E");
        ds.dec("A");
        ds.dec("C");
        ds.dec("D");
        ds.dec("C");
        ds.dec("E");
    }

}
