package gamemodelfacade;

import com.glepoch.tank.PropertiesMgr;
import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.firebulletstrategy.impl.FireFoureBulletStrategy;
import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletStrategy;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
* 门面设计模式，管理所有模型：坦克，子弹，爆炸，墙等
* */
public class GameModel {
    public TankGroupAbtractFactory tankGroup = new GoodTankGroup();
    //TankGroupAbtractFactory badTankGroup = new BadTankGroup();
    public TankAbstract tank = tankGroup.createTank(PropertiesMgr.getInt("goodTankX"), PropertiesMgr.getInt("goodTankY"), Dir.UP, Group.GOOD, this, new FireOneBulletStrategy(), new GoodTankStrategy());

    public List<BulletAbstarct> bulletList = new ArrayList<>();
    public List<TankAbstract> badTankList = new ArrayList<>();
    public List<ExplodeAbstract> explodeList = new ArrayList<>();

    public GameModel() {
        createBadTank();
    }

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
                this.badTankList.add(tankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireOneBulletStrategy(), new BadTankStrategy()));
            } else {
                this.badTankList.add(tankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireFoureBulletStrategy(), new BadTankStrategy()));
            }
        }
    }
}
