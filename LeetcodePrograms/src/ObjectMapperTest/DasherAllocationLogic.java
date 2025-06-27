package LeetcodePrograms.src.ObjectMapperTest;

import java.util.*;

public class DasherAllocationLogic {
    private Map<Integer, String> indexToDasher;
    private int indexLastPosition;
    private Random random;

    public DasherAllocationLogic(Map<Integer, String> initialMap) {
        this.indexToDasher = new HashMap<>(initialMap);
        this.indexLastPosition = initialMap.size() - 1;
        this.random = new Random();
    }

    public void assignDasher() {
        if (indexToDasher.isEmpty()) {
            System.out.println("No dashers available to assign.");
            return;
        }

        // Pick a random index from available indices (0 to indexLastPosition)
        int randIndex = random.nextInt(indexLastPosition + 1);
        String assignedDasher = indexToDasher.get(randIndex);

        System.out.println("Assigned dasher: " + assignedDasher + " from index: " + randIndex);

        // Replace selected index with last index's dasher (if not the same)
        if (randIndex != indexLastPosition) {
            String lastDasher = indexToDasher.get(indexLastPosition);
            indexToDasher.put(randIndex, lastDasher);
        }

        // Remove the last index
        indexToDasher.remove(indexLastPosition);

        // Decrement index pointer
        indexLastPosition--;
    }

    public void printDashers() {
        System.out.println("Current Dasher Pool:");
        for (int i = 0; i <= indexLastPosition; i++) {
            if (indexToDasher.containsKey(i)) {
                System.out.println("Index: " + i + " -> Dasher: " + indexToDasher.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> initialDashers = new HashMap<>();
        initialDashers.put(0, "D1");
        initialDashers.put(1, "D2");
        initialDashers.put(2, "D3");
        initialDashers.put(3, "D4");
        initialDashers.put(4, "D5");

        DasherAllocationLogic allocator = new DasherAllocationLogic(initialDashers);

        allocator.printDashers();
        allocator.assignDasher();
        allocator.printDashers();
        allocator.assignDasher();
        allocator.printDashers();
    }
}
