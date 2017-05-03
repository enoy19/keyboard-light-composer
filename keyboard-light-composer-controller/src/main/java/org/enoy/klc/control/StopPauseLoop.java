package org.enoy.klc.control;

public abstract class StopPauseLoop implements Runnable {

	private volatile boolean stopped = false;
	private volatile boolean paused = false;
	private volatile long sleepLoopCycle;
	private volatile long sleepPaused;
	private PauseListener onPause;

	public StopPauseLoop() {
		this.sleepLoopCycle = 20;
		this.sleepPaused = 100;
		this.paused = true;
	}

	public abstract void executeLoop(double delta);

	@Override
	public void run() {
		long time = System.currentTimeMillis();

		while (!isStopped() && !Thread.currentThread().isInterrupted()) {
			while (!isPaused() && !Thread.currentThread().isInterrupted()) {
				long deltaLong = System.currentTimeMillis() - time;
				time = System.currentTimeMillis();
				double delta = (double) deltaLong / 1000f;

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
