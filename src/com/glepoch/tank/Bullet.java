package com.glepoch.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 5;
    int x = 10, y = 10;
    Dir dir = Dir.DOWN;
    private boolean alive=true;
    TankMainFrame tmf=null;

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

    public Bullet(int x, int y, Dir dir,TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf=tmf;
    }

    public void paint(Graphics g) {
        if(!alive){
            this.tmf.bulletList.remove(this);
        }
        g.fillOval(x, y, 10, 10);
        move();
    }

    private void move() {
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
        if(this.x<0||this.y<0||x>tmf.getWidth()||y>tmf.getHeight()) alive=false;
    }
}
