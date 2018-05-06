package top.wj2yx.tinyioc.aop;

import org.aopalliance.aop.Advice;

/**
 * 依赖于AspectJExpression的切面
 * author: wang
 * date: 2018/5/5
 * time: 21:38
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    /**
     * 外部传入一个增强
     * @param advice
     */
    public void setAdvice(Advice advice){
        this.advice = advice;
    }

    /**
     * 外部传入一个切点表达式
     * @param expression
     */
    public void setExpression(String expression){
        this.pointcut.setExpression(expression);
    }

    /**
     * 得到切点
     * @return
     */
    public Pointcut getPointcut() {
        return pointcut;
    }

    /**
     * 得到增强
     * @return
     */
    public Advice getAdvice() {
        return advice;
    }
}
