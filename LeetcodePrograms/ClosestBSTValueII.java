package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 2/24/19.
 */
public class ClosestBSTValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
            Deque<Integer> dq = new LinkedList<>();

            inorder(root, dq);
            while (dq.size() > k) {
                if (Math.abs(dq.peekFirst() - target) > Math.abs(dq.peekLast() - target))
                    dq.pollFirst();
                else
                    dq.pollLast();
            }

            return new ArrayList<Integer>(dq);
        }

        public void inorder(TreeNode root, Deque<Integer> dq) {
            if (root == null) return;
            inorder(root.left, dq);
            dq.add(root.val);
            inorder(root.right, dq);
        }

//        -------------------------------------------------------------------

    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        inorder(root,target, k ,res);
        return res;
    }
    private void inorder(TreeNode root,double target , int k , LinkedList<Integer> res) {
        if (root == null)
            return;
        inorder(root.left, target, k, res);
        if (res.size() == k) {
            if (Math.abs(target - root.val) < Math.abs(target - res.peek())) {
                res.remove();
            } else {
                return;
            }
        }
        res.add(root.val);
        inorder(root.right, target, k, res);
    }
    public static void main(String []args){
        ClosestBSTValueII closestBST = new ClosestBSTValueII();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        //root.right.right = new TreeNode(6);
        List<Integer> list = closestBST.closestKValues2(root,3.714,2);
        System.out.println(list.get(0));
    }
}
