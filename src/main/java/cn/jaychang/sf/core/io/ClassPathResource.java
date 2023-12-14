package cn.jaychang.sf.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class ClassPathResource implements Resource{
    private final String path;


    public ClassPathResource(String path) {
        this.path = path;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return this.getClass().getResourceAsStream(path);
    }
}
