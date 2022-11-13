package com.lovo.sgproj.service.inter;

import com.lovo.sgproj.bean.UserBean;

public interface UserService {

    /*
        方法调用处：
            LoginFrame的登陆按钮
        参数：
        返回：
        异常：
     */
    public boolean login(String username,String password);

}
