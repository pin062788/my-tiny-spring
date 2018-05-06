package top.wj2yx.tinyioc.aop;

/**
 * 该类对象对应于一个声明的切点
 * author: wang
 * date: 2018/5/5
 * time: 21:22
 */
public interface Pointcut {
    ClassFilter getClassFilter(); //得到切点匹配器
    MethodMatcher getMethodMatcher(); //得到类匹配器
}
