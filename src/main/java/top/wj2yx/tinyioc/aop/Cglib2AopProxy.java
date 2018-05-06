package top.wj2yx.tinyioc.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 具体的Aop代理类，实现了得到目标对象的代理对象的功能
 * author: wang
 * date: 2018/5/5
 * time: 23:17
 */
public class Cglib2AopProxy extends AbstractAopProxy {

    public Cglib2AopProxy(AdvisedSupport advisedSupport){
        super(advisedSupport);
    }

    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        /*
        setCallback: Set the single Callback to use.
        net.sf.cglib.proxy.MethodInterceptor: General-purpose Enhancer callback which provides for "around advice".
         */
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advisedSupport;

        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        private DynamicAdvisedInterceptor(AdvisedSupport advisedSupport){
            this.advisedSupport = advisedSupport;
        }

        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            /*
            org.aopalliance.intercept.Interface MethodInterceptor#invoke(MethodInvocation invocation)
             */
            if(advisedSupport.getMethodMatcher() == null
                    || advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())){
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args, methodProxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args, methodProxy);
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation{
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy){
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
