package util;

import java.io.File;
import java.nio.file.Paths;

public class FileProvider {

    private FileProvider() {
    }

    public static String getTestPath(String fileName) {
        return Paths.get("src","test","resources", fileName).toString();
    }

    public static String getAbsolutePath(String fileName){
        File file=new File(fileName).getAbsoluteFile();
        return file.getAbsolutePath();
    }
}