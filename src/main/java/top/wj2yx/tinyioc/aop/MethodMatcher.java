package top.wj2yx.tinyioc.aop;

import java.lang.reflect.Method;

/**
 * 用于判断某个类中的某个方法是否和切点表达式中的方法匹配
 * author: wang
 * date: 2018/5/5
 * time: 21:27
 */
public interface MethodMatcher {
    boolean matches(Method method, Class targetClass); //用于判断方法是否匹配
}
