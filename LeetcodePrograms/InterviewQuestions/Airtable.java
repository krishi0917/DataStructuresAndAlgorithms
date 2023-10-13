package LeetcodePrograms.InterviewQuestions;
import java.util.*;
/*

https://app.coderpad.io/MG2AXQKD/playback

Imagine that we are writing a backup application used to backup files from your laptop to a remote server.
To save on network bandwidth, we want to identify duplicate files (i.e., files with the same contents).
This way, we only need to upload duplicate files once.

Write a function that identifies sets of files with identical contents.

    find_dupes(root_path) → sets/lists of 2 or more file paths that have identical contents

    find_dupes("/home/airtable") → [
        [".bashrc", "Backups/2017_bashrc"],
        ["Photos/Vacation/DSC1234.JPG", "profile.jpeg", ".trash/lej2dp28/87msnlgyr"],
    ]

For scale, imagine running this on a computer with at most 2 TB of data and at most 1 million files.

For traversing the filesystem, use these library functions:
- list_folder(path) → list of names of immediate file and folder children
- is_folder(path) → boolean

*
*/
public class Airtable {
        Map<String,Set<String>> resultMap = new HashMap<>();
        public List<List<String>> findDuplicates(String path){
            findDuplicatesUtil(path,"");
            List<List<String>> result = new ArrayList<>();
            for(int i = 0 ; i < resultMap.size();i++){
                Set<String> tempResultSet = resultMap.get(i);
                List<String> tempList =  new ArrayList<>(tempResultSet);
                result.add(tempList);
            }
            return result;
        }
        // path A prefix = A
        private void findDuplicatesUtil(String path,String prefix){

            if(!is_folder(prefix + path)){
                String hashPathFile = path; // calculates hash
                if(resultMap.containsKey(hashPathFile)){
                    Set<String> existingSet = resultMap.get(hashPathFile);
                    existingSet.add(prefix + "/" + path);// /D/E/f
                    resultMap.put(hashPathFile,existingSet );
                }else{
                    Set<String> newSet = new HashSet<>();
                    newSet.add(prefix + "/" + path);
                    resultMap.put(hashPathFile, newSet);
                }
            }else{ //its a folder
                List<String> keys = list_folder(path); // A , D
                // E
                for(String key : keys ){ //
                    if(!is_folder(key) ){
                        findDuplicatesUtil(key,prefix);

                    }else{
                        findDuplicatesUtil(key , prefix + "/");    // D , /D
                    }


                }
            }

        }

    private List<String> list_folder(String path) {
            return new ArrayList<>();
    }

    private boolean is_folder(String s) {
            return false;
    }

    public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add("Hello, World!");
            strings.add("Welcome to CoderPad.");

            for (String string : strings) {
                System.out.println(string);
            }
        }
}
