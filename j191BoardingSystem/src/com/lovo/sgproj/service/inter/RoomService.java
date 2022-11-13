package com.lovo.sgproj.service.inter;

import com.lovo.sgproj.bean.RoomBean;

import java.util.ArrayList;

public interface RoomService {
    /*
        方法调用处：
            点击"学生入住"按钮，弹出StudentInDialog;
            点击StudentInDialog上的性别单选框;
            点击"学生换房"按钮，弹出StudentChgDialog;
        参数：在第一和第二调用处，roomID固定传值为0；
             在第三处roomID传换房学生的原房间ID。
        返回：RoomBean无需关联学生
        异常：
     */
    public ArrayList<RoomBean> showLiveableRoomsByGender(boolean gender,int roomID);

    /*
        方法调用处：
            点击"房间管理"菜单/需要刷新列表的时候
        参数：
        返回：RoomBean无需关联学生
        异常：
     */
    public ArrayList<RoomBean> showAllRooms(int count);

    /*
        方法调用处：
            点击RoomAddDialog的"添加"按钮
        参数：房间已住人数和房间状态使用数据库默认值
        返回：如果房间重名，则不执行添加返回false；
        异常：
     */
    public boolean addRoom(RoomBean roomBean);

    /*
        方法调用处：
            点击RoomManagePanel的"删除房间"按钮
        参数：
            被选中的房间ID
        返回：
            如果房间还有学生则返回false，不能删除；
            否则完成删除返回true。
        异常：
     */
    public boolean removeRoom(int roomID);

    /*
        方法调用处：
            点击RoomManagePanel的"查看房间信息"按钮，初始化RoomInfoDialog的时候
        参数：
            被选中的房间ID
        返回：
            RoomBean需要关联学生对象
        异常：
     */
    public RoomBean getRoomByID(int roomID);

    /*
        方法调用处：
            点击RoomManagePanel的"查找"按钮
        参数：
            roomAddress -- 房间地址
            roomStatus -- -1代表不限、0代表损坏、1代表正常
            roomLiveable -- true代表可住、false代表不限
            roomGender -- -1代表不限、0代表女、1代表男
        返回：
            RoomBean不需要关联学生对象
        异常：
     */
    public ArrayList<RoomBean> showAllRoomByCondition(String roomAddress,int roomStatus,boolean liveable,int roomGender,int count);

}
