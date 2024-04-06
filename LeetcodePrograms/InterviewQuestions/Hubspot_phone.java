package LeetcodePrograms.InterviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hubspot_phone { public List<Integer> MergeTwoList(List<Integer> list1, List<Integer> list2, int max_result_length){
    if(list1 == null){
        list1 = new ArrayList<>();
    }
    if(list2== null){
        list2 = new ArrayList<>();
    }
    if(max_result_length <= 0 ){
        return new ArrayList<>();
    }
    int i = 0 , j = 0;
    List<Integer> mergedList = new ArrayList<>(max_result_length);
    // logic to have a better complexity than O(n) is to have the arraylist size predefined, this way when new elements are added, arraylist doesnt have to dynamically increase the size and use cpu memory
    while(i < list1.size() && j < list2.size() && mergedList.size() < max_result_length){
        if(list1.get(i) < list2.get(j)){
            mergedList.add(list1.get(i));
            i++;
        }else{
            mergedList.add(list2.get(j));
            j++;
        }
    }

    while(i < list1.size() && mergedList.size() < max_result_length){
        mergedList.add(list1.get(i));
        i++;
    }

    while(j < list2.size() && mergedList.size() < max_result_length){
        mergedList.add(list2.get(j));
        j++;
    }
    return mergedList;
}
    public static void main(String[] args) {
        Hubspot_phone m = new Hubspot_phone();

        List<Integer> list1 = Arrays.asList(1,3,5);
        List<Integer> list2 = Arrays.asList(2,6,8,9);
        int max_result_size = 4;
        System.out.println(m.MergeTwoList(list1,list2,max_result_size));
        System.out.println(m.MergeTwoList(list1,list2, 10));
        System.out.println(m.MergeTwoList(list1,null, 4));
        System.out.println(m.MergeTwoList(list1,null, 2));
        System.out.println(m.MergeTwoList(null,null, 4));
        System.out.println(m.MergeTwoList(list1,list2, 0));
        System.out.println(m.MergeTwoList(list1,list2, -1));
        System.out.println(m.MergeTwoList(new ArrayList<>(),list2, 2));
    }
}