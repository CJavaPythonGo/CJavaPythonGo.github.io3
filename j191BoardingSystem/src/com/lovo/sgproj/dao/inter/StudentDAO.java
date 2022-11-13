package com.lovo.sgproj.dao.inter;

import com.lovo.sgproj.bean.StudentBean;

import java.util.ArrayList;

public interface StudentDAO {

    public ArrayList<StudentBean> selectAllStudents(int count);

    ArrayList<StudentBean> selectAllStudents();

    /*
            事务操作：1、insert学生；2、修改入住房间的已住人数
         */
    public void insertStudent(StudentBean studentBean);

    /*
        事务操作：1、delete学生；2、修改入住房间的已住人数
     */
    public void deleteStudent(int stuID);

    /*
        事务操作：1、update学生房间ID；2、原房间人数-1；3、新房间人数+1
     */
    public void updateStudent(int stuID, int oldRoomID, int newRoomID);

    /*
        StudentBean都要关联ClassBean和RoomBean
     */
    public StudentBean selectStudentByID(int stuID);

    /*
        StudentBean都要关联ClassBean和RoomBean
     */
    public ArrayList<StudentBean> selectStudentByCondition(String stuName,String className,String roomAddress);


    public ArrayList<StudentBean> selectStudentByClass(int classID);

    public ArrayList<StudentBean> selectStudentByRoom(int roomID);

}
