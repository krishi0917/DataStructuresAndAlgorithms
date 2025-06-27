package LeetcodePrograms.InterviewQuestions;

import java.util.*;

public class LucidScheduleCharging {
    public static List<List<Integer>> scheduleCharging(int[] SoC, int criticalThreshold, int maxStations, int chargeRate) {
        int n = SoC.length;
        int[] soc = Arrays.copyOf(SoC, n); // Mutable SoC tracking
        System.out.println(soc);
        List<List<Integer>> chargingOrder = new ArrayList<>();

        while (true) {
            // PriorityQueue of index sorted by current SoC (lowest first)
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> soc[a] - soc[b]);

            for (int i = 0; i < n; i++) {
                if (soc[i] < criticalThreshold) {
                    pq.offer(i);
                }
            }

            // If all vehicles are above threshold, we’re done
            if (pq.isEmpty()) break;

            List<Integer> chargingNow = new ArrayList<>();
            for (int i = 0; i < maxStations && !pq.isEmpty(); i++) {
                int index = pq.poll();
                chargingNow.add(index);
            }

            // Apply charging
            for (int idx : chargingNow) {
                soc[idx] += chargeRate;
            }

            chargingOrder.add(chargingNow);
        }

        return chargingOrder;
    }

    public static void main(String[] args) {
        int[] SoC = {15, 30, 25, 10, 45, 50, 20, 5, 35, 40, 55, 60, 18, 28, 12};
        int criticalThreshold = 20;
        int maxStations = 3;
        int chargeRate = 10;

        List<List<Integer>> result = scheduleCharging(SoC, criticalThreshold, maxStations, chargeRate);
        for (List<Integer> round : result) {
            System.out.println(round);
        }
    }

}
