package com.cases.game.snakeEat.controllor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.apache.ibatis.type.Alias;

import com.cases.game.snakeEat.domain.Food;
import com.cases.game.snakeEat.domain.Ground;
import com.cases.game.snakeEat.domain.Snake;
import com.cases.game.snakeEat.listener.SnakeListener;
import com.cases.game.snakeEat.panel.GamePanel;

@Alias("Controller2")
public class Controller extends KeyAdapter implements SnakeListener {

	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;

	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}

	@Override
	public void snakeMoved(Snake snake) {
		gamePanel.display(snake, food, ground);
		if (food.isSnakeEatFood(snake)) {
			snake.eatFood();
			food.newFood(ground.getPoint());
		}
		if (ground.isSnakeEatRock(snake)) {
			snake.die();
		}
		if (snake.isEatBody()) {
			snake.die();
		}
	}

	public void newGame() {
		snake.start();
		food.newFood(ground.getPoint());
	}
}
