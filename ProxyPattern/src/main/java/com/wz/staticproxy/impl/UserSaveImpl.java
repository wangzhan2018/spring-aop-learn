package com.wz.staticproxy.impl;

import com.wz.staticproxy.inter.UserSave;

/**
 * Created by wangzhan on 2018-06-26.
 */
public class UserSaveImpl  implements UserSave{

    public void save(){
        System.out.println("----数据已保存----");
    }

}
