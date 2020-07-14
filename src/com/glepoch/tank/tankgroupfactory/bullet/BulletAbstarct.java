package com.glepoch.tank.tankgroupfactory.bullet;

import com.glepoch.tank.PropertiesMgr;
import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import enums.Dir;
import enums.Group;
import gamemodelfacade.GameModel;

import java.awt.*;

public abstract class BulletAbstarct {
    public static final int SPEED = PropertiesMgr.getInt("bulletSpeed");
    ;
    public int x = 10, y = 10;
    public Dir dir = Dir.DOWN;
    public boolean alive = true;
    public GameModel gm = null;
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();

    public BulletAbstarct(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = ResourceMgr.newInstance().TX;
        rect.height = ResourceMgr.newInstance().TY;
    }

    public void paint(Graphics g) {
        if (!alive) {
            this.gm.bulletList.remove(this);
        }
        switch (dir) {
            case LEFT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletL, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletL, x, y, null);
                break;
            case UP:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletU, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletU, x, y, null);
                break;
            case RIGHT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletR, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletR, x, y, null);
                break;
            case DOWN:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletD, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletD, x, y, null);
                break;
        }
        move();
    }

    public void move() {
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
        rect.x = this.x;
        rect.y = this.y;
        if (this.x < 0 || this.y < 0 || x > TankMainFrame.GAME_WIDTH || y > TankMainFrame.GAME_HEIGHT) alive = false;
    }

    public void collideWith(TankAbstract tank) {
        if (tank == null) return;
        if (this.group == tank.group) return;
        if (rect.intersects(tank.rect)) {
            this.die();
            tank.die();
            int EX = tank.x + ResourceMgr.newInstance().TX / 2 - ResourceMgr.newInstance().EX / 2;
            int EY = tank.y + ResourceMgr.newInstance().TY / 2 - ResourceMgr.newInstance().EY / 2;
            gm.explodeList.add(gm.tankGroup.createExplode(EX, EY, gm));
        }
    }

    public void die() {
        alive = false;
    }
}
