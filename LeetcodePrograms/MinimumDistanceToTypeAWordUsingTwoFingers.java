package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 1320. Minimum Distance to Type a Word Using Two Fingers
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some
 * coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1),
 * the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 *
 * Given the string word, return the minimum total distance to type such string using only two fingers. The distance
 * between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 *
 * Note that the initial positions of your two fingers are considered free so don't count towards your total
 * distance, also your two fingers do not have to start at the first letter or the first two letters.
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 */
public class MinimumDistanceToTypeAWordUsingTwoFingers {
    int distance(int[] f1, int[] f2) {
        if(f1 == null || f2 == null) {
            return 0;
        }
        return Math.abs(f1[0] - f2[0]) + Math.abs(f1[1] - f2[1]);
    }

    Map<Character, int[]> keyMap = new HashMap<>();
    Map<String, Integer> dpCache = new HashMap<>();

    public int minimumDistance(String word) {
        char curr = 'A';
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 6; j++) {
                keyMap.put(curr, new int[]{i, j});
                if(curr == 'Z')
                    break;
                curr++;
            }
        }
        return solve(null, null, 0, word);
    }

    int solve(int[] f1, int[] f2, int index, String word) {
        if(index >= word.length())
            return 0;
        String key = Arrays.toString(f1) + ";" + Arrays.toString(f2) + ";" + index;
        if(dpCache.containsKey(key))
            return dpCache.get(key);
        int f1Use = distance(f1, keyMap.get(word.charAt(index))) + solve(keyMap.get(word.charAt(index)), f2, index + 1, word);
        int f2Use = distance(f2, keyMap.get(word.charAt(index))) + solve(f1, keyMap.get(word.charAt(index)), index + 1, word);
        int output = Math.min(f1Use, f2Use);
        dpCache.put(key, output);
        return output;
    }
    public static void main(String []args){
        MinimumDistanceToTypeAWordUsingTwoFingers m = new MinimumDistanceToTypeAWordUsingTwoFingers();
        System.out.println(m.minimumDistance("CAKE"));
    }
}
