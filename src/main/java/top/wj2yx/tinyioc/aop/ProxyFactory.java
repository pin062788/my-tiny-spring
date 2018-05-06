package top.wj2yx.tinyioc.aop;

/**
 * 代理工厂，用来得到目标对象增强后的代理
 * author: wang
 * date: 2018/5/5
 * time: 23:15
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy {
    /**
     * 依赖Cglib2AopProxy对象得到目标对象的代理
     * @return
     */
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    /**
     * 创建AopProxy
     * @return
     */
    protected final AopProxy createAopProxy(){
        return new Cglib2AopProxy(this);
    }
}
