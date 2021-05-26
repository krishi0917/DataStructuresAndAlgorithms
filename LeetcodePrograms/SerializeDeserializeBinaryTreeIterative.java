package LeetcodePrograms;
import java.util.*;

/**
 * Created by rkhurana on 3/5/19.
 */
public class SerializeDeserializeBinaryTreeIterative {
    public String serialize(TreeNode root) {
        if( root == null ) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while( !queue.isEmpty() ){

            TreeNode temp = queue.poll();
            if( temp == null ){
                sb.append(" null");
                continue;
            }
            else{
                sb.append(" ");
                sb.append(String.valueOf(temp.val));
            }

            queue.add(temp.left);
            queue.add(temp.right);
        }

        return sb.toString().trim();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data == null || data.length() <=0 ) return null;

        String[] strArray = data.split(" ");
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode result = new TreeNode(Integer.parseInt(strArray[0]));
        queue.add(result);
        count++;

        while( !queue.isEmpty() ){
            TreeNode temp = queue.poll();
            if( !strArray[count].equals("null") ){
                TreeNode left = new TreeNode(Integer.parseInt(strArray[count]));
                temp.left = left;
                queue.add(left);
            }
            count++;
            if( !strArray[count].equals("null") ){
                TreeNode right = new TreeNode(Integer.parseInt(strArray[count]));
                temp.right = right;
                queue.add(right);
            }
            count++;
        }
        return result;
    }
public static void main(String[]args){
    SerializeDeserializeBinaryTreeIterative binaryTree = new SerializeDeserializeBinaryTreeIterative();
    TreeNode tree = new TreeNode(1);
//        tree.root = new TreeNode(1);
    tree.left = new TreeNode(2);
    tree.right = new TreeNode(3);
    tree.left.left = new TreeNode(4);
    tree.left.right = new TreeNode(5);
    String x = binaryTree.serialize(tree);
        System.out.println(x);
    TreeNode tree2 = binaryTree.deserialize(x);
        System.out.println(tree2);
            System.out.println(x);
}

}
