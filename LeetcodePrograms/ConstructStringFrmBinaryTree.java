package LeetcodePrograms;

public class ConstructStringFrmBinaryTree {
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        if (root.left != null) {
            sb.append("(");
            preorder(root.left, sb);
            sb.append(")");
        }
        if (root.right != null) {
            if (root.left == null) {
                sb.append("()");
            }
            sb.append("(");
            preorder(root.right, sb);
            sb.append(")");
        }
    }
    public static void main(String []args){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        ConstructStringFrmBinaryTree constructStringFrmBinaryTree = new ConstructStringFrmBinaryTree();
        System.out.println(constructStringFrmBinaryTree.tree2str(root));
    }
}
