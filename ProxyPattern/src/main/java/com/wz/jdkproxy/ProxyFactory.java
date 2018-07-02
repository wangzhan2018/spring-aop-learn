package com.wz.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class ProxyFactory {
    private Object target;
    /*构造函数注入代码对象*/
    public ProxyFactory(Object target){
        this.target=target;
    }
    public ProxyFactory(){}
    /*通过set的方式注入*/
    public void setTarget(Object target){
        this.target=target;
    }

    //生产目标对象的代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        before();
                        Object returnValue = method.invoke(target, args);
                        after();
                        return returnValue;
                    }
                }
        );
    }
    //执行目标函数之前要执行的方法
    private void after() {
        System.out.println("Jdk代理：目标函数之后执行");
    }
    //执行目标函数之后要执行的方法
    private void before() {
        System.out.println("Jdk代理：目标函数之前执行");
    }

}
