package LeetcodePrograms;

public class BinaryTreeUpsideDown {
    // recursive (O(depth of the tree) space) and iterative solutions (O(1) space)
    public TreeNode upsideDownBinaryTreeRecursive(TreeNode root) {
        //recursion is easy
        if(root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTreeRecursive(root.left);
        root.left.left = root.right;   // node 2 left children
        root.left.right = root;         // node 2 right children
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
    // you make 1,2,4 as a comb and 3,5 as teeth and then you eliminate the teeth(right child of parent node) and connect the left child to right and make the right node of the left child
    public static void main(String []args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        BinaryTreeUpsideDown u = new BinaryTreeUpsideDown();
        System.out.println(u.upsideDownBinaryTreeIterative(root));
    }

    // difficult to track iterative solution for this problem
    public TreeNode upsideDownBinaryTreeIterative(TreeNode root) {
        TreeNode curr = root;
        TreeNode next = null;
        TreeNode temp = null;
        TreeNode prev = null;

        while(curr != null) {
            next = curr.left;

            // swapping nodes now, need temp to keep the previous right child
            curr.left = temp;
            temp = curr.right;
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
