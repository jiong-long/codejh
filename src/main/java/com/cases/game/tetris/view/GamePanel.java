package com.cases.game.tetris.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.cases.game.tetris.model.Ground;
import com.cases.game.tetris.model.Shape;
import com.cases.game.tetris.util.Global;

/**
 * 显示主界面的面板
 * 
 * @author jinlong wang
 * 
 */
public class GamePanel extends JPanel {
	/**
	 * @creatTime 2017年5月28日 下午6:22:15
	 * @author jinlong
	 */
	private static final long serialVersionUID = 1L;

	private Ground ground;
	private Shape shape;

	public void dispaly(Ground ground, Shape shape) {
		System.out.println("面板显示");
		this.ground = ground;
		this.shape = shape;
		this.repaint();
	}

	@Override
	// 当java认为需要重新绘制组件的时候由java调用
	// 例如在程序中repaint();或者程序窗口最小化，然后恢复。或者程序窗口被遮挡，又显现的时候
	protected void paintComponent(Graphics g) {
		// 必须把之前的图形擦掉
		g.setColor(getBackground());
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);

		if (shape != null && ground != null) {
			ground.show(g);
			shape.show(g);
		}
	}

	// 在构造的时候设置面板的大小
	public GamePanel() {
		this.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
	}
}
