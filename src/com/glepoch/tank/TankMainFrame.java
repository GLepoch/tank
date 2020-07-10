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
    Tank tank = new Tank(100, 100, Dir.DOWN);
    Bullet bullet=new Bullet(150,100,Dir.DOWN);

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
        tank.paint(g);
        bullet.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bl = false, br = false, bu = false, bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    //br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    //bd = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    //bl = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    //bu = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
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
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bl && !br && !bu && !bd) {
                tank.setMoving(false);
            } else {
                tank.setMoving(true);
                if (bl) tank.setDir(Dir.LEFT);
                if (br) tank.setDir(Dir.RIGHT);
                if (bu) tank.setDir(Dir.UP);
                if (bd) tank.setDir(Dir.DOWN);
            }
        }
    }
}
