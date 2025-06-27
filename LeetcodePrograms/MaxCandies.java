package LeetcodePrograms;

import java.util.*;

public class MaxCandies {
    static class Box {
        boolean isLocked;
        int candies;
        List<Integer> boxes;
        List<Integer> keys;

        public Box(boolean isLocked, int candies, List<Integer> boxes, List<Integer> keys) {
            this.isLocked = isLocked;
            this.candies = candies;
            this.boxes = boxes;
            this.keys = keys;
        }
    }


    public int maxCandies(Box[] boxArray, int initialBox) {
        // Step 1: Initialize structures
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> keys = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int totalCandies = 0;

        // Step 2: Add the initial box if it is unlocked
        if (!boxArray[initialBox].isLocked) {
            queue.offer(initialBox);
            visited.add(initialBox);
        }

        // Step 3: BFS traversal
        while (!queue.isEmpty()) {
            int currentBox = queue.poll();
            Box box = boxArray[currentBox];

            // Step 4: Add candies from the current box
            totalCandies += box.candies;

            // Step 5: Collect keys from the current box
            for (int key : box.keys) {
                keys.add(key);
            }

            // Step 6: Explore inner boxes
            for (int nextBox : box.boxes) {
                if (!visited.contains(nextBox) &&
                        (!boxArray[nextBox].isLocked || keys.contains(nextBox))) {
                    queue.offer(nextBox);
                    visited.add(nextBox);
                }
            }
        }

        // Step 7: Return the maximum candies collected
        return totalCandies;
    }

    public static void main(String[] args) {
        MaxCandies mc = new MaxCandies();

        // Example usage
        Box[] boxes = new Box[4];
        boxes[0] = new Box(false, 7, Arrays.asList(1, 2), Collections.emptyList());
        boxes[1] = new Box(true, 5, Arrays.asList(3), Collections.emptyList());
        boxes[2] = new Box(false, 4, Collections.emptyList(), Arrays.asList(1));
        boxes[3] = new Box(true, 20, Collections.emptyList(), Collections.emptyList());

        int result = mc.maxCandies(boxes, 0);
        System.out.println("Maximum Candies: " + result);  // Output: 16
    }
}
