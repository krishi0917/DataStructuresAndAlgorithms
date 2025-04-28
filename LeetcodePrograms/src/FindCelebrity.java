package LeetcodePrograms.src;
import java.util.*;
public class FindCelebrity {
/*
277. Find the Celebrity
Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity.
The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.
Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is ask questions
like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one)
by asking as few questions as possible (in the asymptotic sense).
You are given a helper function bool knows(a, b) that tells you whether A knows B. Implement a function int findCelebrity(n).
There will be exactly one celebrity if they are at the party.

Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.

Example 1:
Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j,
otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because
both 0 and 2 know him but 1 does not know anybody.
 */

    // easy solution is stack solution

    public static boolean knows(int i , int j){
        return false;
    }

    public static int findCelebrity1(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i))
                    candidate = i;
            }
            for(int i = 0; i < n; i++){
                if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
            }
            return candidate;
    }
//    In detail, suppose the candidate after the first for loop is person (candidate) k, it means 0 to k-1 cannot be the celebrity,
//    because they know a previous or current candidate. Also, since k knows no one between k+1 and n-1, k+1 to n-1 can not be the
//    celebrity either. Therefore, k is the only possible celebrity, if there exists one.
//
//    The remaining job is to check if k indeed does not know any other persons and all other persons know k.
//    To this point, I actually realize that we can further shrink the calling of knows method. For example,
//    we don't need to check if k knows k+1 to n-1 in the second loop, because the first loop has already done that.
//
//        The optimized code is as follows:

        public static int findCelebrity2(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i))
                    candidate = i;
            }
            for(int i = 0; i < n; i++){
                if(i<candidate && knows(candidate, i) || !knows(i, candidate))
                    return -1;
                if(i>candidate && !knows(i, candidate)) return -1;
            }
            return candidate;
        }

// this approach is to push all the candidates in the stack and then compare by popping and then whatever is not a celebrity, dont push it back
// and do this till the stack has only 1 element
// once that is done, double check the potental celebrity
    public int findCelebrity(int n) {
        // base case
        if (n <= 0) return -1;
        if (n == 1) return 0;

        Stack<Integer> stack = new Stack<>();

        // put all people to the stack
        for (int i = 0; i < n; i++) stack.push(i);

        int a = 0, b = 0;

        while (stack.size() > 1) {
            a = stack.pop(); b = stack.pop();

            if (knows(a, b))
                // a knows b, so a is not the celebrity, but b may be
                stack.push(b);
            else
                // a doesn't know b, so b is not the celebrity, but a may be
                stack.push(a);
        }

        // double check the potential celebrity
        int c = stack.pop();

        for (int i = 0; i < n; i++)
            // c should not know anyone else
            if (i != c && (knows(c, i) || !knows(i, c)))
                return -1;

        return c;
    }

    public static void main(String []args){
       System.out.println(findCelebrity1(5));
    }
}
