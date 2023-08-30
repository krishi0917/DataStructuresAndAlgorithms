package LeetcodePrograms;

import java.io.*;
import java.util.*;

enum TypeOfFileSystem {
    FILE, FOLDER
}

enum NmCmnds {
    PWD("pwd"),
    LS("ls"),
    MKDIR("mkdir"),
    CD("cd"),
    TOUCh("touch"),
    QUIT("quit");

    private final String data;

    NmCmnds(final String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}

class FileSystemObject  {
    private String name;
    private FileSystemObject fthr;
    private ArrayList<FileSystemObject> chld;
    private TypeOfFileSystem type;
    public static final String SEPARATOR = "/";
    public static final int MAX_CHARS = 100;

    public FileSystemObject(String name, TypeOfFileSystem type, FileSystemObject fthr) {
        this.name = name;
        this.type = type;
        this.fthr = fthr;
        this.chld = new ArrayList<>();
    }

    @Override
    public String toString() {
        return SEPARATOR + name;
    }

    public String getAbsPath() {
        if(fthr == null) {
            return this.toString();
        }
        return fthr.getAbsPath() + this;
    }

    public void printAbsPath() {
        System.out.println(getAbsPath());
    }

    public boolean existDir(String nameOfDirectory) {
        for(FileSystemObject item: chld) {
            if(item.name.compareTo(nameOfDirectory) == 0 && item.type == TypeOfFileSystem.FOLDER) {
                return true;
            }
        }
        return false;
    }

    private boolean existFile(String fileName) {
        for(FileSystemObject item: chld) {
            if(item.name.compareTo(fileName) == 0 && item.type == TypeOfFileSystem.FILE) {
                return true;
            }
        }
        return false;
    }

    public FileSystemObject getSubfolder(String nameOfDirectory) {
        for(FileSystemObject item: chld) {
            if(item.name.compareTo(nameOfDirectory) == 0 && item.type == TypeOfFileSystem.FOLDER) {
                return item;
            }
        }

        return null;
    }

    public void CrtDr(String nameOfDirectory) {
        if (!this.existDir(nameOfDirectory)) {
            chld.add(new FileSystemObject(nameOfDirectory, TypeOfFileSystem.FOLDER, this));
        } else {
            System.out.println("Directory already exists");
        }
    }

    public void lstFlesFoldrs(boolean recrsv) {
        if(recrsv) {
            printAbsPath();
        }else if(fthr == null) {
            System.out.println(this.toString());
        }

        for(FileSystemObject item: chld) {
            if(recrsv && item.type == TypeOfFileSystem.FOLDER) {
                item.lstFlesFoldrs(true);
            } else {
                System.out.println(item.name);
            }
        }
    }

    public FileSystemObject getfthr() {
        return fthr;
    }

    public void CrtFle(String fileName) {
        if(!this.existFile(fileName)) {
            FileSystemObject file = new FileSystemObject(fileName, TypeOfFileSystem.FILE, this);
            chld.add(file);
        }
    }
}

class OperatinSystemFS {
    private static OperatinSystemFS INSTANCE = new OperatinSystemFS();
    private FileSystemObject root;
    private FileSystemObject curPth;
    public static OperatinSystemFS getFileSystem() {
        return INSTANCE;
    }

    private OperatinSystemFS() {
        initialize();
    }

    private void initialize() {
        this.root = new FileSystemObject("root", TypeOfFileSystem.FOLDER, null);
        curPth = root;
    }

    public void clean() {
        initialize();
    }

    protected Object readResolve() {
        return INSTANCE;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        INSTANCE = this;
    }

    public void printAbsPath() {
        curPth.printAbsPath();
    }

    public void CrtDr(String nameOfDirectory) {
        curPth.CrtDr(nameOfDirectory);
    }

    private boolean changeTheDirectory(String nameOfDirectory) {
        boolean foundDir = true;

        if (nameOfDirectory.compareTo("..") == 0 && curPth.getfthr() == null) {
            return true;
        } else if (nameOfDirectory.compareTo("..") == 0 && curPth.getfthr() != null) {
            curPth = curPth.getfthr();
        } else if (nameOfDirectory.compareTo(".") != 0) {
            FileSystemObject subFolder = curPth.getSubfolder(nameOfDirectory);
            if (subFolder != null) {
                curPth = subFolder;
            } else {
                foundDir = false;
            }
        }

        if(!foundDir) {
            System.out.println("Directory not found");
        }
        return foundDir;
    }

    public boolean changeTheDirectory(String[] dirList) {
        FileSystemObject currentFolderTemp = curPth;
        boolean success = true;

        for(String dir: dirList) {
            if(!this.changeTheDirectory(dir))
            {
                success = false;
                break;
            }
        }

        if(!success) {
            curPth = currentFolderTemp;
        }
        return success;
    }

    public void lstFlesFoldrs(boolean recrsv, String nameOfDirectory) {
        FileSystemObject currentFolderTemp = null;

        if (nameOfDirectory.length() > 0) {
            currentFolderTemp = curPth;
            if(!this.changeTheDirectory(nameOfDirectory.split(FileSystemObject.SEPARATOR))) {
                return;
            }
        }

        curPth.lstFlesFoldrs(recrsv);

        if (nameOfDirectory.length() > 0) {
            curPth = currentFolderTemp;
        }
    }

