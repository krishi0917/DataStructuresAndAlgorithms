// have doubts..
// have to iterate tomorrow again

package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * #GoogleInterviewQuestion
 * 1110. Delete Nodes And Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 */
public class DeleteNodesAndReturnForest {
    // Recursive Method
    Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted)
            res.add(node);
        node.left = helper(node.left, deleted);
        node.right =  helper(node.right, deleted);
        return deleted ? null : node;
    }

// Non Recursive method
public List<TreeNode> delNodesNonRecursive(TreeNode root, int[] to_delete) {
    if (root == null)
        return new ArrayList<>();

    Set<TreeNode> resSet = new HashSet<>();
    resSet.add(root);
    if (to_delete.length == 0)
        return new ArrayList<>(resSet);

    Set<Integer> toDelete = new HashSet<>();
    for (int val : to_delete)
        toDelete.add(val);

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        if (toDelete.contains(node.val)) {
            resSet.remove(node);
            if (node.left != null)
                resSet.add(node.left);
            if (node.right != null)
                resSet.add(node.right);
        }
        if (node.left != null) {
            q.offer(node.left);
            if (toDelete.contains(node.left.val))
                node.left = null;
        }
        if (node.right != null) {
            q.offer(node.right);
            if (toDelete.contains(node.right.val))
                node.right = null;
        }
    }
    return new ArrayList<>(resSet);
}

    public static void main(String []args){
               int [] to_delete = {3,5};
        TreeNode node = new TreeNode(1);
        node.left=new TreeNode(2);
        node.right=new TreeNode(3);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(5);
        node.right.left=new TreeNode(6);
        node.right.right=new TreeNode(7);
        DeleteNodesAndReturnForest deleteNodesAndReturnForest = new DeleteNodesAndReturnForest();
        System.out.println(deleteNodesAndReturnForest.delNodesNonRecursive(node,to_delete));
    }
}
