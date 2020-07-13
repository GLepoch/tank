package com.glepoch.tank;

import utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceMgr {
    private static final ResourceMgr RESOURCE_MGR = new ResourceMgr();
    public BufferedImage tankL, tankU, tankR, tankD;
    public BufferedImage badTankL, badTankU, badTankR, badTankD;
    public BufferedImage bulletL, bulletU, bulletR, bulletD;
    public BufferedImage badBulletL, badBulletU, badBulletR, badBulletD;
    public int TX, TY, BX, BY, EX, EY;
    public List<BufferedImage> explodes = new ArrayList<>();

    private ResourceMgr() {
        try {
            //加载坦克图片资源(好)
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            tankL = ImageUtil.rotateImage(tankU, -90);
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankD = ImageUtil.rotateImage(tankU, 180);
            TX = tankL.getWidth();
            TY = tankL.getHeight();
            //加载坦克图片资源(坏)
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            //加载子弹资源（好）
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            BX = bulletL.getWidth();
            BY = bulletL.getHeight();
            //加载子弹资源（坏）
            badBulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            badBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            badBulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            badBulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            //加载爆炸资源
            for (int i = 0; i < 16; i++) {
                explodes.add(ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
            EX = explodes.get(0).getWidth();
            EY = explodes.get(0).getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;

    public static ResourceMgr newInstance() {
        return RESOURCE_MGR;
    }
}
