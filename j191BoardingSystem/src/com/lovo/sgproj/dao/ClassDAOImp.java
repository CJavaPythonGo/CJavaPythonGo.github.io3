package com.lovo.sgproj.dao;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.dao.inter.ClassDAO;
import com.lovo.sgproj.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClassDAOImp implements ClassDAO {
    @Override
    public ArrayList<ClassBean> selectAllClasses(int count) {
        ArrayList<ClassBean> clsLst = new ArrayList<>();
        String sql = "SELECT pk_classID,f_className,f_classTeacher,f_classFoundDate from t_class";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
        ) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ClassBean cls = new ClassBean();
                cls.setClassID(rs.getInt("pk_classID"));
                cls.setClassName(rs.getString("f_className"));
                cls.setClassTeacher(rs.getString("f_classTeacher"));
                cls.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
                clsLst.add(cls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clsLst;
    }

    @Override
    public ClassBean selectClassByName(String className) {
        ClassBean classBean = null;
        String sql = "SELECT pk_classID,f_className,f_classTeacher,f_classFoundDate from t_class " +
                "where f_className=?;";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 1)
        ) {
            ps.setString(1, className);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classBean = new ClassBean();
                classBean.setClassID(rs.getInt("pk_classID"));
                classBean.setClassName(rs.getString("f_className"));
                classBean.setClassTeacher(rs.getString("f_classTeacher"));
                classBean.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classBean;
    }

    //关联学生
    public ClassBean selectClassByID(int classID) {

        ClassBean classBean = null;
        String sql = "SELECT pk_stuID,fk_classID,pk_classID,f_className,f_classTeacher,f_classFoundDate " +
                "from\n" +
                "(SELECT pk_classID,f_className,f_classTeacher,f_classFoundDate from t_class " +
                "where pk_classID=?) as t_cls\n" +
                "join t_student on t_student.fk_classID=t_cls.pk_classID;";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 1)
        ) {
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                classBean = new ClassBean();
                classBean.setClassID(rs.getInt("pk_classID"));
                classBean.setClassName(rs.getString("f_className"));
                classBean.setClassTeacher(rs.getString("f_classTeacher"));
                classBean.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classBean;
    }


    @Override
    public void insertClass(ClassBean classBean) {
        String sql = "INSERT INTO `sgproj191`.`t_class` (`f_className`, `f_classTeacher`, `f_classFoundDate`) VALUES ('" +
                classBean.getClassName() + "', '" + classBean.getClassTeacher() + "','" + classBean.getClassFoundDate() + "');";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()
        ) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteClass(int classID) {
        String sql = "DELETE from t_class WHERE pk_classID='" + classID + "';";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
