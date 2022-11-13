package com.lovo.sgproj.frame.studentmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoFileChooser;
import com.lovo.netCRM.component.LovoRadioButton;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.service.ClassServiceImp;
import com.lovo.sgproj.service.RoomServiceImp;
import com.lovo.sgproj.service.inter.ClassService;
import com.lovo.sgproj.service.inter.RoomService;
import com.lovo.sgproj.util.LovoConvertion;

public class StudentInDialog extends JDialog {

    private LovoTxt nameTxt;

    private LovoRadioButton genderBtn;

    private LovoComboBox<ClassBean> classCmb;// 后期需要把String改为班级Bean

    private LovoComboBox<RoomBean> roomCmb;// 后期需要把String改为房间Bean

    private LovoFileChooser headPic;

    private LovoTxt stuTelTxt;

    private LovoDate stuInTime;

    private LovoButton addBtn;

    private LovoButton cancelBtn;
    private ClassService classService = new ClassServiceImp();
    private RoomService roomService = new RoomServiceImp();
    private ArrayList<RoomBean> roomLst = new ArrayList<>();
    ArrayList<ClassBean> classLst = new ArrayList<>();
    private MainFrame frame;

    public StudentInDialog(MainFrame frame) {
        super(frame, "添加学生", true);
        this.frame = frame;
        this.setBounds(350, 250, 500, 400);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub
        this.nameTxt = new LovoTxt("姓名", 20, 40, this);
        this.genderBtn = new LovoRadioButton("性别", new String[]{"男", "女"},
                20, 120, this) {
            @Override
            public void addListener(String gender) {
                if (gender.equals("男")) {
                    roomLst = roomService.showLiveableRoomsByGender(true, 0);
                    roomCmb.setList(roomLst);
                } else if (gender.equals("女")) {
                    roomLst = roomService.showLiveableRoomsByGender(false, 0);
                    roomCmb.setList(roomLst);
                }
            }
        };


        classLst = classService.showAllClasses(-1);
        this.classCmb = new LovoComboBox<>("班级", classLst, 20, 200, this);

        roomLst = roomService.showLiveableRoomsByGender(true, 0);
        this.roomCmb = new LovoComboBox<>("房间", roomLst, 20, 260, this);

        this.headPic = new LovoFileChooser(this);
        this.headPic.setBounds(320, 20, 100, 150);

        this.stuTelTxt = new LovoTxt("联系电话", 250, 200, this);
        this.stuInTime = new LovoDate("入住时间", 250, 260, this);

        this.addBtn = new LovoButton("添加", 120, 320, this);
        this.cancelBtn = new LovoButton("取消", 270, 320, this);

        this.addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (nameTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入学生姓名");
                    return;
                }

                String headPicPath = headPic.getFilePath();
                String newheadPicPath = "";
                if (headPicPath == null) {
                    newheadPicPath = "image\\\\defaultHead.JPG";
                } else {
                    String[] filePathArr = headPicPath.split("\\.");
                    newheadPicPath = "image\\\\" + new Date().getTime() + "." + filePathArr[filePathArr.length - 1];
                    insertImage(headPicPath, newheadPicPath);
                }

                if (stuInTime.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "请选择入住时间");
                    return;
                }

                //电话
                String stuTel = "";
                if (stuTelTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入学生电话号码");
                    stuTelTxt.setText("");
                    stuTelTxt.requestFocus();
                    return;
                } else if (stuTelTxt.getText().matches("1\\d{10}")) {
                    stuTel = stuTelTxt.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "电话号码必须为1开头的11位数字");
                    stuTelTxt.setText("");
                    stuTelTxt.requestFocus();
                    return;
                }
                StudentBean stu = new StudentBean(nameTxt.getText(),
                        LovoConvertion.stringToGender(genderBtn.getItem()), newheadPicPath, stuTel,
                        LovoConvertion.dateToLoaclDate(stuInTime.getDate()), classCmb.getItem(),
                        roomCmb.getItem());

                frame.getSmP().getStuService().studentIn(stu);
                frame.getSmP().initTableData();
                StudentInDialog.this.dispose();
            }
        });

        this.cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                StudentInDialog.this.dispose();
            }
        });
    }

    //copy image
    private void insertImage(String oldPath, String newPath) {
        try (FileInputStream finput = new FileInputStream(oldPath);
             FileOutputStream fout = new FileOutputStream(newPath)) {
            byte[] tmp = new byte[1024];
            int length = 0;
            while ((length = finput.read(tmp)) != -1) {
                fout.write(tmp, 0, length);
                fout.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
