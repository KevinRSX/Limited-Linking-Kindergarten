package basic;

public class Timer {
	private static Timer ti = new Timer();
	private TimerThread timer;
	private int set_time;
	private int bonus_time;
	private long elapsed_time;
	private boolean is_paused;
	private boolean is_running;
	
	private Timer() {
		set_time = bonus_time = 0;
		elapsed_time = 0L;
		is_running = is_paused = false;
	}
	
	public static Timer getInstance(){
		return ti;
	}
	
	public boolean setUp(int rt) {
		set_time = rt;
		elapsed_time = 0L;
		timer = new TimerThread(rt);
		return true;
	}
	
	public boolean start() {
		if (!is_paused && !is_running) {
			timer.start();
			System.out.println("started.");
			is_running = true;
			return true;
		} else {
			// throw exceptions
			System.out.println("start error.");
			return false;
		}
		
	}
	
	public boolean pause() {
		if (is_running && !is_paused) {
			elapsed_time += timer.getElapsedTime();
			timer.interrupt();
			System.out.println("paused.");
			is_paused = true;
			is_running = false;
			return true;
		} else {
			// throw exceptions
			System.out.println("pause error.");
			return false;
		}
		
	}
	
	public boolean restart() {
		if (is_paused && !is_running) {
			timer = new TimerThread(set_time, elapsed_time);
			timer.start();
			System.out.println("restarted.");
			is_paused = false;
			is_running = true;
			return true;
		} else {
			// throw exceptions
			System.out.println("restart error.");
			return false;
		}
	}
	
	public boolean terminate() {
		if (is_paused && !is_running) {
			System.out.println("terminated.");
			is_paused = false;
			return true;
		} else if (is_running && !is_paused) {
			timer.interrupt();
			System.out.println("terminated.");
			is_running = false;
			return true;
		} else {
			// throw exceptions
			System.out.println("terminate error.");
			return false;
		}			
	}
	
	public boolean increaseTime(int it) {
		if (is_running) {
			elapsed_time += timer.getElapsedTime();
			timer.interrupt();
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			timer = new TimerThread(set_time, elapsed_time);
			timer.start();
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else {
			// throw exceptions
			System.out.println("increase time error.");
			return false;
		}		
	}
	
	public boolean decreaseTime(int dt) {
		if (is_running) {
			elapsed_time += timer.getElapsedTime();
			timer.interrupt();
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			timer = new TimerThread(set_time, elapsed_time);
			timer.start();
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else {
			// throw exceptions
			System.out.println("decrease time error.");
			return false;
		}	
	}
}