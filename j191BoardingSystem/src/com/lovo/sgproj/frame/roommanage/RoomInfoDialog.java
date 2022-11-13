package com.lovo.sgproj.frame.roommanage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoDate;
import com.lovo.netCRM.component.LovoLabel;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.util.LovoConvertion;

public class RoomInfoDialog extends JDialog {

    private LovoLabel roomAddressLab;
    private LovoLabel buildingTypeLab;//户型
    private LovoLabel capNumLab;//可容纳人数
    private LovoLabel inNumLab;//已住人数
    private LovoLabel rentPriceLab;
    private LovoLabel payTypeLab;
    private LovoLabel roomHostLab;//房东
    private LovoLabel hostTelLab;
    private LovoLabel roomStatusLab;//房间状态
    private LovoLabel roomTypeComb;
    private LovoLabel rentDateLab;

    private LovoTable stuTab;

    private LovoButton confirmBtn;
    private MainFrame frame;
    private int roomID;


    public RoomInfoDialog(MainFrame frame, int roomID) {
        // TODO Auto-generated constructor stub
        super(frame, "添加房间", true);
        this.frame = frame;
        this.roomID = roomID;
        this.setBounds(350, 220, 520, 450);
        this.setLayout(null);
        this.init();
        this.initTable();
        this.initTableData();
        this.setVisible(true);
    }

    private void init() {
        // TODO Auto-generated method roomb
        RoomBean room = frame.getRmP().getRoomServiceImp().getRoomByID(roomID);
        this.roomAddressLab = new LovoLabel("房间地址", room.getRoomAddress(), 20, 20, this);
        this.buildingTypeLab = new LovoLabel("房间户型", room.getRoomType(), 270, 20, this);
        this.capNumLab = new LovoLabel("可容纳人数", "" + room.getRoomCanNum(), 20, 60, this);
        this.inNumLab = new LovoLabel("已住人数", "" + room.getRoomInNum(), 270, 60, this);
        this.rentPriceLab = new LovoLabel("房租", "" + room.getRoomRent(), 20, 100, this);
        this.payTypeLab = new LovoLabel("支付方式", LovoConvertion.roomPayWayToString(room.getRoomPayWay()),
                270, 100, this);
        this.roomStatusLab = new LovoLabel("房间状态", LovoConvertion.roomStatusToString(room.isRoomStatus()),
                20, 140, this);
        this.roomTypeComb = new LovoLabel("房间类型", LovoConvertion.genderToString(room.isRoomGender()),
                270, 140, this);
        this.rentDateLab = new LovoLabel("租房日期", room.getRoomRentDate().toString(), 20, 180, this);

        this.confirmBtn = new LovoButton("确定", 220, 390, this);
        this.confirmBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                RoomInfoDialog.this.dispose();
            }
        });
    }

    private void initTable() {
        // TODO Auto-generated method stub
        this.stuTab = new LovoTable(this,
                new String[]{"学生姓名", "所在班级", "联系电话"},
                new String[]{"stuName", "stuClass.className", "stuTel"},//数组中应该是对应属性的属性名
                "stuID");//填入唯一标示属性
        this.stuTab.setSizeAndLocation(20, 220, 480, 150);
    }

    private void initTableData() {
        //获取该房间学生集合
        RoomBean room = frame.getRmP().getRoomServiceImp().getRoomByID(roomID);
        if (room.getRoomStuLst().size() != 0) {
            ArrayList<StudentBean> stuLst = room.getRoomStuLst();
            //添加学生集合到学生表格
            this.stuTab.updateLovoTable(stuLst);
        }
    }
}
