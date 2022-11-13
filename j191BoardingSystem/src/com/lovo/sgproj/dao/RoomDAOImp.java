package com.lovo.sgproj.dao;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.dao.inter.RoomDAO;
import com.lovo.sgproj.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDAOImp implements RoomDAO {
    @Override
    public ArrayList<RoomBean> selectRoomByGenderRoomID(boolean gender, int roomID) {
        ArrayList<RoomBean> roomLst = new ArrayList<>();
        String sql1 = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum," +
                "f_roomGender,f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate\n" +
                "from t_room where f_roomGender=" + gender + " and pk_roomID<>'" + roomID + "' " +
                "and f_roomCanNum>f_roomInNum;";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql1);
            while (rs.next()) {
                //房间
                RoomBean roomBean = new RoomBean();
                roomBean.setRoomAddress(rs.getString("f_roomAddress"));
                roomBean.setRoomID(rs.getInt("pk_roomID"));
                roomBean.setRoomGender(rs.getBoolean("f_roomGender"));
                roomBean.setRoomHost(rs.getString("f_roomHost"));
                roomBean.setRoomRent(rs.getDouble("f_roomRent"));
                roomBean.setRoomCanNum(rs.getInt("f_roomCanNum"));
                roomBean.setRoomHostTel(rs.getString("f_roomHostTel"));
                roomBean.setRoomInNum(rs.getInt("f_roomInNum"));
                roomBean.setRoomPayWay(rs.getInt("f_roomPayWay"));
                roomBean.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                roomBean.setRoomStatus(rs.getBoolean("f_roomStatus"));
                roomBean.setRoomType(rs.getString("f_roomType"));
                roomLst.add(roomBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomLst;
    }

    @Override
    public ArrayList<RoomBean> selectAllRooms(int count) {
        ArrayList<RoomBean> roomLst = new ArrayList<>();
        String sql = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate from t_room;";
        try (Connection con = DBUtil.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //房间
                RoomBean roomBean = new RoomBean();
                roomBean.setRoomAddress(rs.getString("f_roomAddress"));
                roomBean.setRoomID(rs.getInt("pk_roomID"));
                roomBean.setRoomGender(rs.getBoolean("f_roomGender"));
                roomBean.setRoomHost(rs.getString("f_roomHost"));
                roomBean.setRoomRent(rs.getDouble("f_roomRent"));
                roomBean.setRoomCanNum(rs.getInt("f_roomCanNum"));
                roomBean.setRoomHostTel(rs.getString("f_roomHostTel"));
                roomBean.setRoomInNum(rs.getInt("f_roomInNum"));
                roomBean.setRoomPayWay(rs.getInt("f_roomPayWay"));
                roomBean.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                roomBean.setRoomStatus(rs.getBoolean("f_roomStatus"));
                roomBean.setRoomType(rs.getString("f_roomType"));
                roomLst.add(roomBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomLst;
    }

    @Override
    public RoomBean selectRoomByAddress(String roomAddress) {
        RoomBean roomBean = null;
        String sql = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate " +
                "from t_room where f_roomAddress=?;";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 1)) {
            ps.setString(1, roomAddress);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //房间
                roomBean = new RoomBean();
                roomBean.setRoomAddress(rs.getString("f_roomAddress"));
                roomBean.setRoomID(rs.getInt("pk_roomID"));
                roomBean.setRoomGender(rs.getBoolean("f_roomGender"));
                roomBean.setRoomHost(rs.getString("f_roomHost"));
                roomBean.setRoomRent(rs.getDouble("f_roomRent"));
                roomBean.setRoomCanNum(rs.getInt("f_roomCanNum"));
                roomBean.setRoomHostTel(rs.getString("f_roomHostTel"));
                roomBean.setRoomInNum(rs.getInt("f_roomInNum"));
                roomBean.setRoomPayWay(rs.getInt("f_roomPayWay"));
                roomBean.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                roomBean.setRoomStatus(rs.getBoolean("f_roomStatus"));
                roomBean.setRoomType(rs.getString("f_roomType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomBean;
    }

    @Override
    public void insertRoom(RoomBean roomBean) {
        System.out.println(roomBean.isRoomGender());
        String sql = "INSERT INTO `sgproj191`.`t_room` (`f_roomAddress`, `f_roomType`, `f_roomCanNum`, " +
                "`f_roomGender`, `f_roomRent`, `f_roomPayWay`, `f_roomHost`, `f_roomHostTel`, `f_roomStatus`, " +
                "`f_roomRentDate`) " +
                "VALUES ('" + roomBean.getRoomAddress() + "', '" + roomBean.getRoomType() + "', " +
                "'" + roomBean.getRoomCanNum() + "', " + roomBean.isRoomGender() + "," +
                "'" + roomBean.getRoomRent() + "', '" + roomBean.getRoomPayWay() + "', " +
                "'" + roomBean.getRoomHost() + "', '" + roomBean.getRoomHostTel() + "', " +
                "" + roomBean.isRoomStatus() + ", '" + roomBean.getRoomRentDate() + "');";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()
        ) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoomByID(int roomID) {
        String sql = "DELETE from t_room WHERE pk_roomID='" + roomID + "';";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RoomBean selectRoomByID(int roomID) {
        RoomBean roomBean = null;
        String sql = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate, \n" +
                "pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate,fk_classID,fk_roomID," +
                "pk_classID,f_className,f_classTeacher,f_classFoundDate \n" +
                "from t_student  join t_class on t_student.fk_classID=t_class.pk_classid right join\n" +
                "(SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate \n" +
                "from t_room WHERE pk_roomID=?) as t_rom on t_student.fk_roomID=t_rom.pk_roomID  ;";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 1);
        ) {
            ps.setInt(1, roomID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (roomBean == null) {
                    //房间
                    roomBean = new RoomBean();
                    roomBean.setRoomAddress(rs.getString("f_roomAddress"));
                    roomBean.setRoomID(rs.getInt("pk_roomID"));
                    roomBean.setRoomGender(rs.getBoolean("f_roomGender"));
                    roomBean.setRoomHost(rs.getString("f_roomHost"));
                    roomBean.setRoomRent(rs.getDouble("f_roomRent"));
                    roomBean.setRoomCanNum(rs.getInt("f_roomCanNum"));
                    roomBean.setRoomHostTel(rs.getString("f_roomHostTel"));
                    roomBean.setRoomInNum(rs.getInt("f_roomInNum"));
                    roomBean.setRoomPayWay(rs.getInt("f_roomPayWay"));
                    roomBean.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                    roomBean.setRoomStatus(rs.getBoolean("f_roomStatus"));
                    roomBean.setRoomType(rs.getString("f_roomType"));
                }
                if (rs.getInt("pk_stuID") > 0) {
                    //学生
                    StudentBean studentBean = new StudentBean();
                    studentBean.setStuID(rs.getInt("pk_stuID"));
                    studentBean.setStuGender(rs.getBoolean("f_stuGender"));
                    studentBean.setStuImage(rs.getString("f_stuImage"));
                    studentBean.setStuName(rs.getString("f_stuName"));
                    studentBean.setStuInDate(rs.getObject("f_stuInDate", LocalDate.class));
                    studentBean.setStuTel(rs.getString("f_stuTel"));
                    //班级
                    ClassBean classBean = new ClassBean();
                    classBean.setClassID(rs.getInt("pk_classID"));
                    classBean.setClassName(rs.getString("f_className"));
                    classBean.setClassTeacher(rs.getString("f_classTeacher"));
                    classBean.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));

                    studentBean.setStuClass(classBean);

                    roomBean.getRoomStuLst().add(studentBean);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomBean;
    }

    private int getNum1(int num) {
        return num * num;
    }

    private int getNum2(int num, int num1) {
        return (num + num1) * (num + num1) / 4;
    }

    @Override
    public ArrayList<RoomBean> selectRoomByCondition(String roomAddress, int roomStatus, boolean liveable,
                                                     int roomGender, int count) {
        ArrayList<RoomBean> roomLst = new ArrayList<>();
        int roomStatus1 = getNum1(roomStatus);
        int roomStatus2 = getNum2(roomStatus, roomStatus1);
        int roomGender1 = getNum1(roomGender);
        int roomGender2 = getNum2(roomGender, roomGender1);
        String sql = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate \n" +
                "from t_room WHERE  " +
                "(f_roomStatus ='" + roomStatus1 + "' or f_roomStatus='" + roomStatus2 + "') and  " +
                "(f_roomGender ='" + roomGender1 + "' or f_roomGender='" + roomGender2 + "')  " +
                "and f_roomAddress like '%" + roomAddress + "%' " +
                "HAVING ((f_roomCanNum>f_roomInNum) = false or (f_roomCanNum>f_roomInNum)=" + liveable + ")";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                RoomBean roomBean = new RoomBean();
                roomBean.setRoomAddress(rs.getString("f_roomAddress"));
                roomBean.setRoomID(rs.getInt("pk_roomID"));
                roomBean.setRoomGender(rs.getBoolean("f_roomGender"));
                roomBean.setRoomHost(rs.getString("f_roomHost"));
                roomBean.setRoomRent(rs.getDouble("f_roomRent"));
                roomBean.setRoomCanNum(rs.getInt("f_roomCanNum"));
                roomBean.setRoomHostTel(rs.getString("f_roomHostTel"));
                roomBean.setRoomInNum(rs.getInt("f_roomInNum"));
                roomBean.setRoomPayWay(rs.getInt("f_roomPayWay"));
                roomBean.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                roomBean.setRoomStatus(rs.getBoolean("f_roomStatus"));
                roomBean.setRoomType(rs.getString("f_roomType"));
                roomLst.add(roomBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomLst;
    }
}
