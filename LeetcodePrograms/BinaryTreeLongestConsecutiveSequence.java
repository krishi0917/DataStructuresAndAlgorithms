package LeetcodePrograms;

import java.util.*;

/**
 * @author Rishi Khurana
 * 298. Binary Tree Longest Consecutive Sequence
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
 * connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 */
public class BinaryTreeLongestConsecutiveSequence {
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0, root.val);
        return max;
    }

    public void helper(TreeNode root, int cur, int target){
        if(root == null) return;
        if(root.val == target) cur++;
        else cur = 1;
        max = Math.max(cur, max);
        helper(root.left, cur, root.val + 1);
        helper(root.right, cur, root.val + 1);
    }
    
    
    class Pair1{
        Pair1(TreeNode node , int size){
            this.node = node;
            this.size = size;
        }
        TreeNode node;

        TreeNode getNode() {
            return node;
        }

        void setNode(final TreeNode node) {
            this.node = node;
        }

        int getSize() {
            return size;
        }

        void setSize(final int size) {
            this.size = size;
        }

        int size;
    }

    public int longestConsecutive2(TreeNode root)
    {
        //edge case :
        if(root == null)
            return 0;

        //build queue
        //pair.first : current node, pair.second : the longest length until current node
        Deque<Pair1> que = new LinkedList<>();
        que.add(new Pair1(root,1));
        int longest = 1;
        while(!que.isEmpty()){
            int cursize = que.size();
            //query all neighbours in size of 'cursize'
            for(int i = 0; i < cursize; ++i){
                Pair1 pair1 = que.pollFirst();
                TreeNode cur = pair1.node;
                int curlen = pair1.size;
                
                //query left child
                if(cur.left != null){
                    if(cur.left.val - 1 == cur.val){
                        que.push(new Pair1(cur.left,curlen+1));
                        longest = Math.max(longest, curlen+1);
                    }
                    else
                        que.push(new Pair1(cur.left, 1));
                }
                //query right child
                if(cur.right != null){
                    if(cur.right.val - 1 == cur.val){
                        que.push(new Pair1(cur.right, curlen+1));
                        longest = Math.max(longest, curlen+1);
                    }
                    else
                        que.push(new Pair1(cur.right, 1));
                }

            }//end for
        }//end while

        return longest;
    }
    public static void main(String []args){
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(2);
        node.right.right = new TreeNode(4);
        node.right.right.right = new TreeNode(5);
        BinaryTreeLongestConsecutiveSequence bTree = new BinaryTreeLongestConsecutiveSequence();
        bTree.longestConsecutive2(node);
    }
}
