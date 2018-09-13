package com.jianghu.domain.snakeEat.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.jianghu.domain.snakeEat.listener.SnakeListener;
import com.jianghu.domain.snakeEat.util.Global;

public class Snake {

	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;

	private int direction, newDirection;

	private Point oldTail;

	private boolean life;

	private LinkedList<Point> body = new LinkedList<Point>();

	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

	public Snake() {
		init();
	}

	public void init() {
		int x = Global.WIDTH / 2;
		int y = Global.HEIGTJ / 2;
		for (int i = 0; i < 3; i++) {
			body.addLast(new Point(x--, y));
		}
		direction = newDirection = RIGHT;
		life = true;
	}

	public void move() {
		System.out.println("snake move");
		if (body.size() != 0) {
			oldTail = body.removeLast();
			int x;
			int y;
			if (body.size() == 0) {
				x = oldTail.x;
				y = oldTail.y;
			} else {
				x = body.getFirst().x;
				y = body.getFirst().y;
			}
			if (direction + newDirection != 0) {
				direction = newDirection;
			}
			switch (direction) {
			case UP:
				y--;
				if (y < 0) {
					y = Global.HEIGTJ - 1;
				}
				break;
			case DOWN:
				y++;
				if (y >= Global.HEIGTJ) {
					y = 0;
				}
				break;
			case LEFT:
				x--;
				if (x < 0) {
					x = Global.WIDTH - 1;
				}
				break;
			case RIGHT:
				x++;
				if (x >= Global.WIDTH) {
					x = 0;
				}
				break;
			}
			Point point = new Point(x, y);
			body.addFirst(point);
		}
	}

	public Point getHead() {
		return body.getFirst();
	}

	public void changeDirection(int direction) {
		System.out.println("snake changeDirection");
		this.newDirection = direction;
	}

	public void eatFood() {
		System.out.println("snake eatFood");
		body.addLast(oldTail);
	}

	public boolean isEatBody() {
		System.out.println("snake isEatBody");
		for (int i = 1; i < body.size(); i++) {
			if (getHead().equals(body.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void drawMe(Graphics g) {
		System.out.println("snake drawMe");
		for (Point p : body) {
			g.setColor(Color.BLUE);
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, true);
		}
	}

	public void start() {
		new Thread(new SnakeDriver()).start();
	}

	public class SnakeDriver extends Thread {

		public void run() {
			while (life) {

				move();
				for (SnakeListener l : listeners) {
					l.snakeMoved(Snake.this);
				}
				try {
					Thread.sleep(Global.SLEEP);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void addSnakeListener(SnakeListener l) {
		if (l != null) {
			this.listeners.add(l);
		}
	}

	public void die() {
		life = false;
	}
}
