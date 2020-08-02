package com.jianghu.other.Thread;

/**
 * 缓冲区
 * @author wangjinlong
 * @datetime Jul 31, 2020 10:37:46 PM
 *
 */
public class GoodsPool {
	private int currId = 0;
    Goods[] goodPool = new Goods[10];
    
    public synchronized void push(Goods goods) {
    	while(currId == goodPool.length) {
    		try {
    			// wait后，线程会将持有的锁释放，进入阻塞状态；
    			// 这样其它需要锁的线程就可以获得锁；
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	// 唤醒在当前对象等待池中等待的第一个线程。
        // notifyAll叫醒所有在当前对象等待池中等待的所有线程。
    	goodPool[currId++] = goods;
    	this.notify();
    }
    
    public synchronized Goods pop() {
    	while(currId == 0) {
    		try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	this.notify();
    	return goodPool[--currId];
    }
}
