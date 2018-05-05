package top.wj2yx.tinyioc.beans;

import top.wj2yx.tinyioc.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:14
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private Map<String, BeanDefinition> registry; //String对应配置文件中的id属性，BeanDefinition包含了id属性所对应的bean实例

    private ResourceLoader resourceLoader; //资源加载器，用来加载配置文件资源

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
