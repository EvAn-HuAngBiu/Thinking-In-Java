package Chapter18;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Evan
 * @date 2018/09/21 16:05
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Chapter17/.");
        String[] allFiles = file.list();
        System.out.println(Arrays.toString(allFiles));

        String[] filesContainC = file.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile("C.*\\.java");

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        System.out.println(Arrays.toString(filesContainC));

        File c1File = new File(file, "CollectionTest1.java");

        // When use an exist File object to initialize a new File object
        // the symbol '.' will remain in the new path
        // getAbsolutePath method does not remove the symbol while getCanonicalPath does
        System.out.println("Path: " + c1File.getPath());
        System.out.println("AbsolutePath: " + c1File.getAbsolutePath());
        System.out.println("CanonicalPath: " + c1File.getCanonicalPath());

        // Tools in File
        System.out.println("CanExecute: " + c1File.canExecute());
        System.out.println("LastModified: " + c1File.lastModified());
        System.out.println("GetParent: " + c1File.getParent());
        System.out.println("GetName: " + c1File.getName());

    }
}
