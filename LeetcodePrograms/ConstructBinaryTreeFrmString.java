package LeetcodePrograms;
// think of constructing string from a binary tree

//536. Construct Binary Tree from String facebook
// You need to construct a binary tree from a string consisting of parenthesis and integers.
// The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
// The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same
// structure. You always start to construct the left child node of the parent first if it exists.
//Input: s = "4(2(3)(1))(6(5))"
//        Output: [4,2,6,3,1,5]


public class ConstructBinaryTreeFrmString {
    public static TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int firstLeft = s.indexOf('(');
        if (firstLeft == -1) {
            return new TreeNode(Integer.parseInt(s));
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, firstLeft)));
        int count = 0, i = firstLeft;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            if (count == 0) {
                root.left = str2tree(s.substring(firstLeft + 1, i));
                break;
            }
            i++;
        }
        if (i != s.length() - 1) {
            root.right = str2tree(s.substring(i + 2, s.length() - 1));
        }
        return root;
    }

    public static void main(String []args){
        System.out.println(str2tree("4(2(3)(1))(6(5))"));
    }

}
