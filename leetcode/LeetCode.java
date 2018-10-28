//https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/114449/a-general-approach-to-level-order-traversal-questions-in-java
//did not understand the degree of an array
//image smoother
//largest pallindrome product
//stock api
//longest harmonious subsequence
//Range addition II
//binary watch
//Find mode in binary search tree
//fb mutual friends
//implement hashtable with other method
//convert a number into hexa decimal
//number of boomerang
//divide 2 integers
//gray code question is all about the bit manipulation
//clone graph
//course schedule .. i think its a graph question
//Sum of two integers bit manipulation
//https://leetcode.com/problems/sum-of-two-integers/discuss/
//2 elements which are not sorted and you have to return the index
//single number I, II its a bitwise manipulation question..needs to be understood first
//bitwise and of numbers range
//a very good page to study sliding window algorithm
//https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/
//contains duplicates III
//count complete tree nodes
//https://leetcode.com/problems/subsets/discuss/
//the maze question
//partition equal subset sum , target sum and subset sum all are similar and needs to be done together
//word ladder problem
// a very important link
//https://leetcode.com/problems/combination-sum/?tab=Solutions
//longest increasing path in a matrix #TopInterviewQuestion
// implement n-ary tree
// design tic tac toe
// Remove invalid parenthesis #facebook

package leetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class LeetCode {

	// 32. Given a string containing just the characters '(' and ')', find the
	// length of the longest valid (well-formed) parentheses substring.
    //see it again
	public static int longestValidParentheses2(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		int left = -1;
		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) == '(')
				stack.push(j);
			else {
				if (stack.isEmpty())
					left = j;
				else {
					stack.pop();
					if (stack.isEmpty())
						max = Math.max(max, j - left);
					else
						max = Math.max(max, j - stack.peek());
				}
			}
		}
		return max;
	}

	// Q35 search insert position a very easy question
	// Given a sorted array and a target value, return the index if the target
	// is found.
	// If not, return the index where it would be if it were inserted in order.
	public int searchInsert(int[] A, int target) {
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	// Q219 Contain duplicates II
	// Given an array of integers and an integer k, find out whether there are
	// two distinct indices
	// i and j in the array such that nums[i] = nums[j]
	// and the absolute difference between i and j is at most k.

	// we can use hashset as well as we are not looking to return indices
	// this solution : Iterate through array and use HashTable to save number[i]
	// as a key and i as a value.
	// If the map already contains number[i] - subtract map.get(number[i]) from
	// i and return true if result is
	// less or equal to k.
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (i - map.get(nums[i]) <= k)
					return true;
			}
			map.put(nums[i], i);
		}
		return false;
	}

	// Another way of doing it
	public boolean containsNearbyDuplicate2(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k)
				set.remove(nums[i - k - 1]);
			if (!set.add(nums[i]))
				return true;
		}
		return false;
	}

	// Q36 Valid Sudoku #TopInterviewQuestion
	// Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	// The Sudoku board could be partially filled, where empty cells are filled
	// with the character '.'.
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			Set<Character> rows = new HashSet<>(); // hashset if element is
													// already there, it will
													// return as false
			Set<Character> cols = new HashSet<>();
			Set<Character> cubes = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && !rows.add(board[i][j]))
					return false;
				if (board[j][i] != '.' && !cols.add(board[j][i]))
					return false;
				int colOffset = j % 3, rowOffset = j / 3;
				int colStart = 3 * (i % 3), rowStart = 3 * (i / 3);
				int row = rowStart + rowOffset, col = colStart + colOffset;
				if (board[row][col] != '.' && !cubes.add(board[row][col]))
					return false;
			}
		}
		return true;
	}

	// Much better answer for the above question
	public boolean isValidSudoku2(char[][] board) {
		Set<String> set = new HashSet<>();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				char val = board[row][col];
				if (val != '.') {
					int block = (row / 3 * 3) + (col / 3);
					if (set.contains("r" + row + val) || set.contains("c" + col + val)
							|| set.contains("b" + block + val))
						return false;
					else {
						set.add("r" + row + val);
						set.add("c" + col + val);
						set.add("b" + block + val);
					}
				}
			}
		}

		return true;
	}

	// Q1 two sum 2 sum working solution .. accepted in leetcode ..twosum
	// #TopInterviewQuestion
	// Q167 two sum II - input array is sorted .. accepted in leetcode
	// Given an array of integers, return indices of the two numbers such that
	// they add up to a specific target.
	// You may assume that each input would have exactly one solution, and you
	// may not use the same element twice.
	public int[] twoSum(int[] nums, int target) {
		int[] resultIndex = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length - 1; i++) {
			if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
				resultIndex[0] = i;
				resultIndex[1] = map.get(target - nums[i]);
				return resultIndex;
			}
		}
		return resultIndex;
	}

	// Q9 Pallindrome Number #GoodQuestion
	// Determine whether an integer is a palindrome. An integer is a palindrome
	// when it reads the same backward as forward.
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int rev = 0, xx = x;
		while (x != 0) {
			rev = rev * 10 + x % 10;
			x /= 10;
		}
		return (xx == rev);
	}

	// better solution
	// compare half of the digits in x, so don't need to deal with overflow.
	public boolean isPalindrome2(int x) {
		if (x < 0 || (x != 0 && x % 10 == 0))
			// As discussed above, when x < 0, x is not a palindrome.
			// Also if the last digit of the number is 0, in order to be a
			// palindrome,
			// the first digit of the number also needs to be 0.
			// Only 0 satisfy this property.
			return false;
		int rev = 0;
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
// When the length is an odd number, we can get rid of the middle digit
// by revertedNumber/10
// For example when the input is 12321, at the end of the while loop we
// get x = 12, revertedNumber = 123,
// since the middle digit doesn't matter in palidrome(it will always
// equal to itself), we can simply get rid of it.
		return (x == rev || x == rev / 10);
	}

	// Q31 next permutation ... #GoodQuestion
	// [6，3，4，9，8，7，1]
	//      i-1 i     k
	//// (1) leftward find the first decreasing number @ index i - 1, (4)
	// (2) then nums[i:] must be rightward decreasing (9,8,7,1)
	// (3) leftward find the first number that is larger than i - 1, which is at
	// k, (7)
	// (4) swap i - 1 with k, (6,3,7,9,8,4,1). we can see that nums[i:] will
	// still be rightward decreasing (9,8,4,1)
	// (5) But we need them to be rightward increasing so that it's the smallest
	// after swapping, so we reversed nums[i:],
	// which get the result (6,3,7,1,4,8,9)
	public static void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return;
		}
		int i = nums.length - 1;
		for (; i >= 1; i--) {
			if (nums[i] > nums[i - 1]) { // find first number which is smaller
											// than it's after number
				break;
			}
		}
		if (i != 0) {
			swap(nums, i - 1); // if the number exist,which means that the nums
								// not like{5,4,3,2,1}
		}
		reverse(nums, i);
	}

	public static void swap(int[] a, int i) {
		for (int j = a.length - 1; j > i; j--) {
			if (a[j] > a[i]) {
				int t = a[j];
				a[j] = a[i];
				a[i] = t;
				break;
			}
		}
	}

	private static void reverse(int[] a, int i) {// reverse the number after the
													// number we have found
		int first = i;
		int last = a.length - 1;
		while (first < last) {
			int t = a[first];
			a[first] = a[last];
			a[last] = t;
			first++;
			last--;
		}
	}

	// Q33 Search in a rotated sorted array #TopInterviewQuestion
	public int search(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[start] <= nums[mid]) {
				if (target < nums[mid] && target >= nums[start])
					end = mid - 1;
				else
					start = mid + 1;
			}

			if (nums[mid] <= nums[end]) { // right half is sorted
				if (target > nums[mid] && target <= nums[end])
					start = mid + 1;
				else
					end = mid - 1;
			}
		}
		return -1;
	}

	// Q118 Pascal Triangle #TopInterviewQuestion
	// Given a non-negative integer numRows, generate the first numRows of
	// Pascal's triangle. //this solution is better
	public static List<List<Integer>> pascalTriangle(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		if (numRows <= 0)
			return result;
		ArrayList<Integer> pre = new ArrayList<>();
		pre.add(1);
		result.add(pre);
		for (int i = 2; i <= numRows; i++) {
			ArrayList<Integer> cur = new ArrayList<>();
			cur.add(1); // first
			for (int j = 0; j < pre.size() - 1; j++) {
				cur.add(pre.get(j) + pre.get(j + 1)); // middle
			}
			cur.add(1);// last
			result.add(cur);
			pre = cur;
		}
		return result;
	}

	// Another way of doing the above same code
	public List<List<Integer>> pascalTriangletwo(int numRows) {
		List<List<Integer>> allrows = new ArrayList<List<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < numRows; i++) {
			row.add(0, 1);
			for (int j = 1; j < row.size() - 1; j++)
				row.set(j, row.get(j) + row.get(j + 1));
			allrows.add(new ArrayList<Integer>(row));
		}
		return allrows;
	}

	// Q119 Pascal's Triangle II
	// Given a non-negative index k where k ≤ 33, return the kth index row of
	// the Pascal's triangle.
	public List<Integer> getRow(int numRows) {
		List<Integer> list = new ArrayList<Integer>();
		if (numRows < 0)
			return list;

		for (int i = 0; i < numRows + 1; i++) {
			list.add(0, 1);
			for (int j = 1; j < list.size() - 1; j++) {
				list.set(j, list.get(j) + list.get(j + 1));
			}
		}
		return list;
	}

	// Q7 Reverse Integer #TopInterviewQuestion
	// Given a 32-bit signed integer, reverse digits of an integer.
	// Assume we are dealing with an environment which could only store integers
	// within the 32-bit signed integer
	// range: [−231, 231 − 1]. For the purpose of this problem,
	// assume that your function returns 0 when the reversed integer overflows.

	// this is the best method
	// same as the way we found if the number is pallindrome or not.. only
	// difference is in the while loop
	public int reverseNumber(int x) {
		long rev = 0;
		while (x != 0) {
			rev = rev * 10 + x % 10;
			x = x / 10;
			if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
				// this is also one more difference so as to take care of the  range
				return 0;
		}
		return (int) rev;
	}

	public int reverse(int x) {
		int result = 0;
		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
			// If overflow exists, the new result will not equal previous one.
			// below line
			if ((newResult) / 10 != result)
			// if ((newResult - tail) / 10 != result)
			{
				return 0;
			}
			result = newResult;
			x = x / 10;
		}
		return result;
	}

	// explanation of the overflow thing... why that condition is for the
	// overflow
	// For your reference, to test if there is overflow for any integer x of the
	// form x = a * 10 + b
	// where |b| < 10, the right way should be comparing x / 10 (integer
	// division) with a. If x / 10 != a,
	// there is overflow, otherwise no overflow can happen.
	// The proof is as follows: first note that x itself is a signed integer,
	// therefore we have INT_MIN <= x <= INT_MAX, which implies INT_MIN/10 <=
	// x/10 <= INT_MAX/10.
	// So if we assume x / 10 == a, we have INT_MIN/10 <= a <= INT_MAX/10.
	// Since |b| < 10, then a * 10 + b can overflow only if a = INT_MAX/10 or a
	// = INT_MIN/10.
	// For signed 32-bit integers, we have INT_MAX = 2147483647 and INT_MIN =
	// -2147483648.
	// For a = INT_MAX/10 = 214748364, overflow will happen only if b = 8 or 9.
	// However if this is the case, then x = a * 10 + b will be negative and x /
	// 10 cannot be the same as a,
	// contradicting our assumption above. Similarly if a = INT_MIN/10 =
	// -214748364,
	// overflow will happen only if b = -9 but then x = a * 10 + b will be
	// positive and
	// again x / 10 won't be equal to a. In summary, x / 10 != a <==> overflow.
	//
	// As for the test condition in the original post, which is equivalent to
	// testing (x - b) / 10 != a,
	// it will only cover the cases when |a| > INT_MAX/10 but not the cases
	// when |a| = INT_MAX/10, b = 8 or 9 for positive a and -9 for negative a.
	// The test condition works here because the edge cases mentioned above
	// won't happen due to the fact
	// that the input itself is a signed integer. If the input is something
	// else, say a string
	// (see String to Integer (atoi)), the test condition above will fail the
	// edge cases.

	// Q8 String to integer (atoi) #TopInterviewQuestion
	// i guess the below solution is not working properly
	public static int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
		str = str.trim();
		char flag = '+';
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}

		// use double to store result
		double result = 0;

		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			// Character.getNumericValue(str.charAt(i))
			// System.out.println(str.charAt(i));
			// System.out.println(str.charAt(i) - '0');
			// System.out.println("result is " + result);
			// System.out.println(Character.getNumericValue(str.charAt(i)));
			i++;
		}
		if (flag == '-')
			result = -result;
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int) result;
	}

	// this is the method that passes all the leetcode testcases
	public int myAtoi(String str) {
		int index = 0;
		int total = 0;
		int sign = 1;

		// Check if empty string
		if (str.length() == 0)
			return 0;

		// remove white spaces from the string
		while (index < str.length() && str.charAt(index) == ' ')
			index++;

		if (index == str.length())
			return 0; // this should be return total*sign

		// get the sign
		if (str.charAt(index) == '+' || str.charAt(index) == '-') {
			sign = str.charAt(index) == '+' ? 1 : -1;
			index++;
		}

		// convert to the actual number and make sure it's not overflow
		while (index < str.length()) {
			int digit = str.charAt(index) - '0';
			if (digit < 0 || digit > 9)
				break;

				// overflow can be checked by below line have to see about this line above
				if ((Integer.MAX_VALUE - digit) / 10 < total)
					return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

			total = total * 10 + digit;
			index++; // don't forget to increment the counter
		}
		return total * sign;
	}

	// Q13 roman to integer #TopInterviewQuestion
	// I 1
	// V 5
	// X 10
	// L 50
	// C 100
	// D 500
	// M 1000
	public static int romantoint(String str) {
		int sum = 0;
		if (str.indexOf("IV") != -1) {
			sum -= 2;
		}
		if (str.indexOf("IX") != -1) {
			sum -= 2;
		}
		if (str.indexOf("XL") != -1) {
			sum -= 20;
		}
		if (str.indexOf("XC") != -1) {
			sum -= 20;
		}
		if (str.indexOf("CD") != -1) {
			sum -= 200;
		}
		if (str.indexOf("CM") != -1) {
			sum -= 200;
		}
		int count = 0;
		char c[] = str.toCharArray();
		for (; count <= str.length() - 1; count++) {
			if (c[count] == 'M')
				sum += 1000;
			if (c[count] == 'D')
				sum += 500;
			if (c[count] == 'C')
				sum += 100;
			if (c[count] == 'L')
				sum += 50;
			if (c[count] == 'X')
				sum += 10;
			if (c[count] == 'V')
				sum += 5;
			if (c[count] == 'I')
				sum += 1;
		}
		return sum;
	}

	// Another solution
	//this is also a good way of solving
	public static int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		char[] chars = s.toCharArray();
		int result = 0;
		int i = 0, j = 1;
		for (; j < chars.length; i++, j++) {
			if (map.get(chars[i]) >= map.get(chars[j])) {
				result += map.get(chars[i]);
			} else {
				result -= map.get(chars[i]);
			}
		}
		result += map.get(chars[i]);
		return result;
	}

	// Q12 Integer to Roman //#TopInterviewQuestion
	public String intToRoman(int num) {
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}
		return sb.toString();
	}

// Q14 longest common prefix #TopInterviewQuestion
// Write a function to find the longest common prefix string amongst an array of strings.
	public static String longestCommonPrefix(String[] strs) {
		StringBuilder result = new StringBuilder();
		if (strs != null && strs.length > 0) {
			Arrays.sort(strs);
			char[] a = strs[0].toCharArray();
			char[] b = strs[strs.length - 1].toCharArray();
			for (int i = 0; i < a.length; i++) {
				if ( b[i] == a[i]) {
					result.append(b[i]);
				} else {
					return result.toString();
				}
			}
			return result.toString();
		}
		return result.toString();
	}

	// Q20 Valid Parentheses #TopInterviewQuestion
	public boolean isValidParentheses(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']');
			else if (stack.isEmpty() || stack.pop() != c)
				return false;
		}
		return stack.isEmpty();
	}

	// Q98 Validate Binary Search Tree #TopInterviewQuestion
	// Given a binary tree, determine if it is a valid binary search tree (BST).
	public boolean isValidBST(TreeNode root) {

		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
		if (root == null)
			return true;
		if (root.val >= maxVal || root.val <= minVal)
			return false;
		return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
	}

	public boolean isValidBSTIterative(TreeNode root) { // #GoodQuestion
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}

	// Q80 remove duplicates from the sorted array II
	// What if duplicates are allowed at most twice?
	// I think both Remove Duplicates from Sorted Array I and II could be solved
	// in a consistent
	// and more general way by allowing the duplicates to appear k times
	// (k = 1 for problem I and k = 2 for problem II).
	// Here is my way: we need a count variable to keep how many times the
	// duplicated element appears,
	// if we encounter a different element, just set counter to 1, if we
	// encounter a duplicated one,
	// we need to check this count, if it is already k, then we need to skip it,
	// otherwise, we can keep this element. The following is the implementation
	// and can pass both OJ:
	public static int removeDuplicates(int A[], int n, int k) {
		if (n <= k)
			return n;
		int i = 1, j = 1;
		int cnt = 1;
		while (j < n) {
			if (A[j] != A[j - 1]) {
				cnt = 1;
				A[i++] = A[j];
			} else {
				if (cnt < k) {
					A[i++] = A[j];
					cnt++;
				}
			}
			++j;
		}
		return i;
	}

	// very elegant solution for remove duplicates from the sorted array II but
	// need to be seen again
	public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int n : nums)
			if (i < 2 || n > nums[i - 2])
				nums[i++] = n;
		return i;
	}

	// Q27 Remove Element
	// Given an array nums and a value val, remove all instances of that value
	// in-place and return the new length.
	// Do not allocate extra space for another array, you must do this by
	// modifying the input array in-place
	// with O(1) extra memory.
	// The order of elements can be changed. It doesn't matter what you leave
	// beyond the new length.
	public int removeElement1(int[] A, int elem) {
		int m = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != elem) {
				A[m] = A[i];
				m++;
			}
		}
		return m;
	}

	// Remove all the instances of a particular element
	public int removeElement(int[] A, int elem) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		if (A.length == 0)
			return 0;
		for (int i = 0; i < A.length; i++) {
			list.add(A[i]);
		}
		Iterator<Integer> it = list.iterator();
		try {
			while (it.hasNext()) {
				if (it.next() == elem) {
					it.remove();
				}
			}
		} catch (Exception e) {
			System.out.println("No such element");
		}
		return list.size();
	}


	// Q28 Implement strStr() #TopInterviewQuestion
	// function to find the first occurrence of the substring needle in the
	// string haystack
	public int strStr(String haystack, String needle) { // haystack.lastIndexOf(needle);

		int l1 = haystack.length(), l2 = needle.length();
		if (l1 < l2) {
			return -1;
		} else if (l2 == 0) {
			return 0;
		}
		int threshold = l1 - l2;
		for (int i = 0; i <= threshold; ++i) {
			if (haystack.substring(i, i + l2).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

	// Given a sorted array and a target value, return the index if the target
	// is found. If not, return the index where it would be if it were inserted
	// in order.
	public static int SearchInsertPosition(int A[], int target) {
		// binary search and return low
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	// Q38 Count and Say #TopInterviewQuestion
	// 1
	// 11
	// 21
	// 1211
	// 111221
	// 312211
	// 13112221
	// 1113213211
	// 31131211131221
	// 13211311123113112211
	public static void countAndSay(int num) {
		StringBuilder curr = new StringBuilder("1");
		System.out.println("1");
		StringBuilder prev;
		char say;
		int count;
		for (int i = 1; i < num; i++) {
			prev = curr;
			curr = new StringBuilder();
			count = 1;
			say = prev.charAt(0);
			for (int j = 1; j < prev.length(); j++) {
				if (prev.charAt(j) != say) {
					curr.append(count).append(say);
					count = 1;
					say = prev.charAt(j);
				} else
					count++;
			}
			curr.append(count).append(say);
			System.out.println(curr.toString());
		}
	}

	// Another way of doing this question (easy approach)
	public String countAndSay2(int n) {
		String s = "1";
		for (int i = 1; i < n; i++) {
			s = countIdx(s);
		}
		return s;
	}

	public String countIdx(String s) {
		StringBuilder sb = new StringBuilder();
		char c = s.charAt(0);
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
			} else {
				sb.append(count);
				sb.append(c);
				c = s.charAt(i);
				count = 1;
			}
		}
		sb.append(count);
		sb.append(c);
		return sb.toString();
	}

	// Q152 Maximum Product SubArray #TopInterviewQuestion
	// Find the contiguous subarray within an array (containing at least one
	// number) which has the largest sum.
	// For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	// the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	public void maximumSubArray(int[] nums) {
		int max = nums[0];
		int newsum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			newsum = Math.max(newsum + nums[i], nums[i]);
			max = Math.max(max, newsum);
		}
		System.out.println("max is " + max);
	}

	// Q70 Climbing stairs problem #TopInterviewQuestion
	// You are climbing a stair case. It takes n steps to reach to the top.
	// Each time you can either climb 1 or 2 steps. In how many distinct ways
	// can you climb to the top?
	public int fibonacciSeriesRecursive(int n) {
		if (n == 1 || n == 0) {
			return n;
		}
		return fibonacciSeriesRecursive(n - 1) + fibonacciSeriesRecursive(n - 2);
	}

	// non recursive solution
	public static int fibonacciSeries(int n) {
		int n1 = 0, n2 = 1;
		int sum;
		if (n == n1 || n == n2) {
			return n;
		}
		for (int i = 2; i <= n; i++) {
			sum = n1 + n2;
			n1 = n2;
			n2 = sum;
		}
		return n2;
	}

	// Q367 Valid Perfect square
	public boolean isPerfectSquare(int num) {
		int low = 1, high = num;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (mid * mid == num) {
				return true;
			} else if (mid * mid < num) {
				low = (int) mid + 1;
			} else {
				high = (int) mid - 1;
			}
		}
		return false;
	}

	// Q83 Remove Duplicates from sorted list I
	public ListNode deleteDuplicates(ListNode head) {
		ListNode current = head;
		while (current != null && current.next != null) {
			if (current.next.val == current.val) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		return head;
	}

	// Q88 Merge Sorted Array #TopInterviewQuestion
	// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1
	// as one sorted array.
	// Note: The number of elements initialized in nums1 and nums2 are m and n
	// respectively.
	// You may assume that nums1 has enough space (size that is greater or equal
	// to m + n) to hold additional elements from nums2.
	public void merge(int A[], int m, int B[], int n) {
		int i = m - 1;
		int j = n - 1;
		int k = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (A[i] > B[j])
				A[k--] = A[i--];
			else
				A[k--] = B[j--];
		}
		while (j >= 0)
			A[k--] = B[j--];
	}

	// Q243 Shortest word distance
	// Given a list of words and two words word1 and word2,
	// return the shortest distance between these two words in the list.
	public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                p1 = i;

            if (words[i].equals(word2))
                p2 = i;

            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }

        return min;
	}

	// Q101 Symmetric Tree #TopInterviewQuestion
	public boolean isSymmetric(TreeNode root) {
		return root == null || isSymmetricHelp(root.left, root.right);
	}

	private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
		if (left == null || right == null)
			return left == right;
		if (left.val != right.val)
			return false;
		return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
	}

	public boolean isSymmetricNonRecursive(TreeNode root) {
		if (root == null)
			return true;
		Queue<TreeNode> q = new LinkedList();
		q.add(root.left);
		q.add(root.right);
		while (!q.isEmpty()) {
			TreeNode left = q.poll();
			TreeNode right = q.poll();
			if (left == null && right == null)
				continue;
			if (left == null || right == null || left.val != right.val)
				return false;
			q.add(left.left);
			q.add(right.right);
			q.add(left.right);
			q.add(right.left);
		}
		return true;
	}

	// Q104 Maximum depth of Binary tree #TopInterviewQuestion
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	public int maxDepthNonRecursive(TreeNode root) {
		if (root == null)
			return 0;
		int depth = 0;
		Queue<TreeNode> nodes = new LinkedList<>();
		nodes.offer(root);
		while (!nodes.isEmpty()) {
			int size = nodes.size();
			depth++;
			while (size-- > 0) {
				TreeNode node = nodes.poll();
				if (node.left != null)
					nodes.offer(node.left);
				if (node.right != null)
					nodes.offer(node.right);
			}
		}
		return depth;
	}

	// Q111 Minimum Depth of a tree
	// Given a binary tree, find its minimum depth.
	// The minimum depth is the number of nodes along the shortest path from the
	// root node down
	// to the nearest leaf node.
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null)
			return 1 + minDepth(root.right);
		if (root.right == null)
			return 1 + minDepth(root.left);
		return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	}

	public int minDepth2(TreeNode root) {
		if (root == null)
			return 0;
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;

	}

	public int minDepthNonRecursive(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null) {
			return 0;
		}
		int depth = 1;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				root = queue.poll();
				if (root.left == null && root.right == null) {
					return depth;
				}
				if (root.left != null) {
					queue.offer(root.left);
				}
				if (root.right != null) {
					queue.offer(root.right);
				}
			}
			depth++;
		}
		return depth;
	}

	// Q121 Best time to Buy and Sell stocks #TopInterviewQuestion
	// Say you have an array for which the ith element is the price of a given
	// stock on day i.
	// If you were only permitted to complete at most one transaction
	// (ie, buy one and sell one share of the stock), design an algorithm to
	// find the maximum profit.
	public static int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int min = prices[0];
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			} else {
				if (prices[i] - min > profit) {
					profit = prices[i] - min;
				}
			}
			// No else..just the if statement
			// if (prices[i] - min > profit)
			// {
			// profit = prices[i] - min;
			// }
		}
		return profit;
	}

	// Below is a good solution
	// another solution //check once again
	int maxProfit3(int[] prices) {
		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			minPrice = Math.min(minPrice, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - minPrice);
		}
		return maxProfit;
	}

	// Q122 Best time to Buy and Sell stocks II #TopInterviewQuestion
	// Say you have an array for which the ith element is the price of a given
	// stock on day i.
	// Design an algorithm to find the maximum profit. You may complete as many
	// transactions as you like
	// (ie, buy one and sell one share of the stock multiple times).
	// However, you may not engage in multiple transactions at the same time
	// (ie, you must sell the stock before you buy again).

	public int maxProfit2(int[] prices) {
		int total = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] > prices[i])
				total += prices[i + 1] - prices[i];
		}
		return total;
	}

	// or finding local minimum and local maximum
	public int maxProfit4(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int i = 0;
		int peak = prices[0];
		int valley = prices[0];
		int maxProfit = 0;
		int len = prices.length;
		while (i < len - 1) {
			while (i < len - 1 && prices[i] >= prices[i + 1]) {// down
				i++;
			}
			valley = prices[i];
			while (i < len - 1 && prices[i] <= prices[i + 1]) {// up
				i++;
			}
			peak = prices[i];
			maxProfit += peak - valley;
		}
		return maxProfit;
	}

	// Q125 Valid Palindrome #TopInterviewQuestion
	// Given a string, determine if it is a palindrome, considering only
	// alphanumeric characters and ignoring cases.
	// Input: "A man, a plan, a canal: Panama" Output: true
	public boolean isPalindrome(String s) {
		if (s.isEmpty()) {
			return true;
		}
		int head = 0, tail = s.length() - 1;
		char cHead, cTail;
		while (head <= tail) {
			cHead = s.charAt(head);
			cTail = s.charAt(tail);

			if (!Character.isLetterOrDigit(cHead)) {
				head++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				tail--;
			} else {
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
					return false;
				}
				head++;
				tail--;
			}
		}
		return true;
	}

	// Q136 Given an array of integers, every element appears twice except for
	// one. #TopInterviewQuestion
	// Find that single one.
	public int singleNumber(int A[], int n) {
		int result = A[0];
		for (int i = 1; i < n; i++) {
			result = result ^ A[i]; /* Get the xor of all elements */
			// Logic: XOR will return 1 only on two different bits.
			// So if two numbers are the same, XOR will return 0.
			// Finally only one number left.
			// A ^ A = 0 and A ^ B ^ A = B.
		}
		return result;
	}

	// Q171 excel sheet column number #TopInterviewQuestion
	// Given a column title as appear in an Excel sheet, return its
	// corresponding column number.
	// A -> 1
	// B -> 2
	// C -> 3
	// ...
	// Z -> 26
	public int titletoNum(String s) {
		int value = 0;
		int power = 0;
		if (s == null || s.length() == 0)// if enpty or null
			return -1;
		for (int i = s.length() - 1; i >= 0; i--) {
			value = value + ((int) Math.pow(26, power)) * ((int) s.charAt(i) - 64);
			power++;
		}
		return value;
	}

	// Better solution
	public int titletoNum2(String s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result = result * 26 + (s.charAt(i) - 'A' + 1);
		}
		return result;
	}

	// Q168 excel sheet column title
	// Given a positive integer, return its corresponding column title as appear
	// in an Excel sheet.
	public String convertToTitle(int n) {
		StringBuilder result = new StringBuilder();
		while (n > 0) {
			n--;
			// result.insert(0, (char)('A' + n % 26)); //either insert it at the
			// 0th position or append it and later on reverse it.
			result.append((char) ('A' + n % 26));

			// e.g. if n is 25, n%26 = 25, so 'A' + 25 = 'Z'
			// e.g. if n is 26, n%26 = 0, so 'A' + 0 = 'A'.
			// e.g. if n is 27, n%26 = 1, so 'A' + 1 = 'B'
			n /= 26;
		}
		result.reverse();
		return result.toString();
	}

	// Q169 majority element #TopInterviewQuestion
	// Given an array of size n, find the majority element.
	// The majority element is the element that appears more than ⌊ n/2 ⌋ times
	// also sorting the array and first & middle or middle and the last will be
	// same
	public static int majorityElement(int[] num) {
		int major = num[0], count = 1;
		for (int i = 1; i < num.length; i++) {
			if (count == 0) {
				count++;
				major = num[i];
			} else if (major == num[i]) {
				count++;
			} else
				count--;
		}
		return major;
	}

	// Majority Element II
	// Given an integer array of size n, find all elements that appear more than
	// ⌊ n/3 ⌋ times.
	// The algorithm should run in linear time and in O(1) space.
	public List<Integer> majorityElement2(int[] nums) {
		Integer major1 = null, major2 = null, cnt1 = 0, cnt2 = 0;
		for (Integer num : nums) {
			if (num.equals(major1)) {
				cnt1++;
			} else if (num.equals(major2)) {
				cnt2++;
			} else if (cnt1 == 0) {
				major1 = num;
				cnt1 = 1;
			} else if (cnt2 == 0) {
				major2 = num;
				cnt2 = 1;
			} else {
				cnt1--;
				cnt2--;
			}
		}
		cnt1 = cnt2 = 0;
		for (Integer num : nums) {
			if (num.equals(major1))
				cnt1++;
			else if (num.equals(major2))
				cnt2++;
		}
		List<Integer> result = new ArrayList<>();
		if (cnt1 > nums.length / 3)
			result.add(major1);
		if (cnt2 > nums.length / 3)
			result.add(major2);
		return result;
	}

	// Q172 factorial trailing zeros #TopInterviewQuestion
	// Given an integer n, return the number of trailing zeroes in n!.
	// 10 is the product of 2 and 5. In n!, we need to know how many 2 and 5,
	// and the number of zeros is the minimum of the number of 2 and the number
	// of 5.
	// Since multiple of 2 is more than multiple of 5, the number of zeros is
	// dominant by the number of 5.
	public int trailingZeroes(int n) {
		int r = 0;
		while (n > 0) {
			n /= 5;
			r += n;
		}
		return r;
	}

	// Q189 rotate array #TopInterviewQuestion
	// Given an array, rotate the array to the right by k steps, where k is
	// non-negative.
	public void rotate(int[] nums, int k) {
		k %= nums.length;
		// right rotate
		// example of right rotation
		// Input: [1,2,3,4,5,6,7] and k = 3
		// Output: [5,6,7,1,2,3,4]
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);

		// left rotate
		k %= nums.length;
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
		reverse(nums, 0, nums.length - 1);
	}

	private void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}

	// Q202 Happy Number #TopInterviewQuestion
	// A happy number is a number defined by the following process:
	// Starting with any positive integer, replace the number by the sum of the
	// squares of its digits,
	// and repeat the process until the number equals 1 (where it will stay), or
	// it loops endlessly in a cycle which does not include 1. Those numbers for
	// which this process ends in 1 are happy numbers.
	// Example: 19 is a happy number
	// 12 + 92 = 82
	// 82 + 22 = 68
	// 62 + 82 = 100
	// 12 + 02 + 02 = 1
	public static boolean isHappy(int n) {
		Set<Integer> inLoop = new HashSet<Integer>();
		int squareSum, remain;
		while (inLoop.add(n)) {
			squareSum = 0;
			while (n > 0) {
				remain = n % 10;
				squareSum += remain * remain;
				n /= 10;
			}
			if (squareSum == 1)
				return true;
			else
				n = squareSum;
		}
		return false;
	}

	// Alternate Solution and a very good solution based on Floyds Cycle
	// Detection Algorithm
	public static boolean isHappy2(int n) {
		int slow = n, fast = n;
		do {
			slow = next(slow);
			fast = next(next(fast));
		} while (slow != fast);

		return slow == 1 ? true : false;
	}

	private static int next(int n) {
		int sum = 0;
		while (n > 0) {
			int i = n % 10;
			n /= 10;
			sum += i * i;
		}
		return sum;
	}


	// Q231 Power of two
	// Given an integer, write a function to determine if it is a power of two.
	public boolean isPowerOfTwo(int n) {
		if (n == 0)
			return false;
		while (n % 2 == 0)
			n /= 2;
		return (n == 1);
	}

	// Q326 Power of three #TopInterviewQuestion
	public boolean isPowerOfThree(int n) {
		if (n > 1)
			while (n % 3 == 0)
				n /= 3;
		return n == 1;
	}

	// Q258 add digits #GoodQuestion
	// Given a non-negative integer num, repeatedly add all its digits until the
	// result has only one digit.
	public int addDigits(int num) {
		while (num > 9) {
			int temp = 0;
			while (num > 0) {
				temp += num % 10;
				num /= 10;
			}
			num = temp;
		}
		return num;
	}

	// Amazing solution for the above same question
	int addDigits2(int num) {
		int res = num % 9;
		return (res != 0 || num == 0) ? res : 9;
	}

// Q26 remove duplicates from sorted array #TopInterviewQuestion
// Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	public static int removeduplicatesfromSortedArray(int A[], int n) {
		int count = 0;
		for (int i = 1; i < n; i++) {
			if (A[i] == A[i - 1])
				count++;
			else
				A[i - count] = A[i]; // overwriting the duplicated element with the next new element
		}
		return n - count;
	}

	// Q283 move zeroes #TopInterviewQuestion
	// Shifting zeros on the right
	// Shift non-zero values as far forward as possible
	// Fill remaining space with zeros
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int insertPos = 0;
		for (int num : nums) {
			if (num != 0)
				nums[insertPos++] = num;
		}
		while (insertPos < nums.length) {
			nums[insertPos++] = 0;
		}
	}

	// Q284 Peeking iterator
	// Given an Iterator class interface with methods: next() and hasNext(),
	// design and implement a PeekingIterator that support the peek() operation
	// it essentially peek() at the element that will be returned by the next call to next().
	class PeekingIterator implements Iterator<Integer> {
		Integer next;
		Iterator<Integer> iter;
		boolean noSuchElement;

		public PeekingIterator(Iterator<Integer> iterator) {
			// initialize any member here.
			iter = iterator;
			advanceIter();
		}

// Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
// you should confirm with interviewer what to return/throw if there are no more values
			return next;
		}

