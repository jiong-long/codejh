package com.cases.tetris.listener;

import com.cases.tetris.model.Shape;

/**
 * 监听图形已经到达底部
 * 
 * @author jinlong wang
 * 
 */
public interface ShapeListener {

	void shapeMoveDown(Shape shape);

	boolean ismovedownable(Shape shape);

}
