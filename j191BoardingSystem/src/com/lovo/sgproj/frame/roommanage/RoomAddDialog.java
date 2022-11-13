package com.lovo.sgproj.frame.roommanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.classmanage.ClassAddDialog;
import com.lovo.sgproj.frame.studentmanage.StudentInDialog;
import com.lovo.sgproj.util.LovoConvertion;

public class RoomAddDialog extends JDialog {

    private LovoTxt roomAddressTxt;
    private LovoTxt buildingTypeTxt;//户型
    private LovoTxt capNumTxt;//可容纳人数
    private LovoComboBox<String> roomTypeComb;
    private LovoTxt rentPriceTxt;
    private LovoComboBox<String> payTypeTxt;
    private LovoTxt roomHostTxt;//房东
    private LovoTxt hostTelTxt;
    private LovoDate rentDate;

    private LovoButton addBtn;
    private LovoButton cancelBtn;
    private MainFrame frame;


    public RoomAddDialog(MainFrame frame) {
        // TODO Auto-generated constructor stub
        super(frame, "添加房间", true);
        this.frame = frame;
        this.setBounds(350, 250, 520, 400);
        this.setLayout(null);
        this.init();

        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method stub
        this.roomAddressTxt = new LovoTxt("房间地址", 20, 20, this);
        this.buildingTypeTxt = new LovoTxt("房间户型", 270, 20, this);
        this.capNumTxt = new LovoTxt("可容纳人数", 20, 80, this);

        ArrayList<String> roomTypeLst = new ArrayList<String>();
        roomTypeLst.add("男生寝室");
        roomTypeLst.add("女生寝室");
        this.roomTypeComb = new LovoComboBox<String>("房间类型", roomTypeLst, 270, 80, this);

        this.rentPriceTxt = new LovoTxt("房租", 20, 140, this);

        ArrayList<String> roomPayWayLst = new ArrayList<>();
        roomPayWayLst.add("月付");
        roomPayWayLst.add("季付");
        roomPayWayLst.add("半年付");
        roomPayWayLst.add("年付");
        this.payTypeTxt = new LovoComboBox<String>("支付方式", roomPayWayLst, 270, 140, this);
        this.roomHostTxt = new LovoTxt("房东", 20, 200, this);
        this.hostTelTxt = new LovoTxt("房东电话", 270, 200, this);


        this.rentDate = new LovoDate("租房日期", 270, 260, this);

        this.addBtn = new LovoButton("添加", 120, 320, this);
        this.cancelBtn = new LovoButton("取消", 270, 320, this);
        this.addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (roomAddressTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入房间地址");
                    return;
                }
                String roomAddress = roomAddressTxt.getText();//地址
                if (buildingTypeTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入房间户型");
                    buildingTypeTxt.setText("");
                    buildingTypeTxt.requestFocus();
                    return;
                }
                String buildingType = buildingTypeTxt.getText();//户型

                int capNum = 0;
                if (capNumTxt.getText().matches("[1-5]+\\d*")) {
                    capNum = Integer.parseInt(capNumTxt.getText());//可容纳人数
                } else {
                    JOptionPane.showMessageDialog(null, "请输入房间可容纳人数");
                    capNumTxt.setText("");
                    capNumTxt.requestFocus();
                    return;
                }
                boolean roomGender = LovoConvertion.stringToRoomGender(roomTypeComb.getItem());

                double rentPrice = 0;
                if (rentPriceTxt.getText().matches("[1-9]?[\\.]?\\d+")) {
                    rentPrice = Double.parseDouble(rentPriceTxt.getText());//房租
                } else {
                    JOptionPane.showMessageDialog(null, "房租只能输入大于0的数字");
                    return;
                }

                int payType = LovoConvertion.stringToRoomPayWay(payTypeTxt.getItem());
                if (roomHostTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入房东名字");
                    return;
                }
                String roomHost = roomHostTxt.getText();

                String hostTel = "";
                if (hostTelTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入房东电话");
                    hostTelTxt.setText("");
                    hostTelTxt.requestFocus();
                    return;
                } else if (hostTelTxt.getText().matches("1\\d{10}")) {
                    hostTel = hostTelTxt.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "电话只能是以1开头的11位数字");
                    hostTelTxt.setText("");
                    hostTelTxt.requestFocus();
                    return;
                }
                if (RoomAddDialog.this.rentDate.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "租房日期不能为空");
                    return;
                }
                //租房日期

                LocalDate rentThisDate = LovoConvertion.dateToLoaclDate(RoomAddDialog.this.rentDate.getDate());
                RoomBean roomBean = new RoomBean(roomAddress, buildingType, capNum, roomGender, rentPrice, payType,
                        roomHost, hostTel, rentThisDate);
                boolean flag = frame.getRmP().getRoomServiceImp().addRoom(roomBean);
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "该房间已存在");
                    return;
                } else {
                    frame.getRmP().initTableData();
                    RoomAddDialog.this.dispose();
                }
            }
        });

        this.cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                RoomAddDialog.this.dispose();
            }
        });

    }

}
