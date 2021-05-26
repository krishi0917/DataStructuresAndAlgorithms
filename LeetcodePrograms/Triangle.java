package LeetcodePrograms;
import java.util.*;

/**
 * Created by rkhurana on 7/12/18.
 */

//Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
//For example, given the following triangle
//[
//[2],
//[3,4],
//[6,5,7],
//[4,1,8,3]
//]
//The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
public class Triangle {
//    public static int minimumTotal(List<List<Integer>> triangle) {
//        int level = triangle.size();
//        List<Integer> result = new ArrayList(triangle.get(level-1));
//        for(int i = level - 2; i >= 0; i--)
//            for(int j = 0; j <= i; j++)
//                result.set(j,Math.min(result.get(j),result.get(j+1)) + triangle.get(i).get(j));
//        return result.get(0);
//    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        Deque<Integer> queue = new LinkedList<Integer>();
        int count=triangle.size();
        queue.add(triangle.get(0).get(0));
        for (int i=1;i<count;i++){
            List<Integer> list= triangle.get(i);
            for (int j=0;j<=i;j++){
                int min=0;
                if (j==0)
                    min=list.get(0)+queue.peekFirst();
                else if (j==i)
                    min =list.get(j)+queue.pollFirst();
                else {
                    System.out.println(queue.peekFirst());
                    min = Math.min(queue.pollFirst(), queue.peekFirst()) + list.get(j);
                }
                queue.addLast(min);
            }
        }
        int result=Integer.MAX_VALUE;
        for (int i=0;i<count;i++)
            result=Math.min(result, queue.pollFirst());
        return result;
    }

    //the below code looks more sensible to me ...
//    The idea is simple.
//
//    Go from bottom to top.
//
//    We start form the row above the bottom row [size()-2].
//
//    Each number add the smaller number of two numbers that below it.
//
//    And finally we get to the top we the smallest sum
    public static int minimumTotal3(List<List<Integer>> triangle) {
        for(int i = triangle.size() - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }

    public static void main(String [] args ){
        List<List<Integer>> mainList = new ArrayList<>();
        List<Integer> sublist1 = new ArrayList<>();
        List<Integer> sublist2 = new ArrayList<>();
        List<Integer> sublist3 = new ArrayList<>();
        List<Integer> sublist4 = new ArrayList<>();
        sublist1.add(2);
        sublist2.add(3);
        sublist2.add(4);
        sublist3.add(6);
        sublist3.add(5);
        sublist3.add(7);
        sublist4.add(4);
        sublist4.add(1);
        sublist4.add(8);
        sublist4.add(3);
        mainList.add(sublist1);
        mainList.add(sublist2);
        mainList.add(sublist3);
        mainList.add(sublist4);
        minimumTotal2(mainList);

    }
}
