package LeetcodePrograms.src;

public class Candies {
    import java.util.*;

    // Box class representing the structure of each box
    class Box {
        boolean isLocked;
        int candies;
        List<Integer> containedBoxes;
        List<Integer> keys;

        public Box(boolean isLocked, int candies, List<Integer> containedBoxes, List<Integer> keys) {
            this.isLocked = isLocked;
            this.candies = candies;
            this.containedBoxes = containedBoxes;
            this.keys = keys;
        }
    }

    public class MaximumCandies {

        // Method to get the maximum candies we can collect
        public int maxCandies(Box[] boxes, int start) {
            int totalCandies = 0;
            Set<Integer> visited = new HashSet<>();  // Track visited boxes
            Set<Integer> collectedKeys = new HashSet<>();  // Track collected keys
            Queue<Integer> queue = new LinkedList<>();  // BFS queue

            // Start with the given box if it is unlocked
            if (!boxes[start].isLocked) {
                queue.add(start);
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (visited.contains(current)) continue;
                visited.add(current);

                // Collect candies from the current box
                totalCandies += boxes[current].candies;

                // Collect keys from the current box
                for (int key : boxes[current].keys) {
                    collectedKeys.add(key);
                    // If we now have a key to a box that was previously locked
                    if (!visited.contains(key) && boxes[key].isLocked) {
                        queue.add(key);
                        boxes[key].isLocked = false;
                    }
                }

                // Add contained boxes to the queue
                for (int box : boxes[current].containedBoxes) {
                    if (!visited.contains(box)) {
                        if (!boxes[box].isLocked || collectedKeys.contains(box)) {
                            queue.add(box);
                            boxes[box].isLocked = false;
                        }
                    }
                }
            }

            return totalCandies;
        }

        public static void main(String[] args) {
            // Example setup with given scenarios
            Box[] boxes = new Box[4];
            boxes[0] = new Box(false, 7, Arrays.asList(1, 2), Arrays.asList());
            boxes[1] = new Box(true, 5, Arrays.asList(3), Arrays.asList());
            boxes[2] = new Box(false, 4, Arrays.asList(), Arrays.asList(1));
            boxes[3] = new Box(true, 20, Arrays.asList(), Arrays.asList());

            MaximumCandies mc = new MaximumCandies();
            int result = mc.maxCandies(boxes, 0);
            System.out.println("Maximum Candies: " + result);  // Expected output: 16
        }
    }

}
