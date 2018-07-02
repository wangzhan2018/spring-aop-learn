package com.wz.staticproxy;

import com.wz.staticproxy.inter.UserSave;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class UserProxy implements UserSave{
    //接受的目标
    UserSave userSave;

    public UserProxy(){}
    /*通过构造函数注入代理实例*/
    public UserProxy(UserSave userSave){
        this.userSave=userSave;
    }
    /*通过set函数注入代理实例*/
    public void setUserSave(UserSave userSave){
        this.userSave=userSave;
    }
    /*代码主函数*/
    public void save(){
       if (userSave !=null){
           before();
           userSave.save();//目标函数
           atfer();
       }
    }

    //执行目标函数之前要执行的方法
    private void atfer() {
        System.out.println("after proxy!");
    }
    //执行目标函数之后要执行的方法
    private void before() {
        System.out.println("before proxy!");
    }


}
