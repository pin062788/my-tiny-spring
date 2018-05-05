package top.wj2yx.tinyioc.beans;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:12
 */
//bean定义的读取器
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
