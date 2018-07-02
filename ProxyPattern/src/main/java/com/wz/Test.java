package com.wz;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.wz.cglibProxy.FactoryProxy;
import com.wz.jdkproxy.ProxyFactory;
import com.wz.staticproxy.UserProxy;
import com.wz.staticproxy.impl.UserSaveImpl;
import com.wz.staticproxy.inter.UserSave;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class Test {

    public static void main(String args[]){
        //普通静态代理模式
        UserSave userSave=new UserSaveImpl();
       /* UserProxy userProxy=new UserProxy(userSave);
        userProxy.save();
        System.out.println(userProxy);*/
        //jdk动态代理
        ProxyFactory proxyFactory=new ProxyFactory(userSave);
        UserSave userJdkProxy=(UserSave)proxyFactory.getProxyInstance();
        System.out.println(userJdkProxy.getClass());
        System.out.println(userJdkProxy.getClass().getSuperclass());
        userJdkProxy.save();
        UserSaveImpl userSave1= (UserSaveImpl) (new FactoryProxy()).getProxy(UserSaveImpl.class);
        userSave1.save();
        System.out.println(userSave1.getClass());
        System.out.println(userSave1.getClass().getSuperclass());
        for(Class T:userSave1.getClass().getInterfaces()){
            System.out.println(T);
        }
        System.out.println();
        //createProxyClassFile();
    }

    /**
     * 获取动态对象，然后生成class文件
     */
    private static void createProxyClassFile() {
        String name = "$Proxy0";
        byte[] data = ProxyGenerator.generateProxyClass(name, UserSaveImpl.class.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("UserBeanImplProxy" + ".class");
            System.out.println((new File("")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }



}
