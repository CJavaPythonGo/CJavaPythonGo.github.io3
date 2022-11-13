package com.lovo.sgproj.service;

import com.lovo.sgproj.bean.RecordBean;
import com.lovo.sgproj.dao.RecordDAOImp;
import com.lovo.sgproj.dao.inter.RecordDAO;
import com.lovo.sgproj.service.inter.RecordService;

import javax.swing.*;
import java.util.ArrayList;

public class RecordServiceImp implements RecordService {
    private RecordDAO rdDao=new RecordDAOImp();
    @Override
    public ArrayList<RecordBean> showRecordByRoom(int roomID) {
        return rdDao.selectRecordByRoom(roomID);
    }

    @Override
    public void addRecord(RecordBean record, int roomID) {
        ArrayList<RecordBean> rdLst=rdDao.selectRecordByRoom(roomID);
        boolean flag=true;
        for (RecordBean oldRecord:rdLst){
            if (oldRecord.getRecDescription().equals(record.getRecDescription())&&!oldRecord.isRecSoluted()){
                flag=false;
                JOptionPane.showMessageDialog(null, "该损坏内容已被记录，请不要重复记录");
                return;
            }
        }
        rdDao.insertRecord(record, roomID);
    }

    @Override
    public void processRecord(int recordID) {
        rdDao.updateRecord(recordID);
    }
}
