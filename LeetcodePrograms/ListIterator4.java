package LeetcodePrograms;
// Collection of iterators , given a collection of iterator write hasNext and next method
// Example : {{4,3,2,1},{12,13},{6,7,8,9,10,11} . Your output should be
//        4,12,6,3,13,7,2,8,1,9,10,11
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ListIterator4 {
List<List<Iterator>> list = new ArrayList<>();

    public boolean hasNext(List<List<Integer>> list, int index, int listIndex){
        if(index < list.size())
        {
            if(listIndex < list.get(index).size())
                return true ;
            else
                return false;
        }else {
            return false;
        }

    }


    public int Next(List<List<Integer>> list, int index, int listIndex){
        return list.get(index).get(listIndex);
    }

    public static void main(String []args) {
        List<List<Integer>> input = new ArrayList<List<Integer>>();
        input.add(Arrays.asList(4,3,2,1));
        input.add(Arrays.asList(12,13));
        input.add(Arrays.asList(6,7,8,9,11));
        input.add(Arrays.asList());

        ListIterator4 helloworld = new ListIterator4();

        int maxIndex = 0;
        // Time Complexity -- O(number_of_list * number of elements in list)
        for(int index = 0 ; index < input.size(); index++){
            maxIndex = Math.max(maxIndex,  input.get(index).size());
        }

        for(int listIndex = 0; listIndex < maxIndex; listIndex++){
            for(int index = 0 ; index < input.size(); index++){
                if(helloworld.hasNext(input, index, listIndex)){
                    System.out.print((helloworld.Next(input, index, listIndex)) + "  ");
                }
            }
        }

    }

}