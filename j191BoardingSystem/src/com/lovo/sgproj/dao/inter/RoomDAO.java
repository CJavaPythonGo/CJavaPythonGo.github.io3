package com.lovo.sgproj.dao.inter;

import com.lovo.sgproj.bean.RoomBean;

import java.util.ArrayList;

public interface RoomDAO {

    public ArrayList<RoomBean> selectRoomByGenderRoomID(boolean gender,int roomID);

    public ArrayList<RoomBean> selectAllRooms(int count);

    public RoomBean selectRoomByAddress(String roomAddress);

    public void insertRoom(RoomBean roomBean);

    public void deleteRoomByID(int roomID);

    public RoomBean selectRoomByID(int roomID);

    public ArrayList<RoomBean> selectRoomByCondition(String roomAddress,int roomStatus,boolean liveable,int roomGender,int count);

}
