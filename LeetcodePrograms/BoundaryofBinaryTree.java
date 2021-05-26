package LeetcodePrograms;

import java.util.*;

public class  BoundaryofBinaryTree {
    List<Integer> nodes = new ArrayList<>(1000);
    public  List<Integer> boundaryOfBinaryTree(TreeNode root) {

        if(root == null)
            return nodes;

        nodes.add(root.val);
        leftBoundary(root.left);
        leaves(root.left);
        leaves(root.right);
        rightBoundary(root.right);

        return nodes;
    }
    public void leftBoundary(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return;
        nodes.add(root.val);
        if(root.left == null)
            leftBoundary(root.right);
        else
            leftBoundary(root.left);

//  vivekanand khayade way of coding
//  https://www.youtube.com/watch?v=uemjIijtu2I&t=825s
        if(root!=null){
            if(root.left!=null){
                System.out.println(root.val);
                leftBoundary(root.left);
            }
            else if(root.right!=null){
                System.out.println(root.val);
                leftBoundary(root.right);
            }

        }


    }
    public void rightBoundary(TreeNode root) {
        if(root == null || (root.right == null && root.left == null))
            return;
        if(root.right == null)
            rightBoundary(root.left);
        else
            rightBoundary(root.right);
        nodes.add(root.val); // add after child visit(reverse)


        //  vivekanand khayade way of coding
        // over here root->right will be sent as an argument. Reason being root is being already covered
        // in the left boundary
        if(root!=null){
            if(root.right!=null){
                System.out.println(root.val);
                rightBoundary(root.right);
            }
            else if(root.left!=null){
                System.out.println(root.val);
                rightBoundary(root.left);

            }


        }

    }
    public void leaves(TreeNode root) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            nodes.add(root.val);
            return;
        }
        leaves(root.left);
        leaves(root.right);


    //vivekanand styke of codinh
        if(root!=null){
            leaves(root.left);
            if(root.left==null && root.right == null)
                System.out.println(root.val);
            leaves(root.root);

        }

    }

    public static void main(String []args){

        BoundaryofBinaryTree BTree = new BoundaryofBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        BTree.boundaryOfBinaryTree(root);
    }
}
