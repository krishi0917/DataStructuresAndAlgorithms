package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 3/26/19.
 */
public class LeetcodeUpdated {
//528. Random Pick with Weight. #Uber
// Given an array w of positive integers, where w[i] describes the weight of index i, write a function
// pickIndex which randomly picks an index in proportion to its weight.
// very well explained
// https://www.youtube.com/watch?v=KAZM4tsH8aI
//Use accumulated freq array to get idx.
//        w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
//        then get random val random.nextInt(14)+1, idx is in range [1,14]
//
//        idx in [1,2] return 0
//        idx in [3,7] return 1
//        idx in [8,10] return 2
//        idx in [11,14] return 3
    Random random;
    int[] wSums;

    public void RandomPickwithWeightsConstructor(int[] w) {
        this.random = new Random();
        for (int i = 1; i < w.length; ++i)
            w[i] += w[i - 1];
        this.wSums = w;
    }

    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len - 1]) + 1;
        int left = 0, right = len - 1;
        // search position
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (wSums[mid] == idx)
                return mid;
            else if (wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

// 721. Accounts Merge  #Facebook
// Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
// Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
// After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();  //<email node, neighbor nodes>
        Map<String, String> name = new HashMap<>();        //<email, username>
        // Build the graph;
        for (List<String> account : accounts) {
            String userName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                if (!graph.containsKey(account.get(i))) {
                    graph.put(account.get(i), new HashSet<>());
                }
                name.put(account.get(i), userName);

                if (i == 1) continue;
                graph.get(account.get(i)).add(account.get(i - 1));
                graph.get(account.get(i - 1)).add(account.get(i));
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS search the graph;
        for (String email : name.keySet()) {
            List<String> list = new LinkedList<>();
            if (visited.add(email)) {
                dfs(graph, email, visited, list);
                Collections.sort(list);
                list.add(0, name.get(email));
                res.add(list);
            }
        }

        return res;
    }

    public void dfs(Map<String, Set<String>> graph, String email, Set<String> visited, List<String> list) {
        list.add(email);
        for (String next : graph.get(email)) {
            if (visited.add(next)) {
                dfs(graph, next, visited, list);
            }
        }
    }

// 465  Optimal Account Balancing #Uber
// A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
// Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
// Note:
// A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
// Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6

//Solution: With all the given transactions, in the end, each person with ID = id will have an overall balance bal[id].
// Note that the id value or any person coincidentally with 0 balance is irrelevant to debt settling count, so we can
// simply use an array debt[] to store all non-zero balances, where
// debt[i] > 0 means a person needs to pay $ debt[i] to other person(s);
// debt[i] < 0 means a person needs to collect $ debt[i] back from other person(s).
// Starting from first debt debt[0], we look for all other debts debt[i] (i>0) which have opposite sign to debt[0].
// Then each such debt[i] can make one transaction debt[i] += debt[0] to clear the person with debt debt[0]. From now
// on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons one by one until everyone's
// debt is cleared meanwhile updating the minimum number of transactions during DFS.

    public int minTransfers(int[][] transactions) {
            Map<Integer, Integer> m = new HashMap<>(); // each person's overall balance
            for (int[] t : transactions) {
                m.put(t[0], m.getOrDefault(t[0], 0) - t[2]);
                m.put(t[1], m.getOrDefault(t[1], 0) + t[2]);
            }
            return settle(0, new ArrayList<>(m.values()));
        }

        int settle(int start, List<Integer> debt) {
            while (start < debt.size() && debt.get(start) == 0)
                start++; // get next non-zero debt
            if (start == debt.size()) return 0;
            int r = Integer.MAX_VALUE;
            for (int i = start + 1; i < debt.size(); i++)
                if (debt.get(start) * debt.get(i) < 0) {
                    debt.set(i, debt.get(i) + debt.get(start));
                    r = Math.min(r, 1 + settle(start + 1, debt));
                    debt.set(i, debt.get(i) - debt.get(start));
                }
            return r;
        }

// 621. Task Scheduler  #Facebook #Uber
// Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters
// represent different tasks. Tasks could be done without original order. Each task could be done in one interval.
// For each interval, CPU could finish one task or just be idle.
// However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n
// intervals that CPU are doing different tasks or just be idle.
// You need to return the least number of intervals the CPU will take to finish all the given tasks.

    public static int leastInterval(char[]tasks, int n) {
        //freq of tasks
        int[] freq = new int[26];

        for (char c : tasks) {
            freq[c - 'A']++;
        }

        //PQ maxheap
        PriorityQueue<Integer> pq = new PriorityQueue<>(26, Collections.reverseOrder());

        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }
        //resultant time interval
        int time = 0;

        //iterate through pq until it is empty
        while (!pq.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            //iterate upto cooling period n
            while (i <= n) {

                if (!pq.isEmpty()) {

                    if (pq.peek() > 1) {
                        //poll from pq
                        //add remaining freq instances to a temp ArrayList
                        temp.add(pq.poll() - 1);
                    } else {
                        pq.poll();
                    }

                }

                time++;

                //terminating condition for the loop
                if (pq.isEmpty() && temp.size() == 0) {
                    break;
                }

                i++;

            }

            //add remaining instances of tasks accounted for back to the pq
            for (int t : temp) {
                pq.offer(t);
            }
        }

        return time;
    }
    //161. One Edit Distance  #Facebook #Uber
