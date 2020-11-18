package com.brianxia.miniseata.client.spring;

import com.brianxia.miniseata.client.annotation.MiniGlobalTransactional;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author brianxia
 * @version 1.0
 * @date 2020/11/18 15:21
 */
@Configuration
public class GlobalTransactionScanner extends AbstractAutoProxyCreator {

    private MethodInterceptor interceptor;
    private static final Set<String> PROXYED_SET = new HashSet<String>();

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        try {
            synchronized (PROXYED_SET) {
                if (PROXYED_SET.contains(beanName)) {
                    return bean;
                }
                interceptor = null;

                Class<?> serviceInterface = bean.getClass();
                Method[] methods = serviceInterface.getMethods();
                boolean find = false;
                for (Method method : methods) {
                    if(method.getDeclaredAnnotation(MiniGlobalTransactional.class) != null){
                        find = true;
                        break;
                    }
                }

                if(find){
                    interceptor = new GlobalTransactionalInterceptor();
                }else{
                    return bean;
                }

                bean = super.wrapIfNecessary(bean, beanName, cacheKey);
                PROXYED_SET.add(beanName);
                return bean;
            }
        } catch (Exception exx) {
            throw new RuntimeException(exx);
        }
    }

    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> aClass, String s, TargetSource targetSource) throws BeansException {
        return new Object[]{interceptor};
    }
}
