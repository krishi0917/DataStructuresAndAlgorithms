package LeetcodePrograms;

import java.util.Stack;
// 255. Verify Preorder Sequence in Binary Search Tree
// Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
// You may assume each number in the sequence is unique.
// Kinda simulate the traversal, keeping a stack of nodes (just their values) of which we're still in the left subtree.
// If the next number is smaller than the last stack value, then we're still in the left subtree of all stack nodes,
// so just push the new one onto the stack. But before that, pop all smaller ancestor values, as we must now be in their
// right subtrees (or even further, in the right subtree of an ancestor). Also, use the popped values as a lower bound,
// since being in their right subtree means we must never come across a smaller number anymore.
// TIme complexity of this problem in O(n) time.
public class VerifyPreorderinBST {


    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack();
        for (int p : preorder) {
            if (p < low)
                return false;
            while (!path.empty() && p > path.peek())
                low = path.pop();
            path.push(p);
        }
        return true;
    }

//    Second Approach
//    Recursively examine every key in the array. For each BST node, its key must be greater than all keys in left subtree
//  and less than keys in right subtree.
// Since given preorder sequence, the first element is always the root. Partition the array by the key of root, find the
// index of the first number greater than it.
//  Base case:
//    start index exceeds end index, the array to be checked is empty, return true;
//    root key is not within upper and lower boundaries, return false.
//    Solution:

    public boolean verifyPreorder2(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean verify(int[] preorder, int start, int end, int min, int max) {
        if (start > end) {
            return true;
        }
        int root = preorder[start];
        if (root > max || root < min) {
            return false;
        }

        int rightIndex = start;
        while (rightIndex <= end && preorder[rightIndex] <= root) {
            rightIndex++;
        }
        return verify(preorder, start + 1, rightIndex - 1, min, root) && verify(preorder, rightIndex, end, root, max);
    }
    public static void main(String []args){
        VerifyPreorderinBST verifyPreorderinBST = new VerifyPreorderinBST();
        int []preorder = {5,2,1,3 , 6};
        System.out.println(verifyPreorderinBST.verifyPreorder2(preorder));
    }
}
