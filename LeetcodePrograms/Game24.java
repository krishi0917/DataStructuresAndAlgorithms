package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/17/19.
 */
// #Uber
// 679. 24 Game
// You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through
// *, /, +, -, (, ) to get the value of 24.
// this method only tells true or false. What if we need the comeplete expression ?
public class Game24 {

public boolean judgePoint24(int[] nums) {
    List<Double> list = new ArrayList<>();
    for (int i : nums) {
        list.add((double) i);
    }
    return dfs(list);
}

    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            if (Math.abs(list.get(0)- 24.0) < 0.001) {
                return true;
            }
            return false;
        }

        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                for (double c : generatePossibleResults(list.get(i), list.get(j))) {
                    List<Double> nextRound = new ArrayList<>();
                    nextRound.add(c);
                    for(int k = 0; k < list.size(); k++) {
                        if(k == j || k == i)
                            continue;
                        nextRound.add(list.get(k));
                    }
                    if(dfs(nextRound))
                        return true;
                }
            }
        }
        return false;

    }
    private List<Double> generatePossibleResults(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        res.add(a / b);
        res.add(b / a);
        return res;
    }


    public static void main(String []args){
        Game24 game = new Game24();
        int []nums = {4,1,8,7};
        System.out.println(game.judgePoint24(nums));
    }


}