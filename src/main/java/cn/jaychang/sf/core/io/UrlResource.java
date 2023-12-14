package cn.jaychang.sf.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class UrlResource implements Resource{
    private final URL url;


    public UrlResource(URL url) {
        this.url = url;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        try {
            URLConnection urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
