//package LeetcodePrograms.InterviewQuestions;
//
//import java.util.*;
//
//
///**
// * @author Rishi Khurana
// * You are given the class File(String dir) which has the following methods
// * 1) getSize() - return int
// * 2) getName()  - return String
// * 3) isDirectory() - return boolean
// * 4) listFiles() - if isDirectory(), return files on this directory; return List<File>
// * otherwise returns null.
// *
// * base dir: “/home/user/” and
// * conditions "extension is .java" or "file is bigger than 5000 bytes".
// */
//public class AmazonQuestion {
//    class File{
//        public File(){
//
//        }
//
//        public List<File> listFiles(final String dir) {
//        }
//    }
//    List<String> list = new ArrayList<>();
//    public void find(String dir,  String condition){
//            File root = new File(dir);
//        List<File> contents = root.listFiles(dir);
//        flattenList(contents,condition);
//        Iterator itr = list.iterator();
//        while(itr.hasNext()){
//            System.out.print(itr.hasNext());
//        }
//        if(dir == null || condition == null)
//            return null;
//
//    }
//
//
//
//    private void flattenList(List<File> contents, String condition){
//        if(contents!=null){
//            for(File ni : contents){
//                if(!ni.isDirectory() && isConditionMet(ni,condition)){
//                    list.add(ni.getName());
//                }
//                else{
//                    flattenList(ni.listFiles(),condition);
//                }
//            }
//        }
//    }
//    public enum FileConditions{
//        CHECK_EXTENSION,
//        CHECK_SIZE
//    }
//
//    private boolean isConditionMet(File file, String condition){ <-- condition value? <<-- ""
//        String[] conditionArray = condition.split("=");
//        int len = conditionArray.length;
//        if(conditionAray[0])
//            switch(conditionArray[0]){ <--
//                case CHECK_EXTENSION:{
//                    String extenstion[] = conditionArray[1].split(".");
//                    if(file.contains(extension[1]))
//
//                        if(len > 0  && conditionArray[len-1].equals(extension))
//                            return true;
//                    break;
//                }
//                case CHECK_SIZE:{
//                    if(len > 1 && file.getSize() <= Integer.parseInt(conditionArray[len-2]) )
//                }
//
//
//            }
//    }
//
//    void main(){
//        find("/home/user/", [.java])
//    }
//
//
//}
//
//}
