package LeetcodePrograms.InterviewQuestions;
import java.util.*;
public class LucidVehicleRotation {

    private static boolean allVehiclesCoveredAllCategories(Map<String, Set<String>> map) {
        for (Set<String> categories : map.values()) {
            if (categories.size() < 2) return false; // we have only A and B, check basically if every set has received both categories. if not then keep running
        }
        return true;
    }

    public static void scheduleRotation(int totalVehicles, int percentA, int percentB){
        List<String> vehicles = new ArrayList<>();
        for (int i = 1; i <= totalVehicles; i++) {
            vehicles.add("v" + i);
        }

        int countA = (percentA * totalVehicles) / 100;
        int countB = totalVehicles - countA; // Remaining goes to B

        // To track which vehicle has gone to which category
        // purpose of vehicleToCategories map is "I keep a map from each vehicle to the set of categories it has visited.
        // After every round, I update this. I check if we are done by making sure every vehicle has been to all the categories.
        // If yes, we stop. Otherwise, we keep running rounds."
        Map<String, Set<String>> vehicleToCategories = new HashMap<>();
        for (String v : vehicles) {
            vehicleToCategories.put(v, new HashSet<>());
        }

        List<Map<String, List<String>>> rounds = new ArrayList<>();
        int round = 1;
        int startIndex = 0;

        while (!allVehiclesCoveredAllCategories(vehicleToCategories)) {
            Map<String, List<String>> roundAssignment = new HashMap<>();
            List<String> catA = new ArrayList<>();
            List<String> catB = new ArrayList<>();

            // Use circular indexing to assign vehicles
            for (int i = 0; i < countA; i++) {
                String v = vehicles.get((startIndex + i) % totalVehicles);
                catA.add(v);
                vehicleToCategories.get(v).add("A");
            }
            for (int i = countA; i < totalVehicles; i++) {
                String v = vehicles.get((startIndex + i) % totalVehicles);
                catB.add(v);
                vehicleToCategories.get(v).add("B");
            }

            roundAssignment.put("A", catA);
            roundAssignment.put("B", catB);
            rounds.add(roundAssignment);

            startIndex++; // rotate starting point
            round++;
        }

        // Print all rounds
        for (int i = 0; i < rounds.size(); i++) {
            System.out.println("Round " + (i + 1));
            Map<String, List<String>> roundMap = rounds.get(i);
            for (String category : roundMap.keySet()) {
                System.out.println("  " + category + ": " + roundMap.get(category));
            }
        }
    }

    public static void main(String[] args) {
        scheduleRotation(10,20,80);
    }
}
