package tankWar.tool;

import java.awt.Toolkit;

/**
 * Copyright (c) 2018年8月20日  Leon All rights reserved. 
 *
 * Description: 存放一些常量参数
 */
public class Constant {

	/**
	 * 用户电脑屏幕的宽度  
	 */
	public static final int SCREENWIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/**
	 * 用户电脑屏幕的高度
	 */
	public static final int SCREENHEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	/**
	 * 窗口体的宽度
	 */
	public static final int GAME_WIDTH = 800;
	
	/**
	 * 窗口体的高度
	 */
	public static final int GAME_HEIGHT = 600;
	
	/**
	 * X坐标与零点的差距 本来X应该是从零开始的事实上 要重23开始正好 (标题栏的高度)
	 */
	public static final int GAP = 23;
	
	/**
	 * X方向坦克前进的速度
	 */
	public static final int XSPEED = 10;
	
	/**
	 * Y方向坦克前进的速度
	 */
	public static final int YSPEED = 10;
	
	/**
	 * X方向子弹前进的速度
	 */
	public static final int MXSPEED = 20;
	
	/**
	 * Y方向子弹前进的速度
	 */
	public static final int MYSPEED = 20;
	
}