// hasNext() and next() should behave the same as in the Iterator interface.
		@Override
		public Integer next() {
			if (noSuchElement)
				throw new NoSuchElementException();
			Integer res = next;
			advanceIter();
			return res;
		}

		@Override
		public boolean hasNext() {
			return !noSuchElement;
		}

		private void advanceIter() {
			if (iter.hasNext()) {
				next = iter.next();
			} else {
				noSuchElement = true;
			}
		}
	}

	// Q345 Reverse Vowels of a string
	// Write a function that takes a string as input and reverse only the vowels
	// of a string.
	public String reverseVowels(String s) {
		if (s == null || s.length() == 0)
			return s;
		String vowels = "aeiouAEIOU";
		char[] chars = s.toCharArray();
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			while (start < end && !vowels.contains(chars[start] + "")) {
				start++;
			}
			while (start < end && !vowels.contains(chars[end] + "")) {
				end--;
			}
			char temp = chars[start];
			chars[start] = chars[end];
			chars[end] = temp;
			start++;
			end--;
		}

		return new String(chars);
	}

	// Q400 nth digit of a number #GoodQuestion #HardlyAsked
	// Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7,
	// 8, 9, 10, 11, ...
	// To make the problem much much more easier, I divide the problem into 3
	// parts:
	// calculate how many digits the number has.
	// calculate what the muber is.
	// find out which digit in the number is we want.
	// You can find the relative code part in the code section.
	// Here is an example to help you to understand my code:
	// Example:
	// Input: 250
	// After step 1, you will find that the 250th digit must belong to a 3-digit
	// number,since 1~9 can only provide 9 digits and 10~99 can only provide 180
	// digits.Here, n = 250 - 9 - 90 * 2 = 61, and digits = 3.
	// In step 2, we will find the target number, which named as number in my
	// code.
	// From step 1, the n becomes to 61, which means "the 61st digit in 3-digit
	// number is the digit
	// we are looking for ."Easily, we know the 61st digit in 3-digit number
	// belongs to number = 100
	// + 61 / 3 = 100 + 20 = 120.index is the index of the target digit in
	// number.
	// If index equals to 0, it means the target digit is the last digit of
	// number.
	// // Step 3, from step 2, we know index = n % digits = 61 % 3 = 1,
	// which means the target digit is the 1st digit in number. Then, return 1.
	// check the below method. for better understanding
	// https://www.youtube.com/watch?v=VKYnABegaEI
	public static int findNthDigit(int n) {
		// step 1. calculate how many digits the number has.
		long base = 9;
		int digits = 1;
		while (n - base * digits > 0) {
			n -= base * digits;
			base *= 10;
			digits++;
		}
		// step 2. calculate what the number is.
		int index = n % digits;
		if (index == 0)
			index = digits;
		long num = 1;
		for (int i = 1; i < digits; i++)
			num *= 10;
		num += (index == digits) ? n / digits - 1 : n / digits;

		// step 3. find out which digit in the number is we want.
		for (int i = index; i < digits; i++)
			num /= 10;
		return (int) num % 10;
	}

	// Straight forward way to solve the problem in 3 steps:
	// find the length of the number where the nth digit is from like 1-9 is 1
	// 10-99 is 2 and 100-999 is 3
	// find the actual number where the nth digit is from
	// find the nth digit and return
	public int findNthDigit2(int n) {
		int len = 1;
		long count = 9;
		int start = 1;

		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}

		// Step 2
		start += (n - 1) / len;
		// String s = Integer.toString(start);
		String num = Integer.toString(start);

		// Step 3
		int index = num.charAt((n - 1) % len);
		return Character.getNumericValue(index);
	}

	// Q67 Add Binary //the second solution is better
	// Given two binary strings, return their sum (also a binary string).
	// The input strings are both non-empty and contains only characters 1 or 0.
	public static void AddTwoBinaryString(String s1, String s2) {
		int i = -1;
		int j = -1;
		char ch1[] = s1.toCharArray();
		char ch2[] = s2.toCharArray();
		// just to make sure i will greater be j in terms of length so that in
		// the future computer can be done easily
		if (ch1.length > ch2.length) {
			i = ch1.length - 1;
			j = ch2.length - 1;
		} else {
			j = ch1.length - 1;
			i = ch2.length - 1;
		}
		int carry = 0;
		int sum[] = new int[ch1.length];
		int k = ch1.length - 1;
		while (j >= 0) {
			sum[k] = ((int) ch2[j] - 48) + carry;
			// sum[k] = Integer.valueOf(ch2[j] - '0') + carry; dont know if its
			// the correct one or not.
			if (i >= 0 && j >= 0) {
				sum[k] = ((int) ch1[i] - 48) + ((int) ch2[j] - 48) + carry;
			}
			if (sum[k] == 3) {
				sum[k] = 1;
				carry = 1;
			}
			if (sum[k] == 2) {
				sum[k] = 0;
				carry = 1;
			}
			if (sum[k] == 1) {
				sum[k] = 1;
				carry = 0;
			}
			if (sum[k] == 0) {
				sum[k] = 0;
				carry = 0;
			}
			System.out.print(sum[k]);
			i--;
			j--;
			k--;
		}
	}

	// or can be done by below way
	// Q67 Add binary #GoodQuestion
	// Given two binary strings, return their sum (also a binary string).
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1, carry = 0;
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (j >= 0)
				sum += b.charAt(j--) - '0';
			// '0' is for converting the char to its actual int value, otherwise
			// the returned int is its ascii value
			if (i >= 0)
				sum += a.charAt(i--) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0)
			sb.append(carry);
		return sb.reverse().toString();
	}

	// Q415 Add Strings #GoodQuestion
	// Given two non-negative integers num1 and num2 represented as string,
	// return the sum of num1 and num2.
	// Note:
	// The length of both num1 and num2 is < 5100.
	// Both num1 and num2 contains only digits 0-9.
	// Both num1 and num2 does not contain any leading zero.
	// second solution is better
	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
			int x = i < 0 ? 0 : num1.charAt(i) - '0';
			int y = j < 0 ? 0 : num2.charAt(j) - '0';
			sb.append((x + y + carry) % 10);
			carry = (x + y + carry) / 10;
		}
		return sb.reverse().toString();
	}

	// same as we have added two binary elements ..only difference is instead of
	// % and / by 2 we are doing that by 10
	public String addStrings2(String a, String b) {

		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1, carry = 0;
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (j >= 0)
				sum += b.charAt(j--) - '0';
			// '0' is for converting the char to its actual int value, otherwise
			// the returned int is its ascii value
			if (i >= 0)
				sum += a.charAt(i--) - '0';
			sb.append(sum);
			carry = sum / 10;
		}
		if (carry != 0)
			sb.append(carry);
		return sb.reverse().toString();
	}

// Q287 find the duplicate number #TopInterviewQuestion
// Given an array nums containing n + 1 integers where each integer is
// between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one
// duplicate number, find the duplicate one. You must not modify the array (assume the array is read only).
// You must use only constant, O(1) extra space. Your runtime complexity should be less than O(n2).
// both the below solutions are amazing

	// amazing solution using binary search
	// At first the search space is numbers between 1 to n. Each time I select a
	// number mid (which is the one in the middle)
	// and count all the numbers equal to or less than mid. Then if the count is
	// more than mid, the search space will be [1 mid]
	// otherwise [mid+1 n]. I do this until search space is only one number.
	public int findDuplicate(int[] nums) {
		int n = nums.length - 1;
		int left = 0, right = n;
		while (left < right) {
			int mid = left + (right - left) / 2;
			int count = numBelow(nums, mid);
			if (count > mid)
				right = mid;
			else
				left = mid + 1;
		}
		return left;
	}

	public int numBelow(int[] nums, int target) {
		int result = 0;
		for (int i = 0; i < nums.length; i++)
			if (nums[i] <= target)
				result++;
		return result;
	}

// we must choose the last element as the head of the linked list. If we choose 0, we can not detect the cycle.
// Now the problem is the same as find the cycle in linkedlist!

	public int findDuplicate2(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < nums.length; i++)
			nums[i]--;
		int slow = n - 1;
		int fast = n - 1;
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		slow = n - 1;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow + 1;
	}

// if asked for the above solution that you cannot modify then the solution will be like this
	public int findDuplicate3(int[] nums) {
		int n = nums.length;
		int slow = n;
		int fast = n;
		do {
			slow = nums[slow - 1];
			fast = nums[nums[fast - 1] - 1];
		} while (slow != fast);
		slow = n;
		while (slow != fast) {
			slow = nums[slow - 1];
			fast = nums[fast - 1];
		}
		return slow;
	}

	// this i am assuming is the best solution as everything starts from 0
	public int findDuplicate4(int[] nums) {
		int slow = 0, fast = 0;
		do {
			slow = nums[slow];
			fast = nums[nums[fast]];
		} while (slow != fast);
		slow = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}

	// next prime number
	public static int nextPrime(int n) {
		boolean isPrime = false;
		int start = 2;
		while (!isPrime) {
			n = n + 1;
			int m = (int) Math.ceil(Math.sqrt(n));
			isPrime = true;
			for (int i = start; i <= m; i++) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
			}
		}
		// next prime number is
		return n;
	}

	// Q204 Count Primes #TopInterviewQuestion
	// the below two method is useful for counting the number of primes lesser
	// than the given number
	public static int countNumberofPrime(int n) {
		int start = 2;
		int count = 0;
		for (int i = start; i < n; i++) {
			if (isPrime(i)) {
				count++;
			}
		}
		return count;
	}

	public static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false; // number is divisible so its not prime
			}
		}
		return true; // number is prime now
	}

	// Prime Factors //Prime factorization
	public static List<Integer> primeFactors(int n) {

		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
	}

	// Although the above method can also be used for printing the primes
	// sieveOfEratosthenes is for Given a number n, print all primes smaller
	// than or equal to n.
	// It is also given that n is a small number.
	public void sieveOfEratosthenes(int n) {
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i < n; i++)
			prime[i] = true;
		for (int p = 2; p * p <= n; p++) { // If prime[p] is not changed, then
											// it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
		// Print all prime numbers
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true)
				System.out.print(i + " ");
		}
	}

	// Q226 Invert binary tree
	// Note this tree doesn't traverse it from bottom to top
	// it just inverts it
	// 4
	// / \
	// 2 7
	// / \ / \
	// 1 3 6 9
	// to
	// 4
	// / \
	// 7 2
	// / \ / \
	// 9 6 3 1
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return null;
		TreeNode tmp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(tmp);
		return root;
	}

	// one more method is given on leetcode to do the same //no need to do the
	// recursion method.
	// question is hardly been asked
	public TreeNode invertTree2(TreeNode root) {
		if (root == null) {
			return null;
		}
		final Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			final TreeNode node = stack.pop();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		return root;
	}

	// one more method given on leetcode to invert the tree
	public TreeNode invertTree3(TreeNode root) {
		if (root == null) {
			return null;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			final TreeNode node = queue.poll();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return root;
	}

	// Q257 Binary Tree Paths
	// Given a binary tree, return all root-to-leaf paths.
	// For example, given the following binary tree:
	// 1
	// / \
	// 2 3
	// \
	// 5
	// All root-to-leaf paths are:
	//
	// ["1->2->5", "1->3"]
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> answer = new ArrayList<String>();
		if (root != null)
			searchBT(root, "", answer);
		return answer;
	}

	private void searchBT(TreeNode root, String path, List<String> answer) {
		if (root.left == null && root.right == null)
			answer.add(path + root.val);
		if (root.left != null)
			searchBT(root.left, path + root.val + "->", answer);
		if (root.right != null)
			searchBT(root.right, path + root.val + "->", answer);
	}

	// Above question without recursion
	public List<String> binaryTreePathsNonRecursive(TreeNode root) {
		List<String> ret = new ArrayList<>();
		if (root == null) {
			return ret;
		}

		Stack<TreeNode> stack = new Stack<>();
		Stack<String> path_stack = new Stack<>();
		stack.push(root);
		path_stack.push(root.val + "");
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			String path = path_stack.pop();

			if (node.right == null && node.left == null) {
				ret.add(path);
			} else {
				if (node.left != null) {
					stack.push(node.left);
					path_stack.push(path + "->" + node.left.val);
				}
				if (node.right != null) {
					stack.push(node.right);
					path_stack.push(path + "->" + node.right.val);
				}
			}
		}
		return ret;
	}

	// Q278 first bad version
	public static int firstBadVersion(int n) {
		int start = 1, end = n;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (!isBadVersion(mid))
				start = mid + 1;
			else
				end = mid;
		}
		return start;
	}

	public static boolean isBadVersion(int x) {
		// an api which will return whether version is bad.
		return false;
	}

// Q263 Write a program to check whether a given number is an ugly number.
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
// For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
// Note that 1 is typically treated as an ugly number.

	public boolean uglynumber(int num) {
		for (int i = 2; i < 6 && num > 0; i++)
			while (num % i == 0)
				num /= i;
		return num == 1;
	}

// Q264 ugly number ii
// Write a program to find the n-th ugly number.
// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
// For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
// Note that 1 is typically treated as an ugly number, and n does not exceed// 1690.

	public int nthUglyNumber(int n) {
		int[] uglyNumbers = new int[n];
		uglyNumbers[0] = 1;
		int index2 = 0, index3 = 0, index5 = 0;

		for (int i = 1; i < n; i++) {
			// generate ugly number by multiply all the factors
			uglyNumbers[i] = Math.min(uglyNumbers[index2] * 2,
					Math.min(uglyNumbers[index3] * 3, uglyNumbers[index5] * 5));

			// bump up index for the current minimum ugly number
			if (uglyNumbers[i] == uglyNumbers[index2] * 2)
				index2++;
			if (uglyNumbers[i] == uglyNumbers[index3] * 3)
				index3++;
			if (uglyNumbers[i] == uglyNumbers[index5] * 5)
				index5++;
		}

		return uglyNumbers[n - 1];
	}

	// Q292 Nim Game
	// heap of stones on the table, each time one of you take turns to remove 1
	// to 3 stones.
	// The one who removes the last stone will be the winner. You will take the
	// first turn to remove the stones.
	boolean canWinNim(int n) {
		return n % 4 != 0;
	}

	// Q3 Given a string, find the length of the longest substring without
	// repeating characters. #TopInterviewQuestion
	// below method will take O(n) complexity
	// This method is the best method
	public int lengthOfLongestSubstring2(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<>();
		int max = 0;
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	public int lengthOfLongestSubstring(String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int max = 0;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				count = 0;
				i = map.get(s.charAt(i)); // getting the old location and clearing the whole hashmap and starting again from that index
				map.clear(); // this itself takes O(n) so the total would be O(n square)
			} else {
				map.put(s.charAt(i), i);
				count++;
				if (count > max)
					max = count;
			}
		}
		return max;
	}

	// can do the above same thing using ascii solution
	public int lengthOfLongestSubstring3(String s) {

		int len = s.length(), max = 0;
		int[] chars = new int[128];

		for (int i = 0, j = 0; i < len; i++) {
			j = Math.max(j, chars[s.charAt(i)]);
			max = Math.max(max, i - j + 1);
			chars[s.charAt(i)] = i + 1;
		}
		return max;
	}

	public int lengthOfLongestSubstring4(String s) {
		int len = s.length();
		HashSet<Character> set = new HashSet<>();
		int i = 0, j = 0, max = 0;
		while (i < len && j < len) {
			if (!set.contains(s.charAt(i))) {
				set.add(s.charAt(i++));
				max = Math.max(max, i - j);
			} else {
				set.remove(s.charAt(j++));
			}
		}
		return max;

	}

	// Q6 zig zag conversion .. zigzag conversion
	// interesting question #GoodQuestion
	public static String zigzagconvert(String s, int numRows) {
		if (numRows <= 1)
			return s;
		StringBuilder[] sb = new StringBuilder[numRows];
		for (int i = 0; i < sb.length; i++) {
			sb[i] = new StringBuilder(""); // init every sb element **important step!!!!
		}
		int incre = 1;
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			sb[index].append(s.charAt(i));
			if (index == 0) {
				incre = 1;
			}
			if (index == numRows - 1) {
				incre = -1;
			}
			index += incre;
		}
		String re = "";
		for (int i = 0; i < sb.length; i++) {
			re += sb[i];
		}
		return re.toString();
	}

	// Q15 3Sum three sum #TopInterviewQuestion
	// Given an array S of n integers, are there elements a, b, c in S such that
	// a + b + c = 0?
	// Find all unique triplets in the array which gives the sum of zero.
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 3)
			return result;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] > nums[i - 1]) {
				int j = i + 1;
				int k = nums.length - 1;
				while (j < k) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						List<Integer> l = new ArrayList<>();
						l.add(nums[i]);
						l.add(nums[j]);
						l.add(nums[k]);
						result.add(l);
						j++;
						k--;
						// handle duplicate here
						while (j < k && nums[j] == nums[j - 1])
							j++;
						while (j < k && nums[k] == nums[k + 1])
							k--;

					} else if (nums[i] + nums[j] + nums[k] < 0) {
						j++;
					} else {
						k--;
					}
				}
			}
		}
		return result;
	}

	// Q16 3sum closest three sum closest
	public int threeSumClosest(int[] nums, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				int diff = Math.abs(sum - target);
				if (diff == 0)
					return sum;
				if (diff < min) {
					min = diff;
					result = sum;
				}
				if (sum <= target) {
					j++;
				} else {
					k--;
				}
			}
		}
		return result;
	}

	// Q18 4sum four sum
	public List<List<Integer>> fourSum(int[] num, int target) {
		ArrayList<List<Integer>> ans = new ArrayList<>();
		if (num.length < 4)
			return ans;
		Arrays.sort(num);
		for (int i = 0; i < num.length - 3; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			for (int j = i + 1; j < num.length - 2; j++) {
				if (j > i + 1 && num[j] == num[j - 1])
					continue;
				int low = j + 1, high = num.length - 1;
				while (low < high) {
					int sum = num[i] + num[j] + num[low] + num[high];
					if (sum == target) {
						ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
						while (low < high && num[low] == num[low + 1])
							low++;
						while (low < high && num[high] == num[high - 1])
							high--;
						low++;
						high--;
					} else if (sum < target)
						low++;
					else
						high--;
				}
			}
		}
		return ans;
	}

	// Q48 Rotate Image #TopInterviewQuestion
	// The idea was firstly transpose the matrix and then flip it symmetrically
	// 1 2 3      1 4 7                         7 4 1
	// 4 5 6 ---> 2 5 8 (after transpose) --- > 8 5 2
	// 7 8 9      3 6 9                         9 6 3
	public void rotate(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int temp = 0;
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length / 2; j++) {
				int temp = 0;
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length - 1 - j] = temp;
			}
		}
	}

	public static void rotateRightBy90(int[][] arr) {
		int m = arr.length;
		int[][] res = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				res[j][m - 1 - i] = arr[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = res[i][j];
			}
		}
	}

	public static void rotateLeftBy90(int[][] arr) {
		int m = arr.length;
		int[][] res = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				res[i][j] = arr[j][m - 1 - i];
				res[j][m - 1 - i] = arr[i][j];
			}
		}
	}

	// Q17 letter combination of a phone number #TopInterviewQuestion
	// this is how it works for the string 23
	// i=0 -> result=combine("abc", [""]) ---> [a,b,c];
	// i=1 -> result=combine("def", [a,b,c]) ---> [ad,bd,cd, ae,be,ce,
	// af,bf,cf];
	public static ArrayList<String> letterCombinations(String digits) {
		ArrayList<String> res = new ArrayList<>();
		String[] charmap = { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		res.add("");
		System.out.println(res.size());
		for (int i = 0; i < digits.length(); i++) {
			ArrayList<String> tempres = new ArrayList<>();
			String chars = charmap[digits.charAt(i) - '0'];
			for (int c = 0; c < chars.length(); c++)
				for (int j = 0; j < res.size(); j++)
					tempres.add(res.get(j) + chars.charAt(c));
			res = tempres;
		}
		return res;
	}

	// BackTracking solution for the above question //this method is good
	private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public List<String> letterCombinations2(String digits) {
		if (digits.equals(""))
			return new ArrayList<>();
		List<String> ret = new LinkedList<>();
		combination("", digits, 0, ret);
		return ret;
	}

	private void combination(String prefix, String digits, int offset, List<String> ret) {
		if (offset >= digits.length()) {
			ret.add(prefix);
			return;
		}
		String letters = KEYS[(digits.charAt(offset) - '0')];
		for (int i = 0; i < letters.length(); i++) {
			combination(prefix + letters.charAt(i), digits, offset + 1, ret);
		}
	}

	// Q339 Nested List Weight Sum
	// Given a nested list of integers, return the sum of all integers in the
	// list weighted by their depth.
	// Each element is either an integer, or a list -- whose elements may also
	// be integers or other lists.
	// Example 1:Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth
	// 2, one 2 at depth 1)
	public int depthSum(List<NestedInteger> nestedList) {
		return helper(nestedList, 1);
	}

	public int helper(List<NestedInteger> nestedList, int depth) {
		if (nestedList == null || nestedList.size() == 0)
			return 0;
		int sum = 0;
		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				sum += ni.getInteger() * depth;
			} else {
				sum += helper(ni.getList(), depth + 1);
			}
		}
		return sum;
	}

	// Iterative nested list weight sum
	public int depthSum(ArrayList<NestedInteger> nestedList) {
		int level = 1, total = 0;
		while (nestedList.size() != 0) {
			List<NestedInteger> next = new LinkedList<>();
			for (NestedInteger nInt : nestedList) {
				if (nInt.isInteger())
					total += nInt.getInteger() * level;
				else
					next.addAll(nInt.getList());
			}
			level++;
			nestedList = (ArrayList<NestedInteger>) next;
		}
		return total;
	}

	// Q222 Count complete tree nodes
	// Given a complete binary tree, count the number of nodes.
	int countNodes(TreeNode root) {
		if (root == null)
			return 0;
		int hl = 0, hr = 0;
		TreeNode l = root, r = root;
		while (l != null) {
			hl++;
			l = l.left;
		}
		while (r != null) {
			hr++;
			r = r.right;
		}
		if (hl == hr)
			return (int) (Math.pow(2, hl) - 1);
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	// Q49 group anagrams #TopInterviewQuestion
	public static List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			if (!map.containsKey(keyStr))
				map.put(keyStr, new ArrayList<String>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<List<String>>(map.values());
	}

	// Q66 plus one #TopInterviewQuestion
	// Given a non-negative integer represented as a non-empty array of digits,
	// plus one to the integer.
	// You may assume the integer do not contain any leading zero, except the
	// number 0 itself.
	public static int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}
		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;
		return newNumber;
	}

// Q50 Implement pow(x,n) #TopInterviewQuestion
	public static double pow(double x, int n) {
		if (n == 0)
			return 1.;
		if (n < 0) {
			n = -n;
			x = 1 / x;
		}

		if (Double.isInfinite(x)) // to avoid the overflow condition
			return 0.;
		return (n % 2 == 0) ? pow(x * x, n / 2) : x * pow(x * x, n / 2);
	}

	// Q43 Multiply String
	// Given two non-negative integers num1 and num2 represented as strings,
	// return the product of num1 and num2, also represented as a string.
	public static String multiply(String num1, String num2) {
		int[] sum = new int[num1.length() + num2.length()];
		for (int i = num1.length() - 1; i >= 0; i--) {
			int carry = 0;
			for (int j = num2.length() - 1; j >= 0; j--) {
				int tmp = (sum[i + j + 1]) + (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
				sum[i + j + 1] = tmp % 10;
				carry = tmp / 10;
			}
			sum[i] += carry;
		}

		// below lines are only for trimming zero in the beginning of the number
		StringBuilder sb = new StringBuilder();
		for (int i : sum)
			if (sb.length() > 0 || i > 0)
				sb.append(i);
		return (sb.length() == 0) ? "0" : sb.toString();
	}

// Q74 Search a 2D Matrix -- Q240 Search a 2D Matrix || #TopInterviewQuestion
// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
// Integers in each row are sorted from left to right. The first integer of each row is greater than the last integer
// of the previous row.

// start from top-rightmost element in matrix (matrix[0][n-1]) and remove row if target is greater than matrix[0][n-1]
// or remove column if target is lesser than matrix[0][n-1]. repeat step 1 until you find the element!

	// this solution works for both the questions
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
			return false;
		}
		int col = matrix[0].length - 1;
		int row = 0;
		while (col >= 0 && row <= matrix.length - 1) {
			if (target == matrix[row][col]) {
				return true;
			} else if (target < matrix[row][col]) {
				col--;
			} else if (target > matrix[row][col]) {
				row++;
			}
		}
		return false;
	}

	// Q55 jump game #TopInterviewQuestion
	// Given an array of non-negative integers, you are initially positioned at the first index of the array.
	// Each element in the array represents your maximum jump length at that position.
	// Determine if you are able to reach the last index.
	// The basic idea is this: at each step, we keep track of the furthest reachable index.
	// The nature of the problem (eg. maximal jumps where you can hit a range of
	// targets instead of singular jumps where you can only hit one target) is
	// that for an index to be reachable,each of the previous indices have to be reachable.
	// Hence, it suffices that we iterate over each index,
	// and If we ever encounter an index that is not reachable, we abort and return false.
	// By the end, we will have iterated to the last index. If the loop finishes, then the last index is reachable.
	// Given an array of non-negative integers, you are initially positioned at the first index of the array.
	// Each element in the array represents your maximum jump length at that position.
	// Determine if you are able to reach the last index.
	// For example:
	// A = [2,3,1,1,4], return true.
	// A = [3,2,1,0,4], return false.
	public boolean canJump(int[] nums) {
		int n = nums.length, reachablesofar = 0;
		for (int i = 0; i < n; i++) {
			if (reachablesofar < i)
				return false;
			reachablesofar = Math.max(reachablesofar, i + nums[i]);
			if (reachablesofar >= n - 1)
				return true;
		}
		return false;
	}

	// Q71 Simplify Path
	// Given an absolute path for a file (Unix-style), simplify it.For example,
	// path = "/home/", => "/home" .... path = "/a/./b/../../c/", => "/c"
	// The main idea is to push to the stack every valid file name (not in
	// {"",".",".."}),
	// popping only if there's smth to pop and we met "..".
	public String simplifyPath(String path) {
		Deque<String> stack = new LinkedList<>();

		Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
		for (String dir : path.split("/")) {
			if (dir.equals("..") && !stack.isEmpty())
				stack.pop();
			else if (!skip.contains(dir))
				stack.push(dir);
		}
		String res = "";
		for (String dir : stack)
			res = "/" + dir + res;
		return res.isEmpty() ? "/" : res;
	}

	// Q73 Set Matrix Zeroes #TopInterviewQuestion
	// Given a m x n matrix, if an element is 0, set its entire row and column
	// to 0. Do it in place.
	public static void setZero(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];
		Arrays.fill(row, false);
		Arrays.fill(column, false);
		int m = matrix.length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					column[j] = true;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (row[i] || column[j]) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	// Q75 sort colors #TopInterviewQuestion
	// Given an array with n objects colored red, white or blue, sort them so
	// that objects of the same color
	// are adjacent, with the colors in the order red, white and blue. Here, we
	// will use the integers 0, 1, and 2
	// to represent the color red, white, and blue respectively.

	// The idea is to sweep all 0s to the left and all 2s to the right, then all
	// 1s are left in the middle.
	//second solution is the better solution
	void sortColors(int A[], int n) {
		int second = n - 1, zero = 0;
		for (int i = 0; i <= second; i++) {
			while (A[i] == 2 && i < second)
				swap(A[i], A[second--]);
			while (A[i] == 0 && i > zero)
				swap(A[i], A[zero++]);
		}
	}

	public static void swap(int i, int j) {
	}

	public void sortColors2(int[] nums) {
		// 2-pass
		int count0 = 0, count1 = 0, count2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count0++;
			}
			if (nums[i] == 1) {
				count1++;
			}
			if (nums[i] == 2) {
				count2++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (i < count0) {
				nums[i] = 0;
			} else if (i < count0 + count1) {
				nums[i] = 1;
			} else {
				nums[i] = 2;
			}
		}
	}

	// Q61 Rotate List
	// Given a linked list, rotate the list to the right by k places, where k is
	// non-negative.
	// My solution has O(n) time complexity and O(1) memory. The basic idea is to connect the list into a circle.
	// First, count the length of list while going through the list to find the end of it.
	// Connect the tail to head. The problem asked to rotate k nodes, however, now the tail is at the end of the list
	// and its difficult to move backward, so move (k - len) nodes along the
	// list instead. "k = k % len" saves the unnecessary moves because rotate a list with length = len by
	// len times doesn't change the list at all.
	ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null || k == 0)
			return head;
		int len = 1;
		ListNode tail = head;

		/* find the end of list */
		while (tail.next != null) {
			tail = tail.next;
			len++;
		}

		/* form a circle */
		tail.next = head;
		k = k % len;
		for (int i = 0; i < len - k; i++) {
			tail = tail.next;
		}
		head = tail.next;
		tail.next = null;
		return head;
	}

	// Q116 populating next right pointers in each node #TopInterviewQuestion
	// both of the below methods are good
	// perfect binary tree..that means every parent has two children
	public void connect3(TreeLinkNode root) {
		if (root == null)
			return;
		TreeLinkNode pre = root, cur;
		while (pre.left != null) {
			cur = pre;
			while (cur.next != null) {
				cur.left.next = cur.right;
				cur.right.next = cur.next.left;
				cur = cur.next;
			}
			cur.left.next = cur.right;
			pre = pre.left;
		}
	}

	// the above method done non-recursively
	public void connectRecursive(TreeLinkNode root) {
		if (root == null)
			return;
		if (root.left != null) {
			root.left.next = root.right;
			if (root.next != null)
				root.right.next = root.next.left;
		}

		connectRecursive(root.left);
		connectRecursive(root.right);
	}

	// recursive approach
	// Q117 populating next right pointers in each node 2
// doesnt need to be a perfect binary tree
//very well explained by iDeserve https://www.youtube.com/watch?v=G46cenlnXvI&t=478s

	// below method gives null pointer exception
	void connect4RecursiveApproach(TreeLinkNode root) {
		if (root == null)
			return;

		if (root.left != null) {
			// root.left.next = root.right != null ? root.right :
			// findNext(root.next);
			if (root.right != null)
				root.left.next = root.right;
			else
				root.left.next = findNext(root.next);
		}
		if (root.right != null)
			root.right.next = findNext(root.next);

		connect4RecursiveApproach(root.right);
		connect4RecursiveApproach(root.left);
	}

	TreeLinkNode findNext(TreeLinkNode curr) {
		if (curr != null)
			return null;

		if (curr.left != null)
			return curr.left;
		if (curr.right != null)
			return curr.right;

		return findNext(curr.next);

	}

	// based on level order traversal
	public void connect5(TreeLinkNode root) {
		TreeLinkNode head = null; // head of the next level
		TreeLinkNode prev = null; // the leading node on the next level
		TreeLinkNode cur = root; // current node of current level
		while (cur != null) {
			while (cur != null) { // iterate on the current level
				// left child
				if (cur.left != null) {
					if (prev != null) {
						prev.next = cur.left;
					} else {
						head = cur.left;
					}
					prev = cur.left;
				}
				// right child
				if (cur.right != null) {
					if (prev != null) {
						prev.next = cur.right;
					} else {
						head = cur.right;
					}
					prev = cur.right;
				}
				// move to next node
				cur = cur.next;
			}
			// move to next level
			cur = head;
			head = null;
			prev = null;
		}
	}

	public void connect(TreeLinkNode root) {

		while (root != null) {
			TreeLinkNode tempChild = new TreeLinkNode(0);
			TreeLinkNode currentChild = tempChild;
			while (root != null) {
				if (root.left != null) {
					currentChild.next = root.left;
					currentChild = currentChild.next;
				}
				if (root.right != null) {
					currentChild.next = root.right;
					currentChild = currentChild.next;
				}
				root = root.next;
			}
			root = tempChild.next;
		}
	}

	// Q138 Copy List with random pointer #TopInterviewQuestion
	// linked list is given such that each node contains an additional random
	// pointer which could point to any node
	// in the list or null. Return a deep copy of the list.

	// We can solve this problem by doing the following steps:
	// copy every node, i.e., duplicate every node, and insert it to the list
	// copy random pointers for all newly created nodes
	// break the list to two
	// Definition for singly-linked list with a random pointer.
	// class RandomListNode {
	// int label;
	// RandomListNode next, random;
	// RandomListNode(int x) { this.label = x; }
	// };
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		RandomListNode p = head;

		// copy every node and insert to list
		while (p != null) {
			RandomListNode copy = new RandomListNode(p.value);
			copy.next = p.next;
			p.next = copy;
			p = copy.next;
		}

		// copy random pointer for each new node
		p = head;
		while (p != null) {
			if (p.random != null)
				p.next.random = p.random.next;
			p = p.next.next;
		}
		// break list to two
		p = head;
		RandomListNode newHead = head.next;
		while (p != null) {
			RandomListNode temp = p.next;
			p.next = temp.next;
			if (temp.next != null)
				temp.next = temp.next.next;
			p = p.next;
		}
		return newHead;
	}

