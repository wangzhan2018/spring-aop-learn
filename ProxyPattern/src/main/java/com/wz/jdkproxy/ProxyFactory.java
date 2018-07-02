package com.wz.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class ProxyFactory {
    private Object target;
    /*���캯��ע��������*/
    public ProxyFactory(Object target){
        this.target=target;
    }
    public ProxyFactory(){}
    /*ͨ��set�ķ�ʽע��*/
    public void setTarget(Object target){
        this.target=target;
    }

    //����Ŀ�����Ĵ������
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
    //ִ��Ŀ�꺯��֮ǰҪִ�еķ���
    private void after() {
        System.out.println("Jdk����Ŀ�꺯��֮��ִ��");
    }
    //ִ��Ŀ�꺯��֮��Ҫִ�еķ���
    private void before() {
        System.out.println("Jdk����Ŀ�꺯��֮ǰִ��");
    }

}
