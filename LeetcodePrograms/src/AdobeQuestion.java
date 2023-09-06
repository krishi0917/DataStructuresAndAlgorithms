package LeetcodePrograms.src;

import java.util.LinkedList;
import java.util.Queue;
// clone a tree to a particular level
public class AdobeQuestion {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int data;
        public TreeNode(int d){
            data = d;
        }
    }
    public static TreeNode LevelTraversaltoaLevel(TreeNode treeNode, int level){
        if(treeNode == null){
            return null;
        }
        LevelTraversaltoaLevelUtil(treeNode,0, level);
        return treeNode;
    }

    public static void LevelTraversaltoaLevelUtil(TreeNode node , int currentLevel, int finalLevel){
        if(node == null )
            return;
        if(currentLevel >= finalLevel){
            node.left = null;
            node.right = null;
            return ;
        }
        LevelTraversaltoaLevelUtil(node.left , currentLevel + 1 , finalLevel);
        LevelTraversaltoaLevelUtil(node.right , currentLevel + 1 , finalLevel);
    }

    public static void printLevelOrder(TreeNode treeNode){
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();

        if (treeNode == null)
            return;

        // Pushing first level node into first queue
        q1.add(treeNode);

        // Executing loop till both the queues
        // become empty
        while (!q1.isEmpty() || !q2.isEmpty())
        {

            while (!q1.isEmpty())
            {

                // Pushing left child of current node in
                // first queue into second queue
                if (q1.peek().left != null)
                    q2.add(q1.peek().left);

                // pushing right child of current node
                // in first queue into second queue
                if (q1.peek().right != null)
                    q2.add(q1.peek().right);

                System.out.print(q1.peek().data + " ");
                q1.remove();
            }
            System.out.println();

            while (!q2.isEmpty())
            {

                // pushing left child of current node
                // in second queue into first queue
                if (q2.peek().left != null)
                    q1.add(q2.peek().left);

                // pushing right child of current
                // node in second queue into first queue
                if (q2.peek().right != null)
                    q1.add(q2.peek().right);

                System.out.print(q2.peek().data + " ");
                q2.remove();
            }
            System.out.println();
        }
    }
    public static void main(String []args){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.left.left = new TreeNode(8);
        treeNode.left.right = new TreeNode(4);
        treeNode.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);
        treeNode.right.left.left = new TreeNode(9);
        TreeNode result = LevelTraversaltoaLevel(treeNode , 2);
        printLevelOrder(result);
    }
}