// Q419 battleships in the board #GoodQuestion
// Solution : Going over all cells, we can count only those that are the "first" cell of the battleship.
// First cell will be defined as the most top-left cell. We can check for first cells by only counting cells
// that do not have an 'X' to the left and do not have an 'X' above them

	public int countBattleships(char[][] board) {
		int m = board.length;
		if (m == 0)
			return 0;
		int n = board[0].length;
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.')
					continue;
				if (i > 0 && board[i - 1][j] == 'X')
					continue;
				if (j > 0 && board[i][j - 1] == 'X')
					continue;
				count++;
			}
		}
		return count;
	}

	// Q273 Integer to English words #TopInterviewQuestion .. #GoodQuestion
	public class Solution {
		private final String[] belowTen = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
				"Eight", "Nine" };
		private final String[] belowTwenty = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
				"Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		private final String[] belowHundred = new String[] { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
				"Seventy", "Eighty", "Ninety" };

		public String numberToWords(int num) {
			if (num == 0)
				return "Zero";
			return helper(num);
		}

		private String helper(int num) {
			String result;
			if (num < 10)
				result = belowTen[num];
			else if (num < 20)
				result = belowTwenty[num - 10];
			else if (num < 100)
				result = belowHundred[num / 10] + " " + helper(num % 10);
			else if (num < 1000)
				result = helper(num / 100) + " Hundred " + helper(num % 100);
			else if (num < 1000000)
				result = helper(num / 1000) + " Thousand " + helper(num % 1000);
			else if (num < 1000000000)
				result = helper(num / 1000000) + " Million " + helper(num % 1000000);
			else
				result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
			return result.trim();
		}
	}

	// Q143 reorder list
	// Given a singly linked list L: L0→L1→…→Ln-1→Ln,
	// reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
	// You must do this in-place without altering the nodes' values.
	// For example,
	// Given {1,2,3,4}, reorder it to {1,4,2,3}.
	// This question is a combination of Reverse a linked list I & II. Second
	// method is a better one
	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;

		// Find the middle of the list
		ListNode p1 = head;
		ListNode p2 = head;
		while (p2.next != null && p2.next.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
		}

		// Reverse the half after middle 1->2->3->4->5->6 to 1->2->3->6->5->4
		ListNode preMiddle = p1;
		ListNode preCurrent = p1.next;
		while (preCurrent.next != null) {
			ListNode current = preCurrent.next;
			preCurrent.next = current.next;
			current.next = preMiddle.next;
			preMiddle.next = current;
		}

		// Start reorder one by one 1->2->3->6->5->4 to 1->6->2->5->3->4
		p1 = head;
		p2 = preMiddle.next;
		while (p1 != preMiddle) {
			preMiddle.next = p2.next;
			p2.next = p1.next;
			p1.next = p2;
			p1 = p2.next;
			p2 = preMiddle.next;
		}
	}

	// Another way of doing the same method
	public void reorderListanotherway(ListNode head) {
		if (head == null || head.next == null)
			return;
		// step 1. cut the list to two halves
		// prev will be the tail of 1st half
		// slow will be the head of 2nd half
		ListNode prev = null, slow = head, fast = head, l1 = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		// step 2. reverse the 2nd half
		ListNode l2 = reverseit(slow);
		// step 3. merge the two halves
		mergeit(l1, l2);
	}

	ListNode reverseit(ListNode head) {
		ListNode prev = null, curr = head, next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	void mergeit(ListNode l1, ListNode l2) {
		while (l1 != null) {
			ListNode n1 = l1.next, n2 = l2.next;
			l1.next = l2;
			if (n1 == null)
				break;
			l2.next = n1;
			l1 = n1;
			l2 = n2;
		}
	}

	// Q147 sort a linked list using insertion sort //dint get it
	public ListNode insertionSortList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode helper = new ListNode(0); // new starter of the sorted list
		ListNode cur = head; // the node will be inserted
		ListNode pre = helper; // insert node between pre and pre.next
		ListNode next = null; // the next node will be inserted
		// not the end of input list
		while (cur != null) {
			next = cur.next;
			// find the right place to insert
			while (pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
			}
			// insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		return helper.next;
	}

	// Q150 Evaluate Reverse polish notation #TopInterviewQuestion
	public int evalRPN(String[] tokens) {
		int a, b;
		Stack<Integer> S = new Stack<Integer>();
		for (String s : tokens) {
			if (s.equals("+")) {
				S.add(S.pop() + S.pop());
			} else if (s.equals("/")) {
				b = S.pop();
				a = S.pop();
				S.add(a / b);
			} else if (s.equals("*")) {
				S.add(S.pop() * S.pop());
			} else if (s.equals("-")) {
				b = S.pop();
				a = S.pop();
				S.add(a - b);
			} else {
				S.add(Integer.parseInt(s));
			}
		}
		return S.pop();
	}

	// Q91 Decode Ways #TopInterviewQuestion
	// A message containing letters from A-Z is being encoded to numbers using
	// the following mapping:
	// 'A' -> 1
	// 'B' -> 2
	// ...
	// 'Z' -> 26
	// Given an encoded message containing digits, determine the total number of
	// ways to decode it.
	// For example, Given encoded message "12", it could be decoded as "AB" (1
	// 2) or "L" (12).
	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0 || s.charAt(0) == '0')
			return 0;
		if (n == 1)
			return 1;
		int m1 = 1, m2 = 1, num = 0;
		for (int i = 1; i < n; i++) {
			num = 0;
			if (!isValid(s.charAt(i)) && !isValid(s.charAt(i - 1), s.charAt(i)))
				return 0;
			if (isValid(s.charAt(i)))
				num = m1;
			if (isValid(s.charAt(i - 1), s.charAt(i)))
				num += m2;
			m2 = m1;
			m1 = num;
		}
		return num;
	}

	boolean isValid(char a, char b) {
		return a == '1' || (a == '2' && b <= '6');
	}

	boolean isValid(char a) {
		return a != '0';
	}

	// Q162 find peak element #TopInterviewQuestion
	// since we have to find only 1 peak element, it is very obvious that using
	// binary search we can achieve that
	// if a[mid]< a[mid-1] that means, we have atleast one element on the left
	// which is a peak element
	// else element is on the right this way complexity will be reduced to logn
	// second/third method is better ...explained the concept before the third
	// method
	public static Integer getPeakElement(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		int n = array.length;
		int start = 0;
		int end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) {
				return array[mid];
			} else if (mid > 0 && array[mid - 1] > array[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return null;
	}

	// or this one is a better method
	int findPeakElement(int[] num) {
		int low = 0;
		int high = num.length - 1;

		while (low < high) {
			int mid1 = (low + high) / 2;
			int mid2 = mid1 + 1;
			if (num[mid1] < num[mid2])
				low = mid2;
			else
				high = mid1;
		}
		return low;
	}

	// Best Solution So Far
	// This problem is similar to Local Minimum. And according to the given
	// condition, num[i] != num[i+1],
	// there must exist a O(logN) solution. So we use binary search for this
	// problem.
	// If num[i-1] < num[i] > num[i+1], then num[i] is peak
	// If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
	// If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
	// If num[i-1] > num[i] < num[i+1], then both sides have peak
	// (n is num.length)
	public int findPeakElement2(int[] num) {
		return helper2(num, 0, num.length - 1);
	}

	public int helper2(int[] num, int start, int end) {
		if (start == end) {
			return start;
		} else if (start + 1 == end) {
			if (num[start] > num[end])
				return start;
			return end;
		} else {

			int m = (start + end) / 2;

			if (num[m] > num[m - 1] && num[m] > num[m + 1]) {

				return m;

			} else if (num[m - 1] > num[m] && num[m] > num[m + 1]) {

				return helper2(num, start, m - 1);

			} else {

				return helper2(num, m + 1, end);

			}

		}
	}

// Q64 Minimum path sum in a matrix #GoodQuestion
// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which
// minimizes the sum of all numbers along its path.
// Note: You can only move either down or right at any point in time.
// Example 1:
// [[1,3,1],
// [1,5,1],
// [4,2,1]]
// Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
	public int minPathSum(int[][] grid) {
		int m = grid.length;// row
		int n = grid[0].length; // column
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j != 0) {
					grid[i][j] = grid[i][j] + grid[i][j - 1];
				} else if (i != 0 && j == 0) {
					grid[i][j] = grid[i][j] + grid[i - 1][j];
				} else if (i == 0 && j == 0) {
					grid[i][j] = grid[i][j];
				} else {
					grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
				}
			}
		}
		return grid[m - 1][n - 1];
	}

	// Q365 Water & Jug Problem
	// This is a pure Math problem. We need the knowledge of number theory to
	// cover the proof and solution.
	// The basic idea is to use the property of Bézout's identity and check if z
	// is a multiple of GCD(x, y)
	// let a and b be nonzero integers and let d be their greatest common
	// divisor. Then there exist integers x and y such that ax+by=d
	// In addition, the greatest common divisor d is the smallest positive
	// integer that can be written as ax + by
	// every integer of the form ax + by is a multiple of the greatest common
	// divisor d.If a or b is negative this means we are emptying a jug of x or
	// y gallons
	// respectively.Similarly if a or b is positive this means we are filling a
	// jug of x or y gallons respectively.
	// x = 4, y = 6, z = 8.
	// GCD(4, 6) = 2
	// 8 is multiple of 2
	// so this input is valid and we have:
	// -1 * 4 + 6 * 2 = 8
	// In this case, there is a solution obtained by filling the 6 gallon jug
	// twice and emptying the 4 gallon jug once. (Solution. Fill the 6 gallon
	// jug and empty 4 gallons to the 4 gallon jug. Empty the 4 gallon jug. Now
	// empty the remaining two gallons from the 6 gallon jug to the 4 gallon
	// jug. Next refill the 6 gallon jug. This gives 8 gallons in the end)
	public boolean canMeasureWater(int x, int y, int z) {
		// limit brought by the statement that water is finallly in one or both
		// buckets
		if (x + y < z)
			return false;
		// case x or y is zero
		if (x == z || y == z || x + y == z)
			return true;
		// get GCD, then we can use the property of Bézout's identity
		return z % GCD(x, y) == 0;
	}

	public int GCD(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	// Q333 largest BST in a binary tree #hardlyasked
	// concept is to use postorder traversal
	// and make sure min of right side > root and max of left side should be <
	// than root to get the bst
	public class LargestBSTInBinaryTree {

		public int largestBST(Node root) {
			MinMax m = largest(root);
			return m.size;
		}

		private MinMax largest(Node root) {
			// if root is null return min as Integer.MAX and max as Integer.MIN
			if (root == null) {
				return new MinMax();
			}

			// postorder traversal of tree. First visit left and right then
			// use information of left and right to calculate largest BST.
			MinMax leftMinMax = largest(root.left);
			MinMax rightMinMax = largest(root.right);

			MinMax m = new MinMax();

			// if either of left or right subtree says its not BST or the data
			// of this node is not greater/equal than max of left and less than
			// min of right
			// then subtree with this node as root will not be BST.
			// Return false and max size of left and right subtree to parent
			if (leftMinMax.isBST == false || rightMinMax.isBST == false
					|| (leftMinMax.max > root.data || rightMinMax.min <= root.data)) {
				m.isBST = false;
				m.size = Math.max(leftMinMax.size, rightMinMax.size);
				return m;
			}

			// if we reach this point means subtree with this node as root is
			// BST.
			// Set isBST as true. Then set size as size of left + size of right
			// + 1.
			// Set min and max to be returned to parent.
			m.isBST = true;
			m.size = leftMinMax.size + rightMinMax.size + 1;

			// if root.left is null then set root.data as min else
			// take min of left side as min
			m.min = root.left != null ? leftMinMax.min : root.data;

			// if root.right is null then set root.data as max else
			// take max of right side as max.
			m.max = root.right != null ? rightMinMax.max : root.data;

			return m;
		}
	}

	// Q198 House Robber #TopInterviewQuestion
	public int rob(int[] num) {
		if (num.length == 1)
			return 1;
		int evensum = 0, oddsum = 0, len = num.length;
		for (int i = 0; i < len; i++) {
			if (i % 2 == 0) {
				evensum = Math.max(oddsum, evensum + num[i]);
			} else {
				oddsum = Math.max(evensum, oddsum + num[i]);
			}
		}
		return Math.max(evensum, oddsum);
	}

	public int rob2(int[] nums) {

		if (nums.length == 0) return 0;
		int[] memo = new int[nums.length + 1];
		memo[0] = 0;
		memo[1] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int val = nums[i];
			memo[i+1] = Math.max(memo[i], memo[i-1] + val);
		}
		return memo[nums.length];
	}

// Q213 House Robber -2
// This problem is a little tricky at first glance. However, if you have finished the House Robber problem,
// this problem can simply be decomposed into two House Robber problems.
// Suppose there are n houses, since house 0 and n - 1 are now neighbors, we cannot rob them together and thus the
// solution is now the maximum of Rob houses 0 to n - 2 & Rob houses 1 to n - 1.

	public int rob3(int[] nums) {
		if (nums.length == 1)
			return nums[0];
		return Math.max(rob2(nums, 0, nums.length - 1), rob2(nums, 1, nums.length));
	}

	public int rob2(int[] num, int low, int high) {
		int evensum = 0, oddsum = 0, len = num.length;
		for (int i = low; i < high; i++) {
			if (i % 2 == 0) {
				evensum = Math.max(oddsum, evensum + num[i]);
			} else {
				oddsum = Math.max(evensum, oddsum + num[i]);
			}
		}
		return Math.max(evensum, oddsum);
	}

	// Q513 find bottom left tree value
	// Given a binary tree, find the leftmost value in the last row of the tree.
	public int findLeftMostNode(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.right != null)
				queue.add(root.right);
			if (root.left != null)
				queue.add(root.left);
		}
		return root.val;
	}

	// Q452 Minimum Number of Arrows to Burst Balloons #GoodQuestion
	public int findMinArrowShots(int[][] points) {
		if (points.length == 0) {
			return 0;
		}
		// Arrays.sort(points, (a, b) -> a[1] - b[1]);
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				Integer o11 = o1[1];
				Integer o22 = o2[1];
				return o11.compareTo(o22);
			}
		});
		int arrowPos = points[0][1];
		int arrowCnt = 1;
		for (int i = 1; i < points.length; i++) {
			if (arrowPos >= points[i][0]) {
				continue;
			}
			arrowCnt++;
			arrowPos = points[i][1];
		}
		return arrowCnt;
	}

// Q165 compare version numbers
// If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0. You may assume
// that the version strings are non-empty and contain only digits and the character. The . character does not
// represent a decimal point and is used to separate number sequences. For instance, 2.5 is not "two and a half"
// or "half way to version three", it is the fifth second-level revision of the second first-level revision.

	public int compareVersion2(String version1, String version2) {
		int temp1 = 0,temp2 = 0;
		int len1 = version1.length(),len2 = version2.length();
		int i = 0,j = 0;
		while(i<len1 || j<len2) {
			temp1 = 0;
			temp2 = 0;
			while(i<len1 && version1.charAt(i) != '.') {
				temp1 = temp1*10 + version1.charAt(i++)-'0';

			}
			while(j<len2 && version2.charAt(j) != '.') {
				temp2 = temp2*10 + version2.charAt(j++)-'0';

			}
			if(temp1>temp2) return 1;
			else if(temp1<temp2) return -1;
			else {
				i++;
				j++;

			}

		}
		return 0;

	}
	// Q223 Rectangle Area #GoodQuestion
	// Find the total area covered by two rectilinear rectangles in a 2D plane.
	// Each rectangle is defined by its bottom left corner and top right corner
	// as shown in the figure.
	// Rectangle Area.Assume that the total area is never beyond the maximum
	// possible value of int.
	// compute the intersection area
	// first square left bottom co-ordinates (A,B) right top (C,D)
	// second square left bottom co-ordinates (E,F) right top (G,H)
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int areaOfSqrA = (C - A) * (D - B);
		int areaOfSqrB = (G - E) * (H - F);
		int left = Math.max(A, E);
		int right = Math.min(G, C);
		int bottom = Math.max(F, B);
		int top = Math.min(D, H);
		// If overlap
		int overlap = 0;
		if (right > left && top > bottom)
			overlap = (right - left) * (top - bottom);
		return areaOfSqrA + areaOfSqrB - overlap;
	}

// Q225 Implement stack using queues
	// one Queue solution
	private Queue<Integer> q = new LinkedList<Integer>();

	// Push element x onto stack.
	public void push1(int x) {
		q.add(x);
		for (int i = 1; i < q.size(); i++) { // rotate the queue to make the
												// tail be the head
			q.add(q.poll());
		}
	}

	// Removes the element on top of the stack.
	public void pop1() {
		q.poll();
	}

	// Get the top element.
	public int top1() {
		return q.peek();
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return q.isEmpty();
	}

// Implement Stack Using 2 Queues
private Queue<Integer> q1 = new LinkedList<Integer>();
    private Queue<Integer> q2 = new LinkedList<Integer>();
    public void push4(int x) {
        if(q1.isEmpty()) {
            q1.add(x);
            for(int i = 0; i < q2.size(); i ++)
                q1.add(q2.poll());
        }else {
            q2.add(x);
            for(int i = 0; i < q1.size(); i++)
                q2.add(q1.poll());
        }
    }

    public void pop4() {
        if(!q1.isEmpty())
            q1.poll();
        else
            q2.poll();
    }
    public int top4() {
        return q1.isEmpty() ? q2.peek() : q1.peek();
    }
    public boolean empty4() {
        return q1.isEmpty() && q2.isEmpty();
    }


	// Q232 implement queues using stack
	Stack<Integer> input = new Stack();
	Stack<Integer> output = new Stack();

	public void push2(int x) {
		input.push(x);
	}

	public void pop2() {
		peek2();
		output.pop();
	}

	public int peek2() {
		if (output.empty())
			while (!input.empty())
				output.push(input.pop());
		return output.peek();
	}

	public boolean empty2() {
		return input.empty() && output.empty();
	}

	// Q696 count binary substring #UselessQuestion
	/*
	 * Give a string s, count the number of non-empty (contiguous) substrings
	 * that have the same number of 0's and 1's, and all the 0's and all the 1's
	 * in these substrings are grouped consecutively. Substrings that occur
	 * multiple times are counted the number of times they occur.
	 */
	public int countBinarySubstrings(String s) {
		int prevRunLength = 0, curRunLength = 1, res = 0;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1))
				curRunLength++;
			else {
				prevRunLength = curRunLength;
				curRunLength = 1;
			}
			if (prevRunLength >= curRunLength)
				res++;
		}
		return res;
	}

	// Q693 Binary Number with Alternate Bits #UselessQuestion
	// Given a positive integer, check whether it has alternating bits: namely,
	// if two adjacent bits will always have different values.
	public boolean hasAlternatingBits(int n) {
		String bits = Integer.toBinaryString(n);
		for (int i = 0; i < bits.length() - 1; i++) {
			if (bits.charAt(i) == bits.charAt(i + 1)) {
				return false;
			}
		}
		return true;
	}

	// Q690 employee importance (Not asked these days)
	/*
	 * You are given a data structure of employee information, which includes
	 * the employee's unique id, his importance value and his direct
	 * subordinates' id. For example, employee 1 is the leader of employee 2,
	 * and employee 2 is the leader of employee 3. They have importance value
	 * 15, 10 and 5, respectively. Then employee 1 has a data structure like [1,
	 * 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []].
	 * Note that although employee 3 is also a subordinate of employee 1, the
	 * relationship is not direct.
	 */

	public int getImportance1(List<Employee> employees, int id) {
		int total = 0;
		Map<Integer, Employee> map = new HashMap<>();
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		Queue<Employee> queue = new LinkedList<>();
		queue.offer(map.get(id));
		while (!queue.isEmpty()) {
			Employee current = queue.poll();
			total += current.importance;
			for (int subordinate : current.subordinates) {
				queue.offer(map.get(subordinate));
			}
		}
		return total;
	}

	public int getImportance2(List<Employee> employees, int id) {
		Map<Integer, Employee> map = new HashMap<>();
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		return getImportanceHelper(map, id);
	}

	private int getImportanceHelper(Map<Integer, Employee> map, int rootId) {
		Employee root = map.get(rootId);
		int total = root.importance;
		for (int subordinate : root.subordinates) {
			total += getImportanceHelper(map, subordinate);
		}
		return total;
	}

	// Q687 Longest Univalue path
	/*
	 * Given a binary tree, find the length of the longest path where each node
	 * in the path has the same value. This path may or may not pass through the
	 * root. Note: The length of path between two nodes is represented by the
	 * number of edges between them.
	 */
	int len = 0; // global variable

	public int longestUnivaluePath(TreeNode root) {
		if (root == null)
			return 0;
		len = 0;
		getLen(root, root.val);
		return len;
	}

	private int getLen(TreeNode node, int val) {
		if (node == null)
			return 0;
		int left = getLen(node.left, node.val);
		int right = getLen(node.right, node.val);
		len = Math.max(len, left + right);
		if (val == node.val)
			return Math.max(left, right) + 1;
		return 0;
	}

	// Q686 Repeated String Match
	// Given two strings A and B, find the minimum number of times A has to be
	// repeated such that B is a
	// substring of it. If no such solution, return -1.
	public int repeatedStringMatch(String A, String B) {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while (sb.length() < B.length()) {
			sb.append(A);
			count++;
		}
		if (sb.toString().contains(B))
			return count;
		if (sb.append(A).toString().contains(B))
			return ++count;
		return -1;
	}

	// Q682 Baseball Game
	// Given a list of strings, each string can be one of the 4 following types:
	// Integer (one round's score): Directly represents the number of points you
	// get in this round.
	// "+" (one round's score): Represents that the points you get in this round
	// are the sum of the last two valid round's points.
	// "D" (one round's score): Represents that the points you get in this round
	// are the doubled data of the last valid round's points.
	// "C" (an operation, which isn't a round's score): Represents the last
	// valid round's points you get were invalid and should be removed.
	public int calPoints(String[] ops) {
		int sum = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (String op : ops) {
			if (op.equals("C")) {
				sum -= list.removeLast();
			} else if (op.equals("D")) {
				list.add(list.peekLast() * 2);
				sum += list.peekLast();
			} else if (op.equals("+")) {
				list.add(list.peekLast() + list.get(list.size() - 2));
				sum += list.peekLast();
			} else {
				list.add(Integer.parseInt(op));
				sum += list.peekLast();
			}
		}
		return sum;
	}

	public int calPointsAnotherMethod(String[] ops) {
		int sum = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("+")) {
				int temp1 = stack.pop();
				int temp2 = stack.pop();
				int temp_sum = temp1 + temp2;
				sum += temp_sum;
				stack.push(temp2);
				stack.push(temp1);
				stack.push(temp_sum);
			} else if (ops[i].equals("D")) {
				int temp = stack.pop();
				int temp_d = 2 * temp;
				sum += temp_d;
				stack.push(temp);
				stack.push(temp_d);
			} else if (ops[i].equals("C")) {
				int cancel = stack.pop();
				sum -= cancel;
			} else {
				int temp = Integer.parseInt(ops[i]);
				sum += temp;
				stack.push(temp);
			}
		}
		return sum;
	}

// Q671 Second minimum Node in a binary tree
// Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this
// tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the
// smaller value among its two sub-nodes. Given such a binary tree, you need to output the second minimum value in
// the set made of all the nodes' value in the whole tree. If no such second minimum value exists, output
// -1 instead.

// Solution : The author explained pretty clearly, but maybe I can try to rephrase it. The special tree structure
// makes sure that the value of left and right is not lesser than root value, so by asking for second minimum,
// it is actually asking for the smallest value in left and right subtree. This value can't be equal to root value,
// so we need recursion if we find that left or right val is same as root val. It is possible that all values in
// left/right subtree is the same, and we mark the return value as -1 indicating no candidate exists in this subtree.
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) {
			return -1;
		}
		if (root.left == null && root.right == null) {
			return -1;
		}

		int left = root.left.val;
		int right = root.right.val;

		// if value same as root value, need to find the next candidate
		if (root.left.val == root.val) {
			left = findSecondMinimumValue(root.left);
		}
		if (root.right.val == root.val) {
			right = findSecondMinimumValue(root.right);
		}

		if (left != -1 && right != -1) {
			return Math.min(left, right);
		} else if (left != -1) {
			return left;
		} else {
			return right;
		}
	}

	// Q236 lca of a Binary tree #TopInterviewQuestion
	TreeNode getLCA(TreeNode current, TreeNode A, TreeNode B) {
		if (current == null || current == A || current == B)
			return current;

		TreeNode left = getLCA(current.left, A, B);

		TreeNode right = getLCA(current.right, A, B);

		if (left != null && right != null)
			return current;

		if (left == null)
			return right;

		else
			return left;
	}

	// LCA iterative solution if needed
	public TreeNode lowestCommonAncestoriterative(TreeNode root, TreeNode p, TreeNode q) {
		Map<TreeNode, TreeNode> parent = new HashMap<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		parent.put(root, null);
		stack.push(root);

		while (!parent.containsKey(p) || !parent.containsKey(q)) {
			TreeNode node = stack.pop();
			if (node.left != null) {
				parent.put(node.left, node);
				stack.push(node.left);
			}
			if (node.right != null) {
				parent.put(node.right, node);
				stack.push(node.right);
			}
		}
		Set<TreeNode> ancestors = new HashSet<>();
		while (p != null) {
			ancestors.add(p);
			p = parent.get(p);
		}
		while (!ancestors.contains(q))
			q = parent.get(q);
		return q;
	}

	// Q235 Lowest Common Ancestor of a Binary Search Tree
	public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val < root.val && q.val < root.val)
			return lowestCommonAncestorRecursive(root.left, p, q);
		if (p.val > root.val && q.val > root.val)
			return lowestCommonAncestorRecursive(root.right, p, q);
		return root;
	}

	// Another method
	TreeNode lowestCommonAncestorNonRecursive(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode cur = root;
		while (true) {
			if (p.val < cur.val && q.val < cur.val)
				cur = cur.left;
			else if (p.val > cur.val && q.val > cur.val)
				cur = cur.right;
			else
				return cur;
		}
	}

	// Q669 Trim BST
	// Given a binary search tree and the lowest and highest boundaries as L and
	// R, trim the tree
	// so that all its elements lies in [L, R] (R >= L). You might need to
	// change the root of the tree,
	// so the result should return the new root of the trimmed binary search
	// tree.
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null)
			return null;

		if (root.val < L)
			return trimBST(root.right, L, R);
		if (root.val > R)
			return trimBST(root.left, L, R);

		root.left = trimBST(root.left, L, R);
		root.right = trimBST(root.right, L, R);

		return root;
	}

	public TreeNode trimBSTNonRecusrsive(TreeNode root, int L, int R) {
		if (root == null) {
			return root;
		}
		// Find a valid root which is used to return.
		while (root.val < L || root.val > R) {
			if (root.val < L) {
				root = root.right;
			}
			if (root.val > R) {
				root = root.left;
			}
		}
		TreeNode dummy = root;
		// Remove the invalid nodes from left subtree.
		while (dummy != null) {
			while (dummy.left != null && dummy.left.val < L) {
				dummy.left = dummy.left.right;
				// If the left child is smaller than L, then we just keep the
				// right subtree of it.
			}
			dummy = dummy.left;
		}
		dummy = root;
		// Remove the invalid nodes from right subtree
		while (dummy != null) {
			while (dummy.right != null && dummy.right.val > R) {
				dummy.right = dummy.right.left;
				// If the right child is bigger than R, then we just keep the
				// left subtree of it.
			}
			dummy = dummy.right;
		}
		return root;
	}

// Q665 non decreasing (increasing) array
// Given an array with n integers, your task is to check if it could become non-decreasing by modifying
// at most 1 element. We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

