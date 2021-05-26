package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */

import java.util.ArrayList;
import java.util.List;


/******************
 * Name Matching
 *
 *   At Checkr, one of the most important aspects of our work is accurately matching records
 * to candidates. One of the ways that we do this is by comparing the name on a given record
 * to a list of known aliases for the candidate. In this exercise, we will implement a
 * `nameMatch` method that accepts the list of known aliases as well as the name returned
 * on a record. It should return true if the name matches any of the aliases and false otherwise.
 *
 * The nameMatch method will be required to pass the following tests:
 *
 * 1. Exact match
 *
 *   knownAliases = ["Alphonse Gabriel Capone", "Al Capone"]
 *   nameMatch(knownAliases, "Alphonse Gabriel Capone") => true
 *   nameMatch(knownAliases, "Al Capone")               => true
 *   nameMatch(knownAliases, "Alphonse Francis Capone") => false
 *
 *
 * 2. Middle name missing (on alias)
 *
 *   knownAliases = ["Alphonse Capone"]
 *   nameMatch(knownAliases, "Alphonse Gabriel Capone") => true
 *   nameMatch(knownAliases, "Alphonse Francis Capone") => true
 *   nameMatch(knownAliases, "Alexander Capone")        => false
 *
 *
 * 3. Middle name missing (on record name)
 *
 *   knownAliases = ["Alphonse Gabriel Capone"]
 *   nameMatch(knownAliases, "Alphonse Capone")         => true
 *   nameMatch(knownAliases, "Alphonse Francis Capone") => false
 *   nameMatch(knownAliases, "Alexander Capone")        => false
 *
 *
 * 4. More middle name tests
 *    These serve as a sanity check of your implementation of cases 2 and 3
 *
 *   knownAliases = ["Alphonse Gabriel Capone", "Alphonse Francis Capone"]
 *   nameMatch(knownAliases, "Alphonse Gabriel Capone") => true
 *   nameMatch(knownAliases, "Alphonse Francis Capone") => true
 *   nameMatch(knownAliases, "Alphonse Edward Capone")  => false
 *
 *
 * 5. Middle initial matches middle name
 *
 *   knownAliases = ["Alphonse Gabriel Capone", "Alphonse F Capone"]
 *   nameMatch(knownAliases, "Alphonse G Capone")       => true
 *   nameMatch(knownAliases, "Alphonse Francis Capone") => true
 *   nameMatch(knownAliases, "Alphonse E Capone")       => false
 *   nameMatch(knownAliases, "Alphonse Edward Capone")  => false
 *   nameMatch(knownAliases, "Alphonse Gregory Capone") => false
 *
 *
 * Bonus: Transposition
 *
 * Transposition (swapping) of the first name and middle name is relatively common.
 * In order to accurately match the name returned from a record we should take this
 * into account.
 *
 * All of the test cases implemented previously also apply to the transposed name.
 *
 *
 * 6. First name and middle name can be transposed
 *
 *   "Gabriel Alphonse Capone" is a valid transposition of "Alphonse Gabriel Capone"
 *
 *   knownAliases = ["Alphonse Gabriel Capone"]
 *   nameMatch(knownAliases, "Gabriel Alphonse Capone") => true
 *   nameMatch(knownAliases, "Gabriel A Capone")        => true
 *   nameMatch(knownAliases, "Gabriel Capone")          => true
 *   nameMatch(knownAliases, "Gabriel Francis Capone")  => false
 *
 *
 * 7. Last name cannot be transposed
 *
 *   "Alphonse Capone Gabriel" is NOT a valid transposition of "Alphonse Gabriel Capone"
 *   "Capone Alphonse Gabriel" is NOT a valid transposition of "Alphonse Gabriel Capone"
 *
 *   knownAliases = ["Alphonse Gabriel Capone"]
 *   nameMatch(knownAliases, "Alphonse Capone Gabriel") => false
 *   nameMatch(knownAliases, "Capone Alphonse Gabriel") => false
 *   nameMatch(knownAliases, "Capone Gabriel")          => false
 */

public class checkRInterviewQuestion {

