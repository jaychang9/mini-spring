package cn.jaychang.sf.io;

import cn.hutool.core.io.IoUtil;
import cn.jaychang.sf.core.io.DefaultResourceLoader;
import cn.jaychang.sf.core.io.Resource;
import cn.jaychang.sf.core.io.ResourceLoader;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class ResourceTest {

    @Test
    public void testResourceLoader() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);

        resource = resourceLoader.getResource("./src/test/resources/hello.txt");
        content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);

        resource = resourceLoader.getResource("https://www.baidu.com");
        content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }
}
