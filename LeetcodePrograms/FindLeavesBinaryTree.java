package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;


public class FindLeavesBinaryTree {
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
//        root.left = root.right = null;
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
