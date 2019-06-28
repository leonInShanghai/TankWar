package tankWar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

import tankWar.main.Tank.Direction;
import tankWar.tool.Constant;

/**
 * Copyright (c) 2018年8月21日  Leon All rights reserved. 
 *
 * Description: 炮弹类
 */
public class Missile {
	
	/**炮弹的宽度  解决第一次位置不准确给了默认值16*/
	public static int WIDTH = 16;
	
	/**炮弹的高度   解决第一次位置不准确给了默认值16*/
	public static int HEIGHT = 16;

	/**子弹的坐标位置*/
	private int x,y;
	
	/**子弹前进的方向*/
	private Tank.Direction dir;
	
	/**子弹的发出方 英雄还是敌人*/
	private boolean good;
	
	/**子弹的生命 默认为true 当飞出边界 打中敌人 为false*/
	private boolean live = true;
	
	/**引用大管家方便传值*/
	private TankClient tc; 


	public Missile(int x, int y, Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	
	public Missile(int x, int y,boolean good, Direction dir,TankClient tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}
	
	
	/**
	 * 坦克自己画自己的方法
	 * @param g：传递过来的画笔
	 */
	public void draw(Graphics g) {
		
		//如果子弹已经打中坦克 
		if (!live) {
			//移除飞出边界的子弹
			tc.missiles.remove(this);
			//返回什么都不做
			return;
		}
		
		
		//取出画笔原来的颜色
		Color c = g.getColor();
		
		//设置画笔颜色为黑色
		g.setColor(Color.BLACK);
		
		//Leon网上找的图片（直升机）
		ImageIcon icon = new ImageIcon("image\\missile.png");
		Image img = icon.getImage();
		WIDTH = img.getWidth(null);
		HEIGHT = img.getHeight(null);
		g.drawImage(img, x,y,null);
		
		//画图形的子弹 简陋
		//g.fillOval(x, y, 10, 10);
		g.setColor(c);
		
		move();
	}
	
	
	/**
	 * 根据当前的方向先前进
	 */
	private void move() {
		switch (dir) {
		case L:
			x -= Constant.MXSPEED;
			break;
		case LU:
			//斜向速度需  ÷ √2
			x -= Constant.MXSPEED / Math.sqrt(2);
			y -= Constant.MYSPEED / Math.sqrt(2);
			break;	
		case U:
			y -= Constant.MYSPEED;
			break;
		case RU:
			//斜向速度需  ÷ √2
			x += Constant.MXSPEED / Math.sqrt(2);
			y -= Constant.MYSPEED / Math.sqrt(2);
			break;	
		case R:
			x += Constant.MXSPEED;
			break;	
		case RD:
			//斜向速度需  ÷ √2
			x += Constant.MXSPEED / Math.sqrt(2);
			y += Constant.MYSPEED / Math.sqrt(2);
			break;	
		case D:
			y += Constant.MYSPEED;
			break;	
		case LD:
			//斜向速度需  ÷ √2
			x -= Constant.MXSPEED / Math.sqrt(2);
			y += Constant.MYSPEED / Math.sqrt(2);
			break;	
		case STOP:
			//这里什么都不做 解决黄色警告
			break;
		}
		
		//判断子弹是否飞出界
		if (x < 0 || y < 0 || x > Constant.GAME_WIDTH || y > Constant.GAME_HEIGHT) {
			//生命改为false
			live = false;
		}
	}
	
	
	/**返回子弹是否活着*/
	public boolean isLive() {
		return live;
	}
	
	
	/**
	 * 为碰撞检测打基础
	 * @return 一个子弹的[数]长方形，矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	
	/**
	 * 是否打中坦克  碰撞检测由子弹发起
	 * @param t 坦克对象
	 * @return 是否碰撞
	 */
	public boolean hitTank(Tank t) {
		
		/**
		 * intersects : 横断，横切，横穿( intersect的第三人称单数 )
		 * 判断子弹的矩形是否和坦克的矩形相交 即 碰撞检测
		 * && good != t.isGood() 区分敌我 敌人的子弹不能伤害敌人 英雄的子弹不能伤害自己
		 */
		if (live && this.getRect().intersects(t.getRect()) && t.isLive() && good != t.isGood()) {
			
			//敌人打中英雄坦克的处理
			if (t.isGood()) {
				t.setLife(t.getLife() - 20);
				//坦克生命设置为false
				if (t.getLife() <= 0) t.setLive(false);
			}else {//英雄打中敌人
				//坦克生命设置为false
				t.setLive(false);
			}
			
			//子弹的生命也设置为false
			live = false;
			
			//开始执行爆炸的动画
			//Explode e = new Explode(x, y, tc);
			Explode e = new Explode(x + WIDTH / 2, y + HEIGHT / 2, tc);
			
			//添加到爆炸的集合中
			tc.explodes.add(e);
		}
		return this.getRect().intersects(t.getRect());	
	}
	
	
	/**
	 * 是否打中坦克子弹数集合对坦克集合  碰撞检测由子弹发起
	 * @param tanks 坦克集合
	 * @return 是否碰撞
	 */
	public boolean hitTanks(List<Tank> tanks) {
	
		for (int i = 0; i < tanks.size(); i++) {
		
			if (hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 子弹和墙的碰撞检测
	 * @return
	 */
	public boolean hitWall(Wall w) {
		
		/**
		 * 子弹撞到墙的处理方法
		 */
		if (live && this.getRect().intersects(w.getRect())) {
			live = false;
			
			//开始执行爆炸的动画
			Explode e = new Explode(x + WIDTH / 2, y + HEIGHT / 2, tc);
			
			//添加到爆炸的集合中
			tc.explodes.add(e);
			
			return true;
		}
		
		return false;
	}
	
}

