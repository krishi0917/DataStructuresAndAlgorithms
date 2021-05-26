package LeetcodePrograms;

/**
 * Created by rkhurana on 11/2/18.
 */
public class CoinChangeProblem2 {
    public static int coinChange(int[] coins, int amount) {
        Integer T[][] = new Integer [coins.length+1 ][amount+1];
        for(int i =0 ; i <=coins.length ; i++ ) {

                T[i][0] = 1;
        }
        for(int j =0 ; j <=amount ; j++ ) {
            if (j == 0)
                T[0][j] = 1;
            else
                T[0][j] = 0;
        }


        for(int i = 1; i <= coins.length ; i++){
            for(int j = 1 ; j <= amount ; j++){

                if(i==0 && j == 0 )
                    T[i][j] = 1;
                else if(i==0)
                    T[i][j] = 0;
                else
                {    if(i > j)
                    T[i][j] = T[i-1][j];

                else
                    T[i][j] = T[i-1][j] + T[i][j-i];
                }

            }
        }
        return T[coins.length ] [amount];
    }
    public static void main(String []args){
        int []coins = {1,2,5};
        System.out.println(coinChange(coins , 11));
    }
}
