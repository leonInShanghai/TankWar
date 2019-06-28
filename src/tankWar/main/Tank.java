package tankWar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import tankWar.tool.Constant;


/**
 * Copyright (c) 2018年8月20日  Leon All rights reserved. 
 *
 * Description: 坦克对象 good为真时是英雄坦克  为假时是敌人坦克
 */
public class Tank {
	
	/**坦克的宽度*/
	public static int WIDTH;
	
	/**坦克的高度*/
	public static int HEIGHT;
	
	/**坦克的生命变量 默认为true被打中为false*/
	private boolean live = true;
	
	/**英雄坦克头顶的血条*/
	private BloodBar bb = new BloodBar();
	
	/**英雄坦克的生命值 每次被打到减掉20*/
	private int life = 100;
	
	/**引用大管家方便传值*/
	private TankClient tc; 
	
	/**用于区分敌我的变量   英雄坦克和敌人坦克都用这个类*/
	private boolean good;
	
	/**坦克的坐标*/
	private int x,y;
	
	/**坦克的上一个坐标*/
	private int oldX,oldY;
	
	/**随机数产生器*/
	private static Random r = new Random();
	
	/**坦克朝  右上 左上  左下 右下*/
	private boolean bL = false,bU = false,bR = false,bD = false;
	
	/**坦克的 八个方向 枚举  L:左,LU：左上,U：上,RU：右上,R：右,RD：右下,D：下,LD：左下,STOP:停*/
	enum Direction{
		L,LU,U,RU,R,RD,D,LD,STOP
	}
	
	/**坦克的 八个方向 枚举  L:左,LU：左上,U：上,RU：右上,R：右,RD：右下,D：下,LD：左下,默认：STOP*/
	private Direction dir = Direction.STOP;
	
	/**坦克炮筒的方向  枚举  L:左,LU：左上,U：上,RU：右上,R：右,RD：右下,D：下,LD：左下,默认：D*/
	private Direction ptDir = Direction.D;
	
	/**地方坦克随机移动的步骤*/
	private int step = r.nextInt(12) + 3;
	