// This problem is like a greedy problem. When you find nums[i-1] > nums[i] for some i, you will prefer to change
// nums[i-1]'s value, since a larger nums[i] will give you more risks that you get inversion errors after position i.
// But, if you also find nums[i-2] > nums[i], then you have to change nums[i]'s value instead, or else you need to change
// both of nums[i-2]'s and nums[i-1]'s values.

	public boolean checkPossibility(int[] nums) {
		int cnt = 0; // the number of changes
		for (int i = 1; i < nums.length && cnt <= 1; i++) {
			if (nums[i - 1] > nums[i]) {
				cnt++;
				if (i - 2 < 0 || nums[i - 2] <= nums[i])
					nums[i - 1] = nums[i]; // modify nums[i-1] of a priority
				else
					nums[i] = nums[i - 1]; // have to modify nums[i]
			}
		}
		return cnt <= 1;
	}

	// Q657 judge route circle
	// Initially, there is a Robot at position (0, 0). Given a sequence of its
	// moves, judge if this robot makes a circle,
	// which means it moves back to the original place. The move sequence is
	// represented by a string. And each move is
	// represent by a character. The valid robot moves are R (Right), L (Left),
	// U (Up) and D (down). The output should be
	// true or false representing whether the robot makes a circle.
	public boolean judgeCircle(String moves) {
		int x = 0;
		int y = 0;
		for (char ch : moves.toCharArray()) {
			if (ch == 'U')
				y++;
			else if (ch == 'D')
				y--;
			else if (ch == 'R')
				x++;
			else if (ch == 'L')
				x--;
		}
		return x == 0 && y == 0;
	}

	// Q653. Two Sum IV - Input is a BST
	// Given a Binary Search Tree and a target number, return true
	// if there exist two elements in the BST such that their sum is equal to
	// the given target.

	public boolean findTarget(TreeNode root, int k) {
		HashSet<Integer> set = new HashSet<>();
		return dfs(root, set, k);
	}

	public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
		if (root == null)
			return false;
		if (set.contains(k - root.val))
			return true;
		set.add(root.val);
		return dfs(root.left, set, k) || dfs(root.right, set, k);
	}

	// Same above method using inorder traversal
	public boolean findTargetInorderTraversal(TreeNode root, int k) {
		List<Integer> nums = new ArrayList<>();
		inorder(root, nums);
		for (int i = 0, j = nums.size() - 1; i < j;) {
			if (nums.get(i) + nums.get(j) == k)
				return true;
			if (nums.get(i) + nums.get(j) < k)
				i++;
			else
				j--;
		}
		return false;
	}

	public void inorder(TreeNode root, List<Integer> nums) {
		if (root == null)
			return;
		inorder(root.left, nums);
		nums.add(root.val);
		inorder(root.right, nums);
	}

	// Q645 Set mismatch
	// The set S originally contains numbers from 1 to n.
	// But unfortunately, due to the data error, one of the numbers in the set
	// got duplicated to another number in the set,
	// which results in repetition of one number and loss of another number.
	// Given an array nums representing the data status of this set after the
	// error.
	// Your task is to firstly find the number occurs twice and then find the
	// number that is missing.
	// Return them in the form of an array.
	public int[] findErrorNums(int[] nums) {
		Set<Integer> set = new HashSet<>();
		int duplicate = 0, n = nums.length;
		long sum = (n * (n + 1)) / 2;
		for (int i : nums) {
			if (set.contains(i))
				duplicate = i;
			sum -= i;
			set.add(i);
		}
		return new int[] { duplicate, (int) sum + duplicate };
	}

	// Q268 missing number #TopInterviewQuestion
	// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
	// find the one that is missing
	// from the array. For example, Given nums = [0, 1, 3] return 2.
	public int missingNumber(int[] nums) {
		int sum = 0;
		for (int num : nums)
			sum += num;

		return (nums.length * (nums.length + 1)) / 2 - sum;
	}

	// Bit Manipulation of the above question
	public int missingNumberBitManipulation(int[] nums) {
		int res = nums.length;
		for (int i = 0; i < nums.length; i++) {
			res = res ^ i ^ nums[i]; // a^b^b = a
		}
		return res;
	}

	// Q643 Maximum Average Sub array I
	// Given an array consisting of n integers, find the contiguous subarray of
	// given length k that has the maximum average value.
	// And you need to output the maximum average value.
	public double findMaxAverage(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}

		int maxSum = sum;
		for (int i = 0, j = k; j < nums.length; i++, j++) {
			sum = sum - nums[i] + nums[j];
			maxSum = Math.max(sum, maxSum);
		}

		return ((double) maxSum) / ((double) k);
	}

	// Q53 maximum subarray #TopInterviewQuestion
	// Given an integer array nums, find the contiguous subarray (containing at
	// least one number) which has the largest sum and return its sum.
	public static int maxSubArray(int[] A) {
		int maxSoFar = A[0], maxEndingHere = A[0];
		for (int i = 1; i < A.length; ++i) {
			maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	// Q58 Length of the last word
	// Given a string s consists of upper/lower-case alphabets and empty space
	// characters ' ', return the length of last word in the string.
	// If the last word does not exist, return 0.
	public int lengthOfLastWord(String s) {
		String use = s.trim();
		int count = 0;
		for (int i = use.length() - 1; i >= 0; i--) {
			if (use.charAt(i) != ' ')
				count++;
			else
				break;
		}
		return count;
	}

	// Q69 Sqrt(x) #TopInterviewQuestion
	// Find the square root of a number
	public static int sqrt(int x) {
		if (0 == x)
			return 0;
		int left = 1, right = x;
		int ans = -1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (mid <= x / mid) {
				left = mid + 1;
				ans = mid;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	// Q637 Average of levels in binary tree #GoodQuestion
	// Given a non-empty binary tree, return the average value of the nodes on
	// each level in the form of an array.
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> result = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		if (root == null)
			return result;
		q.add(root);
		while (!q.isEmpty()) {
			int n = q.size();
			double sum = 0.0;
			for (int i = 0; i < n; i++) {
				TreeNode node = q.poll();
				sum += node.val;
				if (node.left != null)
					q.offer(node.left);
				if (node.right != null)
					q.offer(node.right);
			}
			result.add(sum / n);
		}
		return result;
	}

	// Q633 Sum of square numbers
// Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
	public boolean judgeSquareSum(int c) {
		if (c < 0) {
			return false;
		}
		int left = 0, right = (int) Math.sqrt(c);
		while (left <= right) {
			int cur = left * left + right * right;
			if (cur < c) {
				left++;
			} else if (cur > c) {
				right--;
			} else {
				return true;
			}
		}
		return false;
	}

// Q628 Maximum product of three numbers #GoodQuestion
// Given an integer array, find three numbers whose product is maximum and output the maximum product.
// Simply find out the three largest numbers and the two smallest numbers using one pass.
// Heap Solution is a better one
	public int maximumProduct(int[] nums) {
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE,
                min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (n > max2) {
				max3 = max2;
				max2 = n;
			} else if (n > max3) {
				max3 = n;
			}

			if (n < min1) {
				min2 = min1;
				min1 = n;
			} else if (n < min2) {
				min2 = n;
			}
		}
		return Math.max(max1 * max2 * max3, max1 * min1 * min2);
	}

	// Heap Solution for the same above question
	public int maximumProductHeap(int[] nums) {
		PriorityQueue<Integer> poheap = new PriorityQueue<>(); //stores big elements
		PriorityQueue<Integer> neheap = new PriorityQueue<>(Collections.reverseOrder()); //stores smaller elements
		for (int num : nums) {
			poheap.offer(num);
			neheap.offer(num);
			if (poheap.size() > 3) {
				poheap.poll();
			}
			if (neheap.size() > 2) {
				neheap.poll();
			}
		}
		int c1 = 1;
		int max = 0;
		while (!poheap.isEmpty()) {
			max = poheap.poll();
			c1 *= max;
		}
		while (!neheap.isEmpty()) {
			max *= neheap.poll();
		}
		return Math.max(c1, max);
	}

	// Q617 Merge two binary trees
	// Given two binary trees and imagine that when you put one of them to cover
	// the other,
	// some nodes of the two trees are overlapped while the others are not.
	// You need to merge them into a new binary tree. The merge rule is that if
	// two nodes overlap,
	// then sum node values up as the new value of the merged node.
	// Otherwise, the NOT null node will be used as the node of new tree.
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return null;
		int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
		TreeNode newNode = new TreeNode(val);
		newNode.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
		newNode.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
		return newNode;
	}

	// Iterative Solution of merging 2 binary trees
	public TreeNode mergeTreesNonRecursive(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null)
			return null;
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.push(t1);
		stack2.push(t2);
		TreeNode res = t1;
		while (!stack1.isEmpty()) {
			TreeNode cur1 = stack1.pop();
			TreeNode cur2 = stack2.pop();
			cur1.val += cur2.val;
			if (cur1.right == null && cur2.right != null) {
				cur1.right = cur2.right;
			} else if (cur1.right != null && cur2.right != null) {
				stack1.push(cur1.right);
				stack2.push(cur2.right);
			}
			if (cur1.left == null && cur2.left != null) {
				cur1.left = cur2.left;
			} else if (cur1.left != null && cur2.left != null) {
				stack1.push(cur1.left);
				stack2.push(cur2.left);
			}
		}
		return res;
	}

	// Q606 Construct string from a binary tree
	// You need to construct a string consists of parenthesis and integers from
	// a binary tree with the preorder traversing way.
	// The null node needs to be represented by empty parenthesis pair "()".
	// And you need to omit all the empty parenthesis pairs that don't affect
	// the one-to-one mapping relationship
	// between the string and the original binary tree.
	// Input: Binary tree: [1,2,3,4]
	// 1
	// / \
	// 2 3
	// /
	// 4
	// Output: "1(2(4))(3)"
	// Explanation: Originallay it needs to be "1(2(4)())(3()())",
	// but you need to omit all the unnecessary empty parenthesis pairs.
	// And it will be "1(2(4))(3)".
	public String tree2str1(TreeNode t) {
		if (t == null)
			return "";

		String result = t.val + "";

		String left = tree2str1(t.left);
		String right = tree2str1(t.right);

		if (left == "" && right == "")
			return result;
		if (left == "")
			return result + "()" + "(" + right + ")";
		if (right == "")
			return result + "(" + left + ")";
		return result + "(" + left + ")" + "(" + right + ")";
	}

	// Another way
	public String tree2str2(TreeNode t) {
		if (t == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		preorder(t, sb);
		return sb.toString();
	}

	private void preorder(TreeNode root, StringBuilder sb) {
		if (root == null) {
			return;
		}
		sb.append(root.val);
		if (root.left != null) {
			sb.append("(");
			preorder(root.left, sb);
			sb.append(")");
		}
		if (root.right != null) {
			if (root.left == null) {
				sb.append("()");
			}
			sb.append("(");
			preorder(root.right, sb);
			sb.append(")");
		}
	}

	// Q605 Can Place Flowers
	// Suppose you have a long flowerbed in which some of the plots are planted
	// and some are not. However,
	// flowers cannot be planted in adjacent plots - they would compete for
	// water and both would die.
	// Given a flowerbed (represented as an array containing 0 and 1, where 0
	// means empty and 1 means not empty),
	// and a number n, return if n new flowers can be planted in it without
	// violating the no-adjacent-flowers rule.
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int count = 0;
		for (int i = 0; i < flowerbed.length && count < n; i++) {
			if (flowerbed[i] == 0) {
				// get next and prev flower bed slot values. If i lies at the
				// ends the next and prev are considered as 0.
				int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
				int prev = (i == 0) ? 0 : flowerbed[i - 1];
				if (next == 0 && prev == 0) {
					flowerbed[i] = 1;
					count++;
				}
			}
		}
		return count == n;
	}

	// difficult to thing about this solution but a good solution for the above
	// question
	public boolean canPlaceFlowers2(int[] flowerbed, int n) {
		int count = 1;
		int result = 0;
		for (int i = 0; i < flowerbed.length; i++) {
			if (flowerbed[i] == 0) {
				count++;
			} else {
				result += (count - 1) / 2;
				count = 0;
			}
		}
		if (count != 0)
			result += count / 2;
		return result >= n;
	}

	// Q500 Keyboard Row
	// Given a List of words, return the words that can be typed using letters
	// of alphabet on only one row's of American keyboard
	public String[] findWords(String[] words) {
		String[] strs = { "QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM" };
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			for (char c : strs[i].toCharArray()) {
				map.put(c, i);// put <char, rowIndex> pair into the map
			}
		}
		List<String> res = new LinkedList<>();
		for (String w : words) {
			if (w.equals(""))
				continue;
			int index = map.get(w.toUpperCase().charAt(0));
			for (char c : w.toUpperCase().toCharArray()) {
				if (map.get(c) != index) {
					index = -1; // don't need a boolean flag.
					break;
				}
			}
			if (index != -1)
				res.add(w);// if index != -1, this is a valid string
		}
		return res.toArray(new String[0]);
	}

	// better solution and understandable than the above solution
	public String[] findWords2(String[] words) {
		Set<Character> row1 = stringToCharSet("qwertyuiop");
		Set<Character> row2 = stringToCharSet("asdfghjkl");
		Set<Character> row3 = stringToCharSet("zxcvbnm");

		List<String> res = new ArrayList();
		for (String word : words) {
			if (isValid(row1, word) || isValid(row2, word) || isValid(row3, word)) {
				res.add(word);
			}
		}

		return res.toArray(new String[0]);
	}

	boolean isValid(Set<Character> row, String word) {
		for (char c : word.toCharArray()) {
			if (!row.contains(Character.toLowerCase(c)))
				return false;
		}
		return true;
	}

	Set<Character> stringToCharSet(String str) {
		Set<Character> set = new HashSet();
		for (char c : str.toCharArray()) {
			set.add(c);
		}
		return set;
	}

	// dint understand properly.. have to check again
	// Q501 find mode in binary search tree
	// Given a binary search tree (BST) with duplicates, find all the mode(s)
	// (the most frequently occurred element) in the given BST.
	List<Integer> modes = new ArrayList<>();
	Integer pre = null, maxCount = null;
	int count1 = 0;
	Integer mode = null;

	void checkCount() {
		if (maxCount == null || count1 > maxCount) {
			maxCount = count1;
			modes.clear();
			modes.add(pre);
		} else if (count1 == maxCount) {
			modes.add(pre);
		}
	}

	void helper(TreeNode root) {
		if (root == null)
			return;
		helper(root.left);
		if (pre == null) {
			pre = root.val;
			count1 = 1;
		} else {
			if (pre == root.val) {
				count1++;
			} else {
				checkCount();
				count1 = 1;
				pre = root.val;
			}
		}
		helper(root.right);
	}

	public int[] findMode(TreeNode root) {
		if (root == null)
			return new int[0];
		helper(root);
		checkCount();
		int[] ret = new int[modes.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = modes.get(i);
		return ret;
	}

	// Q504 Base 7
	// Given an integer, return its base 7 string representation.
	public String convertTo7(int num) {
		if (num == 0)
			return "0";
		StringBuilder sb = new StringBuilder();
		boolean negative = false;
		if (num < 0) {
			negative = true;
		}
		while (num != 0) {
			sb.append(Math.abs(num % 7));
			num = num / 7;
		}
		if (negative) {
			sb.append("-");
		}
		return sb.reverse().toString();
	}

	// Q506 find relative ranks
	// Given scores of N athletes, find their relative ranks and the people with
	// the top three highest scores,
	// who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze
	// Medal". Basically this question is to
	// find out the score -> ranking mapping. The easiest way is to sort those
	// scores in nums. But we will lose
	// their original order. We can create (score , original index) pairs and
	// sort them by score decreasingly.
	// Then we will have score -> ranking (new index) mapping and we can use
	// original index to create the result.
	// Time complexity: O(NlgN). Space complexity: O(N). N is the number of
	// scores.
	public static String[] findRelativeRanks(int[] nums) {
		Integer[] index = new Integer[nums.length];
		for (int i = 0; i < nums.length; i++) {
			index[i] = i;
		}
		Arrays.sort(index, (a, b) -> (nums[b] - nums[a]));
		String[] result = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				result[index[i]] = "Gold Medal";
			} else if (i == 1) {
				result[index[i]] = "Silver Medal";
			} else if (i == 2) {
				result[index[i]] = "Bronze Medal";
			} else {
				result[index[i]] = (i + 1) + "";
			}
		}
		return result;
	}

	// Another way using hashmap
	public String[] findRelativeRanks2(int[] nums) {
		int[] tmp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			tmp[i] = nums[i];
		}
		Arrays.sort(tmp);
		Map<Integer, String> rankMap = new HashMap();
		int len = nums.length;
		for (int i = len - 1; i >= 0; i--) {
			if (i == len - 1)
				rankMap.put(tmp[i], "Gold Medal");
			else if (i == len - 2)
				rankMap.put(tmp[i], "Silver Medal");
			else if (i == len - 3)
				rankMap.put(tmp[i], "Bronze Medal");
			else
				rankMap.put(tmp[i], String.valueOf(len - i));
		}
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			result[i] = rankMap.get(nums[i]);
		}
		return result;
	}

	// Q520 Detect Capital
	// Given a word, you need to judge whether the usage of capitals in it is
	// right or not.
	// We define the usage of capitals in a word to be right when one of the
	// following cases holds:
	// All letters in this word are capitals, like "USA".
	// All letters in this word are not capitals, like "leetcode".
	// Only the first letter in this word is capital if it has more than one
	// letter, like "Google".
	// Otherwise, we define that this word doesn't use capitals in a right way.
	public boolean detectCapitalUse(String word) {
		int numUpper = 0;
		for (int i = 0; i < word.length(); i++)
			if (Character.isUpperCase(word.charAt(i)))
				numUpper++;
		if (numUpper == 1)
			return Character.isUpperCase(word.charAt(0));
		return numUpper == 0 || numUpper == word.length();
	}

	// Q599 Minimum Index Sum of Two Lists
	// Suppose Andy and Doris want to choose a restaurant for dinner, and they
	// both have a list of favorite restaurants
	// represented by strings. You need to help them find out their common
	// interest with the least list index sum.
	// If there is a choice tie between answers, output all of them with no
	// order requirement. You could assume there
	// always exists an answer.
	public String[] findRestaurant(String[] list1, String[] list2) {
		Map<String, Integer> map = new HashMap<>();
		List<String> res = new LinkedList<>();
		int minSum = Integer.MAX_VALUE;
		for (int i = 0; i < list1.length; i++)
			map.put(list1[i], i);
		for (int i = 0; i < list2.length; i++) {
			Integer j = map.get(list2[i]);
			if (j != null && i + j <= minSum) {
				if (i + j < minSum) {
					res.clear();
					minSum = i + j;
				}
				res.add(list2[i]);
			}
		}
		return res.toArray(new

		String[res.size()]);
	}

	// Q581 Shortest Unsorted Continuous Subarray
	// Given an integer array, you need to find one continuous subarray that if
	// you only sort this subarray in ascending order,
	// then the whole array will be sorted in ascending order, too.
	// https://www.youtube.com/watch?v=Hg03KTli9co link for better understanding
	// (ideserve)
	static void printUnsorted(int arr[], int n) {
		int s = 0, e = n - 1, i, max, min;
		// step 1(a) of above algo
		// move from start index till array[i] > array[i+1]
		for (s = 0; s < n - 1; s++) {
			if (arr[s] > arr[s + 1])
				break;
		}
		if (s == n - 1) {
			System.out.println("The complete array is sorted");
			return;
		}
		// step 1(b) of above algo
		// move from end index till array[j-1] > array[j]
		for (e = n - 1; e > 0; e--) {
			if (arr[e] < arr[e - 1])
				break;
		}
		// step 2(a) of above algo
		// find the max and min
		max = arr[s];
		min = arr[s];
		for (i = s + 1; i <= e; i++) {
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}

		// step 2(b) of above algo
		// search the sorted array from 0 to start index at which minimum
		// element will be in sorted array
		for (i = 0; i < s; i++) {
			if (arr[i] > min) {
				s = i;
				break;
			}
		}
		// step 2(c) of above algo
		// search the sorted array from end index to end of array to find the
		// index at which maximum element will be in a sorted array, maxindex
		for (i = n - 1; i >= e + 1; i--) {
			if (arr[i] < max) {
				e = i;
				break;
			}
		}
		// step 3 of above algo
		// print
		System.out.println(" The unsorted subarray which" + " makes the given array sorted lies"
				+ "  between the indices " + s + " and " + e);
		return;
	}

	// Q575 distribute candies
	// Given an integer array with even length, where different numbers in this
	// array represent different kinds of candies.
	// Each number means one candy of the corresponding kind.
	// You need to distribute these candies equally in number to brother and
	// sister.
	// Return the maximum number of kinds of candies the sister could gain.
	public int distributeCandies(int[] candies) {
		int total = candies.length;
		Set<Integer> kinds = new HashSet<Integer>();
		for (int i = 0; i < total; i++) {
			kinds.add(candies[i]);
		}
		if (kinds.size() > total / 2) {
			return total / 2;
		} else {
			return kinds.size();
		}
	}

	// Q566 Reshape the matrix
	// In MATLAB, there is a very useful function called 'reshape', which can
	// reshape a matrix into a new one with
	// different size but keep its original data.You're given a matrix
	// represented by a two-dimensional array, and two
	// positive integers r and c representing the row number and column number
	// of the wanted reshaped matrix, respectively.
	// The reshaped matrix need to be filled with all the elements of the
	// original matrix in the same row-traversing order
	// as they were. If the 'reshape' operation with given parameters is
	// possible and legal, output the new reshaped matrix;
	// Otherwise, output the original matrix.Both are good solutions
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int m = nums.length, n = nums[0].length;
		if (m * n != r * c)
			return nums;
		int[][] result = new int[r][c];
		int row = 0, col = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[row][col] = nums[i][j];
				col++;
				if (col == c) {
					col = 0;
					row++;
				}
			}
		}
		return result;
	}

	// ALternate solution
	public int[][] matrixReshape2(int[][] nums, int r, int c) {
		int row = nums.length;
		int col = nums[0].length;
		if (r * c != row * col)
			return nums;

		int[][] newMatrix = new int[r][c];
		for (int i = 0; i < r * c; ++i) {
			newMatrix[i / c][i % c] = nums[i / col][i % col];
		}
		return newMatrix;
	}

	// Q563 binary tree tilt
	// Given a binary tree, return the tilt of the whole tree.
	// The tilt of a tree node is defined as the absolute difference between the
	// sum of all left subtree node values and the sum of all right subtree node
	// values.
	// Null node has tilt 0.
	// The tilt of the whole tree is defined as the sum of all nodes' tilt.
	int result = 0;

	public int findTilt(TreeNode root) {
		postOrder(root);
		return result;
	}

	private int postOrder(TreeNode root) {
		if (root == null)
			return 0;
		int left = postOrder(root.left);
		int right = postOrder(root.right);
		result += Math.abs(left - right);
		return left + right + root.val;
	}

	public int findTiltNonRecursive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int tilt = 0;
		Stack<TreeNode> stack = new Stack<>();
		Map<TreeNode, Integer> map = new HashMap<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.peek();
			if ((node.left == null || map.containsKey(node.left))
					&& (node.right == null || map.containsKey(node.right))) {
				stack.pop();
				int left = map.containsKey(node.left) ? map.get(node.left) : 0;
				int right = map.containsKey(node.right) ? map.get(node.right) : 0;
				tilt += Math.abs(left - right);
				map.put(node, left + right + node.val);
			} else {
				if (node.left != null && !map.containsKey(node.left)) {
					stack.push(node.left);
				}

				if (node.right != null && !map.containsKey(node.right)) {
					stack.push(node.right);
				}
			}
		}
		return tilt;
	}

	// Q561 array partition I .. did not understand.
	// Given an array of 2n integers, your task is to group these integers into
	// n pairs of integer, say (a1, b1),
	// (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1
	// to n as large as possible.
	// there are two ways of solving it if the range is given like in this
	// example( range is n is a positive integer,
	// which is in the range of [1, 10000].All the integers in the array will be
	// in the range of [-10000, 10000].)
	// we use the below method. if range is not given then the below method is
	// not of much use.
	// that time we will sort the elements and take the odd elements to make a
	// pair and even elements to make a pair.
	// for (int i = 0; i < nums.length; i += 2) {result += nums[i]};}
	public int arrayPairSum(int[] nums) {
		int[] exist = new int[20001];
		for (int i = 0; i < nums.length; i++) {
			exist[nums[i] + 10000]++;
		}
		int sum = 0;
		boolean odd = true;
		for (int i = 0; i < exist.length; i++) {
			while (exist[i] > 0) {
				if (odd) {
					sum += i - 10000;
				}
				odd = !odd;
				exist[i]--;
			}
		}
		return sum;
	}

	// Student attendance Record I
	// You are given a string representing an attendance record for a student.
	// The record only contains the following three characters:
	// 'A' : Absent.
	// 'L' : Late.
	// 'P' : Present.
	// A student could be rewarded if his attendance record doesn't contain more
	// than one 'A' (absent) or more than two continuous 'L'
	public boolean checkRecord(String s) {
		int a = 0, l = 0;
		char[] s1 = s.toCharArray();
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] == 'A')
				a++;
			if (s1[i] == 'L')
				l++;
			else
				l = 0;
			if (a >= 2 || l > 2)
				return false;
		}
		return true;
	}

    // Q538 Given a Binary Search Tree (BST), convert it to a Greater Tree such
    // that every key of the original BST
    // is changed to the original key plus sum of all keys greater than the
    // original key in BST.
    // Example:
    // Input: The root of a Binary Search Tree like this:
    // 5
    // / \
    // 2 13
    //
    // Output: The root of a Greater Tree like this:
    // 18
    // / \
    // 20 13

    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    public void convert(TreeNode cur) {
        if (cur == null)
            return;
        convert(cur.right);
        cur.val += sum;
        sum = cur.val;
        convert(cur.left);
    }

    // Non recursive approach of the above question
    public TreeNode convertBSTNonRecursive(TreeNode root) {
        if (root == null)
            return null;
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            int tmp = cur.val;
            cur.val += sum;
            sum += tmp;
            cur = cur.left;
        }
        return root;
    }

	// Q344 Reverse String #TopInterviewQuestion
	public static String reverseString(String s) {
		if (s.length() == 0)
			return s;
		return reverseString(s.substring(1)) + s.charAt(0);
	}

	// Q151 Reverse words in a String #GoodQuestion
	// Given an input string, reverse the string word by word. A word is defined
	// as a sequence of non-space characters.
	// The input string does not contain leading or trailing spaces and the
	// words are always separated by a single space.
	// For example,
	// Given s = "the sky is blue",
	// return "blue is sky the".
	// Could you do it in-place without allocating extra space?
	// this will be the response after every reverse method for the string my
	// name is
	// yM name is
	// yM eman is
	// yM eman si
	// is name My
	public static char[] reverseWords1(String n) {
		char[] s = n.toCharArray();
		int i = 0;
		for (int j = 0; j < s.length; j++) {
			if (s[j] == ' ') {
				reverse(s, i, j - 1);
				i = j + 1;
			}
		}
		reverse(s, i, s.length - 1);
		reverse(s, 0, s.length - 1);
		return s;
	}

	private static void reverse(char[] s, int i, int j) {
		while (i < j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}

	public static String reverseString2(String s) {

		if (s.length() == 0)

			return s;

		return reverseString2(s.substring(1)) + s.charAt(0);

	}

	// Q557 Reverse words in the string III
	// Given a string, you need to reverse the order of characters in each word
	// within a sentence while still preserving
	// whitespace and initial word order.
	// Input: "Let's take LeetCode contest"
	// Output: "s'teL ekat edoCteeL tsetnoc"
	public String reverseWords(String s) {
		String[] str = s.split(" ");
		for (int i = 0; i < str.length; i++)
			str[i] = new StringBuilder(str[i]).reverse().toString();
		StringBuilder result = new StringBuilder();
		for (String st : str)
			result.append(st + " ");
		return result.toString().trim();
	}

	// Another way of doing it
	public String reverseWordsRecursion(String s) {
		char[] s1 = s.toCharArray();
		int i = 0;
		for (int j = 0; j < s1.length; j++) {
			if (s1[j] == ' ') {
				reverse1(s1, i, j - 1);
				i = j + 1;
			}
		}
		reverse1(s1, i, s1.length - 1);
		return new String(s1);
	}

	public void reverse1(char[] s, int l, int r) {
		while (l < r) {
			char temp = s[l];
			s[l] = s[r];
			s[r] = temp;
			l++;
			r--;
		}
	}

	// Q541 Reverse String II
	// Given a string and an integer k, you need to reverse the first k
	// characters for every 2k characters
	// counting from the start of the string. If there are less than k
	// characters left, reverse all of them.
	// If there are less than 2k but greater than or equal to k characters, then
	// reverse the first k characters and left the other as original.
	// Input: s = "abcdefg", k = 2
	// Output: "bacdfeg"
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		int i = 0;
		while (i < n) {
			int j = Math.min(i + k - 1, n - 1);
			swap(arr, i, j);
			i += 2 * k;
		}
		return String.valueOf(arr);
	}

	private void swap(char[] arr, int l, int r) {
		while (l < r) {
			char temp = arr[l];
			arr[l++] = arr[r];
			arr[r--] = temp;
		}
	}

	// Q543 Diameter of a binary tree
	// Given a binary tree, you need to compute the length of the diameter of
	// the tree. The diameter of a binary tree is the length
	// of the longest path between any two nodes in a tree. This path may or may
	// not pass through the root.
	// Example:
	// Given a binary tree
	// 1
	// / \
	// 2 3
	// / \
	// 4 5
	// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
	int max1 = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		maxDepth1(root);
		return max1;
	}

	private int maxDepth1(TreeNode root) {
		if (root == null)
			return 0;
		int left = maxDepth1(root.left);
		int right = maxDepth1(root.right);
		int max2 = Math.max(max1, left + right);
		return Math.max(left, right) + 1;
	}

// Q128 Longest consecutive sequence #TopInterviewQuestion #GoodQuestion
// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
// For example, Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
// We will use HashTable. The key thing is to keep track of the sequence length and store that in the boundary
// points of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.
// Whenever a new element n is inserted into the map, do two things: See if n - 1 and n + 1 exist in the map,
// and if so, it means there is an existing sequence next to n.
// Variables left and right will be the length of those two sequences, while 0 means there is no sequence
// and n will be the boundary point later. Store (left + right + 1) as the associated value to key n into
// the map. Use left and right to locate the other end of the sequences to the left and right of n respectively, and
// replace the value with the new length.

	public int longestConsecutive(int[] num) {
		int res = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int n : num) {
			if (!map.containsKey(n)) {
				int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
				int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;

				// sum: length of the sequence n is in
				int sum = left + right + 1;
				map.put(n, sum);

				// keep track of the max length
				res = Math.max(res, sum);

				// extend the length to the boundary(s) of the sequence will do
				// nothing if n has no neighbors
				map.put(n - left, sum);
				map.put(n + right, sum);
			} else {
				// duplicates
				continue;
			}
		}
		return res;
	}



	// Q532 k-diff pairs in an array
	// Given an array of integers and an integer k, you need to find the number
	// of unique k-diff pairs in the array.
	// Here a k-diff pair is defined as an integer pair (i, j), where i and j
	// are both numbers in the array and their
	// absolute difference is k.
	// Input: [3, 1, 4, 1, 5], k = 2
	// Output: 2
	public int findPairs(int[] nums, int k) {
		if (nums.length == 0 || k < 0)
			return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		map.put(nums[0], 1);
		for (int i = 1; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (k == 0 && map.get(nums[i]) == 1)
					count++;
				map.put(nums[i], 2);
				continue;
			}
			if (map.containsKey(nums[i] - k))
				count++;
			if (map.containsKey(nums[i] + k))
				count++;
			map.put(nums[i], 1);
		}
		return count;
	}

	// Q530. Minimum Absolute Difference in BST
	// Given a binary search tree with non-negative values, find the minimum
	// absolute difference between values of any two nodes.
	// first method is to get the inorder traversal and then you can get your
	// solution
	// but the follow up question says what if the tree is not BST
	int min = Integer.MAX_VALUE;
	Integer prev = null;

	public int getMinimumDifference(TreeNode root) {
		if (root == null)
			return min;

		getMinimumDifference(root.left);

		if (prev != null) {
			min = Math.min(min, root.val - prev);
		}
		prev = root.val;

		getMinimumDifference(root.right);

		return min;
	}

	// Q521 Longest UnCommon Subsequence I
	// Given a group of two strings, you need to find the longest uncommon
	// subsequence of this group of two strings.
	// The longest uncommon subsequence is defined as the longest subsequence of
	// one of these strings and this subsequence
	// should not be any subsequence of the other strings.
	// A subsequence is a sequence that can be derived from one sequence by
	// deleting some characters without changing the order of the remaining
	// elements. Trivially, any string is a subsequence of itself and an empty
	// string is a subsequence of any string.
	// The input will be two strings, and the output needs to be the length of
	// the longest uncommon subsequence. If the longest uncommon subsequence
	// doesn't exist, return -1.
	// Example 1: Input: "aba", "cdc" Output: 3
	// Explanation: The longest uncommon subsequence is "aba" (or "cdc"),
	// because "aba" is a subsequence of "aba",
	// but not a subsequence of any other strings in the group of two strings.

	public int findLUSlength(String a, String b) {
		return a.equals(b) ? -1 : Math.max(a.length(), b.length());
	}

	// Q507 Perfect Number
	// We define the Perfect Number is a positive integer that is equal to the
	// sum of all its positive divisors except itself.
	// Now, given an integer n, write a function that returns true when it is a
	// perfect number and false when it is not.
	// Example: Input: 28 Output: True
	// Explanation: 28 = 1 + 2 + 4 + 7 + 14
	public boolean checkPerfectNumber(int num) {
		if (num == 1)
			return false;
		int sum = 1;
		int sqrtNum = (int) Math.sqrt(num);
		for (int i = 2; i <= sqrtNum; ++i) {
			if (num % i == 0) {
				sum += i + num / i;
			}
		}
		return sum == num;
	}

	// Q496 Next greater Element I
	// You are given two arrays (without duplicates) nums1 and nums2 where
	// nums1’s elements are subset of nums2.
	// Find all the next greater numbers for nums1's elements in the
	// corresponding places of nums2.
	// The Next Greater Number of a number x in nums1 is the first greater
	// number to its right in nums2.
	// If it does not exist, output -1 for this number.
	// Solution: Suppose we have a decreasing sequence followed by a greater
	// number
	// For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next
	// greater element for all previous numbers
	// in the sequence. We use a stack to keep a decreasing sub-sequence,
	// whenever we see a number x greater than stack.peek()
	// we pop all elements less than x and for all the popped ones, their next
	// greater element is x
	// For example [9, 8, 7, 3, 2, 1, 6]
	// The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which
	// is greater than 1 so we pop 1 2 3 whose next greater element should be 6
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // map from x to next
														// greater element of x
		Stack<Integer> stack1 = new Stack<>();
		for (int num : nums) {
			while (!stack1.isEmpty() && stack1.peek() < num)
				map.put(stack1.pop(), num);
			stack1.push(num);
		}
		for (int i = 0; i < findNums.length; i++)
			findNums[i] = map.getOrDefault(findNums[i], -1);
		return findNums;
	}

	// Q492 construct the rectangle
	// For a web developer, it is very important to know how to design a web
	// page's size. So, given a specific rectangular
	// web page’s area, your job by now is to design a rectangular web page,
	// whose length L and width W satisfy the following requirements:
	// 1. The area of the rectangular web page you designed must equal to the
	// given target area.
	// 2. The width W should not be larger than the length L, which means L >=
	// W.
	// 3. The difference between length L and width W should be as small as
	// possible.

	/*
	 * The W is always less than or equal to the square root of area so we start
	 * searching at sqrt(area) till we find the result
	 */
	public int[] constructRectangle(int area) {
		int w = (int) Math.sqrt(area);
		while (area % w != 0)
			w--;
		return new int[] { area / w, w };
	}

	// Q476 Number Compliment #GoodQuestion
	// Given a positive integer, output its complement number. The complement
	// strategy is to flip the bits of its binary representation.
	public int findComplement(int num) {
		int i = 0;
		int j = 0;
		while (i < num) {
			i += Math.pow(2, j);
			j++;
		}
		return i - num;
	}

	// Another way
	public int findComplement2(int num) {
		String s = Integer.toBinaryString(num);
		int t = (int) (Math.pow(2, s.length()) - 1);
		return t - num;
	}

	// Heaters // dint understand the logic
	// Winter is coming! Your first job during the contest is to design a
	// standard heater with fixed warm radius to warm all the houses.
	// Now, you are given positions of houses and heaters on a horizontal line,
	// find out minimum radius of heaters so that all houses could be covered by
	// those heaters.
	// So, your input will be the positions of houses and heaters seperately,
	// and your expected output will be the minimum radius standard of heaters.
	// The idea is to leverage decent Arrays.binarySearch() function provided by
	// Java.
	// For each house, find its position between those heaters (thus we need the
	// heaters array to be sorted).
	// Calculate the distances between this house and left heater and right
	// heater, get a MIN value of those two values. Corner cases are there is no
	// left or right heater.
	// Get MAX value among distances in step 2. It's the answer.
	// Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n
	// is the length of heaters.
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;
		for (int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if (index < 0) {
				index = -(index + 1);
			}
			int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
			int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
			result = Math.max(result, Math.min(dist1, dist2));
		}
		return result;
	}

	// Q463 Island Perimeter #GoodQuestion
	// You are given a map in form of a two-dimensional integer grid where 1
	// represents land and 0 represents water.
	// Grid cells are connected horizontally/vertically (not diagonally). The
	// grid is completely surrounded by water,
	// and there is exactly one island (i.e., one or more connected land cells).
	// The island doesn't have "lakes"
	// (water inside that isn't connected to the water around the island). One
	// cell is a square with side length 1.
	// The grid is rectangular, width and height don't exceed 100. Determine the
	// perimeter of the island

	// Solution add 4 for each land and remove 2 for each internal edge
	public static int islandPerimeter(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		int result = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					result += 4;
					if (i > 0 && grid[i - 1][j] == 1)
						result -= 2;
					if (j > 0 && grid[i][j - 1] == 1)
						result -= 2;
				}
			}
		}
		return result;
	}

	// Q374 guess number higher or lower
	// We are playing the Guess Game. The game is as follows:
	// I pick a number from 1 to n. You have to guess which number I picked.
	// Every time you guess wrong, I'll tell you whether the number is higher or
	// lower.
	// You call a pre-defined API guess(int num) which returns 3 possible
	// results (-1, 1, or 0):
	// -1 : My number is lower
	// 1 : My number is higher
	// 0 : Congrats! You got it!

	private int guess(int num) {
		// pre-defined api as per the question
		return 0;
	}

	public int guessNumber(int n) {
		int i = 1, j = n;
		while (i < j) {
			int mid = i + (j - i) / 2;
			if (guess(mid) == 0) {
				return mid;
			} else if (guess(mid) == 1) {
				i = mid + 1;
			} else {
				j = mid;
			}
		}
		return i;
	}

	// Q404 sum of left leaves #GoodQuestion
	// Recursive method. For given node we check whether its left child is a
	// leaf. If it is the case,
	// we add its value to answer, otherwise recursively call method on left
	// child. For right child we
	// call method only if it has at least one non-null child.
	public int sumOfLeftLeaves1(TreeNode root) {
		if (root == null)
			return 0;
		int ans = 0;
		if (root.left != null) {
			if (root.left.left == null && root.left.right == null)
				ans += root.left.val;
			else
				ans += sumOfLeftLeaves1(root.left);
		}
		ans += sumOfLeftLeaves1(root.right);
		return ans;
	}

	// Iterative method. Here for each node in the tree we check whether its
	// left child is a leaf.
	// If it is true, we add its value to answer, otherwise add left child to
	// the stack to process it later.
	// For right child we add it to stack only if it is not a leaf.
	public int sumOfLeftLeaves2(TreeNode root) {
		if (root == null)
			return 0;
		int ans = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.empty()) {
			TreeNode node = stack.pop();
			if (node.left != null) {
				if (node.left.left == null && node.left.right == null)
					ans += node.left.val;
				else
					stack.push(node.left);
			}
			if (node.right != null) {
				if (node.right.left != null || node.right.right != null)
					stack.push(node.right);
			}
		}
		return ans;
	}

	// Q113 PATH SUM II
	// Given a binary tree and a sum, find all root-to-leaf paths where each
	// path's sum equals the given sum.
	// For example:
	// Given the below binary tree and sum = 22,
	// 5
	// / \
	// 4 8
	// / / \
	// 11 13 4
	// / \ / \
	// 7 2 5 1
	// return
	// [
	// [5,4,11,2],
	// [5,8,4,5]
	// ]

	public List<List<Integer>> pathSum2(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList();
		List<Integer> path = new ArrayList();
		dfs(root, sum, res, path);
		return res;
	}

	private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
		if (root == null)
			return;
		path.add(root.val);
		if (root.left == null && root.right == null && root.val == sum)
			res.add(new ArrayList(path));
		dfs(root.left, sum - root.val, res, path);
		dfs(root.right, sum - root.val, res, path);
		path.remove(path.size() - 1);
	}

	// Iterative approach for the above same question. would be a little
	// difficult in solving
	public List<List<Integer>> pathSumII(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int SUM = 0;
		TreeNode cur = root;
		TreeNode pre = null;
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				path.add(cur.val);
				SUM += cur.val;
				cur = cur.left;
			}
			cur = stack.peek();
			if (cur.right != null && cur.right != pre) {
				cur = cur.right;
				continue;
			}
			if (cur.left == null && cur.right == null && SUM == sum)
				res.add(new ArrayList<Integer>(path));

			pre = cur;
			stack.pop();
			path.remove(path.size() - 1);
			SUM -= cur.val;
			cur = null;

		}
		return res;
	}

	// Q437 Path Sum III
	// You are given a binary tree in which each node contains an integer value.
	// Find the number of paths that sum to a given value.
	// The path does not need to start or end at the root or a leaf, but it must
	// go downwards (traveling only from parent nodes to child nodes).
	// The tree has no more than 1,000 nodes and the values are in the range
	// -1,000,000 to 1,000,000.
	// So the idea is similar as Two sum, using HashTable to store ( key : the
	// prefix sum, value : how many ways get to this prefix sum) , and whenever
	// reach a node, we check if prefix sum - target exists in hashmap or not,
	// if it does, we added up the ways of prefix sum - target into res.
	// For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will
	// be: 1, 3, 2, 1, 3, let's say we want to find target sum is 2, then we
	// will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.
	// I used global variable count, but obviously we can avoid global variable
	// by passing the count from bottom up. The time complexity is O(n). This is
	// my first post in discuss, open to any improvement or criticism. :)
	public int pathSum3(TreeNode root, int sum) {
		if (root == null)
			return 0;
		return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}

	private int pathSumFrom(TreeNode node, int sum) {
		if (node == null)
			return 0;
		return (node.val == sum ? 1 : 0) + pathSumFrom(node.left, sum - node.val)
				+ pathSumFrom(node.right, sum - node.val);
	}

	// below method is difficult to understand
	public int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap();
		preSum.put(0, 1);
		helper(root, 0, sum, preSum);
		return count;
	}

	int count = 0;

	public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
		if (root == null) {
			return;
		}
		currSum += root.val;
		if (preSum.containsKey(currSum - target)) {
			count += preSum.get(currSum - target);
		}
		if (!preSum.containsKey(currSum)) {
			preSum.put(currSum, 1);
		} else {
			preSum.put(currSum, preSum.get(currSum) + 1);
		}

		helper(root.left, currSum, target, preSum);
		helper(root.right, currSum, target, preSum);
		preSum.put(currSum, preSum.get(currSum) - 1);
	}

	// Q438 find all anagrams in a string
	// in the top the link is given, it can be done using sliding window
	// mechanism

	// Assign Cookies
	// Assume you are an awesome parent and want to give your children some
	// cookies. But, you should give each child at most one cookie. Each child i
	// has a greed factor gi, which is the minimum size of a cookie that the
	// child will be content with; and each cookie j has a size sj. If sj >= gi,
	// we can assign the cookie j to the child i, and the child i will be
	// content. Your goal is to maximize the number of your content children and
	// output the maximum number.
	//
	// Note:
	// You may assume the greed factor is always positive.
	// You cannot assign more than one cookie to one child

	public static int findContentChildren(int[] g, int[] s) {
		int count = 0;
		TreeMap<Integer, Integer> tree = new TreeMap<>();
		for (int temp : s) {
			Integer num = tree.get(temp);
			num = num == null ? 0 : num;
			tree.put(temp, num + 1);
		}
		for (int temp : g) {
			Integer targ = tree.ceilingKey(temp);
			if (targ != null) {
				Integer num = tree.get(targ);
				if (num > 0) {
					count++;
					if (num == 1) {
						tree.remove(targ);
					} else {
						tree.put(targ, num - 1);
					}
				}
			}
		}
		return count;
	}

	// Q459 repeated substring pattern
	// Given a non-empty string check if it can be constructed by taking a
	// substring of it and
	// appending multiple copies of the substring together. You may assume the
	// given string consists
	// of lowercase English letters only and its length will not exceed 10000.
	public boolean repeatedSubstringPattern(String str) {
		// This is the kmp issue
		int[] prefix = kmp(str);
		int len = prefix[str.length() - 1];
		int n = str.length();
		return (len > 0 && n % (n - len) == 0);
	}

	private int[] kmp(String s) {
		int len = s.length();
		int[] res = new int[len];
		char[] ch = s.toCharArray();
		int i = 0, j = 1;
		res[0] = 0;
		while (i < ch.length && j < ch.length) {
			if (ch[j] == ch[i]) {
				res[j] = i + 1;
				i++;
				j++;
			} else {
				if (i == 0) {
					res[j] = 0;
					j++;
				} else {
					i = res[i - 1];
				}
			}
		}
		return res;
	}

	// Q34 Find First and Last Position of Element in Sorted Array
	// #TopInterviewQuestion
	// Search for a Range
	// Given an array of integers sorted in ascending order, find the starting
	// and ending position of a given target value.
	// Your algorithm's runtime complexity must be in the order of O(log n).
	// If the target is not found in the array, return [-1, -1].

	// Solution: If A[mid] < target, then the range must begins on the right of
	// mid (hence i = mid+1 for the next iteration)
	// If A[mid] > target, it means the range must begins on the left of mid (j
	// = mid-1)
	// If A[mid] = target, then the range must begins on the left of or at mid
	// (j= mid)
	// Since we would move the search range to the same side for case 2 and 3,
	// we might as well merge them as one single case so that less code is
	// needed:
	//
	// If A[mid] >= target, j = mid;
	public int[] searchRange2(int[] nums, int target) {
		int[] result = new int[2];
		result[0] = findFirst(nums, target);
		result[1] = findLast(nums, target);
		return result;
	}

	private int findFirst(int[] nums, int target) {
		int idx = -1;
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[mid] >= target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
			if (nums[mid] == target)
				idx = mid;
		}
		return idx;
	}

	private int findLast(int[] nums, int target) {
		int idx = -1;
		int start = 0;
		int end = nums.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (nums[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
			if (nums[mid] == target)
				idx = mid;
		}
		return idx;
	}

	// Q54 Spiral Matrix #TopInterviewQuestion
	// Given a matrix of m x n elements (m rows, n columns), return all elements
	// of the matrix in spiral order.
	private static void spiralOrder(int[][] arr, int row, int column) {
		int T = 0;
		int B = row - 1;
		int L = 0;
		int R = column - 1;
		int direction = 0;
		while (T <= B && L <= R) {
			if (direction == 0) {
				for (int i = L; i <= R; i++) {
					System.out.print(arr[T][i] + "  ");
				}
				T++;
			}

			else if (direction == 1) {
				for (int i = T; i <= B; i++) {
					System.out.print(arr[i][R] + "  ");

				}
				R--;
			}

			else if (direction == 2) {
				for (int i = R; i >= L; i--) {
					System.out.print(arr[B][i] + "  ");

				}
				B--;
			}

			else if (direction == 3) {
				for (int i = B; i >= T; i--) {
					System.out.print(arr[i][L] + "  ");

				}
				L++;
			}
			direction = (direction + 1) % 4;
		}
	}

	// generate matrix
	// Spiral Matrix II
	// Given an integer n, generate a square matrix filled with elements from 1
	// to n2 in spiral order.
	// For example,
	// Given n = 3,
	// You should return the following matrix:
	// [
	// [ 1, 2, 3 ],
	// [ 8, 9, 4 ],
	// [ 7, 6, 5 ]
	// ]
	public static int[][] generateMatrix(int n) {
		int[][] ret = new int[n][n];
		int left = 0, top = 0;
		int right = n - 1, down = n - 1;
		int count1 = 1;
		while (left <= right) {
			for (int j = left; j <= right; j++) {
				ret[top][j] = count1++;
			}
			top++;
			for (int i = top; i <= down; i++) {
				ret[i][right] = count1++;
			}
			right--;
			for (int j = right; j >= left; j--) {
				ret[down][j] = count1++;
			}
			down--;
			for (int i = down; i >= top; i--) {
				ret[i][left] = count1++;
			}
			left++;
		}
		return ret;
	}

	// Q60 permutation sequence #GoodQuestion
	// The set [1,2,3,…,n] contains a total of n! unique permutations.
	// By listing and labeling all of the permutations in order,
	// We get the following sequence (ie, for n = 3):
	// "123"
	// "132"
	// "213"
	// "231"
	// "312"
	// "321"
	// Given n and k, return the kth permutation sequence.
	// Note: Given n will be between 1 and 9 inclusive.
	// very intersting question and clearly explained in
	// https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n
	public String getPermutation(int n, int k) {
		int pos = 0;
		List<Integer> numbers = new ArrayList<>();
		int[] factorial = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		// create an array of factorial lookup
		int sum = 1;
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
			factorial[i] = sum;
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}
		// create a list of numbers to get indices
		for (int i = 1; i <= n; i++) {
			numbers.add(i);
		}
		// numbers = {1, 2, 3, 4}
		k--;
		for (int i = 1; i <= n; i++) {
			int index = k / factorial[n - i];
			sb.append(String.valueOf(numbers.get(index)));
			numbers.remove(index);
			k -= index * factorial[n - i];
		}
		return String.valueOf(sb);
	}

	// Q81 Search in rotated sorted array II
	public boolean search(int A[], int n, int target) {
		int lo = 0, hi = n - 1;
		int mid = 0;
		while (lo < hi) {
			mid = (lo + hi) / 2;
			if (A[mid] == target)
				return true;
			if (A[mid] > A[hi]) {
				if (A[mid] > target && A[lo] <= target)
					hi = mid;
				else
					lo = mid + 1;
			} else if (A[mid] < A[hi]) {
				if (A[mid] < target && A[hi] >= target)
					lo = mid + 1;
				else
					hi = mid;
			} else {
				hi--;
			}
		}
		return A[lo] == target ? true : false;
	}

	// Q153 find minimum in rotated sorted array #GoodQuestion
	// You may assume no duplicate exists in the array.
	int findMin2(int[] num) {
		int start = 0, end = num.length - 1;

		while (start < end) {
			if (num[start] < num[end])
				return num[start];

			int mid = (start + end) / 2;

			if (num[mid] >= num[start]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		return num[start];
	}

	// Q154 minimum in rotated sorted array with duplicates allowed..send the
	// lower index

//    We assert the loop invariant is the index of the minimum, min, is within the range [lo, hi].
//
//    Before the loop, min is in [0, nums.length - 1]. To satisfy the invariant, lo = 0, hi = nums.length - 1
//    If we guess mi,
//if nums[mi] > nums[hi], min should be always in [mi + 1, hi] (explained in Essence). To satisfy the invariant, lo = mi + 1;
//else if nums[mi] < nums[lo], min should be always in [lo + 1, mi] (explained in Essence), to satisfy the assertion, hi = mi, lo = lo + 1;
//else (nums[lo] <= nums[mi] <= nums[hi]) min should be always nums[lo].
//    After the loop, lo = hi, min should be in [lo, lo], to satisfy the assertion, min = lo.
//            Essence
//    If we split the array with mi into [lo, mi] and [mi, hi]. If [lo, mi] is not sorted, since we detect [lo, mi] is not sorted by nums[lo] > nums[mi] so nums[lo] cannot be min, min must be within (lo, mi]. If [mi, hi] is not sorted, min must be within (mi, hi] - since we detect [mi, hi] is not sorted by nums[mi] > nums[hi], nums[mi] cannot be min. If they are both sorted, nums[lo] is the min.
//            There are 4 kinds of relationship among num[lo], nums[mi], nums[hi]
//
// nums[lo] <= nums[mi] <= nums[hi], min is nums[lo]
// nums[lo] > nums[mi] <= nums[hi], (lo, mi] is not sorted, min is inside
// nums[lo] <= nums[mi] > nums[hi], (mi, hi] is not sorted, min is inside
// nums[lo] > nums[mi] > nums[hi], impossible
    public int findMin(int[] nums) {

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (nums[mi] > nums[hi]) {
                lo = mi + 1;
            }
            else if (nums[mi] < nums[lo]) {
                hi = mi;
                lo++;
            }
            else { // nums[lo] <= nums[mi] <= nums[hi]
                hi--;
            }
        }

        return nums[lo];
    }

	// Q41 First Missing Positive #TopInterviewQuestion
	// Given an unsorted integer array, find the smallest missing positive
	// integer.
	// second solution is better
	public static int firstMissingPositiveAnd0(int A[]) {
		int n = A.length;
		for (int i = 0; i < n; i++) {
			while (A[i] != i + 1) {
				if (A[i] <= 0 || A[i] >= n)
					break;
				if (A[i] == A[A[i] - 1])
					break;
				int temp = A[i];
				A[i] = A[temp - 1];
				A[temp - 1] = temp;
			}
		}
		for (int i = 0; i < n; i++) {
			if (A[i] != i + 1) {
				return i + 1;
			}
		}
		return n + 1;
	}

	// this looks better
	public int firstMissingPositive(int[] A) {
		int i = 0;
		while (i < A.length) {
			if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length)
				i++;
			else if (A[A[i] - 1] != A[i])
				swap(A, i, A[i] - 1);
			else
				i++;
		}
		i = 0;
		while (i < A.length && A[i] == i + 1)
			i++;
		return i + 1;
	}

	private void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	// Q11 Container with Most Water #TopInterviewQuestion
	// Given n non-negative integers a1, a2, ..., an, where each represents a
	// point at coordinate (i, ai).
	// n vertical lines are drawn such that the two endpoints of line i is at
	// (i, ai) and (i, 0).
	// Find two lines, which together with x-axis forms a container, such that
	// the container contains
	// the most water.

	// AKA, the general idea to find some max is to go through all cases where max value can possibly occur
	// and keep updating the max value. The efficiency of the scan depends on the size of cases you plan to
	// scan. To increase efficiency, all we need to do is to find a smart way of scan to cut off the useless
	// cases and meanwhile 100% guarantee the max value can be reached through the rest of cases.
	// In this problem, the smart scan way is to set two pointers initialized at both ends of the array.
	// Every time move the smaller value pointer to inner array. Then after the two pointers meet, all possible
	// max cases have been scanned and the max situation is 100% reached somewhere in the scan.

	public int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int maxArea = 0;
		while (left < right) {
//			maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            int currentSum = (right - left )* Math.min(height[left] , height[right]);
            maxArea = Math.max(maxArea, currentSum);

            if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxArea;
	}

