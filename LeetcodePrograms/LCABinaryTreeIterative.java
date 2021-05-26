package LeetcodePrograms;

import java.util.*;


/**
 * @author Rishi Khurana
 */
public class LCABinaryTreeIterative {
    public class MyNode{
        TreeNode node;
        MyNode parent;
        boolean visited;
        List<TreeNode> result = new ArrayList<TreeNode>();

        public MyNode(TreeNode node, MyNode parent){
            this.node = node;
            this.parent = parent;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        MyNode dummy = new MyNode(null, null);//used to retrive global result
        MyNode rootNode = new MyNode(root, dummy);
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(rootNode);

        while(!stack.isEmpty()){
            MyNode curr = stack.peek();
            TreeNode node = curr.node;
            MyNode parent = curr.parent;
            //if we reach bottom or we found target
            if(node == null || node == p || node == q){
                parent.result.add(node);
                stack.pop();//we are done with this node, pop out
            }else if(!curr.visited){
                //have not visited curr node, push right first then left
                curr.visited = true;
                stack.push(new MyNode(node.right, curr));
                stack.push(new MyNode(node.left, curr));
            }else if(curr.visited){
                //if visited, update result
                TreeNode left = curr.result.get(0);
                TreeNode right = curr.result.get(1);
                if(left != null && right != null){
                    parent.result.add(node);//curr treeNode is LCA
                }else if(left != null){
                    parent.result.add(left);
                }else{
                    parent.result.add(right);
                }

                stack.pop();//we are done with this node, pop out
            }
        }

        return dummy.result.get(0);

    }

    public static void main(String []args){
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(5);
        tree.right = new TreeNode(1);
        tree.left.left = new TreeNode(6);
        tree.left.right = new TreeNode(2);
        tree.left.right.left = new TreeNode(7);
        tree.left.right.right = new TreeNode(4);
        tree.right.left = new TreeNode(0);
        tree.right.right = new TreeNode(8);
        LCABinaryTreeIterative lcaBinaryTreeIterative = new LCABinaryTreeIterative();
        TreeNode n = lcaBinaryTreeIterative.lowestCommonAncestoriterative(tree, tree.left.left ,  tree.right.right);
        System.out.println("value is " + n.val);
    }

    // LCA iterative solution if needed
    // amazing solution
    // you basically first make the Map with every node with its parent. Once it is made.
    // you pick the first treenode whose ancestor you have to find and start looking for its parent via this hashmap
    // and keeps on adding it in the hashset until you reach null
    // next, you take the other node and look for its parent in the hashmap and check if its available in the hashset
    // just created in the last step.
    // as soon as you find the element that is the common ancestor.

    public TreeNode lowestCommonAncestoriterative(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}
