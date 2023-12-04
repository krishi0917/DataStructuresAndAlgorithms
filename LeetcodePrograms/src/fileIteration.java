//import java.io.File;
//import java.util.Queue;
//
//public class fileIteration {
//	public void listf(String directoryName, Queue files) {
//		File directory = new File(directoryName);
//
//		// get all the files from a directory
//		File[] fList = directory.listFiles();
//		for (File file : fList) {
//			if (file.isFile()) {
//				if (FilenameUtils.getExtension(file).equals("html"))
//
//					// files.add(file);
//					System.out.println(file);
//			} else if (file.isDirectory()) {
//				files.add(file); // here we add file to queue
//			}
//		}
//		if (!files.isEmpty()) // if sub directories are not empty, go recursive
//			listf(files.remove().toString(), files);
//	}
//
//	public static void main(String[] args) {
//
//	}
//}