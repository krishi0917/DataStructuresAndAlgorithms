package LeetcodePrograms;
import java.util.*;

/**
 * Created by rkhurana on 3/3/19.
 */
// 426. Convert Binary Search Tree to Sorted Doubly Linked List facebook
// Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the
// previous and next pointers in a doubly-linked list. We want to transform this BST into a circular doubly linked list. Each
// node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first
// element is the last element, and the successor of the last element is the first element.

public class ConvertBSTtoDoublyLinkedList {
    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node firstNode = null;
        Node prev = null;

        Stack<Node> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            //firstNode will be set when we visit the first leftmost leaf
            if (firstNode == null) {
                firstNode = root;
            }

            if (prev != null) {
                prev.right = root;
                root.left = prev;
            }

            prev = root;
            root = root.right;
        }

        //At the end of the while loop, prev holds the rightmost leaf in the tree.
        prev.right = firstNode;
        firstNode.left = prev;
        return firstNode;
    }
}
