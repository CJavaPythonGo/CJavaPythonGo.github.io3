package com.lovo.sgproj.service;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.dao.ClassDAOImp;
import com.lovo.sgproj.dao.inter.ClassDAO;
import com.lovo.sgproj.service.inter.ClassService;

import java.util.ArrayList;

public class ClassServiceImp implements ClassService {
    private ClassDAO classDAO = new ClassDAOImp();

    @Override
    public ArrayList<ClassBean> showAllClasses(int count) {
        ArrayList<ClassBean> clsLst = classDAO.selectAllClasses(count);
        return clsLst;
    }

    @Override
    public boolean addClass(ClassBean classBean) {
        //通过传入的班级对象查询是否已存在该班级
        ClassBean oldClass = classDAO.selectClassByName(classBean.getClassName());
        if (oldClass != null) {
            return false;
        } else {
            //增加班级
            classDAO.insertClass(classBean);
            return true;
        }
    }

    @Override
    public boolean removeClass(int classID) {
        ClassBean cls = classDAO.selectClassByID(classID);
        if (cls != null) {
            return false;
        }
        classDAO.deleteClass(classID);
        return true;
    }
}
