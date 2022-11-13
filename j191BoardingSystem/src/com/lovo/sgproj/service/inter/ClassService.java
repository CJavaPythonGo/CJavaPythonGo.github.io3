package com.lovo.sgproj.service.inter;

import com.lovo.sgproj.bean.ClassBean;

import java.util.ArrayList;

public interface ClassService {
    /*
        方法调用处：
            点击"学生入住"按钮，弹出StudentInDialog;
            点击"班级管理"菜单
        参数：
        返回：
        异常：
     */
    public ArrayList<ClassBean> showAllClasses(int count);


    /*
        方法调用处：
            点击ClassAddDialog的"添加"按钮
        参数：
        返回：如果班级重名，则不执行添加返回false；
        异常：
     */
    public boolean addClass(ClassBean classBean);

    /*
        方法调用处：
            点击ClassManagePanel的"删除班级"按钮
        参数：
            被选中的班级ID
        返回：
            如果班级还有学生则返回false，不能删除；
            否则完成删除返回true。
        异常：
     */
    public boolean removeClass(int classID);

}
