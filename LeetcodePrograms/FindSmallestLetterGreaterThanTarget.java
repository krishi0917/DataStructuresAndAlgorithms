package LeetcodePrograms;
/*
744. Find Smallest Letter Greater Than Target
Given a characters array letters that is sorted in non-decreasing order and a character target, return the smallest character in the array that is larger than target.
Note that the letters wrap around.
For example, if target == 'z' and letters == ['a', 'b'], the answer is 'a'.
Example 1:
Input: letters = ["c","f","j"], target = "a"
Output: "c"
Example 2:
Input: letters = ["c","f","j"], target = "c"
Output: "f"
 */

public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters[0] > target) return letters[0];
        if (letters[letters.length - 1] <= target) return letters[0];

        return binarySearch(letters, target);
    }

    private char binarySearch(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) {
                start = mid + 1;
            }
            else if (letters[mid] > target) {
                end = mid - 1;
            }
        }
        return letters[start];
    }
}
