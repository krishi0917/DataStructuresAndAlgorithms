package LeetcodePrograms;
import java.util.Stack;
//Implement an iterator over a binary search tree (BST). 
//Your iterator will be initialized with the root node of a BST.
//
//Calling next() will return the next smallest number in the BST.
//
//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, 
//where h is the height of the tree.
//public class BSTIterator {
//	Stack<TreeNode> stack;
// 
//	public BSTIterator(TreeNode root) {
//		stack = new Stack<TreeNode>();
//		while (root != null) {
//			stack.push(root);
//			root = root.left;
//		}
//	}
// 
//	public boolean hasNext() {
//		return !stack.isEmpty();
//	}
// 
//	public int next() {
//		TreeNode node = stack.pop();
//		int result = node.val;
//		if (node.right != null) {
//			node = node.right;
//			while (node != null) {
//				stack.push(node);
//				node = node.left;
//			}
//		}
//		return result;
//	}
//}

public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BSTIterator(TreeNode root) {

        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {

        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {

        for (; node != null; stack.push(node), node = node.left);
    }
}