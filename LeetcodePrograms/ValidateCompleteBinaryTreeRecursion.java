package LeetcodePrograms;

/**
 * Created by rkhurana on 1/29/19.
 */
public class ValidateCompleteBinaryTreeRecursion {

    Node root;

    /* This function counts the number of nodes in a binary tree */
    int countNodes(Node root) {
        if (root == null)
            return (0);
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

    /* This function checks if the binary tree is complete or not */
    boolean isComplete(Node root, int index, int number_nodes) {
        // An empty tree is complete
        if (root == null)
            return true;

        // If index assigned to current node is more than
        // number of nodes in tree, then tree is not complete
        if (index >= number_nodes)
            return false;

        // Recur for left and right subtrees
        return (isComplete(root.left, 2 * index + 1, number_nodes)
                && isComplete(root.right, 2 * index + 2, number_nodes));

    }

    public static void main(String args[])
    {
        ValidateCompleteBinaryTreeRecursion tree = new ValidateCompleteBinaryTreeRecursion();

        // Le us create tree in the last diagram above
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.right = new Node(5);
        tree.root.left.left = new Node(4);
        tree.root.right.right = new Node(6);

        int node_count = tree.countNodes(tree.root);
        int index = 0;

        if (tree.isComplete(tree.root, index, node_count))
            System.out.print("The binary tree is complete");
        else
            System.out.print("The binary tree is not complete");
    }
}