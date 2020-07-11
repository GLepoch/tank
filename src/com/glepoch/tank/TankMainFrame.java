package com.glepoch.tank;


import sun.awt.SunHints;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMainFrame extends Frame {
    Tank tank = new Tank(100, 300, Dir.UP, Group.GOOD, this);
    //Bullet bullet=new Bullet(150,100,Dir.DOWN);
    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 400;
    List<Bullet> bulletList = new ArrayList<>();
    public List<Tank> badTankList = new ArrayList<>();


    public TankMainFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.CYAN);
        g.drawString("子弹数量：" + this.bulletList.size(), 20, 50);
        g.drawString("敌人数量：" + this.badTankList.size(), 20, 80);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i < badTankList.size(); i++) {
            badTankList.get(i).paint(g);
        }
        tank.paint(g);
        g.setColor(color);
        for (int i = 0; i < bulletList.size(); i++) {
            for (int i1 = 0; i1 < badTankList.size(); i1++) {
                bulletList.get(i).collideWith(badTankList.get(i1));
            }
        }
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
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
