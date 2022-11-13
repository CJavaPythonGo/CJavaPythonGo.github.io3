package com.lovo.sgproj.frame.studentmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.service.RoomServiceImp;
import com.lovo.sgproj.service.inter.RoomService;
import com.lovo.sgproj.util.LovoConvertion;

public class StudentChgDialog extends JDialog {

    private LovoLabel nameLab;
    private LovoLabel genderLab;
    private LovoLabel classLab;
    private LovoLabel oldRoomLab;
    private LovoComboBox<RoomBean> newRoomCmb;//后期换做房间Bean

    private LovoButton chgBtn;
    private LovoButton cancelBtn;
    private MainFrame frame;
    private int stuID;
    private ArrayList<RoomBean> roomLst = new ArrayList<>();
    private RoomService roomService = new RoomServiceImp();


    public StudentChgDialog(MainFrame frame, int stuID) {
        super(frame, "学生换房", true);
        this.frame = frame;
        this.stuID = stuID;
        this.setBounds(450, 350, 450, 280);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub

        StudentBean stu = frame.getSmP().getStuService().showStudentByID(this.stuID);

        this.nameLab = new LovoLabel("姓名", stu.getStuName(), 20, 20, this);
        this.genderLab = new LovoLabel("性别", LovoConvertion.genderToString(stu.isStuGender()),
                220, 20, this);
        this.classLab = new LovoLabel("班级", stu.getStuClass().getClassName(), 20, 80, this);
        this.oldRoomLab = new LovoLabel("原房间", stu.getStuRoom().getRoomAddress(), 220, 80,
                this);


        roomLst = roomService.showLiveableRoomsByGender(stu.isStuGender(), stu.getStuRoom().getRoomID());
        this.newRoomCmb = new LovoComboBox<RoomBean>("新房间", roomLst, 20, 140, this);


        this.chgBtn = new LovoButton("确定", 120, 200, this);
        this.cancelBtn = new LovoButton("取消", 270, 200, this);
        this.chgBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                //更新学生信息，旧房间-1，新房间+1
                frame.getSmP().getStuService().studentChg(stuID, stu.getStuRoom().getRoomID(),
                        newRoomCmb.getItem().getRoomID());
                frame.getSmP().initTableData();
                StudentChgDialog.this.dispose();
            }
        });

        this.cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                StudentChgDialog.this.dispose();
            }
        });

    }
}
