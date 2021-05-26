package LeetcodePrograms;

/**
 * Created by rkhurana on 7/12/18.
 */

public class RightSideView {
    Node root;
    int max_level =0 ;
    void rightViewUtil(Node node, int level, int max_level) {

        // Base Case
        if (node == null)
            return;

        // If this is the last Node of its level
        if (max_level < level) {
            System.out.print(node.data + " ");
            max_level = level;
        }
        // Recur for right subtree first, then left subtree
        rightViewUtil(node.right, level + 1, 0);
        rightViewUtil(node.left, level + 1, 0);
    }


    void rightView(Node node) {
        rightViewUtil(node, 1, Integer.MIN_VALUE);
    }
    void rightView(){
        rightView(root);
    }

    public static void main(String args[]) {
        RightSideView tree = new RightSideView();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.right.left.right = new Node(8);

        tree.rightView();

    }
}
