package com.jianghu.domain.tetris.listener;

import com.jianghu.domain.tetris.model.Shape;

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
