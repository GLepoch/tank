package com.glepoch.tank;


import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import enums.Dir;
import gamemodelfacade.GameModel;

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
    public static final int GAME_WIDTH = PropertiesMgr.getInt("gameWidth");
    public static final int GAME_HEIGHT = PropertiesMgr.getInt("gameHeight");
    GameModel gm = new GameModel();

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
        gm.paint(g);
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
            TankAbstract tank = gm.tank;
            if (tank == null) return;
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
            TankAbstract tank = gm.tank;
            if (tank == null) return;
            if (!bl && !br && !bu && !bd) {
                tank.moving = false;
            } else {
                tank.moving = true;
                if (bl) tank.dir = Dir.LEFT;
                if (br) tank.dir = Dir.RIGHT;
                if (bu) tank.dir = Dir.UP;
                if (bd) tank.dir = Dir.DOWN;

            }
        }
    }


}
