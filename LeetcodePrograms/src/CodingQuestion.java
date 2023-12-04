package LeetcodePrograms.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * @author Rishi Khurana
 * 0
 * [4,3]
 * A B C D E
 * F G H I J
 * K L M N O
 * T U V X Y
 * Z A B C D
 *
 * 1
 * [0,1]
 * A B C D
 * E F G H
 */
public class CodingQuestion {

    public static void fileQuestion() throws FileNotFoundException {

        File text = new File("/Users/riskhurana/Documents/RishiWorkspace"
                + "/PractiseQuestion/src/ReadAndWriteFile/test.txt");

        Scanner scnr = new Scanner(text);

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        //read the input record
        List<List<String>> list;

        list = new ArrayList<>();

        int index = 0;

        int elementPlace = 0;
        String elementFound="";
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();

            if(index == 0){
                 elementPlace = Integer.parseInt(line);

            }else if(index == 1){
                 elementFound = line;

            }else if(line.length()!=0){
                List<String> innerList = new ArrayList<>();
                String lineArr[] = line.split(" ");
                for (int i = 0; i < lineArr.length; i++) {
                    innerList.add(lineArr[i]);

                }
                list.add(innerList);

            }
            if(!scnr.hasNextLine() || line.length() ==0 ) {

                //innitialize everything and calculate everything

                String requiredEndString = elementFound.substring(elementFound.indexOf(",") + 1,
                        elementFound.indexOf("]"));
                String requiredStartString = elementFound.substring(elementFound.indexOf("[") + 1,
                        elementFound.indexOf(","));

                List<String> currentList = list.get((list.size() -1) - Integer.parseInt(requiredEndString));

                String finalResult = currentList.get(Integer.parseInt(requiredStartString));

                list = new ArrayList<>(); // reinnitializing

                treeMap.put(elementPlace , finalResult);

                index = -1;
            }

            index++;

        }

        for(Integer i : treeMap.keySet()){
             System.out.print(treeMap.get(i) + "  ");
        }


    }
    public static void main(String[]args) throws FileNotFoundException {
        fileQuestion();
    }
}