	public Tank(int x, int y,boolean good) {
		super();
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}
	
	
	public Tank(int x, int y,boolean good,Direction dir,TankClient tc) {
		this(x,y,good);
		this.dir = dir;
		this.tc = tc;
	}
	
	
	/**
	 * 坦克自己画自己的方法 
	 * @param g 传递过来的画笔
	 */
	public void draw(Graphics g) {
		
		//处理坦克已经被打中 
		if (!live) {
		
			//敌人被打中
			if (!good) {
				//从敌人坦克集合中移除
				tc.tanks.remove(this);
			}
			
			//返不再画坦克
			return;
		}

		//取出画笔原来的颜色
		Color c = g.getColor();
		
		//设置画笔颜色为红色
		g.setColor(Color.RED);
		
		if (good) bb.draw(g);
		
		//Leon网上找的图片（直升机）icon = new ImageIcon("image\\self0.png");
		ImageIcon icon = null;
		
		
		//根据炮筒的方向画出炮筒
		switch (ptDir) {
		case L:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self90.png");
			}else {
				icon = new ImageIcon("image/enemy90.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
			break;
		case LU:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self135.png");
			}else {
				icon = new ImageIcon("image/enemy135.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);
			break;	
		case U:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self180.png");
			}else {
				icon = new ImageIcon("image/enemy180.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);
			break;
		case RU:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self225.png");
			}else {
				icon = new ImageIcon("image/enemy225.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);
			break;	
		case R:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self270.png");
			}else {
				icon = new ImageIcon("image/enemy270.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);
			break;	
		case RD:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self315.png");
			}else {
				icon = new ImageIcon("image/enemy315.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);
			break;	
		case D:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self0.png");
			}else {
				icon = new ImageIcon("image/enemy0.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);
			break;	
		case LD:
			//图片坦克
			if (good) {
				icon = new ImageIcon("image/self45.png");
			}else {
				icon = new ImageIcon("image/enemy45.png");
			}
			//画的图形坦克的炮筒
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT);
			break;
		case STOP:
			//这里不做任何是为了消除黄色警告
		    break;
		}
		
		//自己画的好看的图片坦克
		Image img = icon.getImage();
		WIDTH = img.getWidth(null);
		HEIGHT = img.getHeight(null);
		g.drawImage(img, x,y,null);
		
		//画图形的坦克 简陋
		//g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		move();
	}


	/**
	 * 根据当前的方向先前移动-用户控制方向坦克自己走
	 */
	private void move(){
		
		//记录坦克移动前的位置
		this.oldX = x;
		this.oldY = y;
		
		switch (dir) {
		case L:
			x -= Constant.XSPEED;
			break;
		case LU:
			//斜向速度需  ÷ √2
			x -= Constant.XSPEED / Math.sqrt(2);
			y -= Constant.YSPEED / Math.sqrt(2);
			break;	
		case U:
			y -= Constant.YSPEED;
			break;
		case RU:
			//斜向速度需  ÷ √2
			x += Constant.XSPEED / Math.sqrt(2);
			y -= Constant.YSPEED / Math.sqrt(2);
			break;	
		case R:
			x += Constant.XSPEED;
			break;	
		case RD:
			//斜向速度需  ÷ √2
			x += Constant.XSPEED / Math.sqrt(2);
			y += Constant.YSPEED / Math.sqrt(2);
			break;	
		case D:
			y += Constant.YSPEED;
			break;	
		case LD:
			//斜向速度需  ÷ √2
			x -= Constant.XSPEED / Math.sqrt(2);
			y += Constant.YSPEED / Math.sqrt(2);
			break;	
		case STOP:
			//停止的时候什么都不做
			break;
		}
		
		//根据坦克前进的方向调整坦克炮筒的方向（头朝哪）
		if (dir != Direction.STOP) {
			ptDir = dir;
		}
		
		//处理坦克出界的问题
		if (x < 0) { x = 0; }
		if (y < Constant.GAP) { y = Constant.GAP; }
		if (x + Tank.WIDTH > Constant.GAME_WIDTH) { x = Constant.GAME_WIDTH - Tank.WIDTH; }
		if (y + Tank.HEIGHT > Constant.GAME_HEIGHT) { y = Constant.GAME_HEIGHT - Tank.HEIGHT; }
		
		//敌人坦克随机动起来
		if (!good) {
			
			//枚举转数组
			Direction[] dirs = Direction.values();
			
			//随机的调用坦克随机改变方向
			if (step == 0) {
				step = r.nextInt(12) + 3;
				
				//用随机数产生器产生随机数
				int rn = r.nextInt(dirs.length);
				
				//随机改变坦克的方向
				dir = dirs[rn];
			}
			
			step--;
			
			//敌人坦克开火
			if (r.nextInt(40) > 38) {
				fire();
			}
		}
		
	}
	
	
	/**
	 * 停留在原地的方法-处理敌人坦克撞到墙
	 */
	private void stay() {
		x = oldX;
		y = oldY;
	}
	
	
	/**
	 * 英雄坦克根据用户按下的键盘执行行动上的命令
	 * @param e:用户按下的按键目前仅监听了 上 下 左 右 
	 */
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_F2:  
			if (!live) {
				live = true;
				setLife(100);
			}
			break;
		
		case KeyEvent.VK_LEFT:  
			bL = true;
			break;

		case KeyEvent.VK_UP:  
			bU = true;
			break;
			
		case KeyEvent.VK_RIGHT:  
			bR = true;
			break;
			
		case KeyEvent.VK_DOWN:  
			bD = true;
			break;
		}
		
		locateDirection();
		
	}
	
	
	/**
	 * 根据用户按下的键盘按键 计算出 坦克前进的 8个方向
	 */
	private void locateDirection() {
		
		if (bL && !bU && !bR && !bD) {//用户只按下了左键
			dir = Direction.L;
		}else
		
		if (bL && bU && !bR && !bD) {//用户同时按下了左 上 键
			dir = Direction.LU;
		}else
		
		if (!bL && bU && !bR && !bD) {//用户只按下了上键
			dir = Direction.U;
		}else
		
		if (!bL && bU && bR && !bD) {//用户同时按下了右 上 键
			dir = Direction.RU;
		}else
		
		if (!bL && !bU && bR && !bD) {//用户只按下了右键
			dir = Direction.R;
		}else
		
		if (!bL && !bU && bR && bD) {//用户同时按下了右 下 键
			dir = Direction.RD;
		}else
		
		if (!bL && !bU && !bR && bD) {//用户只按下了下键
			dir = Direction.D;
		}else
		
		if (bL && !bU && !bR && bD) {//用户同时按下了左 下 键
			dir = Direction.LD;
		}else
		
		if (!bL && !bU && !bR && !bD) {//用户什么都没有按
			dir = Direction.STOP;
		}
		
	}
	
	
	/**
	 * 英雄坦克根据用户抬起的键盘停止执行行动上的命令
	 * @param e:用户按下的按键目前仅监听了 上 下 左 右 ctrl
	 */
	public void keyReleased(KeyEvent e){
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_CONTROL:  
			fire();
			break;
		
		case KeyEvent.VK_LEFT:  
			bL = false;
			break;

		case KeyEvent.VK_UP:  
			bU = false;
			break;
			
		case KeyEvent.VK_RIGHT:  
			bR = false;
			break;
			
		case KeyEvent.VK_DOWN:  
			bD = false;
			break;
			
		case KeyEvent.VK_ENTER:  
			superFire();
			break;
		}
		
		locateDirection();
	}
	
	
	/**
	 * 坦克开火打架的方法
	 * @return m:炮弹类
	 */
	public Missile fire() {
		
		//如果 坦克已经死亡 不能再发子弹 主要是针对英雄
		if (!live) return null;
		
		Missile m = new Missile(x + Tank.WIDTH / 2 - Missile.WIDTH / 2, y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2, good,ptDir, tc);
		tc.missiles.add(m);
		return m;
	}
	
	
	/**
	 * 朝某个方向发射炮弹
	 * @param dir：方向
	 */
	public Missile fire(Direction dir) {
		//如果 坦克已经死亡 不能再发子弹 主要是针对英雄
		if (!live) return null;
		
		Missile m = new Missile(x + Tank.WIDTH / 2 - Missile.WIDTH / 2, y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2, good,dir, tc);
		tc.missiles.add(m);
		return m;
	}
	
	
	/**
	 * 为碰撞检测打基础
	 * @return 一个坦克的[数]长方形，矩形
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	
	/**
	 * 设置坦克 生命
	 * @param live
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	/**
	 * 坦克的生命的getter方法
	 * @return live坦克的生命Boolean类型
	 */
	public boolean isLive() {
		return live;
	}
	

	/**
	 * @return 坦克属于敌人还是英雄  敌人的子弹对敌人没有伤害 英雄的子弹对自己也没有伤害
	 */
	public boolean isGood() {
		return good;
	}

	
	/**
	 * 坦克跟墙相撞的碰撞检测
	 * @param w 传递过来的墙
	 * @return 碰撞检测的结果
	 */
	public boolean collidesWithWall(Wall w) {
		if (live && this.getRect().intersects(w.getRect())) {
			//碰到墙让坦克回到移动前的位置
			stay();
			return true;
		}
		return false;
	}
	
	
	/**
	 * 检测两个坦克是否相互交互-不让两个坦克互相穿过对方
	 * @return 碰撞检测的结果
	 */
	public boolean collodesWithTanks(List<Tank> tanks) {
		
		//遍历坦克集合 对比所有坦克是否 有交互
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t ) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					//两辆坦克交互 都回到上一个位置
					this.stay();
					t.stay();
					return true;	
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 发射超级炮弹
	 */
	private void superFire() {
		//枚举转数组
		Direction[] dirs = Direction.values();
		
		//朝向8个方向开火
		for (int i = 0; i < dirs.length -1; i++) {
			fire(dirs[i]);
		}
	}
	
	
	/**
	 * 英雄坦克被敌人击中 设置（减少）生命值
	 * @param life：英雄坦克的生命值
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	
	/**
	 * @return 英雄坦克的生命值
	 */
	public int getLife() {
		return life;
	}
	
	
	/**
	 * Description: 内部类 英雄坦克的血条
	 * @author Leon 
	 * @date 2018年8月28日
	 */
	private class BloodBar {
		
		/**
		 * 血条画自己的方法
		 */
		public void draw(Graphics g) {
			
			//得到画笔的颜色
			Color c = g.getColor();
			
			g.setColor(Color.RED);
			
			//画一个空心的血条框
			g.drawRect(x, y - 10, WIDTH, 10);
			
			//根据英雄的血量计算出 血条的宽度
			int w = WIDTH * life / 100;
			
			//画出英雄坦克的血条
			g.fillRect(x, y - 10, w, 10);
			
			//设置回画笔原来的颜色
			g.setColor(c);
		}
		
	}
	
	
	/**
	 *英雄坦克吃血块的方法 
	 */
	public boolean eat(Blood b) {
		if (good && this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			
			//让英雄生命值100%
			this.setLife(100);
			
			//让降落伞（血块）消失
			b.setLive(false);
			
			return true;
		}
		return false;
	}
	
}
