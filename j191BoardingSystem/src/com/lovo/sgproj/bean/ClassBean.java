package com.lovo.sgproj.bean;

import java.time.LocalDate;

//班级实体
public class ClassBean {
    private int classID;
    private String className;//班级名称
    private String classTeacher;//带班老师
    private LocalDate classFoundDate;//开班时间

    public ClassBean() {
    }

    public ClassBean(int classID, String className, String classTeacher, LocalDate classFoundDate) {
        this.classID = classID;
        this.className = className;
        this.classTeacher = classTeacher;
        this.classFoundDate = classFoundDate;
    }

    public ClassBean(String className, String classTeacher, LocalDate classFoundDate) {
        this.className = className;
        this.classTeacher = classTeacher;
        this.classFoundDate = classFoundDate;
    }


    public ClassBean(String className) {
        this.className = className;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public LocalDate getClassFoundDate() {
        return classFoundDate;
    }

    public void setClassFoundDate(LocalDate classFoundDate) {
        this.classFoundDate = classFoundDate;
    }

    @Override
    public String toString() {
        return ""+ className;
    }
}
