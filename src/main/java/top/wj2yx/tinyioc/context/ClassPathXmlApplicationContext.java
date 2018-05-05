package top.wj2yx.tinyioc.context;

import top.wj2yx.tinyioc.beans.BeanDefinition;
import top.wj2yx.tinyioc.beans.factory.AbstractBeanFactory;
import top.wj2yx.tinyioc.beans.factory.AutowireCapableBeanFactory;
import top.wj2yx.tinyioc.beans.io.UrlResourceLoader;
import top.wj2yx.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * author: wang
 * date: 2018/5/5
 * time: 19:31
 */
//比BeanFactory更高级的容器
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception{
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AutowireCapableBeanFactory beanFactory) throws Exception{
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new UrlResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for(Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
