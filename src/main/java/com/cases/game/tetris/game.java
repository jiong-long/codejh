package com.cases.game.tetris;

import javax.swing.JFrame;

import com.cases.game.tetris.control.Controller;
import com.cases.game.tetris.model.Ground;
import com.cases.game.tetris.model.ShapeFactory;
import com.cases.game.tetris.view.GamePanel;

public class game {

	public static void main(String[] args) {
		ShapeFactory factory = new ShapeFactory();
		Ground ground = new Ground();
		GamePanel panel = new GamePanel();

		Controller controller = new Controller(factory, ground, panel);

		JFrame frame = new JFrame();
		// 点击叉时关闭窗体
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}
}
