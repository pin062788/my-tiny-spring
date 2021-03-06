package top.wj2yx.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 既是AopProxy，可以getProxy，也是InvocationHandler，可以作为参数传入newProxyInstance方法
 * author: wang
 * date: 2018/5/6
 * time: 16:48
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {
    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
        if(advisedSupport.getMethodMatcher() != null && advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())){
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),
                    method, args));
        }else{
            return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
        }
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advisedSupport.getTargetSource().getInterfaces(), this);
    }
}
