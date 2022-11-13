package com.lovo.sgproj.service;

import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.dao.StudentDAOImp;
import com.lovo.sgproj.dao.inter.StudentDAO;
import com.lovo.sgproj.service.inter.StudentService;

import java.util.ArrayList;

public class StudentServiceImp implements StudentService {

    private StudentDAO stuDAO = new StudentDAOImp();

    @Override
    public ArrayList<StudentBean> showAllStudents(int count) {
        return null;
    }

    @Override
    public ArrayList<StudentBean> showAllStudents() {
        ArrayList<StudentBean> stuLst = stuDAO.selectAllStudents();
        return stuLst;
    }

    @Override
    public void studentIn(StudentBean stu) {
        stuDAO.insertStudent(stu);
    }

    @Override
    public void studentOut(int stuID) {
        stuDAO.deleteStudent(stuID);
    }

    @Override
    public StudentBean showStudentByID(int stuID) {
        StudentBean stuB = stuDAO.selectStudentByID(stuID);
        return stuB;
    }

    @Override
    public void studentChg(int stuID, int oldRoomID, int newRoomID) {
        stuDAO.updateStudent(stuID, oldRoomID, newRoomID);
    }

    @Override
    public ArrayList<StudentBean> showAllStudentsByCondition(String stuName, String className,
                                                             String roomAddress) {
        return stuDAO.selectStudentByCondition(stuName, className, roomAddress);
    }
}
