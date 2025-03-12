package LeetcodePrograms.src;

public class KokoEatingBananas {
    /*
}
    Thought Process - Understanding the Problem

    We are given n piles of bananas.
    Koko can eat at a speed of k bananas per hour.
    We must determine the minimum value of k such that Koko can finish all the bananas within h hours.
    Brute Force Approach (Not Optimal)

    Try all possible values of k from 1 to max(piles), checking if Koko can finish within h hours.
    This results in an inefficient O(max(piles) * n) time complexity.
    Optimized Approach (Binary Search)

    Since k must be between 1 (minimum eating speed) and max(piles) (eating the largest pile in one go), we can use Binary Search on k.
    If a mid-value k allows finishing within h hours, try a smaller k to minimize it.
    If k is too small, increase it.
    Time Complexity Analysis

    The binary search runs in O(log max(piles)), and for each k, we compute total hours in O(n).
    Overall, O(n log max(piles)) is optimal.
*/
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            // Step 1: Find the search range for 'k'
            int left = 1, right = getMaxPile(piles);
            int result = right; // Initialize result with the highest possible value

            // Step 2: Perform Binary Search
            while (left <= right) {
                int mid = left + (right - left) / 2; // Midpoint of current range

                // Check if Koko can finish all bananas at speed 'mid'
                if (canEatAll(piles, h, mid)) {
                    result = mid; // Update result to find the minimum possible k
                    right = mid - 1; // Try to minimize k
                } else {
                    left = mid + 1; // Increase speed as h is too small
                }
            }
            return result;
        }

        // Helper function to get the max pile size
        private int getMaxPile(int[] piles) {
            int max = 0;
            for (int pile : piles) {
                max = Math.max(max, pile);
            }
            return max;
        }

        // Helper function to check if Koko can eat all bananas within 'h' hours at speed 'k'
        private boolean canEatAll(int[] piles, int h, int k) {
            int hoursNeeded = 0;
            for (int pile : piles) {
                hoursNeeded += Math.ceil((double) pile / k); // Compute hours needed for each pile
            }
            return hoursNeeded <= h; // Return if it's possible to finish in 'h' hours
        }
    }
}
    /*
    Explanation of Each Part
1. Initializing the Search Range
    int left = 1, right = getMaxPile(piles);
    left = 1: The minimum speed Koko can eat is 1 banana per hour.
            right = getMaxPile(piles): The maximum speed would be eating the largest pile in one hour.
            2. Implementing Binary Search
            java
    Copy
            Edit
while (left <= right) {
        int mid = left + (right - left) / 2;
        We use binary search to find the smallest k that works.
                mid represents a possible eating speed.
        3. Checking if Speed mid is Sufficient
        java
                Copy
        Edit
        if (canEatAll(piles, h, mid)) {
            result = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
        If mid is enough to eat within h hours, try a smaller value (right = mid - 1).
                If not, increase k (left = mid + 1).
        4. Computing Maximum Pile Value
        java
                Copy
        Edit
        private int getMaxPile(int[] piles) {
            int max = 0;
            for (int pile : piles) {
                max = Math.max(max, pile);
            }
            return max;
        }
        Finds the largest pile, which determines the upper limit for k.
        5. Checking Feasibility of Eating Speed
        private boolean canEatAll(int[] piles, int h, int k) {
            int hoursNeeded = 0;
            for (int pile : piles) {
                hoursNeeded += Math.ceil((double) pile / k);
            }
            return hoursNeeded <= h;
        }
        Computes total hours required at speed k.
                Uses Math.ceil(pile / k) to round up (eating partial bananas requires a full extra hour).
                Time and Space Complexity
        Complexity	Analysis
        Time Complexity	O(n log max(piles))
        Space Complexity	O(1) (constant extra space)
        The binary search runs in O(log max(piles)).
        Checking canEatAll runs in O(n).
                The overall complexity is O(n log max(piles)), which is optimal.

    }
*/