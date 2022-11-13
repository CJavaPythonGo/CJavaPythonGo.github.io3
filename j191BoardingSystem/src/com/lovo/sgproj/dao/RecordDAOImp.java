package com.lovo.sgproj.dao;

import com.lovo.sgproj.bean.RecordBean;
import com.lovo.sgproj.dao.inter.RecordDAO;
import com.lovo.sgproj.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RecordDAOImp implements RecordDAO {
    @Override
    public ArrayList<RecordBean> selectRecordByRoom(int roomID) {
        ArrayList<RecordBean> rdLst=new ArrayList<>();
        String sql="SELECT pk_recID,f_recDescription,f_recReportDate,f_recSoluted,fk_roomID from t_record where fk_roomID=?;";
        try(Connection con= DBUtil.getConnection();
            PreparedStatement ps=con.prepareStatement(sql, 1)){
            ps.setInt(1, roomID);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                RecordBean record=new RecordBean();
                record.setRecID(rs.getInt("pk_recID"));
                record.setRecDescription(rs.getString("f_recDescription"));
                record.setRecReportDate(rs.getObject("f_recReportDate", LocalDate.class));
                record.setRecSoluted(rs.getBoolean("f_recSoluted"));
                rdLst.add(record);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rdLst;
    }

    @Override
    public void insertRecord(RecordBean record, int roomID) {
        String sql="INSERT INTO `sgproj191`.`t_record` (`pk_recID`, `f_recDescription`, `f_recReportDate`, `f_recSoluted`, `fk_roomID`) "+
                "VALUES ('"+record.getRecID()+"', '"+record.getRecDescription()+"', '"+record.getRecReportDate()+"', "+record.isRecSoluted()+", '"+roomID+"');";
        try(Connection con=DBUtil.getConnection();
            Statement st=con.createStatement()){
            st.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(int recordID) {
        //更新该条记录
        String sql1="UPDATE `sgproj191`.`t_record` SET  `f_recSoluted`='1' WHERE `pk_recID`=?;";
        //查询该房间其他未解决记录
        String sql2="SELECT pk_recID,f_recDescription,f_recReportDate,f_recSoluted,fk_roomID from t_record WHERE f_recSoluted='0' \n" +
                "and pk_recid<>? and fk_roomID=\n" +
                "(SELECT fk_roomID from t_record WHERE pk_recid=?)";
        //更新房间状态
        String sql3="UPDATE `sgproj191`.`t_room` SET `f_roomStatus`='1' WHERE `pk_roomID`=(SELECT fk_roomID from t_record WHERE pk_recid=?);";
        try(Connection con=DBUtil.getConnection();
            PreparedStatement ps1=con.prepareStatement(sql1);
            PreparedStatement ps2=con.prepareStatement(sql2,2);
            PreparedStatement ps3=con.prepareStatement(sql3)){
            ps2.setInt(1, recordID);
            ps2.setInt(2, recordID);
            ResultSet rs2=ps2.executeQuery();
            while (rs2.next()){
                ps1.setInt(1, recordID);
                ps1.executeUpdate();
                return;
            }
            while (!rs2.next()){
                con.setAutoCommit(false);
                try {
                    ps1.setInt(1, recordID);
                    ps1.executeUpdate();
                    ps3.setInt(1, recordID);
                    ps3.executeUpdate();
                    con.commit();
                }catch (Exception e){
                    e.printStackTrace();
                    con.rollback();
                }
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
