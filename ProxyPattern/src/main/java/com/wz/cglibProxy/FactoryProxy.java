//������ �����־
package com.wz.cglibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class FactoryProxy implements MethodInterceptor {
//ͨ��Enhancer �����������
    private Enhancer enhancer = new Enhancer();

    //ͨ��Class�����ȡ�������
    public Object getProxy(Class c){
        //���ô����������
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method m, Object[] args, MethodProxy proxy) throws Throwable {
        // TODO Auto-generated method stub
        after();
        //��������ø���ķ���
        proxy.invokeSuper(obj, args);
        before();
        return null;
    }
    //ִ��Ŀ�꺯��֮ǰҪִ�еķ���
    private void after() {
        System.out.println("cglib����Ŀ�꺯��֮��ִ��");
    }
    //ִ��Ŀ�꺯��֮��Ҫִ�еķ���
    private void before() {
        System.out.println("cglib����Ŀ�꺯��֮ǰִ��");
    }
}