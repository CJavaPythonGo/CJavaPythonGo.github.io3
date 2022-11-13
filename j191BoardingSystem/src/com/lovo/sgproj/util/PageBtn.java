package com.lovo.sgproj.util;

import javax.swing.*;
import java.awt.*;

public class PageBtn extends JButton {
    public PageBtn(String info, int x, int y, Container con) {
        super(info);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBounds(x, y, 30, 20);
        con.add(this);
    }
}
