package LeetcodePrograms.InterviewQuestions;

import java.util.*;

public class MetaQuestions {
    /**
     // Given a pattern, check if the input string matches it.
     // "i18n" is a short way to write down "internationalization",
     // when given a pattern and an input string, check if the input
     // string matches the pattern.

     // For example:
     // "i18n" should match "internationalization", but not "interpolation"
     // "f6k" should match "facebook", but not "focus"
     // "F2eb2k" or "8" should match "Facebook"Ï
     */
        public boolean validWordPatternMatch(String word, String pattern){
            int i =0 , j = 0;
            while(i < word.length() && j < pattern.length()){
                if(word.charAt(i) == word.charAt(j)){
                    i++;
                    j++;
                    continue;
                }

                if(pattern.charAt(j) <='0' || pattern.charAt(j) > '9'){
                    return false;
                }

                int start = j;
                while(j < pattern.length() && word.charAt(j) >= '0' && word.charAt(j) <='9'){
                    ++j;
                }
                int num = Integer.valueOf(pattern.substring(start, j));
                i = i +num;
            }

            return i == word.length() && j == pattern.length();
        }

        /**
         * Merge 2 intervals
         // Given two sorted, non-overlapping interval lists, return a 3rd interval list that is the union of the input interval lists.
         //
         // For example:
         // Input:
         // {[1,2], [3,9]}
         // {[4,6], [8,10], [11,12]}

         // Output should be:
         // {[1,2], [3,10], [11,12]}
         //
         // Input:
         // 0   1   2   3   4   5   6   7   8   9  10  11  12
         //     [---]   [-----------------------]
         //                 [-------]       [------]   [----]
         // Output:
         //     [---]   [--------------------------]   [----]

         */
        class Interval{
            int start;
            int end;
        }

        public List<Interval> mereSortedList(List<Interval> l1, List<Interval> l2){

            if( l1 == null || l1.size() == 0)
                return l2;
            if( l2 == null || l2.size() == 0)
                return l1;

            List<Interval> result = new ArrayList<>();

            Interval prev = null;

            int indexList1 = 0;
            int indexList2 = 0;

            // find first interval for result list
            if(l1.get(0).start < l2.get(0).start){
                prev = l1.get(0);
                indexList1++;
            }else{
                prev = l2.get(0);
                indexList2++;
            }
            while(indexList1 < l1.size() || indexList2 < l2.size()){

                if(indexList2 == l2.size() || (indexList1 < l1.size() && l1.get(indexList1).start < l2.get(indexList2).start)){
                    // Merge prev1 with list1
                    if(prev.end < l1.get(indexList1).start){
                        result.add(prev);
                        prev  = l1.get(indexList1);
                    }else{
                        prev.end = Math.max(prev.end , l1.get(indexList1).end);
                    }
                    indexList1++;
                }else{
                    if(prev.end < l2.get(indexList2).start){
                        result.add(prev);
                        prev = l2.get(indexList2);
                    }else{
                        prev.end = Math.max(prev.end , l2.get(indexList2).end);
                    }
                    indexList2++;
                }
            }
            result.add(prev);
            return result;
        }
/*
    767. Reorganize String
    Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
    Return any possible rearrangement of s or return "" if not possible.
    Example 1:

    Input: s = "aab"
    Output: "aba"
    Example 2:

    Input: s = "aaab"
    Output: ""
*/

    public String reorganizeString(String S) {

        // step 1:
        // build a hashmap to store characters and its frequencies:
        Map<Character, Integer> freq_map = new HashMap<>();
        for (char c: S.toCharArray()) {
            freq_map.put(c, freq_map.getOrDefault(c, 0) + 1);
        }
        // step 2:
        // put the char of freq_map into the maxheap with sorting the frequencies by large->small
        PriorityQueue<Character> maxheap = new PriorityQueue<>(
                (a, b) -> freq_map.get(b) - freq_map.get(a)
        );
        // addAll() is adding more then one element to heap
        maxheap.addAll(freq_map.keySet());

        // now maxheap has the most frequent character on the top

        // step 3:
        // obtain the character 2 by 2 from the maxheap to put in the result sb
        // until there is only one element(character) left in the maxheap
        // create a stringbuilder to build the result result
        StringBuilder sb = new StringBuilder();
        while (maxheap.size() > 1) {
            char first = maxheap.poll();
            char second = maxheap.poll();
            sb.append(first);
            sb.append(second);
            freq_map.put(first, freq_map.get(first) - 1);
            freq_map.put(second, freq_map.get(second) - 1);
            // insert the character back to the freq_map if the count in
            // hashmap of these two character are still > 0

            if (freq_map.get(first) > 0) {
                maxheap.offer(first);
            }
            if (freq_map.get(second) > 0) {
                maxheap.offer(second);
            }
        }

        if (!maxheap.isEmpty()) {
            // when there is only 1 element left in the maxheap
            // check the count, it should not be greater than 1
            // otherwise it would be impossible and should return ""
            if (freq_map.get(maxheap.peek()) > 1) {
                return "";
            }
            else {
                sb.append(maxheap.poll());
            }
        }
        return sb.toString();
    }

    /*
    129. Sum Root to Leaf Numbers
    You are given the root of a binary tree containing digits from 0 to 9 only.
    Each root-to-leaf path in the tree represents a number.
    For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
    Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
    A leaf node is a node with no children.
    Example 1:
    Input: root = [1,2,3]
    Output: 25
    Explanation:
    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.
    Therefore, sum = 12 + 13 = 25.
    */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }

    public int sum(TreeNode n, int s){
        if (n == null) return 0;
        if (n.right == null && n.left == null) return s*10 + n.val;
        return sum(n.left, s*10 + n.val) + sum(n.right, s*10 + n.val);
    }

// Given a directed graph, compress the graph by combining nodes.
// Input: Origal graph:
// a --> d
// b --> d
// c --> d
// d --> e
// e --> f
// f --> g
// f --> h
// {A, B, C} -> D -> E -> F -> {G, H}

// Output: output GRAPH:
// a --> def
// b --> def
// c --> def
// def --> g
// def --> h
// {A, B, C} -> DEF -> {G, H}

    public static void main(String[] args) {

    }
}
