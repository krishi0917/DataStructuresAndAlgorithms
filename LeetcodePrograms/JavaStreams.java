package LeetcodePrograms;
import java.util.Arrays;
import java.util.*;
import java.util.stream.*;

/**
 * @author Rishi Khurana
 */
public class JavaStreams {

    public static void main(String []args){
//        IntStream.
//                range(1,10).forEach(System.out::print);
//        System.out.println();
//
//        IntStream.range(1,10).skip(5).forEach(x->System.out.println(x)); // skipped the first five elements
//
//        System.out.println(IntStream.range(1,10).sum());
        String []names= {"abc" , "def" , "ghi" , "gab","gpl", "jkl"};
//        Stream.of(names)
//                .sorted(Collections.reverseOrder())
//                .findFirst()
//                .ifPresent(System.out::println);

//        Arrays.stream(names)
//                .filter(x -> x.startsWith("g"))
//                .sorted()
//                .forEach(System.out::println);

//        System.out.println(Arrays.stream(new int[]  {2,4,6,8})
//            .map(x -> x*x)
//            .sum());
//
//        Arrays.stream(new int[]  {2,4,6,8})
//                .map(x -> x*x)
//                .average()
//                .ifPresent(System.out::println);

        List<String> people = Arrays.asList("abc" , "def" , "ghi" , "gab","gpl", "jkl");

        people
            .stream()
            .map(String :: toLowerCase)
            .filter(x -> x.startsWith("g"))
            .sorted()
            .findFirst()
            .ifPresent(System.out::println);

        people
            .stream()
            .map(String :: toLowerCase)
            .filter( x -> x.startsWith("g"))
            .forEach(System.out::print);



    }
}