    private static List<Person> persons = new ArrayList<>();

    static class Person{
        String firstName;
        String middleName;
        String lastName;
        public Person(String fName , String lName){
            firstName = fName;
            middleName = null;
            lastName = lName;
        }
        public Person(String fName , String mName ,String lName){
            firstName = fName;
            middleName = mName;
            lastName = lName;

        }
    }

    public static boolean nameMatch(String[] knownAliases, String recordName) {
        for(String s : knownAliases){
            String []nameArr = s.split(" ");
            if(nameArr.length == 2){
                Person p = new Person(nameArr[0],nameArr[1]);
                persons.add(p);
            }else{
                Person p = new Person(nameArr[0],nameArr[1] , nameArr[2]);
                persons.add(p);
            }
        }

        String nameArr [] = recordName.split(" ");
        boolean validMatch = false;
        for(Person person : persons){
            if(person.firstName.equals(nameArr[0])){ //first name matched
                if(person.lastName.equals(nameArr[nameArr.length-1])){ // last name matched
                    if(null == person.middleName ){ // no middle name
                        validMatch = true;
                    }else if(nameArr.length == 2){
                        validMatch = true;
                    } else if(nameArr.length == 3) {
                        if(nameArr[1].equals(person.middleName) || nameArr[1].substring(0,1).equals(person.middleName)
                                || nameArr[1].equals(person.middleName.substring(0,1))
                        )
                        //middle name exist
                        validMatch = true ; // exact match found with middle name

                    }
                }
            }
        }
        return validMatch;
    }

    public static void assertEqual(boolean expected, boolean result, String errorMessage) {
        if (result != expected) {
            System.out.println(errorMessage);
            System.out.println("expected: " + expected);
            System.out.println("actual: " + result);
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        String[] knownAliases ; // = new String[]{ "Al Capone" , "Alphonse Gabriel Capone"};
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Gabriel Capone"), "error 1.1");
//        assertEqual(true,  nameMatch(knownAliases, "Al Capone"),               "error 1.2");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Francis Capone"), "error 1.3");
//
//        knownAliases = new String[]{"Alphonse Capone"};
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Gabriel Capone"), "error 2.1");
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Francis Capone"), "error 2.2");
//        assertEqual(false, nameMatch(knownAliases, "Alexander Capone"),        "error 2.3");

//        knownAliases = new String[]{"Alphonse Gabriel Capone"};
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Capone"),         "error 3.1");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Francis Capone"), "error 3.2");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Edward Capone"),  "error 3.3");

//        knownAliases = new String[]{"Alphonse Gabriel Capone", "Alphonse Francis Capone"};
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Gabriel Capone"), "error 4.1");
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Francis Capone"), "error 4.2");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Edward Capone"),  "error 4.3");
//
//        knownAliases = new String[]{"Alphonse Gabriel Capone", "Alphonse F Capone"};
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse G Capone"),       "error 5.1");
//        assertEqual(true,  nameMatch(knownAliases, "Alphonse Francis Capone"), "error 5.2");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse E Capone"),       "error 5.3");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Edward Capone"),  "error 5.4");
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Gregory Capone"), "error 5.5");

        knownAliases = new String[]{"Alphonse Gabriel Capone"};
        assertEqual(true,  nameMatch(knownAliases, "Gabriel Alphonse Capone"), "error 6.1");
        assertEqual(true,  nameMatch(knownAliases, "Gabriel A Capone"),        "error 6.2");
        assertEqual(true,  nameMatch(knownAliases, "Gabriel Capone"),          "error 6.3");
        assertEqual(false, nameMatch(knownAliases, "Gabriel Francis Capone"),  "error 6.4");
//
//        knownAliases = new String[]{"Alphonse Gabriel Capone"};
//        assertEqual(false, nameMatch(knownAliases, "Alphonse Capone Gabriel"), "error 7.1");
//        assertEqual(false, nameMatch(knownAliases, "Capone Alphonse Gabriel"), "error 7.2");
//        assertEqual(false, nameMatch(knownAliases, "Capone Gabriel"),          "error 7.3");

        System.out.println("Test run finished");
    }
}


