package com.jianghu.domain.tetris.model;

import java.awt.Color;
import java.awt.Graphics;

import com.jianghu.domain.tetris.util.Global;

/**
 * 已经下落到底部的图形
 * 
 * @author jinlong wang
 * 
 */
public class Ground {
	// 接受已经下落的图形
	private int[][] stop = new int[Global.WIDTH][Global.HEIGHT];

	public void accept(Shape shape) {
		System.out.println("接受已经下落的图形");

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape.isone(i, j, false)) {
					stop[shape.getLeft() + i][shape.getTop() + j] = 1;
				}
			}
		}
		isfullline();
	}

	// 判断是否为满行
	private void isfullline() {
		for (int i = Global.HEIGHT - 1; i >= 0; i--) {
			boolean full = true;
			for (int j = 0; j < Global.WIDTH; j++) {
				if (stop[j][i] == 0) {
					full = false;
				}
			}
			if (full == true) {
				System.out.println("出现满行.......");
				deletefullline(i);

			}
		}
	}

	// 删除满行
	private void deletefullline(int i) {
		for (int j = i; j > 0; j--) {
			for (int k = 0; k < Global.WIDTH; k++) {
				stop[k][j] = stop[k][j - 1];
			}
		}
	}

	// 判断游戏结束
	public boolean isfull() {
		for (int i = 0; i < Global.WIDTH; i++) {
			if (stop[i][0] == 1) {
				return true;
			}
		}
		return false;
	}

	// 显示以下落的图形
	public void show(Graphics graphics) {
		System.out.println("显示以下落的图形");
		graphics.setColor(Color.blue);
		for (int i = 0; i < Global.WIDTH; i++) {
			for (int j = 0; j < Global.HEIGHT; j++) {
				if (stop[i][j] == 1) {
					graphics.fill3DRect(i * Global.CELL_SIZE, j * Global.CELL_SIZE,
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

	// 判断图形是否已经碰到了边界
	public boolean ismoveable(Shape shape, int action) {
		int left = shape.getLeft();
		int top = shape.getTop();
		switch (action) {
		case Shape.LEFT:
			left--;
			break;
		case Shape.RIGHT:
			left++;
			break;
		case Shape.DOWN:
			top++;
			break;
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (shape.isone(i, j, action == Shape.CHANGE)) {
					if (top + j >= Global.HEIGHT || left + i < 0 || left + i >= Global.HEIGHT
							|| stop[left + i][top + j] == 1) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
