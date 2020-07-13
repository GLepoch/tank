package com.glepoch.tank;

import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletBulletStrategy;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankimageStrategy.impl.GoodTankStrategy;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import enums.Dir;
import enums.Group;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED = PropertiesMgr.getInt("tankSpeed");
    public Rectangle rect = new Rectangle();
    boolean moving = true;
    public TankMainFrame tmf = null;
    public int x = 50, y = 50;
    public Dir dir = Dir.DOWN;
    public boolean alive = true;
    public Group group = Group.BAD;
    public FireBulletStrategy fireBulletStrategy = new FireOneBulletBulletStrategy();
    public TankImageStrategy tankImageStrategy = new GoodTankStrategy();

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, Group group, TankMainFrame tmf, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf = tmf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = ResourceMgr.newInstance().TX;
        rect.height = ResourceMgr.newInstance().TY;
        this.fireBulletStrategy = fireBulletStrategy;
        this.tankImageStrategy=tankImageStrategy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        tankImageStrategy.paint(g, this);
    }

    public void move() {
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

    private void boundsCheck() {
        if (this.x < 0) this.x = 0;
        if (this.x > TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX) this.x = TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX;
        if (this.y < 50) this.y = 50;
        if (this.y > TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY) this.y = TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY;
    }

    private void changeDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireBulletStrategy.fire(this);
    }

    public void die() {
        alive = false;
        System.out.println(this.alive + "   " + this.group);
        tmf.badTankList.remove(this);
        tmf.tank = null;
    }
}
