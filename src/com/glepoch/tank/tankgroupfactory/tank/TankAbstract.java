package com.glepoch.tank.tankgroupfactory.tank;

import com.glepoch.tank.PropertiesMgr;
import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import com.glepoch.tank.tankimageStrategy.impl.GoodTankStrategy;
import enums.Dir;
import enums.Group;
import gamemodelfacade.GameModel;

import java.awt.*;
import java.util.Random;

public abstract class TankAbstract extends GameObject {
    public static final int SPEED = PropertiesMgr.getInt("tankSpeed");
    public Rectangle rect = new Rectangle();
    public boolean moving = true;
    public GameModel gm = null;
    public Dir dir = Dir.DOWN;
    public boolean alive = true;
    public Group group = Group.BAD;
    public FireBulletStrategy fireBulletStrategy = new FireOneBulletStrategy();
    public TankImageStrategy tankImageStrategy = new GoodTankStrategy();
    public Random random = new Random();
    public TankGroupAbtractFactory tankGroupAbtractFactory;
    public int oldX,oldY;

    public TankAbstract(int x, int y, Dir dir, Group group, GameModel gm, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = ResourceMgr.newInstance().TX;
        rect.height = ResourceMgr.newInstance().TY;
        this.fireBulletStrategy = fireBulletStrategy;
        this.tankImageStrategy = tankImageStrategy;
        this.tankGroupAbtractFactory = tankGroupAbtractFactory;
    }

    public abstract void paint(Graphics g);

    public void move() {
        oldX=x;
        oldY=y;
        if (!moving)
            return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (random.nextInt(100) > 95 && this.group != Group.GOOD) {
            this.fire();
        }
        if (random.nextInt(100) > 95 && this.group != Group.GOOD) {
            this.changeDir();
        }
        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;
    }

    public void boundsCheck() {
        if (this.x < 0) this.x = 0;
        if (this.x > TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX)
            this.x = TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX;
        if (this.y < 50) this.y = 50;
        if (this.y > TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY)
            this.y = TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY;
    }

    public void changeDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public abstract void fire();

    public void die() {
        alive = false;
        System.out.println(this.alive + "   " + this.group);
        gm.gameObjects.remove(this);
        //tmf.tank = null;
    }

    public abstract void stop();

    public abstract void back();
}