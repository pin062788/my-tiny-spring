package top.wj2yx.tinyioc.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * (http://aopalliance.sourceforge.net/doc/org/aopalliance/intercept/MethodInvocation.html)
 * org.aopalliance.intercept.MethodInvocation:
 * Description of an invocation to a method, given to an interceptor upon method-call.
 * A method invocation is a joinpoint and can be intercepted by a method interceptor.
 * 对（普通）方法调用的描述，一个（普通）方法调用是一个连接点，可以被方法拦截器拦截。
 *
 * author: wang
 * date: 2018/5/6
 * time: 12:53
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected Object target;

    protected Method method;

    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    public Object getThis() {
        return target;
    }

    public AccessibleObject getStaticPart() {
        return method;
    }
}
