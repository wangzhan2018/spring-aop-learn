package com.wz;


import com.wz.test.TestBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestMain {
    public static void main(String args[]){
        ClassPathXmlApplicationContext bf=new ClassPathXmlApplicationContext("AplicationContext.xml");
        //org.springframework.core.io.Resource resource =new ClassPathResource("AplicationContext.xml");
       // BeanFactory bf=new XmlBeanFactory(resource);

        TestBean bean=(TestBean) bf.getBean("test");
        bean.test();
        System.out.println(bean.getClass());
        System.out.println(bf.getBeanDefinitionCount());
        for (String str:bf.getBeanDefinitionNames()) {
            System.out.println(str);
        }
        System.out.println(bf.getBeanFactory().getBeanDefinition("test"));
    }
}
