package com.lovo.sgproj.frame.classmanage;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.studentmanage.StudentShowDialog;
import com.lovo.sgproj.service.ClassServiceImp;
import com.lovo.sgproj.service.inter.ClassService;
import com.lovo.sgproj.test.LovoComponetFrame;
import com.lovo.sgproj.util.LovoConvertion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class ClassAddDialog extends JDialog {

    private LovoTxt classTxt;
    private LovoTxt teacherTxt;
    private LovoDate classOpenTime;
    private LovoButton addBtn;
    private LovoButton cancelBtn;
    private MainFrame frame;


    public ClassAddDialog(MainFrame frame) {
        super(frame, "添加班级", true);
        this.frame = frame;
        this.setBounds(550, 300, 300, 280);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub
        this.classTxt = new LovoTxt("班级名称", 20, 20, this);
        this.teacherTxt = new LovoTxt("带班老师", 20, 80, this);
        this.classOpenTime = new LovoDate("开班时间", 20, 140, this);
        this.addBtn = new LovoButton("添加", 40, 200, this);
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertClass();
            }
        });
        this.cancelBtn = new LovoButton("取消", 150, 200, this);
        this.cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassAddDialog.this.dispose();
            }
        });
    }

    //添加班级
    private void insertClass() {
        String className = this.classTxt.getText();
        String classTeacher = this.teacherTxt.getText();
        Date openTime = ClassAddDialog.this.classOpenTime.getDate();
        if (className.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入班级名称");
            return;
        }
        if (classTeacher.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入带班老师");
            return;
        }
        if (openTime == null) {
            JOptionPane.showMessageDialog(null, "请选择开班时间");
            return;
        }
        LocalDate classOpTime = LovoConvertion.dateToLoaclDate(openTime);
        ClassBean classBean = new ClassBean(className, classTeacher, classOpTime);
        boolean flag = frame.getCmP().getClassService().addClass(classBean);
        if (!flag) {
            JOptionPane.showMessageDialog(null, "该班级已存在");
        } else {
            JOptionPane.showMessageDialog(null, "添加班级成功");
            frame.getCmP().initTableData();
            ClassAddDialog.this.dispose();
        }
    }

}
