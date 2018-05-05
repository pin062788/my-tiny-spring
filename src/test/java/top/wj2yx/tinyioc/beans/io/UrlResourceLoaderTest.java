package top.wj2yx.tinyioc.beans.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: wang
 * date: 2018/5/5
 * time: 18:39
 */

public class UrlResourceLoaderTest {
    @Test
    public void getResource() throws IOException{
        ResourceLoader urlResourceLoader = new UrlResourceLoader();
        Resource urlResource = urlResourceLoader.getResource("tinyioc.xml");
        InputStream inputStream = urlResource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
