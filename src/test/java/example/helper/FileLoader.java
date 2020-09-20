package example.helper;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

public class FileLoader {
    public static String read(String filePath) throws IOException {
        var file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
