package top.wj2yx.tinyioc.aop;

/**
 * 用来判断某个类是否与切点表达式中声明的类是否匹配
 * author: wang
 * date: 2018/5/5
 * time: 21:26
 */
public interface ClassFilter {
    boolean matches(Class targetClass); //用于类匹配
}
