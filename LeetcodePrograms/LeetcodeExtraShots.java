package LeetcodePrograms;

import java.util.*;

/**
 * @author Rishi Khurana
 */
public class LeetcodeExtraShots {
    //1007. Minimum Domino Rotations For Equal Row
    //    In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile
    //    with two numbers from 1 to 6 - one on each half of the tile.)
    //    We may rotate the i-th domino, so that A[i] and B[i] swap values.
    //    Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
    //    If it cannot be done, return -1.

    public int minDominoRotations(int[] A, int[] B) {
        if (A[0] == B[0])
            return Count(A, B, A[0]);
        else
            return Math.max(Count(A, B, A[0]), Count(A, B, B[0]));
        // reason for taking the above value from max is
        // to avoid a situation where one of the above conditions return -1. In that case, we want to return the
        // other value. Also, it is not possible to have two different values (apart from -1) for the above two
        // condition
        // there wouldnt be any case when Count(A, B, A[0])or Count(A, B, B[0]) would be any different.
        // So therefore max will work
    }

    public int Count(int[] A, int[] B, int target) {
        int countTop = 0;
        int countBot = 0;
        int n = A.length;

        for (int i = 0; i != n; ++i) {
            if (A[i] != target && B[i] != target)
                return -1;
            else if (A[i] != target)
                ++countTop;
            else if (B[i] != target)
                ++countBot;
        }

        return Math.min(countBot, countTop);
    }

    /**
     * @author Rishi Khurana
     * 1056. Confusing Number
     * Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
     *
     * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0,
     * 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is
     * a number that when rotated 180 degrees becomes a different number with each digit valid.
     */
    public boolean confusingNumber3(int N) {
    int[] rotate = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    int num = N;
    int rotated = 0;

    while (num > 0) {
    int d = num % 10;
    if (rotate[d] < 0)
    return false;
    rotated = rotated * 10 + rotate[d];
    num /= 10;
    }
    return rotated != N;
    }
//    or
    public boolean confusingNumber2(int N) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(6, 9);
    map.put(9, 6);
    map.put(0, 0);
    map.put(1, 1);
    map.put(8, 8);
    int newNum = 0;
    int x = N;
    while (x != 0) {
        int remainder = x % 10;
        if (!map.containsKey(remainder))
            return false;
        if(newNum > Integer.MAX_VALUE/10)
            return false;
        newNum = newNum * 10 + map.get(remainder);
        x /= 10;
    }
    return N == newNum? false: true;
}

    /**
     *
     *  1088. Confusing Number II
     * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0,
     * 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
     *
     * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
     * (Note that the rotated number can be greater than the original number.)
     *
     * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
     *
     */
