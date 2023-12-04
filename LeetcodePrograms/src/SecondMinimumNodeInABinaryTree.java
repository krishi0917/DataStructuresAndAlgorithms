package LeetcodePrograms.src;

// Q671 Second minimum Node in a binary tree  #Linkedin
// Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this
// tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the
// smaller value among its two sub-nodes. Given such a binary tree, you need to output the second minimum value in
// the set made of all the nodes' value in the whole tree. If no such second minimum value exists, output
// -1 instead.

// Solution :The special tree structure makes sure that the value of left and right is not lesser than root value,
// so by asking for second minimum, it is actually asking for the smallest value in left and right subtree.
// This value can't be equal to root value, so we need recursion if we find that left or right val is same as root val.
// It is possible that all values in left/right subtree is the same, and we mark the return value as -1 indicating no
// candidate exists in this subtree.

public class SecondMinimumNodeInABinaryTree {
    // Time Complexity worst O(n) where you have to iterate the whole tree and all having the same value
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }

        int left = root.left.val;
        int right = root.right.val;

        // if value same as root value, need to find the next candidate
        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
}
