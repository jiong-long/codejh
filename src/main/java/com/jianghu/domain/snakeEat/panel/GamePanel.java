package com.jianghu.domain.snakeEat.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.apache.ibatis.type.Alias;

import com.jianghu.domain.snakeEat.domain.Food;
import com.jianghu.domain.snakeEat.domain.Ground;
import com.jianghu.domain.snakeEat.domain.Snake;
import com.jianghu.domain.snakeEat.util.Global;

@Alias("GamePanel2")
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1130677437761849334L;
	private Snake snake;
	private Food food;
	private Ground ground;

	public void display(Snake snake, Food food, Ground ground) {
		System.out.println("GamePanel display");
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGTJ);
		if (ground != null && food != null && snake != null) {
			this.ground.drawMe(g);
			this.food.drawMe(g);
			this.snake.drawMe(g);
		}
	}

}
