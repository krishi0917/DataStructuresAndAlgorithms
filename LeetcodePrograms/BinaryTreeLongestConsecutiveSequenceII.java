package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 549. Binary Tree Longest Consecutive Sequence II
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both
 * considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the
 * child-Parent-child order, where not necessarily be parent-child order.
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    public int[] helper(TreeNode root){
        if(root == null) return new int[]{0,0};
        int[] left = helper(root.left);
        int[] right= helper(root.right);
        int inc = 1, des = 1;
        if(root.left != null){
            if(root.val - root.left.val == 1){
                des = left[1]+1;
            }else if(root.val - root.left.val == -1){
                inc = left[0]+1;
            }
        }
        if(root.right != null){
            if(root.val - root.right.val == 1){
                des = Math.max(des,right[1]+1);
            }else if(root.val - root.right.val == -1){
                inc = Math.max(inc,right[0]+1);
            }
        }
        max = Math.max(max,inc+des-1);
        return new int[]{inc,des};
    }
}
