package LeetcodePrograms;

/**
 * Created by rkhurana on 12/3/18.
 */
public class BestTimeToBuyANDSellStocks {
    public static int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0)
            return 0;
        int i = 0;
        int peak;
        int valley;
        int maxProfit = 0;
        int len = prices.length;
        while (i < len - 1) {
            while (i < len - 1 && prices[i] >= prices[i + 1]) {// down
                i++;
            }
            valley = prices[i];
            while (i < len - 1 && prices[i] <= prices[i + 1]) {// up
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }
    public static void main(String [] args){
        int []num ={7,1,5,3,6,4};
        System.out.println(maxProfit(num));
    }
}
