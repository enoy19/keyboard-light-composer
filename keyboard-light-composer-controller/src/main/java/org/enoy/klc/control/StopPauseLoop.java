package org.enoy.klc.control;

import org.enoy.klc.common.Resettable;

public abstract class StopPauseLoop implements Runnable, Resettable {

	private volatile boolean stopped = false;
	private volatile boolean paused = false;
	private volatile long sleepLoopCycle;
	private volatile long sleepPaused;
	private PauseListener onPause;
	private boolean reset;

	public StopPauseLoop() {
		this.sleepLoopCycle = 20;
		this.sleepPaused = 100;
		this.paused = true;
	}

	public abstract void executeLoop(double delta);

	@Override
	public void run() {
		while (!isStopped() && !Thread.currentThread().isInterrupted()) {
			long time = System.currentTimeMillis();
			reset = false;
			while (!isPaused() && !Thread.currentThread().isInterrupted()) {
				long deltaLong = System.currentTimeMillis() - time;
				time = System.currentTimeMillis();
				double delta = (double) deltaLong / 1000f;
				
				if(!reset){
					reset = true;
					reset();
				}
				
				executeLoop(delta);

				try {
					Thread.sleep(sleepLoopCycle);
				} catch (InterruptedException e) {
					setStopped(true);
					setPaused(true);
				}
			}
			try {
				Thread.sleep(sleepPaused);
			} catch (InterruptedException e) {
				setStopped(true);
			}
		}

	}
	
	public long getSleepLoopCycle() {
		return sleepLoopCycle;
	}

	public void setSleepLoopCycle(long sleepLoopCycle) {
		this.sleepLoopCycle = sleepLoopCycle;
	}

	public long getSleepPaused() {
		return sleepPaused;
	}

	public void setSleepPaused(long sleepPaused) {
		this.sleepPaused = sleepPaused;
	}

	public boolean isStopped() {
		return stopped;
	}
	
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		if(this.paused != paused){
			this.paused = paused;
			if(onPause != null){
				onPause.onPause(paused);
			}
		}
	}
	
	public void setOnPause(PauseListener onPause) {
		this.onPause = onPause;
	}
	
	public static interface PauseListener{
		
		public void onPause(boolean pause);
		
	}

}
