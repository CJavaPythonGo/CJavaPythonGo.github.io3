package com.lovo.sgproj.frame.studentmanage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.service.StudentServiceImp;
import com.lovo.sgproj.service.inter.StudentService;
import com.lovo.sgproj.util.LovoConvertion;
import com.lovo.sgproj.util.PageBtn;

public class StudentManagePanel extends JPanel {

    private LovoTable stuTab;

    private LovoButton stuInBtn;
    private LovoButton stuOutBtn;
    private LovoButton stuChgBtn;
    private LovoButton stuInfoBtn;

    private JPanel queryPanel;
    private JLabel nameLab;
    private JLabel classLab;
    private JLabel roomLab;
    private JTextField nameTxt;
    private JTextField classTxt;
    private JTextField roomTxt;

    private JButton queryBtn;

    private MainFrame frame;
    ArrayList<StudentBean> stuLst = new ArrayList<>();//学生管理界面
    ArrayList<StudentBean> stuLstByQuery = new ArrayList<>();//学生查询界面

    //关联学生业务层接口
    private StudentService stuService = new StudentServiceImp();

    public StudentManagePanel(MainFrame frame) {
        this.frame = frame;
        this.stuLst = this.stuService.showAllStudents();
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.initTable();
        this.initBtn();
        this.initQueryPanel();

    }

    public StudentService getStuService() {
        return stuService;
    }

    private void initTable() {
        // TODO Auto-generated method stub
        this.stuTab = new LovoTable(this,
                new String[]{"学生姓名", "性别", "班级", "所在房间", "联系电话"},
                new String[]{"stuName", "stuGender", "stuClass.className", "stuRoom.roomAddress", "stuTel"},
                // 数组中应该是对应属性的属性名
                "stuID");// 填入唯一标示属性
        this.stuTab.setSizeAndLocation(20, 0, 750, 300);

    }

    //读取所有学生显示在学生表格上
    public void initTableData() {
        //1、获取StudentBean集合
        stuLst = this.stuService.showAllStudents();

        //2、放入到表格当中
        this.stuTab.updateLovoTable(stuLst);

        //3、修改某些列的显示数据
        for (int i = 0; i < stuLst.size(); i++) {
            this.stuTab.setValueAt(LovoConvertion.genderToString(stuLst.get(i).isStuGender()), i, 1);
        }
    }

    private void initBtn() {
        // TODO Auto-generated method stub
        this.stuInBtn = new LovoButton("学生入住", 80, 380, this);
        this.stuOutBtn = new LovoButton("学生退房", 240, 380, this);
        this.stuChgBtn = new LovoButton("学生换房", 80, 450, this);
        this.stuInfoBtn = new LovoButton("学生详细信息", 240, 450, this);

        this.stuInBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new StudentInDialog(StudentManagePanel.this.frame);
            }
        });

        this.stuOutBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int stuID = StudentManagePanel.this.stuTab.getKey();
                if (stuID == -1) {
                    JOptionPane.showMessageDialog(StudentManagePanel.this,
                            "请选择要退房的学生");
                } else {
                    new StudentOutDialog(StudentManagePanel.this.frame, stuID);
                }
            }
        });

        this.stuChgBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int stuID = StudentManagePanel.this.stuTab.getKey();
                if (stuID == -1) {
                    JOptionPane.showMessageDialog(StudentManagePanel.this,
                            "请选择要换房的学生");
                } else {
                    new StudentChgDialog(StudentManagePanel.this.frame, stuID);
                }
            }
        });

        this.stuInfoBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int stuID = StudentManagePanel.this.stuTab.getKey();
                if (stuID == -1) {
                    JOptionPane.showMessageDialog(StudentManagePanel.this,
                            "请选择要查看的学生");
                } else {
                    new StudentShowDialog(StudentManagePanel.this.frame, stuID);
                }
            }
        });

    }

    private void initQueryPanel() {
        // TODO Auto-generated method stub
        this.queryPanel = new JPanel();
        this.queryPanel.setBorder(BorderFactory.createTitledBorder("查询学生信息"));
        this.queryPanel.setBackground(Color.WHITE);
        this.queryPanel.setLayout(null);
        this.queryPanel.setBounds(380, 310, 300, 180);
        this.add(this.queryPanel);

        // 标签
        this.nameLab = new JLabel("姓名");
        this.nameLab.setBounds(40, 20, 30, 30);
        this.queryPanel.add(this.nameLab);
        this.classLab = new JLabel("班级");
        this.classLab.setBounds(40, 60, 30, 30);
        this.queryPanel.add(this.classLab);
        this.roomLab = new JLabel("房间");
        this.roomLab.setBounds(40, 100, 30, 30);
        this.queryPanel.add(this.roomLab);

        // 文本框
        this.nameTxt = new JTextField();
        this.nameTxt.setBounds(80, 27, 140, 20);
        this.queryPanel.add(this.nameTxt);
        this.classTxt = new JTextField();
        this.classTxt.setBounds(80, 67, 140, 20);
        this.queryPanel.add(this.classTxt);
        this.roomTxt = new JTextField();
        this.roomTxt.setBounds(80, 107, 140, 20);
        this.queryPanel.add(this.roomTxt);

        // 查询按钮
        this.queryBtn = new JButton("查询");
        this.queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//				count=0;
//				frame.setFlag(true);
                queryStuLstByCondition();
            }
        });
        this.queryBtn.setBounds(200, 140, 80, 20);
        this.queryPanel.add(this.queryBtn);
    }

    private void queryStuLstByCondition() {

        //1、获取StudentBean集合
        stuLstByQuery = stuService.showAllStudentsByCondition(this.nameTxt.getText(), this.classTxt.getText(),
                this.roomTxt.getText());
        //2、放入到表格当中
        this.stuTab.updateLovoTable(stuLstByQuery);

        //3、修改某些列的显示数据
        for (int i = 0; i < stuLstByQuery.size(); i++) {
            this.stuTab.setValueAt(LovoConvertion.genderToString(stuLstByQuery.get(i).isStuGender()),
                    i, 1);
        }
    }

}
