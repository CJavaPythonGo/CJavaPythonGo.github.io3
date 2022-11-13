package com.lovo.sgproj.frame.roommanage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.lovo.netCRM.component.LovoButton;
import com.lovo.netCRM.component.LovoComboBox;
import com.lovo.netCRM.component.LovoTable;
import com.lovo.netCRM.component.LovoTxt;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.RoomBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.frame.MainFrame;
import com.lovo.sgproj.frame.classmanage.ClassDelDialog;
import com.lovo.sgproj.frame.classmanage.ClassManagePanel;
import com.lovo.sgproj.frame.studentmanage.StudentManagePanel;
import com.lovo.sgproj.frame.studentmanage.StudentShowDialog;
import com.lovo.sgproj.service.RoomServiceImp;
import com.lovo.sgproj.service.inter.RoomService;
import com.lovo.sgproj.util.LovoConvertion;
import com.lovo.sgproj.util.PageBtn;

public class RoomManagePanel extends JPanel {

    private MainFrame frame;

    private LovoTable roomTab;

    private LovoButton addRoomBtn;
    private LovoButton delRoomBtn;
    private LovoButton roomInfoBtn;
    private LovoButton roomEqpBtn;

    private JPanel queryPanel;
    private JLabel roomAddressLab;
    private JTextField roomAddressTxt;
    private LovoComboBox<String> roomStatCmb;
    private LovoComboBox<String> liveableCmb;
    private LovoComboBox<String> roomTypeCmb;
    private JButton queryBtn;
    private RoomService roomServiceImp = new RoomServiceImp();
    private PageBtn indexBtn;
    private PageBtn previousBtn;
    private PageBtn nextBtn;
    private PageBtn finalBtn;
    private PageBtn pageBtn;
    private JTextField pageTxt;
    private int count;
    ArrayList<RoomBean> roomLst = new ArrayList<>();    //房间管理
    ArrayList<RoomBean> roomLstByQuery = new ArrayList<>(); //房间查询界面

    public RoomManagePanel(MainFrame frame) {
        this.frame = frame;
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.initTable();
        this.initBtn();
        this.initQueryPanel();
    }

    public RoomService getRoomServiceImp() {
        return roomServiceImp;
    }

    private void initTable() {
        // TODO Auto-generated method stub
        this.roomTab = new LovoTable(this,
                new String[]{"房间地址", "可容纳人数", "已住人数", "房租", "房间状态", "房东", "房东电话", "房间类型"},
                new String[]{"roomAddress", "roomCanNum", "roomInNum", "roomRent", "roomStatus", "roomHost",
                        "roomHostTel", "roomGender"},// 数组中应该是对应属性的属性名
                "roomID");// 填入唯一标示属性
        this.roomTab.setSizeAndLocation(0, 0, 800, 300);
    }

    //读取所有房间显示在表格上
    public void initTableData() {
        //1、获取RoomBean集合
        roomLst = roomServiceImp.showAllRooms(this.count);
        //2、放入表格当中
        this.roomTab.updateLovoTable(roomLst);
        //3、修改某些列的显示数据
        for (int i = 0; i < roomLst.size(); i++) {
            this.roomTab.setValueAt(LovoConvertion.roomGenderToString(roomLst.get(i).isRoomGender()), i, 7);
            this.roomTab.setValueAt(LovoConvertion.roomStatusToString(roomLst.get(i).isRoomStatus()), i, 4);
        }
    }

