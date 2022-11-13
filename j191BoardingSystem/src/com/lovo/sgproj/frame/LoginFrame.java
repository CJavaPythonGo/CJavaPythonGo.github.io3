package com.lovo.sgproj.frame;

import com.lovo.sgproj.service.UserServiceImp;
import com.lovo.sgproj.service.inter.UserService;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private Container contentP;

    private JLabel titleLab;

    private JLabel nameLab;

    private JLabel pwdLab;

    private JTextField nameTxt;

    private JPasswordField pwdTxt;

    private JButton loginBtn;
    private UserService userService = new UserServiceImp();


    public LoginFrame() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setIconImage(tk.createImage("image/hp.JPG"));
        this.setTitle("宿舍管理系统");
        this.setLocation(440, 330);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.init();

        this.setVisible(true);

    }

    private void init() {


        this.contentP = this.getContentPane();
        this.contentP.setBackground(Color.WHITE);
        this.contentP.setLayout(null);

        this.titleLab = new JLabel();
        this.titleLab.setText("LOVO宿管系统");
        this.titleLab.setFont(new Font("微软雅黑", Font.ITALIC, 28));
        this.titleLab.setForeground(new Color(153, 71, 133));
        this.titleLab.setBounds(100, 0, 200, 50);
        this.contentP.add(this.titleLab);


        this.nameLab = new JLabel();
        this.nameLab.setText("用户名：");
        this.nameLab.setFont(new Font("微软雅黑", Font.ITALIC, 14));
        this.nameLab.setForeground(new Color(153, 71, 133));
        this.nameLab.setBounds(50, 70, 80, 30);
        this.contentP.add(this.nameLab);

        this.pwdLab = new JLabel();
        this.pwdLab.setText("密   码：");
        this.pwdLab.setFont(new Font("微软雅黑", Font.ITALIC, 14));
        this.pwdLab.setForeground(new Color(153, 71, 133));
        this.pwdLab.setBounds(50, 130, 80, 30);
        this.contentP.add(this.pwdLab);


        this.nameTxt = new JTextField();
        this.nameTxt.setFont(new Font("微软雅黑", Font.ITALIC, 18));
        this.nameTxt.setForeground(new Color(153, 71, 133));
        this.nameTxt.setBounds(135, 70, 150, 30);
        this.contentP.add(this.nameTxt);

        this.pwdTxt = new JPasswordField();
        this.pwdTxt.setFont(new Font("微软雅黑", Font.ITALIC, 18));
        this.pwdTxt.setForeground(new Color(153, 71, 133));
        this.pwdTxt.setBounds(135, 130, 150, 30);
        this.contentP.add(this.pwdTxt);


        this.loginBtn = new JButton();
        this.loginBtn.setText("登录");
        this.loginBtn.setFont(new Font("微软雅黑", Font.BOLD, 18));
        this.loginBtn.setForeground(new Color(153, 71, 133));
        this.loginBtn.setBounds(235, 200, 80, 30);
        this.contentP.add(this.loginBtn);

        this.loginBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                // 登录验证
                if (nameTxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入用户名");
                    return;
                }
                if (new String(pwdTxt.getPassword()) == null) {
                    JOptionPane.showMessageDialog(null, "请输入密码");
                    return;
                }
                if (!new String(pwdTxt.getPassword()).matches("\\d{6}")) {
                    JOptionPane.showMessageDialog(null, "请输入6位数字密码");
                    return;
                }
                boolean flag = userService.login(nameTxt.getText(), new String(pwdTxt.getPassword()));
                if (flag) {
                    new MainFrame();
                    LoginFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "用户名不存在或密码错误");
                }
//
//                new MainFrame();
//                LoginFrame.this.dispose();
            }
        });

    }

}
