package com.wz.staticproxy;

import com.wz.staticproxy.inter.UserSave;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class UserProxy implements UserSave{
    //���ܵ�Ŀ��
    UserSave userSave;

    public UserProxy(){}
    /*ͨ�����캯��ע�����ʵ��*/
    public UserProxy(UserSave userSave){
        this.userSave=userSave;
    }
    /*ͨ��set����ע�����ʵ��*/
    public void setUserSave(UserSave userSave){
        this.userSave=userSave;
    }
    /*����������*/
    public void save(){
       if (userSave !=null){
           before();
           userSave.save();//Ŀ�꺯��
           atfer();
       }
    }

    //ִ��Ŀ�꺯��֮ǰҪִ�еķ���
    private void atfer() {
        System.out.println("after proxy!");
    }
    //ִ��Ŀ�꺯��֮��Ҫִ�еķ���
    private void before() {
        System.out.println("before proxy!");
    }


}
