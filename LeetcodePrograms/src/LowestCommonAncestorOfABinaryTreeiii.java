package LeetcodePrograms.src;

/*
1650. Lowest Common Ancestor of a Binary Tree III
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the
lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 */

public class LowestCommonAncestorOfABinaryTreeiii {
/*
    Approach : Calculate the depth of each one and get down to the same depth from the higher depth one.
    now keep on going to the parent untill you meet
*/

    class Node{
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    public Node lowestCommonAncestor(Node p, Node q) {

        if (p == null || q == null)
            throw new IllegalArgumentException("Invalid input as p and q are guaranteed to exist");

        int pDepth = getDepth(p), qDepth = getDepth(q);

        while (pDepth > qDepth) {
            pDepth--;
            p = p.parent;
        }

        while (qDepth > pDepth) {
            qDepth--;
            q = q.parent;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private int getDepth(Node a) {
        int depth = 0;
        while (a != null) {
            a = a.parent;
            depth++;
        }
        return depth;
    }
}
