package LeetcodePrograms.src;

import java.util.*;


/**
 * @author Rishi Khurana
 */
public class Salesforce {
    static Map<String,String> map = new HashMap<>();

    static String getMorseCodeFor(String str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < str.length() ; i++){
            char ch = str.charAt(i);
            sb.append(map.get(Character.toString(ch)));
        }
        return sb.toString();
    }
    static void doIt(String[] input) {
        int i=0;
        Map<String,List<String>> words = new HashMap<>();
        for( i = 0 ; i < input.length ; i++){
            if(!input[i].equals("*")){
                String[]temp = input[i].split(" ");
                map.put(temp[0],temp[temp.length-1].trim());
            }else{
                break;
            }
        }
        i++;
        for(;i<input.length ; i++){
            if(!input[i].equals("*")){
                String code = getMorseCodeFor(input[i]);
                if(words.get(code)==null){
                    List<String> list = new ArrayList<>();
                    list.add(input[i]);
                    words.put(code,list);
                }else{
                    List<String> list = words.get(code);
                    if(list.get(0).length()>input[i].length()){
                        list.add(0,input[i]);
                    }else{
                        list.add(input[i]);
                    }
                    words.put(code,list);
                }
            }else{
                break;
            }
        }

        i++;
        boolean flag = false;
        for(;i<input.length ; i++){
            if(!input[i].equals("*")){
                String []str = input[i].split(" ");
                for(int j = 0 ; j < str.length ; j++){
                    if(words.containsKey(str[j])){
                        if(words.get(str[j]).size()>1){
                            System.out.println(words.get(str[j]).get(0)+"!");
                        }else{
                            System.out.println(words.get(str[j]).get(0));
                        }
                    }else{
                        if(str[j].length()>1){
                            List<String> l1 = new ArrayList<>();
                            for(String key : words.keySet()){
                                if(key.contains(str[j])){
                                    if(l1.size() > 0 && l1.get(0).length() < words.get(key).get(0).length()){
                                        l1.add(0, words.get(key).get(0));

                                    }else{
                                        l1.add(words.get(key).get(0));

                                    }
                                }
                            }
                            if(l1.size() > 0) {
                                System.out.println(l1.get(0) + "?");
                                break;
                            }
                            for(int k = 1 ; k < str[j].length() ; k++){

                                String subStr = str[j].substring(0,k);
                                if(words.containsKey(subStr)){
                                    System.out.println(words.get(subStr).get(0)+"?");
                                    break;
                                }

                            }
                            flag = true;
                            //System.out.println("No matching word found");
                        }
                    }
                }
            }else{

                break;
            }
        }

        if(flag)
            System.out.println("No matching word found");

    }

    public static void main(String[]args){
        String input [] = {
                "A .-","B -...","C       -.-.","*","AB","AC","*",".--..."};

        String[] input1 = {"A       .-","B       -...","C       -.-.","D       -..","E       .","F       ..-.","G     "
                + "  --.","H       ....","I       ..","J       .---","K       -.-","L       .-..","M       --","N    "
                + "   -.","O       ---","P       .--.","Q       --.-","R       .-.","S       ...","T       -","U     "
                + "  ..-","V       ...-","W       .--","X       -..-","Y       -.--","Z       --..","0       ------",
                "1       .-----","2       ..---","3       ...--","4       ....-","5       .....","6       -....","7       --...","8       ---..","9       ----.","*","AN","EARTHQUAKE","EAT","GOD","HATH","IM","READY","TO","WHAT","WROTH","*",".--.-.----.."};
                //,".--.....--   .....--....","--.----..   .--.-.----..",".--.....--   .--.","..-.-.-....--.-..-.--.-"
                //+ ".","..--   .-...--..-.--","----        ..--","*" };
        String[] input2 = {"A       .-","B       -...","C       -.-.","D       -..","E       .","F       ..-.","G    "
                + "   --.","H       ....","I       ..","J       .---","K       -.-","L       .-..","M       --","N   "
                + "    -.","O       ---","P       .--.","Q       --.-","R       .-.","S       ...","T       -","U       ..-","V       ...-","W       .--","X       -..-","Y       -.--","Z       --..","0       ------","1       .-----","2       ..---","3       ...--","4       ....-","5       .....","6       -....","7       --...","8       ---..","9       ----.","*","AN","EARTHQUAKE","EAT","GOD","HATH","IM","READY","TO","WHAT","WROTH","*",".--.....--   .....--....","--.----..   .--.-.----..",".--.....--   .--.","..-.-.-....--.-..-.--.-.","..--   .-...--..-.--","----        ..--","*" };
        doIt(input);
    }
}
