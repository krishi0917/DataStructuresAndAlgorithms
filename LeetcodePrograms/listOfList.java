package LeetcodePrograms;

/**
 * Created by rkhurana on 7/1/18.
 */
import java.util.ArrayList;
import java.util.List;

public class listOfList {
    ArrayList<ArrayList<Integer>> list;

    private int outerIndex = 0; // for outer list
    private int innerIndex = 0; // for inner list

    public listOfList(ArrayList<ArrayList<Integer>> list) {
        this.list = list;

    }

    public boolean hasNext() {
        while (outerIndex < list.size()) {
            List<Integer> insideList = list.get(outerIndex);
            if (innerIndex < insideList.size()) {
                return true;
            } else {
                outerIndex++;
            }
        }
        return false;
    }

    public int next() {
        if (hasNext()) {
            List<Integer> thisList = list.get(innerIndex);
            if (innerIndex == thisList.size() - 1) {
                //int temp = outerIndex;
                //outerIndex = 0;
                //innerIndex++;
                innerIndex = 0;
                outerIndex++;
                int temp =outerIndex;
                return thisList.get(temp);
            } else if (outerIndex < thisList.size()) {
                return thisList.get(outerIndex++);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> firstList = new ArrayList<Integer>();
        ArrayList<Integer> secondList = new ArrayList<Integer>();
        ArrayList<Integer> thirdList = new ArrayList<Integer>();
        firstList.add(2);
        firstList.add(3);
        firstList.add(4);
        firstList.add(5);
        secondList.add(7);
        secondList.add(7);
        thirdList.add(6);
        thirdList.add(1);
        thirdList.add(9);
        thirdList.add(10);
        thirdList.add(11);
        thirdList.add(12);
        listOfLists.add(firstList);
        listOfLists.add(secondList);
        listOfLists.add(thirdList);

        listOfList itr = new listOfList(listOfLists);
        while (itr.hasNext()) {
            int x = (int) itr.next();
            System.out.println(x);
        }

    }

}
