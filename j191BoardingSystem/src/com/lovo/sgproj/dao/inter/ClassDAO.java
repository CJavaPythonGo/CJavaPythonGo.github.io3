package com.lovo.sgproj.dao.inter;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.StudentBean;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ClassDAO {


    public ArrayList<ClassBean> selectAllClasses(int count);

    public ClassBean selectClassByName(String className);

    public ClassBean selectClassByID(int classID);

    public void insertClass(ClassBean classBean);

    public void deleteClass(int classID);



}
