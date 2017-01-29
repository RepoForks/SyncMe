package tools.system;

import java.io.File;

/**
 * Created by @AdrianBZG (www.adrianbazaga.com) on 29/01/2017.
 */
public class FileChecker {
    public static boolean fileExists(String fileName) {
        File f = new File(fileName);
        if(f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }
}
