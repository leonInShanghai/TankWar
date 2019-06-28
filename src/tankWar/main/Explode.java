package tankWar.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Copyright (c) 2018年8月26日 Leon All rights reserved. 
 *
 * Description: 处理坦克被击中之后的爆炸
 */
public class Explode {
	
	/**爆炸的坐标*/
	private int x,y;
	
	/**爆炸的生命周期*/
	private boolean live = true;
	
	/**爆炸过程原的直径*/
	int[] diameter = {4,7,12,18,26,32,49,30,14,6};
	
	/**爆炸过程的步骤*/
	private int step = 0;
	
	/**引用大管家方便传值*/
	private TankClient tc; 
	
	
	public Explode(int x, int y, TankClient tc) {
		super();
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	
	public void draw(Graphics g) {
		
		//如果生命周期为false 返回什么都不做
		if (!live) {
			//从集合中移除
			tc.explodes.remove(this);
			return;
		}
		
		//爆炸的动画进行到最后一步动画结束 步骤为0 返回
		if (step == diameter.length) {
			live = false;
			step = 0;
			return;
		}
		
		//得到原来画笔的颜色
		Color c = g.getColor();
		
		//设置画笔的颜色为橘色
		g.setColor(Color.ORANGE);
		
		//填充由指定矩形限定的椭圆
		//g.fillOval(x, y, diameter[step], diameter[step]);
		g.fillOval(x - diameter[step] / 2, y - diameter[step] / 2, diameter[step], diameter[step]);
		
		//设置画笔的颜色为原来的颜色
		g.setColor(c);
		
		//进入下一步骤
		step++;
 		
	}

}
