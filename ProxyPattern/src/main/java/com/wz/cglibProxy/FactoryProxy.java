//代理类 输出日志
package com.wz.cglibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class FactoryProxy implements MethodInterceptor {
//通过Enhancer 创建代理对象
    private Enhancer enhancer = new Enhancer();

    //通过Class对象获取代理对象
    public Object getProxy(Class c){
        //设置创建子类的类
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method m, Object[] args, MethodProxy proxy) throws Throwable {
        // TODO Auto-generated method stub
        after();
        //代理类调用父类的方法
        proxy.invokeSuper(obj, args);
        before();
        return null;
    }
    //执行目标函数之前要执行的方法
    private void after() {
        System.out.println("cglib代理：目标函数之后执行");
    }
    //执行目标函数之后要执行的方法
    private void before() {
        System.out.println("cglib代理：目标函数之前执行");
    }
}