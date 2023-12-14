package cn.jaychang.sf.core.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class FileSystemResource implements Resource{
    private final String filePath;


    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        try {
            File file = new File(filePath);
            return Files.newInputStream(file.toPath());
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
}
