package LeetcodePrograms.src;

import java.util.*;

public class DasherAllocator {
    private Map<Integer, String> indexToDasher;
    private Random random;

    public DasherAllocator(List<String> dasherIds) {
        indexToDasher = new HashMap<>();
        for (int i = 0; i < dasherIds.size(); i++) {
            indexToDasher.put(i, dasherIds.get(i));
        }
        random = new Random();
    }

    public void assignDasher() {
        if (indexToDasher.isEmpty()) {
            System.out.println("No dashers available to assign.");
            return;
        }

        // Get current valid indices
        List<Integer> keys = new ArrayList<>(indexToDasher.keySet());
        int randIndex = keys.get(random.nextInt(keys.size()));
        String assignedDasher = indexToDasher.get(randIndex);

        System.out.println("Assigned dasher: " + assignedDasher + " from index: " + randIndex);

        // Remove the selected dasher
        int lastIndex = getMaxIndex();
        if (randIndex != lastIndex) {
            // Move the last dasher to the removed index
            String lastDasher = indexToDasher.get(lastIndex);
            indexToDasher.put(randIndex, lastDasher);
        }
        indexToDasher.remove(lastIndex); // Remove the last dasher
    }

    private int getMaxIndex() {
        return Collections.max(indexToDasher.keySet());
    }

    public void printDashers() {
        System.out.println("Current dasher pool:");
        for (Map.Entry<Integer, String> entry : indexToDasher.entrySet()) {
            System.out.println("Index: " + entry.getKey() + " => DasherId: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        List<String> dasherIds = Arrays.asList("D1", "D2", "D3", "D4", "D5");
        DasherAllocator allocator = new DasherAllocator(dasherIds);

        allocator.printDashers();
        System.out.println("--- Assigning Dasher ---");
        allocator.assignDasher();

        allocator.printDashers();
        System.out.println("--- Assigning Dasher ---");
        allocator.assignDasher();

        allocator.printDashers();
    }
}
