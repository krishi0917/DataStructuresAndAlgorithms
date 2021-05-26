package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Rishi Khurana
 */
public class AmazonCompartmentQuestion {
    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < startIndices.size(); i++) {
            int tempCount = 0;
            int count = 0;
            boolean startCompartment = false;

            for (int j = startIndices.get(i) - 1; j < endIndices.get(i); j++) {
                char c = s.charAt(j);

                if (c == '|' && startCompartment == true) {
                    count += tempCount;
                    tempCount = 0;
                } else if (c == '|' && startCompartment == false) {
                    startCompartment = true;
                } else if (c == '*' && startCompartment == true) {
                    tempCount++;
                }
            }

            list.add(count);
        }

        return list;
    }

    public static void main(String []args){
        String s = "*|*|*|";
        List<Integer> startIndices = Arrays.asList(1,1);
        List<Integer> endIndices = Arrays.asList(1,6);
        System.out.println(numberOfItems(s,startIndices,endIndices));
    }
}
