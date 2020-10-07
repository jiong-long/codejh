package com.cases.Thread;

public class MainTest {
	
	public static void main(String[] args) {
		//缓冲区
		GoodsPool goodsPool = new GoodsPool();
		//消费者
		Consumer consumer = new Consumer(goodsPool);
		//生产者
		Producer producer = new Producer(goodsPool);
		
		Thread threadCon = new Thread(consumer);
		Thread threadPro = new Thread(producer);
		threadCon.start();
		threadPro.start();
	}
}