    public void CrtFle(String fileName) {
        curPth.CrtFle(fileName);
    }
}

interface Command {
    public static final String ARG_DELIMITER = " ";
    public static final String ARG_recrsv = "-r";

    public void exeCommand();
    public boolean check();
}

class CurrentDir implements Command{
    private String command;
    public CurrentDir(String command) {
        this.command = command;
    }
    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;
        if (splitCmds.length != 1) {
            valid = false;
        }
        return valid;
    }

    public void exeCommand() {
        if (check()) {
            OperatinSystemFS.getFileSystem().printAbsPath();
        }

    }
}

class LstCnt implements Command {

    private boolean recrsv = false;
    private String command;
    private String nameOfDirectory;

    public LstCnt(String command)
    {
        this.command = command;
        this.nameOfDirectory = "";
    }

    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;

        if (splitCmds.length > 3) {
            valid = false;
        } else if (splitCmds.length == 2 && splitCmds[1].compareTo(Command.ARG_recrsv) == 0) {
            recrsv = true;
        } else if (splitCmds.length == 2 && splitCmds[1].compareTo(Command.ARG_recrsv) != 0) {
            nameOfDirectory = splitCmds[1];
        } else if (splitCmds.length == 3 && splitCmds[1].compareTo(Command.ARG_recrsv) == 0) {
            recrsv = true;
            nameOfDirectory = splitCmds[2];
        }

        return valid;
    }

    public void exeCommand()
    {
        if(check()) {
            OperatinSystemFS.getFileSystem().lstFlesFoldrs(recrsv, nameOfDirectory);
        }
        else {
            System.out.println("Invalid Command");
        }
    }
}

class CrtDr implements Command {

    private String nameOfDirectory = "";
    private String command;

    public CrtDr(String command) {
        this.command = command;
    }

    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;

        if (splitCmds.length == 2) {
            nameOfDirectory = splitCmds[1];

            if(nameOfDirectory.length() >= FileSystemObject.MAX_CHARS) {
                System.out.println("Invalid File or Folder Name");
                valid = false;
            }
        } else {
            valid = false;
        }
        return valid;
    }

    public void exeCommand() {
        if(check()) {
            OperatinSystemFS.getFileSystem().CrtDr(nameOfDirectory);
        }
    }
}

class CrtFle implements Command {

    private String fileName = "";
    private String command;
//righting the codesa
    public CrtFle(String command) {
        this.command = command;
    }

    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;

        if (splitCmds.length == 2) {
            fileName = splitCmds[1];

            if(fileName.length() >= FileSystemObject.MAX_CHARS) {
                System.out.println("Invalid File or Folder Name");

                valid = false;
            }
        } else {
            valid = false;
        }

        return valid;
    }

    public void exeCommand() {
        if(check()) {
            OperatinSystemFS.getFileSystem().CrtFle(fileName);
        }
    }
}

class changeTheDirectory implements Command {

    private String[] multipleDir;
    private String command;

    public changeTheDirectory(String command) {
        this.command = command;
    }
    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;

        if (splitCmds.length == 2) {
            String nameOfDirectory = splitCmds[1];
            multipleDir = nameOfDirectory.split(FileSystemObject.SEPARATOR);
        } else {
            valid = false;
        }

        return valid;
    }

    public void exeCommand() {
        if(check()){
            OperatinSystemFS.getFileSystem().changeTheDirectory(multipleDir);
        } else {
            System.out.println("Invalid Command");

        }
    }
}

class Quit implements Command {

    private String command = "";

    public Quit(String command) {
        this.command = command;
    }

    public boolean check() {
        String[] splitCmds = command.split(Command.ARG_DELIMITER);
        boolean valid = true;
        if (splitCmds.length != 1) {
            valid = false;
            System.out.println("Invalid Command");
        }
        return valid;
    }
    public void exeCommand() {
    }
}

/**
 * The entry point for the Test program
 */
public class SalesforceAloha4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String strCommand;
        do {
            strCommand = sc.nextLine();
            Command cmd = null;

            if (strCommand.startsWith(NmCmnds.QUIT.toString())) {
                cmd = new Quit(strCommand);
                if (cmd.check()) {
                    break;
                }
            } else if (strCommand.startsWith(NmCmnds.PWD.toString())) {
                cmd = new CurrentDir(strCommand);
            } else if (strCommand.startsWith(NmCmnds.LS.toString())) {
                cmd = new LstCnt(strCommand);
            } else if (strCommand.startsWith(NmCmnds.MKDIR.toString())) {
                cmd = new CrtDr(strCommand);
            } else if (strCommand.startsWith(NmCmnds.CD.toString())) {
                cmd = new changeTheDirectory(strCommand);
            } else if (strCommand.startsWith(NmCmnds.TOUCh.toString())) {
                cmd = new CrtFle(strCommand);
            }

            if(cmd != null)
                cmd.exeCommand();
            else
                System.out.println("Unrecognized command");


        } while(true);

        sc.close();
    }
}