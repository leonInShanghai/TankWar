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
 * Copyright (c) 2018��8��20��  Leon All rights reserved. 
 *
 * Description: ̹�˶��� goodΪ��ʱ��Ӣ��̹��  Ϊ��ʱ�ǵ���̹��
 */
public class Tank {
	
	/**̹�˵Ŀ��*/
	public static int WIDTH;
	
	/**̹�˵ĸ߶�*/
	public static int HEIGHT;
	
	/**̹�˵��������� Ĭ��Ϊtrue������Ϊfalse*/
	private boolean live = true;
	
	/**Ӣ��̹��ͷ����Ѫ��*/
	private BloodBar bb = new BloodBar();
	
	/**Ӣ��̹�˵�����ֵ ÿ�α��򵽼���20*/
	private int life = 100;
	
	/**���ô�ܼҷ��㴫ֵ*/
	private TankClient tc; 
	
	/**�������ֵ��ҵı���   Ӣ��̹�˺͵���̹�˶��������*/
	private boolean good;
	
	/**̹�˵�����*/
	private int x,y;
	
	/**̹�˵���һ������*/
	private int oldX,oldY;
	
	/**�����������*/
	private static Random r = new Random();
	
	/**̹�˳�  ���� ����  ���� ����*/
	private boolean bL = false,bU = false,bR = false,bD = false;
	
	/**̹�˵� �˸����� ö��  L:��,LU������,U����,RU������,R����,RD������,D����,LD������,STOP:ͣ*/
	enum Direction{
		L,LU,U,RU,R,RD,D,LD,STOP
	}
	
	/**̹�˵� �˸����� ö��  L:��,LU������,U����,RU������,R����,RD������,D����,LD������,Ĭ�ϣ�STOP*/
	private Direction dir = Direction.STOP;
	
	/**̹����Ͳ�ķ���  ö��  L:��,LU������,U����,RU������,R����,RD������,D����,LD������,Ĭ�ϣ�D*/
	private Direction ptDir = Direction.D;
	
