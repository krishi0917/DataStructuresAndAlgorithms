package LeetcodePrograms;
// working solution
// iterating first element from each list before moving it to the second element of each list again
// Collection of iterators , given a collection of iterator write hasNext and next method
// Example : {{4,3,2,1},{12,13},{6,7,8,9,10,11} . Your output should be
// 4,12,6,3,13,7,2,8,1,9,10,11
import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;


public class ListIterator5 {

    List<List<Integer>> input ;
    int maxIndex ;
    public ListIterator5(List<List<Integer>> list){
        input = list;
        int maxInd = Integer.MIN_VALUE;   // maximum index is required for returning false from nextElement
        // because thats the way you will know that you have reached the end. Else it wont be possible for you
        // to know about that
        for(int index = 0 ; index < input.size(); index++){
            maxInd = Math.max(maxInd,  input.get(index).size());
        }
        maxIndex = maxInd;
    }

    int indexWithinList=0, indexforList =0;

    public  boolean hasNext2(){
        while(indexforList < input.size()){
            if(indexWithinList == maxIndex )
                return false;
            if(indexWithinList <= input.get(indexforList).size()-1){
                return true;
            } else if(indexWithinList > input.get(indexforList).size() - 1){
                if(indexforList != input.size() - 1)  // if during the first iteration, you have reached till the end
                    // but still u dint get the number. time to move the list but if you have reached at the end of
                    // the current list, increase the index within the list and move the index for list to 0
                    indexforList++;
                else {
                    indexforList = 0;
                    indexWithinList++;
                }
            }
        }


        return false; //this will never be called
    }
    public int next2(){
        if(hasNext2()){
            int number = input.get(indexforList).get(indexWithinList);
            if(indexforList == input.size()-1)
                indexforList=0;
            else {
                indexforList++;
            }
            return number;
        }
        else return -1;
    }
    public static void main(String[]args){
        ListIterator5 listIterator = new ListIterator5((ListIterator5.getInput()));
        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

        System.out.print(listIterator.hasNext2() + " ");
        System.out.print(listIterator.next2() + " ");

    }
    public static List<List<Integer>> getInput() {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(asList(1,5)));
        list.add(new ArrayList<>(asList(2,6,9)));
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>(asList(3,7,10,12,13)));
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>(asList(4,8 ,11)));
        list.add(new ArrayList<>());
        return list;
    }
}
