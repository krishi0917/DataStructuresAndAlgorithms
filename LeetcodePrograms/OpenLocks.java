package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 752. Open the Lock
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5',
 * '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to
 * be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the
 * lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of
 * turns required to open the lock, or -1 if it is impossible.
 */
public class OpenLocks {
    public static int openLock(String[] deadends, String target) {

        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000");
        end.add(target);
        int level = 0;
        Set<String> temp;
        while(!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
               temp = begin;
               begin = end;
                end = temp;
             }
        temp = new HashSet<>();
        for(String s : begin) {
            if(end.contains(s))
                return level;
            if(deads.contains(s))
                continue;
            deads.add(s);
            StringBuilder sb = new StringBuilder(s);
            for(int i = 0; i < 4; i ++) {
                char c = sb.charAt(i);
                String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                if(!deads.contains(s1))
                    temp.add(s1);
                if(!deads.contains(s2))
                    temp.add(s2);
            }
        }
        level ++;
        begin = temp;
        }
        return -1;
    }

    //using queue
    public int openLock2(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                String s = q.poll();
                if(deads.contains(s)) {
                    size --;
                    continue;
                }
                if(s.equals(target))
                    return level;
                StringBuilder sb = new StringBuilder(s);
                for(int i = 0; i < 4; i ++) {
                    char c = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if(!visited.contains(s1) && !deads.contains(s1)) {
                        q.offer(s1);
                        visited.add(s1);
                    }
                    if(!visited.contains(s2) && !deads.contains(s2)) {
                        q.offer(s2);
                        visited.add(s2);
                    }
                }
                size --;
            }
            level ++;
        }
        return -1;
    }
    public static void main(String []args){
        String []deadends = {"0201","0101","0102","1212","2002"};String  target = "0202";
        System.out.println(openLock3(deadends,target));
    }
    public static int openLock3(String[] deadends, String target) {
        //bfs initial  is 0000
        //each time go from 0 to next 1 or 9
        //check if visited and locked combination, skip and continue
        //record levels
        Set<String> visited = new HashSet<>();
        Set<String> locked = new HashSet<>(Arrays.asList(deadends));

        visited.add("0000");
        Queue<String> qu = new LinkedList<>();
        qu.add("0000");
        int level = 0;
        while (!qu.isEmpty()) {
            int size = qu.size();
            // level++;
            for (int i = 0; i < size; i++) {
                String prev = qu.poll();
                if (locked.contains(prev))
                    continue;
                if (target.equals(prev))
                    return level;

                List<String> nt = getNext(prev);
                for (String next : nt) {
                    if (visited.contains(next) || locked.contains(next))
                        continue;
                    qu.offer(next);
                    visited.add(next);
                }
            }
            level++;
        }

        return -1;
    }

    private static List<String> getNext(String prev) {
        List<String> nt = new ArrayList<>();
        for (int i = 0; i < prev.length(); i++) {
            int wheel = prev.charAt(i) - '0';
            nt.add(prev.substring(0, i) + String.valueOf((10 + wheel + 1) % 10) +
                    prev.substring(i+1));
            nt.add(prev.substring(0, i) + String.valueOf((10 + wheel - 1) % 10) +
                    prev.substring(i+1));
        }
        return nt;
    }
}
