package LeetcodePrograms.src;

public class DeleteNodeInABST {
/*Q. 450. Delete Node in a BST

    Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated)
    of the BST. Basically, the deletion can be divided into two stages:
    Search for a node to remove. If the node is found, delete the node.
*/
/*
# Approach : 3 possible conditiions
* Node is a leaf, and one could delete it straightforward : node = null.
* Node is not a leaf and has a right child. Then the node could be replaced by its successor which is somewhere lower in the right subtree.
    Then one could proceed down recursively to delete the successor.
* Node is not a leaf, has no right child and has a left child. That means that its successor is somewhere upper in the tree but we don't
     want to go back. Let's use the predecessor here which is somewhere lower in the left subtree. The node could be replaced by its predecessor and then one could proceed down recursively to delete the predecessor.

Return root.


// Complexity
   Time complexity : O(logN). During the algorithm execution we go down the tree all the time - on the left or on the right, first to search the node to delete O(H 1)
  time complexity as already discussed) and then to actually delete it. O(H1) is a tree height from the root to the node to delete. Delete process takes O(H 2)time, where O(H2) time where O(H2)
  is a tree height from the root to delete to the leafs. That in total results in O(H1 +H 2)=O(H) time complexity, where HH is a tree height, equal to logN in the case of the balanced tree.

Space complexity : O(H) to keep the recursion stack, where HH is a tree height. H =logN for the balanced tree.

    /*
  One step right and then always left
  */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    /*
    One step left and then always right
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val)
            root.right = deleteNode(root.right, key);

        // delete from the left subtree
        else if (key < root.val)
            root.left = deleteNode(root.left, key);

        // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null)
                root = null;

            // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
