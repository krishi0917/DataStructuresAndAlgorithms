package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class HouseRobberIII {
    public int rob(Node root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(Node root) {
        if (root == null)
            return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

//        If we were able to maintain the information about the two scenarios for each tree root, let's see how
//        it plays out. Redefine rob(root) as a new function which will return an array of two elements, the first
//        element of which denotes the maximum amount of money that can be robbed if root is not robbed, while the
//        second element signifies the maximum amount of money robbed if it is robbed.
//
//        Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc. For the 1st element of rob(root), we only need to sum up the larger elements of rob(root.left) and rob(root.right), respectively, since root is not robbed and we are free to rob its left and right subtrees. For the 2nd element of rob(root), however, we only need to add up the 1st elements of rob(root.left) and rob(root.right), respectively, plus the value robbed from root itself, since in this case it's guaranteed that we cannot rob the nodes of root.left and root.right.
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // this is when root is not robbed.
        res[1] = root.val + left[0] + right[0]; // this is when root is robbed

        return res;
    }
    public static void main(String []args){
        HouseRobberIII hRobber = new HouseRobberIII();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);


        hRobber.rob(root);
    }
}
