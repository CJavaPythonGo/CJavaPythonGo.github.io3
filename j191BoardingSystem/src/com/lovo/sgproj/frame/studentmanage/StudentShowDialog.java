package com.lovo.sgproj.frame.studentmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.service.StudentServiceImp;
import com.lovo.sgproj.service.inter.StudentService;
import com.lovo.sgproj.util.LovoConvertion;

public class StudentShowDialog extends JDialog {

    private LovoLabel nameLab;
    private LovoLabel genderLab;
    private LovoLabel classLab;
    private LovoLabel roomLab;
    private JLabel headPicLab;
    private LovoLabel stuTelLab;
    private LovoLabel stuInTimeLab;
    private LovoButton okBtn;

    private int stuID;

    private StudentService stuService = new StudentServiceImp();


    public StudentShowDialog(MainFrame frame, int stuID) {
        // TODO Auto-generated constructor stub
        super(frame, "学生详细信息", true);
        this.stuID = stuID;
        this.setBounds(350, 250, 500, 400);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub

        StudentBean stu = stuService.showStudentByID(this.stuID);

        this.nameLab = new LovoLabel("姓名", stu.getStuName(), 20, 40, this);
        this.genderLab = new LovoLabel("性别", LovoConvertion.genderToString(stu.isStuGender()),
                20, 120, this);
        this.classLab = new LovoLabel("班级", stu.getStuClass().getClassName(), 20, 200, this);
        this.roomLab = new LovoLabel("房间", stu.getStuRoom().getRoomAddress(), 20, 260, this);

        this.headPicLab = new JLabel(new ImageIcon(stu.getStuImage()));
        this.headPicLab.setBounds(300, 40, 73, 106);
        this.add(this.headPicLab);

        this.stuTelLab = new LovoLabel("联系电话", stu.getStuTel(), 250, 200, this);
        this.stuInTimeLab = new LovoLabel("入住时间", stu.getStuInDate().toString(), 250, 260,
                this);

        this.okBtn = new LovoButton("确定", 180, 320, this);
        this.okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                StudentShowDialog.this.dispose();
            }
        });
    }

}
