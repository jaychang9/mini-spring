package cn.jaychang.sf.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class DefaultResourceLoader implements ResourceLoader {
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        Assert.notBlank(location, "location must not blank");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            try {
                ClassPathResource classPathResource = new ClassPathResource("/" + location.replace(CLASSPATH_URL_PREFIX, ""));
                return classPathResource;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new UrlResource(new URL(location));
        } catch (MalformedURLException malformedURLException) {
            try {
                return new FileSystemResource(location);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
