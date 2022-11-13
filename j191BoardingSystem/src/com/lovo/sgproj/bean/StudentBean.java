package com.lovo.sgproj.bean;

import java.time.LocalDate;

//学生实体
public class StudentBean {
    private int stuID;
    private String stuName;//姓名
    private boolean stuGender;//性别
    private String stuImage;//头像
    private String stuTel;//联系电话
    private LocalDate stuInDate;//入住时间

    private ClassBean stuClass;//学生所在班级
    private RoomBean stuRoom;//学生所在房间

    public StudentBean() {
    }

    public StudentBean(String stuName, boolean stuGender, String stuImage, String stuTel, LocalDate stuInDate,
                       ClassBean stuClass, RoomBean stuRoom) {
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuImage = stuImage;
        this.stuTel = stuTel;
        this.stuInDate = stuInDate;
        this.stuClass = stuClass;
        this.stuRoom = stuRoom;
    }

    public StudentBean(int stuID, String stuName, boolean stuGender, String stuImage, String stuTel,
                       LocalDate stuInDate) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuImage = stuImage;
        this.stuTel = stuTel;
        this.stuInDate = stuInDate;
    }

    public StudentBean(String stuName, boolean stuGender, String stuImage, String stuTel,
                       LocalDate stuInDate) {
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuImage = stuImage;
        this.stuTel = stuTel;
        this.stuInDate = stuInDate;
    }

    public StudentBean(int stuID, String stuName, boolean stuGender, ClassBean stuClass) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.stuGender = stuGender;
        this.stuClass = stuClass;
    }

    public int getStuID() {
        return stuID;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public boolean isStuGender() {
        return stuGender;
    }

    public void setStuGender(boolean stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuImage() {
        return stuImage;
    }

    public void setStuImage(String stuImage) {
        this.stuImage = stuImage;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public LocalDate getStuInDate() {
        return stuInDate;
    }

    public void setStuInDate(LocalDate stuInDate) {
        this.stuInDate = stuInDate;
    }

    public ClassBean getStuClass() {
        return stuClass;
    }

    public void setStuClass(ClassBean stuClass) {
        this.stuClass = stuClass;
    }

    public RoomBean getStuRoom() {
        return stuRoom;
    }

    public void setStuRoom(RoomBean stuRoom) {
        this.stuRoom = stuRoom;
    }

    @Override
    public String toString() {
        return stuName;
    }
}
