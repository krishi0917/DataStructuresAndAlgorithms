package LeetcodePrograms;

/**
 * Created by rkhurana on 2/25/19.
 */
//#LinkedinQuestion #FacebookQuestion
public class FriendCircle {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length]; //visited[i] means if ith person is visited in this algorithm
        int count = 0;
        for(int i = 0; i < M.length; i++) {
            if(!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
    private void dfs(int[][] M, boolean[] visited, int person) {
        for(int other = 0; other < M.length; other++) {
            if(M[person][other] == 1 && !visited[other]) {
                //We found an unvisited person in the current friend cycle
                visited[other] = true;
                dfs(M, visited, other); //Start DFS on this new found person
            }
        }
    }

    public static void main(String []args) {
        FriendCircle fc =new FriendCircle();
        int[][] m = {  {1, 1, 0 , 0},
                {1, 1, 1, 0},
                {0, 0, 0,0},
                {0,0,0,1}
                        };

        System.out.println(fc.findCircleNum(m));
    }
}
