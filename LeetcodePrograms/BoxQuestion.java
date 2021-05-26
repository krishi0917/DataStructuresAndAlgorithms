package LeetcodePrograms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BoxQuestion {
    static class HashMapX {

        private HashMap<String,String> dictionary;
        private Stack<Transactions> action;


        public HashMapX(){
            dictionary = new HashMap<>();
            action = new Stack<>();
        }

        public void parseCommand(String command){
            String[] parsedCommand =  command.split(" ");
            if (parsedCommand[0].compareTo("SET")==0 && parsedCommand.length==3){
                if (action.isEmpty()){
                    if (parsedCommand[2]==null){
                        System.out.println("Cannot set null values");
                        return;
                    }
                    this.set(parsedCommand[1], parsedCommand[2]);
                }else {
                    action.peek().addSetCommand(parsedCommand);
                }
                return;
            }
            if (parsedCommand[0].compareTo("GET")==0){
                System.out.println(dictionary.get(parsedCommand[1]));
                return;
            }
            if (parsedCommand[0].compareTo("DELETE")==0){
                if (action.isEmpty()){
                    this.unset(parsedCommand[1]);
                }else {
                    action.peek().addUnsetCommand(parsedCommand);
                }
                return;
            }
            if (parsedCommand[0].compareTo("BEGIN")==0){
                this.startXaction();
                return;
            }
            if (parsedCommand[0].compareTo("ROLLBACK")==0){
                this.rollback();
                return;
            }
            if (parsedCommand[0].compareTo("COMMIT")==0){
                this.commit();
                return;
            }
            if (parsedCommand[0].compareTo("COUNT")==0){
                System.out.println(this.getCount(parsedCommand[1]));
                return;
            }
            else {
                System.out.println("Invalid command");
            }
        }

        public String set(String key, String value){
            String old = null;
            if (dictionary.containsKey(key)){
                old = dictionary.get(key);
            }
            dictionary.put(key, value);
            return old;
        }

        public String unset(String key){
            String value = null;
            if (dictionary.containsKey(key)){
                value = dictionary.get(key);
                dictionary.remove(key);
            }
            return value;
        }

        public String get(String key){
            return dictionary.get(key);
        }

        public void startXaction(){
            action.push(new Transactions(this));
        }

        public void rollback(){
            if (action.isEmpty()){
                //System.out.println("NO TRANSACTIONS IN PROGRESS");
                return;
            }
            action.pop().rollback();
        }

        public void commit(){
            if (action.isEmpty()){
                //System.out.println("NO TRANSACTIONS IN PROGRESS");
                return;
            }
            action.clear();
        }

        public String getCount(String value) {
            int count = 0;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if (entry.getValue().equals(value)) {
                    count++;
                }
            }
            return String.valueOf(count);
        }

    }

    static class Transactions{
        private Stack<String> commands;
        private HashMapX database;

        public Transactions(HashMapX db){
            database = db;
            commands = new Stack<>();
        }

        public void addSetCommand(String[] command){
            if (command[2]==null){
                System.out.println("Cannot set null values");
                return;
            }
            String oldValue = database.set(command[1], command[2]);
            if (oldValue == null){
                commands.push(command[1]);
            }else {
                commands.push(command[1]+" "+oldValue);
            }

        }

        public void addUnsetCommand(String[] command){
            String oldValue = database.unset(command[1]);
            if (oldValue!=null){
                commands.push(command[1]+" "+oldValue);
            }
        }

        public void rollback(){
            while (!commands.isEmpty()){
                String command = commands.pop();
                String[] parsed = command.split(" ");
                if (parsed.length==1){
                    database.unset(parsed[0]);
                }else {
                    database.set(parsed[0],parsed[1]);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException{
        //Solution s = new Solution();
        HashMapX newDB =  new HashMapX();
        //     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //     boolean isExit = false;
        //     while (!isExit){
        //         String command = reader.readLine();
        //         if (command.compareTo("END")==0){
        //                 isExit = true;
        //             }else{
        //                 newDB.parseCommand(command);
        //             }
        //         }

        // newDB.parseCommand("SET A 10");
        // newDB.parseCommand("BEGIN");
        // newDB.parseCommand("GET A");

        newDB.parseCommand("SET A 60");
        newDB.parseCommand("BEGIN");
        newDB.parseCommand("DELETE A");
        newDB.parseCommand("GET A");
        newDB.parseCommand("ROLLBACK");
        newDB.parseCommand("GET A");

        newDB.parseCommand("COMMIT");
        newDB.parseCommand("GET A");


    }
}
