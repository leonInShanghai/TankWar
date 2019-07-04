package tankWar.main;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import tankWar.tool.Constant;

/**
 * Copyright (c) 2018年8月20日  Leon All rights reserved. 
 *
 * Description: 程序的入口 自定义 Frame类 大管家
 */
public class TankClient extends Frame {

	/**
	 * serialVersionUID作用是序列化时保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	 */
	private static final long serialVersionUID = 15132131132L;
	
	/**创建 英雄坦克并指定坐标*/
	Tank myTank = new Tank(580, 750 + Constant.GAP,true,Tank.Direction.STOP,this);
	
	/**创建坦克大战中的墙*/
	Wall w1 = new Wall(100, 200, 20, 150, this),w2 = new Wall(300, 100, 300, 20, this);
	
	/**坦克被击中后产生的爆炸对象 由子弹发起*/
	public List<Explode> explodes = new ArrayList<Explode>();
	
	/**坦克打出的炮弹由用户按Ctrl坦克类发起*/
	public List<Missile> missiles = new ArrayList<Missile>();
	
	/**装敌人坦克 的集合*/
	public List<Tank> tanks = new ArrayList<Tank>();
			
	Image offScreenImage = null; 
	
	Image offScreenImage2 = null; 
	
	Image offScreenImage3 = null; 
	
	Image offScreenImage4 = null; 
	

	/**降落伞道具*/
	Blood b = new Blood();
	
	@Override
	public void paint(Graphics g) {
		
		//2019-6-8注释掉 解决屏幕闪烁问题
		//super.paint(g);
		
		//在屏幕上显示现在还有多少发炮弹没有释放-合理管理内存
		g.drawString("missiles count:" + missiles.size(), 10, 50);
		
		//在屏幕上显示还有多少爆炸没有释放-合理管理内存
		g.drawString("explodes count:"+explodes.size(), 10, 70);
		
		//在屏幕上显示还有多少坦克没有释放-合理管理内存
		g.drawString("tank     life:"+myTank.getLife(), 10, 90);
		
		//在屏幕上显示英雄坦克还有多少生命值
		g.drawString("tanks    count:"+tanks.size(), 10, 110);
		
		//敌人消灭完 再创建10辆敌人坦克
		if (tanks.size() <= 0) {
			for (int i = 0; i < 10; i++) {
				tanks.add(new Tank(50 + 40 * (i + 1),  50, false,Tank.Direction.D, this));
			}
		}
		
		//道具领取后发放新的道具
		if (!b.isLive()) {
			b = null;
			b = new Blood();
		}
				
		for(int i = 0; i < missiles.size();i++){
			//通过for循环画出所有的子弹
			Missile m = missiles.get(i);
			
			//开始子弹的碰撞检测 打敌人
			m.hitTanks(tanks);
			
			//开始子弹的碰撞检测 打英雄
			m.hitTank(myTank);
			
			//开始子弹的碰撞检测 打墙
			m.hitWall(w1);
			
			//开始子弹的碰撞检测 打墙
			m.hitWall(w2);
			
			//if (!m.isLive()) { 这是移除飞出边界子弹方法的一种
				//missiles.remove(m);
			//}else {
				//m.draw(g);
			//}
			
			m.draw(g);
		}
		
		for (int i = 0; i < explodes.size(); i++) {
			//通过for循环画出所有的爆炸
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for (int i = 0; i < tanks.size(); i++) {
			//画出集合中的所有敌人坦克
			Tank t = tanks.get(i);
			
			//集合中的坦克和墙的碰撞检测
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			
			//集合中的地方坦克互相不能穿过对方
			t.collodesWithTanks(tanks);
			
			//画出集合中的所有坦克
			t.draw(g);
		}
		
		myTank.draw(g);
		myTank.eat(b);
		w1.draw(g);
		w2.draw(g);
		b.draw(g);
		
		//g.drawImage(offScreenImage, 0, 0, null);    //将缓冲图案绘制在屏幕上 
	}
	
	
	
	/**
	 *调用这个方法 在paint之前 update时 画一张图片 再把所有对象画在图片上 
	 *再把图片画在 窗口体上说是能 减轻闪烁现象 感觉没有什么卵用 
	 */
	@Override
	public void update(Graphics g) {
		// 2019-6-8注释掉解决屏幕闪烁的问题
		//super.update(g);
		
		if (offScreenImage == null) {
			offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		}
		
		//得到offScreenImage的画笔
		Graphics gOffScreen = offScreenImage.getGraphics();
		
		
		//得到画笔原来的颜色
		Color c = gOffScreen.getColor();
		
		//设置新的画笔的颜色 green
		gOffScreen.setColor(Color.GREEN);
		
		//刷新界面
		gOffScreen.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		//设置回原来画笔的颜色
		gOffScreen.setColor(c);
		
		//调用paint用gOffScreen的画笔将对象划到图片上
		paint(gOffScreen);
		
		//再把图片画到窗口体上
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	
	/**
	 * 启动 窗口体方法
	 */
	public void lauchFrame() {
		
		//游戏启动之初 创建出10辆敌人的坦克
		for (int i = 0; i < 10; i++) {
			tanks.add(new Tank(50 + 40 * (i + 1),  50, false,Tank.Direction.D, this));
		}
		
		this.setTitle("波波直升机大战");
		
		//设置在屏幕上的位置基于左上角 这里通过计算让其在用户屏幕的正中心
		this.setLocation((Constant.SCREENWIDTH - Constant.GAME_WIDTH) / 2, (Constant.SCREENHEIGHT - Constant.GAME_HEIGHT) / 2);
		
		//设置窗口体的大小尺寸
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		
		//是否允许用户拖拽改变窗口体的大小 不写为true
		this.setResizable(false);
		
		//设置左上角的icon 自定义
		ImageIcon icon = new ImageIcon("image/clear_logo.png");
		Image img = icon.getImage();
		this.setIconImage(img);
		
		//设置背景颜色为绿色
		this.setBackground(Color.GREEN);
		
		//添加键盘监听事件
		this.addKeyListener(new KeyMonitor());
		
		//是否显示
		this.setVisible(true);
		
		//设置键盘默认为英文 方便监听点击事件
		this.enableInputMethods(false);
		
		//启动子线程
		new Thread(new paintThread()).start();
		
	}
	
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
		
		//监听并处理用户点击关闭窗口的事件
		tc.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){  
		        System.exit(0);  
		    }  
		});	
	}
	
	
	/**
	 * Description:定义一个内部类 开启一条子线程
	 */
	private class paintThread implements Runnable {

		@Override
		public void run() {
			
			while (true) {
				
				try {
					//java提供的方法調用paint(Graphics g)
					repaint();
					Thread.sleep(100);
					
					//解決屏幕分辨率低的電腦上閃爍的問題
//					if (Constant.SCREENWIDTH > 1919) {
//						Thread.sleep(100);
//						repaint(2000000000000000l,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
//						//System.out.println("屏幕分辨率大于1920");
//					}else {
//						Thread.sleep(150);
//						repaint(2000000000000000l,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
//						//System.out.println("屏幕分辨率小于1920");
//					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Description: 自定义内部类 监听并处理用户 键盘事件
	 */
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// 当用户按下键盘会调用此方法
			super.keyPressed(e);
			
			myTank.keyPressed(e);
		}
		
		
		@Override
		public void keyReleased(KeyEvent e) {
			//  当用户抬起键盘会调用此方法
			super.keyReleased(e);
			
			myTank.keyReleased(e);
		}
		
	}

}
