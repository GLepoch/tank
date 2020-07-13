package com.glepoch.tank;


import com.glepoch.tank.firebulletstrategy.impl.FireFoureBulletBulletStrategy;
import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankgroupfactory.GroupFactory.impl.BadTankGroup;
import com.glepoch.tank.tankgroupfactory.GroupFactory.impl.GoodTankGroup;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import com.glepoch.tank.tankgroupfactory.explode.ExplodeAbstract;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankimageStrategy.impl.BadTankStrategy;
import com.glepoch.tank.tankimageStrategy.impl.GoodTankStrategy;
import enums.Dir;
import enums.Group;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMainFrame extends Frame {
    TankGroupAbtractFactory goodTankGroup = new GoodTankGroup();
    TankGroupAbtractFactory badTankGroup = new BadTankGroup();
    public TankAbstract tank = goodTankGroup.createTank(PropertiesMgr.getInt("goodTankX"), PropertiesMgr.getInt("goodTankY"), Dir.UP, Group.GOOD, this, new FireOneBulletBulletStrategy(), new GoodTankStrategy(), goodTankGroup);
    public static final int GAME_WIDTH = PropertiesMgr.getInt("gameWidth");
    public static final int GAME_HEIGHT = PropertiesMgr.getInt("gameHeight");
    ;
    public List<BulletAbstarct> bulletList = new ArrayList<>();
    public List<TankAbstract> badTankList = new ArrayList<>();
    public List<ExplodeAbstract> explodeList = new ArrayList<>();

    public TankMainFrame() {
        createBadTank();
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
        g.drawString("爆炸数量:" + explodeList.size(), 20, 100);
        for (int i = 0; i < badTankList.size(); i++) {
            badTankList.get(i).paint(g);
        }
        if (tank != null)
            tank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        g.setColor(color);
        for (int i = 0; i < bulletList.size(); i++) {
            for (int i1 = 0; i1 < badTankList.size(); i1++) {
                bulletList.get(i).collideWith(badTankList.get(i1));
            }
        }
        /*我方坦克和子弹碰撞检测*/
        /*for (int i = 0; i < bulletList.size(); i++) {
                bulletList.get(i).collideWith(tank);
        }*/
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
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

    void createBadTank() {
        Random random = new Random();
        Integer badTankCount = PropertiesMgr.getInt("badTankCount");
        for (int i = 0; i < badTankCount; i++) {
            int btx = random.nextInt(TankMainFrame.GAME_WIDTH);
            int bty = random.nextInt(TankMainFrame.GAME_HEIGHT);
            Dir dir = Dir.values()[random.nextInt(4)];
            if (btx >= TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX) {
                btx = TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX;
            }
            if (bty >= TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY) {
                bty = TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY;
            }
            if (i % 5 != 0) {
                this.badTankList.add(badTankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireOneBulletBulletStrategy(), new BadTankStrategy(), badTankGroup));
            } else {
                this.badTankList.add(badTankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireFoureBulletBulletStrategy(), new BadTankStrategy(), badTankGroup));
            }
        }
    }
}
