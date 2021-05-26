package LeetcodePrograms;

/**
 * Created by rkhurana on 2/24/19.
 *
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 */
public class ClosestBSTValue {
    public int closestValue(TreeNode root, double target) {

        double closest = Integer.MAX_VALUE;
        int value = 0;
        TreeNode current = root;
        while (current != null) {
            if (closest > Math.abs(current.val-target)) {
                closest = Math.abs(current.val-target);
                value = current.val;
            }

            if (current.val < target) {
                current = current.right;
            } else if (current.val > target) {
                current = current.left;
            } else {
                break;
            }
        }
        return value;
    }

}
