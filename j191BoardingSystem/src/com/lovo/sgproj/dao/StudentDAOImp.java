package com.lovo.sgproj.dao;

import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.dao.inter.StudentDAO;
import com.lovo.sgproj.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentDAOImp implements StudentDAO {
    @Override
    public ArrayList<StudentBean> selectAllStudents(int count) {
        return null;
    }

    @Override
    public ArrayList<StudentBean> selectAllStudents() {
        ArrayList<StudentBean> stuLst = new ArrayList<>();

        String sql = "select pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate," +
                "pk_classID,f_className,f_classTeacher,f_classFoundDate," +
                "pk_roomID,f_roomAddress,f_roomCanNum,f_roomGender,f_roomHost,f_roomHostTel,f_roomInNum," +
                "f_roomPayWay,f_roomRent,f_roomRentDate,f_roomStatus,f_roomType " +
                "from (t_student join t_class on pk_classID=fk_classID) " +
                "join t_room on pk_roomID = fk_roomID order by pk_stuID desc ";

        try (Connection con = DBUtil.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //1、学生
                StudentBean stu = new StudentBean();
                stu.setStuID(rs.getInt("pk_stuID"));
                stu.setStuGender(rs.getBoolean("f_stuGender"));
                stu.setStuImage(rs.getString("f_stuImage"));
                stu.setStuName(rs.getString("f_stuName"));
                stu.setStuInDate(rs.getObject("f_stuInDate", LocalDate.class));
                stu.setStuTel(rs.getString("f_stuTel"));
                //2、班级
                ClassBean cls = new ClassBean();
                cls.setClassID(rs.getInt("pk_classID"));
                cls.setClassName(rs.getString("f_className"));
                cls.setClassTeacher(rs.getString("f_classTeacher"));
                cls.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
                //3、房间
                RoomBean room = new RoomBean();
                room.setRoomAddress(rs.getString("f_roomAddress"));
                room.setRoomID(rs.getInt("pk_roomID"));
                room.setRoomGender(rs.getBoolean("f_roomGender"));
                room.setRoomHost(rs.getString("f_roomHost"));
                room.setRoomRent(rs.getDouble("f_roomRent"));
                room.setRoomCanNum(rs.getInt("f_roomCanNum"));
                room.setRoomHostTel(rs.getString("f_roomHostTel"));
                room.setRoomInNum(rs.getInt("f_roomInNum"));
                room.setRoomPayWay(rs.getInt("f_roomPayWay"));
                room.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                room.setRoomStatus(rs.getBoolean("f_roomStatus"));
                room.setRoomType(rs.getString("f_roomType"));

                //4、房间和班级放入学生
                stu.setStuClass(cls);
                stu.setStuRoom(room);

                //5、学生放入集合
                stuLst.add(stu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stuLst;
    }

    @Override
    public void insertStudent(StudentBean studentBean) {
        String sqlInStu = "INSERT INTO `sgproj191`.`t_student` (`f_stuName`, `f_stuGender`, `f_stuImage`, " +
                "`f_stuTel`, `f_stuInDate`, `fk_classID`, `fk_roomID`) " +
                "VALUES ('" + studentBean.getStuName() + "', " + studentBean.isStuGender() + ", " +
                "'" + studentBean.getStuImage() + "', '" + studentBean.getStuTel() + "', " +
                "'" + studentBean.getStuInDate() + "', '" + studentBean.getStuClass().getClassID() + "', " +
                "'" + studentBean.getStuRoom().getRoomID() + "');";
        String sqlUpdateStu = "UPDATE `sgproj191`.`t_room` SET `f_roomInNum`=f_roomInNum+1 " +
                "WHERE `pk_roomID`='" + studentBean.getStuRoom().getRoomID() + "';";

        try (Connection con = DBUtil.getConnection();
             Statement st1 = con.createStatement();
             Statement st2 = con.createStatement()) {
            con.setAutoCommit(false);
            try {
                st1.executeUpdate(sqlInStu);
                st2.executeUpdate(sqlUpdateStu);
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int stuID) {
        String sql = "DELETE from t_student WHERE pk_stuid='" + stuID + "';";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(int stuID, int oldRoomID, int newRoomID) {
        String sqlUS1 = "UPDATE `sgproj191`.`t_student` SET `fk_roomID`='" + newRoomID + "' " +
                "WHERE `pk_stuID`='" + stuID + "';";
        String sqlUS2 = "UPDATE `sgproj191`.`t_room` SET `f_roomInNum`=f_roomInNum-1 " +
                "WHERE `pk_roomID`='" + oldRoomID + "';";
        String sqlUS3 = "UPDATE `sgproj191`.`t_room` SET `f_roomInNum`=f_roomInNum+1 " +
                "WHERE `pk_roomID`='" + newRoomID + "';";

        try (Connection con = DBUtil.getConnection();
             Statement st1 = con.createStatement();
             Statement st2 = con.createStatement();
             Statement st3 = con.createStatement()
        ) {
            con.setAutoCommit(false);
            try {
                st1.executeUpdate(sqlUS1);
                st2.executeUpdate(sqlUS2);
                st3.executeUpdate(sqlUS3);
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StudentBean selectStudentByID(int stuID) {
        StudentBean stu = null;
        String sql = "select pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate," +
                "pk_classID,f_className,f_classTeacher,f_classFoundDate," +
                "pk_roomID,f_roomAddress,f_roomCanNum,f_roomGender,f_roomHost,f_roomHostTel,f_roomInNum," +
                "f_roomPayWay,f_roomRent,f_roomRentDate,f_roomStatus,f_roomType " +
                "from (t_student join t_class on pk_classID=fk_classID) " +
                "join t_room on pk_roomID = fk_roomID where pk_stuID = " + stuID;

        try (Connection con = DBUtil.getConnection();
             Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //1、学生
                stu = new StudentBean();
                stu.setStuID(rs.getInt("pk_stuID"));
                stu.setStuGender(rs.getBoolean("f_stuGender"));
                stu.setStuImage(rs.getString("f_stuImage"));
                stu.setStuName(rs.getString("f_stuName"));
                stu.setStuInDate(rs.getObject("f_stuInDate", LocalDate.class));
                stu.setStuTel(rs.getString("f_stuTel"));
                //2、班级
                ClassBean cls = new ClassBean();
                cls.setClassID(rs.getInt("pk_classID"));
                cls.setClassName(rs.getString("f_className"));
                cls.setClassTeacher(rs.getString("f_classTeacher"));
                cls.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
                //3、房间
                RoomBean room = new RoomBean();
                room.setRoomAddress(rs.getString("f_roomAddress"));
                room.setRoomID(rs.getInt("pk_roomID"));
                room.setRoomGender(rs.getBoolean("f_roomGender"));
                room.setRoomHost(rs.getString("f_roomHost"));
                room.setRoomRent(rs.getDouble("f_roomRent"));
                room.setRoomCanNum(rs.getInt("f_roomCanNum"));
                room.setRoomHostTel(rs.getString("f_roomHostTel"));
                room.setRoomInNum(rs.getInt("f_roomInNum"));
                room.setRoomPayWay(rs.getInt("f_roomPayWay"));
                room.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                room.setRoomStatus(rs.getBoolean("f_roomStatus"));
                room.setRoomType(rs.getString("f_roomType"));

                //4、房间和班级放入学生
                stu.setStuClass(cls);
                stu.setStuRoom(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return stu;
    }

    @Override
    public ArrayList<StudentBean> selectStudentByCondition(String stuName, String className, String roomAddress) {
        ArrayList<StudentBean> stuLst = new ArrayList<>();
        String sql = "SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate,\n" +
                "pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate,fk_classID,fk_roomID," +
                "pk_classID,f_className,f_classTeacher,f_classFoundDate\n" +
                " from (SELECT pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate,fk_classID," +
                "fk_roomID from t_student WHERE f_stuName like ?) as t_t \n" +
                "join (SELECT pk_classID,f_className,f_classTeacher,f_classFoundDate from t_class " +
                "WHERE f_className like ?) as t_c on t_t.fk_classid=t_c.pk_classid\n" +
                "join (SELECT pk_roomID,f_roomAddress,f_roomType,f_roomCanNum,f_roomInNum,f_roomGender," +
                "f_roomRent,f_roomPayWay,f_roomHost,f_roomHostTel,f_roomStatus,f_roomRentDate " +
                "from t_room WHERE f_roomAddress like ?) as t_r on t_t.fk_roomid=t_r.pk_roomid " +
                "order by pk_stuID desc";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 3)) {
            ps.setString(1, '%' + stuName + '%');
            ps.setString(2, '%' + className + '%');
            ps.setString(3, '%' + roomAddress + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean stu = new StudentBean();
                stu.setStuID(rs.getInt("pk_stuID"));
                stu.setStuGender(rs.getBoolean("f_stuGender"));
                stu.setStuImage(rs.getString("f_stuImage"));
                stu.setStuName(rs.getString("f_stuName"));
                stu.setStuInDate(rs.getObject("f_stuInDate", LocalDate.class));
                stu.setStuTel(rs.getString("f_stuTel"));
                //2、班级
                ClassBean cls = new ClassBean();
                cls.setClassID(rs.getInt("pk_classID"));
                cls.setClassName(rs.getString("f_className"));
                cls.setClassTeacher(rs.getString("f_classTeacher"));
                cls.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
                //3、房间
                RoomBean room = new RoomBean();
                room.setRoomAddress(rs.getString("f_roomAddress"));
                room.setRoomID(rs.getInt("pk_roomID"));
                room.setRoomGender(rs.getBoolean("f_roomGender"));
                room.setRoomHost(rs.getString("f_roomHost"));
                room.setRoomRent(rs.getDouble("f_roomRent"));
                room.setRoomCanNum(rs.getInt("f_roomCanNum"));
                room.setRoomHostTel(rs.getString("f_roomHostTel"));
                room.setRoomInNum(rs.getInt("f_roomInNum"));
                room.setRoomPayWay(rs.getInt("f_roomPayWay"));
                room.setRoomRentDate(rs.getObject("f_roomRentDate", LocalDate.class));
                room.setRoomStatus(rs.getBoolean("f_roomStatus"));
                room.setRoomType(rs.getString("f_roomType"));

                //4、房间和班级放入学生
                stu.setStuClass(cls);
                stu.setStuRoom(room);

                stuLst.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuLst;
    }

    @Override
    public ArrayList<StudentBean> selectStudentByClass(int classID) {
        ArrayList<StudentBean> stuLst = new ArrayList<>();
        String sql = "SELECT pk_stuID,f_stuName,f_stuGender,f_stuImage,f_stuTel,f_stuInDate,fk_classID," +
                "fk_roomID,pk_classID,f_className,f_classTeacher,f_classFoundDate from t_student join \n" +
                "(SELECT pk_roomID from t_room " +
                "WHERE pk_roomID='1') as t_rom on t_student.fk_roomID=t_rom.pk_roomID " +
                "join t_class on t_class.pk_classID=t_student.fk_classID;";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, 1)) {
            ps.setInt(1, classID);
            ResultSet rs = ps.executeQuery();
            ClassBean cls = null;
            while (rs.next()) {
                //班级
                int clsID = rs.getInt("pk_classID");
                if (cls == null || clsID != cls.getClassID()) {
                    cls = new ClassBean();
                    cls.setClassID(rs.getInt("pk_classID"));
                    cls.setClassName(rs.getString("f_className"));
                    cls.setClassTeacher(rs.getString("f_classTeacher"));
                    cls.setClassFoundDate(rs.getObject("f_classFoundDate", LocalDate.class));
                }
                //学生
                StudentBean stu = new StudentBean();
                stu.setStuID(rs.getInt("pk_stuID"));
                stu.setStuGender(rs.getBoolean("f_stuGender"));
                stu.setStuImage(rs.getString("f_stuImage"));
                stu.setStuName(rs.getString("f_stuName"));
                stu.setStuInDate(rs.getObject("f_stuInDate", LocalDate.class));
                stu.setStuTel(rs.getString("f_stuTel"));
                stu.setStuClass(cls);

                stuLst.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuLst;
    }

    @Override
    public ArrayList<StudentBean> selectStudentByRoom(int roomID) {
        return null;
    }
}
