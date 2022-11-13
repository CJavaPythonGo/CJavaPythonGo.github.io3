package com.lovo.sgproj.dao;

import com.lovo.sgproj.bean.UserBean;
import com.lovo.sgproj.dao.inter.UserDAO;
import com.lovo.sgproj.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImp implements UserDAO {
    @Override
    public UserBean selectUserByNamePwd(String userName, String pwd) {
        UserBean userBean = null;
        String sql = "SELECT pk_userid,f_username,f_password from t_user WHERE f_username='" + userName + "' " +
                "and f_password='" + pwd + "';";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userBean = new UserBean();
                userBean.setUserID(rs.getInt("pk_userid"));
                userBean.setUsername(rs.getString("f_username"));
                userBean.setPassword(rs.getString("f_password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }
}
