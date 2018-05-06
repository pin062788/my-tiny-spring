package top.wj2yx.tinyioc.aop;

/**
 * 抽象AopProxy代理类
 * author: wang
 * date: 2018/5/5
 * time: 23:18
 */
public abstract class AbstractAopProxy implements AopProxy {

    /**
     * 需要传入一个advisedSupport对象
     */
    protected AdvisedSupport advisedSupport;

    public AbstractAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
}
