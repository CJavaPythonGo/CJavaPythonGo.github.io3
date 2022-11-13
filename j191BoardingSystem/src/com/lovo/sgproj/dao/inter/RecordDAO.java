package com.lovo.sgproj.dao.inter;

import com.lovo.sgproj.bean.RecordBean;

import java.util.ArrayList;

public interface RecordDAO {

    public ArrayList<RecordBean> selectRecordByRoom(int roomID);

    /*
        事务：1、update房间状态；2、insert记录
     */
    public void insertRecord(RecordBean record,int roomID);

    /*
        事务：1、udpate这条记录本身；2、查看该房间是否还有未解决记录；3、如果没有，那么修改房间状态。
     */
    public void updateRecord(int recordID);

}
