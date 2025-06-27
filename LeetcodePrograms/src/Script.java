package LeetcodePrograms.src;

import java.io.*;
import java.util.Scanner;

public class Script {
    public static void FirstCharacterLastNameAtEmail(){
        try {

            // FileReader reads text files in the default encoding.
            Scanner input = new Scanner(new File("/Users/rishi.khurana/Documents/WorkingDirectory/DataStructuresAndAlgorithms/LeetcodePrograms/src/input.txt"));
            // Always wrap FileReader in BufferedReader.
            // BufferedReader bufferedReader = new BufferedReader(input);
            String line, lineArr[], firstName, lastName, company, email;
            char firstCharacter, secondCharacter;
            company = "snap";
            String csvFile = "output.csv";
            while(input.hasNextLine()) {
                line = input.nextLine();
                line = line.toLowerCase().trim();
                lineArr = line.split("\\s+");
                firstName = lineArr[0];
                System.out.println("first name: " + firstName);
                firstCharacter = firstName.charAt(0);
                secondCharacter = firstName.charAt(1);
                System.out.println("first character: " + firstCharacter);
                System.out.println("second character: " + secondCharacter);
                lastName = lineArr[1];
                // company = lineArr[2];
                email = firstCharacter + lastName + "@" + company + ".com";
                FileWriter fw = new FileWriter(csvFile,true);
                fw.append(firstName);
                fw.append(",");
                fw.append(lastName);
                fw.append(",");
                fw.append(email);fw.append(",");
                fw.append(company);fw.append("\n");
                fw.flush();
            }
            // Always close files.

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String []args){
        FirstCharacterLastNameAtEmail();
    }
}
