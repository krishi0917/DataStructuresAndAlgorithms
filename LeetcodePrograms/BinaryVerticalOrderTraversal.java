package LeetcodePrograms;


import java.util.*;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BinaryVerticalOrderTraversal {
    class Node {
        int hd;
        TreeNode root;
        public Node(int hd, TreeNode root) {
            this.hd = hd;
            this.root = root;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int minHd = Integer.MAX_VALUE;
        int maxHd = Integer.MIN_VALUE;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, root));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            TreeNode node = cur.root;
            int hd = cur.hd;
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.val);
            minHd = Math.min(minHd, hd);
            maxHd = Math.max(maxHd, hd);

            if (node.left != null) {
                q.offer(new Node(hd - 1, node.left));
            }

            if (node.right != null) {
                q.offer(new Node(hd + 1, node.right));
            }
        }

        for (int i = minHd; i <= maxHd; i++) {
            res.add(map.get(i));
        }

        return res;
    }
}
