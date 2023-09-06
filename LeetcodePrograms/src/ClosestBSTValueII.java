package LeetcodePrograms.src;
import java.util.*;
/**
 * 272. Closest Binary Search Tree Value II

 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are
 * closest to the target. You may return the answer in any order.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

 * Example 1:
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 */
public class ClosestBSTValueII {

    // ignore the first two solutions. start with 2 stack solution and then the optimized version

    // Approach 1
    // so the below method is to add all the elements in the dequeue first and then start polling the elements from the dequeue depending on the condition
    // not the best solution but something to start with
    //  it is O(n + h) time complexity and O(n) space complexity.
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
// Time complexity will be O(k) for the best case, when target is very close to smallest value of the BST. and will be O(n) for the worse case, when target is close to the largest value of the BST and when k starts to get closer to n (number of nodes int the BST)
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
            //if size k, add curent and remove head if it's optimal, otherwise return
            if (Math.abs(target - root.val) < Math.abs(target - res.peek())) {  // peek is first item
                res.remove();  // removeFirst()
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
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        //root.right.right = new TreeNode(6);
        List<Integer> list = closestBST.closestKValues3(root,3.714,2);
        System.out.println(list.get(0));
    }

// this is the better solution
// The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to
// track the predecessors and successors, then like what we do in merge sort, we compare and pick the closest one to
// the target and put it to the result list.
// As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.

//We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is the recursion version.

// Approach 2 stacks are created one with less than target 3,2,1 and second with greater 4,5,7
// then 3 and 7 are compared and the one closer to the target is popped and added to the results

// usually you need to push more than k nodes into the two stacks to find k closest nodes, which costs you more than O(k) time.
// So O(log(n) + k) is for best scenario
    public List<Integer> closestKValues3(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target))
            return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }

    // a very interesting way where you dont add all the elements like the previous example. you add a part of it and
    // processing it you check if you want to add more element in the stack or not
    public List<Integer> closestKValues4(TreeNode root, double target, int k) {
        Stack<TreeNode> smaller = new Stack<>();
        Stack<TreeNode> larger = new Stack<>();
        pushSmaller(root, target, smaller);
        pushLarger(root, target, larger);

        List<Integer> res = new ArrayList<>();
        TreeNode cur = null;
        while (res.size() < k) {
            if (smaller.isEmpty() || (!larger.isEmpty() && larger.peek().val - target < target - smaller.peek().val)) {
                cur = larger.pop();
                res.add(cur.val);
                pushLarger(cur.right, target, larger);
            } else {
                cur = smaller.pop();
                res.add(cur.val);
                pushSmaller(cur.left, target, smaller);
            }
        }
        return res;
    }

    private void pushSmaller(TreeNode node, double target,  Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val < target) {
                stack.push(node);
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }

    private void pushLarger(TreeNode node, double target, Stack<TreeNode> stack) {
        while (node != null) {
            if (node.val >= target) {
                stack.push(node);
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
}
