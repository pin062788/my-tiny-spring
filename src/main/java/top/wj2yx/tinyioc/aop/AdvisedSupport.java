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
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/Interceptor.html)
     * org.aopalliance.intercept.Interceptor:
     * This interface represents a generic interceptor.
     * A generic interceptor can intercept runtime events that occur within a base program.
     * Those events are materialized by (reified in) joinpoints.
     * Runtime joinpoints can be invocations, field access, exceptions...
     * 该接口代表一个通用拦截器。这个通用拦截器可以拦截发生在基本程序中的运行时事件。这些事件由连接点实现。运行时连接点可以是方法调用，字段存取，异常等。
     *
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/MethodInterceptor.html)
     * org.aopalliance.intercept.MethodInterceptor:
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
     *
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/Joinpoint.html)
     * org.aopalliance.intercept.Joinpoint:
     * This interface represents a generic runtime joinpoint (in the AOP terminology).
     * 在AOP术语中，代表一个通用的运行时连接点
     *
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/Invocation.html)
     * org.aopalliance.intercept.Invocation:
     * This interface represents an invocation in the program.
     * An invocation is a joinpoint and can be intercepted by an interceptor.
     * 该接口代表程序中的一个方法调用(构造方法，普通方法)，这个方法调用是一个连接点，可以被拦截器拦截
     *
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/MethodInvocation.html)
     * org.aopalliance.intercept.MethodInvocation:
     * Description of an invocation to a method, given to an interceptor upon method-call.
     * A method invocation is a joinpoint and can be intercepted by a method interceptor.
     * 对（普通）方法调用的描述，一个（普通）方法调用是一个连接点，可以被方法拦截器拦截。
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