// Q42 Trapping Rain Water #TopInterviewQuestion
// very well explained https://www.youtube.com/watch?v=pq7Xon_VXeU
// Given n non-negative integers representing an elevation map where the
// width of each bar is 1, compute how much water it is able to trap after raining
	public int trap(int A[], int n) {
		int left = 0;
		int right = n - 1;
		int maxLeft = 0, maxRight = 0;
		int sum = 0;
		while (left <= right) {
			if (A[left] <= A[right]) {
				if (A[left] >= maxLeft) {
					maxLeft = A[left]; // when we are updating max left we are
										// not making any calculation as doing a
					// calculation means we are spilling the water.
				} else {
					sum += maxLeft - A[left];
				}
				left++;
			} else {
				if (A[right] >= maxRight) {
					maxRight = A[right];
				} else {
					sum += maxRight - A[right];
				}
				right--;
			}

		}
		return sum;
	}

	// Q205 isomorphic Strings
	// Given two strings s and t, determine if they are isomorphic.
	// Two strings are isomorphic if the characters in s can be replaced to get
	// t.
	// All occurrences of a character must be replaced with another character
	// while preserving the order of characters.
	// No two characters may map to the same character but a character may map
	// to itself.
	// For example,
	// Given "egg", "add", return true.
	public boolean isIsomorphic(String s, String t) {
		if (s == null || s.length() <= 1)
			return true;
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			char b = t.charAt(i);
			if (map.containsKey(a)) {
				if (map.get(a).equals(b))
					continue;
				else
					return false;
			} else {
				if (!map.containsValue(b))
					map.put(a, b);
				else
					return false;

			}
		}
		return true;
	}

	// Another implementation of isomorphic strings
	public boolean isIsomorphic2(String s1, String s2) {
		int[] m = new int[512];
		for (int i = 0; i < s1.length(); i++) {
			if (m[s1.charAt(i)] != m[s2.charAt(i) + 256])
				return false;
			m[s1.charAt(i)] = m[s2.charAt(i) + 256] = i + 1;
		}
		return true;
	}

	// Q139 Word Break Problem #TopInterviewQuestion
	// Given a non-empty string s and a dictionary wordDict containing a list of
	// non-empty words, determine if s can be segmented
	// into a space-separated sequence of one or more dictionary words. You may
	// assume the dictionary does not contain duplicate words.
	// For example, given
	// s = "leetcode",
	// dict = ["leet", "code"].
	// Return true because "leetcode" can be segmented as "leet code".
	public boolean wordBreak(String s, Set<String> dict) {
		boolean[] f = new boolean[s.length() + 1];
		f[0] = true;

		/*
		 * First DP for(int i = 1; i <= s.length(); i++){ for(String str: dict){
		 * if(str.length() <= i){ if(f[i - str.length()]){
		 * if(s.substring(i-str.length(), i).equals(str)){ f[i] = true; break; }
		 * } } } }
		 */

		// Second DP
		// amazing solution
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (f[j] && dict.contains(s.substring(j, i))) {
					f[i] = true; // this means the word is valid
					break;
				}
			}
		}
		return f[s.length()];
	}

	// Word break II #TopInterviewQuestion
	// Given a non-empty string s and a dictionary wordDict containing a list of
	// non-empty words,
	// add spaces in s to construct a sentence where each word is a valid
	// dictionary word.
	// You may assume the dictionary does not contain duplicate words.]
	// Return all such possible sentences.
	// For example, given
	// s = "catsanddog",
	// dict = ["cat", "cats", "and", "sand", "dog"].
	// A solution is ["cats and dog", "cat sand dog"].
	// difficult to understand
	HashMap<String, List<String>> map = new HashMap<String, List<String>>();

	public List<String> wordBreak1(String s, Set<String> wordDict) {
		List<String> res = new ArrayList<String>();
		if (s == null || s.length() == 0) {
			return res;
		}
		if (map.containsKey(s)) {
			return map.get(s);
		}
		if (wordDict.contains(s)) {
			res.add(s);
		}
		for (int i = 1; i < s.length(); i++) {
			String t = s.substring(i);
			if (wordDict.contains(t)) {
				List<String> temp = wordBreak1(s.substring(0, i), wordDict);
				if (temp.size() != 0) {
					for (int j = 0; j < temp.size(); j++) {
						res.add(temp.get(j) + " " + t);
					}
				}
			}
		}
		map.put(s, res);
		return res;
	}


	// Q120 Triangle #GoodQuestion
	// Given a triangle, find the minimum path sum from top to bottom. Each step
	/////////////////////////////////////// you may move to adjacent numbers on
	/////////////////////////////////////// the row below.
	// For example, given the following triangle
	// [
	// [2],
	// [3,4],
	// [6,5,7],
	// [4,1,8,3]
	// ]
	// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	public int minimumTotal(List<List<Integer>> triangle) {
		Deque<Integer> queue = new LinkedList<Integer>();
		int count = triangle.size();
		queue.add(triangle.get(0).get(0));
		for (int i = 1; i < count; i++) {
			List<Integer> list = triangle.get(i);
			for (int j = 0; j <= i; j++) {
				int min = 0;
				if (j == 0)
					min = list.get(0) + queue.peekFirst();
				else if (j == i)
					min = list.get(j) + queue.pollFirst();
				else
					min = Math.min(queue.pollFirst(), queue.peekFirst()) + list.get(j);
				queue.addLast(min);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++)
			result = Math.min(result, queue.pollFirst());
		return result;
	}

	// or below is the easy method
	// Go from bottom to top.
	// We start form the row above the bottom row [size()-2].
	// Each number add the smaller number of two numbers that below it.
	// And finally we get to the top we the smallest sum
	public static int minimumTotal3(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; i--)
			for (int j = 0; j <= i; j++)
				triangle.get(i).set(j,
						triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
		return triangle.get(0).get(0);
	}

	// Q179 largest number #TopInterviewQuestino
	// Given a list of non negative integers, arrange them such that they form
	// the largest number.
	// For example, given [3, 30, 34, 5, 9], the largest formed number is
	// 9534330.
	// Note: The result may be very large, so you need to return a string
	// instead of an integer.
	public String largestNumber(int[] num) {
		if (num == null || num.length == 0)
			return "";

		// Convert int array to String array, so we can sort later on
		String[] s_num = new String[num.length];
		for (int i = 0; i < num.length; i++)
			s_num[i] = String.valueOf(num[i]);

		// Comparator to decide which string should come first in concatenation
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				String s1 = str1 + str2;
				String s2 = str2 + str1;

				return s2.compareTo(s1); // reverse order here, so we can do append() later
			}
		};

		Arrays.sort(s_num, comp);

// the whole above commented out lines can be replaced by one below line.
// Arrays.sort(s_num, (a, b) -> (b + a).compareTo(a + b));
// An extreme edge case by lc, say you have only a bunch of 0 in your int array
		if (s_num[0].charAt(0) == '0')
			return "0";

		StringBuilder sb = new StringBuilder();
		for (String s : s_num)
			sb.append(s);
		return sb.toString();

	}

	// Q134 Gas Station #TopInterviewQuestion
// There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
// You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
// You begin the journey with an empty tank at one of the gas stations. Return the starting gas station's index if you can
// travel around the circuit once in the clockwise direction, otherwise return -1.

	// The reason why I think this works:
	// 1, if sum of gas is more than sum of cost, then there must be a solution.
	// And the question guaranteed that the solution is unique(The first one I
	// found is the right one).
	// 2, The tank should never be negative, so restart whenever there is a
	// negative number.
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0, total = 0, tank = 0;

// if car fails at 'start', record the next station
		for (int i = 0; i < gas.length; i++) {
			tank += gas[i] - cost[i];
			if (tank < 0) {
				start = i + 1; // move starting position forward
				total += tank; // add the negative tank value to total
				tank = 0; // reset tank
			}
		}
