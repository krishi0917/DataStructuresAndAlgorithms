package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 10/27/18.
 */
public class BTMaximumPathSum {
    TreeNode root;
    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);

        return Math.max(left, right) + node.val;
    }

    public Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }

    public int maxPathSum2(TreeNode root) {
        int result = Integer.MIN_VALUE;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
        maxRootPath.put(null, 0); // for simplicity we want to handle null nodes
        for (TreeNode node : topSort(root)) {
            // as we process nodes in post-order their children are already cached
            if (node.left != null)
                System.out.println("node.left" + node.left.val);
            if (node.right != null)
                System.out.println("node.right" + node.right.val);
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.val);
            result = Math.max(left + right + node.val, result);
        }
        return result;
    }


    public static void main(String [] args){
        BTMaximumPathSum tree = new BTMaximumPathSum();
        tree.root = new TreeNode(-10);
        tree.root.left = new TreeNode(9);
        tree.root.right = new TreeNode(20);
//        tree.root.left.left = new TreeNode(20);
//        tree.root.left.right = new TreeNode(1);
//        tree.root.right.right = new TreeNode(-25);
//        tree.root.right.right.left = new TreeNode(3);
//        tree.root.right.right.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(7);
        System.out.println("maximum path sum is : " +
                tree.maxPathSum2(tree.root));
    }
}
