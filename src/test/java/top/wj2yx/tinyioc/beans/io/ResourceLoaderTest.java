package top.wj2yx.tinyioc.beans.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yihua.huang@dianping.com
 */
public class ResourceLoaderTest {

	@Test
	public void test() throws IOException {
		ResourceLoader resourceLoader = new UrlResourceLoader();
        Resource resource = resourceLoader.getResource("tinyioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
