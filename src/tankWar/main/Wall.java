package tankWar.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Copyright (c) 2018年8月28日 Leon All rights reserved. 
 *
 * Description: 坦克大战中的墙
 */
public class Wall {
	
	/**墙的坐标位置4个点*/
	int x,y,w,h;
	
	/**引用大管家方便以后传值*/
	TankClient tc;
	
	
	public Wall(int x, int y, int w, int h, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	
	
	/**
	 * 画墙的方法
	 * @param g 传递过来的画笔
	 */
	public void draw(Graphics g) {
		g.fillRect(x, y, w, h);
	}
	
	
	/**
	 * @return 墙的位置形成的矩形 做碰撞检测用
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}

}
