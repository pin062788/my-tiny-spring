package top.wj2yx.tinyioc.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 依赖于AspectJExpression的切点类，同时它也是ClassFilter和MethodMatcher
 * author: wang
 * date: 2018/5/5
 * time: 21:40
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher{

    /**
     * A PointcutParser can be used to build PointcutExpressions
     * for a user-defined subset of AspectJ's pointcut language.
     * (https://www.eclipse.org/aspectj/doc/next/weaver-api/org/aspectj/weaver/tools/PointcutParser.html)
     */
    private PointcutParser pointcutParser;

    /**
     * 切点表达式字符串
     */
    private String expression;

    /**
     * Represents an AspectJ pointcut expression and provides convenience methods to determine
     * whether or not the pointcut matches join points specified in terms of the java.lang.reflect interfaces.
     * (https://www.eclipse.org/aspectj/doc/next/weaver-api/org/aspectj/weaver/tools/PointcutExpression.html)
     */
    private PointcutExpression pointcutExpression;

    /**
     * PointcutPrimitive: An enumeration of the different kinds of pointcut primitives supported by AspectJ
     * (https://www.eclipse.org/aspectj/doc/next/weaver-api/org/aspectj/weaver/tools/PointcutPrimitive.html)
     */
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut(){
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    /**
     * 传入切点原语集合，用于生成pointcutParser
     * @param defaultSupportedPrimitives
     */
    public AspectJExpressionPointcut(Set<PointcutPrimitive> defaultSupportedPrimitives) {
        pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(defaultSupportedPrimitives);
    }

    /**
     * 检查是否准备好匹配，即pointcutExpression对象是否生成了，如果没生成，则调用生成
     */
    protected void checkReadyToMatch(){
        if(pointcutExpression == null){
            pointcutExpression = buildPointcutExpression();
        }
    }

    /**
     * 用pointcutParser生成pointcutExpression
     * @return
     */
    private PointcutExpression buildPointcutExpression() {
        return pointcutParser.parsePointcutExpression(expression);
    }

    /**
     * 由外部来指定切点表达式字符串
     * @param expression
     */
    public void setExpression(String expression){
        this.expression = expression;
    }

    /**
     * 依赖pointcutExpression.couldMatchJoinPointsInType(targetClass)来实现自己的matches(Class targetClass)
     * 用于判定某个类与切点是否匹配
     * @param targetClass
     * @return
     */
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        /*
        couldMatchJoinPointsInType: Determine whether or not this pointcut could ever match a join point in the given class.
         */
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }


    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        /*
        matchesMethodExecution: Determine whether or not this pointcut matches the execution of a given method.
        ShadowMatch: The result of asking a PointcutExpression to match at a shadow (method execution, handler, constructor call, and so on).
         */
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        }
        //TODO:其他情況的判断有待完善，见org.springframework.aop.aspectj.RuntimeTestWalker
        return false;
    }

    /**
     * public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher
     * 返回自己，因为自己实现了ClassFilter接口
     * @return
     */
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher
     * 返回自己，因为自己实现了MethodMatcher接口
     * @return
     */
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
