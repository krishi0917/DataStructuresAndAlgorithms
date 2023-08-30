package LeetcodePrograms;
import java.util.*;
public class Aloha4CodingSolution {




    public class Main {
        static class NaryTree{
            NaryTree parent;
            ArrayList<NaryTree> children;
            String path;
            boolean isDir;
            public NaryTree(String path , boolean isDir , NaryTree parent){
                children = new ArrayList<NaryTree>();
                this.path = path;
                this.isDir = isDir;
                this.parent = parent;
            }

        }

        public static void listFiles(boolean isRecursive,NaryTree currentNode){
            System.out.println(currentNode.path);
            for(int i =0 ; i < currentNode.children.size() ; i++){
                NaryTree temp = currentNode.children.get(i);
                if(temp.isDir){
                    if(isRecursive){
                        listFiles(isRecursive, temp);
                    }else{
                        System.out.println(temp.path);
                    }
                }else{
                    System.out.println(temp.path);
                }
            }
        }
        public static void mkdirectory(String name,NaryTree currentNode){
            NaryTree newDir = new NaryTree(currentNode.path+"/"+name , true,currentNode  );
            for(int i = 0 ; i < currentNode.children.size() ; i++){
                if(currentNode.children.get(i).path.equals(newDir.path)){
                    System.out.println("Directory already exists");
                    return;
                }
            }
            currentNode.children.add(newDir);
        }

        public static void touchFile(String name,NaryTree currentNode){
            NaryTree newDir = new NaryTree(name , false,currentNode  );
            currentNode.children.add(newDir);
        }

        public static void changeDirectory(String dirName ,NaryTree currentNode){
            for(int i = 0 ; i < currentNode.children.size() ; i++){
                if(currentNode.children.get(i).path.equals(currentNode.path + "/"+dirName)){
                    if(currentNode.children.get(i).isDir){
                        currentNode = currentNode.children.get(i);
                        return;
                    }
                }
            }
            System.out.println("Directory not found");
        }

        public static void processCommand(String command , NaryTree root, NaryTree currentNode){
            String cmdArr[] = command.split(" ");
            String action = cmdArr[0];
            if(action.equals("ls")){
                if(cmdArr.length > 1 ){
                    if(cmdArr[1].equals("-r")){
                        listFiles(true,currentNode);
                    }else{
                        System.out.println("Invalid Command");
                    }
                }else{
                    listFiles(false,currentNode);
                }
            }else if(action.equals("pwd")){
                if(cmdArr.length == 1){
                    System.out.println(currentNode.path);
                }else{
                    System.out.println("Invalid Command");
                }

            }else if(action.equals("mkdir")){
                if(cmdArr.length > 2){
                    System.out.println("Invalid Command");

                }else{
                    String name = cmdArr[1];
                    if(name.length()<=100){
                        mkdirectory(name,currentNode);
                    }else{
                        System.out.println("Invalid File or Folder Name");
                    }
                }

            }else if(action.equals("touch")){
                if(cmdArr.length > 2){
                    System.out.println("Invalid Command");
                }else if(cmdArr.length > 1 ){
                    String filename = cmdArr[1];
                    if(filename.length()<=100){
                        touchFile(filename,currentNode);
                    }else{
                        System.out.println("Invalid File or Folder Name");
                    }
                }
            }else if(action.contains("cd")){
                if(cmdArr.length > 2 || cmdArr.length == 1){
                    System.out.println("Invalid Command");
                }else if(cmdArr.length > 1 ){
                    String params = cmdArr[1];
                    if(!params.equals("..")){
                        String dirName = cmdArr[1];
                        changeDirectory(dirName,currentNode );
                    }else{
                        if(currentNode.parent == null){
                            currentNode = root;
                        }else{
                            currentNode = currentNode.parent;
                        }
                    }
                }
            }else{
                System.out.println("Unrecognized command");
            }
        }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            NaryTree root = new NaryTree("/root",true,null);
            NaryTree currentNode = root;

            while(sc.hasNext()){
                String command = sc.nextLine();
                if(command.equals("quit")){
                    return;
                }else if (command.contains("dummy")){
                    System.out.println("Invalid Command");
                }
                else{
                    processCommand(command , root , currentNode);
                }
            }
        }
    }

}
