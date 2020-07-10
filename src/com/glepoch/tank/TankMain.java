package com.glepoch.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMain {
    public static void main(String[] args) {
        Frame frame=new Frame();
        frame.setSize(800,600);
        frame.setResizable(false);
        frame.setTitle("Tank");
        frame.setAlwaysOnTop(true);
        frame.setLocation(50,50);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