// second solution is better
// basically the same idea. However, we generate numbers in the natural order, so we can pause when it reaches N.

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        public int confusingNumberII(int N) {
            map.put(0, 0);
            map.put(1, 1);
            map.put(6, 9);
            map.put(8, 8);
            map.put(9, 6);
            helper(N, 0);
            return res;
        }
        private void helper(int N, long cur) {
            if (isConfusingNumber(cur)) {
                res++;
            }
            for (Integer i : map.keySet()) {
                if (cur * 10 + i <= N && cur * 10 + i != 0) {
                    helper(N, cur * 10 + i);
                }
            }
        }
        private boolean isConfusingNumber(long n) {
            long src = n;
            long res = 0;
            while (n > 0) {
                res = res * 10 + map.get((int) n % 10);
                n /= 10;
            }
            return res != src;
        }

        // -------------------------

        int[] digits = new int[]{0, 1, 6, 8, 9};
        int[] rotate = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

        public int confusingNumberIIIterative(int N) {
            int res = 0;

            List<Integer> list = new ArrayList<>();
            list.add(0);

            boolean found = false;

            while (!found) {
                List<Integer> tmp = new ArrayList<>();
                for (Integer n : list) {

                    for (int i = 0; i < 5; i++) {
                        int nn = n * 10 + digits[i];

                        if (nn > N) {
                            found = true;
                            break;
                        }
                        if (nn != 0) {
                            tmp.add(nn);
                        }
                        if (isConfusing(nn)) {
                            res++;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                list = tmp;
            }

            return res;
        }

        private boolean isConfusing(int num) {
            int tmp = num;
            int rot = 0;
            while (tmp > 0) {
                int d = tmp % 10;
                rot = rot * 10 + rotate[d];
                tmp /= 10;
            }
            return rot != num;
        }

    /* 809. Expressive Words
     *
     * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In
     * these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll",
     * "ooo".
     *
     * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of
     * applications
     * of the following extension operation: choose a group consisting of characters c, and add some number of
     * characters
     * c to the group so that the size of the group is 3 or more.
     *
     * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
     * "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to
     * get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two
     * extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
     *
     * Given a list of query words, return the number of words that are stretchy.
     */

    // Solution : We have two pointers, use i to scan S, and use j to scan each word in words.
    //        c1 keeps the track of the count in String S of that particular character
    //        c2 keeps the track of the count in Words[] word of that particular character
    //        then check for all the condition and if something fails. return
    public class ExpressiveWords {
        public int expressiveWords(String S, String[] words) {
            if (S == null || words == null) {
                return -1;
            }
            int count = 0;
            for (String word : words) {
                if (isStretchy(S, word)) {
                    count++;
                }
            }
            return count;
        }

        private boolean isStretchy(String S, String word) {
            int i = 0;
            int j = 0;

            while (i < S.length() && j < word.length()) {
                char ch1 = S.charAt(i);
                char ch2 = word.charAt(j);
                if (ch1 != ch2) {
                    return false;
                }
                int c1 = 0;
                while (i < S.length() && S.charAt(i) == ch1) {
                    c1++;
                    i++;
                }
                int c2 = 0;
                while (j < word.length() && word.charAt(j) == ch2) {
                    c2++;
                    j++;
                }
                if (c2 > c1) {
                    return false;
                }
                int diff = c1 - c2;
                if (diff != 0 && diff + c2 < 3) {
                    return false;
                }

            }

            return i == S.length() && j == word.length();
        }
    }


//    1231. Divide Chocolate
//    You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
//    You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts,
//    each piece consists of some consecutive chunks.
//    Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
//    Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
//            Example 1:
//
//    Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
//    Output: 6
//    Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]

// second solution is better
public int maximizeSweetness(int[] sweetness, int K) {
    K=K+1; // Include yourself.
    int lo = getMin(sweetness);
    int hi = getSum(sweetness);
    while (lo < hi) {
        int mid = (lo + hi + 1) / 2; // +1 is basically to target the high if difference is 1
        if (split2(sweetness, mid) < K) {
            hi = mid - 1;
        } else {
            lo = mid; // we have already reached the target, we might have to have the right amount
        }
    }
    return lo;
}

    private int split2(int[] arr, int minSweetness) {
        int peopleCount = 0;
        int sweetness = 0;
        for (int val : arr) {
            sweetness += val;
            if (sweetness >= minSweetness) {
                peopleCount++;
                sweetness = 0;
            }
        }
        return peopleCount;
    }

    private int getMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int val : arr) {
            min = Math.min(min, val);
        }
        return min;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }
// or
    public int maximizeSweetness2(int[] sweetness, int K) {
        int low = getMin(sweetness);
        int high = getSum(sweetness);
        while(low < high) {
            int mid = low + (high - low + 1) / 2; // between lo and hi if difference is 1 return high
            int slice = 0;
            int sum = 0;
            for(int s : sweetness) {
                sum += s;
                if(sum >= mid) {
                    sum = 0;
                    slice++;
                    if(slice > K)
                        break;
                }
            }
            if(slice > K) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

//    951. Flip Equivalent Binary Trees
//    For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
//
//    A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip
//    operations. Write a function that determines whether two binary trees are flip equivalent.  The trees are given
//    by root nodes root1 and root2.

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode curr1 = queue.poll();
            TreeNode curr2 = queue.poll();

            if (curr1 == null && curr2 == null) {
                continue;
            }
            if (!isEquals(curr1, curr2)) {
                return false;
            }
            if (isEquals(curr1.left, curr2.left) && isEquals(curr1.right, curr2.right)) {
                queue.offer(curr1.left);
                queue.offer(curr2.left);
                queue.offer(curr1.right);
                queue.offer(curr2.right);
            } else if (isEquals(curr1.left, curr2.right) && isEquals(curr1.right, curr2.left)) {
                queue.offer(curr1.left);
                queue.offer(curr2.right);
                queue.offer(curr1.right);
                queue.offer(curr2.left);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null && root1.val == root2.val) {
            return true;
        } else {
            return false;
        }
    }

    // * 659. Split Array into Consecutive Subsequences
    // * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more
    // * subsequences such that each subsequence consists of consecutive integers and has length atleast 3
    //            * Input: [1,2,3,3,4,5]
    //            * Output: True
    // * Explanation:
    //            * You can split them into two consecutive subsequences :
    //            * 1, 2, 3
    //            * 3, 4, 5

    //first (below) way is better
    //    https://www.youtube.com/watch?v=uJ8BAQ8lASE
    // this method is better...  explanation in the abovex link
    // Solution
    // an incoming number will first try to see if it can be inserted into the existing the subsequence
    // before it tries to create one
    //hypothetical map will keep track of the element that can be added hypothetically in the existing map
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> existingSequences = new HashMap<>(); // hypothetical map
        Map<Integer, Integer> createNewSequence = new HashMap<>(); //  frequency map

        for (int i : nums) {
            createNewSequence.put(i, createNewSequence.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (createNewSequence.get(i) == 0)
                continue; // this takes care of all the elements who are already
            // decremented and reduced to zero in the last iteration.

            if (existingSequences.getOrDefault(i, 0) > 0) { // checks if the hypothetical map has this element.
                // if it does, it automatically removes the element from hypothetical map, assuming that element is
                // inserted to the existing map and a new space is been created in hypothetical map.
                existingSequences.put(i, existingSequences.get(i) - 1);
                existingSequences.put(i + 1, existingSequences.getOrDefault(i + 1, 0) + 1);
                createNewSequence.put(i, createNewSequence.get(i) - 1);
            }

            //creating a new map and checking if the next 3 values already exist in the frequency map..
            // if it does , it decrements those values and the 4th value is been added in the hypothetical map.
            else if (createNewSequence.getOrDefault(i, 0) > 0 && createNewSequence.getOrDefault(i + 1, 0) > 0
                    && createNewSequence.getOrDefault(i + 2, 0) > 0) {
                createNewSequence.put(i, createNewSequence.get(i) - 1);
                createNewSequence.put(i + 1, createNewSequence.get(i + 1) - 1);
                createNewSequence.put(i + 2, createNewSequence.get(i + 2) - 1);
                existingSequences.put(i + 3, existingSequences.getOrDefault(i + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    //or
    class Interval {
        int start;
        int end;
        int length;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            length = end - start + 1;
        }
    }

    public boolean isPossible2(int[] nums) {
        PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                if (a.end == b.end) {
                    return Integer.compare(a.length, b.length);
                }
                return Integer.compare(a.end, b.end);
            }
        });

        for (int num : nums) {
            while (!pq.isEmpty() && pq.peek().end + 1 < num) {
                if (pq.poll().length < 3)
                    return false;
            }
            if (pq.isEmpty() || pq.peek().end == num) { //first condition meaning the iteration just begun..
                // second condition is when a new interval is to be created because the incoming element is same
                // as that of the last element
                pq.add(new Interval(num, num));
            } else { // pq.peek().end + 1 == num
                pq.add(new Interval(pq.poll().start, num));
            }
        }

        while (!pq.isEmpty()) {
            if (pq.poll().length < 3)
                return false;
        }

        return true;
    }

    //    846. Hand of Straights
    //    Alice has a hand of cards, given as an array of integers.
    //
    //    Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
    //
    //            Return true if and only if she can.
    //
    //
    //
    //    Example 1:
    //
    //    Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
    //    Output: true
    //    Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].

    public boolean isNStraightHand(int[] hand, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < hand.length; i++)
            nums.add(hand[i]);
        Collections.sort(nums);
        Map<Integer, Integer> map = new TreeMap<>();
        if (nums.size() % k != 0)
            return false;

        int firstIndex = 0;
        for (int i = 0; i < nums.size(); i++)
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
        while (0 != nums.size()) {
            int secondIndex = 0;
            List<Integer> tempList = new ArrayList<>();
            int prevElement = -1;
            while (secondIndex < k) {
                if (nums.size() == firstIndex) // to avoid the null pointer exception we get when list is 1,1,2,2,3,3
                    return false;
                int element = nums.get(firstIndex);
                if ((secondIndex == 0)) {
                    tempList.add(element);
                    map.put(element, map.get(element) - 1);
                    if (map.get(element) == 0)
                        map.remove(element);
                    nums.remove(0);
                    secondIndex++;
                    prevElement = element;

                } else if ((element - prevElement) == 1) {
                    tempList.add(nums.get(firstIndex));
                    map.put(element, map.get(element) - 1);
                    if (map.get(element) == 0)
                        map.remove(element);
                    nums.remove(firstIndex);
                    secondIndex++;
                    prevElement = element;
                } else if ((element - prevElement) == 0) {
                    firstIndex++;
                    continue;
                } else
                    return false;
            }
            secondIndex = 0;
            firstIndex = 0;
        }
        return true;
    }

    //    946. Validate Stack Sequences
    //    Given two sequences pushed and popped with distinct values, return true if and only if this could have been the
    //   result of a sequence of push and pop operations on an initially empty stack.
    //    Example 1:
    //    Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
    //    Output: true
    //    Explanation: We might do the following sequence:
    //    push(1), push(2), push(3), push(4), pop() -> 4,
    //    push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.empty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.empty();
    }

    // only to have with no using stack solution
    public static boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed.length < 3) {
            return true;
        }
        int i = 0, j = 0;
        for (int newElement : pushed) {
            pushed[i++] = newElement;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return i == 0;
    }

    //  299. Bulls and Cows
    //  You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
    //  guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits
    //  in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits
    //  match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses
    //  and hints to eventually derive the secret number.
    //
    //  Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
    //  and B to indicate the cows.
    //  Please note that both secret number and friend's guess may contain duplicate digits.
    //  Example 1:
    //  Input: secret = "1807", guess = "7810"
    //  Output: "1A3B"
    //  Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.

    //first method is better
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int[] nums1 = new int[10];
        int[] nums2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {
                bulls++;
            } else {
                nums1[s]++;
                nums2[g]++;
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            cows += Math.min(nums1[i], nums2[i]);
            //  compare the count in for each digit, say secret has 4 'a' and guess has 2'a' , then your guess is
            //                partially right which is Math.min(4, 2)= 2
        }
        String res = bulls + "A" + cows + "B";
        return res;
    }

    // below method uses just one array
    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i))
                bulls++;
            else {
                if (numbers[secret.charAt(i) - '0']++ < 0)
                    cows++;
                if (numbers[guess.charAt(i) - '0']-- > 0)
                    cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }

    //    801. Minimum Swaps To Make Sequences Increasing
    //    We have two integer sequences A and B of the same non-zero length.
    //
    //    We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
    //    respective sequences.
    //    At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing
    //    if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
    //    Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed
    //    that the given input always makes it possible.

    //  Solution
    //    two arrays
    //            one no_swap array whose first value will be 0
    //            onr swap_array whose first value will be 1
    //    if (a[i] > a[i-1] && b[i] > b[i-1])
    //        set no swap array to the same as i-1
    //        set swap array to the value at (i-1) + 1
    //                reason being since we know in the last(i-1) we have made one swap and since we can see that the arrays are incresing,
    //        we have to swap it again so as to reverse the last swap and maintain the strictly increasing order
    //
    //    now compare the elements cross functionally
    //            and now if they have the same condition as above we are going to floor it. meaning the same thing but doing it cross functionally
    //
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int x[] = new int[n];// (n, 0); // number of swaps needed up to i, if no swap at i
        Arrays.fill(x, 0);
        int y[] = new int[n];
        ; // number of swaps needed up to i, if swap at i
        Arrays.fill(y, 1);

        for (int i = 1; i < n; ++i) {
            x[i] = y[i] = n;

            // elements are in order without a swap
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                x[i] = x[i - 1];
                y[i] = y[i - 1] + 1;
            }

            // elements are in order with a swap
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                x[i] = Math.min(x[i], y[i - 1]);
                y[i] = Math.min(y[i], x[i - 1] + 1);
            }
        }

        return Math.min(y[n - 1], x[n - 1]);
    }

    //   727. Minimum Window Subsequence
    //   Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
    //   If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
    //   such minimum-length windows, return the one with the left-most starting index.
    //   S = "abcdebdde", T = "bde"
    //            * Output: "bcde"
    //            */
    public static String minWindow(String S, String T) {
        if (S.length() == 0 || T.length() == 0) {
            return "";
        }

        /** Solution
         * we can conduct two steps by using two pointers for this probelm:
         * 1. check feasibility from left to right
         * 2. check optimization from right to left
         * we can traverse from left to right, find a possible candidate until reach the first ending character of T
         * eg: for the string s = abcdebdde and t = bde, we should traverse s string until we find first e,
         * i.e. abcde, then traverse back from current "e" to find if we have other combination of bde with smaller
         * length.
         * @param right: fast pointer that always points the last character of T in S
         * @param left: slow pointer that used to traverse back when right pointer find the last character of T in S
         * @param tIndex: third pointer used to scan string T
         * @param minLen: current minimum length of subsequence
         * */
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        while (right < S.length()) {
            int tIndex = 0;
            // use fast pointer to find the last character of T in S
            while (right < S.length()) {
                if (S.charAt(right) == T.charAt(tIndex)) {
                    tIndex++;
                }
                if (tIndex == T.length()) {
                    break;
                }
                right++;
            }

            // if right pointer is over than boundary
            if (right == S.length()) {
                break;
            }

            // use another slow pointer to traverse from right to left until find first character of T in S
            int left = right;
            tIndex = T.length() - 1;
            while (left >= 0) {
                if (S.charAt(left) == T.charAt(tIndex)) {
                    tIndex--;
                }
                if (tIndex < 0) {
                    break;
                }
                left--;
            }
            // if we found another subsequence with smaller length, update result
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                result = S.substring(left, right + 1);
            }
            // WARNING: we have to move right pointer to the next position of left pointer, NOT the next position
            // of right pointer
            right = left + 1;
        }
        return result;
    }

    /**
     * 1153. String Transforms Into Another String
     * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing
     * zero or more conversions.
     * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English
     * character.
     * Return true if and only if you can transform str1 into str2.
     * Input: str1 = "aabcc", str2 = "ccdee"
     * Output: true
     */

    public static boolean canConvert(String s1, String s2) {

        if (s1.equals(s2))
            return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
        //        return true;
        // the above line cannot be directly used because
        // when we say the hashset value should be less than 26 that means , there should be one temp variable to which
        // it should be transformable else if even a single circular dependency exists then the strings
        //   cannot be converted from one to the other using the global replaceAll
        // for exampple str1 = "ab" str2 = "ba"
        // now if in the first iteration b is changed to a in str 1
        // str 1 becomes aa..and if now a is changed to b, final string becomes bb
        // which we are not looking
        // so here first we are transforming b to c making str1 as ac
        // and then transformin a to b and c to a one by one
        // thus you need one extra character for temporary swapping and thatswhy
        // during the return size of hashset is been checked
    }

    //did not understand completely the below commented logic
    public static boolean isHomomorphicII(String s, String t) {
        if (s.equals(t))
            return true;
        int length = s.length();
        if (length != t.length())
            return false;

        int[] mapping = new int[26];
        boolean[] visited = new boolean[26];
        int uniq_count = 0;
        for (int i = 0; i < 26; i++) {
            mapping[i] = -1;
            visited[i] = false;
        }

        // build mapping
        for (int i = 0; i < s.length(); i++) {
            int sc = s.charAt(i) - 'a';
            int tc = t.charAt(i) - 'a';
            if (mapping[sc] == -1) {
                mapping[sc] = tc;
            } else if (mapping[sc] != tc) {
                // this character maps to more than one character,
                // so it is not homomorphic
                return false;
            }
            // track how many unique characters appear in 't'
            if (!visited[tc]) {
                visited[tc] = true;
                uniq_count++;
            }
        }

        // if we have at least one swap character available then we can always transform the strings
        if (uniq_count < 26)
            return true;

        // at this point we don't have any characters available for swap space
        // so if even a single circular dependency exists then the strings
        // cannot be converted from one to the other using the global replaceAll

        // dont know why the below commented part is getting used. Directly returning the false will also give the
        // right solution
        //        for (int i = 0; i < 26; i++) {
        //            visited[i] = false;
        //        }
        //        for (int i = 0; i < length; i++) {
        //            int sc = s.charAt(i) - 'a';
        //            if (!visited[sc]) {
        //                visited[sc] = true;
        //                // use fast and slow pointers
        //                int slow = sc, fast = sc;
        //                while (true) {
        //                    slow = mapping[slow];
        //                    if ((fast = mapping[fast]) == -1) break;
        //                    visited[fast] = true;
        //                    if ((fast = mapping[fast]) == -1) break;
        //                    visited[fast] = true;
        //                    // if fast == slow then a cycle was detected, and there aren't any swap characters available so fail
        //                    if (fast == slow)
        //                        return false;
        //                }
        //            }
        //        }
        //        // if we made it this far, they are homomorphic and s can be transformed to t
        //        return true;

        return false;
    }

    //430. Flatten a Multilevel Doubly Linked List
    //  You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
    //  which may or may not point to a separate doubly linked list. These child lists may have one or more children of their
    //  own, and so on, to produce a multilevel data structure, as shown in the example below.
    //
    //  Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
    //  Example 1:
    //  Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
    //        Output: [1,2,3,7,8,11,12,9,10,4,5,6]

    // Solution Start form the head , move one step each time to the next node
    // When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next,
    // by doing this we merged the child chain back to the main thread. Return to p and proceed until find next node with child.
    // Repeat until reach null
    public class FlattenMultilevelDoublyLinkedList {
        class Node {
            public int val;
            public Node prev;
            public Node next;
            public Node child;

            public Node() {
            }

            public Node(int _val, Node _prev, Node _next, Node _child) {
                val = _val;
                prev = _prev;
                next = _next;
                child = _child;
            }
        }

        public Node flatten(Node head) {
            if (head == null)
                return head;
            // Pointer
            Node p = head;
            while (p != null) {
                /* CASE 1: if no child, proceed */
                if (p.child == null) {
                    p = p.next;
                    continue;
                }
                /* CASE 2: got child, find the tail of the child and link it to p.next */
                Node temp = p.child;
                // Find the tail of the child
                while (temp.next != null)
                    temp = temp.next;
                // Connect tail with p.next, if it is not null
                temp.next = p.next;
                if (p.next != null)
                    p.next.prev = temp;
                // Connect p with p.child, and remove p.child
                p.next = p.child;
                p.child.prev = p;
                p.child = null;
            }
            return head;
        }
    }

    //  1055. Shortest Way to Form String
