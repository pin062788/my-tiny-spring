package top.wj2yx.tinyioc.beans.factory;

/**
 * author: wang
 * date: 2018/5/5
 * time: 16:58
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
