package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * @author Rishi Khurana
 */
public class ListIterator6 {
    List<Iterator> iteratorsList ;
    int outerIdx;
    int totalListcount ;
    int currentListCount;
    public ListIterator6(List<Iterator> list){
        iteratorsList = list;
        outerIdx = 0;
        currentListCount = 0;
        totalListcount = list.size();
    }

    public boolean hasNext(){
        if(outerIdx == iteratorsList.size()){
            outerIdx=0;
        }
        if(iteratorsList.get(outerIdx).hasNext()){
            return true;
        }
        return false;
    }

    public void next(){
        if(hasNext()){
            System.out.println(iteratorsList.get(outerIdx).next());
            if(hasNext()){
                currentListCount=0;
            }
            outerIdx++;
        }else {

            if(currentListCount == totalListcount) {
                System.out.println("all elements are visited. No more elements left");
            }else{
                currentListCount++;
                outerIdx++;
                next();
            }
        }
    }

    public static void main(String []args){
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(4,5,6,10);
        List<Integer> list3 = Arrays.asList();
        List<Integer> list4 = Arrays.asList(7,8,9,11,12);
        Iterator i1 = list1.iterator();
        Iterator i2 = list2.iterator();
        Iterator i3 = list3.iterator();
        Iterator i4 = list4.iterator();
        List<Iterator>  iteratorList= new ArrayList<>();
        iteratorList.add(i1);
        iteratorList.add(i2);
        iteratorList.add(i3);
        iteratorList.add(i4);
        ListIterator6 listIterator6 = new ListIterator6(iteratorList);
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();
        listIterator6.next();

    }

}
