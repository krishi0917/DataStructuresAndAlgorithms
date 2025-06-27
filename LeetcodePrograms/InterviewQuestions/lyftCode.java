package LeetcodePrograms.InterviewQuestions;

import java.io.InputStream;
import java.util.*;
public class lyftCode {
    public Integer globalVersionNumber=0;
    HashMap<String,LinkedHashMap<Integer,Integer>> map = new HashMap<>();
    public lyftCode(){

    }
    public void PUT(String key,String value1){
        int value = Integer.valueOf(value1);
        globalVersionNumber++;
        //LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();

        if(map.containsKey(key)){
            map.get(key).put(globalVersionNumber,value);
        }
        else{
            LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(globalVersionNumber, value);
            map.put(key,linkedHashMap);
        }
        System.out.println("PUT"+ "(#"+globalVersionNumber + ") " + key + " = " + value);
    }

    public void GETByKey(String Key){
        if(map.containsKey(Key)){
            LinkedHashMap currentHashMap  = map.get(Key);
            String value=null;
            Set set = currentHashMap.entrySet();
            Iterator i = set.iterator();
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                 value = me.getValue().toString();
            }
            System.out.println("GET" + Key + " = " + value);
        } else{
            System.out.println("GET" + Key + " =  " + "<NULL>");
            }
    }

    public void GETByKeyAndVersion(String Key, String Version){
            int version = Integer.valueOf(Version);
            String value = null;
        if(map.containsKey(Key)){
            LinkedHashMap currentHashMap  = map.get(Key);

            Set set = currentHashMap.entrySet();
            Iterator i = set.iterator();
            while(i.hasNext()) {
                Map.Entry me = (Map.Entry)i.next();
                if(((int)me.getKey()==version)){ // trying to find the current version. If we get it then return
                    System.out.println("GET" + Key +  "(#"+globalVersionNumber + ") " + "=" +   me.getValue().toString() +")");
                    break;
                }
                value = me.getValue().toString(); //if we dont get the version then this will have the latest value of the version
            }
            System.out.println("GET " + Key + "(#"+globalVersionNumber + ") " + " = " + value);
        } else{
            System.out.println("GET " + Key + " =  " + "<NULL>"); // if value not found simply return null
        }

    }


    public static void main(String [] args)
    {   lyftCode code = new lyftCode();
        InputStream source = System.in;

        /*
        Or, to read from a file:
        InputStream source = new FileInputStream(filename);

        Or, to read from a network stream:
        InputStream source = socket.getInputStream();
        */

        Scanner in = new Scanner(source);
        while(in.hasNext()){
            String input = in.nextLine(); // Use in.nextLine() for line-by-line reading
            String str[] = input.split(" ");
            if(str[0].equalsIgnoreCase("PUT")) {
                code.PUT(str[1], str[2]);
            }
            if(str[0].equalsIgnoreCase("GET")){
                if(str.length ==2)
                    code.GETByKey(str[1]);
                else
                    code.GETByKeyAndVersion(str[1],str[2]);
            }
        }


    }
}
