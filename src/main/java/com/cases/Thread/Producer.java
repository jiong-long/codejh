package com.cases.Thread;

/**
 * 生产者
 * @author wangjinlong
 * @datetime Jul 31, 2020 10:34:32 PM
 *
 */
public class Producer extends Thread{
	private GoodsPool goodsPool = null;
	 
    public Producer(GoodsPool goodsPool) {
        this.goodsPool = goodsPool;
    }
	
	@Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("生产馒头：" + i);
            Goods goods = new Goods(i);
            goodsPool.push(goods);
        }
    }
}
