package proyecto2_edd.utils;

import java.nio.file.Paths;

public class FilePathUtil {

    // Method to get absolute path from a relative path of a JSON file
    public static String getJsonFilePath(String relativePath) {
        return Paths.get(relativePath).toAbsolutePath().toString();
    }
}
