package LeetcodePrograms.src;

import java.util.*;
// might not be working
public class SalesforceAloha4TempSolution {

        public static class Cd implements Command{
            public boolean execute(Directory directory, String[] params) {
                String path = params[1];
                if(path.equals(".."))
                    path = directory.getParentPath();

                boolean result = directory.setCurrentPath(path);
                if(!result){
                    System.out.println("Directory not found");
                }
                return true;
            }
        }

        public static interface Command {
            boolean execute(Directory directory, String[] params);
        }

        public static  class CommandBuilder {

            private HashMap<String, Command> commands;

            public boolean executeCommand(Directory currentDirectory, String[] command){
                Command commandClass = commands.get(command[0]);
                if(commandClass == null){
                    System.out.println("Unrecognized command");
                    return true;
                }
                return commandClass.execute(currentDirectory, command);
            };

            private HashMap<String, Command> loadCommands(){

                HashMap<String, Command> commands = new HashMap<String, Command>();
                commands.put("cd", new Cd());
                commands.put("ls", new Ls());
                commands.put("pwd", new Pwd());
                commands.put("mkdir", new Mkdir());
                commands.put("touch", new Touch());
                commands.put("quit", null);

                return commands;
            }

            public CommandBuilder(){
                this.commands = loadCommands();
            }
        }

        public static  class Directory {
            private String currentPath = "";
            private HashMap<String, Folder> contents = new HashMap<String, Folder>();

            public String getCurrentPath(){
                return this.currentPath;
            }

            public boolean setCurrentPath(String newPath){
                Folder folder = contents.get(newPath);
                if(folder.getRoot() != currentPath)
                    return false;
                this.currentPath = newPath;
                return true;
            }

            public boolean createNewDirectory(String name){
                if(this.contents.get(name) != null)
                    return false;

                else {
                    Folder currentDir = contents.get(this.currentPath);
                    Folder newDir = new Folder(this.currentPath, name);
                    currentDir.addDirectory(newDir);
                    this.contents.put(name, newDir);
                    return true;
                }
            }

            public ArrayList<String> getCurrentPathFiles(){
                Folder currentPathContent = this.contents.get(this.currentPath);
                return currentPathContent.getFiles();
            }

            public Folder getCurrentFolder(){
                return this.contents.get(this.currentPath);
            }

            public void CreateNewFile(String File) {
                Folder currentDic = this.contents.get(this.currentPath);
                currentDic.addFile(File);
            }

            public Directory(){
                this.currentPath ="";
                this.contents.put(this.currentPath, new Folder("", ""));
            }

            public String getParentPath() {
                Folder currentDir = contents.get(this.currentPath);
                return currentDir.getRoot();
            }
        }

        public static class Folder {
            private String name;
            private String root;
            private ArrayList<String> files;
            private ArrayList<Folder> folders;

            public String getRoot(){
                return this.root;
            }

            public ArrayList<String> getFiles(){
                return this.files;
            }

            public void addFile(String file){
                this.files.add(file);
            }

            public ArrayList<Folder> getFolders(){
                return this.folders;
            }

            public void addDirectory(Folder Directory){
                this.folders.add(Directory);
            }

            public String getName(){
                return this.name;
            }

            public Folder(String root, String name){
                this.root = root;
                this.name = name;
                this.files = new ArrayList<String>();
                this.folders = new ArrayList<Folder>();
            }
        }

        public static class Ls implements Command {

            public boolean execute(Directory directory, String[] params) {
                if(params.length > 1 && params[1].equals("-r"))
                    printAllSubDirectories(directory);
                else {
                    ArrayList<String> files = directory.getCurrentPathFiles();
                    Folder currentFolder = directory.getCurrentFolder();
                    for (String file : files) {
                        System.out.println(file);
                    }
                    ArrayList<Folder> folders = currentFolder.getFolders();
                    for (Folder folder : folders) {
                        System.out.println(folder.getName());
                    }
                }
                return true;
            }

            private void printAllSubDirectories(Directory directory) {
                if(directory.getCurrentPath() != ""){
                    Folder currentFolder = directory.getCurrentFolder();
                    System.out.println();
                    printFiles(directory.getCurrentPathFiles());
                    _printAllSubDirectories(currentFolder);
                }
            }

            private void _printAllSubDirectories(Folder folder){
                if(folder.getFolders().isEmpty()) {
                    System.out.println(folder.getName());
                    printFiles(folder.getFiles());
                }
                else {
                    ArrayList<Folder> folders = folder.getFolders();
                    for (Folder childFolder : folders) {
                        _printAllSubDirectories(childFolder);
                    }
                }
            }

            private void printFiles(ArrayList<String> files){
                for (String file : files) {
                    System.out.println(file);
                }
            }

        }

        public static class Mkdir implements Command {

            public boolean execute(Directory directory, String[] params) {
                String newDir = params[1];

                if(newDir.length() > 100)
                    newDir = newDir.substring(0,100);

                boolean result = directory.createNewDirectory(newDir);
                if(!result)
                    System.out.println("Directory already exists");

                return true;
            }
        }

        public static class Pwd implements Command {

            public boolean execute(Directory directory, String[] params) {
                System.out.println(directory.getCurrentPath());
                return true;
            }
        }

        public static class Quit implements Command {

            public boolean execute(Directory terminal, String[] params) {
                return false;
            }
        }


        public static class Touch implements Command {

            public boolean execute(Directory terminal, String[] params) {
                String newFile = params[1];

                if(newFile.length() > 100)
                    newFile = newFile.substring(0,100);

                terminal.CreateNewFile(newFile);
                return true;
            }
        }

        public static void main(String args[] ) throws Exception {
            // OuterClass.InnerClass innerObject = outerObject.new InnerClass();
            SalesforceAloha4TempSolution s = new SalesforceAloha4TempSolution();
            Scanner scanner = new Scanner(System.in);


            Directory currentDirectory = new Directory();
            CommandBuilder builder = new CommandBuilder();
            int i = 0;
            boolean next = true;
            // String [] command = new String[]();
            List<String> commandList = new ArrayList<>();
            while(scanner.hasNext()) {
                //  System.out.println("cominghere"  + scanner.nextLine());
                commandList.add(scanner.nextLine());
                //command[i] = scanner.nextLine();
                //String[] command = args[i].split("\\n");
            }

            next = builder.executeCommand(currentDirectory, commandList.toArray(new String[commandList.size()]));
        }
    }

