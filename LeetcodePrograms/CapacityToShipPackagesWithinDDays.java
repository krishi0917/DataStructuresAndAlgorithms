package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 1011. Capacity To Ship Packages Within D Days
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i].  Each day, we load the ship with packages on the
 * conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the
 * ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being
 * shipped within D days.
 *
 * Binary Search problem just like split array largest sum and divide chocolate
 */
public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
