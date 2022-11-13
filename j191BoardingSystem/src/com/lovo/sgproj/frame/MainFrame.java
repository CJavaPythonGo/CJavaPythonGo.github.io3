package com.lovo.sgproj.frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.lovo.netCRM.component.LovoTree;
import com.lovo.netCRM.component.LovoTreeNode;
import com.lovo.sgproj.frame.classmanage.ClassManagePanel;
import com.lovo.sgproj.frame.roommanage.RoomManagePanel;
import com.lovo.sgproj.frame.studentmanage.StudentManagePanel;

public class MainFrame extends JFrame {

    private Container contentP;

    private LovoTree tree;

    private LovoTreeNode rootNode;//根节点
    private LovoTreeNode resourceMagNode;//资料管理
    private LovoTreeNode studentNode;
    private LovoTreeNode classNode;
    private LovoTreeNode roomMagNode;
    private LovoTreeNode roomNode;


    private JPanel cardPanel;
    private WelcomePanel wp;
    private StudentManagePanel smP;
    private ClassManagePanel cmP;
    private RoomManagePanel rmP;
    private boolean flag;//flag = false则为学生管理界面信息, 否则为学生查询界面信息


    public MainFrame() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setIconImage(tk.createImage("image/hp.JPG"));
        this.setTitle("宿舍管理系统");
        this.setLocation(140, 190);
        this.setSize(1000, 580);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initTree();
        this.initCard();

        this.setVisible(true);
    }

    private void initTree() {
        // TODO Auto-generated method stub
        this.contentP = this.getContentPane();
//		this.contentP.setBackground(Color.WHITE);
        this.contentP.setLayout(null);

        this.rootNode = new LovoTreeNode("宿管系统管理");
        this.resourceMagNode = new LovoTreeNode("资料管理");
        this.studentNode = new LovoTreeNode("学生管理") {
            @Override
            public void addListener() {
                // TODO Auto-generated method stub
//                flag = false;
                MainFrame.this.smP.initTableData();
                CardLayout cl = (CardLayout) MainFrame.this.cardPanel.getLayout();
                cl.show(MainFrame.this.cardPanel, "student");
            }
        };

        this.classNode = new LovoTreeNode("班级管理") {
            @Override
            public void addListener() {
                // TODO Auto-generated method stub
                MainFrame.this.cmP.initTableData();
                CardLayout cl = (CardLayout) MainFrame.this.cardPanel.getLayout();
                cl.show(MainFrame.this.cardPanel, "class");
            }
        };
        this.roomMagNode = new LovoTreeNode("房间管理");
        this.roomNode = new LovoTreeNode("房间信息") {
            @Override
            public void addListener() {
                // TODO Auto-generated method stub
                MainFrame.this.rmP.initTableData();
                CardLayout cl = (CardLayout) MainFrame.this.cardPanel.getLayout();
                cl.show(MainFrame.this.cardPanel, "room");
            }
        };

        //第一层
        this.rootNode.add(this.resourceMagNode);
        this.rootNode.add(this.roomMagNode);

        //第二层
        this.resourceMagNode.add(this.studentNode);
        this.resourceMagNode.add(this.classNode);

        this.roomMagNode.add(this.roomNode);


        this.tree = new LovoTree(this.rootNode);
        this.tree.setFont(new Font("微软雅黑", Font.ITALIC, 20));
        this.tree.setBounds(15, 20, 150, 505);
        this.contentP.add(this.tree);
    }

    private void initCard() {
        // TODO Auto-generated method stub
        this.cardPanel = new JPanel();
        this.cardPanel.setLayout(new CardLayout());

        this.wp = new WelcomePanel();
        this.smP = new StudentManagePanel(this);
        this.cmP = new ClassManagePanel(this);
        this.rmP = new RoomManagePanel(this);

        this.cardPanel.add(this.wp, "welcome");
        this.cardPanel.add(this.smP, "student");
        this.cardPanel.add(this.cmP, "class");
        this.cardPanel.add(this.rmP, "room");


        this.cardPanel.setBounds(170, 20, 800, 505);
        this.contentP.add(this.cardPanel);

    }

    public Container getContentP() {
        return contentP;
    }

    public void setContentP(Container contentP) {
        this.contentP = contentP;
    }

    public StudentManagePanel getSmP() {
        return smP;
    }

    public void setSmP(StudentManagePanel smP) {
        this.smP = smP;
    }

    public ClassManagePanel getCmP() {
        return cmP;
    }

    public void setCmP(ClassManagePanel cmP) {
        this.cmP = cmP;
    }

    public RoomManagePanel getRmP() {
        return rmP;
    }

    public void setRmP(RoomManagePanel rmP) {
        this.rmP = rmP;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
