package com.example.learn_demo.aop

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

/**
 * @author vianhuang
 * @date 2020/9/18 3:34 PM
 *
 * @Aspect: 声明切面，标记类
 * @Pointcut(切点表达式)：定义切点，标记方法
 * @Before(切点表达式)：前置通知，切点之前执行
 * @Around(切点表达式)：环绕通知，切点前后执行
 * @After(切点表达式)：后置通知，切点之后执行
 * @AfterReturning(切点表达式)：返回通知，切点方法返回结果之后执行
 * @AfterThrowing(切点表达式)：异常通知，切点抛出异常时执行
 */


@Aspect
class AspectJTest {

    @Pointcut("execution(@com.example.learn_demo.aop.BehaviorTrace**(..))")
    fun pointCut() {
    }

   
    @Around("pointCut()")
    fun aroundTest(joinPoint: ProceedingJoinPoint) {
        Log.i("aop", "weaveJoinPoint")
        //拿到方法的签名
        val methodSignature = joinPoint.signature as MethodSignature
        //类名
        val className = methodSignature.declaringType.simpleName
        //方法名
        val methodName = methodSignature.name
        //功能名
        val behaviorTrace = methodSignature.method.getAnnotation(BehaviorTrace::class.java)
        val `fun`: String = behaviorTrace.value

        //方法执行前
        val begin = System.currentTimeMillis()
        //执行拦截方法
        val result = joinPoint.proceed()
        //方法执行后
        val duration = System.currentTimeMillis() - begin
        
        Log.i(
            "aop",
            String.format("功能：%s，%s的%s方法执行，耗时：%d ms", `fun`, className, methodName, duration)
        )
    }
}