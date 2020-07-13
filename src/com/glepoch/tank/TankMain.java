package com.glepoch.tank;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMain {

    public static void main(String[] args) {
        TankMainFrame tank = new TankMainFrame();
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true) {
            try {
                Thread.sleep(20);
                tank.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
