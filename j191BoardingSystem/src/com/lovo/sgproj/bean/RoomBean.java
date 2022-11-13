package com.lovo.sgproj.bean;

import java.time.LocalDate;
import java.util.ArrayList;

//房间实体
public class RoomBean {
    private int roomID;
    private String roomAddress;//房间地址
    private String roomType;//房间户型
    private int roomCanNum;//可容纳人数
    private int roomInNum = 0;//已住人数
    private boolean roomGender;//房间类型（男女）
    private double roomRent;//房租
    private int roomPayWay;//支付方式(月付--0；季付--1;半年付--2;年付--3)
    private String roomHost;//房东
    private String roomHostTel;//房东电话
    private boolean roomStatus = true;//房间状态(正常--true;设施损坏 -- false)
    private LocalDate roomRentDate;//租房日期

    private ArrayList<StudentBean> roomStuLst = new ArrayList<>();

    public RoomBean() {
    }

    public RoomBean(int roomID, String roomAddress, String roomType, int roomCanNum, int roomInNum, boolean roomGender, double roomRent, int roomPayWay, String roomHost, String roomHostTel, boolean roomStatus, LocalDate roomRentDate) {
        this.roomID = roomID;
        this.roomAddress = roomAddress;
        this.roomType = roomType;
        this.roomCanNum = roomCanNum;
        this.roomInNum = roomInNum;
        this.roomGender = roomGender;
        this.roomRent = roomRent;
        this.roomPayWay = roomPayWay;
        this.roomHost = roomHost;
        this.roomHostTel = roomHostTel;
        this.roomStatus = roomStatus;
        this.roomRentDate = roomRentDate;
    }

    public RoomBean(String roomAddress, String roomType, int roomCanNum, boolean roomGender, double roomRent, int roomPayWay, String roomHost, String roomHostTel, LocalDate roomRentDate) {
        this.roomAddress = roomAddress;
        this.roomType = roomType;
        this.roomCanNum = roomCanNum;
        this.roomGender = roomGender;
        this.roomRent = roomRent;
        this.roomPayWay = roomPayWay;
        this.roomHost = roomHost;
        this.roomHostTel = roomHostTel;
        this.roomRentDate = roomRentDate;
        this.roomStuLst = roomStuLst;
    }

    public RoomBean(String roomAddress, String roomType, int roomCanNum, int roomInNum, boolean roomGender, double roomRent, int roomPayWay, String roomHost, String roomHostTel, boolean roomStatus, LocalDate roomRentDate) {
        this.roomAddress = roomAddress;
        this.roomType = roomType;
        this.roomCanNum = roomCanNum;
        this.roomInNum = roomInNum;
        this.roomGender = roomGender;
        this.roomRent = roomRent;
        this.roomPayWay = roomPayWay;
        this.roomHost = roomHost;
        this.roomHostTel = roomHostTel;
        this.roomStatus = roomStatus;
        this.roomRentDate = roomRentDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCanNum() {
        return roomCanNum;
    }

    public void setRoomCanNum(int roomCanNum) {
        this.roomCanNum = roomCanNum;
    }

    public int getRoomInNum() {
        return roomInNum;
    }

    public void setRoomInNum(int roomInNum) {
        this.roomInNum = roomInNum;
    }

    public boolean isRoomGender() {
        return roomGender;
    }

    public void setRoomGender(boolean roomGender) {
        this.roomGender = roomGender;
    }

    public double getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(double roomRent) {
        this.roomRent = roomRent;
    }

    public int getRoomPayWay() {
        return roomPayWay;
    }

    public void setRoomPayWay(int roomPayWay) {
        this.roomPayWay = roomPayWay;
    }

    public String getRoomHost() {
        return roomHost;
    }

    public void setRoomHost(String roomHost) {
        this.roomHost = roomHost;
    }

    public String getRoomHostTel() {
        return roomHostTel;
    }

    public void setRoomHostTel(String roomHostTel) {
        this.roomHostTel = roomHostTel;
    }

    public boolean isRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public LocalDate getRoomRentDate() {
        return roomRentDate;
    }

    public void setRoomRentDate(LocalDate roomRentDate) {
        this.roomRentDate = roomRentDate;
    }

    public ArrayList<StudentBean> getRoomStuLst() {
        return roomStuLst;
    }

    public void setRoomStuLst(ArrayList<StudentBean> roomStuLst) {
        this.roomStuLst = roomStuLst;
    }

    @Override
    public String toString() {
        return ""+roomAddress;
    }
}
