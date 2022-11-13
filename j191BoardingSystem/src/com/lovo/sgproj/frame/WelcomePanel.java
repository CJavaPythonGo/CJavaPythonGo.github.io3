package com.lovo.sgproj.frame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel {

    private JLabel imgLab;

    public WelcomePanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(null);

        this.imgLab = new JLabel(new ImageIcon("image/home.JPG"));
        this.imgLab.setBounds(100, 0, 629, 505);
        this.add(this.imgLab);
    }

}
