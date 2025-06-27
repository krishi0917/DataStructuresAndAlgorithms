package LeetcodePrograms.src.LinkedinTop10;
import LeetcodePrograms.src.All0OneDataStructure3;

import java.util.*;
    /*
https://photos.google.com/share/AF1QipN8DtG8CnIp1cBaXFJ_nAelLrBlEHs5m4Cjfz0KA5O4d8kRbLNqUC8FeNjaycGqUg?pli=1&key=VTY2LThrVHlRV0ExN1NkWVZjclFTeWVGVkxvbWhn

https://leetcode.com/problems/all-oone-data-structure/solutions/731468/hashmap-doublylinkedlist-strategy/

Doubly Linked List (DLL):
Node: Stores a count and a set of keys with that count, plus pointers to previous and next nodes.
HashMap (keyMap): Maps each key to its corresponding node in the DLL.

Operations
Increment (inc(key)):
If key exists:

Remove the key from its current node.
Move the key to the next node if it has count +1, or create a new node with count +1.
Update keyMap to point the key to the new node.
Remove the current node if it's empty.
If key does not exist:

Add the key to the node with count 1 if it exists, or create a new node with count 1.
Update keyMap to point the key to this node.
Decrement (dec(key)):
If key exists:
Remove the key from its current node.
Move the key to the previous node if it has count -1, or create a new node with count -1.
Remove the key from keyMap if its count drops to zero.
Remove the current node if it's empty.

Get Maximum Key (getMaxKey):
Return any key from the node just before the tail, or an empty string if the list is empty.
Get Minimum Key (getMinKey):
Return any key from the node just after the head, or an empty string if the list is empty.
*/

class AllOOneDataStructure {
    class Node {
        int count; // this is the frequency
        Set<String> keys;
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }
    Map<String, Node> keyMap;
    Node head;
    Node tail;

    public AllOOneDataStructure() {
        this.keyMap = new HashMap<>();
        this.head = new Node(Integer.MIN_VALUE);
        this.tail = new Node(Integer.MAX_VALUE);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void inc(String key) {
        if(keyMap.containsKey(key)){
            Node node = keyMap.get(key);
            node.keys.remove(key);
            Node nextNode = node.next;

            if(nextNode != tail && nextNode.count == node.count +1){ // the very next node exists and add this new node there
                nextNode.keys.add(key);
            }else{
                Node newNode = new Node(node.count+1);
                newNode.keys.add(key);
                offerNode(newNode, node);
                nextNode = newNode;
            }
            keyMap.put(key, nextNode);
            if(node.keys.isEmpty()){
                removeNode(node);
            }
        } else{
// For a string that is not present in hashmap whether to create a new bucket
// this part is just to add a node for count 1 and if already a node present with frequency 1 use it, else create a new node
            if(head.next.count == 1){
                head.next.keys.add(key);
                keyMap.put(key, head.next);
            } else{
                Node newNode = new Node(1);
                newNode.keys.add(key);
                offerNode(newNode, head);
                keyMap.put(key, newNode);
            }
        }
    }

    public void dec(String key) {
        if(keyMap.containsKey(key)){
            Node node = keyMap.get(key);
            node.keys.remove(key);

            if(node.count > 1){
                Node prevNode = node.prev;
                if(prevNode.count == node.count-1){ // if a node is present with frequence 1 less than this 1, add it over there.
                    prevNode.keys.add(key);
                } else{
                    Node newNode = new Node(node.count-1); // otherwise create a new node
                    newNode.keys.add(key);
                    offerNode(newNode, prevNode);
                    prevNode = newNode;
                }
                keyMap.put(key, prevNode);
            } else{
                keyMap.remove(key);
            }
            if(node.keys.isEmpty()){
                removeNode(node);
            }
        }
    }

    public String getMaxKey() {
        if(tail.prev != head){
            return tail.prev.keys.iterator().next();
        }
        return "";
    }

    public String getMinKey() {
        if(head.next != tail){
            return head.next.keys.iterator().next();
        }
        return "";
    }

    private void offerNode(Node newNode, Node node){
        newNode.next = node.next;
        newNode.prev = node;
        node.next.prev = newNode;
        node.next = newNode;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String []args) {
        AllOOneDataStructure ds = new AllOOneDataStructure();
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

