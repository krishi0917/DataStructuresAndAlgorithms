package LeetcodePrograms.src.TrieAndFileSystem;
/*
Given a list of strings that represent files, output the Directory structure
represented by these files.

Example:
Input files =  [
“/app/components/header”,
“/app/services”,
“/app/tests/components/header”,
“/images/image.png”,
"/tsconfig.json",
"/index.html",
];

Output:
-- app
-- components
      -- header
  -- services
  -- tests
      -- components
          -- header
-- images
-- image.png
-- tsconfig.json
-- index.html

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RepresentDirectoryStructure {

        class Dir {
            Map<String, Dir> dirs = new HashMap<>();
        }

        Dir root;

        private RepresentDirectoryStructure() {
            root = new Dir();
        }

        private void mkdir(String path) {
            if (path == null || path.length() == 0 || path.equals("/")) {
                return;
            }

            Dir temp = root;
            String[] array = path.split("/");
            for (int i = 1; i < array.length; i++) {
                String name = array[i];
                if (!temp.dirs.containsKey(name)) {
                    temp.dirs.put(name, new Dir());
                }
                temp = temp.dirs.get(name);
            }
        }

        private void printFileStructure(List<String> input) {
            for (String path : input) {
                mkdir(path);
            }

            printStructure(root.dirs, 0);
        }

        private void printStructure(Map<String, Dir> dirMap, int tabLength) {
            if (dirMap.size() == 0) {
                return;
            }

            for (Map.Entry<String, Dir> entry : dirMap.entrySet()) {
                for (int i = 0; i < tabLength; i++) {
                    System.out.print("-");
                }

                System.out.print(entry.getKey() + "\n");

                printStructure(entry.getValue().dirs, tabLength + 1);
            }
        }

        public static void main(String[] args) {
            ArrayList<String> input = new ArrayList<String>();
            input.add("/app/components/header");
            input.add("/app/services");
            input.add("/app/tests/components/header");
            input.add("/images/image.png");
            input.add("/tsconfig.json");
            input.add("/index.html");

            RepresentDirectoryStructure s = new RepresentDirectoryStructure();
            s.printFileStructure(input);
        }

}
