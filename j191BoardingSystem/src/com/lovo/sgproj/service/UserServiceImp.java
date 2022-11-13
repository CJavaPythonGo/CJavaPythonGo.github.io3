package com.lovo.sgproj.service;

import com.lovo.sgproj.bean.UserBean;
import com.lovo.sgproj.dao.UserDAOImp;
import com.lovo.sgproj.dao.inter.UserDAO;
import com.lovo.sgproj.service.inter.UserService;

public class UserServiceImp implements UserService {
    private UserDAO userDAO = new UserDAOImp();

    @Override
    public boolean login(String userName, String pwd) {
        UserBean userBean = userDAO.selectUserByNamePwd(userName, pwd);
        if (userBean == null) {
            return false;
        } else {
            return true;
        }
    }
}
