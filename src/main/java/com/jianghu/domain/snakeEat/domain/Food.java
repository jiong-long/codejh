package com.jianghu.domain.snakeEat.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.jianghu.domain.snakeEat.util.Global;

public class Food extends Point {
	private static final long serialVersionUID = -545532043981820040L;

	public boolean isSnakeEatFood(Snake snake) {
		System.out.println("Food isSnakeEatFood");
		return this.equals(snake.getHead());
	}

	public void drawMe(Graphics g) {
		System.out.println("Food drawME");
		g.setColor(Color.BLUE);
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE,
				Global.CELL_SIZE, true);
	}

	public void newFood(Point p) {
		this.setLocation(p);
	}
}
