package LeetcodePrograms.src.TrieAndFileSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
588. Design In-Memory File System
Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:
FileSystem()
    Initializes the object of the system.
List<String> ls(String path)  // The answer should in lexicographic order.
    If path is a file path,
        returns a list that only contains this file's name.
    If path is a directory path,
        returns the list of file and directory names in this directory.

void mkdir(String path)
    Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
    If filePath does not exist,
        creates that file containing given content.
    If filePath already exists,
        appends the given content to original content.
String readContentFromFile(String filePath)
    Returns the content in the file at filePath.

Example 1:
Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 */
public class DesignInMemoryFileSystem {
    private class TrieNode {
        private TreeMap<String, TrieNode> children;
        private String fileName;
        private String content;

        public TrieNode(String fileName, String content) {
            children = new TreeMap<>();
            this.fileName = fileName;
            this.content = content;
        }

        public boolean isFile(TrieNode curNode){
            if(curNode.content.length() == 0){
                return false;
            }
            return true;
        }

        public String addContent(TrieNode curNode, String newC){
            return curNode.content + newC;
        }

    }

    TrieNode root;

    public DesignInMemoryFileSystem() {
        root = new TrieNode("", "");
    }

    public List<String> ls(String path) {
        List<String> lst = new ArrayList<>();
        TrieNode cur = getNode(path);
        if(cur.isFile(cur)){
            lst.add(cur.fileName);
        }else{
            lst.addAll(cur.children.keySet());
        }
        return lst;
    }

    public void mkdir(String path) {
        getNode(path);
    }

    public void addContentToFile(String filePath, String content) {
        TrieNode cur = getNode(filePath);
        String newString = cur.addContent(cur, content);
        cur.content = newString;
    }

    public String readContentFromFile(String filePath) {
        return getNode(filePath).content;
    }

    private TrieNode getNode(String path){
        TrieNode cur = root;
        String[] strPath = path.split("/");
        for(String str: strPath){
            if(str == ""){
                continue;
            }
            if(!cur.children.containsKey(str)){
                cur.children.put(str, new TrieNode(str, ""));
            }
            cur = cur.children.get(str);
            if(cur.isFile(cur)){
                break;
            }
        }
        return cur;
    }

    public static void main(String []args){
        DesignInMemoryFileSystem fileSystem = new DesignInMemoryFileSystem();
        fileSystem.ls("/");                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        fileSystem.ls("/");                         // return ["a"]
        fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
    }
}
