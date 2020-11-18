package com.brianxia.miniseata.client.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author brianxia
 * @version 1.0
 * @date 2020/11/18 15:48
 */
public class GlobalTransactionalInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("方法拦截器生效");
        return methodInvocation.proceed();
    }
}
