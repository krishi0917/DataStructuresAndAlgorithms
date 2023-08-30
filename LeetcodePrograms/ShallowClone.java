package LeetcodePrograms;
import java.util.*;
/*
1. Giving a binary tree, the level of its root is 1, the level of its root's children is 2, and so on.
Please clone a new tree with shallowest level such that the sum of all values of nodes in this level is maximal across all the levels of the tree.

Tree node value is integer and follows: -10^5 <= node.getValue() <= 10^5

E.g.
   4.    --------> 1
 //  \\
 2     10 ---------> 2
     // \\
    5    5 --------> 3

 */

public class ShallowClone {
    static class Node{
        int data;
         Node left;
         Node right;
        public Node(int d){
            data = d;
            left = null;
            right = null;
        }
    }
    public int maxLevel(Node root) {
        int max = Integer.MIN_VALUE, maxLevel = 1;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        for (int level = 1; !q.isEmpty(); ++level) {
            int sum = 0;
            for (int sz = q.size(); sz > 0; --sz) {
                Node n = q.poll();
                sum += n.data;
                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
            }
            if (max < sum) {
                max = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }

    public Node cloneTreeToLevel(Node root,int levelToTraverse){
        return dfs (root,1,levelToTraverse);
    }

    public Node dfs(Node root, int currentIndex , int finalLevel){
        if(root == null) return null;
        Node newNode = new Node(root.data);
        if(currentIndex < finalLevel){
            newNode.left = dfs(root.left,currentIndex+1,finalLevel);
            newNode.right = dfs(root.right,currentIndex+1,finalLevel);
        }
        return newNode;
    }

    public Node cloneTreeLevel(Node root){
        int levelToTraverse = maxLevel(root);
        Node head = cloneTreeToLevel(root,levelToTraverse);
        return head;
    }

    public static void main(String[] args) {
        ShallowClone cloneTree = new ShallowClone();
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(10);
        root.right.left = new Node(5);
        root.right.right = new Node(5);
        Node clonedHead = cloneTree.cloneTreeLevel(root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(clonedHead);
        while(queue!=null){
            Node n = queue.poll();
            System.out.print(n.data + "  ");
            if(n.left != null){
                queue.offer(n.left);
            }
            if(n.right != null){
                queue.offer(n.right);
            }
        }
    }
}
