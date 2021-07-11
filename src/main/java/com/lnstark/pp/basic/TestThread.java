package com.lnstark.pp.basic;

public class TestThread {
	// 公共变量
	int count = (int) 1e6;

	public static void main(String[] args) {
		// new一个实现Runnable的类
		TestThread test = new TestThread();
		// 创建1个任务
		MyRunnable myRunnable1 = test.new MyRunnable();
		// 创建5个线程
		for (int i = 0; i < 4; i++) {
			new Thread(myRunnable1).start();
		}
	}

	// 创建一个实现Runnable的类
	class MyRunnable implements Runnable {
		public void run() {
			while (true) {
				// 锁住的是同一对象
				synchronized (this) {
					if (count == 0) {
						break;
					}
					System.out.println(Thread.currentThread().getName() + ":count:" + (--count));
					// 测试时，线程更容易切换
					Thread.yield();
				}

			}
		}

	}

}