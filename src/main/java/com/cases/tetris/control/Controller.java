package com.cases.tetris.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.cases.tetris.listener.ShapeListener;
import com.cases.tetris.model.Ground;
import com.cases.tetris.model.Shape;
import com.cases.tetris.model.ShapeFactory;
import com.cases.tetris.view.GamePanel;

/**
 * 控制器 控制各种键盘事件的发生
 * 
 * @author jinlong wang
 * 
 */
// 继承KeyAdapter控制各种事件的发生
public class Controller extends KeyAdapter implements ShapeListener {

	private Shape shape;
	private ShapeFactory factory;
	private Ground ground;
	private GamePanel panel;

	@Override
	// 当键盘事件发生相应的处理办法
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {// 获取发生的键盘事件
		// 图形每次移动之前都要判断是否碰到了边界
		case KeyEvent.VK_UP:
			if (ground.ismoveable(shape, Shape.CHANGE)) {
				shape.change();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (ismovedownable(shape)) {
				shape.down();
			}
			break;
		case KeyEvent.VK_LEFT:
			if (ground.ismoveable(shape, Shape.LEFT)) {
				shape.left();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (ground.ismoveable(shape, Shape.RIGHT)) {
				shape.right();
			}
			break;
		}
		panel.dispaly(ground, shape);
	}

	// 图形每下落一次就重新显示一下界面
	@Override
	public void shapeMoveDown(Shape shape) {
		panel.dispaly(ground, shape);
	}

	// 判断图形是否能否在继续下落,该方法必须是线程同步的
	@Override
	public synchronized boolean ismovedownable(Shape shape) {
		if (this.shape != shape) {
			return false;
		}
		if (ground.ismoveable(shape, Shape.DOWN)) {
			return true;
		}
		// 如果图形到达底部，则应该产生一个新的图形
		ground.accept(this.shape);// this代表当前类
		if (!ground.isfull()) {
			this.shape = factory.getshape(this);
		}
		return false;
	}

	// 有图形开始下落表示开始了一个新游戏
	public void newgame() {
		shape = factory.getshape(this);
	}

	// 外界传递参数。需要通过构造方法
	public Controller(ShapeFactory factory, Ground ground, GamePanel panel) {
		super();
		this.factory = factory;
		this.ground = ground;
		this.panel = panel;
	}

}
