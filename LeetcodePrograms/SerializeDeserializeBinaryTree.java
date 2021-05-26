package LeetcodePrograms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rkhurana on 3/5/19.
 */
public class SerializeDeserializeBinaryTree {
    private static final String splitter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
//        if(node.left ==null && node.right == null)
//            sb.append(AN).append(splitter);
        if (node == null) {
            sb.append(NN).append(splitter);
        } else {
            sb.append(node.val).append(splitter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        //Deque<String> nodes = new LinkedList<>();
        Queue<String> nodes1 = new LinkedList<>();
        //both the below statements will work

        nodes1.addAll(Arrays.asList(data.split(splitter)));
//		Collections.addAll(nodes1, data.split(splitter)); this works too
        return buildTree(nodes1);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();

        if (val.equals(NN))
            return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
    public static void main(String []args){
        SerializeDeserializeBinaryTree binaryTree = new SerializeDeserializeBinaryTree();
        TreeNode tree = new TreeNode(1);
//        tree.root = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.right = new TreeNode(2);
//        tree.right = new TreeNode(3);
//        tree.left.left = new TreeNode(4);
//        tree.left.right = new TreeNode(5);
        String x = binaryTree.serialize2(tree);
        System.out.println(x);
        TreeNode tree2 = binaryTree.deserialize2(x);
        System.out.println(tree2);
//            System.out.println(x);
    }

    // Encodes a tree to a single string. //inorder traversal
    public String serialize(TreeNode root) {
        if (root==null)
            return "";
        Queue<TreeNode> qu=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        qu.offer(root);
        sb.append(root.val);
        sb.append(' ');
        while (!qu.isEmpty()) {
            TreeNode x=qu.poll();
            if (x.left==null)
                sb.append("null ");
            else {
                qu.offer(x.left);
                sb.append(String.valueOf(x.left.val));
                sb.append(' ');
            }
            if (x.right==null)
                sb.append("null ");
            else {
                qu.offer(x.right);
                sb.append(String.valueOf(x.right.val));
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // in decoding there are 2 queues one for pushing the main element and then second is used for the left and right
    // of it and once the iteration of the outer queue is finished, we set the outerqueue as inner queue and then
    // another round of iteration goes

    public TreeNode deserialize(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        Queue<TreeNode> qu=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        qu.offer(root);
        int i=1;
        while (!qu.isEmpty()) {
            Queue<TreeNode> nextQu=new LinkedList<>();
            while (!qu.isEmpty()) {
                TreeNode x=qu.poll();
                if (node[i].equals("null")) x.left=null;
                else {
                    x.left=new TreeNode(Integer.valueOf(node[i]));
                    nextQu.offer(x.left);
                }
                i++;
                if (node[i].equals("null")) x.right=null;
                else {
                    x.right=new TreeNode(Integer.valueOf(node[i]));
                    nextQu.offer(x.right);
                }
                i++;
            }
            qu=nextQu;
        }
        return root;
    }
    // no need to use 2 queues in the above deserialization
    public TreeNode deserialize_bfs(String data) {
        if (data.length()==0) return null;
        String[] node=data.split(" ");
        Queue<TreeNode> qu=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(node[0]));
        qu.offer(root);
        int i=1;
        while (!qu.isEmpty()) {
            // Queue<TreeNode> nextQu=new LinkedList<>();
            while (!qu.isEmpty()) {
                int size = qu.size();
                for (int y = 0; y < size; ++y) {
                    TreeNode x=qu.poll();
                    if (node[i].equals("null")) x.left=null;
                    else {
                        x.left=new TreeNode(Integer.valueOf(node[i]));
                        qu.offer(x.left);
                    }
                    i++;
                    if (node[i].equals("null")) x.right=null;
                    else {
                        x.right=new TreeNode(Integer.valueOf(node[i]));
                        qu.offer(x.right);
                    }
                    i++;
                }
            }
        }
        return root;
    }
}