	/**�ط�̹������ƶ��Ĳ���*/
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
	 * ̹���Լ����Լ��ķ��� 
	 * @param g ���ݹ����Ļ���
	 */
	public void draw(Graphics g) {
		
		//����̹���Ѿ������� 
		if (!live) {
		
			//���˱�����
			if (!good) {
				//�ӵ���̹�˼������Ƴ�
				tc.tanks.remove(this);
			}
			
			//�����ٻ�̹��
			return;
		}

		//ȡ������ԭ������ɫ
		Color c = g.getColor();
		
		//���û�����ɫΪ��ɫ
		g.setColor(Color.RED);
		
		if (good) bb.draw(g);
		
		//Leon�����ҵ�ͼƬ��ֱ������icon = new ImageIcon("image\\self0.png");
		ImageIcon icon = null;
		
		
		//������Ͳ�ķ��򻭳���Ͳ
		switch (ptDir) {
		case L:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self90.png");
			}else {
				icon = new ImageIcon("image/enemy90.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
			break;
		case LU:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self135.png");
			}else {
				icon = new ImageIcon("image/enemy135.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);
			break;	
		case U:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self180.png");
			}else {
				icon = new ImageIcon("image/enemy180.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);
			break;
		case RU:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self225.png");
			}else {
				icon = new ImageIcon("image/enemy225.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);
			break;	
		case R:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self270.png");
			}else {
				icon = new ImageIcon("image/enemy270.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);
			break;	
		case RD:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self315.png");
			}else {
				icon = new ImageIcon("image/enemy315.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);
			break;	
		case D:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self0.png");
			}else {
				icon = new ImageIcon("image/enemy0.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);
			break;	
		case LD:
			//ͼƬ̹��
			if (good) {
				icon = new ImageIcon("image/self45.png");
			}else {
				icon = new ImageIcon("image/enemy45.png");
			}
			//����ͼ��̹�˵���Ͳ
			//g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT);
			break;
		case STOP:
			//���ﲻ���κ���Ϊ��������ɫ����
		    break;
		}
		
		//�Լ����ĺÿ���ͼƬ̹��
		Image img = icon.getImage();
		WIDTH = img.getWidth(null);
		HEIGHT = img.getHeight(null);
		g.drawImage(img, x,y,null);
		
		//��ͼ�ε�̹�� ��ª
		//g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		move();
	}


	/**
	 * ���ݵ�ǰ�ķ�����ǰ�ƶ�-�û����Ʒ���̹���Լ���
	 */
	private void move(){
		
		//��¼̹���ƶ�ǰ��λ��
		this.oldX = x;
		this.oldY = y;
		
		switch (dir) {
		case L:
			x -= Constant.XSPEED;
			break;
		case LU:
			//б���ٶ���  �� ��2
			x -= Constant.XSPEED / Math.sqrt(2);
			y -= Constant.YSPEED / Math.sqrt(2);
			break;	
		case U:
			y -= Constant.YSPEED;
			break;
		case RU:
			//б���ٶ���  �� ��2
			x += Constant.XSPEED / Math.sqrt(2);
			y -= Constant.YSPEED / Math.sqrt(2);
			break;	
		case R:
			x += Constant.XSPEED;
			break;	
		case RD:
			//б���ٶ���  �� ��2
			x += Constant.XSPEED / Math.sqrt(2);
			y += Constant.YSPEED / Math.sqrt(2);
			break;	
		case D:
			y += Constant.YSPEED;
			break;	
		case LD:
			//б���ٶ���  �� ��2
			x -= Constant.XSPEED / Math.sqrt(2);
			y += Constant.YSPEED / Math.sqrt(2);
			break;	
		case STOP:
			//ֹͣ��ʱ��ʲô������
			break;
		}
		
		//����̹��ǰ���ķ������̹����Ͳ�ķ���ͷ���ģ�
		if (dir != Direction.STOP) {
			ptDir = dir;
		}
		
		//����̹�˳��������
		if (x < 0) { x = 0; }
		if (y < Constant.GAP) { y = Constant.GAP; }
		if (x + Tank.WIDTH > Constant.GAME_WIDTH) { x = Constant.GAME_WIDTH - Tank.WIDTH; }
		if (y + Tank.HEIGHT > Constant.GAME_HEIGHT) { y = Constant.GAME_HEIGHT - Tank.HEIGHT; }
		
		//����̹�����������
		if (!good) {
			
			//ö��ת����
			Direction[] dirs = Direction.values();
			
			//����ĵ���̹������ı䷽��
			if (step == 0) {
				step = r.nextInt(12) + 3;
				
				//����������������������
				int rn = r.nextInt(dirs.length);
				
				//����ı�̹�˵ķ���
				dir = dirs[rn];
			}
			
			step--;
			
			//����̹�˿���
			if (r.nextInt(40) > 38) {
				fire();
			}
		}
		
	}
	
	
	/**
	 * ͣ����ԭ�صķ���-�������̹��ײ��ǽ
	 */
	private void stay() {
		x = oldX;
		y = oldY;
	}
	
	
	/**
	 * Ӣ��̹�˸����û����µļ���ִ���ж��ϵ�����
	 * @param e:�û����µİ���Ŀǰ�������� �� �� �� �� 
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
	 * �����û����µļ��̰��� ����� ̹��ǰ���� 8������
	 */
	private void locateDirection() {
		
		if (bL && !bU && !bR && !bD) {//�û�ֻ���������
			dir = Direction.L;
		}else
		
		if (bL && bU && !bR && !bD) {//�û�ͬʱ�������� �� ��
			dir = Direction.LU;
		}else
		
		if (!bL && bU && !bR && !bD) {//�û�ֻ�������ϼ�
			dir = Direction.U;
		}else
		
		if (!bL && bU && bR && !bD) {//�û�ͬʱ�������� �� ��
			dir = Direction.RU;
		}else
		
		if (!bL && !bU && bR && !bD) {//�û�ֻ�������Ҽ�
			dir = Direction.R;
		}else
		
		if (!bL && !bU && bR && bD) {//�û�ͬʱ�������� �� ��
			dir = Direction.RD;
		}else
		
		if (!bL && !bU && !bR && bD) {//�û�ֻ�������¼�
			dir = Direction.D;
		}else
		
		if (bL && !bU && !bR && bD) {//�û�ͬʱ�������� �� ��
			dir = Direction.LD;
		}else
		
		if (!bL && !bU && !bR && !bD) {//�û�ʲô��û�а�
			dir = Direction.STOP;
		}
		
	}
	
	
	/**
	 * Ӣ��̹�˸����û�̧��ļ���ִֹͣ���ж��ϵ�����
	 * @param e:�û����µİ���Ŀǰ�������� �� �� �� �� ctrl
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
	 * ̹�˿����ܵķ���
	 * @return m:�ڵ���
	 */
	public Missile fire() {
		
		//��� ̹���Ѿ����� �����ٷ��ӵ� ��Ҫ�����Ӣ��
		if (!live) return null;
		
		Missile m = new Missile(x + Tank.WIDTH / 2 - Missile.WIDTH / 2, y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2, good,ptDir, tc);
		tc.missiles.add(m);
		return m;
	}
	
	
	/**
	 * ��ĳ���������ڵ�
	 * @param dir������
	 */
	public Missile fire(Direction dir) {
		//��� ̹���Ѿ����� �����ٷ��ӵ� ��Ҫ�����Ӣ��
		if (!live) return null;
		
		Missile m = new Missile(x + Tank.WIDTH / 2 - Missile.WIDTH / 2, y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2, good,dir, tc);
		tc.missiles.add(m);
		return m;
	}
	
	
	/**
	 * Ϊ��ײ�������
	 * @return һ��̹�˵�[��]�����Σ�����
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	
	/**
	 * ����̹�� ����
	 * @param live
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	/**
	 * ̹�˵�������getter����
	 * @return live̹�˵�����Boolean����
	 */
	public boolean isLive() {
		return live;
	}
	

	/**
	 * @return ̹�����ڵ��˻���Ӣ��  ���˵��ӵ��Ե���û���˺� Ӣ�۵��ӵ����Լ�Ҳû���˺�
	 */
	public boolean isGood() {
		return good;
	}

	
	/**
	 * ̹�˸�ǽ��ײ����ײ���
	 * @param w ���ݹ�����ǽ
	 * @return ��ײ���Ľ��
	 */
	public boolean collidesWithWall(Wall w) {
		if (live && this.getRect().intersects(w.getRect())) {
			//����ǽ��̹�˻ص��ƶ�ǰ��λ��
			stay();
			return true;
		}
		return false;
	}
	
	
	/**
	 * �������̹���Ƿ��໥����-��������̹�˻��ഩ���Է�
	 * @return ��ײ���Ľ��
	 */
	public boolean collodesWithTanks(List<Tank> tanks) {
		
		//����̹�˼��� �Ա�����̹���Ƿ� �н���
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t ) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					//����̹�˽��� ���ص���һ��λ��
					this.stay();
					t.stay();
					return true;	
				}
			}
		}
		return false;
	}
	
	
	/**
	 * ���䳬���ڵ�
	 */
	private void superFire() {
		//ö��ת����
		Direction[] dirs = Direction.values();
		
		//����8�����򿪻�
		for (int i = 0; i < dirs.length -1; i++) {
			fire(dirs[i]);
		}
	}
	
	
	/**
	 * Ӣ��̹�˱����˻��� ���ã����٣�����ֵ
	 * @param life��Ӣ��̹�˵�����ֵ
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	
	/**
	 * @return Ӣ��̹�˵�����ֵ
	 */
	public int getLife() {
		return life;
	}
	
	
	/**
	 * Description: �ڲ��� Ӣ��̹�˵�Ѫ��
	 * @author Leon 
	 * @date 2018��8��28��
	 */
	private class BloodBar {
		
		/**
		 * Ѫ�����Լ��ķ���
		 */
		public void draw(Graphics g) {
			
			//�õ����ʵ���ɫ
			Color c = g.getColor();
			
			g.setColor(Color.RED);
			
			//��һ�����ĵ�Ѫ����
			g.drawRect(x, y - 10, WIDTH, 10);
			
			//����Ӣ�۵�Ѫ������� Ѫ���Ŀ��
			int w = WIDTH * life / 100;
			
			//����Ӣ��̹�˵�Ѫ��
			g.fillRect(x, y - 10, w, 10);
			
			//���ûػ���ԭ������ɫ
			g.setColor(c);
		}
		
	}
	
	
	/**
	 *Ӣ��̹�˳�Ѫ��ķ��� 
	 */
	public boolean eat(Blood b) {
		if (good && this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			
			//��Ӣ������ֵ100%
			this.setLife(100);
			
			//�ý���ɡ��Ѫ�飩��ʧ
			b.setLive(false);
			
			return true;
		}
		return false;
	}
	
}
