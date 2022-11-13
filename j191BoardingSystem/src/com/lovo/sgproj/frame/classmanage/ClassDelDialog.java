package com.lovo.sgproj.frame.classmanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.studentmanage.StudentOutDialog;

public class ClassDelDialog extends JDialog {

    private JLabel infoLab;
    private LovoButton comfirmBtn;
    private MainFrame frame;
    private int clsID;


    public ClassDelDialog(MainFrame frame, int clsID) {
        super(frame, "删除班级", true);
        this.frame = frame;
        this.clsID = clsID;
        this.setBounds(550, 400, 300, 150);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub
        this.infoLab = new JLabel("确定删除该班级？");
        this.infoLab.setBounds(50, 30, 200, 30);
        this.add(this.infoLab);

        this.comfirmBtn = new LovoButton("确定", 100, 80, this);

        this.comfirmBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                boolean flag = frame.getCmP().getClassService().removeClass(clsID);
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "该班还有学生,不能删除");
                    ClassDelDialog.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "删除班级成功");
                    frame.getCmP().initTableData();
                    ClassDelDialog.this.dispose();
                }
            }
        });
    }

}
