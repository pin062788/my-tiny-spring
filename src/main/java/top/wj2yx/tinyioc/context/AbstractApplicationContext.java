package top.wj2yx.tinyioc.context;

import top.wj2yx.tinyioc.beans.BeanPostProcessor;
import top.wj2yx.tinyioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * author: wang
 * date: 2018/5/5
 * time: 19:10
 */
public abstract class AbstractApplicationContext implements ApplicationContext{
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    /**
     * 加载Bean定义等一系列操作
     * @throws Exception
     */
    public void refresh() throws Exception{
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    /**
     * 实例化单例的bean
     * @throws Exception
     */
    private void onRefresh() throws Exception{
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 将BeanPostProcessors从Map中找出来并注册到BeanPostProcessor数组中
     * @param beanFactory
     * @throws Exception
     */
    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception{
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for(Object beanPostProcessor : beanPostProcessors){
            beanFactory.addBeanPostProcessor((BeanPostProcessor)beanPostProcessor);
        }
    }

    /**
     * 取得bean
     * @param name
     * @return
     * @throws Exception
     */
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