//Given two strings s and t, determine if they are both one edit distance apart.
//        Note:
//        There are 3 possiblities to satisify one edit distance apart:
//        Insert a character into s to get t
//        Delete a character from s to get t
//        Replace a character of s to get t
//Solution
        /*
     * There're 3 possibilities to satisfy one edit distance apart:
     *
     * 1) Replace 1 char:
           s: a B c
           t: a D c
     * 2) Delete 1 char from s:
          s: a D  b c
          t: a    b c
     * 3) Delete 1 char from t
          s: a   b c
          t: a D b c
     */
        public boolean isOneEditDistance(String s, String t) {
            for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                        return s.substring(i).equals(t.substring(i + 1));
                    else // s is longer than t, so the only possibility is deleting one char from s
                        return t.substring(i).equals(s.substring(i + 1));
                }
            }
            //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
            return Math.abs(s.length() - t.length()) == 1;
        }

    public class DesignSnakeGame {
        int[][] food;
        int index;
        int row, col;
        int x, y;
        int len;
        LinkedList<int[]> queue;

        /**
         * Initialize your data structure here.
         *
         * @param width  - screen width
         * @param height - screen height
         * @param food   - A list of food positions
         *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
         */
        public DesignSnakeGame(int width, int height, int[][] food) {
            this.food = food;
            this.index = 0;
            this.x = 0;
            this.y = 0;
            this.row = height;
            this.col = width;
            this.queue = new LinkedList<int[]>();
            this.queue.offer(new int[]{0, 0});
        }

        /**
         * Moves the snake.
         *
         * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         * @return The game's score after the move. Return -1 if game over.
         * Game over when snake crosses the screen boundary or bites its body.
         */
        public int move(String direction) {
            switch (direction) {
                case "U":
                    x--;
                    break;
                case "L":
                    y--;
                    break;
                case "R":
                    y++;
                    break;
                case "D":
                    x++;
                    break;
            }

            if (!isValid(x, y)) {
                return -1;
            }

            return process(x, y);
        }

        public boolean isValid(int x, int y) {
            if (x < 0 || x >= row || y < 0 || y >= col)
                return false;

            return true;
        }

        public int process(int x, int y) {

            if (index == food.length) {
                queue.poll();

            } else if (food[index][0] == x && food[index][1] == y) {
                len++;
                index++;
            } else {
                queue.poll();
            }

            for (int[] p : queue) {
                if (p[0] == x && p[1] == y)
                    return -1;
            }

            queue.offer(new int[]{x, y});

            return len;
        }
    }
    // 359. Logger Rate Limiter
// Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only
// if it is not printed in the last 10 seconds. Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
    class LoggerRateLimiter {
        Map<String, Integer> map;

        /** Initialize your data structure here. */
        public LoggerRateLimiter() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            int threshold = map.getOrDefault(message, timestamp);
            if (threshold <= timestamp) { // contains two scenarios: message hasn't occurred & timeout
                map.put(message, timestamp + 10);
                return true;
            }
            return false;
        }
    }
}
