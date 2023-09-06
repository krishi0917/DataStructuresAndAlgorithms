package LeetcodePrograms.src;

import java.util.*;
/*
1660. Correct a Binary Tree

You have a binary tree with a small defect. There is exactly one invalid node where its right child incorrectly points to another node
at the same depth but to the invalid node's right.
Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this invalid node and
every node underneath it (minus the node it incorrectly points to).

Custom testing:
The test input is read as 3 lines:
TreeNode root
int fromNode (not available to correctBinaryTree)
int toNode (not available to correctBinaryTree)
After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.
 */

public class CorrectABinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode correctBinaryTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> set = new HashSet<>(); // per level, to store next level children

        queue.offer(root);
        int size = 1;

        while (size > 0) {
            TreeNode current = queue.poll();
            size--;

            if (current.right != null) {
                set.add(current.right);
                queue.offer(current.right);

                if (set.contains(current.right.right)) {
                    current.right = null;
                    break;
                }
            }

            if (current.left != null) {
                set.add(current.left);
                queue.offer(current.left);

                if (set.contains(current.left.right)) {
                    current.left = null;
                    break;
                }
            }

            if (size == 0) {
                size = queue.size();
                set = new HashSet<>();
            }
        }
        return root;
    }

    public static void main(String []args){
        CorrectABinaryTree c = new CorrectABinaryTree();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left  = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.right = treeNode.right;
        TreeNode resultNode = correctBinaryTree(treeNode);
        
    }
}
