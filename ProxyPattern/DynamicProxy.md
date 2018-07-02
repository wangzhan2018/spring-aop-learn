# 				代理模式

### 1.代理模式的概念

​	代理模式的定义：为其他对象提供一种[代理](https://baike.baidu.com/item/%E4%BB%A3%E7%90%86)以控制对这个对象的访问。在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。<!--from百度百科-->

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Proxy_pattern_diagram.svg/439px-Proxy_pattern_diagram.svg.png)  

代理模式一般可以分为动态代理和静态代理。

优点：

​	(1).职责清晰

​	真实的角色就是实现实际的[业务逻辑](https://baike.baidu.com/item/%E4%B8%9A%E5%8A%A1%E9%80%BB%E8%BE%91)，不用关心其他非本职责的事务，通过后期的代理完成一件完成事务，附带的结果就是编程简洁清晰。

​	(2).代理对象可以在客户端和目标对象之间起到中介的作用，这样起到了中介的作用和保护了目标对象的作用。

​	(3).高扩展性

### 2.静态代理

​	静态代理是由程序员创建或工具生成代理类的源码，再编译代理类。所谓静态也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了 。

​	

### 3.动态代理

​	代理类在程序运行时创建的代理方式被成为动态代理。 在springAop中使用了jdk的动态代理和cglib的动态代理，下面我们写两个例子，看jdk和cglib的动态代理是怎么使用的。

#### 	1）jdk实现动态代理：

​	定义一个借口：

​	`public interface UserSave {    void save();}`

​	写一个接口的实现：

```java
public class UserSaveImpl  implements UserSave{

    public void save(){
        System.out.println("----数据已保存----");
    }

}


//代理类
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

//main 方法
   public static void main(String args[]){
       UserSave userSave=new UserSaveImpl();
        //jdk动态代理
        ProxyFactory proxyFactory=new ProxyFactory(userSave);
        UserSave userJdkProxy=(UserSave)proxyFactory.getProxyInstance();
        System.out.println(userJdkProxy);
        System.out.println(userJdkProxy.getClass().getSuperclass());
        userJdkProxy.save();
    }
```

​	执行结果：![1530151523883](C:\Users\wangzhan\AppData\Local\Temp\1530151523883.png)

​	从jdk代理实现类来看，jdk的动态代理必须要实现一个接口。

1. 通过实现 InvocationHandler 接口创建自己的调用处理器； 
2. 通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类； 
3. 通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型； 
4. 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入 





### 特点

动态生成的代理类本身的一些特点 

1. 包：如果所代理的接口都是 public 的，那么它将被定义在顶层包（即包路径为空），如果所代理的接口中有非 public 的接口（因为接口不能被定义为 protect或private，所以除 public之外就是默认的package访问级别，那么它将被定义在该接口所在包，这样设计的目的是为了最大程度的保证动态代理类不会因为包管理的问题而无法被成功定义并访问； 
2. 类修饰符：该代理类具有 final 和 public 修饰符，意味着它可以被所有的类访问，但是不能被再度继承； 
3. 类名：格式是“$ProxyN”，其中 N 是一个逐一递增的阿拉伯数字，代表 Proxy 类第 N 次生成的动态代理类，值得注意的一点是，并不是每次调用 Proxy 的静态方法创建动态代理类都会使得 N 值增加，原因是如果对同一组接口（包括接口排列的顺序相同）试图重复创建动态代理类，它会很聪明地返回先前已经创建好的代理类的类对象，而不会再尝试去创建一个全新的代理类，这样可以节省不必要的代码重复生成，提高了代理类的创建效率。 
4. 类继承关系：Proxy 类是它的父类，这个规则适用于所有由 Proxy 创建的动态代理类。而且该类还实现了其所代理的一组接口; 

#### ​ 2）cglib实现动态代理

CGLIB是什么？

CGLIB是一个强大的、高性能的代码生成库。其被广泛应用于AOP框架（Spring、dynaop）中，用以提供方法拦截操作。Hibernate作为一个比较受欢迎的ORM框架，同样使用CGLIB来代理单端（多对一和一对一）关联（延迟提取集合使用的另一种机制）。CGLIB作为一个开源项目，其代码托管在github，地址为：<https://github.com/cglib/cglib> 

