package com.lovo.sgproj.frame.roommanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.classmanage.ClassDelDialog;

public class RoomDelDialog extends JDialog {

    private JLabel infoLab;
    private LovoButton comfirmBtn;
    private MainFrame frame;
    private int roomID;

    public RoomDelDialog(MainFrame frame, int roomID) {
        super(frame, "删除房间", true);
        this.frame = frame;
        this.roomID = roomID;
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
                boolean flag = frame.getRmP().getRoomServiceImp().removeRoom(roomID);
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "该房间还有学生，不能删除");
                    RoomDelDialog.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "删除成功");
                    frame.getRmP().initTableData();
                    RoomDelDialog.this.dispose();
                }
            }
        });
    }

}
