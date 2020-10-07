package com.cases.tetris.model;

import java.awt.Color;
import java.awt.Graphics;

import com.cases.tetris.listener.ShapeListener;
import com.cases.tetris.util.Global;

/**
 * 图形相关方法
 * 
 * @author jinlong wang
 * 
 */
public class Shape {

	public static final int CHANGE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;

	private int[][] body;// 表示图形的几种状态
	private int statement;// 表示当前的图形状态

	private int left;// 表示图形激励左边界的距离
	private int top;// 表示图形距离上边界的距离

	private ShapeListener listener;

	// 图形左移
	public void left() {
		System.out.println("shape left");
		left--;
	}

	// 图形右移
	public void right() {
		System.out.println("shape right");
		left++;
	}

	// 图形下落
	public void down() {
		System.out.println("shape dowm");
		top++;
	}

	// 图形变换
	public void change() {
		System.out.println("Shape change");
		statement = (statement + 1) % 4;
	}

	// 图形显示
	public void show(Graphics graphics) {
		System.out.println("shape show");
		graphics.setColor(Color.red);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (isone(i, j) == true) {// 当结果为true绘制图形
					graphics.fill3DRect((i + left) * Global.CELL_SIZE,
							(j + top) * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}

	// 判断当前的坐标是否为1
	private boolean isone(int x, int y) {
		return body[statement][y * 4 + x] == 1;
	}

	// 判断当前的坐标是否为1
	public boolean isone(int x, int y, boolean action) {
		int state = statement;
		if (action == true) {
			state = (statement + 1) % 4;
		}
		return body[state][y * 4 + x] == 1;
	}

	// 使图形不断下落
	private class shapedown implements Runnable {
		@Override
		public void run() {
			while (listener.ismovedownable(Shape.this)) {
				down();
				// 内部类用到外部类使用类名.this
				listener.shapeMoveDown(Shape.this);
				try {
					// 休息的时间就是图形下落的过程所有的时间
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 当图形创建出来就来启动线程，是图形下落
	public Shape() {
		new Thread(new shapedown()).start();
	}

	// 注册监听器
	public void addshapeListener(ShapeListener listener) {
		// 声明了一个监听器对象，将其赋值给当前的成员变量
		if (listener != null) {
			this.listener = listener;
		}
	}

	public int[][] getBody() {
		return body;
	}

	public void setBody(int[][] body) {
		this.body = body;
	}

	public int getStatement() {
		return statement;
	}

	public void setStatement(int statement) {
		this.statement = statement;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
