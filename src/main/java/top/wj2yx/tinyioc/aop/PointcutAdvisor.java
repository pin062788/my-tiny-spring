package top.wj2yx.tinyioc.aop;

/**
 * 该类对象对应于一个切面
 * author: wang
 * date: 2018/5/5
 * time: 21:24
 */
public interface PointcutAdvisor extends Advisor{
    Pointcut getPointcut(); //得到切点，因此该类相当于组合了Pointcut
}
