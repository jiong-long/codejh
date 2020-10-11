package com.jianghu.web.action.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.cases.game.snakeEat.controllor.Controller;
import com.cases.game.snakeEat.domain.Food;
import com.cases.game.snakeEat.domain.Ground;
import com.cases.game.snakeEat.domain.Snake;
import com.cases.game.snakeEat.panel.GamePanel;
import com.cases.game.snakeEat.util.Global;
import com.cases.game.tetris.model.ShapeFactory;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 游戏相关Action
 * 
 * @creatTime 2017年5月28日 下午6:33:06
 * @author jinlong
 * 
 */
public class GameAction extends ActionSupport {
	/**
	 * @creatTime 2017年5月28日 下午6:33:00
	 * @author jinlong
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 贪吃蛇
	 * 
	 * @creatTime 2017年5月28日 下午6:56:15
	 * @author jinlong
	 * @return
	 */
	public String snakeEat() {
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(snake, food, ground, gamePanel);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGTJ * Global.CELL_SIZE);
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 15, Global.HEIGTJ * Global.CELL_SIZE + 35);
		frame.add(gamePanel, BorderLayout.CENTER);

		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		frame.addKeyListener(controller);

		frame.setVisible(true);
		controller.newGame();

		return NONE;
	}

	/**
	 * 俄罗斯方块
	 * 
	 * @creatTime 2017年5月28日 下午6:56:42
	 * @author jinlong
	 * @return
	 */
	public String tetris() {
		ShapeFactory factory = new ShapeFactory();
		com.cases.game.tetris.model.Ground ground = new com.cases.game.tetris.model.Ground();
		com.cases.game.tetris.view.GamePanel panel = new com.cases.game.tetris.view.GamePanel();

		com.cases.game.tetris.control.Controller controller = new com.cases.game.tetris.control.Controller(
				factory, ground, panel);

		JFrame frame = new JFrame();
		// 点击叉时关闭窗体,关闭所有窗体，会将weblogic服务关闭
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 关闭当前窗体
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 使窗体的大小比游戏的大小稍大
		frame.setSize(panel.getSize().width + 16, panel.getSize().height + 40);
		// 将游戏窗口添加到窗体中
		frame.add(panel);
		// 为游戏窗口添加键盘事件
		panel.addKeyListener(controller);
		// 为窗体添加键盘事件
		frame.addKeyListener(controller);
		frame.setTitle("俄罗斯方块");
		frame.setVisible(true);
		controller.newgame();

		return NONE;
	}
}
