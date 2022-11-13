package com.lovo.sgproj.service.inter;

import com.lovo.sgproj.bean.StudentBean;

import java.util.ArrayList;

public interface StudentService {
    /*
        方法调用处：
            点击"学生管理"菜单\需要刷新列表的时候
        参数：
        返回：每个StudentBean都要关联ClassBean和RoomBean
        异常：
     */
    public ArrayList<StudentBean> showAllStudents(int count);

    ArrayList<StudentBean> showAllStudents();

    /*
            方法调用处：
               点击StudentInDialog的"添加"按钮
            参数：stu中需要关联ClassBean和RoomBean
            返回：
            异常：
         */
    public void studentIn(StudentBean stu);


    /*
        方法调用处：
           点击StudentOutDialog的"确定"按钮
        参数：被选中的学生ID
        返回：
        异常：
     */
    public void studentOut(int stuID);

    /*
        方法调用处：
            点击"学生换房"按钮，弹出StudentChgDialog;
            点击"学生详细信息"按钮，弹出StudentShowDialog;
        参数：被选中的学生ID
        返回：StudentBean对象中需要关联班级和房间
        异常：
     */
    public StudentBean showStudentByID(int stuID);

    /*
       方法调用处：
           点击StudentChgDialog的"确定"按钮;
       参数：
       返回：
       异常：
    */
    public void studentChg(int stuID, int oldRoomID, int newRoomID);

    /*
       方法调用处：
           点击StudentManagePanel的"查找"按钮;
       参数：
            三个字符串不能传入null，如果用户没有输入，应该传入""
       返回：每个StudentBean都要关联ClassBean和RoomBean
       异常：
    */
    public ArrayList<StudentBean> showAllStudentsByCondition(String stuName,String className,String roomAddress);
}
