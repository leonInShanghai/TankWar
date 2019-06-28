package tankWar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

/**
 * Copyright (c) 2018年8月28日 Leon All rights reserved. 
 *
 * Description: 降落伞（血块）英雄坦克吃了生命增加
 */
public class Blood {
	
	/**降落伞类的坐标宽高*/
	private int x,y,w,h;
	
	/**引用大管家方便传值*/
	private TankClient tc; 
	
	/**降落伞（血块）的生命变量*/
	private boolean live = true;
	
	/**存储血块（降落伞）运动轨迹的二维数组*/
	private int[][] pos = { 
			{350,300},{390,300},{575,275},{600,200},{760,270},{665,290},{640,280},{600,590},{700,520}
	}; 
	
	/**降落伞漂浮的步骤*/
	private int step = new Random().nextInt(pos.length);//0;
	
	
	public Blood() {
		super();
		this.x = pos[0][0];
		this.y = pos[0][1];
		this.w = 20;
		this.h = 20;
	}


	/**
	 * @param g : 传递过来的画笔 自己画自己的方法
	 */
	public void draw(Graphics g) {
		
		if (!live) return;
		
		//得到画笔原来的颜色
		Color c = g.getColor();
		
		//设置画笔的颜色
		g.setColor(Color.MAGENTA);
		
		//原来画出来的血块道具（不好看）
		//g.fillRect(x, y, w, h);
		
		//Leon在网上找的图片
		ImageIcon icon = new ImageIcon("image\\parachute.png");
		Image img = icon.getImage();
		g.drawImage(img, x,y,null);
		
		//设置回原来的颜色
		g.setColor(c);
		
		move();
	}
	
	
	/**血块移动的方法*/
	private void move() {
		//step++;//++后降落伞（血块）就会动态运动
		//避免数组越界
		if (step >= pos.length) step = 0; 
		this.x = pos[step][0];
		this.y = pos[step][1];
	}
	
	
	/**
	 * 返回降落伞矩形框 做碰撞检测使用 
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
	
	
	/**
	 * @return : 降落伞的生命 Boolean值
	 */
	public boolean isLive() {
		return live;
	}
	
	
	/**
	 * 设置降落伞（血块）的生命的方法
	 * @param live
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
}
