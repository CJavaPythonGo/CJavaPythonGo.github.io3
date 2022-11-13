package com.lovo.sgproj.frame.classmanage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.studentmanage.StudentManagePanel;
import com.lovo.sgproj.frame.studentmanage.StudentShowDialog;
import com.lovo.sgproj.service.ClassServiceImp;
import com.lovo.sgproj.service.inter.ClassService;
import com.lovo.sgproj.util.PageBtn;

public class ClassManagePanel extends JPanel {
    private MainFrame frame;

    private LovoTable classTab;

    private LovoButton classInBtn;
    private LovoButton classOutBtn;
    private PageBtn indexBtn;
    private PageBtn previousBtn;
    private PageBtn nextBtn;
    private PageBtn finalBtn;
    private PageBtn pageBtn;
    private JTextField pageTxt;

    // 联接班级管理业务层
    private ClassService classService = new ClassServiceImp();
    private ArrayList<ClassBean> clsLst = new ArrayList<>();
    private int count;

    public ClassManagePanel(MainFrame frame) {
        this.frame = frame;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.initTable();
        this.initBtn();
    }

    public ClassService getClassService() {
        return classService;
    }

    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    private void initTable() {
        // TODO Auto-generated method stub
        this.classTab = new LovoTable(this,
                new String[]{"班级名称", "开班时间", "带班老师"},
                new String[]{"className", "classFoundDate", "classTeacher"},//数组中应该是对应属性的属性名
                "classID");//填入唯一标示属性
        this.classTab.setSizeAndLocation(20, 0, 750, 300);
    }

    //读取所有班级显示在学生表格上
    public void initTableData() {
        //1、获取classBean集合
        clsLst = classService.showAllClasses(this.count);
        //2、放入表格当中
        this.classTab.updateLovoTable(clsLst);
    }

    private void initBtn() {
        // TODO Auto-generated method stub
        this.classInBtn = new LovoButton("添加班级", 220, 380, this);
        this.classOutBtn = new LovoButton("删除班级", 450, 380, this);

        this.classInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new ClassAddDialog(frame);
            }
        });

        this.classOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int clsID = ClassManagePanel.this.classTab.getKey();
                //数据有效性验证
                if (clsID == -1) {
                    JOptionPane.showMessageDialog(ClassManagePanel.this,
                            "请选择要删除的班级");
                    return;
                }
                new ClassDelDialog(ClassManagePanel.this.frame, clsID);
            }
        });

    }

}
