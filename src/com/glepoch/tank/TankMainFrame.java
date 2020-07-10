package com.glepoch.tank;


import sun.awt.SunHints;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMainFrame extends Frame {
    int x = 10, y = 10;

    public TankMainFrame() {
        setSize(600, 400);
        setResizable(true);
        setTitle("Tank");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 100, 100);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bl = false, br = false, bu = false, bd = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    bd = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    bl = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    bu = false;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                default:
                    break;
            }
        }
    }
}
