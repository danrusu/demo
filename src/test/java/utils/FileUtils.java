package utils;

import java.nio.file.Path;

import static java.lang.String.format;

public class FileUtils {
    public static String getFileURI(Path filePath){
        return format("file://%s", filePath.toUri().getRawPath());
    }
}