// negative total + positive tank should be 0 or more, if so we can do a round trip and return start
		return (total + tank < 0) ? -1 : start;
	}

	// Basic Calculator 1
	// Implement a basic calculator to evaluate a simple expression string.
	// The expression string may contain open ( and closing parentheses ), the
	// plus + or minus sign -, non-negative integers and empty spaces .
	// One important thing is that the input is valid, which means the
	// parentheses are always paired and in order.
	// Only 5 possible input we need to pay attention:
	//
	// digit: it should be one digit from the current number
	// '+': number is over, we can add the previous number and start a new
	// number
	// '-': same as above
	// '(': push the previous result and the sign into the stack, set result to
	// 0, just calculate the new result within the parenthesis.
	// ')': pop out the top two numbers from stack, first one is the sign before
	// this pair of parenthesis, second is the temporary result before this pair
	// of parenthesis. We add them together.
	// Finally if there is only one number, from the above solution, we haven't
	// add the number to the result, so we do a check see if the number is zero.
	public static int calculate1(String s) {
		int len = s.length(), sign = 1, result = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				int sum = s.charAt(i) - '0';
				while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
					sum = sum * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				result += sum * sign;
			} else if (s.charAt(i) == '+')
				sign = 1;
			else if (s.charAt(i) == '-')
				sign = -1;
			else if (s.charAt(i) == '(') {
				stack.push(result);
				stack.push(sign);
				result = 0;
				sign = 1;
			} else if (s.charAt(i) == ')') {
				result = result * stack.pop() + stack.pop();
			}
		}
		return result;
	}

	// Q227 Basic Calculator II #TopInterviewQuestion
	public int calculate(String s) {
		int len;
		if (s == null || (len = s.length()) == 0)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
			}
			if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
				if (sign == '-') {
					stack.push(-num);
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num);
				}
				if (sign == '/') {
					stack.push(stack.pop() / num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}
		int re = 0;
		for (int i : stack) {
			re += i;
		}
		return re;
	}

	// Q166 Fraction to recurring decimal #TopInterviewQuestion
	// Given two integers representing the numerator and denominator of a
	// fraction, return the fraction in string format.
	// If the fractional part is repeating, enclose the repeating part in
	// parentheses.
	// For example,
	// Given numerator = 1, denominator = 2, return "0.5".
	// Given numerator = 2, denominator = 1, return "2".
	// Given numerator = 2, denominator = 3, return "0.(6)".
	public String fractionToDecimal(int numerator, int denominator) {
		if (denominator == 0)
			return "NaN";
		if (numerator == 0)
			return "0";
		StringBuilder result = new StringBuilder();
		Long n = new Long(numerator);
		Long d = new Long(denominator);
		// negative or positive
		if (n * d < 0)
			result.append("-");
		n = Math.abs(n);
		d = Math.abs(d);

		result.append(Long.toString(n / d));
		// result is integer or float
		if (n % d == 0)
			return result.toString();
		else
			result.append(".");

		// deal with the float part
		// key is the remainder, value is the start positon of possible repeat
		// numbers
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		Long r = n % d;
		while (r > 0) {
			if (map.containsKey(r)) {
				result.insert(map.get(r), "(");
				result.append(")");
				break;
			}
			map.put(r, result.length());
			r *= 10;
			result.append(Long.toString(r / d));
			r %= d;
		}
		return result.toString();
	}

	// Q209 minimum size subarray sum #GoodQuestion
	// Given an array of n positive integers and a positive integer s, find the
	// minimal length of a contiguous subarray
	// of which the sum ≥ s. If there isn't one, return 0 instead.
	// For example, given the array [2,3,1,2,4,3] and s = 7,
	// the subarray [4,3] has the minimal length under the problem constraint.
	// this method is better

	// good explanation https://www.youtube.com/watch?v=NKoHjWl2m8Y
	// i think the above and the below method is the same .. a little here and
	// there
	// keeping two pointers start and end both innitialized at 0 when sum
	// exceeds the target, increment start
	// else keep on incrementing the end
	// Returns length of smallest subarray with sum greater than s.
	// If there is no subarray with given sum, then returns n+1
	static int smallestSubWithSum(int nums[], int s) {
		int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
		while (end < nums.length) {
			while (end < nums.length && sum < s)
				sum += nums[end++];
			if (sum < s)
				break;
			while (start < end && sum >= s)
				sum -= nums[start++];
			if (end - start + 1 < minLen)
				minLen = end - start + 1;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	// Q238 Product of Array Except Self #TopInterviewQuestion
	// Given an array of n integers where n > 1, nums, return an array output
	// such that output[i] is equal to the product of all the elements of nums
	// except nums[i].
	// Solve it without division and in O(n).
	// For example, given [1,2,3,4], return [24,12,8,6].
	// its more of calculating the left and right of the required number
	// output = [
	// 24, // left: init=1 nums[i]=1 right: 2 * 3 * 4
	// 12, // left: 1 nums[i]=2 right: 3 * 4
	// 8, // left: 1 * 2 nums[i]=3 right: 4
	// 6, // left: 1 * 2 * 3 nums[i]=4 right: init=1
	// ]

	// both the below methods are good
	public int[] productExceptSelf2(int[] nums) {
		int[] result = new int[nums.length];
		for (int i = 0, tmp = 1; i < nums.length; i++) {
			result[i] = tmp;
			tmp *= nums[i];
		}
		for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
			result[i] *= tmp;
			tmp *= nums[i];
		}
		return result;
	}

	// or below method
	public static void prod(int[] input) {
		int n = input.length;
		int[] leftArr = new int[n];
		int left = 1;
		// Traverse from the left
		for (int i = 0; i < n; ++i) {
			leftArr[i] = left;
			left = left * input[i];
		}

		// Traverse from the right
		int right = 1;
		int[] prodArray = leftArr;
		for (int i = n - 1; i >= 0; --i) {
			prodArray[i] = right * prodArray[i];
			right = right * input[i];
		}
		System.out.println(Arrays.toString(prodArray));
	}

	// Summary Ranges
	// Given a sorted integer array without duplicates, return the summary of
	// its ranges.
	// Example 1:
	// Input: [0,1,2,4,5,7]
	// Output: ["0->2","4->5","7"]
	// Example 2:
	// Input: [0,2,3,4,6,8,9]
	// Output: ["0","2->4","6","8->9"]
	public List<String> summaryRanges(int[] nums) {
		List<String> list = new ArrayList();
		if (nums.length == 1) {
			list.add(nums[0] + "");
			return list;
		}
		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			while (i + 1 < nums.length && (nums[i + 1] - nums[i]) == 1) {
				i++;
			}
			if (a != nums[i]) {
				list.add(a + "->" + nums[i]);
			} else {
				list.add(a + "");
			}
		}
		return list;
	}

	// Q221 Maximal square #GoodQuestion
	// Given a 2D binary matrix filled with 0's and 1's, find the largest square
	// containing only 1's and return its area.
	// For example, given the following matrix:
	// 1 0 1 0 0
	// 1 0 1 1 1
	// 1 1 1 1 1
	// 1 0 0 1 0
	// Return 4.
	public int maximalSquare(char[][] a) {
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

	// Maximal rectangle
	// Given a 2D binary matrix filled with 0's and 1's, find the largest square
	// containing only 1's and return its area.
	// For example, given the following matrix:
	// 1 0 1 0 0
	// 1 0 1 1 1
	// 1 1 1 1 1
	// 1 0 0 1 0
	// Return 6.
	public static int maximum(int input[][]) {
		int temp[] = new int[input[0].length];
		int maxArea = 0;
		int area;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (input[i][j] == 0) {
					temp[j] = 0;
				} else {
					temp[j] += input[i][j];
				}
			}
			area = maxHistogram(temp);
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}

	// Q84 Largest rectangle in histogram #TopInterviewQuestion
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
				// if stack is empty means everything till i has to be
				// greater or equal to input[top] so get area by
				// input[top] * i;
				if (stack.isEmpty()) {
					area = input[top] * i;
				}
				// if stack is not empty then everythin from i-1 to input.peek()
				// + 1
				// has to be greater or equal to input[top]
				// so area = input[top]*(i - stack.peek() - 1);
				else {
					area = input[top] * (i - stack.peekFirst() - 1);
				}
				if (area > maxArea) {
					maxArea = area;
				}
			}
		}
		while (!stack.isEmpty()) {
			int top = stack.pollFirst();
			// if stack is empty means everything till i has to be
			// greater or equal to input[top] so get area by
			// input[top] * i;
			if (stack.isEmpty()) {
				area = input[top] * i;
			}
			// if stack is not empty then everything from i-1 to input.peek() +
			// 1
			// has to be greater or equal to input[top]
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

	// Q296 Best Meeting point
	// A group of two or more people wants to meet and minimize the total travel
	// distance.
	// You are given a 2D grid of values 0 or 1, where each 1 marks the home of
	// someone in the group.
	// The distance is calculated using Manhattan Distance, where distance(p1,
	// p2) = |p2.x - p1.x| + |p2.y - p1.y|.
	// For example, given three people living at (0,0), (0,4), and (2,2):
	//
	// 1 - 0 - 0 - 0 - 1
	// | | | | |
	// 0 - 0 - 0 - 0 - 0
	// | | | | |
	// 0 - 0 - 1 - 0 - 0
	// The point (0,2) is an ideal meeting point, as the total travel distance
	// of 2+2+2=6 is minimal. So return 6.
	// 1) Store all horizontal and vertical positions of all group member.
	// 2) Now sort it to find minimum middle position, which will be the best
	// meeting point.
	// 3) Find the distance of all members from best meeting point.

	// this is the accepted solution
	public int minTotalDistance2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		ArrayList<Integer> cols = new ArrayList<Integer>();
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					rows.add(i);
					cols.add(j);
				}
			}
		}

		int sum = 0;

		for (Integer i : rows) {
			sum += Math.abs(i - rows.get(rows.size() / 2));
		}

		Collections.sort(cols);

		for (Integer i : cols) {
			sum += Math.abs(i - cols.get(cols.size() / 2));
		}

		return sum;
	}

	// Q173 Binary Search Tree Iterator BST iterator #GoodQuestion
	// Implement an iterator over a binary search tree (BST). Your iterator will
	// be initialized with the root node of a BST.
	// Calling next() will return the next smallest number in the BST.
	// Note: next() and hasNext() should run in average O(1) time and uses O(h)
	// memory, where h is the height of the tree.
	private Stack<TreeNode> stack = new Stack<>();

	public void constructor(TreeNode root) {
		pushAll(root);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode tmpNode = stack.pop();
		pushAll(tmpNode.right);
		return tmpNode.val;
	}

	private void pushAll(TreeNode node) {
		for (; node != null; stack.push(node), node = node.left)
			;
	}

	// Q155 Min Stack #TopInterviewQuestion
	// Design a stack that supports push, pop, top, and retrieving the minimum
	// element in constant time.
	// push(x) -- Push element x onto stack.
	// pop() -- Removes the element on top of the stack.
	// top() -- Get the top element.
	// getMin() -- Retrieve the minimum element in the stack.

	// second method (using 2 stacks) is better
	int min1 = Integer.MAX_VALUE;
	Stack<Integer> stack1 = new Stack<Integer>();

	public void push(int x) {
		// only push the old minimum value when the current minimum value
		// changes after pushing the new value x
		if (x <= min1) {
			stack1.push(min1);
			min1 = x;
		}
		stack1.push(x);
	}

	public void pop() {
		// if pop operation could result in the changing of the current minimum
		// value,
		// pop twice and change the current minimum value to the last minimum
		// value.
		if (stack1.pop() == min1)
			min1 = stack1.pop();
	}

	public int top() {
		return stack1.peek();
	}

	public int getMin() {
		return min1;

	}

	// Same above question but Using two stacks
	Stack<Integer> main_stack = new Stack<>();
	Stack<Integer> help_stack = new Stack<>();

	/** initialize your data structure here. */
	public void push3(int x) {
		main_stack.push(x);
		if (help_stack.isEmpty() || x <= help_stack.peek())
			help_stack.push(x);
	}

	public void pop3() {
		int top = main_stack.pop();
		if (top == help_stack.peek()) {
			help_stack.pop();
		}
	}

	public int top3() {
		return main_stack.peek();
	}

	public int getMin3() {
		return help_stack.peek();
	}

	// Range sum query - Immutable
	// Given an integer array nums, find the sum of the elements between indices
	// i and j (i ≤ j), inclusive.
	public class NumArray {
		private int[] nums;

		public NumArray(int[] nums) {
			for (int i = 1; i < nums.length; i++)
				nums[i] += nums[i - 1];
			this.nums = nums;
		}

		public int sumRange(int i, int j) {
			if (i == 0)
				return nums[j];
			return nums[j] - nums[i - 1];
		}
	}

	// have certain doubts
	// Q149 Max Points on a line ( maximum points on a plane )
	// #TopInterviewQuestion
	// Given n points on a 2D plane, find the maximum number of points that lie
	// on the same straight line.
	public int maxPoints(Point[] points) {
		if (points.length <= 0)
			return 0;
		if (points.length <= 2)
			return points.length;
		int result = 0;
		for (int i = 0; i < points.length; i++) {
			HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
			int samex = 1; // why is this innitialised to 1 and not 0
			int samep = 0;
			for (int j = 0; j < points.length; j++) {
				if (j != i) {
					if ((points[j].x == points[i].x) && (points[j].y == points[i].y)) {
						samep++;
					}
					if (points[j].x == points[i].x) {
						samex++;
						continue;
					}
					double k = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);
					if (hm.containsKey(k)) {
						hm.put(k, hm.get(k) + 1);
					} else {
						hm.put(k, 2);
					}
					result = Math.max(result, hm.get(k) + samep);
				}
			}
			result = Math.max(result, samex);
		}
		return result;
	}

	// or
	public int maxPoints2(Point[] points) {
		if (points == null || points.length == 0)
			return 0;

		HashMap<Double, Integer> result = new HashMap<Double, Integer>();
		int max = 0;

		for (int i = 0; i < points.length; i++) {
			int duplicate = 1;//
			int vertical = 0;
			for (int j = i + 1; j < points.length; j++) {
				// handle duplicates and vertical
				if (points[i].x == points[j].x) {
					if (points[i].y == points[j].y) {
						duplicate++;
					} else {
						vertical++;
					}
				} else {
					double slope = points[j].y == points[i].y ? 0.0
							: (1.0 * (points[j].y - points[i].y)) / (points[j].x - points[i].x);
					if (result.get(slope) != null) {
						result.put(slope, result.get(slope) + 1);
					} else {
						result.put(slope, 1);
					}
				}
			}
			for (Integer count : result.values()) {
				if (count + duplicate > max) {
					max = count + duplicate;
				}
			}
			max = Math.max(vertical + duplicate, max);
			result.clear();
		}
		return max;
	}

	// Q4 Median of Two Sorted Arrays #TopInterviewQuestion
	// very well explained by tushar
	// https://www.youtube.com/watch?v=LPFhl65R7ww&t=1206s
	public double findMedianSortedArrays(int input1[], int input2[]) {

		// if input1 length is greater than switch them so that input1 is
		// smaller than input2.
		if (input1.length > input2.length) {
			return findMedianSortedArrays(input2, input1);
		}
		int x = input1.length;
		int y = input2.length;
		int low = 0;
		int high = x;
		while (low <= high) {

			// binary search on a smaller size array
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX; // formula needs to
															// be remembered +1
															// coz
			// it plays well with both odd and even numbered sum

			// partition the array into two halves such that number of element
			// on each half is exactly same and
			// every element on the left half is less than every element on the
			// right

			// if partitionX is 0 it means nothing is there on left side. Use
			// -INF for maxLeftX
			// if partitionX is length of input then there is nothing on right
			// side. Use +INF for minRightX
			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				// We have partitioned array at correct place
				// Now get max of left elements and min of right elements to get
				// the median in case of even length combined array size
				// or get max of left for odd length combined array size.
				if ((x + y) % 2 == 0) {
					return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) { // we are too far on right side
												// for partitionX. Go on left
												// side.
				high = partitionX - 1;
			} else { // we are too far on left side for partitionX. Go on right
						// side.
				low = partitionX + 1;
			}
		}
		// Only we we can come here is if input arrays were not sorted. Throw in
		// that scenario.
		throw new IllegalArgumentException();
	}

	// Q252 Meeting Rooms I
	// Given an array of meeting time intervals consisting of start and end
	// times [[s1,e1],[s2,e2],...] (si < ei),
	// determine if a person could attend all meetings. For example, Given [[0,
	// 30],[5, 10],[15, 20]], return false.
	// If a person can attend all meetings, there must not be any overlaps
	// between any meetings.
	// After sorting the intervals, we can compare the current end and next
	// start.
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});

		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i].end > intervals[i + 1].start) {
				return false;
			}
		}
		return true;
	}

	// Q253 Meeting Rooms II #TopInterviewQuestion
	// Given an array of meeting time intervals consisting of start and end
	// times [[s1,e1],[s2,e2],...]
	// find the minimum number of conference rooms required.
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		});

		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(); // it has
																		// to be
																		// a
																		// priority
																		// queue
																		// instead
																		// of a
																		// simple
																		// queue..
		// for example [[0,30],[5,10],[15,20]]

		int count = 1;
		queue.offer(intervals[0].end);

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < queue.peek()) {
				count++;

			} else {
				queue.poll();
			}

			queue.offer(intervals[i].end);
		}
		return count;
	}

	// NOT a leetcode question BUT similar to the above one. Only difference
	// Meeting start time and end times are two different arrays
	// Returns minimum number of platforms required
	public static int findPlatform(int arr[], int dep[], int n) {
		// Sort arrival and departure arrays
		Arrays.sort(arr);
		Arrays.sort(dep);

		// plat_needed indicates number of platforms
		// needed at a time
		int plat_needed = 1, result = 1;
		int i = 1, j = 0;

		// Similar to merge in merge sort to process
		// all events in sorted order
		while (i < n && j < n) {
			// If next event in sorted order is arrival,
			// increment count of platforms needed
			if (arr[i] <= dep[j]) {
				plat_needed++;
				i++;

				// Update result if needed
				if (plat_needed > result)
					result = plat_needed;
			}

			// understood why is it decrementing it..but i guess there will be a
			// way where we dont need to decrement
			// Else decrement count of platforms needed
			else {
				plat_needed--;
				j++;
			}
		}

		return result;
	}

	// Q239 Sliding Window Maximum #TopInterviewQuestion
	// Given an array nums, there is a sliding window of size k which is moving
	// from the very left of the array
	// to the very right. You can only see the k numbers in the window. Each
	// time the sliding window moves right
	// by one position. Return the max sliding window.
	// very well explained by iDeserve
	// https://www.youtube.com/watch?v=39grPZtywyQ

	public void printMaxfromEachSubarray(int[] array, int k) {
		LinkedList<Integer> list = new LinkedList();
		// list will be storing the indexed and the not the actual elements
		for (int i = 0; i < k; i++) {
			// remove all useless elements present at the front of the list
			while (!list.isEmpty() && array[list.getLast()] < array[i]) {
				list.removeLast();
			}
			// add index of current element at the back
			list.addLast(i);
		}
		for (int i = k; i < array.length; i++) {
			// first element present in the list is the greatest element for the
			// last 'k' sized sub-array
			System.out.println(array[list.getFirst()]);

			// now remove all indices of elements from the list which do not
			// belong to current window
			while (!list.isEmpty() && (list.getFirst() < (i - k + 1))) {
				list.removeFirst();
			}

			// now again remove all useless elements present at the front of the
			// list
			// remove all useless elements present at the front of the list
			while (!list.isEmpty() && array[list.getLast()] < array[i]) {
				list.removeLast();
			}

			// and finally insert this new element at the back of the list
			list.add(i);
		}

		// now print the largest element from the last sub-array(window)
		System.out.println(array[list.getFirst()]);
	}

	// another way
	public static int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return new int[0];
		int[] result = new int[nums.length - k + 1];
		int storeIndex = 0;
		LinkedList<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			// remove numbers out of range k
			if (!deque.isEmpty() && deque.peekFirst() == i - k)
				deque.poll();
			// remove smaller numbers in k range as they are useless
			while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
				deque.removeLast();
			}
			deque.offer(i);
			if (i >= k - 1)
				result[storeIndex++] = nums[deque.peek()];
		}
		return result;
	}

	// Q346 Moving Average from the data stream #GoodQuestion
	// for saying we can say about the below methods which creates a queue of a
	// defined size
	// Queue<Integer> fifo = new CircularFifoQueue<Integer>(2);
	// Queue<Integer> fifo = EvictingQueue.create(2);
	public class MovingAverage {
		LinkedList<Integer> queue;
		int size;
		double avg;

		/** Initialize your data structure here. */
		public MovingAverage(int size) {
			this.queue = new LinkedList<Integer>();
			this.size = size;
		}

		public double next(int val) {
			if (queue.size() < this.size) {
				queue.offer(val);
				int sum = 0;
				for (int i : queue) {
					sum += i;
				}
				avg = (double) sum / queue.size();
				return avg;
			} else {
				int head = queue.poll();
				double minus = (double) head / this.size;
				queue.offer(val);
				double add = (double) val / this.size;
				avg = avg + add - minus;
				return avg;
			}
		}
	}

	// below is a better method coz in the above method the queue keeps on
	// increasing
	private int[] window;
	private int n1, insert;
	private long sum;

	/** Initialize your data structure here. */
	public void MovingAverageConstructor(int size) {
		window = new int[size];
		insert = 0;
		sum = 0;
	}

	public double next2(int val) {
		if (n1 < window.length)
			n1++;
		sum -= window[insert];
		sum += val;
		window[insert] = val;
		insert = (insert + 1) % window.length; // it stores the element directly
												// on the index so the size will
												// only be three

		return (double) sum / n1;
	}

	// Q 454 4sumII #TopInterviewQuestion
	// Given four lists A, B, C, D of integer values, compute how many tuples
	// (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

	// Take the arrays A and B, and compute all the possible sums of two
	// elements. Put the sum in the Hash map, and
	// increase the hash map value if more than 1 pair sums to the same value.
	// Compute all the possible sums of the arrays C and D. If the hash map
	// contains the opposite value of the
	// current sum, increase the count of four elements sum to 0 by the counter
	// in the map.
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		Map<Integer, Integer> sums = new HashMap<>();
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				int sum = A[i] + B[j];
				if (sums.containsKey(sum)) {
					sums.put(sum, sums.get(sum) + 1);
				} else {
					sums.put(sum, 1);
				}
			}
		}
		for (int k = 0; k < C.length; k++) {
			for (int z = 0; z < D.length; z++) {
				int sum = -(C[k] + D[z]);
				if (sums.containsKey(sum)) {
					count += sums.get(sum);
				}
			}
		}
		return count;
	}

	// Q387 First Unique Character in a String #TopInterviewQuestion
	// Given a string, find the first non-repeating character in it and return
	// it's index. If it doesn't exist, return -1.
	// do it either by hashmap or by taking an integer array of 26 characters
	public int firstUniqChar(String s) {
		int freq[] = new int[26];
		for (int i = 0; i < s.length(); i++)
			freq[s.charAt(i) - 'a']++;
		for (int i = 0; i < s.length(); i++)
			if (freq[s.charAt(i) - 'a'] == 1)
				return i;
		return -1;
	}

	// Q395 Longest Substring with At Least K Repeating Characters
	// #TopInterviewQuestion

	// in the first pass I record counts of every character in a hashmap
	// in the second pass I locate the first character that appear less than k
	// times in the string.
	// this character is definitely not included in the result, and that
	// separates the string into two parts.
	// keep doing this recursively and the maximum of the left/right part is the
	// answer.

	int longestSubstring(String s, int k) {
		if (s.length() == 0 || k > s.length())
			return 0;
		if (k == 0)
			return s.length();
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
			} else
				map.put(s.charAt(i), 1);
		}
		int idx = 0;
		while (idx < s.length() && map.get(s.charAt(idx)) >= k)
			idx++;
		if (idx == s.length())
			return s.length();
		int left = longestSubstring(s.substring(0, idx), k);
		int right = longestSubstring(s.substring(idx + 1), k);
		return Math.max(left, right);
	}

	// Q340 Longest substring with at most k distinct characters
	// #TopInterviewQuestion
	// https://www.youtube.com/watch?v=RHFrVNmlyA8&t=6s
	int lengthOfLongestSubstringKDistinct(String str, int k) {
		int s = 0, e = 0, maxlen = 0;
		Map<Character, Integer> dic = new HashMap<>();
		while (e < str.length()) {
			char c = str.charAt(e);
			if (dic.containsKey(c)) {
				dic.put(c, dic.get(c) + 1);

			} else {
				dic.put(c, 1);
			}

			while (dic.size() > k) {
				char ch = str.charAt(s);
				if (dic.get(ch) == 0)
					dic.remove(ch);
				else
					dic.put(ch, dic.get(ch) - 1);

				++s;
			}
			maxlen = Math.max(maxlen, e - s + 1);
			++e;
		}
		return maxlen;
	}

	// dont know something is wrong in the above solution. null pointer
	// exception.
	// below solution is working fine
	public int lengthOfLongestSubstringKDistinct2(String s, int k) {
		if (k == 0 || s == null || s.length() == 0)
			return 0;
		if (s.length() < k)
			return s.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLen = k;
		int left = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
			if (map.size() > k) {
				maxLen = Math.max(maxLen, i - left);
				while (map.size() > k) {
					char fc = s.charAt(left);
					if (map.get(fc) == 1) {
						map.remove(fc);
					} else {
						map.put(fc, map.get(fc) - 1);
					}
					left++;
				}
			}
		}
		maxLen = Math.max(maxLen, s.length() - left);
		return maxLen;
	}

	// Longest substring with m unique characters #GoodQuestion
	// https://www.youtube.com/watch?v=8AQra0p_HmI
	public static String solution(String s, Integer m) {
		int start = 0, end = 0, windowSize = 1, windowStart = 0;
		int size = s.length();
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		int ch = (int) s.charAt(0);
		hash.put(ch, 1);

		for (int i = 1; i < size; i++) {
			ch = (int) s.charAt(i);
			if (!hash.containsKey(ch)) {
				hash.put(ch, 1);
			} else {
				int temp = hash.get(ch);
				hash.put(ch, ++temp);
			}
			end++;

			// move start forward if number of unique characters is greater than
			// m
			while (!isLessThanM(hash, m)) {
				int temp = hash.get((int) s.charAt(start));
				hash.put((int) s.charAt(start), --temp);
				start++;
			}
			if (end - start + 1 > windowSize) {
				windowSize = end - start + 1;
				windowStart = start;
			}
		}

		return s.substring(windowStart, windowStart + windowSize);
	}

	public static boolean isLessThanM(HashMap<Integer, Integer> hash, Integer m) {
		int count = 0;
		for (Integer key : hash.keySet())
			if (hash.get(key) > 0)
				count++;

		return (count <= m);
	}

	// Q76 Given a string S and a string T, find the minimum window in S which
	// will contain all the characters in T
	// in complexity O(n).
	// Minimum Window Substring
	public static String minWindow(String s, String t) {
		Map<Character, Integer> map = new HashMap<>();
		for (char temp : s.toCharArray()) {
			map.put(temp, 0);
		}
		for (char temp : t.toCharArray()) {
			if (map.containsKey(temp)) {
				map.put(temp, map.get(temp) + 1);
			} else
				return "";
		}
		int start = 0, end = 0, minLength = Integer.MAX_VALUE, minStart = 0, numOfTargets = t.length();
		while (end < s.length()) {
			char current = s.charAt(end);
			if (map.get(current) > 0) {
				numOfTargets--;
			}
			map.put(current, map.get(current) - 1);
			while (numOfTargets == 0) {
				if (minLength > end - start + 1) {
					minLength = end - start + 1;
					minStart = start;
				}
				// this is like now we are moving the pointer start to the left
				// and increasing the pointer back to the position
				char head = s.charAt(start);
				if (map.get(head) >= 0)
					numOfTargets++;
				map.put(head, map.get(head) + 1);
				start++;
			}
			end++;
		}
		return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
	}

	// Q380 Insert Delete GetRandom O(1) #TopInterviewQuestion
	// Design a data structure that supports all following operations in average
	// O(1) time.
	// insert(val): Inserts an item val to the set if not already present.
	// remove(val): Removes an item val from the set if present.
	// getRandom: Returns a random element from current set of elements. Each
	// element must have the same probability of being returned.
	ArrayList<Integer> nums;
	HashMap<Integer, Integer> locs;
	java.util.Random rand = new java.util.Random();

	public void ContructorRandomizedSet() {
		nums = new ArrayList<Integer>();

		// map for storing the number and its index
		locs = new HashMap<Integer, Integer>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already
	 * contain the specified element.
	 */
	public boolean insert(int val) {
		boolean contain = locs.containsKey(val);
		if (contain)
			return false;
		locs.put(val, nums.size());
		nums.add(val);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the
	 * specified element.
	 */
	public boolean remove(int val) {
		boolean contain = locs.containsKey(val);
		if (!contain)
			return false;
		int loc = locs.get(val);
		if (loc < nums.size() - 1) { // if it is not the last one than swap the
										// last one with this val
			int lastone = nums.get(nums.size() - 1); // this is the actual
														// element
			nums.set(loc, lastone);
			locs.put(lastone, loc);
		}
		locs.remove(val);
		nums.remove(nums.size() - 1);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return nums.get(rand.nextInt(nums.size()));
	}

	// Q381 Insert Delete GetRandom O(1) -- Duplicates Allowed (follow up
	// question for the above question)

	class RandomizedCollection {
		ArrayList<Integer> nums;
		HashMap<Integer, Set<Integer>> locs;
		java.util.Random rand = new java.util.Random();

		/** Initialize your data structure here. */
		public RandomizedCollection() {
			nums = new ArrayList<Integer>();
			locs = new HashMap<Integer, Set<Integer>>();
		}

		/**
		 * Inserts a value to the collection. Returns true if the collection did
		 * not already contain the specified element.
		 */
		public boolean insert(int val) {
			boolean contain = locs.containsKey(val);
			if (!contain)
				locs.put(val, new LinkedHashSet<Integer>());
			locs.get(val).add(nums.size());
			nums.add(val);
			return !contain;
		}

		/**
		 * Removes a value from the collection. Returns true if the collection
		 * contained the specified element.
		 */
		public boolean remove(int val) {
			boolean contain = locs.containsKey(val);
			if (!contain)
				return false;
			int loc = locs.get(val).iterator().next();
			locs.get(val).remove(loc);
			if (loc < nums.size() - 1) {
				int lastone = nums.get(nums.size() - 1);
				nums.set(loc, lastone);
				locs.get(lastone).remove(nums.size() - 1);
				locs.get(lastone).add(loc);
			}
			nums.remove(nums.size() - 1);

			if (locs.get(val).isEmpty())
				locs.remove(val);
			return true;
		}

		/** Get a random element from the collection. */
		public int getRandom() {
			return nums.get(rand.nextInt(nums.size()));

		}
	}

	// Q378 kth smallest element in the sorted matrix #TopInterviewQuestion
	// Given a n x n matrix where each of the rows and columns are sorted in
	// ascending order, find the kth smallest element in the matrix.

	// Note that it is the kth smallest element in the sorted order, not the kth
	// distinct element.
	// Solution : Build a minHeap of elements from the first row.
	// Do the following operations k-1 times :
	// Every time when you poll out the root(Top Element in Heap), you need to
	// know the row number and column number
	// of that element(so we can create a tuple class here), replace that root
	// with the next element from the same column.
	// explained it very well https://www.youtube.com/watch?v=zIaMTdBQT34&t=309s
//	public int kthSmallest1(int[][] matrix, int k) {
//		int n = matrix.length;
//		PriorityQueue<Tuple> pq = new PriorityQueue<>();
//		for (int j = 0; j <= n - 1; j++)
//			pq.offer(new Tuple(0, j, matrix[0][j]));
//		for (int i = 0; i < k - 1; i++) {
//			Tuple t = pq.poll();
//			if (t.x == n - 1)
//				continue;
//			pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
//		}
//		return pq.poll().val;
//	}

	// or

	public int kthSmallest2(int[][] matrix, int k) {
		int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;// [lo,
																						// hi)
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			int count = 0, j = matrix[0].length - 1;
			for (int i = 0; i < matrix.length; i++) {
				while (j >= 0 && matrix[i][j] > mid)
					j--;
				count += (j + 1);
			}
			if (count < k)
				lo = mid + 1;
			else
				hi = mid;
		}
		return lo;
	}

	// Given a list of strings write a function to write the kth most frequent
	// recurrent strings
	// this method looks good
	public String kthMostFrequentString(String[] string, int k) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String s : string) {
			Integer x = map.get(s);
			if (x == null)
				x = 0;
			map.put(s, ++x);
		}
		List<Map.Entry> list = new ArrayList(map.entrySet());
		// Collections.sort(list, new Comparator<GenericHashMap.Entry<String,
		// Integer>>() {
		// public int compare(GenericHashMap.Entry<String, Integer> a,
		// GenericHashMap.Entry<String, Integer> b) {
		// return b.getValue() - a.getValue();
		// }
		// });

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Integer v1 = (Integer) ((Map.Entry) o1).getValue();
				Integer v2 = (Integer) ((Map.Entry) o2).getValue();
				return v1.compareTo(v2);
			}
		});

		if (list.size() > k)
			return (String) (list.get(k)).getKey();
		return null;
	}

	// same logic as above but above they have string below we have set of
	// integers.
	// Q347 Top k frequent elements #TopInterviewQuestion
	// Given a non-empty array of integers, return the k most frequent elements.
	// For example,
	// Given [1,1,1,2,2,3] and k = 2, return [1,2].
	// this method is a much better option
	public List<Integer> topKFrequent(int[] nums, int k) {
		if (nums == null) {
			return null;
		}

		Map<Integer, Integer> freqs = new HashMap<>();
		for (int num : nums) {
			freqs.put(num, freqs.getOrDefault(num, 0) + 1);
		}

		List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freqs.entrySet());
		// Collections.sort method is only applicable to list and thus we are
		// adding the complete hashmap in the list
		Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
				return b.getValue() - a.getValue();
			}
		});

		List<Integer> results = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			results.add(entries.get(i).getKey());
		}

		return results;
	}

	// or another way is below
	// use treeMap. Use freqncy as the key so we can get all freqencies in order
	public List<Integer> topKFrequent2(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}

		TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
		for (int num : map.keySet()) {
			int freq = map.get(num);
			if (!freqMap.containsKey(freq)) {
				freqMap.put(freq, new LinkedList<>());
			}
			freqMap.get(freq).add(num);
		}

		List<Integer> res = new ArrayList<>();
		while (res.size() < k) {
			Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
			res.addAll(entry.getValue());
		}
		return res;
	}

	// Q341 Flatten Nest List Iterator #TopInterviewQuestion
	// In the constructor, we push all the nestedList into the stack from back
	// to front, so when we pop the stack,
	// it returns the very first element. Second, in the hasNext() function, we
	// peek the first element in stack currently,
	// and if it is an Integer, we will return true and pop the element. If it
	// is a list, we will further flatten it.
	// This is iterative version of flatting the nested list. Again, we need to
	// iterate from the back to front of the list.

	// https://github.com/awangdev/LintCode/blob/master/Java/Flatten%20Nested%20List%20Iterator.java

	public class NestedIterator implements Iterator<Integer> {
		Stack<NestedInteger> stack = new Stack<>();

		public NestedIterator(List<NestedInteger> nestedList) {
			for (int i = nestedList.size() - 1; i >= 0; i--) {
				stack.push(nestedList.get(i));
			}
		}

		@Override
		public Integer next() {
			return stack.pop().getInteger();
		}

		@Override
		public boolean hasNext() {
			while (!stack.isEmpty()) {
				NestedInteger curr = stack.peek();
				if (curr.isInteger()) {
					return true;
				}
				stack.pop();
				for (int i = curr.getList().size() - 1; i >= 0; i--) {
					stack.push(curr.getList().get(i));
				}
			}
			return false;
		}
	}

	// 515 Find Largest Value in Each Tree Row
	// Just a simple pre-order traverse idea. Use depth to expand result list
	// size and put the max value in the appropriate position.
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		helper(root, res, 0);
		return res;
	}

	private void helper(TreeNode root, List<Integer> res, int d) {
		if (root == null) {
			return;
		}
		// expand list size
		if (d == res.size()) {
			res.add(root.val);
		} else {
			// or set value
			res.set(d, Math.max(res.get(d), root.val));
		}
		helper(root.left, res, d + 1);
		helper(root.right, res, d + 1);
	}

	// or Non recursive method .. the below method is a good method
	public int[] findValueMostElement(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<Integer> res = new ArrayList<Integer>();
		queue.add(root);
		int queueSize = root == null ? 0 : 1;
		while (queueSize > 0) {
			int largestElement = Integer.MIN_VALUE;
			for (int i = 0; i < queueSize; i++) {
				TreeNode cur = queue.poll();
				largestElement = Math.max(cur.val, largestElement);
				if (cur.left != null)
					queue.add(cur.left);
				if (cur.right != null)
					queue.add(cur.right);
			}
			res.add(largestElement);
			queueSize = queue.size();
		}
		int[] resArray = new int[res.size()];
		for (int i = 0; i < res.size(); i++)
			resArray[i] = res.get(i);
		return resArray;
	}

	// Q94 Binary Tree Inorder Traversal #TopInterviewQuestion
	void inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;
		boolean done = false;
		while (!done) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				if (stack.isEmpty())
					done = true;
				else {
					current = stack.pop();
					System.out.println(current.val);
					current = current.right;
				}
			}
		}
	}

	void preOrderTraversal(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> S = new Stack<>();
		Queue<TreeNode> Q = new LinkedList<>();
		S.push(root);
		while (!S.isEmpty()) {
			TreeNode curr = S.peek();
			Q.add(curr);
			S.pop();
			if (curr.right != null)
				S.push(curr.right);
			if (curr.left != null)
				S.push(curr.left);

		}
		while (!Q.isEmpty())
			System.out.println(Q.poll().val);

	}

	void postOrderTraversal(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> Stack = new Stack<>();
		Stack<TreeNode> out = new Stack<>();
		Stack.push(root);
		while (!Stack.isEmpty()) {
			TreeNode cur = Stack.peek();
			out.push(cur);
			Stack.pop();
			if (cur.left != null)
				Stack.push(cur.left);
			if (cur.right != null)
				Stack.push(cur.right);
		}
		while (!out.isEmpty()) {
			System.out.println(out.peek().val);
			out.pop();
		}

	}

	// Q46 Permutation of a string #TopInterviewQuestion
	public static HashSet<String> getAllPermutations(String str) {
		// Create a hash set to prevent any duplicate entries
		HashSet<String> permutations = new HashSet<>();
		if (str == null || str.length() == 0) {
			permutations.add("");
			return permutations;
		}
		char first = str.charAt(0);
		String remainingString = str.substring(1);
		HashSet<String> words = getAllPermutations(remainingString);
		for (String word : words) {
			for (int i = 0; i <= word.length(); i++) {
				String prefix = word.substring(0, i);
				String suffix = word.substring(i);
				permutations.add(prefix + first + suffix);
			}
		}
		return permutations;
	}

	// Q105 Construct Binary Tree from Preorder and Inorder Traversal
	// #TopInterviewQuestion
	// Say we have 2 arrays, PRE and IN.
	// Preorder traversing implies that PRE[0] is the root node.
	// Then we can find this PRE[0] in IN, say it's IN[5].
	// Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the
	// left side,
	// IN[6] to the end is on the right side.
	// Recursively doing this on subarrays, we can build a tree out of it :)
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);
		int inIndex = 0; // Index of current root in inorder
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		return root;
	}

	public static void AddTwoArrays() {
		int val = 0;
		int carry = 0;
		int arrA[] = { 5, 6, 6, 9 };
		int i = arrA.length - 1;
		int arrB[] = { 4, 6, 4 };
		int j = arrB.length - 1;
		int k;
		if (i > j) {
			k = i;
		} else
			k = j;
		int result[] = new int[k + 1];
		// int result1[] = new int[result.length + 1];
		while (i >= 0 && j >= 0) {
			val = arrA[i] + arrB[j] + carry; // what if val >10 and what if it
												// doesnt
			result[k] = val % 10;
			carry = val / 10;
			i--;
			j--;
			k--;
		}
		while (i >= 0) {
			val = arrA[i] + carry; // what if val >10 and what if it doesnt
			result[k] = val % 10;
			carry = val / 10;
			i--;
			k--;
		}
		while (j >= 0) {
			val = arrB[j] + carry; // what if val >10 and what if it doesnt
			result[k] = val % 10;
			carry = val / 10;
			j--;
			k--;
		}
		if (carry == 1) {
			result = new int[k + 1];
			result[0] = 1;
		}
		System.out.println("result comes out to be ");
		for (i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}

	// Recursive Java program to print all files
	// in a folder(and sub-folders)
	static void RecursivePrint(File[] arr, int index, int level) {
		// terminate condition
		if (index == arr.length)
			return;

		// tabs for internal levels
		for (int i = 0; i < level; i++)
			System.out.print("\t");

		// for files
		if (arr[index].isFile())
			System.out.println(arr[index].getName());

		// for sub-directories
		else if (arr[index].isDirectory()) {
			System.out.println("[" + arr[index].getName() + "]");

			// recursion for sub-directories
			RecursivePrint(arr[index].listFiles(), 0, level + 1);
		}

		// recursion for main directory
		RecursivePrint(arr, ++index, level);
	}

	// Find file duplicates in a directory
	private static MessageDigest messageDigest;
	static {
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("cannot initialize SHA-512 hash function", e);
		}
	}

	public static void findDuplicateFiles(Map<String, List<String>> filesList, File directory) {
		for (File dirChild : directory.listFiles()) {
			// Iterate all file sub directories recursively
			if (dirChild.isDirectory()) {
				findDuplicateFiles(filesList, dirChild);
			} else {
				try {
					// Read file as bytes
					FileInputStream fileInput = new FileInputStream(dirChild);
					byte fileData[] = new byte[(int) dirChild.length()];
					fileInput.read(fileData);
					fileInput.close();
					// Create unique hash for current file
					String uniqueFileHash = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
					List<String> identicalList = filesList.get(uniqueFileHash);
					if (identicalList == null) {
						identicalList = new LinkedList<String>();
					}
					// Add path to list
					identicalList.add(dirChild.getAbsolutePath());
					// push updated list to Hash table
					filesList.put(uniqueFileHash, identicalList);
				} catch (IOException e) {
					throw new RuntimeException("cannot read file " + dirChild.getAbsolutePath(), e);
				}
			}
		}
	}

	public class HeapInsertion {
		private void shiftUp(ArrayList<Integer> items) {
			int k = items.size() - 1;
			while (k > 0) {
				int p = (k - 1) / 2;
				int item = items.get(k);
				int parent = items.get(p);

				// swap
				if (item > parent) {
					items.set(k, parent);
					items.set(p, item);

					// move up 1 level
					k = p;
				} else {
					break;
				}
			}
		}

		private void ShiftDown(ArrayList<Integer> items) {
			int k = 0;
			int l = 2 * k + 1;
			while (l < items.size()) {
				int max = l;
				int r = l + 1;
				if (r < items.size()) // there is a right child
				{
					if (items.get(k) > items.get(l)) {
						max++;
					}
				}
				if (items.get(k) < items.get(l)) { // switch
					int temp = items.get(k);
					items.set(k, items.get(max));
					items.set(max, temp);
					k = max;
					l = 2 * k + 1;
				} else {
					break;
				}
			}
		}
	}

	public class HItCounter {
		private int[] times;
		private int[] hits;

		/** Initialize your data structure here. */
		public HItCounter() {
			times = new int[300];
			hits = new int[300];
		}

		// Record a hit. @param timestamp - The current timestamp (in seconds
		// granularity).
		public void hit(int timestamp) {
			int index = timestamp % 300;
			if (times[index] != timestamp) {
				times[index] = timestamp;
				hits[index] = 1;
			} else {
				hits[index]++;
			}
		}

		// Return the number of hits in the past 5 minutes.
		// @param timestamp - The current timestamp (in seconds granularity).
		public int getHits(int timestamp) {
			int total = 0;
			for (int i = 0; i < 300; i++) {
				if (timestamp - times[i] < 300) {
					total += hits[i];
				}
			}
			return total;
		}
	}

	// URL shortener
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int BASE = ALPHABET.length();

	public static String encode(int num) {
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			// System.out.println("appended string " + ALPHABET.charAt( num %
			// BASE ));
			sb.append(ALPHABET.charAt(num % BASE));
			num /= BASE;
		}
		return sb.reverse().toString();
	}

	public static int decode(String str) {
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			num = num * BASE + ALPHABET.indexOf(str.charAt(i));
		}
		return num;
	}

	// The pattern here is :
	// Shift 'n-1' disks from 'A' to 'B'.
	// Shift last disk from 'A' to 'C'.
	// Shift 'n-1' disks from 'B' to 'C'.
	static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod) {
		if (n == 1) {
			System.out.println("Move disk 1 from rod " + from_rod + " to rod " + to_rod);
			return;
		}
		towerOfHanoi(n - 1, from_rod, aux_rod, to_rod);
		System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);
		towerOfHanoi(n - 1, aux_rod, to_rod, from_rod);
	}

	public static boolean checkPanagram(String s) {
		int[] a = new int[26];
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
				if (a[s.charAt(i) - 65] == 0)
					count++;
				a[s.charAt(i) - 65]++;
			} else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
				if (a[s.charAt(i) - 97] == 0)
					count++;
				a[s.charAt(i) - 97]++;
			}
		}
		if (count == 26) {
			System.out.println("panagram means every character between a to z comes atleast once");
			return true;
		} else {
			return false;
		}

	}

	// Q297 Serialize and Deserialize Binary Tree #TopInterviewQuestion
	// The idea is simple: print the tree in pre-order traversal and use "X" to
	// denote null node and split node
	// with ",". We can use a StringBuilder for building the string on the fly.
	// For deserializing, we use a Queue
	// to store the pre-order traversal and since we have "X" as null node, we
	// know exactly how to where to end building subtress.

	private static final String splitter = ",";
	private static final String NN = "X";

	// Encodes a tree to a single string.
	public String serialize2(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	private void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(NN).append(splitter);
		} else {
			sb.append(node.val).append(splitter);
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize2(String data) {
		Deque<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(splitter)));
		return buildTree(nodes);
	}

	private TreeNode buildTree(Deque<String> nodes) {
		String val = nodes.remove();

		if (val.equals(NN))
			return null;
		else {
			TreeNode node = new TreeNode(Integer.valueOf(val));
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);
			return node;
		}
	}

	// Without recursion Serialize and Deserialize
	// Here I use typical BFS method to handle a binary tree. I use string n to
	// represent null values.
	// The string of the binary tree in the example will be "1 2 3 n n 4 5 n n n
	// n ".
	// When deserialize the string, I assign left and right child for each
	// not-null node, and add the not-null
	// children to the queue, waiting to be handled later.
	public String serialize(TreeNode root) {
		if (root == null)
			return "";
		Queue<TreeNode> q = new LinkedList<>();
		StringBuilder res = new StringBuilder();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node == null) {
				res.append("n ");
				continue;
			}
			res.append(node.val + " ");
			q.add(node.left);
			q.add(node.right);
		}
		return res.toString();
	}

	public TreeNode deserialize(String data) {
		if (data == "")
			return null;
		Queue<TreeNode> q = new LinkedList<>();
		String[] values = data.split(" ");
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		q.add(root);
		for (int i = 1; i < values.length; i++) {
			TreeNode parent = q.poll();
			if (!values[i].equals("n")) {
				TreeNode left = new TreeNode(Integer.parseInt(values[i]));
				parent.left = left;
				q.add(left);
			}
			if (!values[++i].equals("n")) {
				TreeNode right = new TreeNode(Integer.parseInt(values[i]));
				parent.right = right;
				q.add(right);
			}
		}
		return root;
	}

	// Q680 Valid palindrome 2 #GoodQuestion
	// Given a non-empty string s, you may delete at most one character.
	// Judge whether you can make it a palindrome
	public boolean validPalindrome2(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j && s.charAt(i) == s.charAt(j)) {
			i++;
			j--;
		}
		if (i >= j)
			return true;
		if (isPalin(s, i + 1, j) || isPalin(s, i, j - 1))
			return true;
		return false;
	}

	private boolean isPalin(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) == s.charAt(j)) {
				i++;
				j--;
			} else
				return false;
		}
		return true;
	}

	// Minimum insertions to form a palindrome #GoodQuestion
	// Base Case For Recursive Solution :
	// if l > h (we crossed pointers) return INT_MAX
	// if l == h return 0;
	// (only one character, which is already palindrome, 0 insertion is required
	// to make it palindrome)
	// if l == h-1 and if str[l] == str[h] return 0;
	// (if two length string example “aa or ab” and both characters are same ie
	// : “aa” , its already palindrome,
	// so return 0)
	// if l == h-1 and if str[l] != str[h] return 1;
	// (if two length string is there example “ab” and both characters are
	// different ,
	// we need 1 insertion to make this string a palindrome one ie “bab” or
	// “aba“
	// very well explained. https://www.youtube.com/watch?v=DOnK40BvazI
	int findMinInsertionsRec(char str[], int l, int h) {
		if (l > h)
			return Integer.MAX_VALUE;
		if (l == h)
			return 0; // only 1 character
		if (l == h - 1)
			return (str[l] == str[h]) ? 0 : 1;
		// this is for aa , ab type of strings when there is only 2 characters

		return (str[l] == str[h]) ? findMinInsertionsRec(str, l + 1, h - 1)
				: (Math.min(findMinInsertionsRec(str, l, h - 1), findMinInsertionsRec(str, l + 1, h)) + 1);
	}

	int findMinInsertionsDP(char str[], int n) {
		int[][] table = new int[n][n];
		int l, h, len;
		// memset(table, 0, sizeof(table));
		Arrays.fill(table, 0);
		for (len = 1; len < n; len++) // ++gap for length of the string as
										// 1,2,3,4..n
			for (l = 0, h = len; h < n; l++, h++) // it was ++l,++h
				table[l][h] = (str[l] == str[h]) ? table[l + 1][h - 1]
						: (Math.min(table[l][h - 1], table[l + 1][h]) + 1);
		return table[0][n - 1];
	}

	// Q658 K closest points to the given value #GoodQuestion
	// Given a sorted array, two integers k and x, find the k closest elements
	// to x in the array.
	// The result should also be sorted in ascending order. If there is a tie,
	// the smaller elements are always preferred.
	// In the following solutions, it is assumed that all elements of array are
	// distinct.
	// A simple solution is to do linear search for k closest elements.
	// 1) Start from the first element and search for the crossover point (The
	// point before which elements are smaller
	// than or equal to X and after which elements are greater). This step takes
	// O(n) time.
	// 2) Once we find the crossover point, we can compare elements on both
	// sides of crossover point to print k closest elements.
	// This step takes O(k) time.

	class KClosest {
		/*
		 * Function to find the cross over point (the point before which
		 * elements are smaller than or equal to x and after which greater than
		 * x)
		 */
		int findCrossOver(int arr[], int low, int high, int x) {
			// Base cases
			if (arr[high] <= x) // x is greater than all
				return high;
			if (arr[low] > x) // x is smaller than all
				return low;

			// Find the middle point
			int mid = (low + high) / 2; /* low + (high - low)/2 */

			/* If x is same as middle element, then return mid */
			if (arr[mid] <= x && arr[mid + 1] > x)
				return mid;

			/*
			 * If x is greater than arr[mid], then either arr[mid + 1] is
			 * ceiling of x or ceiling lies in arr[mid+1...high]
			 */
			if (arr[mid] < x)
				return findCrossOver(arr, mid + 1, high, x);
			return findCrossOver(arr, low, mid - 1, x);
		}

		// This function prints k closest elements to x in arr[]. n is the
		// number of elements in arr[]
		void printKclosest(int arr[], int x, int k, int n) {

			// Find the crossover point
			int l = findCrossOver(arr, 0, n - 1, x);
			int r = l + 1; // Right index to search
			int count = 0; // To keep track of count of elements already printed

			// If x is present in arr[], then reduce left index Assumption: all
			// elements in arr[] are distinct
			if (arr[l] == x)
				l--;

			// Compare elements on left and right of crossover point to find the
			// k closest elements
			while (l >= 0 && r < n && count < k) {
				if (x - arr[l] < arr[r] - x)
					System.out.print(arr[l--] + " ");
				else
					System.out.print(arr[r++] + " ");
				count++;
			}

			// If there are no more elements on right side, then print left
			// elements
			while (count < k && l >= 0) {
				System.out.print(arr[l--] + " ");
				count++;
			}

			// If there are no more elements on left side, then print right
			// elements
			while (count < k && r < n) {
				System.out.print(arr[r++] + " ");
				count++;
			}
		}
	}

	// Q334 Increasing triplet subsequence #TopInterviewQuestion
	// Given an unsorted array return whether an increasing subsequence of
	// length 3 exists or not in the array.
	public boolean increasingTriplet(int[] nums) {
		// start with two largest values, as soon as we find a number bigger
		// than both, while both have been updated, return true.
		int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small) {
				small = n;
			} // update small if n is smaller than both ..// c1 is min seen so
				// far (it's a candidate for 1st element)
			else if (n <= big) { // here when x > c1, i.e. x might be either c2
									// or c3
				big = n;
			} // update big only if greater than small but smaller than big ..//
				// x is better than the current c2, store it
			else
				return true; // return if you find a number bigger than both //
								// the increasing subsequence of 3 elements
								// exists
		}
		return false;
	}

	// Q674 longest continuous increase subsequence #GoodQuestion
	// Given an unsorted array of integers, find the length of longest
	// continuous increasing subsequence (subarray).
	public int findLengthOfLCIS(int[] nums) {
		int res = 0, cnt = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i - 1] < nums[i])
				res = Math.max(res, ++cnt);
			else
				cnt = 1;
		}
		return res;
	}

	// Q300 Length of longest increasing subsequence #TopInterviewQuestion
	// Given an unsorted array of integers, find the length of longest
	// increasing subsequence.
	// Very well explained by tushar https://www.youtube.com/watch?v=CE2b_-XfVDk
	// basic condition if arr[j] < arr[i] then T[i] = Max (T[i] , T[j]+1)

	public int longestSubsequenceWithActualSolution(int arr[]) {
		int T[] = new int[arr.length];
		int actualSolution[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			T[i] = 1;
			actualSolution[i] = i;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					if (T[j] + 1 > T[i]) {
						T[i] = T[j] + 1;
						// set the actualSolution to point to guy before me
						actualSolution[i] = j;
					}
				}
			}
		}

		// find the index of max number in T
		int maxIndex = 0;
		for (int i = 0; i < T.length; i++) {
			if (T[i] > T[maxIndex]) {
				maxIndex = i;
			}
		}

		// lets print the actual solution
		int t = maxIndex;
		int newT = maxIndex;
		do {
			t = newT;
			System.out.print(arr[t] + " ");
			newT = actualSolution[t];
		} while (t != newT);
		System.out.println();

		return T[maxIndex];
	}

	// Q45 Jump Game II
	// Given an array of non-negative integers, you are initially positioned at
	// the first index of the array.
	// Each element in the array represents your maximum jump length at that
	// position.
	// In this method, we build a jumps[] array from left to right such that
	// jumps[i] indicates the minimum
	// number of jumps needed to reach arr[i] from arr[0]. Finally, we return
	// jumps[n-1].
	// tusharroy example. https://www.youtube.com/watch?v=cETfFsSTGJI
	private static int minJumps2(int[] arr, int n) {
		int jumps[] = new int[n]; // jumps[n-1] will hold the result
		int i, j;
		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE; // if first element is 0, END CANNOT BE
										// REACHED
		jumps[0] = 0;
		// Find the minimum number of jumps to reach arr[i] from arr[0], and
		// assign this value to jumps[i]
		for (i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n - 1];
	}

	// Q56 Merge Intervals
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0)
			return result;

		// Sort by ascending starting point using an anonymous Comparator
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start)
					return i1.start - i2.start;
				else
					return i1.end - i2.end;
			}
		});
		Interval pre = intervals.get(0);
		for (int i = 0; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (curr.start > pre.end) {
				result.add(pre);
				pre = curr;
			} else {

				Interval merged = new Interval(pre.start, Math.max(pre.end, curr.end));
				pre = merged;
			}
		}
		result.add(pre);
		return result;
	}

	// Q57 Insert Interval
	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> result = new ArrayList<Interval>();
		for (Interval interval : intervals) {
			if (interval.end < newInterval.start) {
				result.add(interval);
			} else if (interval.start > newInterval.end) {
				result.add(newInterval);
				newInterval = interval;
			} else if (interval.end >= newInterval.start || interval.start <= newInterval.end) {
				newInterval = new Interval(Math.min(interval.start, newInterval.start),
						Math.max(newInterval.end, interval.end));
			}
		}
		result.add(newInterval);
		return result;
	}

	// Palindrome Partitioning - II #HardlyAsked #LeavingQuestion
	// Given a string s, partition s such that every substring of the partition
	// is a palindrome.
	// Return the minimum cuts needed for a palindrome partitioning of s.
	// i is the starting index and j is the ending index. i must be passed as 0
	// and j as n-1
	// minPalPartion(str, i, j) = 0 if i == j. // When string is of length 1.
	// minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.
	//// If none of the above conditions is true, then minPalPartion(str, i, j)
	// can be
	//// calculated recursively using the following formula.
	// minPalPartion(str, i, j) = Min { minPalPartion(str, i, k) + 1 +
	// minPalPartion(str, k+1, j) } where k varies from i to j-1
	static int minPalPartion(String str) {
		// Get the length of the string
		int n = str.length();
		/*
		 * Create two arrays to build the solution in bottom up manner C[i][j] =
		 * Minimum number of cuts needed // for palindrome partitioning of
		 * substring str[i..j] P[i][j] = true if substring str[i..j] is
		 * palindrome, else false Note that C[i][j] is 0 if P[i][j] is true
		 */
		int[][] C = new int[n][n];
		boolean[][] P = new boolean[n][n];
		int i, j, k, L; // different looping variables
		// Every substring of length 1 is a palindrome
		for (i = 0; i < n; i++) {
			P[i][i] = true;
			C[i][i] = 0;
		}

		/*
		 * L is substring length. Build the solution in bottom up manner by
		 * considering all substrings of length starting from 2 to n. The loop
		 * structure is same as Matrx Chain Multiplication problem ( See
		 * https://www.geeksforgeeks.org/archives/15553 )
		 */
		for (L = 2; L <= n; L++) {
			// For substring of length L, set different possible starting
			// indexes
			for (i = 0; i < n - L + 1; i++) {
				j = i + L - 1; // Set ending index
				// If L is 2, then we just need to compare two characters.
				// Else need to check two corner characters and value of
				// P[i+1][j-1]
				if (L == 2)
					P[i][j] = (str.charAt(i) == str.charAt(j));
				else
					P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1];

				// IF str[i..j] is palindrome, then C[i][j] is 0
				if (P[i][j] == true)
					C[i][j] = 0;
				else {
					// Make a cut at every possible localtion starting from i to
					// j, and get the minimum cost cut.
					C[i][j] = Integer.MAX_VALUE;
					for (k = i; k <= j - 1; k++)
						C[i][j] = Integer.min(C[i][j], C[i][k] + C[k + 1][j] + 1);
				}
			}
		}
		// Return the min cut value for complete string. i.e., str[0..n-1]
		return C[0][n - 1];
	}

	// the above solution is O(n3)
	// Returns the minimum number of cuts needed to partition a string such that
	// every part is a palindrome
	// below solution is O(n2)
	static int minPalPartion2(String str) {
		// Get the length of the string
		int n = str.length();
		/*
		 * Create two arrays to build the solution in bottom up manner C[i] =
		 * Minimum number of cuts needed for palindrome partitioning of
		 * substring str[0..i] P[i][j] = true if substring str[i..j] is
		 * palindrome, else false Note that C[i] is 0 if P[0][i] is true
		 */
		int[] C = new int[n];
		boolean[][] P = new boolean[n][n];
		int i, j, k, L; // different looping variables
		// Every substring of length 1 is a palindrome
		for (i = 0; i < n; i++) {
			P[i][i] = true;
		}

		/*
		 * L is substring length. Build the solution in bottom up manner by
		 * considering all substrings of length starting from 2 to n.
		 */
		for (L = 2; L <= n; L++) {
			// For substring of length L, set different possible starting
			// indexes
			for (i = 0; i < n - L + 1; i++) {
				j = i + L - 1; // Set ending index
				// If L is 2, then we just need to compare two characters. Else
				// need to check two corner characters and value of P[i+1][j-1]
				if (L == 2)
					P[i][j] = (str.charAt(i) == str.charAt(j));
				else
					P[i][j] = (str.charAt(i) == str.charAt(j)) && P[i + 1][j - 1];
			}
		}

		for (i = 0; i < n; i++) {
			if (P[0][i] == true)
				C[i] = 0;
			else {
				C[i] = Integer.MAX_VALUE;
				for (j = 0; j < i; j++) {
					if (P[j + 1][i] == true && 1 + C[j] < C[i])
						C[i] = 1 + C[j];
				}
			}
		}
		// Return the min cut value for complete string. i.e., str[0..n-1]
		return C[n - 1];
	}

	// K closest point #GoodQuestion
	public Point[] findKClosestPoints(Point[] points, int k, Point target) {
		if (points == null || points.length == 0 || k < 1 || k > points.length)
			return points;
		Queue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				int d1 = (p1.x - target.x) * (p1.x - target.x) + (p1.y - target.y) * (p1.y - target.y);
				int d2 = (p2.x - target.x) * (p2.x - target.x) + (p2.y - target.y) * (p2.y - target.y);
				return d2 - d1;
			}
		});
		for (Point p : points) {
			pq.offer(p);
			if (pq.size() > k)
				pq.poll();
		}
		Point[] res = new Point[k];
		for (int i = k - 1; i >= 0; i--)
			res[i] = pq.poll();
		return res;
	}

	// Q127 Word ladder #TopInterviewQuestion
	// Given two words (beginWord and endWord), and a dictionary's word list,
	// find the length of shortest transformation sequence from beginWord to
	// endWord, such that:
	// Only one letter can be changed at a time.
	// Each transformed word must exist in the word list. Note that beginWord is
	// not a transformed word.
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		int len = 1;
		HashSet<String> visited = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		while (!beginSet.isEmpty() && !endSet.isEmpty()) {
			if (beginSet.size() > endSet.size()) {
				Set<String> set = beginSet;
				beginSet = endSet;
				endSet = set;
			}
			Set<String> temp = new HashSet<>();
			for (String word : beginSet) {
				char[] chs = word.toCharArray();
				for (int i = 0; i < chs.length; i++) {
					for (char c = 'a'; c <= 'z'; c++) {
						char old = chs[i];
						chs[i] = c;
						String target = String.valueOf(chs);
						if (endSet.contains(target)) {
							return len + 1;
						}
						if (!visited.contains(target) && wordList.contains(target)) {
							temp.add(target);
							visited.add(target);
						}
						chs[i] = old;
					}
				}
			}
			beginSet = temp;
			len++;
		}
		return 0;
	}

	// Q207 Course Schedule #TopInterviewQuestion
	// There are a total of n courses you have to take, labeled from 0 to n-1.
	// Some courses may have prerequisites, for example to take course 0 you
	// have to first take course 1, which is expressed as a pair:
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 1)
			return true;
		if (prerequisites.length == 0 || prerequisites[0].length == 0)
			return true;

		// Building a graph
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < numCourses; i++)
			graph.put(i, new HashSet<Integer>());
		for (int i = 0; i < prerequisites.length; i++)
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);

		Queue<Integer> queue = new LinkedList<Integer>();
		int courseRemaining = numCourses;

		for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
			if (entry.getValue().size() == 0) {
				queue.offer(entry.getKey());
				courseRemaining--;
			}
		}
		while (!queue.isEmpty()) {
			int key = queue.poll();
			for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
				if (entry.getValue().contains(key)) {
					entry.getValue().remove(key);
					if (entry.getValue().size() == 0) {
						queue.offer(entry.getKey());
						courseRemaining--;
					}
				}
			}
		}
		return courseRemaining == 0;
	}

	// Tree Related Questions
	// Q230 kth smallest element in the BST #TopInterviewQuestion
	// Given a binary search tree, write a function kthSmallest to find the kth
	// smallest element in it.
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> st = new Stack<>();
		while (root != null) {
			st.push(root);
			root = root.left;
		}
		while (k != 0) {
			TreeNode n = st.pop();
			k--;
			if (k == 0)
				return n.val;
			TreeNode right = n.right;
			while (right != null) {
				st.push(right);
				right = right.left;
			}
		}
		return -1; // never hit if k is valid
	}

	// find kth largest no in a binary search tree
	void kthLargestUtil(Node node, int k, int count) {
		// Base cases, the second condition is important to avoid unnecessary recursive calls
		if (node == null || count >= k)
			return;

		// Follow reverse inorder traversal so that the largest element is visited first
		this.kthLargestUtil(node.right, k, count);

		// Increment count of visited nodes
		count++;

		// If c becomes k now, then this is the k'th largest
		if (count == k) {
			System.out.println(k + "th largest element is " + node.data);
			return;
		}

		// Recur for left subtree
		this.kthLargestUtil(node.left, k, count);
	}

	// Q99 Recover Binary Search Tree #GoodQuestion
	// Two elements of a binary search tree (BST) are swapped by mistake.
	// Recover the tree without changing its structure.
	// approach is to do the inorder traversal, and find the two pairs where
	// elements are not sorted,
	// when we find those, we will take the first element of the first pair and
	// replace it with the second
	// element of the second pair
	// https://www.youtube.com/watch?v=LR3K5XAWV5k very well explained in this

	Node firstStartPoint, lastEndPoint, prevNode;

	public void recoverTree(Node root) {
		findSegments(root);
		int temp = firstStartPoint.data;
		firstStartPoint.data = lastEndPoint.data;
		lastEndPoint.data = temp;
	}

	public void findSegments(Node root) {
		if (root == null)
			return;
		findSegments(root.left);
		if (prevNode != null) {
			if (prevNode.data > root.data) {
				if (firstStartPoint == null)
					firstStartPoint = prevNode;
				lastEndPoint = root;
			}
		}
		prevNode = root;
		findSegments(root.right);

	}

	//better solution of the above question recover binary search tree
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {

        // In order traversal to find the two elements
        traverse(root);

        // Swap the values of the two nodes
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    private void traverse(TreeNode root) {
        if (root == null)
            return;
        traverse(root.left);

        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }

        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }

        prevElement = root;

        traverse(root.right);
    }

	// Bottom view of a binary tree #Confused should be done or leave it ??
	// Method that prints the bottom view.
	public void bottomView(Node root) {

		if (root == null)
			return;

		// Initialize a variable 'hd' with 0 for the root element.
		int hd = 0;

		// TreeMap which stores key value pair sorted on key value
		Map<Integer, Integer> map = new TreeMap<>();

		// Queue to store tree nodes in level order traversal
		Queue<Node> queue = new LinkedList<Node>();

		// Assign initialized horizontal distance value to root
		// node and add it to the queue.
		root.hd = hd;
		queue.add(root);

		// Loop until the queue is empty (standard level order loop)
		while (!queue.isEmpty()) {
			Node temp = queue.remove();

			// Extract the horizontal distance value from the
			// dequeued tree node.
			hd = temp.hd;

			// Put the dequeued tree node to TreeMap having key
			// as horizontal distance. Every time we find a node
			// having same horizontal distance we need to replace
			// the data in the map.
			map.put(hd, temp.data);

			// If the dequeued node has a left child add it to the
			// queue with a horizontal distance hd-1.
			if (temp.left != null) {
				temp.left.hd = hd - 1;
				queue.add(temp.left);
			}
			// If the dequeued node has a left child add it to the
			// queue with a horizontal distance hd+1.
			if (temp.right != null) {
				temp.right.hd = hd + 1;
				queue.add(temp.right);
			}
		}

		// Extract the entries of map into a set to traverse
		// an iterator over that.
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();

		// Make an iterator
		Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();

		// Traverse the map elements using the iterator.
		while (iterator.hasNext()) {
			Map.Entry<Integer, Integer> me = iterator.next();
			System.out.print(me.getValue() + " ");
		}
	}

	// Q107 Level order traversal II #GoodQuestion
	// Given a binary tree, return the bottom-up level order traversal of its
	// nodes' values. (ie, from left to right, level by level from leaf to
	// root).
	// For example:
	// Given binary tree [3,9,20,null,null,15,7],
	// 3
	/// \
	// 9 20
	/// \
	// 15 7
	// return its bottom-up level order traversal as:
	// [
	// [15,7],
	// [9,20],
	// [3]
	// ]
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		if (root == null)
			return result;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (q.size() > 0) {
			List<Integer> list = new ArrayList<>();
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = q.poll();
				list.add(node.val);
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			}
			result.add(0, list);
		}
		return result;
	}

	// Q108 Convert Sorted Array to Binary Search Tree #TopInterviewQuestion
	// Given an array where elements are sorted in ascending order, convert it
	// to a height balanced BST.
	public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0) {
			return null;
		}
		TreeNode head = helper(num, 0, num.length - 1);
		return head;
	}

	public TreeNode helper(int[] num, int low, int high) {
		if (low > high) { // Done
			return null;
		}
		int mid = (low + high) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = helper(num, low, mid - 1);
		node.right = helper(num, mid + 1, high);
		return node;
	}

	// Q110 Balanced Binary Tree
	// Given a binary tree, determine if it is height-balanced.
	// a binary tree in which the depth of the two subtrees of every node never
	// differ by more than 1.

	// second method looks more reasonable and self explanatory

	// below method is DFS recursion
	public int dfsHeight(TreeNode root) {
		if (root == null)
			return 0;

		int leftHeight = dfsHeight(root.left);
		if (leftHeight == -1)
			return -1;

		int rightHeight = dfsHeight(root.right);
		if (rightHeight == -1)
			return -1;
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public boolean isBalanced2(TreeNode root) {
		return dfsHeight(root) != -1;
	}

	int depth(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(depth(root.left), depth(root.right)) + 1;
	}

	boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		int left = depth(root.left);
		int right = depth(root.right);

		return Math.abs(left - right) <= 1 && isBalanced2(root.left) && isBalanced2(root.right);
	}

	// Q103 Binary Tree Zigzag Level Order Traversal #TopInterviewQuestion
	// Given a binary tree, return the zigzag level order traversal of its
	// nodes' values.
	// (ie, from left to right, then right to left for the next level and
	// alternate between).
	// spiral traversal
	// For example:
	// Given binary tree [3,9,20,null,null,15,7],
	// 3
	/// \
	// 9 20
	// / \
	// 15 7
	// return its zigzag level order traversal as:
	// [
	// [3],
	// [20,9],
	// [15,7]
	// ]
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null)
			return res;

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		boolean order = true;
		int size = 1;

		while (!q.isEmpty()) {
			List<Integer> tmp = new ArrayList<>();
			for (int i = 0; i < size; ++i) {
				TreeNode n = q.poll();
				if (order) {
					tmp.add(n.val);
				} else {
					tmp.add(0, n.val);
				}
				if (n.left != null)
					q.add(n.left);
				if (n.right != null)
					q.add(n.right);
			}
			res.add(tmp);
			size = q.size();
			order = order ? false : true; // this line means order = !order;
		}
		return res;
	}

	// or this method is better
	public void spiralWithTwoStack(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();
		s1.push(root);

		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.isEmpty()) {
				root = s1.pop();
				System.out.print(root.data + " ");
				if (root.left != null) {
					s2.push(root.left);
				}
				if (root.right != null) {
					s2.push(root.right);
				}
			}
			while (!s2.isEmpty()) {
				root = s2.pop();
				System.out.print(root.data + " ");
				if (root.right != null) {
					s1.push(root.right);
				}
				if (root.left != null) {
					s1.push(root.left);
				}
			}
		}
	}

	// Q100 Same Tree
	// Given two binary trees, write a function to check if they are the same or
	// not.
	// Two binary trees are considered the same if they are structurally
	// identical and the nodes have the same value.
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val == q.val)
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		return false;
	}

	public boolean isSameTreeNonRecursive(TreeNode p, TreeNode q) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(p);
		queue.add(q);
		while (!queue.isEmpty()) {
			TreeNode f = queue.poll();
			TreeNode s = queue.poll();
			if (f == null && s == null) {
				continue;
			} else if (f == null || s == null || f.val != s.val) {
				return false;
			}
			queue.add(f.left);
			queue.add(s.left);
			queue.add(f.right);
			queue.add(s.right);
		}
		return true;
	}
	// Q96 Unique Binary Search Trees
	// Given n, how many structurally unique BST's (binary search trees) that
	// store values 1...n?
	//
	// For example,
	// Given n = 3, there are a total of 5 unique BST's.
	//
	// 1 3 3 2 1
	// \ / / / \ \
	// 3 2 1 1 3 2
	/// / \ \
	// 2 1 2 3

	// very well explained by tushar roy
	// https://www.youtube.com/watch?v=YDf982Lb84o
	public int numTrees(int n) {
		int[] G = new int[n + 1];
		G[0] = G[1] = 1;

		for (int i = 2; i <= n; ++i) {
			for (int j = 0; j < i; j++) {
				G[i] += G[j] * G[i - j - 1];
			}
		}

		return G[n];
	}

	// Using recursion also we can do it
	int countTreesRec(int numKeys) {
		if (numKeys <= 1) {
			return (1);
		} else {
			int sum = 0;
			int left, right, root;
			for (root = 1; root <= numKeys; root++) {
				left = countTreesRec(root - 1);
				right = countTreesRec(numKeys - root);
				sum += left * right;
			}
			return (sum);
		}
	}

	// or the below method
	// its more of counting the possibilities for the left number of elements
	// and right number of elements and then multiplying them
	public int countPossibilities(int n, int[] solutions) {
		if (n < 0)
			return 0;
		else if (n == 1 || n == 0)
			return 1;
		int possibilities = 0;
		for (int i = 0; i < n; i++) {
			if (solutions[i] == -1)
				solutions[i] = countPossibilities(i, solutions); // compute all	BST's in the left subtree
			if (solutions[n - 1 - i] == -1)
				solutions[n - 1 - i] = countPossibilities(n - 1 - i, solutions); // compute all BST's in the left subtree
			possibilities += solutions[i] + solutions[n - 1 - i];
		}
		return possibilities;
	}

	// Q95 Unique Binary Search Trees II
	// Given an integer n, generate all structurally unique BST's (binary search
	// trees) that store values 1...n.
	//
	// For example,
	// Given n = 3, your program should return all 5 unique BST's shown below.
	//
	// 1 3 3 2 1
	// \ / / / \ \
	// 3 2 1 1 3 2
	/// / \ \
	// 2 1 2 3

	// Solution: I start by noting that 1..n is the in-order traversal for any
	// BST with nodes 1 to n.
	// So if I pick i-th node as my root, the left subtree will contain elements
	// 1 to (i-1), and the
	// right subtree will contain elements (i+1) to n. I use recursive calls to
	// get back all possible
	// trees for left and right subtrees and combine them in all possible ways
	// with the root.
	public List<TreeNode> generateTrees(int n) {
		return genTrees(1, n);
	}

	public List<TreeNode> genTrees(int start, int end) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if (start > end) {
			list.add(null);
			return list;
		}
		if (start == end) {
			list.add(new TreeNode(start));
			return list;
		}
		List<TreeNode> left, right;
		for (int i = start; i <= end; i++) {
			left = genTrees(start, i - 1);
			right = genTrees(i + 1, end);
			for (TreeNode lnode : left) {
				for (TreeNode rnode : right) {
					TreeNode root = new TreeNode(i);
					root.left = lnode;
					root.right = rnode;
					list.add(root);
				}
			}
		}
		return list;
	}

	// Q156 Binary Tree upside down #GoodQuestion #HardlyAsked
	// Given a binary tree where all the right nodes are either leaf nodes with
	// a sibling (a left node that shares the same parent node) or empty, flip
	// it upside down and turn it into a tree where the original right nodes
	// turned into left leaf nodes. Return the new root.
	// For example:
	// Given a binary tree {1,2,3,4,5},
	// 1
	/// \
	// 2 3
	/// \
	// 4 5
	// return the root of the binary tree [4,5,2,#,#,3,1].
	// 4
	/// \
	// 5 2
	/// \
	// 3 1

	// see the diagram at https://www.geeksforgeeks.org/flip-binary-tree/ for
	// better understanding of the recursion method

	public TreeNode upsideDownBinaryTree1(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = upsideDownBinaryTree1(root.left);
		root.left.left = root.right; // node 2 left children
		root.left.right = root; // node 2 right children
		root.left = null;
		root.right = null;
		return newRoot;
	}

	// don't know if the below solution will work or not
	// could not trace it
	public TreeNode upsideDownBinaryTree2(TreeNode root) {
		TreeNode curr = root;
		TreeNode next = null;
		TreeNode temp = null;
		TreeNode prev = null;

		while (curr != null) {
			next = curr.left;

			// swapping nodes now, need temp to keep the previous right child
			curr.left = temp;
			temp = curr.right;
			curr.right = prev;

			prev = curr;
			curr = next;
		}
		return prev;
	}

	// Binary Tree Right Side View
	// Given a binary tree, imagine yourself standing on the right side of it,
	// return the values of the nodes you can see ordered from top to bottom.
	// The core idea of this algorithm:
	// 1.Each depth of the tree only select one node.
	// 2. View depth is current size of result list.
	public List<Integer> rightView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		rightView(root, result, 0);
		return result;
	}

	public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
		if (curr == null) {
			return;
		}
		if (currDepth == result.size()) {
			result.add(curr.val);
		}
		rightView(curr.right, result, currDepth + 1);
		rightView(curr.left, result, currDepth + 1);
	}

	// can also be done in the below way
	int maxLevel = -1;

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		rightSideView(root, 0, result);
		return result;
	}

	public void rightSideView(TreeNode curr, int currDepth, List<Integer> result) {
		if (curr == null) {
			return;
		}
		if (currDepth > maxLevel) {
			maxLevel = currDepth;
			result.add(curr.val);
		}
		rightSideView(curr.right, currDepth + 1, result);
		rightSideView(curr.left, currDepth + 1, result);
	}

	// Without Recursion
	public List<Integer> rightSideViewNoRecursion(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				TreeNode node = queue.poll();
				if (i == levelSize - 1)
					result.add(node.val);
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
		}
		return result;
	}

	// Q124 Binary Tree Maximum Path Sum #TopInterviewQuestion
	// https://www.youtube.com/watch?v=cSnETAcziS0&t=229s
	// the same way we can do the minimum path sum
	// Given a binary tree, find the maximum path sum.
	// For this problem, a path is defined as any sequence of nodes from some
	// starting node to any node in the tree along the parent-child connections.
	// The path must contain at least one node and does not need to go through
	// the root.
	// For example: Given the below binary tree,
	// 1
	// / \
	// 2 3
	// Return 6.

    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);

        return Math.max(left, right) + node.val;
    }

	// Q112 Path sum of a binary tree
	// Given a binary tree and a sum, determine if the tree has a root-to-leaf
	// path such that adding up all the
	// values along the path equals the given sum.
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.left == null && root.right == null && sum - root.val == 0)
			return true;
		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	public static boolean hasPathSumNonRecursive(TreeNode root, int sum) {
		if (root == null)
			return false;
		Stack<TreeNode> stackNode = new Stack<>();
		Stack<Integer> stackValue = new Stack<>();
		stackNode.push(root);
		stackValue.push(root.val);
		while (!stackNode.isEmpty()) {
			TreeNode node = stackNode.pop();
			int value = stackValue.pop();
			if (node.left == null && node.right == null && value == sum)
				return true;
			else {
				if (node.right != null) {
					stackNode.push(node.right);
					stackValue.push(node.right.val + value);
				}
				if (node.left != null) {
					stackNode.push(node.left);
					stackValue.push(node.left.val + value);
				}
			}
		}
		return false;
	}

	// LinkedList Related Questions

	// Q203 Remove Linked List elements
	// Remove all elements from a linked list of integers that have value val.
	// Example
	// Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
	// Return: 1 --> 2 --> 3 --> 4 --> 5
	public ListNode removeElementsNonRecursive(ListNode head, int val) {
		ListNode fakeHead = new ListNode(-1);
		fakeHead.next = head;
		ListNode curr = head, prev = fakeHead;
		while (curr != null) {
			if (curr.val == val) {
				prev.next = curr.next;
			} else {
				prev = prev.next;
			}
			curr = curr.next;
		}
		return fakeHead.next;
	}

	// Same Question as before
	public ListNode removeElements(ListNode head, int val) {
		if (head == null)
			return null;
		head.next = removeElements(head.next, val);
		return head.val == val ? head.next : head;
	}

	// Q141 Linked List cycle #TopInterviewQuestion
	// Given a linked list, determine if it has a cycle in it.
	public boolean hasCycle(ListNode head) {
		if (head == null)
			return false;
		ListNode walker = head;
		ListNode runner = head;
		while (runner.next != null && runner.next.next != null) {
			walker = walker.next;
			runner = runner.next.next;
			if (walker == runner)
				return true;
		}
		return false;
	}

	// Q142 Linked List cycle II
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next; // Slow moves by 1 step
			fast = fast.next.next; // Fast moves by two steps
			// If they meet then there is a loop
			if (slow == fast) { // To find the starting element where the loop
				// starts
				// fast = fast;
				slow = head;
				while (slow != fast) { // Both move by 1 step
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}
		return null; // No loop
	}

	// Q24 Swap nodes in pairs in the linked list
	// Given a linked list, swap every two adjacent nodes and return its head.
	// For example, Given 1->2->3->4, you should return the list as 2->1->4->3.

	public ListNode swapPairsusingrecursion(ListNode head) {
		if ((head == null) || (head.next == null))
			return head;
		ListNode n = head.next;
		head.next = swapPairsusingrecursion(head.next.next);
		n.next = head;
		return n;
	}

	public ListNode swapPairsNonRecursive(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = dummy;
		while (current.next != null && current.next.next != null) {
			ListNode first = current.next;
			ListNode second = current.next.next;
			first.next = second.next;
			current.next = second;
			current.next.next = first;
			current = current.next.next;
		}
		return dummy.next;
	}

	// Q160 Intersection of two LinkedLists #TopInterviewQuestion
	// Write a program to find the node at which the intersection of two singly
	// linked lists begins.

	// Apart from the general method which is been user for getting the
	// intersection of two linkedlist,
	// below is one interesting method to get the intersection
	// assigning the pointer to the other list once it reached the end

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		ListNode node1 = headA;
		ListNode node2 = headB;
		while (node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
			if (node1 == node2)
				return node1; // in case node1 == node2 == null Any time they collide or reach end together
			                // without colliding then return any one of the pointers.
			if (node1 == null)
				node1 = headB; // If one of them reaches the end earlier then reuse it by moving it to the
			// beginning of other list. Once both of them go through reassigning, they will be equidistant from the collision point.
			if (node2 == null)
				node2 = headA;
		}
		return node1;
	}

	// Q82 Remove Duplicates from sorted list II
	// Given a sorted linked list, delete all nodes that have duplicate numbers,
	// leaving only distinct numbers from the original list. For example,
	// Given 1->2->3->3->4->4->5, return 1->2->5.
	// Given 1->1->1->2->3, return 2->3.
	public ListNode deleteDuplicates2(ListNode head) {
		// use two pointers, slow - track the node before the dup nodes,
		// fast - to find the last node of dups.
		ListNode dummy = new ListNode(0), fast = head, slow = dummy;
		slow.next = fast;
		while (fast != null) {
			while (fast.next != null && fast.val == fast.next.val) {
				fast = fast.next; // while loop to find the last node of the
									// dups.
			}
			if (slow.next != fast) { // duplicates detected.
				slow.next = fast.next; // remove the dups.
				fast = slow.next; // reposition the fast pointer.
			} else { // no dup, move down both pointer.
				slow = slow.next;
				fast = fast.next;
			}
		}
		return dummy.next;
	}

	// Q328 Odd Even Linked List #TopInterviewQuestion
	// Given a singly linked list, group all odd nodes together followed by the
	// even nodes.
	// Input: 1->2->3->4->5->NULL
	// Output: 1->3->5->2->4->NULL
	public ListNode oddEvenList(ListNode head) {
		if (head != null) {

			ListNode odd = head, even = head.next, evenHead = even;

			while (even != null && even.next != null) {
				odd.next = odd.next.next;
				even.next = even.next.next;
				odd = odd.next;
				even = even.next;
			}
			odd.next = evenHead;
		}
		return head;
	}

	// Reverse Linked List #TopInterviewQuestion
	public void ReverseLinkedList(ListNode head) {
		ListNode current, previous, next;
		current = head;
		previous = null;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}

	public void ReversePrintRecursive(ListNode p) {
		if (p == null) {
			return;
		}
		ReversePrint(p.next);
	}

	public void ReversePrint(ListNode p) {
		if (p.next == null) {
			ListNode head = p;
			return;
		}
		ReversePrint(p.next);
		ListNode q = p.next;
		q.next = p;
		p.next = null;
	}

	// Reverse Linked List II #GoodQuestion
	// Reverse a linked list from position m to n. Do it in-place and in
	// one-pass. For example:
	// Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	// return 1->4->3->2->5->NULL.
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0); // create a dummy node to mark the
											// head of this list
		dummy.next = head;
		ListNode pre = dummy; // make a pointer pre as a marker for the node
								// before reversing
		for (int i = 0; i < m - 1; i++)
			pre = pre.next;

		ListNode start = pre.next; // a pointer to the beginning of a sub-list
									// that will be reversed
		ListNode then = start.next; // a pointer to a node that will be reversed
		// 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
		// dummy-> 1 -> 2 -> 3 -> 4 -> 5
		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		// first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then
		// = 4
		// second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then
		// = 5 (finish)
		return dummy.next;
	}

	// Q234 Pallindrome Linked List #TopInterviewQuestion
	// Given a singly linked list, determine if it is a palindrome.
	// This can be solved by reversing the 2nd half and compare the two halves.
	public boolean isPalindrome(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		if (fast != null) { // odd nodes: let right half smaller
			slow = slow.next;
		}
		slow = reverse(slow);
		fast = head;
		while (slow != null) {
			if (fast.val != slow.val) {
				return false;
			}
			fast = fast.next;
			slow = slow.next;
		}
		return true;
	}

	public ListNode reverse(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	// Q148 Sort List (linked list) #TopInterviewQuestion
	// Sort a linked list in O(n log n) time using constant space complexity.
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		// step 1. cut the list to two halves
		ListNode prev = null, slow = head, fast = head;

		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		prev.next = null;

		// step 2. sort each half
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);

		// step 3. merge l1 and l2
		return merge(l1, l2);
	}

	ListNode merge(ListNode l1, ListNode l2) {
		ListNode l = new ListNode(0), p = l;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				p.next = l1;
				l1 = l1.next;
			} else {
				p.next = l2;
				l2 = l2.next;
			}
			p = p.next;
		}
		if (l1 != null)
			p.next = l1;
		if (l2 != null)
			p.next = l2;
		return l.next;
	}

	// Q86 partition list
	// Given a linked list and a value x, partition it such that all nodes less
	// than x come before nodes greater than or equal to x.
	// You should preserve the original relative order of the nodes in each of
	// the two partitions. For example,
	// Given 1->4->3->2->5->2 and x = 3,
	// return 1->2->2->4->3->5.
	// the basic idea is to maintain two queues, the first one stores all nodes
	// with val less than x ,
	// and the second queue stores all the rest nodes. Then concat these two
	// queues. Remember to set the tail of second queue a null next, or u will
	// get TLE.
	public ListNode partition(ListNode head, int x) {
		ListNode node1 = new ListNode(0);
		ListNode node2 = new ListNode(0);
		ListNode p1 = node1, p2 = node2;
		while (head != null) {
			if (head.val < x)
				p1 = p1.next = head;
			else
				p2 = p2.next = head;
			head = head.next;
		}
		p2.next = null;
		p1.next = node2.next;
		return node1.next;
	}

	// 2 Add Two Numbers #TopInterviewQuestion
	// actually its two linked listed in reverse order
	// You are given two non-empty linked lists representing two non-negative
	// integers. The digits are
	// stored in reverse order and each of their nodes contain a single digit.
	// Add the two numbers and return it as a linked list.
	// You may assume the two numbers do not contain any leading zero, except
	// the number 0 itself.
	// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	// Output: 7 -> 0 -> 8

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode c1 = l1;
		ListNode c2 = l2;
		ListNode sentinel = new ListNode(0);
		ListNode d = sentinel;
		int sum = 0;
		while (c1 != null || c2 != null) {
			sum /= 10;
			if (c1 != null) {
				sum += c1.val;
				c1 = c1.next;
			}
			if (c2 != null) {
				sum += c2.val;
				c2 = c2.next;
			}
			d.next = new ListNode(sum % 10);
			d = d.next;
		}
		if (sum / 10 == 1)
			d.next = new ListNode(1);
		return sentinel.next;
	}

	// above solution is a better solution
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode prev = new ListNode(0);
		ListNode head = prev;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			ListNode cur = new ListNode(0);
			int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
			cur.val = sum % 10;
			carry = sum / 10;
			prev.next = cur;
			prev = cur;

			l1 = (l1 == null) ? l1 : l1.next;
			l2 = (l2 == null) ? l2 : l2.next;
		}
		return head.next;
	}

	// Q21 merge two sorted lists #TopInterviewQuestion
	// merge two sorted linked lists
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l2.next, l1);
			return l2;
		}
	}

	// Non recursive Approach
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode head = new ListNode(-1), ptr = head;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				ptr.next = l1;
				l1 = l1.next;
			} else {
				ptr.next = l2;
				l2 = l2.next;
			}
			ptr = ptr.next;
		}
		ptr.next = l1 == null ? l2 : l1;
		return head.next;
	}

	// Q19 Remove Nth Node From End of List #TopInterviewQuestion
	// Given a linked list, remove the nth node from the end of list and return
	// its head.

	// A one pass solution can be done using pointers.Move one pointer fast --> n+1 places forward,
	// to maintain a gap of n between the two pointers and then move both at the same speed. Finally,
	// when the fast pointer reaches the end,the slow pointer will be n+1 places behind - just the right spot
	// for it to be able to skip the next node.Since the question gives that n is valid, not too many checks
	// have to be put in place. Otherwise, this would be necessary.
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode start = new ListNode(0);
		ListNode slow = start, fast = start;
		slow.next = head; // Move fast in front so that the gap between slow and
							// fast becomes n
		for (int i = 1; i <= n + 1; i++) {
			fast = fast.next;
		}
		// Move fast to the end, maintaining the gap
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}
		// Skip the desired node
		slow.next = slow.next.next;
		return start.next;
	}

	// Dynamic Programming

	// Q322 Coin Change Problem #TopInterviewQuestion
	// You are given coins of different denominations and a total amount of
	// money amount.
	// Write a function to compute the fewest number of coins that you need to
	// make up that amount.
	// If that amount of money cannot be made up by any combination of the
	// coins, return -1.
	/**
	 * Bottom up way of solving this problem. Keep input sorted. Otherwise
	 * temp[j-arr[i]) + 1 can become Integer.Max_value + 1 which can be very low
	 * negative number Returns Integer.MAX_VALUE - 1 if solution is not
	 * possible.
	 */
	public int minimumCoinBottomUp(int total, int coins[]) {
		int T[] = new int[total + 1]; // to keep the track of minimum number of
										// coins to form a total of that index
		int R[] = new int[total + 1]; // this is useful to get the final answer
		// foe example coins[0] = 7 , coins[1] = 2 , coins[2] = 3 , coins[3] = 6
		// ;
		T[0] = 0;
		for (int i = 1; i <= total; i++) {
			T[i] = Integer.MAX_VALUE - 1;
			R[i] = -1;
		}
		for (int j = 0; j < coins.length; j++) {
			for (int i = 1; i <= total; i++) {
				if (i >= coins[j]) {
					if (T[i - coins[j]] + 1 < T[i]) {
						T[i] = 1 + T[i - coins[j]];
						R[i] = j;
					}
				}
			}
		}
		printCoinCombination(R, coins);
		return T[total];
	}

	private void printCoinCombination(int R[], int coins[]) {
		if (R[R.length - 1] == -1) {
			System.out.print("No solution is possible");
			return;
		}
		int start = R.length - 1;
		System.out.print("Coins used to form total ");
		while (start != 0) {
			int j = R[start];
			System.out.print(coins[j] + " ");
			start = start - coins[j];
		}
		System.out.print("\n");
	}

	// Given a total and coins of certain denominations find number of ways
	// total
	// * can be formed from coins assuming infinity supply of coins

	// below solution tells you the number of ways we can achieve that total and
	// not the combination
	public int numberOfSolutions(int total, int coins[]) {
		int temp[][] = new int[coins.length + 1][total + 1];
		for (int i = 0; i <= coins.length; i++) {
			temp[i][0] = 1;
		}
		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= total; j++) {
				if (coins[i - 1] > j) {
					temp[i][j] = temp[i - 1][j];
				} else {
					temp[i][j] = temp[i][j - coins[i - 1]] + temp[i - 1][j];
				}
			}
		}
		return temp[coins.length][total];
	}

	// Q62 Unique Paths #TopInterviewQuestion
	// A robot is located at the top-left corner of a m x n grid (marked 'Start'
	// in the diagram below).
	// The robot can only move either down or right at any point in time. The
	// robot is trying to reach the bottom-right corner
	// of the grid (marked 'Finish' in the diagram below).
	// How many possible unique paths are there?

	// This is a fundamental DP problem. First of all, let's make some
	// observations.
	// Since the robot can only move right and down, when it arrives at a point,
	// there are only two possibilities:
	// It arrives at that point from above (moving down to that point);
	// It arrives at that point from left (moving right to that point).
	// Thus, we have the following state equations: suppose the number of paths
	// to arrive at a point (i, j) is denoted
	// as P[i][j], it is easily concluded that P[i][j] = P[i - 1][j] + P[i][j -
	// 1].
	// The boundary conditions of the above equation occur at the leftmost
	// column (P[i][j - 1] does not exist)
	// and the uppermost row (P[i - 1][j] does not exist). These conditions can
	// be handled by initialization (pre-processing)
	// initialize P[0][j] = 1, P[i][0] = 1 for all valid i, j. Note the initial
	// value is 1 instead of 0!

	public int uniquePaths(int m, int n) {
		Integer[][] map = new Integer[m][n];
		for (int i = 0; i < m; i++) {
			map[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			map[0][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}

	// Unique Path 2
	// Now consider if some obstacles are added to the grids. How many unique
	// paths would there be?
	// An obstacle and empty space is marked as 1 and 0 respectively in the
	// grid.
	// For example,
	// There is one obstacle in the middle of a 3x3 grid as illustrated below.
	// [
	// [0,0,0],
	// [0,1,0],
	// [0,0,0]
	// ]
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0)
			return 0;
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (obstacleGrid[i][j] == 1)
					obstacleGrid[i][j] = 0;
				else if (i == 0 && j == 0)
					obstacleGrid[i][j] = 1;
				else if (i == 0)
					obstacleGrid[i][j] = obstacleGrid[i][j - 1] * 1;// For row 0, if there are no paths to left cell, then its 0,else 1
				else if (j == 0)
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] * 1;// For col
																	// 0, if
																	// there are
																	// no paths
																	// to upper
																	// cell,
																	// then its
																	// 0,else 1
				else
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
			}
		}
		return obstacleGrid[rows - 1][cols - 1];
	}

	// Q44 Wild Card Matching #TopInterviewQuestion
	// ? : any one character
	// * : 0 or more sequence
	public boolean isMatch(String s, String p) {
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		// replace multiple * with one *
		// e.g a**b***c --> a*b*c

		int writeIndex = 0;
		boolean isFirst = true;
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*') {
				if (isFirst) {
					pattern[writeIndex++] = pattern[i];
					isFirst = false;
				}
			} else {
				pattern[writeIndex++] = pattern[i];
				isFirst = true;
			}
		}
		boolean T[][] = new boolean[str.length + 1][writeIndex + 1];
		// write index is the pattern we got after removing multiple stars ***
		// into 1 star *
		if (writeIndex > 0 && pattern[0] == '*') {
			T[0][1] = true;
		}

		T[0][0] = true;

		for (int i = 1; i < T.length; i++) {
			for (int j = 1; j < T[0].length; j++) {
				if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) { // first
																				// condition
					T[i][j] = T[i - 1][j - 1];
				} else if (pattern[j - 1] == '*') { // second condition
					T[i][j] = T[i - 1][j] || T[i][j - 1];
				}
			}
		}
		return T[str.length][writeIndex];
	}

	// Q10 Regular Expression #TopInterviewQuestion
	// * matches 0 or more occurances of character before *
	// . matches any single character

	// T[i][j] = T[i-1][j-1] if str[i]=pat[j] || pat[j] = .
	// = T[i][j-2] if 0 occurance for pat[j] = *
	// = T[i-1][j] if str[i]=pat[j-1] || pat[j-1] == . for pat[j] = *
	public boolean matchRegex(char[] text, char[] pattern) {
		boolean T[][] = new boolean[text.length + 1][pattern.length + 1];
		T[0][0] = true;
		// Deals with patterns like a* or a*b* or a*b*c* //these things can be
		// matched with empty text as well
		for (int i = 1; i < T[0].length; i++) {
			if (pattern[i - 1] == '*') {
				T[0][i] = T[0][i - 2];
			}
		}
		for (int i = 1; i < T.length; i++) {
			for (int j = 1; j < T[0].length; j++) {
				if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) { // first
																				// condition
					T[i][j] = T[i - 1][j - 1];
				} else if (pattern[j - 1] == '*') {
					T[i][j] = T[i][j - 2];
					if (pattern[j - 2] == '.' || pattern[j - 2] == text[i - 1]) { // second
																					// condition
						T[i][j] = T[i][j] | T[i - 1][j];
					}
				} else {
					T[i][j] = false;
				}
			}
		}
		return T[text.length][pattern.length];
	}

	public boolean subsetSum(int input[], int total) {
		boolean T[][] = new boolean[input.length + 1][total + 1];
		for (int i = 0; i <= input.length; i++) {
			T[i][0] = true;
		}

		for (int i = 1; i <= input.length; i++) {
			for (int j = 1; j <= total; j++) {
				if (j - input[i - 1] >= 0) {
					T[i][j] = T[i - 1][j] || T[i - 1][j - input[i - 1]];
				} else {
					T[i][j] = T[i - 1][j];
				}
			}
		}
		return T[input.length][total];
	}

	// Q5 longest palindromic Substring #TopInterviewQuestion
	// ideserve https://www.youtube.com/watch?v=obBdxeCx_Qs&t=117s
	public static String LPS(String s) {
		int n = s.length();
		int palindromeBeginsAt = 0; // index where the longest palindrome begins
		int max_len = 1;// length of the longest palindrome
		boolean palindrome[][] = new boolean[n][n]; // boolean table to store
													// palindrome truth

		// Trivial case: single letter palindromes
		for (int i = 0; i < n; i++) {
			palindrome[i][i] = true;
		}

		// Finding palindromes of two characters.
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				palindrome[i][i + 1] = true;
				palindromeBeginsAt = i;
				max_len = 2;
			}
		}

		// Finding palindromes of length 3 to n and saving the longest
		for (int curr_len = 3; curr_len <= n; curr_len++) {
			for (int i = 0; i < n - curr_len + 1; i++) {
				int j = i + curr_len - 1;
				if (s.charAt(i) == s.charAt(j) // 1. The first and last
												// characters should match
						&& palindrome[i + 1][j - 1]) // 2. Rest of the substring
														// should be a
														// palindrome
				{
					palindrome[i][j] = true;
					palindromeBeginsAt = i;
					max_len = curr_len;
				}
			}
		}
		return s.substring(palindromeBeginsAt, max_len + palindromeBeginsAt);
	}

	// Longest common Subsequence
	public static void commonSubsequence(String S1, String S2) {
		int s1_len = S1.length();
		int s2_len = S2.length();
		final int pick_s1_or_s2 = 0;
		final int pick_s1 = 1;
		final int pick_s2 = 2;
		int match[][] = new int[s1_len][s2_len];
		int pointer[][] = new int[s1_len][s2_len];
		for (int i = 0; i < s1_len; i++) {
			for (int j = 0; j < s2_len; j++) {
				if (S1.charAt(i) == S2.charAt(j)) // Characters match
				{
					if ((i == 0) || (j == 0)) // first row or first column
					{
						match[i][j] = 1; // initialize
					} else {
						match[i][j] = match[i - 1][j - 1] + 1;
					}
					pointer[i][j] = pick_s1_or_s2;
				} else // Characters mismatch
				{
					if ((i > 0) && (j > 0)) // neither the first row nor first
											// column
					{
						// Refer in ppt :- LCS(ACBE,ADC) = max(LCS(ACB,ADC),
						// LCS(ACBE,AD))
						// Thumb rule:- mismatch take max. if not in first row
						// or column.
						if (match[i - 1][j] >= match[i][j - 1]) {
							match[i][j] = match[i - 1][j];
							pointer[i][j] = pick_s2; // ditch s1's character
														// trimming s1s
														// character
						} else {
							match[i][j] = match[i][j - 1];
							pointer[i][j] = pick_s1;// ditch s2's character.
						}
					} else if ((i == 0) && (j > 0)) // first row
					{
						match[i][j] = match[i][j - 1];
						pointer[i][j] = pick_s1;
					} else if ((j == 0) && (i > 0)) // first column

					{
						match[i][j] = match[i - 1][j];
						pointer[i][j] = pick_s2;
					}

				}

			}
		}
		// Printing the LCS.
		int i = s1_len - 1;
		int j = s2_len - 1;
		StringBuffer result = new StringBuffer();

		while (i >= 0 && j >= 0) {
			switch (pointer[i][j]) {
			// go diagonal and collect the matched character
			case pick_s1_or_s2:
				result.append(S1.charAt(i));
				i--;
				j--;
				break;
			case pick_s1:// go left
				j--;
				break;
			case pick_s2:// go up
				i--;
				break;
			}
		}
		System.out.println(result.reverse());
	}

	// longest palindromic subsequence
	// 1. Initialize a palindrome DP table of size nxn where n is the length of
	// the given String
	// 2. Set single length palindrome values to 1
	// 3. Loop from lengths 2 to n and check palindrome for each length using
	// the following rule
	// palindrome[i][j] = palindrome[i+1][j-1] + 2, if s[i] == s[j]
	// palindrome[i][j] = Math.max(palindrome[i+1][j], palindrome[i][j-1]), if
	// s[i] != s[j]
	// 4. after the loop, return palindrome[0][n-1]
	public static int LPSunsequence(String s) {
		int n = s.length();
		int palindrome[][] = new int[n][n]; // Table to store lengths of
											// palindrome subsequences.

		// Trivial case: single letter palindromes
		for (int i = 0; i < n; i++) {
			palindrome[i][i] = 1;
		}

		// Finding palindromes of length 2 to n and saving the longest
		for (int curr_len = 2; curr_len <= n; curr_len++) {
			for (int i = 0; i < n - curr_len + 1; i++) {
				int j = i + curr_len - 1;
				if (s.charAt(i) == s.charAt(j))// Trim if match and add 2
				{
					palindrome[i][j] = palindrome[i + 1][j - 1] + 2;
				} else // Trim one at a time and take max
				{
					palindrome[i][j] = Math.max(palindrome[i + 1][j], palindrome[i][j - 1]);
				}
			}
		}

		return palindrome[0][n - 1];
	}

	// longest common substring
	public static List<String> commonSubstring(String S1, String S2) {
		Integer match[][] = new Integer[S1.length()][S2.length()];
		int len1 = S1.length();
		int len2 = S2.length();
		int max = Integer.MIN_VALUE; // Maximum length of the string
		ArrayList<String> result = null; // Result list
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (S1.charAt(i) == S2.charAt(j)) {
					if (i == 0 || j == 0)
						match[i][j] = 1;
					else
						match[i][j] = match[i - 1][j - 1] + 1;
					if (match[i][j] > max) // If you find a longer common
											// substring re-initialize the max
											// count and update the result list.
					{
						max = match[i][j];
						result = new ArrayList<String>();
						result.add(S1.substring(i - max + 1, i + 1)); // substring
																		// starts
																		// at
																		// i-max+1
																		// and
																		// ends
																		// at i
					} else if (match[i][j] == max) // else if you find a common
													// substring with the max
													// length, store it in the
													// list.
					{
						result.add(S1.substring(i - max + 1, i + 1));
					}
				} else
					match[i][j] = 0;

			}
		}
		return result;
	}

	// Priority Queue related questions

	// Q215 kth largest element in an array #TopInterviewQuestion
	public int findKthLargest(int[] nums, int k) {
		final PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int val : nums) {
			pq.offer(val);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq.peek();
	}

	// 295. Find Median from Data Stream #TopInterviewQuestion
	// I keep two heaps (or priority queues):
	// Max-heap / small has the smaller half of the numbers.
	// Min-heap / large has the larger half of the numbers.
	// This gives me direct access to the one or two middle values (they're the
	// tops of the heaps), so getting
	// the median takes O(1) time. And adding a number takes O(log n) time.
	// Supporting both min- and max-heap is more or less cumbersome, depending
	// on the language,
	// so I simply negate the numbers in the heap in which I want the reverse of
	// the default order.
	// To prevent this from causing a bug with -231 (which negated is itself,
	// when using 32-bit ints),
	// I use integer types larger than 32 bits.
	// Using larger integer types also prevents an overflow error when taking
	// the mean of the two middle numbers.
	// I think almost all solutions posted previously have that bug.

	private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
	private PriorityQueue<Integer> large = new PriorityQueue<>();
	private boolean even = true;

	public double findMedian() {
		if (even)
			return (small.peek() + large.peek()) / 2.0;
		else
			return small.peek();
	}

	public void addNum(int num) {
		if (even) {
			// only the below line needs to be remembered
			large.offer(num);
			small.offer(large.poll());
		} else {
			small.offer(num);
			large.offer(small.poll());
		}
		even = !even;
	}

	// Another way of doing the same question
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());// smaller
																						// half
	PriorityQueue<Integer> minHeap = new PriorityQueue<>();; // higher half

	public void addNum2(int num) {
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());
		if (maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian2() {
		if (maxHeap.size() == minHeap.size()) {
			return (double) (maxHeap.peek() + (minHeap.peek())) / 2;
		} else {
			return maxHeap.peek();
		}
	}

	// Merge k sorted array //not a leetcode question but similar to merge k
	// sorted lists
	public static int[] mergeKLists(int[][] array) {
		if (array == null || array.length == 0)
			return null;
		PriorityQueue<Integer> queue = new PriorityQueue<>(array.length, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 < o2)
					return -1;
				else if (o1 == o2)
					return 0;
				else
					return 1;
			}
		});
		PriorityQueue<MergedContainer> queue2 = new PriorityQueue<MergedContainer>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				queue.add(array[i][j]);
			}
		}
		while (!queue.isEmpty())
			System.out.println(queue.poll());
		return null;
	}

	// Q23 merge k sorted lists #TopInterviewQuestion
	// Merge k sorted linked lists and return it as one sorted list. Analyze and
	// describe its complexity.
	// Simple solution : iterate over the list and add the elements in the
	// priority queue and then pop it
	public static ListNode mergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
