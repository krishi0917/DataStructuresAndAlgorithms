package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author Rishi Khurana
 */
public class BinaryTreePaths {
    public static List<String> binaryTreePathsNonRecursive(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> path_stack = new Stack<>();
        stack.push(root);
        path_stack.push(root.val + "");
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            String path = path_stack.pop();

            if (node.right == null && node.left == null) {
                ret.add(path);
            } else {
                if (node.left != null) {
                    stack.push(node.left);
                    path_stack.push(path + "->" + node.left.val);
                }
                if (node.right != null) {
                    stack.push(node.right);
                    path_stack.push(path + "->" + node.right.val);
                }
            }
        }
        return ret;
    }

    public static void main(String []args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        //root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        System.out.println(binaryTreePathsNonRecursive(root));
    }
}
