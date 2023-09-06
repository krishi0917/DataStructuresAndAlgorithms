package LeetcodePrograms.src;

// Q605 Can Place Flowers
// Suppose you have a long flowerbed in which some plots are planted and some are not. However,
// flowers cannot be planted in adjacent plots - they would compete for water and both would die.
// Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
// and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

public class CanPlaceFlowers {

    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int count = 0;
        int i = 0;
        while(i < flowerbed.length){
            if(flowerbed[i] == 0){
                if((i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)){
                    flowerbed[i] = 1;
                    count++;
                }
            }
            i++;
        }
        return count >= n;
    }

    //this method is better
    // basically checking the next and prev value whenever we encounter 0. If next or prev value is not 0 move forward, if 0, then increment the count
    // and in the end match the count with n input variable and return true or false
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
