package com.lovo.sgproj.frame.roommanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.sgproj.bean.RecordBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.service.RecordServiceImp;
import com.lovo.sgproj.service.inter.RecordService;
import com.lovo.sgproj.util.LovoConvertion;

public class RoomEqpDialog extends JDialog {

    private LovoTable recordTab;

    private LovoDate reportDate;// 报损日期
    private JLabel descriptLab;
    private JTextArea descriptArea;

    private LovoButton addRecordBtn;
    private LovoButton processBtn;
    private LovoButton exitBtn;
    private MainFrame frame;
    private int roomID;
    private RecordService recordService = new RecordServiceImp();

    public RoomEqpDialog(MainFrame frame, int roomID) {
        // TODO Auto-generated constructor stub
        super(frame, "房间设施维护", true);
        this.frame = frame;
        this.roomID = roomID;
        this.setBounds(350, 250, 420, 400);
        this.setLayout(null);
        this.initTable();
        this.init();
        this.initBtn();
        this.initTableData();
        this.setVisible(true);
    }

    private void initTable() {
        this.recordTab = new LovoTable(this,
                new String[]{"损坏描述", "报损日期", "是否解决"},
                new String[]{"recDescription", "recReportDate", "recSoluted"},// 数组中应该是对应属性的属性名
                "recID");// 填入唯一标示属性
        this.recordTab.setSizeAndLocation(20, 10, 360, 150);
    }

    private void initTableData() {
        //获取损坏记录集合
        ArrayList<RecordBean> rdLst = recordService.showRecordByRoom(roomID);
        //添加该集合到记录表格
        this.recordTab.updateLovoTable(rdLst);
        //修改显示
        for (int i = 0; i < rdLst.size(); i++) {
            this.recordTab.setValueAt(LovoConvertion.roomRecordToString(rdLst.get(i).isRecSoluted()), i, 2);
        }
    }

    private void init() {
        // TODO Auto-generated method stub
        this.reportDate = new LovoDate("报损日期", 80, 180, this);

        this.descriptLab = new JLabel("损坏描述");
        this.descriptLab.setBounds(80, 220, 60, 30);
        this.add(this.descriptLab);

        this.descriptArea = new JTextArea();
        JScrollPane jsp = new JScrollPane(this.descriptArea);
        jsp.setBounds(150, 220, 220, 100);
        this.add(jsp);
    }

    private void initBtn() {
        // TODO Auto-generated method stub
        this.addRecordBtn = new LovoButton("添加设施损坏记录", 10, 330, this);
        this.addRecordBtn.setSize(120, 20);

        this.processBtn = new LovoButton("处理已损坏设备", 145, 330, this);
        this.processBtn.setSize(120, 20);

        this.exitBtn = new LovoButton("退出", 280, 330, this);
        this.exitBtn.setSize(120, 20);

        //添加设施损坏记录
        this.addRecordBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (reportDate.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "请选择报损日期");
                    return;
                }
                LocalDate recDate = LovoConvertion.dateToLoaclDate(reportDate.getDate());

                //损坏描述
                if (descriptArea.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请填写损坏描述");
                    return;
                }
                recordService.addRecord(new RecordBean(descriptArea.getText(), recDate, false), roomID);
                RoomEqpDialog.this.dispose();
            }
        });

        //处理已损坏设备
        this.processBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int recordID = recordTab.getKey();
                //数据有效性验证
                if (recordID == -1) {
                    JOptionPane.showMessageDialog(RoomEqpDialog.this, "请选择要处理的记录");
                } else {
                    recordService.processRecord(recordID);
                    frame.getRmP().initTableData();
                    RoomEqpDialog.this.dispose();
                }

            }
        });

        //退出
        this.exitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                RoomEqpDialog.this.dispose();
            }
        });
    }

}
