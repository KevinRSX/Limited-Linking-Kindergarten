package timer;

import java.awt.Color;

import javax.swing.JProgressBar; 

public class TimerThread extends Thread {
	private Object obj;
	private Stoppable sp;
	private int display_time;
	private int set_time;
	private int e_time;
	private long duration_time;
	private long creation_time;
	private long finish_time;
	
	public TimerThread(Object obj, Stoppable sp, int rt) {
		this.obj = obj;
		this.sp = sp;
		e_time = 0;
		set_time = display_time = rt;
		duration_time = rt * 1000;
		this.setPriority(10);
	}
	
	public TimerThread(Object obj, Stoppable sp, int rt, long et) {
		this.obj = obj;
		this.sp = sp;
		set_time = rt;	 
		e_time = (int)(et / 1000);
		duration_time = rt * 1000 - et; 
		display_time = (int)(duration_time / 1000);
		this.setPriority(10);
	}
	
	// specific designed for obj:JProgressBar.
	@Override
	public void run() {
		JProgressBar jpb = (JProgressBar) obj;
		jpb.setValue(e_time);
		creation_time = System.currentTimeMillis();
		finish_time = creation_time + duration_time;
		long temp = creation_time;
		boolean is_finished_nor = false;
		while(!Thread.currentThread().isInterrupted()) {
			if (System.currentTimeMillis() - temp == 1000) {
				--display_time;
				System.out.println(display_time);
				jpb.setValue(jpb.getValue() + 1);
				temp = System.currentTimeMillis();
			}
			if (jpb.getValue() > set_time * 0.7) {
				jpb.setForeground(Color.RED);
			} else {
				jpb.setBackground(new Color(238,226,29));
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
