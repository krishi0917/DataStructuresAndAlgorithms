package LeetcodePrograms;

import java.util.*;
// this question also we have to do
// https://leetcode.com/discuss/interview-experience/1379020/Google-phone-screen

// Given list of folders, print the path of a given folder from root. If there is no path to the root folder then return an empty string.
// Root level folders will have 0 as id. The structure of Folder is like this:
// class Folder {
//    int id;
//    List<int> subfolders;
//    String name;
// }
// Ex:
// list = [Folder(0, [7, 3], “abc”), Folder(0, [], “xyz”), Folder(3, [], “pqr”), Folder(8, [], “def”), Folder(7, [9], “ijk), Folder(9, [], “lmn”)]

// printPath(9) = “abc” -> “ijk” -> “lmn” printPath(8) = ""
// printpAth(7) abc -> ijk



public class UberFolderFileStructure {
    static class Folder {
        int id;
        List<Integer> subfolders;
        String name;
        public Folder(int i, List<Integer> sub , String n){
            id = i;
            subfolders = sub;
            name = n;
        }
    }
    Map<Integer,Integer> subFolderToFolderMap = new HashMap<>();
    Map<Folder,String> nameToIdMap = new HashMap<>();
    public void createFolder(List<Folder> folderList){
        for(Folder folder : folderList){
            nameToIdMap.put(folder, folder.name);
            List<Integer> subfolders = folder.subfolders;
            for(Integer s : subfolders){
                subFolderToFolderMap.put(s,folder.id);
            }
        }
        System.out.println(nameToIdMap);
        System.out.println(subFolderToFolderMap);
    }


    public void printPath(int id){
        String result = "";
        if(!subFolderToFolderMap.containsKey(id)){
            System.out.println("");
        }else{
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            while(!flag){
                sb.append(nameToIdMap.get(id) + "  ");
                if(subFolderToFolderMap.containsKey(subFolderToFolderMap.get(id))){
                    printPath(subFolderToFolderMap.get(id));
                }else{
                    flag = true;
                    result = sb.toString();
                }

            }
        }

        System.out.println(result);
    }
    public static void main(String[] args) {
        // Folder(0, [7, 3], “abc”), Folder(0, [], “xyz”), Folder(3, [], “pqr”), Folder(8, [], “def”), Folder(7, [9], “ijk), Folder(9, [], “lmn”)
        UberFolderFileStructure s = new UberFolderFileStructure();
        Folder f1 = new Folder(0,Arrays.asList(7,3),"abc");
        Folder f2 = new Folder(0,new ArrayList<>(),"xyz");
        Folder f3 = new Folder(3,new ArrayList<>(),"pqr");
        Folder f4 = new Folder(8,new ArrayList<>(),"def");
        Folder f5 = new Folder(7,Arrays.asList(9),"ijk");
        Folder f6 = new Folder(9,new ArrayList<>(),"lmn");

        s.createFolder(Arrays.asList(f1,f2,f3,f4,f5,f6));
        s.printPath(9);
    }
}





