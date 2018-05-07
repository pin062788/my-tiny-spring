package top.wj2yx.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * 该类对象对应于一个增强器
 * author: wang
 * date: 2018/5/5
 * time: 21:18
 */
public interface Advisor {
    /**
     * Advice:
     * (http://aopalliance.sourceforge.net/doc/org/aopalliance/aop/Advice.html)
     * Tag interface for Advice. Implementations can be any type of advice, such as Interceptors.
     * 一个标记类，它的实现者可以是任意类型的advice，比如Interceptor拦截器
     * @return
     */
    Advice getAdvice(); //得到增强
}
