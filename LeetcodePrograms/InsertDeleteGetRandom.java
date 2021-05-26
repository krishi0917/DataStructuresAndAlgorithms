package LeetcodePrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Rishi Khurana
 */
public class InsertDeleteGetRandom {
    // apart from insert remove delete , getLast in O(1)
    // Design a data structure that supports all following operations in average O(1) time.
    // insert(val): Inserts an item val to the set if not already present.
    // remove(val): Removes an item val from the set if present.
    // getRandom: Returns a random element from current set of elements.
    // getLast returns the last number
    // Each element must have the same probability of being returned.

    class Node{
        Node prev;
        Node next;
        int val;
        public Node(int val){
            this.val = val;
        }
    }

    ArrayList<Integer> list;
    HashMap<Integer, Integer> indexMap;
    HashMap<Integer, Node> nodeMap;
    Random rand = new Random();
    Node head;
    Node tail;
    public InsertDeleteGetRandom() {
        head = null;
        tail = null;
        list = new ArrayList<>();
        indexMap = new HashMap<>();// map for storing the number and its index
        nodeMap = new HashMap<>(); // map for storing the referring of the node for getlast operation to make it O(1);
    }

    // Inserts a value to the set. Returns true if the set did not already contain the specified element
    public boolean insert(int val) {
        boolean contain = indexMap.containsKey(val);
        if (contain)
            return false;
        indexMap.put(val, list.size());
        list.add(val);
        insertNodeinLinkedList(val);
        return true;
    }

    private void insertNodeinLinkedList(int val) {
        if(head == null && tail == null){
            Node n = new Node(val);
            head = n;
            tail = n;
            nodeMap.put(val,tail);
        }else{
            Node n = new Node(val);
            n.prev = tail;
            tail.next = n;
            tail = tail.next;
            nodeMap.put(val,tail);
        }
    }

    // Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        boolean contain = indexMap.containsKey(val);
        if (!contain)
            return false;
        int location = indexMap.get(val);
        if (location < list.size() - 1) { // if it is not the last one , then take the last element and
            // override it with the element that needs to be swapped and then after this if bracket is over , remove the last one
            int lastOne = list.get(list.size() - 1); // this is the actual element
            list.set(location, lastOne);
            indexMap.put(lastOne, location);
        }
        indexMap.remove(val);
        list.remove(list.size() - 1);
        removeNodefromLinkedList(val);
        return true;
    }

    private void removeNodefromLinkedList(int val) {
        Node node = nodeMap.get(val);
        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }else{
            tail = node.prev;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    public int getLast(){
        return tail.val;
    }
//    //below are the two methods of the above question
//    public boolean remove1(int val) {
//        int ind = indexMap.getOrDefault(val, -1);
//        if (ind == -1)
//            return false;
//        Collections.swap(list, ind, list.size() - 1);
//        int swappedWith = list.get(ind);
//        indexMap.put(swappedWith, ind);
//        list.remove(list.size() - 1);
//        indexMap.remove(val);
//        return true;
//    }
//
//    /**
//     * Get a random element from the set.
//     */
//    public int getRandom1() {
//        int max = list.size();
//        int min = 0;
//        int ind = (int) (Math.random() * (max - min) + min);
//        return list.get(ind);
//    }

    public static void main(String []args){
        InsertDeleteGetRandom insertDeleteGetRandom = new InsertDeleteGetRandom();
        insertDeleteGetRandom.insert(1);
        insertDeleteGetRandom.insert(2);
        insertDeleteGetRandom.insert(3);
        insertDeleteGetRandom.insert(4);
        insertDeleteGetRandom.insert(5);
        insertDeleteGetRandom.insert(6);
        insertDeleteGetRandom.insert(7);
        insertDeleteGetRandom.remove(1);
        System.out.println(insertDeleteGetRandom.getLast());
        insertDeleteGetRandom.remove(7);
        System.out.println(insertDeleteGetRandom.getLast());
        insertDeleteGetRandom.remove(4);
        System.out.println(insertDeleteGetRandom.getLast());
    }
}
