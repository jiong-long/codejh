package com.jianghu.other.Thread;

/**
 * 消费者
 * 
 * @author wangjinlong
 * @datetime Jul 31, 2020 10:36:53 PM
 *
 */
public class Consumer implements Runnable {

	private GoodsPool goodsPool = null;

	public Consumer(GoodsPool goodsPool) {
		this.goodsPool = goodsPool;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Goods goods = goodsPool.pop();
			System.out.println("消费商品：" + goods.getGoodId());
		}
	}
}
