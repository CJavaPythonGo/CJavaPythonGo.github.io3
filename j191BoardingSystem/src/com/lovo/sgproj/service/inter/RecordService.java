package com.lovo.sgproj.service.inter;

import com.lovo.sgproj.bean.RecordBean;

import java.util.ArrayList;

public interface RecordService {

    /*
        方法调用处：
            点击RoomManagePanel的"查看房间设置"按钮，初始化RoomEqpDialog的时候
        参数：
            被选中的房间ID
        返回:
        异常：
     */
    public ArrayList<RecordBean> showRecordByRoom(int roomID);

    /*
        方法调用处：
            点击RoomEqpDialog的"添加设施记录"按钮
        参数：
            record -- recSoluted属性默认false
            roomID -- 被选中房间
        返回:
        异常：
     */
    public void addRecord(RecordBean record,int roomID);

    /*
          方法调用处：
                点击RoomEqpDialog的"处理已损坏设施"按钮
           参数：被选中的设施记录ID
           返回:
           异常：
         */
    public void processRecord(int recordID);

}
