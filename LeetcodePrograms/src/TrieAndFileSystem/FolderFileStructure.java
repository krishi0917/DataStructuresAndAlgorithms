package LeetcodePrograms.src.TrieAndFileSystem;

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

// printPath(9) = “abc” -> “ijk” -> “lmn”
// printPath(8) = ""
// printpAth(7) abc -> ijk

public class FolderFileStructure {
    static class Folder {
        int id;
        List<Integer> subfolderIds;
        String name;
        public Folder(int i, List<Integer> sub , String n){
            id = i;
            subfolderIds = sub;
            name = n;
        }
    }
    Map<Integer,Integer> childToFatherRelationshipMap;
    Map<Integer,String> parentIdToNameMap;
    Map<Integer,String> firstChildToFatherRelationshipMap; //since for id 0, there are two routes. hence the special case is separately taken

    public FolderFileStructure(){
        childToFatherRelationshipMap = new HashMap<>();
        parentIdToNameMap = new HashMap<>();
        firstChildToFatherRelationshipMap = new HashMap<>();
    }
    public void createFolder(List<Folder> folderList) {
        for(Folder folder : folderList){
            int id = folder.id;
            List<Integer> subfolderIds = folder.subfolderIds;
            String name = folder.name;

            for(Integer subfolderId : subfolderIds){
                if(folder.id == 0){ // when id is 0, means we reached root. hence store the sufolderid with name and not with id.
                    firstChildToFatherRelationshipMap.put( subfolderId, folder.name );
                }else{
                    childToFatherRelationshipMap.put(subfolderId, id);
                }
            }
            parentIdToNameMap.put(id,name);
        }
    }

    public List<String> printPath(int id){
        List<String> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        boolean isFound = false;
        int firstChild = -1;
        while(!isFound && !queue.isEmpty()){
            int element = queue.poll();
            String name = parentIdToNameMap.get(element);
            result.add(name);
            if(childToFatherRelationshipMap.containsKey(element)){
                int fatherId = childToFatherRelationshipMap.get(element);
                if(fatherId == 0){
                    isFound = true;
                }
                queue.offer(fatherId);
            }
            if(firstChildToFatherRelationshipMap.containsKey(element)){
                result.add(firstChildToFatherRelationshipMap.get(element));
                isFound = true;
            }
        }
        if(isFound)
            return result;
        else
            return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Folder(0, [7, 3], “abc”), Folder(0, [], “xyz”), Folder(3, [], “pqr”), Folder(8, [], “def”), Folder(7, [9], “ijk), Folder(9, [], “lmn”)
        FolderFileStructure s = new FolderFileStructure();
        Folder f1 = new Folder(0, Arrays.asList(7, 3), "abc");
        Folder f2 = new Folder(0, new ArrayList<>(), "xyz");
        Folder f3 = new Folder(3, new ArrayList<>(), "pqr");
        Folder f4 = new Folder(8, new ArrayList<>(), "def");
        Folder f5 = new Folder(7, Arrays.asList(9), "ijk");
        Folder f6 = new Folder(9, new ArrayList<>(), "lmn");
// printPath(9) = “abc” -> “ijk” -> “lmn”
// printPath(8) = ""
// printpAth(7) abc -> ijk

        s.createFolder(Arrays.asList(f1, f2, f3, f4, f5, f6));
        System.out.println(s.printPath(8));
    }
}





