package basic;

public class TimerThread extends Thread {
	private int set_time;
	private int display_time;
	private long duration_time;
	private long creation_time;
	private long finish_time;
	private Stoppable sp;
	
	public TimerThread(int rt, Stoppable s) {
		set_time = display_time = rt;
		duration_time = rt * 1000;
		sp = s;
	}
	
	public TimerThread(int rt, long et, Stoppable s) {
		set_time = rt;	 
		duration_time = rt * 1000 - et; 
		display_time = (int)(duration_time / 1000);
		sp = s;
	}
	
	@Override
	public void run() {
		creation_time = System.currentTimeMillis();
		finish_time = creation_time + duration_time;
		long temp = creation_time;
		boolean is_finished_nor = false;
		while(!Thread.currentThread().isInterrupted()) {
			if (System.currentTimeMillis() - temp == 1000) {
				--display_time;
				temp = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() >= finish_time) {
				is_finished_nor = true;
				break;
			}
		}
		if (is_finished_nor) {
			sp.stop();
		}
	}
	
	public synchronized long getElapsedTime() {
		return (System.currentTimeMillis() - creation_time);
	}

	public synchronized void showTime() {
		System.out.println("Time left: "+ display_time +" seconds.");
	}
}
