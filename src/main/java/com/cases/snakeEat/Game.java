package com.cases.snakeEat;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.apache.ibatis.type.Alias;

import com.cases.snakeEat.controllor.Controller;
import com.cases.snakeEat.domain.Food;
import com.cases.snakeEat.domain.Ground;
import com.cases.snakeEat.domain.Snake;
import com.cases.snakeEat.panel.GamePanel;
import com.cases.snakeEat.util.Global;

@Alias("Game2")
public class Game {
	public static void main(String[] args) {
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(snake, food, ground, gamePanel);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGTJ * Global.CELL_SIZE);
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 15, Global.HEIGTJ * Global.CELL_SIZE + 35);
		frame.add(gamePanel, BorderLayout.CENTER);

		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		frame.addKeyListener(controller);

		frame.setVisible(true);
		controller.newGame();
	}
}
