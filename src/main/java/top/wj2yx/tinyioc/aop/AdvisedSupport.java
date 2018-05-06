package top.wj2yx.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 增强支持类
 * author: wang
 * date: 2018/5/5
 * time: 22:58
 */
public class AdvisedSupport {

    /**
     * 目标对象相关类
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/MethodInterceptor.html)
     * Intercepts calls on an interface on its way to the target. These are nested "on top" of the target.
     * The user should implement the invoke(MethodInvocation) method to modify the original behavior. E.g. the following class implements a tracing interceptor (traces all the calls on the intercepted method(s)):
     * class TracingInterceptor implements MethodInterceptor {
     *      Object invoke(MethodInvocation i) throws Throwable {
     *          System.out.println("method "+i.getMethod()+" is called on "+
     *          i.getThis()+" with args "+i.getArguments());
     *          Object ret=i.proceed();
     *          System.out.println("method "+i.getMethod()+" returns "+ret);
     *          return ret;
     *      }
     *}
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
