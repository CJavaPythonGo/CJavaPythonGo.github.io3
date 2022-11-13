package com.lovo.sgproj.dao.inter;

import com.lovo.sgproj.bean.UserBean;

public interface UserDAO {

    /*
        方法调用处：
        参数：
        返回：
        异常：
        SQL：
     */
    public UserBean selectUserByNamePwd(String username,String password);

}