//				if (o1.val < o2.val)
//					return -1;
//				else if (o1.val == o2.val)
//					return 0;
//				else
//					return 1;

				Integer a = o1.val;
                Integer b = o2.val;
                return a.compareTo(b);

			}
		});
		// PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((n1, n2)
		// -> n1.val - n2.val);
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);
		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}
	// Backtracking Questions

	// Q130 Surrounded Regions #TopInterviewQuestion
	// Given a 2D board containing 'X' and 'O' (the letter O), capture all
	// regions surrounded by 'X'.
	// A region is captured by flipping all 'O's into 'X's in that surrounded
	// region.
	// For example,
	// X X X X
	// X O O X
	// X X O X
	// X O X X

	// After running your function, the board should be:
	//
	// X X X X
	// X X X X
	// X X X X
	// X O X X

	// Solution
	// X X X X    X X X X     X X X X
	// X X O X -> X X O X ->  X X X X
	// X O X X    X * X X     X O X X
	// X O X X    X * X X     X O X X
	public static void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;
		if (board.length < 2 || board[0].length < 2)
			return;
		int m = board.length, n = board[0].length;

		// Any 'O' connected to a boundary can't be turned to 'X', so. Start
		// from first and last column, turn 'O' to '*'.
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O')
				boundaryDFS(board, i, 0);
			if (board[i][n - 1] == 'O')
				boundaryDFS(board, i, n - 1);
		}

		// Start from first and last row, turn '0' to '*'
		for (int j = 0; j < n; j++) {
			if (board[0][j] == 'O')
				boundaryDFS(board, 0, j);
			if (board[m - 1][j] == 'O')
				boundaryDFS(board, m - 1, j);
		}
		// post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == '*')
					board[i][j] = 'O';
			}
		}
	}

	// Use DFS algo to turn internal however boundary-connected 'O' to '*';
	private static void boundaryDFS(char[][] board, int i, int j) {
		if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1)
			return;
		if (board[i][j] == 'O')
			board[i][j] = '*';
		if (i > 1 && board[i - 1][j] == 'O')
			boundaryDFS(board, i - 1, j);
		if (i < board.length - 2 && board[i + 1][j] == 'O')
			boundaryDFS(board, i + 1, j);
		if (j > 1 && board[i][j - 1] == 'O')
			boundaryDFS(board, i, j - 1);
		if (j < board[i].length - 2 && board[i][j + 1] == 'O')
			boundaryDFS(board, i, j + 1);
	}

	// Q200 Number of islands #TopInterviewQuestion
	// Given a 2d grid map of '1's (land) and '0's (water), count the number of
	// islands.
	// An island is surrounded by water and is formed by connecting adjacent
	// lands horizontally or vertically.
	// You may assume all four edges of the grid are all surrounded by water.
	private int n;
	private int m;

	public int numIslands(char[][] grid) {
		int count = 0;
		n = grid.length;
		if (n == 0)
			return 0;
		m = grid[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				if (grid[i][j] == '1') {
					DFSMarking(grid, i, j);
					++count;
				}
		}
		return count;
	}

	private void DFSMarking(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1')
			return;
		grid[i][j] = '0';
		DFSMarking(grid, i + 1, j);
		DFSMarking(grid, i - 1, j);
		DFSMarking(grid, i, j + 1);
		DFSMarking(grid, i, j - 1);
	}

	// Q695 maximum area of an island
	// Given a non-empty 2D array grid of 0's and 1's, an island is a group of
	// 1's (representing land) connected
	// 4-directionally (horizontal or vertical.) You may assume all four edges
	// of the grid are surrounded by water.
	// Find the maximum area of an island in the given 2D array. (If there is no
	// island, the maximum area is 0.)
	public int maxAreaOfIsland(int[][] grid) {
		int max_area = 0;
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == 1)
					max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
		return max_area;
	}

	public int AreaOfIsland(int[][] grid, int i, int j) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
			grid[i][j] = 0;
			return 1 + AreaOfIsland(grid, i + 1, j) + AreaOfIsland(grid, i - 1, j) + AreaOfIsland(grid, i, j - 1)
					+ AreaOfIsland(grid, i, j + 1);
		}
		return 0;
	}

	// or
	public int maxAreaOfIsland2(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					int area = dfs(grid, i, j, m, n, 0);
					max = Math.max(area, max);
				}
			}
		}
		return max;
	}

	int dfs(int[][] grid, int i, int j, int m, int n, int area) {
		if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
			return area;
		}
		grid[i][j] = 0;
		area++;
		area = dfs(grid, i + 1, j, m, n, area);
		area = dfs(grid, i, j + 1, m, n, area);
		area = dfs(grid, i - 1, j, m, n, area);
		area = dfs(grid, i, j - 1, m, n, area);
		return area;
	}

	// Q22 generate parenthesis #TopInterviewQuestion
	// Given n pairs of parentheses, write a function to generate all
	// combinations of well-formed parentheses.
	// For example, given n = 3, a solution set is:
	//
	// [
	// "((()))",
	// "(()())",
	// "(())()",
	// "()(())",
	// "()()()"
	// ]
	// second method is a better one
	public static List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		generateOneByOne("", list, n, n);
		return list;
	}

	private static void generateOneByOne(String sublist, List<String> list, int left, int right) {
		if (left > right) {
			return;
		}
		if (left > 0) {
			generateOneByOne(sublist + "(", list, left - 1, right);
			System.out.println(sublist);
		}
		if (right > 0) {
			generateOneByOne(sublist + ")", list, left, right - 1);
			System.out.println(sublist);
		}
		if (left == 0 && right == 0) {
			list.add(sublist);
			return;
		}
	}

	// another solution
	public List<String> generateParenthesisbacktracking(int n) {
		List<String> list = new ArrayList<>();
		backtrack(list, "", 0, 0, n);
		return list;
	}

	public void backtrack(List<String> list, String str, int open, int close, int max) {
		if (str.length() == max * 2) {
			list.add(str);
			return;
		}
		if (open < max)
			backtrack(list, str + "(", open + 1, close, max);
		if (close < open)
			backtrack(list, str + ")", open, close + 1, max);
	}

	// Q79 Word Search #TopInterviewQuestion
	// Given a 2D board and a word, find if the word exists in the grid.
	// The word can be constructed from letters of sequentially adjacent cell,
	// where "adjacent" cells are those horizontally or
	// vertically neighboring. The same letter cell may not be used more than
	// once.
	// board =
	// [
	// ['A','B','C','E'],
	// ['S','F','C','S'],
	// ['A','D','E','E']
	// ]
	//
	// Given word = "ABCCED", return true.
	// Given word = "SEE", return true.
	// Given word = "ABCB", return false.
	static boolean[][] visited;

	public boolean exist(char[][] board, String word) {
		visited = new boolean[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if ((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)) {
					return true;
				}
			}
		}

		return false;
	}

    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }

        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 ||
                board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }

        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) ||
                search(board, word, i+1, j, index+1) ||
                search(board, word, i, j-1, index+1) ||
                search(board, word, i, j+1, index+1)){
            return true;
        }

        visited[i][j] = false;
        return false;
    }

	// Q212 Word Search 2 using trie #TopInterviewQuestion
	// Given a 2D board and a list of words from the dictionary, find all words
	// in the board.
	// Each word must be constructed from letters of sequentially adjacent cell,
	// where "adjacent" cells are those
	// horizontally or vertically neighboring. The same letter cell may not be
	// used more than once in a word.
	// For example,
	// Given words = ["oath","pea","eat","rain"] and board =
	// [
	// ['o','a','a','n'],
	// ['e','t','a','e'],
	// ['i','h','k','r'],
	// ['i','f','l','v']
	// ]

	Set<String> res = new HashSet<String>();

	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board, visited, "", i, j, trie);
			}
		}
		return new ArrayList<String>(res);
	}

	public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
			return;
		if (visited[x][y])
			return;
		str += board[x][y];
		if (!trie.startsWith(str))
			return;
		if (trie.search(str)) {
			res.add(str);
		}
		visited[x][y] = true;
		dfs(board, visited, str, x - 1, y, trie);
		dfs(board, visited, str, x + 1, y, trie);
		dfs(board, visited, str, x, y - 1, trie);
		dfs(board, visited, str, x, y + 1, trie);
		visited[x][y] = false;
	}

	// Q78 Subsets #TopInterviewQuestion
	// Given a set of distinct integers, nums, return all possible subsets (the
	// power set).
	// Input: nums = [1,2,3]
	// Output:
	// [
	// [3],
	// [1],
	// [2],
	// [1,2,3],
	// [1,3],
	// [2,3],
	// [1,2],
	// []
	// ]

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	// Combination of a String
	static void combine(String str) {
		int length = str.length();
		StringBuffer output = new StringBuffer(length);
		combination1(str, length, output, 0);
	}

	static void combination1(String str, int length, StringBuffer output, int allowedStart) {
		for (int curr = allowedStart; curr < length; curr++) {
			output.append(str.charAt(curr));
			System.out.println(output);
			if (curr + 1 != length)
				combination1(str, length, output, curr + 1);
			output.deleteCharAt(output.length() - 1);
			System.out.println("output coming out to be now " + output);
			System.out.println("allowed start is " + allowedStart);
		}
	}
	// Combination Sum
	// Given a collection of candidate numbers (C) and a target number (T), find
	// all unique combinations in C where the candidate numbers sums to T.
	// Each number in C may only be used once in the combination.
	// Note: All numbers (including target) will be positive integers.
	// The solution set must not contain duplicate combinations.
	// For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
	// A solution set is:
	// [
	// [1, 7],
	// [1, 2, 5],
	// [2, 6],
	// [1, 1, 6]
	// ]

	public List<List<Integer>> combinationSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
		if (remain < 0)
			return;
		else if (remain == 0)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, remain - nums[i], i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	// Q131 pallindrome partitioning //TopInterviewQuestion #NOTAskedMuch..dont
	// know why it is topinterviewquestion
	// there is one more solution. check the leetcode.
	// Given a string s, partition s such that every substring of the partition
	// is a palindrome.
	// Return all possible palindrome partitioning of s.
	//
	// For example, given s = "aab",
	// Return
	//
	// [
	// ["aa","b"],
	// ["a","a","b"]
	// ]
	public List<List<String>> partition(String s) {
		List<List<String>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), s, 0);
		return list;
	}

	public void backtrack(List<List<String>> list, List<String> tempList, String s, int start) {
		if (start == s.length())
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome1(s, start, i)) {
					tempList.add(s.substring(start, i + 1));
					backtrack(list, tempList, s, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	public boolean isPalindrome1(String s, int low, int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--))
				return false;
		return true;
	}

	// or
	public List<List<String>> partitionanothermethod(String s) {
		List<List<String>> lists = new ArrayList<>();
		int len = s.length();
		if (len == 0)
			return lists;
		backtrack(lists, new ArrayList<>(), s, 0, len);
		return lists;
	}

	private void backtrack(List<List<String>> lists, List<String> list, String s, int l, int r) {
		if (l == r) {
			lists.add(new ArrayList<>(list));
			return;
		}
		for (int i = l + 1; i <= r; i++) {
			if (isPalindrome(s, l, i)) {
				list.add(s.substring(l, i));
				backtrack(lists, list, s, i, r);
				list.remove(list.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String s, int l, int r) {
		if (l == r - 1)
			return true;
		while (l < r - 1) {
			if (s.charAt(l) != s.charAt(r - 1))
				return false;
			l++;
			r--;
		}
		return true;
	}

	// Q90 Subsets II
	// Given a collection of integers that might contain duplicates, nums,
	// return all possible subsets (the power set).
	public List<List<Integer>> subsetsWithDup(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> empty = new ArrayList<Integer>();
		result.add(empty);
		Arrays.sort(num);

		for (int i = 0; i < num.length; i++) {
			int dupCount = 0;
			while (((i + 1) < num.length) && num[i + 1] == num[i]) {
				dupCount++;
				i++;
			}
			int prevNum = result.size();
			for (int j = 0; j < prevNum; j++) {
				List<Integer> element = new ArrayList<Integer>(result.get(j));
				for (int t = 0; t <= dupCount; t++) {
					element.add(num[i]);
					result.add(new ArrayList<Integer>(element));
				}
			}
		}
		return result;
	}

	// increasing subsequence
	// Given an integer array, your task is to find all the different possible
	// increasing subsequences of the given array,
	// and the length of an increasing subsequence should be at least 2 .
	// Example:
	// Input: [4, 6, 7, 7]
	// Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7],
	// [7,7], [4,7,7]]
	public List<List<Integer>> findSubsequences(int[] nums) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> holder = new ArrayList<Integer>();
		findSequence(res, holder, 0, nums);
		List result = new ArrayList(res);
		return result;
	}

	public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
		if (holder.size() >= 2) {
			res.add(new ArrayList(holder));
		}
		for (int i = index; i < nums.length; i++) {
			if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
				holder.add(nums[i]);
				findSequence(res, holder, i + 1, nums);
				holder.remove(holder.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		String b[] = { "geeksforgeeks", "geeks", "geek", "geezer" };
		// System.out.println(isHappy(19));
		// System.out.println(removeduplicatesfromSortedArray(a,a.length));
		// System.out.println(longestCommonPrefix(b));
		int c[] = { 1, 2, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 8, 9, 9 };
		int len = c.length;
		int k = 2;
		// zigzagconvert("PAYPALISHIRING",3);
		// removeDuplicates(c,len,k);
		int num[] = { 1, 2, 1, 2, 2, 2, 2, 1, 1, 1, 2 };
		// letterCombinations("23");
		// generateParenthesis(3);
		String str = "My name is Rishi Khurana";
		char ch[] = str.toCharArray();
		// System.out.println(compareVersion("23.32","23.43"));
		// System.out.println(gcd(8,12));
		// System.out.println(pascalTriangle(6));
		// System.out.println(generateParenthesis(3));
		int nums[] = { 10, 3, 8, 9, 4 };
		// System.out.println(findRelativeRanks(nums));
	}

	// h index
	// Given an array of citations (each citation is a non-negative integer) of
	// a researcher, write a function
	// to compute the researcher's h-index. According to the definition of
	// h-index on Wikipedia: "A scientist has
	// index h if h of his/her N papers have at least h citations each, and the
	// other N − h papers have no more than
	// h citations each.". For example, given citations = [3, 0, 6, 1, 5], which
	// means the researcher has 5 papers in
	// total and each of them had received 3, 0, 6, 1, 5 citations respectively.
	// Since the researcher has 3 papers with
	// at least 3 citations each and the remaining two with no more than 3
	// citations each, his h-index is 3.
	// Note: If there are several possible values for h, the maximum one is
	// taken as the h-index.

	public int hIndex(int[] citations) {
		int length = citations.length;
		if (length == 0) {
			return 0;
		}

		int[] array2 = new int[length + 1];
		for (int i = 0; i < length; i++) {
			if (citations[i] > length) {
				array2[length] += 1;
			} else {
				array2[citations[i]] += 1;
			}
		}
		int t = 0;
		int result = 0;

		for (int i = length; i >= 0; i--) {
			t = t + array2[i];
			if (t >= i) {
				return i;
			}
		}
		return 0;
	}

	// h index 2
	// What if the citations array is sorted in ascending order? Could you
	// optimize your algorithm?
	public int hIndex2(int[] citations) {
		if (citations == null || citations.length == 0)
			return 0;
		int l = 0, r = citations.length;
		int n = citations.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (citations[mid] == n - mid)
				return n - mid;
			if (citations[mid] < citations.length - mid)
				l = mid + 1;
			else
				r = mid;
		}
		return n - l;
	}

	// Bulb Switcher
	// There are n bulbs that are initially off. You first turn on all the
	// bulbs.
	// Then, you turn off every second bulb. On the third round, you toggle
	// every third bulb
	// (turning on if it's off or turning off if it's on). For the ith round,
	// you toggle every i bulb.
	// For the nth round, you only toggle the last bulb. Find how many bulbs are
	// on after n rounds.
	// Example:
	// Given n = 3.
	// At first, the three bulbs are [off, off, off].
	// After first round, the three bulbs are [on, on, on].
	// After second round, the three bulbs are [on, off, on].
	// After third round, the three bulbs are [on, off, off].
	//
	// So you should return 1, because there is only one bulb is on.
	// explanation : see the leetcode
	double bulbSwitch(int n) {
		return Math.sqrt(n);
	}

	// Additive Number
	// Additive number is a string whose digits can form additive sequence.
	//
	// A valid additive sequence should contain at least three numbers. Except
	// for the first two numbers, each subsequent number in the sequence must be
	// the sum of the preceding two.
	//
	// For example:
	// "112358" is an additive number because the digits can form an additive
	// sequence: 1, 1, 2, 3, 5, 8.
	//
	// 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
	// "199100199" is also an additive number, the additive sequence is: 1, 99,
	// 100, 199.
	// 1 + 99 = 100, 99 + 100 = 199
	// Note: Numbers in the additive sequence cannot have leading zeros, so
	// sequence 1, 2, 03 or 1, 02, 3 is invalid.
	private String add(String a, String b) {
		StringBuilder rst = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1, carry = 0;
		while (i >= 0 || j >= 0 || carry > 0) {
			int sum = carry + (i >= 0 ? a.charAt(i--) - '0' : 0) + (j >= 0 ? b.charAt(j--) - '0' : 0);
			rst.insert(0, sum % 10);
			carry = sum / 10;
		}
		return rst.toString();
	}

	private boolean check(String a, String b, String c) {
		if (a.length() > 1 && a.charAt(0) == '0' || b.length() > 1 && b.charAt(0) == '0')
			return false;
		String sum = add(a, b);
		if (sum.equals(c))
			return true;
		if (c.length() <= sum.length() || !c.substring(0, sum.length()).equals(sum))
			return false;
		return check(b, sum, c.substring(sum.length()));
	}

	public boolean isAdditiveNumber(String num) {
		for (int i = 1; i <= (num.length() >> 1); i++) {
			for (int j = 1; j <= ((num.length() - i) >> 1); j++) {
				if (check(num.substring(0, i), num.substring(i, i + j), num.substring(i + j)))
					return true;
			}
		}
		return false;
	}

	// Repeated DNA sequence
	// All DNA is composed of a series of nucleotides abbreviated as A, C, G,
	// and T, for example: "ACGAATTCCG".
	// When studying DNA, it is sometimes useful to identify repeated sequences
	// within the DNA.
	// Write a function to find all the 10-letter-long sequences (substrings)
	// that occur more than once in a DNA molecule.
	public List<String> findRepeatedDnaSequences(String s) {
		Set seen = new HashSet(), repeated = new HashSet();
		for (int i = 0; i + 9 < s.length(); i++) {
			String ten = s.substring(i, i + 10);
			if (!seen.add(ten))
				repeated.add(ten);
		}
		return new ArrayList(repeated);
	}
	// Candy
	// There are N children standing in a line. Each child is assigned a rating
	// value.
	//
	// You are giving candies to these children subjected to the following
	// requirements:
	//
	// Each child must have at least one candy.
	// Children with a higher rating get more candies than their neighbors.
	// What is the minimum candies you must give?

	public int candy(int[] ratings) {
		int candies[] = new int[ratings.length];
		Arrays.fill(candies, 1);// Give each child 1 candy

		for (int i = 1; i < candies.length; i++) {// Scan from left to right, to
													// make sure right higher
													// rated child gets 1 more
													// candy than left lower
													// rated child
			if (ratings[i] > ratings[i - 1])
				candies[i] = (candies[i - 1] + 1);
		}

		for (int i = candies.length - 2; i >= 0; i--) {// Scan from right to
														// left, to make sure
														// left higher rated
														// child gets 1 more
														// candy than right
														// lower rated child
			if (ratings[i] > ratings[i + 1])
				candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
		}

		int sum = 0;
		for (int candy : candies)
			sum += candy;
		return sum;
	}

	// Arranging coins
	// You have a total of n coins that you want to form in a staircase shape,
	// where every k-th row must have exactly k coins.
	//
	// Given n, find the total number of full staircase rows that can be formed.
	//
	// n is a non-negative integer and fits within the range of a 32-bit signed
	// integer.

	// The idea is about quadratic equation, the formula to get the sum of
	// arithmetic progression is
	// sum = (x + 1) * x / 2
	// so for this problem, if we know the the sum, then we can know the x = (-1
	// + sqrt(8 * n + 1)) / 2
	//

	public int arrangeCoins(int n) {
		return (int) ((-1 + Math.sqrt(1 + 8 * (long) n)) / 2);
	}
	// Poor Pigs
	// With 2 pigs, poison killing in 15 minutes, and having 60 minutes, we can
	// find the poison in up to 25 buckets in the following way. Arrange the
	// buckets in a 5×5 square:
	//
	// 1 2 3 4 5
	// 6 7 8 9 10
	// 11 12 13 14 15
	// 16 17 18 19 20
	// 21 22 23 24 25
	// Now use one pig to find the row (make it drink from buckets 1, 2, 3, 4,
	// 5, wait 15 minutes, make it drink from buckets 6, 7, 8, 9, 10, wait 15
	// minutes, etc). Use the second pig to find the column (make it drink 1, 6,
	// 11, 16, 21, then 2, 7, 12, 17, 22, etc).
	//
	// Having 60 minutes and tests taking 15 minutes means we can run four
	// tests. If the row pig dies in the third test, the poison is in the third
	// row. If the column pig doesn't die at all, the poison is in the fifth
	// column (this is why we can cover five rows/columns even though we can
	// only run four tests).
	//
	// With 3 pigs, we can similarly use a 5×5×5 cube instead of a 5×5 square
	// and again use one pig to determine the coordinate of one dimension (one
	// pig drinks layers from top to bottom, one drinks layers from left to
	// right, one drinks layers from front to back). So 3 pigs can solve up to
	// 125 buckets.
	//
	// In general, we can solve up to (⌊minutesToTest / minutesToDie⌋ + 1)pigs
	// buckets this way, so just find the smallest sufficient number of pigs for
	// example like this:
	//
	// def poorPigs(self, buckets, minutesToDie, minutesToTest):
	// pigs = 0
	// while (minutesToTest / minutesToDie + 1) ** pigs < buckets:
	// pigs += 1
	// return pigs
	// Or with logarithm like I've seen other people do it. That's also where I
	// got the idea from (I didn't really try solving this problem on my own
	// because the judge's solution originally was wrong and I was more
	// interested in possibly helping to make it right quickly).

	// 453. Minimum Moves to Equal Array Elements
	// https://leetcode.com/problems/minimum-moves-to-equal-array-elements/description/
	// have to check again

	// Find all elements disappeared in the array
	// The basic idea is that we iterate through the input array and mark
	// elements as negative using nums[nums[i] -1] = -nums[nums[i]-1]. In this
	// way all the numbers that we have seen will be marked as negative. In the
	// second iteration, if a value is not marked as negative, it implies we
	// have never seen that index before, so just add it to the return list.
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> ret = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				ret.add(i + 1);
			}
		}
		return ret;
	}

	// Diagonal Traverse
	// Given a matrix of M x N elements (M rows, N columns), return all elements
	// of the matrix in diagonal order
	// as shown in the below image.
	// Example:
	// Input:
	// [
	// [ 1, 2, 3 ],
	// [ 4, 5, 6 ],
	// [ 7, 8, 9 ]
	// ]
	// Output: [1,2,4,7,5,3,6,8,9]
	// Explanation:
	//
	// Walk patterns:
	//
	// If out of bottom border (row >= m) then row = m - 1; col += 2; change
	// walk direction.
	// if out of right border (col >= n) then col = n - 1; row += 2; change walk
	// direction.
	// if out of top border (row < 0) then row = 0; change walk direction.
	// if out of left border (col < 0) then col = 0; change walk direction.
	// Otherwise, just go along with the current direction.
	// Time complexity: O(m * n), m = number of rows, n = number of columns.
	// Space complexity: O(1).
	public int[] findDiagonalOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return new int[0];
		int m = matrix.length, n = matrix[0].length;

		int[] result = new int[m * n];
		int row = 0, col = 0, d = 0;
		int[][] dirs = { { -1, 1 }, { 1, -1 } };

		for (int i = 0; i < m * n; i++) {
			result[i] = matrix[row][col];
			row += dirs[d][0];
			col += dirs[d][1];

			if (row >= m) {
				row = m - 1;
				col += 2;
				d = 1 - d;
			}
			if (col >= n) {
				col = n - 1;
				row += 2;
				d = 1 - d;
			}
			if (row < 0) {
				row = 0;
				d = 1 - d;
			}
			if (col < 0) {
				col = 0;
				d = 1 - d;
			}
		}
		return result;
	}

	// Rotate Function
	// Given an array of integers A and let n to be its length
	// Assume Bk to be an array obtained by rotating the array A k positions
	// clock-wise, we define a "rotation function" F on A as follow:
	// F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
	// Calculate the maximum value of F(0), F(1), ..., F(n-1).
	public int maxRotateFunction(int[] A) {
		int allSum = 0;
		int len = A.length;
		int F = 0;
		for (int i = 0; i < len; i++) {
			F += i * A[i];
			allSum += A[i];
		}
		int max = F;
		for (int i = len - 1; i >= 1; i--) {
			F = F + allSum - len * A[i];
			max = Math.max(F, max);
		}
		return max;
	}

	// Restore IP addresses
	// Given a string containing only digits, restore it by returning all
	// possible valid IP address combinations.
	// For example:
	// Given "25525511135",
	// return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

	// 3-loop divides the string s into 4 substring: s1, s2, s3, s4. Check if
	// each substring is valid.
	// In isValid, strings whose length greater than 3 or equals to 0 is not
	// valid; or if the string's length is longer than 1 and the first letter is
	// '0' then it's invalid; or the string whose integer representation greater
	// than 255 is invalid.
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k),
							s4 = s.substring(k, len);
					if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
						res.add(s1 + "." + s2 + "." + s3 + "." + s4);
					}
				}
			}
		}
		return res;
	}

	public boolean isValid(String s) {
		if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
			return false;
		return true;
	}

	// Another way of vaidating ip address
	public List<String> restoreIpAddresses2(String s) {
		List<String> solutions = new ArrayList<String>();
		restoreIp(s, solutions, 0, "", 0);
		return solutions;
	}

	private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
		if (count > 4)
			return;
		if (count == 4 && idx == ip.length())
			solutions.add(restored);
		for (int i = 1; i < 4; i++) {
			if (idx + i > ip.length())
				return;
			String s = ip.substring(idx, idx + i);
			if ((s.startsWith("0") && s.length() > 1) || (Integer.parseInt(s) >= 256))
				return;
			restoreIp(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1);
		}
	}

	// Q461 Hamming distance
	// XOR can help you find out at what positions bits are different. i.e. 1001
	// ^ 0111 = 1110.
	// Then things left is to count how many 1 in above result.
	// Integer.bitCount() can help you do that.
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	// Q409 Longest pallindrome .. useless question
	// Given a string which consists of lowercase or uppercase letters, find the
	// length of the longest palindromes
	// that can be built with those letters.
	public int longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> hs = new HashSet<Character>();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(i));
				count++;
			} else {
				hs.add(s.charAt(i));
			}
		}
		if (!hs.isEmpty())
			return count * 2 + 1;
		return count * 2;
	}
	// Range Sum query 2d - immutable
	// Given a 2D matrix matrix, find the sum of the elements inside the
	// rectangle defined by its upper left corner (row1, col1) and lower right
	// corner (row2, col2).

	// Q308 Range Sum Query 2D #TopInterviewQuestion
	// The above rectangle (with the red border) is defined by (row1, col1) =
	// (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
	int[][] dp;

	public class NumMatrix1 {
		public NumMatrix1(int[][] matrix) {
			if (matrix == null || matrix.length == 0)
				return;
			int m = matrix.length, n = matrix[0].length;
			dp = new int[m + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				for (int j = 1; j <= n; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
		}
	}

}
