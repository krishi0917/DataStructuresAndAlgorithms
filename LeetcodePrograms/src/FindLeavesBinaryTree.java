package LeetcodePrograms.src;

import java.util.ArrayList;
import java.util.List;

/*
366. Find Leaves of Binary Tree

Given the root of a binary tree, collect a tree's nodes as if you were doing this:
Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.

Example 1:
Input: root = [1,2,3,4,5]
Output: [[4,5,3],[2],[1]]
Explanation:
[[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
*/


public class FindLeavesBinaryTree {
//    approach For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
//    The essential of problem is not to find the leaves, but group leaves of same level together and also to cut the tree.
    public List<List<Integer>> findLeaves(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper(list, root);
        return list;
    }

    // return the level of root
    private int findLeavesHelper(List<List<Integer>> list, Node root) {
        if (root == null) {
            return -1;
        }
        int leftLevel = findLeavesHelper(list, root.left);
        int rightLevel = findLeavesHelper(list, root.right);
        int level = Math.max(leftLevel, rightLevel) + 1;
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
//        root.left = root.right = null; this line will be there when you want to remove/delete the nodes
        return level;
    }

    public static void main(String []args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        FindLeavesBinaryTree findLeavesBinaryTree = new FindLeavesBinaryTree();
        System.out.println(findLeavesBinaryTree.findLeaves(root));
    }
}
