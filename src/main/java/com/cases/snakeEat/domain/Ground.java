package com.cases.snakeEat.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import org.apache.ibatis.type.Alias;

import com.cases.snakeEat.util.Global;

@Alias("Ground2")
public class Ground {
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGTJ];

	public Ground() {
		for (int i = 0; i < Global.WIDTH; i++) {
			rocks[0][i] = 1;
			rocks[Global.HEIGTJ - 1][i] = 1;
		}
		for (int j = 0; j < Global.HEIGTJ; j++) {
			rocks[j][0] = 1;
			rocks[j][Global.WIDTH - 1] = 1;
		}
	}

	public boolean isSnakeEatRock(Snake snake) {
		System.out.println("Food isSnakeEatRock");
		for (int i = 0; i < Global.WIDTH; i++) {
			for (int j = 0; j < Global.HEIGTJ; j++) {
				if (rocks[i][j] == 1 && (i == snake.getHead().x && j == snake.getHead().y)) {
					return true;
				}
			}
		}
		return false;
	}

	public Point getPoint() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGTJ);
		} while (rocks[x][y] == 1);
		return new Point(x, y);
	}

	public void drawMe(Graphics g) {
		System.out.println("Ground  drawME");
		for (int i = 0; i < Global.WIDTH; i++) {
			for (int j = 0; j < Global.HEIGTJ; j++) {
				if (rocks[i][j] == 1) {
					g.setColor(Color.RED);
					g.fill3DRect(i * Global.CELL_SIZE, j * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
}
