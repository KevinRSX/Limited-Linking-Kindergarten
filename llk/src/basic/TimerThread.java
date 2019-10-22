package basic;

public class TimerThread extends Thread {
	private int set_time;
	private int display_time;
	private long duration_time;
	private long creation_time;
	private long finish_time;
	
	public TimerThread(int rt) {
		set_time = display_time = rt;
		duration_time = rt * 1000;
	}
	
	public TimerThread(int rt, long et) {
		set_time = rt;	 
		duration_time = rt * 1000 - et;
		display_time = (int)(duration_time / 1000);
	}
	
	@Override
	public void run() {
		creation_time = System.currentTimeMillis();
		finish_time = creation_time + duration_time;
		long temp = creation_time;
		System.out.println(display_time);
		while(!Thread.currentThread().isInterrupted()) {
			if (System.currentTimeMillis() - temp == 1000) {
				System.out.println(--display_time);
				temp = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() >= finish_time) {
				break;
			}
		}
		// successfully finished task, notify Game
	}
	
	public synchronized long getElapsedTime() {
		return (System.currentTimeMillis() - creation_time);
	}
	/*
	private class TimerThread extends Thread {
		private int re_time_sec = 0;
		private long creation_time;
		private long finish_time;
		
		private TimerThread(int rt) {
			re_time_sec = rt;
		}
		
		@Override
		public void run() {
			creation_time = System.currentTimeMillis();
			finish_time = creation_time + re_time_sec * 1000;
			long temp = creation_time;
			System.out.println(re_time_sec);
			while(true) {
				if (System.currentTimeMillis() - temp == 1000) {
					System.out.println(--re_time_sec);
					temp = System.currentTimeMillis();
				}
				if (System.currentTimeMillis() >= finish_time)
					break;
			}
			// successfully finished task, notify Game
		}
	}*/
}
