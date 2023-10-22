package LeetcodePrograms.src.TrieAndFileSystem;

import java.util.HashMap;
import java.util.Map;

// 1166. Design File System
// You are asked to design a file system that allows you to create new paths and associate them with different values.
// The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
// For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
//
// Implement the FileSystem class:
//    bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
//        Returns false if the path already exists or its parent path doesn't exist.
//    int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.

/*
Example 1:
Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.

 */
public class DesignFileSystem {
    class TrieNode{
        Map<String, TrieNode> nodes;
        int content;
        boolean isAlreadyPath;

        public TrieNode(){
            nodes = new HashMap<>();
            content = -1;
            isAlreadyPath = false;
        }
    }

    TrieNode ROOT;

    public DesignFileSystem() {
        ROOT = new TrieNode();
    }

    public boolean createPath(String path, int value) {
        return addToTrie(path, value);
    }

    public int get(String path) {
        return getContent(path);
    }

    boolean addToTrie(String path, int value){
        TrieNode currRoot = ROOT;
        String[] dirs = path.split("/");
        int length = dirs.length;
        for(int i = 0; i < length; i++){
            String dir = dirs[i];
            if("".equals(dir)) continue;
            if(currRoot.nodes.containsKey(dir)){
                currRoot = currRoot.nodes.get(dir);
            } else if(i == length - 1){
                TrieNode node = new TrieNode();
                currRoot.nodes.put(dir, node);
                currRoot = node;
            } else {
                return false;
            }
        }
        if(currRoot.isAlreadyPath) return false;
        currRoot.content = value;
        return currRoot.isAlreadyPath = true;
    }

    int getContent(String path){
        TrieNode currRoot = ROOT;
        String[] dirs = path.split("/");
        int length = dirs.length;
        for(String dir : dirs){
            if("".equals(dir)) continue;
            if(currRoot.nodes.containsKey(dir)){
                currRoot = currRoot.nodes.get(dir);
            } else return -1;
        }
        return currRoot.content;
    }

    public static void main(String []args){
        DesignFileSystem designFileSystem = new DesignFileSystem();
        designFileSystem.createPath("/root",2);
        designFileSystem.createPath("/root/path1",3);
        System.out.println(designFileSystem.get("root/path1"));
    }
}
