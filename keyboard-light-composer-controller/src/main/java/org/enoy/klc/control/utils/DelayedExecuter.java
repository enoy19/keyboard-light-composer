package org.enoy.klc.control.utils;

public class DelayedExecuter {

	public static void execute(final long millis, Runnable runnable){
		Thread t = new Thread(()->{
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runnable.run();
		});
		t.setDaemon(true);
		t.start();
	}
	
}
