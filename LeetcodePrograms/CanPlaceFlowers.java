package LeetcodePrograms;

/**
 * Created by rkhurana on 2/24/19.
 */
// Q605 Can Place Flowers
// Suppose you have a long flowerbed in which some of the plots are planted and some are not. However,
// flowers cannot be planted in adjacent plots - they would compete for water and both would die.
// Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
// and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

public class CanPlaceFlowers {

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

    //this method is better
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i = 0; i < flowerbed.length && count < n; i++) {
            if(flowerbed[i] == 0) {
                //get next and prev flower bed slot values. If i lies at the ends the next and prev are considered as 0.
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if(next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }

        return count == n;
    }


}
