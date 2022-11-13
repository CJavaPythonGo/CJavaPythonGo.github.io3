package com.lovo.sgproj.test;


import com.lovo.netCRM.component.*;
import com.lovo.sgproj.bean.ClassBean;
import com.lovo.sgproj.bean.StudentBean;
import com.lovo.sgproj.util.LovoConvertion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LovoComponetFrame extends JFrame {

    private Container contentp;

    private LovoTable stuTab; //表格控件
    private LovoButton tabBtn;//表格操作按钮

    private LovoFileChooser headChos;//文件选择器
    private LovoButton fileBtn;//头像获取按钮

    private LovoDate lovoDate;//日历控件
    private LovoButton dateBtn;//日历获取按钮

    private LovoComboBox<StudentBean> stuComb;//下拉框
    private LovoButton combBtn;//下拉选项获取

    private LovoRadioButton genderBtn;//单选框


    public LovoComponetFrame(){
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("朗沃控件测试窗体");

        this.addContent();

        this.setVisible(true);
    }

    private void addContent() {
        this.contentp = this.getContentPane();
        this.contentp.setLayout(null);
        // 表格演示：
        /*
            四个参数：
            1、传入LovoTable所在的容器 --- 源代码中已经写好，不用动
            2、String[],代表表格的头部信息 --- 源代码中已经写好，不用动
            3、String[],代表放入表格的Bean对象的对应属性名,顺序要和第二个参数对应
               --- 在源代码中只有一个new String[]{}，自己要往里面放属性名;
            4、String，代表放入表格的Bean对象中主键属性的名字。--- 源代码中只有""，需要自己加入
         */
        this.stuTab = new LovoTable(this.contentp,
                new String[]{"学生姓名","性别","班级"},
                new String[]{"stuName","stuGender","stuClass.className"},
                "stuID");
        //控制表格的位置和大小 --- 源代码已经确定
        this.stuTab.setSizeAndLocation(20, 0, 500, 100);
        //做假数据 --- 源代码中需要调用Service的方法获取真实数据
        ArrayList<StudentBean> stuLst = new ArrayList<>();
        stuLst.add(new StudentBean(1,"张思炀",true,new ClassBean("J191")));
        stuLst.add(new StudentBean(2,"张思德",true,new ClassBean("J189")));
        stuLst.add(new StudentBean(3,"张思网",true,new ClassBean("J192")));
        stuLst.add(new StudentBean(4,"张思汤",false,new ClassBean("J190")));
        //把数据放入到表格当中
        this.stuTab.updateLovoTable(stuLst);
        //如果需要替换某些列的数据展示
        for(int i = 0; i < stuLst.size(); i++){
            this.stuTab.setValueAt(LovoConvertion.genderToString(stuLst.get(i).isStuGender()),i,1);
        }
        //使用按钮操作表格
        this.tabBtn = new LovoButton("选择行",550,20,this.contentp);
        this.tabBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int stuID = LovoComponetFrame.this.stuTab.getKey();
                //如果没有选中，那么这里的stuID的值是-1
                System.out.println(stuID);
            }
        });


        //头像的文件选择器
        this.headChos = new LovoFileChooser(this.contentp);//源代码写好的
        this.headChos.setBounds(20,120,100,150);
        this.tabBtn = new LovoButton("获取文件路径",150,120,this.contentp);
        this.tabBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = LovoComponetFrame.this.headChos.getFilePath();
                JOptionPane.showMessageDialog(null,filePath);
                /*
                    你们需要判断文件路径是否为空或空传，如果是那么直接使用image/defaultHead.JPG;
                    如果不为空，那么需要把这片文件拷贝到image路径下，文件名要修改为唯一值，但是文件
                    后缀名不能变。然后再把这个新的路径放入到学生Bean对象当中去，存入数据库。
                 */

            }
        });

        //日历控件
        this.lovoDate = new LovoDate("生日：",250,120,this.contentp);
        this.dateBtn = new LovoButton("获取生日",250,160,this.contentp);
        this.lovoDate.setEditable(false);
        this.dateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date birthday = LovoComponetFrame.this.lovoDate.getDate();
                /*
                    1、由于该控件返回的是一个java.util.Date对象，
                       而我们的Bean中设计的是LocalDate来存放时间。
                       所以需要做转换。
                 */
                LocalDate birthLocalDate = LovoConvertion.dateToLoaclDate(birthday);
                /*
                    2、该控件不支持直接在文本框中输入时间日期，这算一个bug，
                    只是这个bug是控件的bug，你们不用去管。
                 */
                JOptionPane.showMessageDialog(null,birthLocalDate);
            }
        });

        //下拉框演示
        /*
            在源代码中，所有的下拉框都是<String>，这是为了做界面原型DEMO;
            在开发的时候，你们需要把放对象的下拉框改成<Bean对象>,这样就可以
            直接从下拉框中获取对象，而不是只有对象的名字属性.
         */

        /*
            参数1：界面上显示的标签，源代码中已经定义好不用动
            参数2：放入下拉单的集合对象，这个需要你们通过调用Service方法去获取，然后传入
            参数3：x坐标；
            参数4：y坐标
            参数5：容器
         */
        this.stuComb = new LovoComboBox<>("学生：",stuLst,250,200,this.contentp);
        /*
            默认显示的是调用Bean对象的toString方法，所以需要重写Bean的toString方法，
            返回我们需要它显示的值。
         */

        this.combBtn = new LovoButton("获取下拉单",250,240,this.contentp);
        this.combBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentBean stu = LovoComponetFrame.this.stuComb.getItem();
                JOptionPane.showMessageDialog(null,stu);
            }
        });

        //单选框的选择事件
        this.genderBtn = new LovoRadioButton("性别：",new String[]{"男","女","不详"},
                250,300,this.contentp){
            @Override
            public void addListener(String itemName) {
                String msg = "";
                if(itemName.equals("男")){
                    msg = "男同学";
                }else if(itemName.equals("女")){
                    msg = "女同学";
                }else{
                    msg = "滚！";
                }
                JOptionPane.showMessageDialog(null,msg);
            }
        };


    }

}