    private void initBtn() {
        // TODO Auto-generated method stub
        this.addRoomBtn = new LovoButton("添加房间", 15, 380, this);
        this.delRoomBtn = new LovoButton("删除房间", 160, 380, this);
        this.roomInfoBtn = new LovoButton("查看房间信息", 15, 450, this);
        this.roomEqpBtn = new LovoButton("查看房间设施", 160, 450, this);

        this.addRoomBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new RoomAddDialog(RoomManagePanel.this.frame);
            }
        });

        this.delRoomBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int roomID = RoomManagePanel.this.roomTab.getKey();
                //数据有效性验证
                if (roomID == -1) {
                    JOptionPane.showMessageDialog(RoomManagePanel.this,
                            "请选择要删除的房间");
                    return;
                }
                new RoomDelDialog(RoomManagePanel.this.frame, roomID);
            }
        });

        this.roomInfoBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int roomID = RoomManagePanel.this.roomTab.getKey();
                //数据有效性验证
                if (roomID == -1) {
                    JOptionPane.showMessageDialog(RoomManagePanel.this,
                            "请选择要查看的房间");
                } else {
                    new RoomInfoDialog(RoomManagePanel.this.frame, roomID);
                }
            }
        });

        this.roomEqpBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int roomID = RoomManagePanel.this.roomTab.getKey();
                //数据有效性验证
                if (roomID == -1) {
                    JOptionPane.showMessageDialog(RoomManagePanel.this,
                            "请选择要查看的房间");
                } else {
                    new RoomEqpDialog(RoomManagePanel.this.frame, roomID);
                }
            }
        });
    }

    private void initQueryPanel() {
        // TODO Auto-generated method stub
        this.queryPanel = new JPanel();
        this.queryPanel.setBorder(BorderFactory.createTitledBorder("查询房间信息"));
        this.queryPanel.setBackground(Color.WHITE);
        this.queryPanel.setLayout(null);
        this.queryPanel.setBounds(300, 310, 480, 180);
        this.add(this.queryPanel);

        this.roomAddressLab = new JLabel("房间地址");
        this.roomAddressLab.setBounds(20, 20, 60, 20);
        this.queryPanel.add(this.roomAddressLab);
        this.roomAddressTxt = new JTextField();
        this.roomAddressTxt.setBounds(100, 20, 120, 20);
        this.queryPanel.add(this.roomAddressTxt);

        ArrayList<String> statLst = new ArrayList<String>();
        statLst.add("不限");
        statLst.add("正常");
        statLst.add("损坏");
        this.roomStatCmb = new LovoComboBox<String>("房间状态", statLst, 260, 20,
                this.queryPanel);

        ArrayList<String> liveLst = new ArrayList<String>();
        liveLst.add("不限");
        liveLst.add("可住房间");
        this.liveableCmb = new LovoComboBox<String>("可住房间", liveLst, 20, 80,
                this.queryPanel);

        ArrayList<String> typeLst = new ArrayList<String>();
        typeLst.add("不限");
        typeLst.add("男生寝室");
        typeLst.add("女生寝室");
        this.roomTypeCmb = new LovoComboBox<String>("房间类型", typeLst, 260, 80,
                this.queryPanel);

        this.queryBtn = new JButton("查找");
        this.queryBtn.setBounds(220, 130, 80, 20);
        this.queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setFlag(true);
                getRoomLstByCondition();
            }
        });
        this.queryPanel.add(this.queryBtn);
    }

    private void getRoomLstByCondition() {
        //获取查询房间
        roomLst = roomServiceImp.showAllRoomByCondition(roomAddressTxt.getText(),
                LovoConvertion.stringToRoomStatusWhenQuery(roomStatCmb.getItem()),
                LovoConvertion.stringToRoomCanIn(liveableCmb.getItem()),
                LovoConvertion.stringToRoomGenderWhenQuery(roomTypeCmb.getItem()), this.count);
        //将集合放入房间表格
        roomTab.updateLovoTable(roomLst);

        //3、修改某些列的显示数据
        for (int i = 0; i < roomLst.size(); i++) {
            roomTab.setValueAt(LovoConvertion.roomGenderToString(roomLst.get(i).isRoomGender()), i, 7);
            roomTab.setValueAt(LovoConvertion.roomStatusToString(roomLst.get(i).isRoomStatus()), i, 4);
        }
    }

}
