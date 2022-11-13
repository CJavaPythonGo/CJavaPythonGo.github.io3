package com.lovo.sgproj.service;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.dao.ClassDAOImp;
import com.lovo.sgproj.dao.RoomDAOImp;
import com.lovo.sgproj.dao.inter.RoomDAO;
import com.lovo.sgproj.service.inter.RoomService;
import com.lovo.sgproj.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomServiceImp implements RoomService {
    private RoomDAOImp roomDAO = new RoomDAOImp();

    @Override
    public ArrayList<RoomBean> showLiveableRoomsByGender(boolean gender, int roomID) {
        return roomDAO.selectRoomByGenderRoomID(gender, roomID);
    }

    @Override
    public ArrayList<RoomBean> showAllRooms(int count) {
        return roomDAO.selectAllRooms(count);
    }

    @Override
    public boolean addRoom(RoomBean roomBean) {
        RoomBean oldRoom = roomDAO.selectRoomByAddress(roomBean.getRoomAddress());
        if (oldRoom != null) {
            return false;
        } else {
            roomDAO.insertRoom(roomBean);
            return true;
        }
    }

    @Override
    public boolean removeRoom(int roomID) {
        RoomBean cls = roomDAO.selectRoomByID(roomID);
        if (cls != null) {
            return false;
        }
        roomDAO.deleteRoomByID(roomID);
        return true;
    }

    @Override
    public RoomBean getRoomByID(int roomID) {
        return roomDAO.selectRoomByID(roomID);
    }

    @Override
    public ArrayList<RoomBean> showAllRoomByCondition(String roomAddress, int roomStatus, boolean liveable, int roomGender, int count) {
        return roomDAO.selectRoomByCondition(roomAddress, roomStatus, liveable, roomGender, count);
    }
}
