package top.wj2yx.tinyioc.beans.factory;

import top.wj2yx.tinyioc.beans.BeanDefinition;
import top.wj2yx.tinyioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: wang
 * date: 2018/5/5
 * time: 17:03
 */
public class AbstractBeanFactory implements BeanFactory{
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();//String对应于xml文件中的id

    private final List<String> beanDefinitionNames = new ArrayList<String>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object initializeBean(Object bean, String name) throws Exception{
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);//这一步很关键，传进去的是普通bean，传出来的很可能就是代理bean了。
        }

        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);//这一步也很关键，传进去的是原先的bean，传出来的很可能就是代理bean了。
        }

        return bean;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception{
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        return beanDefinition.getBeanClass().newInstance();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception{
        for(Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();){
            String beanName = (String)it.next();
            getBean(beanName);
        }
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception{
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class type) throws Exception{
        List beans = new ArrayList<Object>();
        for(String beanDefinitionName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
