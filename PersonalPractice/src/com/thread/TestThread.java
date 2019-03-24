package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.tool.util;

public class TestThread {
	
	private Object t[] = new Object[8];
	
	volatile private int size = 0;
	
	public static void main(String[] args) {
		TestThread t = new TestThread();
		final Object lock = new Object();
		CountDownLatch latch = new CountDownLatch(1);
		new Thread(() -> {
			if(t.size() != 5) {
				try {
					
					latch.await();
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("size is 5 detected");
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		new Thread(() -> {
				for(int i = 0; i < 10; i++) {
					t.add(i);
					if(t.size() == 5) {
						latch.countDown();
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		).start();
		
		
	}
	
	void add(Object o) {
		t[size++] = o;
		if(size == t.length) {
			Object newt[] = new Object[t.length * 2];
			for(int i = 0; i < size; i++) {
				newt[i] = t[i];
			}
			t = newt;
		}
		System.out.println("after added, size is "+size);
//		if(size == 5) {
//			System.out.println("after added, size is "+size);
//		}
	}
	
	synchronized void print() {
		for(int i = 0; i < size; i++) {
			System.out.print(t[i] + " ");
		}
	}
	
	int size() {
		return size;
	}
}