//  From any string, we can form a subsequence of that string by deleting some number of characters (possibly no
    //  deletions).
    //  Given two strings source and target, return the minimum number of subsequences of source such that their
    //  concatenation equals target. If the task is impossible, return -1.
    // Example 1:
    // Input: source = "abc", target = "abcbc"
    // Output: 2
    // Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

    //Accepted Solution
    public static int shortestWay(String source, String target) {
        int iSource = 0;
        int jTarget = 0;
        int count = 1;
        HashSet<Character> set = new HashSet<>();

        for (char ch : source.toCharArray())
            set.add(ch);  // just to make sure if any of the target element is not present in the source, there is no
        // way you can create the target from that source
        while (jTarget < target.length()) {
            if (!set.contains(target.charAt(jTarget))) {
                //element not present
                return -1;
            } else if (jTarget >= target.length())
                return count;
            else if (iSource >= source.length()) {
                //reinnitialise iSource to 0
                iSource = 0;
                count++;
            } else if (source.charAt(iSource) == target.charAt(jTarget)) {
                iSource++;
                jTarget++;
            } else {
                iSource++;
            }

        }
        return count;
    }

    /*
     * 939. Minimum Area Rectangle
     * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with
     * sides parallel to the x and y axes.
     *
     * If there isn't any rectangle, return 0.
     * Example 1:
     *
     * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
     * Output: 4
     * Example 2:
     *
     * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
     * Output: 2
     */

    // Solution
    // basic idea is to find the diagonals so that if we multiply the diagonals we can get the area
    // and the way we find the diagonal is by checking if the x cordinates of both the points or the y coordinate of both
    // the points doesnt coincide

    public static int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    // my concern is we are iterating all the points . run time complexoty should be atleast better
                    // than this
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // 465. Optimal Account Balancing
    // A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10.
    // Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave
    // person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions
    // can be represented as [[0, 1, 10], [2, 0, 5]].
    // Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
    // Note: A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
    // Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6

    //Solution:
    // We go through every transaction and update the values for each person.
    //
    // If a person has given money to some one else, he gets back that much money. So we add that money to his account.
    // If a person owes money, then we deduct the money from his account.
    // We will be left with three kinds of people
    //
    // People who will get money back (Positive)
    // People who owe money. (Negative)
    // People whoe neither get back or owe money. We will ignore these people as they are settled.
    // Now we need to match the positive amounts with negative amounts. Once all the positive and negatives cancel out, we check the transactions
    // required and we take the minimum.
    //
    // We do not know the order in which these balances are matched which will yeild minimum transactions. So we need to try out all the combinations.
    // We use backtracking/dfs.
    // We will discard the dfs where the transactions count is greater than the minimum. (Pruning)

    static int min = Integer.MAX_VALUE;

    public int minTransfers(int[][] transactions) {
        min = Integer.MAX_VALUE;
        HashMap<Integer, Integer> profitMap = new HashMap<>();
        for (int[] trans : transactions) {
            int a = trans[2];
            profitMap.put(trans[0], profitMap.getOrDefault(trans[0], 0) + a);
            profitMap.put(trans[1], profitMap.getOrDefault(trans[1], 0) - a);
        }
        LinkedList<Integer> positive = new LinkedList<>();
        LinkedList<Integer> negative = new LinkedList<>();
        for (Integer key : profitMap.keySet()) {
            Integer val = profitMap.get(key);
            if (val > 0) {
                positive.add(val);
            } else if (val < 0) {
                negative.add(val);
            }
        }
        dfs(positive, negative, 0);
        return min;
    }

    public void dfs(List<Integer> positive, List<Integer> negative, int count) {
        if (positive.size() == 0 && negative.size() == 0) {
            min = Math.min(count, min);
            return;
        }
        if (count >= min) {
            return;
        }
        int positiveVal = positive.get(0);

        // We start will different negative values and use
        for (int j = 0; j < negative.size(); j++) {
            int negativeVal = negative.get(j);
            // Deduct the balance. If the new values become zero then we remove those values from the list.
            int newPositiveVal = Math.max(positiveVal + negativeVal, 0);
            int newNegativeVal = Math.min(0, positiveVal + negativeVal);
            if (newPositiveVal == 0) {
                positive.remove(0);
            } else {
                positive.set(0, newPositiveVal);
            }
            if (newNegativeVal == 0) {
                negative.remove(j);
            } else {
                negative.set(j, newNegativeVal);
            }

            dfs(positive, negative, count + 1);

            // Backtrack, we need to add back the values.
            if (newPositiveVal == 0) {
                positive.add(0, positiveVal);
            } else {
                positive.set(0, positiveVal);
            }
            if (newNegativeVal == 0) {
                negative.add(j, negativeVal);
            } else {
                negative.set(j, negativeVal);
            }
        }
    }

    // 679. 24 Game
    // You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through
    // *, /, +, -, (, ) to get the value of 24.
    // this method only tells true or false. What if we need the comeplete expression ?
        public boolean judgePoint24(int[] nums) {
            List<Double> list = new ArrayList<>();
            for (int i : nums) {
                list.add((double) i);
            }
            return dfs(list);
        }

        private boolean dfs(List<Double> list) {
            if (list.size() == 1) {
                if (Math.abs(list.get(0)- 24.0) < 0.001) {
                    return true;
                }
                return false;
            }

            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    for (double c : generatePossibleResults(list.get(i), list.get(j))) {
                        List<Double> nextRound = new ArrayList<>();
                        nextRound.add(c);
                        for(int k = 0; k < list.size(); k++) {
                            if(k == j || k == i)
                                continue;
                            nextRound.add(list.get(k));
                        }
                        if(dfs(nextRound))
                            return true;
                    }
                }
            }
            return false;

        }
        private List<Double> generatePossibleResults(double a, double b) {
            List<Double> res = new ArrayList<>();
            res.add(a + b);
            res.add(a - b);
            res.add(b - a);
            res.add(a * b);
            res.add(a / b);
            res.add(b / a);
            return res;
        }

    /**
     * 221. Maximal Square
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     */
    public static int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    // minimum of all the three (i,j-1),(i-1,j-1),(i-1,j) + 1
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    /**
     * 1146. Snapshot Array
     *
     * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element
     * equals 0.
     * void set(index, val) sets the element at the given index to be equal to val.
     * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
     * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
     *
     * Time O(logS)
     * Space O(S)
     * where S is the number of set called.
     *
     * SnapshotArray(int length) is O(N) time
     * set(int index, int val) is O(1) in Python and O(logSnap) in Java
     * snap() is O(1)
     * get(int index, int snap_id) is O(logSnap)
     *
     */
     TreeMap<Integer, Integer>[] A; // treemap array with key as snap id and value as value
     int snap_id = 0;
     public void SnapshotArrayConstruct(int length) {
         A = new TreeMap[length];
         for (int i = 0; i < length; i++) {
             A[i] = new TreeMap<Integer, Integer>(); // treemap of snapId and value
             A[i].put(0, 0);
         }
     }

     public void set(int index, int val) {
         A[index].put(snap_id, val);
     }

     public int snap() {
         return snap_id++;
     }

     public int get(int index, int snap_id) {
         return A[index].floorEntry(snap_id).getValue();
     }

    //  1057. Campus Bikes
    // On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
    // coordinate on this grid.
    //
    //  Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike)
    //  pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are
    //  multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest
    //  worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat
    //  this process until there are no available workers.
    //
    //   The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
    //
    //   Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is
    //  assigned to.

    // Solution : use a priority queue and calculate all the distance of workers with the bikes and put it in a
    // priority queue and then iterate over the queue and check if the value is not yet used .if used ignore it
    class node {
        int dist;
        int worker;
        int bike;

        public node(int dist, int worker, int bike) {
            this.dist = dist;
            this.worker = worker;
            this.bike = bike;
        }
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        boolean[] used = new boolean[bikes.length];
        int cnt = 0;
        PriorityQueue<node> heap = new PriorityQueue<node>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                if (o1.dist == o2.dist) {
                    if (o1.worker == o2.worker) {
                        return o1.bike - o2.bike;
                    }
                    return o1.worker - o2.worker;
                } else {
                    return o1.dist - o2.dist;
                }
            }
        });

        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = calDist(workers[i], bikes[j]);
                node n = new node(dist, i, j);
                heap.offer(n);
            }
        }
        while (cnt != workers.length) {
            node cur = heap.poll();
            int index = cur.worker;
            int bike = cur.bike;
            if (ans[index] == -1 && !used[bike]) {
                ans[index] = bike;
                used[bike] = true;
                cnt++;
            }
        }
        return ans;

    }

    public int calDist(int[] worker, int[] bikes) {
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }

    /**
     * 723. Candy Crush
     * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent
     * different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The
     * given board represents the state of the game following the player's move. Now, you need to restore the board to a
     * stable state by crushing candies according to the following rules:
     * If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same
     * time - these positions become empty.
     * After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then
     * these
     * candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top
     * boundary.)
     * After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above
     * steps.
     * If there does not exist more candies that can be crushed (ie. the board is stable), then return the current
     * board.
     * You need to perform the above rules until the board becomes stable, then return the current board.
     * Example:
     * Input:
     * board =
     * [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],
     * [710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
     * Output:
     * [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,
     * 211,
     * 112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
     */
    public static int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean shouldContinue = false;
        // this flag will tell us if we want to continue for the next iteration
        // if it is false, we will display the result. If not, we will call this method again in the return statement

        // we make the number negative if we want to delete it
        // Crush horizontally
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 2; j++) {
                int v = Math.abs(board[i][j]);
                if (v > 0 && v == Math.abs(board[i][j + 1]) && v == Math.abs(board[i][j + 2])) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
                    shouldContinue = true;
                }
            }
        }

        // Crush vertically
        for (int i = 0; i < m - 2; i++) {
            for (int j = 0; j < n; j++) {
                int v = Math.abs(board[i][j]);
                if (v > 0 && v == Math.abs(board[i + 1][j]) && v == Math.abs(board[i + 2][j])) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
                    shouldContinue = true;
                }
            }
        }

        //current status
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }

        // below part is a little tricky .. when we have some negtive numbers. these negative numbers to be replaced
        // with the numbers above them
        // Drop vertically
        for (int j = 0; j < n; j++) {
            int r = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] >= 0) {
                    board[r--][j] = board[i][j];
                }
            }
            for (int i = r; i >= 0; i--) {
                board[i][j] = 0;
            }
        }

        return shouldContinue ? candyCrush(board) : board;
    }

    /**
     * 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
     * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less
     * than
     * or equal to threshold or return 0 if there is no such square.
     * Example 1:
     * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
     * Output: 2
     * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
     * https://www.youtube.com/watch?v=t4J-Sp3BWh4
     */

    // Solution : not much of an explanation is required.
    // you calculate the prefix sum for every element and then check if the prefix sum is less than or equal to
    // threshold. If yes add it to result
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];

        int res = 0;
        int len = 1; // square side length

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];

                if (i >= len && j >= len
                        && sum[i][j] - sum[i - len][j] - sum[i][j - len] + sum[i - len][j - len] <= threshold)
                    res = len++;
            }
        }

        return res;
    }

    /**
     * 1048. Longest String Chain
     * Given a list of words, each word consists of English lowercase letters.
     * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make
     * it equal to word2.  For example, "abc" is a predecessor of "abac".
     * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of
     * word_2, word_2 is a predecessor of word_3, and so on.
     * Return the longest possible length of a word chain with words chosen from the given list of words.
     * Example 1:
     * Input: ["a","b","ba","bca","bda","bdca"]
     * Output: 4
     * Explanation: one of the longest word chain is "a","ba","bda","bdca"
     */

    public static int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }

    /**
     * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only
     * 0s and 1s as values.)
     * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it
     * on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in
     * both images.
     * (Note also that a translation does not include any kind of rotation.)
     * What is the largest possible overlap?
     * Example 1:
     * Input: A = [[1,1,0],
     * [0,1,0],
     * [0,1,0]]
     * B = [[0,0,0],
     * [0,1,1],
     * [0,0,1]]
     * Output: 3
     * Explanation: We slide A to right by 1 unit and down by 1 unit.
     */
    public static int largestOverlap(int[][] A, int[][] B) {
        int rows = A.length, cols = A[0].length;
        List<int[]> la = new ArrayList<>(),
                lb = new ArrayList<>(); // two lists to save pixel coordinates
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1)
                    la.add(new int[] { r, c }); // save the pixel coordinates
                if (B[r][c] == 1)
                    lb.add(new int[] { r, c });
            }
        Map<String, Integer> map = new HashMap<>(); // map to map the vector (from a pixel in A to a pixel in B) to
        // its count
        for (int[] pa : la)
            for (int[] pb : lb) {
                String s =
                        (pa[0] - pb[0]) + " " + (pa[1] - pb[1]); // get the vector from a pixel in A to a pixel in B
                map.put(s, map.getOrDefault(s, 0) + 1); // count the number of same vectors
            }
        int max = 0;
        for (int count : map.values())
            max = Math.max(max, count);
        return max;
    }

    // https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
    // #Salesforce  #OnsiteInterview
    // 1296. Divide Array in Sets of K Consecutive Numbers
    // Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into
    // sets of k consecutive numbers
    //        Return True if its possible otherwise return False.
    //        Example 1:
    //
    //        Input: nums = [1,2,3,3,4,4,5,6], k = 4
    //        Output: true
    //        Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

    // Solution
    // firstIndex = element fetched from the arraylist .. elements can be deleted
    // secondIndex = location in which element is to be added in the templist

    public static boolean findPossibleDivide(List<Integer> nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>(); // could be hashmap
        if (nums.size() % k != 0)
            return false;

        int firstIndex = 0;
        for (int i = 0; i < nums.size(); i++)
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
        while (0 != nums.size()) {
            int secondIndex = 0;
            List<Integer> tempList = new ArrayList<>();
            int prevElement = -1;
            while (secondIndex < k) {
                int element = nums.get(firstIndex);
                if ((secondIndex == 0)) {
                    tempList.add(element);
                    map.put(element, map.get(element) - 1);
                    if (map.get(element) == 0)
                        map.remove(element);
                    nums.remove(0);
                    secondIndex++;
                    prevElement = element;

                } else if (
                        (element - prevElement) == 1) {
                    tempList.add(nums.get(firstIndex));
                    map.put(element, map.get(element) - 1);
                    if (map.get(element) == 0)
                        map.remove(element);
                    nums.remove(firstIndex);
                    secondIndex++;
                    prevElement = element;
                } else if ((element - prevElement) == 0) {
                    firstIndex++;
                    continue;
                } else
                    return false;
            }
            secondIndex = 0;
            firstIndex = 0;
        }
        return true;
    }

    /**
     * https://www.youtube.com/watch?v=pfQoqxP-6DE
     * 399. Evaluate Division
     * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
     * number (floating point number). Given some queries, return the answers. If the answer does not exist, return
     * -1.0.
     * Example:
     * Given a / b = 2.0, b / c = 3.0.
     * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
     * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     * According to the example above:
     * equations = [ ["a", "b"], ["b", "c"] ],
     * values = [2.0, 3.0],
     * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     */
    public class EvaluateDivision {
        class Node {
            String val;
            int state;
            Map<Node, Double> neighbours;

            public Node(String val) {
                this.val = val;
                this.state = 0;
                this.neighbours = new HashMap<>();
            }
        }

        class Graph {
            Map<String, Node> nodeMap;

            public Graph() {
                this.nodeMap = new HashMap<>();
            }

            public void buildGraph(String[][] equations, double[] values) {
                for (int i = 0; i < equations.length; i++) {
                    String[] equation = equations[i];
                    double value = values[i];
                    String first = equation[0];
                    String second = equation[1];
                    if (!nodeMap.containsKey(first)) {
                        nodeMap.put(first, new Node(first));
                    }
                    if (!nodeMap.containsKey(second)) {
                        nodeMap.put(second, new Node(second));
                    }
                    nodeMap.get(first).neighbours.put(nodeMap.get(second), value);
                    nodeMap.get(second).neighbours.put(nodeMap.get(first), 1.0 / value);
                }
            }

            public void resetState() {
                for (String key : nodeMap.keySet()) {
                    nodeMap.get(key).state = 0;
                }
            }
        }

        public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
            Graph graph = new Graph();
            graph.buildGraph(equations, values);
            double[] ans = new double[queries.length];
            int i = 0;
            for (String[] query : queries) {
                Node start = graph.nodeMap.get(query[0]);
                Node end = graph.nodeMap.get(query[1]);
                ans[i] = dfs(start, end, 1.0);
                i++;
                graph.resetState();
            }
            return ans;
        }

        public double dfs(Node start, Node end, double product) {
            if (start == null || end == null) {
                return -1;
            }
            if (start.val.equals(end.val)) {
                return product;
            }
            start.state = 1;
            for (Node neighbor : start.neighbours.keySet()) {
                if (neighbor.state == 0) {
                    double cand = dfs(neighbor, end, product * start.neighbours.get(neighbor));
                    if (cand != -1) {
                        return cand;
                    }
                }
            }
            start.state = 2;
            return -1;
        }

        public void main(String[] args) {
            String[][] equations = { { "a", "b" }, { "b", "c" } };
            double[] values = { 2.0, 3.0 };
            String[][] queries = { { "a", "c" }, { "b", "a" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
            //            EvaluateDivision eDivide = new EvaluateDivision();
            calcEquation(equations, values, queries);
        }
    }

    /**
     * 743. Network Delay Time
     * There are N network nodes, labelled 1 to N.
     * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the
     * target node, and w is the time it takes for a signal to travel from source to target.
     * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is
     * impossible, return -1.
     * Example 1:
     * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
     * Output: 2
     */
    // Solution build a graph as the first step using times int[][]. second step is to use a priority queue and insert
    // the elements in the priority queue based on the distance. pop the elements from the queue and then continue adding
    // the adjacent elements. third step is to have a hashmap "dist" which will be used for storing the information the
    // way we are popping from heap. we can use this for displaying the result.
    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[1] - info2[1]);
        heap.offer(new int[] { K, 0 });

        //Key is node, val is dist from K
        Map<Integer, Integer> dist = new HashMap();  //this is used to calculate the distance from the main node
        // for the final answer.

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[1], node = info[0];
            if (dist.containsKey(node))
                continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge : graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[] { nei, d + d2 });
                }
        }

        if (dist.size() != N)
            return -1;
        int ans = 0;
        for (int cand : dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
    /**
     * 271. Encode and Decode Strings
     * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and
     * is decoded back to the original list of strings.
     *
     * Machine 1 (sender) has the function:
     *
     * string encode(vector<string> strs) {
     *   // ... your code
     *   return encoded_string;
     * }
     * Machine 2 (receiver) has the function:
     * vector<string> decode(string s) {
     *   //... your code
     *   return strs;
     * }
     * So Machine 1 does:
     *
     * string encoded_string = encode(strs);
     * and Machine 2 does:
     *
     * vector<string> strs2 = decode(encoded_string);
     * strs2 in Machine 2 should be the same as strs in Machine 1.
     *
     * Implement the encode and decode methods.
     */
        public static String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for(String s : strs) {
                sb.append(s.length()).append('/').append(s);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public static List<String> decode(String s) {
            List<String> ret = new ArrayList<String>();
            int i = 0;
            while(i < s.length()) {
                int slash = s.indexOf('/', i);
                int size = Integer.valueOf(s.substring(i, slash));
                i = slash + size + 1;
                ret.add(s.substring(slash + 1, i));
            }
            return ret;
        }

    /**
     * Next Closest Time
     * https://www.youtube.com/watch?v=tGpJegBlgOw
     * https://www.youtube.com/watch?v=99Gw7Hezii8
     * 681. Next Closest Time
     * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is
     * no limit on how many times a digit can be reused.
     *
     * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9"
     * are all invalid.
     *
     * Example 1:
     *
     * Input: "19:34"
     * Output: "19:39"
     * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is
     * not 19:33, because this occurs 23 hours and 59 minutes later.
     * Example 2:
     *
     * Input: "23:59"
     * Output: "22:22"
     * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned
     * time is next day's time since it is smaller than the input time numerically.
     */

    // Solution convert the time to minutes and add 1 minute to it.
    // second step is to put the Input variable that we got, put it in the hashset and check for the next valid time
    public String nextClosestTime(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60; // changing hour to minutes
        minutes += Integer.parseInt(time.substring(3));

        HashSet<Integer> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            digits.add(c - '0');
        }
        while (true) {
            minutes = (minutes + 1) % (24 * 60); // so as to make sure the conversion goes right. for example if time is
            // 23:59 it should be changed to 00:00

            //now parsing the minutes to hour:minutes
            int nextTime[] = { minutes / 60 / 10 /* to get the first number*/, minutes / 60 % 10, /*second number*/
                    minutes % 60 / 10, minutes % 60 % 10 };
            boolean isValid = true;
            for (int digit : nextTime) {
                if (!digits.contains(digit)) {
                    isValid = false;
                }
            }
            if (isValid) {
                return String.format("%02d:%02d", minutes / 60, minutes % 60);
            }
        }
    }

    // below is the way where wer are using dfs .. we start with 1111 and then keep on changing the characters based
    // on hashset and then comparing if this number is closest from the actual number than the previous number we
    // calculated. If yes, will make this number as minDifference number.
    private static String result = "";
    static int minDiff = Integer.MAX_VALUE;
    public static String nextClosestTime2(String time) {
        int originalMins = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3,5));
        HashSet<Character> digits = new HashSet<>();
        for (char c : time.toCharArray()) {
            if(c != ':')
                digits.add(c);
        }
        List<Character> list = new ArrayList<Character>(digits);
        dfs(list,"",originalMins);
        return result;
    }

    private static void dfs(final List<Character> list,  String temp,  int originalMins) {
        if(temp.length() == 4){
            String hourString = temp.substring(0,2);
            String minString = temp.substring(2);
            int hour = Integer.parseInt(hourString);
            int min = Integer.parseInt(minString);
            if(hour > 23 || min > 59) return ;
            int totalMins = hour *60 + min ;
            totalMins += (totalMins <= originalMins ? 24*60 :0);
            //if totalMins < originalMins , we are going to add one day
            if(minDiff > (totalMins - originalMins)){
                minDiff = totalMins - originalMins;
                result = hourString + ":" + minString;
            }
            return ;
        }
        for(int i = 0 ; i < list.size() ; i++){
            temp+=list.get(i);
            dfs(list,temp,originalMins);
            temp = temp.substring(0,temp.length()-1);
        }
        return ;
    }

    /**
     * https://www.youtube.com/watch?v=1OrzRFwc9mw&t=4s
     * 562. Longest Line of Consecutive One in Matrix
     * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal,
     * vertical, diagonal or anti-diagonal.
     * Example:
     * Input:
     * [[0,1,1,0],
     *  [0,1,1,0],
     *  [0,0,0,1]]
     * Output: 3
     * Hint: The number of elements in the given matrix will not exceed 10,000.
     */

    public static int longestLine(int[][] M) {
        if (M == null || M.length == 0)
            return 0;
        int rows = M.length, cols = M[0].length;
        int[][][] dp = new int[rows + 1][cols + 2][4]; // 4 because of 4 directions
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (M[i][j] == 1) {
                    dp[i + 1][j + 1][0] = dp[i + 1][j][0] + 1; // horizontal
                    res = Math.max(res, dp[i + 1][j + 1][0]);
                    dp[i + 1][j + 1][1] = dp[i][j + 1][1] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][1]);

                    dp[i + 1][j + 1][2] = dp[i][j][2] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][2]);

                    dp[i + 1][j + 1][3] = dp[i][j + 2][3] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][3]);
                }
            }
        }
        return res;
    }

    /**
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

    /**
          729. My Calendar I
     * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a
     * double booking.
     *
     * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open
     * interval [start, end), the range of real numbers x such that start <= x < end.
     *
     * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common
     * to both events.)
     *
     * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
     * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
     *
     * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
     * Example 1:
     *
     * MyCalendar();
     * MyCalendar.book(10, 20); // returns true
     * MyCalendar.book(15, 25); // returns false
     * MyCalendar.book(20, 30); // returns true
     * Explanation:
     * The first event can be booked.  The second can't because time 15 is already booked by another event.
     * The third event can be booked, as the first event takes every time less than 20, but not including 20.\
    */
        TreeMap<Integer, Integer> calendar;

        public void MyCalendarIConstructor() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer floorKey = calendar.floorKey(start);
            if (floorKey != null && calendar.get(floorKey) > start)
                return false;
            Integer ceilingKey = calendar.ceilingKey(start);
            if (ceilingKey != null && ceilingKey < end)
                return false;

            calendar.put(start, end);
            return true;
        }
        TreeSet<int[]> books = new TreeSet<int[]>((int[] a, int[] b) -> a[0] - b[0]);

        public boolean book2(int s, int e) {
            int[] book = new int[] { s, e }, floor = books.floor(book), ceiling = books.ceiling(book);
            if (floor != null && s < floor[1])
                return false; // (s, e) start within floor
            if (ceiling != null && ceiling[0] < e)
                return false; // ceiling start within (s, e)
            books.add(book);
            return true;
        }

    // 560. Subarray Sum Equals K
    // Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
    // Use a hash map to store preSum and its frequency, if map contains key of current preSum - k, then the original
    // array must contain a subarray sum equals k */
        public static int subarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int result = 0;
            int preSum = 0;

            HashMap<Integer, Integer> map = new HashMap<>();

    // corner case: if the very first subarray that start from index 0 has preSum equals k, we will have to check whether we have key (preSum - k = 0),
    // so must put(0, 1) as pre-processing, since this case is considered a valid case, we must add count value 1 to result
    // There is a special case like in the Solution2 when nums[x] == k that is current sum itself is equal to target without any subtractions. For this solution,
    // we can either increment count by 1 whenever sum == k below or make an entry as a special case in our map
    // preSumFreq.put(0, 1) to cover those cases.
            map.put(0, 1);

            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                if (map.containsKey(preSum - k)) {

                    // count of subarray that have prefix sum equals preSum - k, add count to result
                    result += map.get(preSum - k);
                }
                map.put(preSum, map.getOrDefault(preSum, 0) + 1);
            }
            return result;
        }

    /**
     * 336. Palindrome Pairs
     *
     * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the
     * concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     *
     * Example 1:
     *
     * Input: ["abcd","dcba","lls","s","sssll"]
     * Output: [[0,1],[1,0],[3,2],[2,4]]
     * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
     */
    /*
         * Step 1: store every word with its index into a hash map.
         * Step 2: For each word in the array, split into two parts str1 and str2. Check whether str1 and str2 is palindrome
         * If str1 is palindrome, we can use str1 as middle part, str2 as right part, and find if map contains reversed str2.
         * If contains, then we can use that string as left part, combine with middle part, right part, it will form a correct
         * palindrome string.
         * Step 3: do all same operations for str2 (set str2 as middle part)
         * */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words.length == 0) {
            return result;
        }

        // step1
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);

                // step 2
                if (isPalindrome(str1)) {
                    String reversedStr2 = new StringBuilder(str2).reverse().toString();

                    /* WARNING: MUST CHECK whether str.length() is equal to 0 in either if statement, because we need to make sure
                    *  we do not add duplicate pairs (if str1.length() == 0, both of str1 and str2 will from input array) */
                    if (map.containsKey(reversedStr2) && map.get(reversedStr2) != i && str1.length() != 0) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(map.get(reversedStr2));
                        newPair.add(i);
                        result.add(newPair);
                    }
                }

                // step 3
                if (isPalindrome(str2)) {
                    String reversedStr1 = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(reversedStr1) && map.get(reversedStr1) != i) {
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(i); // we are only adding the indices through which we are getting our palindrome
                        // formed. as that is the output this question is looking for
                        newPair.add(map.get(reversedStr1));
                        result.add(newPair);
                    }
                }
            }
        }
        return result;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     *410. Split Array Largest Sum
     * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
     * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
     *
     * Note:
     * If n is the length of array, assume the following constraints are satisfied:
     *
     * 1 ≤ n ≤ 1000
     * 1 ≤ m ≤ min(50, n)
     * Examples:
     *
     * Input:
     * nums = [7,2,5,10,8]
     * m = 2
     *
     * Output:
     * 18
     */
    //Solution
    //   The answer is between maximum value of input array numbers and sum of those numbers.
    //   Use binary search to approach the correct answer. We have l = max number of array; r = sum of all numbers in the
    //   array;Every time we do mid = (l + r) / 2;
    //   Use greedy to narrow down left and right boundaries in binary search.
    //   3.1 Cut the array from left.
    //   3.2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still
    //   less than mid.
    //   3.3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot.
    //   If we can, it means that the mid value we pick is too small because we've already tried our best to make sure each
    //   part holds as many non-negative numbers as we can but we still have numbers left. So, it is impossible to cut the
    //   array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to l = mid + 1;
    //   If we can't, it is either we successfully divide the array into m parts and the sum of each part is less than mid,
    //   or we used up all numbers before we reach m. Both of them mean that we should lower mid because we need to find
    //   the minimum one. This leads to r = mid - 1;
        public static int splitArray(int[] nums, int m) {
            int max = 0;
            int sum = 0;
            for (int num : nums) {
                max = Math.max(max, num);
                sum += num;
            }

            int lo = max;
            int hi = sum;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int numOfSubarrays = split(nums, mid);
                if (numOfSubarrays > m) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            return lo;
        }

        private static int split(int[] nums, int validMaxSum) {
            int sum = 0;
            int count = 1;
            for (int num : nums) {
                if (sum + num > validMaxSum) {
                    sum = num;
                    count++;
                } else {
                    sum += num;
                }
            }
            return count;
        }

    /**
     * @author Rishi Khurana
     * 845. Longest Mountain in Array
     * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
     *
     * B.length >= 3
     * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * (Note that B could be any subarray of A, including the entire array A.)
     *
     * Given an array A of integers, return the length of the longest mountain.
     *
     * Return 0 if there is no mountain.
     *
     * Example 1:
     *
     * Input: [2,1,4,7,3,2,5]
     * Output: 5
     * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
     */
        public static int longestMountain(int[] A) {
            int res = 0, up = 0, down = 0;
            for (int i = 1; i < A.length; ++i) {
                if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) // if the previous element is same as that of this
                    // one, return  false or its the opposite of a mountain meaning first going down and then increasing,
                    // we dont have to consider that case and thus resetting it to zero
                    up = down = 0;
                if (A[i - 1] < A[i])
                    up++;
                if (A[i - 1] > A[i])
                    down++;
                if (up > 0 && down > 0 && up + down + 1 > res) // if we find the mountain then calculate
                    res = up + down + 1;
            }
            return res;
        }

        public boolean validMountainAray(int []A){
            int i = 0 ;
            while(i<A.length && i+1 < A.length && A[i] < A[i+1]){
                i++;
            }
            if(i == 0 || i+1 >=A.length) // meaning it was never increasing or we reached
                // it on the end
                return false;
            while(i < A.length && i+1 < A.length){
                if(A[i] <= A[i++ + 1]){
                    return false;
                }
            }
            return true;
        }


    /**
     * 593. Valid Square
     * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
     *
     * The coordinate (x,y) of a point is represented by an integer array with two integers.
     *
     * Example:
     *
     * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
     * Output: True
     */

    // another solution is mentioned in the validsquare class
    // Solution: the idea is to find the square of lengths, and validate that
    // There are only two equal longest lenghts. which will be diagonal
    // The non longest lengths are all equal which will be the sides

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        long[] lengths = {length(p1, p2), length(p2, p3), length(p3, p4),
        length(p4, p1), length(p1, p3),length(p2, p4)}; // all 6 sides

        long max = 0, nonMax = 0;
        for(long len : lengths) {
            max = Math.max(max, len);
        }
        int count = 0;
        for(int i = 0; i < lengths.length; i++) {
            if(lengths[i] == max)
                count++;
            else nonMax = lengths[i]; // non diagonal side.
        }
        if(count != 2)
            return false; // diagonals lenghts have to be same.
        for(long len : lengths) {
            if(len != max && len != nonMax) return false; // sides have to be same length
        }
        return true;
    }
    private long length(int[] p1, int[] p2) {
        return (long)Math.pow(p1[0]-p2[0],2) + (long)Math.pow(p1[1]-p2[1], 2);
    }

    /**
     * 482. License Key Formatting
     * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The
     * string is separated into N+1 groups by N dashes.
     *
     * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except
     * for the first group which could be shorter than K, but still must contain at least one character. Furthermore,
     * there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
     *
     * Given a non-empty string S and a number K, format the string according to the rules described above.
     *
     * Example 1:
     * Input: S = "5F3Z-2e-9-w", K = 4
     *
     * Output: "5F3Z-2E9W"
     *
     * Explanation: The string S has been split into two parts, each part has 4 characters.
     * Note that the two extra dashes are not needed and can be removed.
     */

    // accepted solution
    public static String licenseKeyFormatting2(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int len = S.length()-1;
        while(len >=0){
            int i =K-1;
            while(i >= 0 && len >=0){
                if(S.charAt(len) == '-') {
                    len--;
                }
                else {
                    char ch = S.charAt(len);
                    sb.append(Character.toUpperCase(ch));
                    len--;i--;
                }
            }
            if(len>=0)
                sb.append("-");
        }
        if(sb.length() >= 1 && sb.charAt(sb.length()-1) == '-')
            sb.replace(sb.length()-1 , sb.length() , "");
        return sb.reverse().toString();
    }

    // dint see the below solution
    public static String licenseKeyFormatting(String S, int K) {
        // Replacing all - and converting all letters to uppercase
        String S1 = S.replace("-", "");
        S1 = S1.toUpperCase();

        // Making stringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S1.length(); i++) {
            sb.append(S1.charAt(i));
        }
        int len = sb.toString().length();
        // Inserting '-' from back at every K position
        for (int i = K; i < len; i = i + K) {
            sb.insert(len - i, '-');
        }
        return sb.toString();
    }


    /**
     * @author Rishi Khurana
     * 1047. Remove All Adjacent Duplicates In String
     * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters,
     * and removing them.
     * We repeatedly make duplicate removals on S until we no longer can.
     * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
     *
     */
        public static String removeDuplicates(String s) {
            int i = 0, n = s.length();
            char[] res = s.toCharArray();
            for (int j = 0; j < n; ++j, ++i) {
                res[i] = res[j];
                if (i > 0 && res[i - 1] == res[i]) // count = 2
                    i -= 2;
            }
            return new String(res, 0, i);
        }
        public static String removeDuplicates2(String S) {
            Stack<Character> stack = new Stack<>();
            for(char s : S.toCharArray()){
                if(!stack.isEmpty() && stack.peek() == s)
                    stack.pop();
                else
                    stack.push(s);
            }
            StringBuilder sb = new StringBuilder();
            for(char s : stack) sb.append(s);
            return sb.toString();
        }

        /**
         * 1209. Remove All Adjacent Duplicates in String II
         * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them
         * causing the left and the right side of the deleted substring to concatenate together.
         *
         * We repeatedly make k duplicate removals on s until we no longer can.
         * Return the final string after all such duplicate removals have been made.
         * It is guaranteed that the answer is unique.
         * Example 1:
         * Input: s = "abcd", k = 2
         * Output: "abcd"
         * Explanation: There's nothing to delete
         * Input: s = "deeedbbcccbdaa", k = 3
         * Output: "aa"
         */
            public  String removeDuplicates(String s, int k) {
                Stack<Character> stackChar = new Stack<>();
                Stack<Integer> stackAdjCnt = new Stack<>();

                for (char c : s.toCharArray()) {
                    if (!stackChar.isEmpty() && stackChar.peek() == c) {
                        stackAdjCnt.push(stackAdjCnt.peek() + 1);
                    } else {
                        stackAdjCnt.push(1);
                    }
                    stackChar.push(c);
                    if (stackAdjCnt.peek() == k) {
                        for (int i = 0; i < k; i++) { // pop k adjacent equal chars
                            stackChar.pop();
                            stackAdjCnt.pop();
                        }
                    }
                }

                // Convert stack to string
                StringBuilder sb = new StringBuilder();
                while (!stackChar.empty()) {
                    sb.append(stackChar.pop());
                }
                return sb.reverse().toString();
            }


            // Solution without using Stack
            public String removeDuplicates2(String s, int k) {
                // LinkedList will be more efficient than Stack because Stack has to reallocate when size over capacity
                LinkedList<Adjacent> stack = new LinkedList<>();

                for (char c : s.toCharArray()) {
                    if (!stack.isEmpty() && stack.peek().ch == c) {
                        stack.peek().freq++;
                    } else {
                        stack.push(new Adjacent(c, 1));
                    }
                    if (stack.peek().freq == k) {
                        stack.pop();
                    }
                }

                // Convert linked list stack to string
                StringBuilder sb = new StringBuilder();
                while (stack.size() > 0) {
                    Adjacent peek = stack.removeLast();
                    for (int i = 0; i < peek.freq; i++) { // pop once
                        sb.append(peek.ch);
                    }
                }
                return sb.toString();
            }

            class Adjacent {
                char ch;
                int freq;

                public Adjacent(char ch, int freq) {
                    this.ch = ch;
                    this.freq = freq;
                }
            }

    /**
     * @author Rishi Khurana
     * 115. Distinct Subsequences
     * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
     *
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     *
     * It's guaranteed the answer fits on a 32-bit signed integer.
     *
     * Example 1:
     *
     * Input: S = "rabbbit", T = "rabbit"
     * Output: 3
     * Explanation:
     * As shown below, there are 3 ways you can generate "rabbit" from S.
     * (The caret symbol ^ means the chosen letters)
     *
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     * very good explanation https://www.youtube.com/watch?v=9yV6Elqvblw
     */
    //    Solution
    //    Explanation to the state transition function
    //
    //    dp[i][j] = dp[i-1][j] when s[i-1] != t[j-1] or dp[i][j] = dp[i-1][j] + dp[i-1][j-1] when s[i-1] == t[j-1],
    //    dp[i][j] represents the number of distinct subsequences for s[0, i-1] and t[0, t-1];
    //
    //    We first keep in mind that s is the longer string [0, i-1], t is the shorter substring [0, j-1]. We can assume t is fixed, and s is increasing in character one by one (this is the key point).
    //
    //    For example:
    //    t : ab--> ab --> ab --> ab
    //    s: a --> ac --> acb --> acbb
    //
    //    The first case is easy to catch. When the new character in s, s[i-1], is not equal with the head char in t, t[j-1], we can no longer increment the number of distinct subsequences, it is the same as the situation before incrementing the s, so dp[i][j] = dp[i-1][j].
    //
    //    However, when the new incrementing character in s, s[i-1] is equal with t[j-1], which contains two case:
    //
    //    We don't match those two characters, which means that it still has original number of distinct subsequences, so dp[i][j] = dp[i-1][j].
    //    We match those two characters, in this way. dp[i][j] = dp[i-1][j-1];
    //    Thus, including both two case, dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            int[][] dp = new int[m + 1][n + 1];
            // initialize the dp value when t is an empty string, number of subsequence of an empty string should be 1
            for(int i = 0; i < m; i ++){
                dp[i][0] = 1;
            }
            for(int i = 1; i <= m; i ++){
                for(int j = 1; j <= n; j ++){
                    //in both cases, the subsequence in String t should be ending with character t.charAt(j - 1)
            if(s.charAt(i - 1) == t.charAt(j - 1)){
                        // when two pointers pointing to same character
                        // if we take these two characters simultaneously, we should have dp[i-1][j-1] subsequences
                        // otherwise if we overlook current i (moving back for one step) and keeping the current j we have another dp[i -1][j]
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }else{
                        // when two pointers pointing to difference characters
                        //we cannot take these two characters but we still should make j ending with pointing to current position
                        // then we should move i backward
                    dp[i][j] = dp[i - 1][j];
                }
            }
         }
        return dp[m][n];
    }

    /**
     * 1110. Delete Nodes And Return Forest
     * Given the root of a binary tree, each node in the tree has a distinct value.
     * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
     * Return the roots of the trees in the remaining forest.  You may return the result in any order.
     */

    // iterative solution is better
        // Recursive Method
        Set<Integer> to_delete_set;
        List<TreeNode> res1;
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            to_delete_set = new HashSet<>();
            res1 = new ArrayList<>();
            for (int i : to_delete)
                to_delete_set.add(i);
            helper(root, true);
            return res1;
        }

        private TreeNode helper(TreeNode node, boolean is_root) {
            if (node == null) return null;
            boolean deleted = to_delete_set.contains(node.val);
            if (is_root && !deleted)
                res1.add(node);
            node.left = helper(node.left, deleted);
            node.right =  helper(node.right, deleted);
            return deleted ? null : node;
        }

        // Non Recursive method
        public List<TreeNode> delNodesNonRecursive(TreeNode root, int[] to_delete) {
            if (root == null)
                return new ArrayList<>();

            Set<TreeNode> resSet = new HashSet<>();
            resSet.add(root);
            if (to_delete.length == 0)
                return new ArrayList<>(resSet);

            Set<Integer> toDelete = new HashSet<>();
            for (int val : to_delete) toDelete.add(val);

            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (toDelete.contains(node.val)) {
                    resSet.remove(node);
                    if (node.left != null)
                        resSet.add(node.left);
                    if (node.right != null)
                        resSet.add(node.right);
                }
                if (node.left != null) {
                    q.offer(node.left);
                    if (toDelete.contains(node.left.val))
                        node.left = null;
                }
                if (node.right != null) {
                    q.offer(node.right);
                    if (toDelete.contains(node.right.val))
                        node.right = null;
                }
            }
            return new ArrayList<>(resSet);
        }
}



