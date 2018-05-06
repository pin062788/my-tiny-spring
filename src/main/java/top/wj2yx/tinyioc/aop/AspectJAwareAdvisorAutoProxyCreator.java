package top.wj2yx.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import top.wj2yx.tinyioc.beans.BeanPostProcessor;
import top.wj2yx.tinyioc.beans.factory.AbstractBeanFactory;
import top.wj2yx.tinyioc.beans.factory.BeanFactory;

import java.util.List;

/**
 * 代理增强生成器
 * author: wang
 * date: 2018/5/5
 * time: 21:34
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware{
    private AbstractBeanFactory beanFactory;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    /**
     * 用于生成目标对象的代理对象
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if(bean instanceof AspectJExpressionPointcutAdvisor){
            return bean;
        }
        if(bean instanceof MethodInterceptor){
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory
                .getBeansForType(AspectJExpressionPointcutAdvisor.class);

        for(AspectJExpressionPointcutAdvisor advisor : advisors){
            if(advisor.getPointcut().getClassFilter().matches(bean.getClass())){
                ProxyFactory proxyFactory = new ProxyFactory();
                proxyFactory.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
                proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                proxyFactory.setTargetSource(targetSource);

                return proxyFactory.getProxy();
            }
        }
        return bean;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory)beanFactory;
    }
}
