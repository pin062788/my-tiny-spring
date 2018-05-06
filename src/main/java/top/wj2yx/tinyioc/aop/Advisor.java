package top.wj2yx.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * 该类对象对应于一个增强器
 * author: wang
 * date: 2018/5/5
 * time: 21:18
 */
public interface Advisor {
    Advice getAdvice(); //得到增强
}
