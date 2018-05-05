package top.wj2yx.tinyioc.beans.xml;

import org.junit.Assert;
import org.junit.Test;
import top.wj2yx.tinyioc.beans.BeanDefinition;
import top.wj2yx.tinyioc.beans.io.UrlResourceLoader;

import java.util.Map;

/**
 * author: wang
 * date: 2018/5/5
 * time: 18:47
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void loadBeanDefinitions() throws Exception{
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }
}
