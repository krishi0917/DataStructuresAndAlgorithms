package LeetcodePrograms;
import java.util.*;
// dint get it
public class SlidingPuzzle {

    public int slidingPuzzle(int[][] board) {
    String target = "123450";
    int M = 2, N = 3;
    StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
        for (int i: row)
            sb.append(i);
    }
    String start = sb.toString();
    Queue<String> queue = new LinkedList();
    Set<String> visited = new HashSet();
    int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.add(start);
        visited.add(start);
    int steps = 0;
        while (!queue.isEmpty()) {
        int size = queue.size();
        for (int k = 0; k < size; k++) {
            String cur = queue.poll();
            if (cur.equals(target))
                return steps;
            int i = cur.indexOf("0");
            int m = i/N, n = i % N;
            for (int[] move : moves) {
                int mt = m + move[0], nt = n + move[1];
                if (mt < 0 || mt >= M || nt < 0 || nt >= N)
                    continue;
                String nxt = swap(cur, i, mt*N + nt);
                if (visited.contains(nxt))
                    continue;
                queue.add(nxt);
                visited.add(nxt);
            }
        }
        steps++;
    }
        return -1;
}

    private String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return new String(arr);
    }

    public static void main(String []args){
        int [][]board = {{1,2,3},{4,0,5}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        System.out.println(slidingPuzzle.slidingPuzzle(board));
    }
}
