package LeetcodePrograms;
import java.util.*;
public class MaximalRectangle {
    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int temp[] = new int[matrix[0].length];
        int maxArea = 0;
        int area;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    temp[j] = 0;
                } else {
                    temp[j] += matrix[i][j]-'0';
                }
            }
            area = maxHistogram(temp);
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
    public static int maxHistogram(int input[]) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for (i = 0; i < input.length;) {
            if (stack.isEmpty() || input[stack.peekFirst()] <= input[i]) {
                stack.offerFirst(i++);
            } else {
                int top = stack.pollFirst();
// if stack is empty means everything till i has to be greater or equal to input[top] so get area by  input[top] * i;
                if (stack.isEmpty()) {
                    area = input[top] * i;
                }
// if stack is not empty then everythin from i-1 to input.peek() + 1 has to be greater or equal to input[top] so area = input[top]*(i - stack.peek() - 1);
                else {
                    area = input[top] * (i - stack.peekFirst() - 1); //point to remember
                }
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pollFirst();
// if stack is empty means everything till i has to be greater or equal to input[top] so get area by input[top] * i;
            if (stack.isEmpty()) {
                area = input[top] * i;
            }
// if stack is not empty then everything from i-1 to input.peek() + 1 has to be greater or equal to input[top]
// so area = input[top]*(i - stack.peek() - 1);
            else {
                area = input[top] * (i - stack.peekFirst() - 1);
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String []args){
        char[][]input ={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(input));
    }
}
