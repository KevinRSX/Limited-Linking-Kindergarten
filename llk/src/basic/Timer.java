package basic;

public class Timer {
	private static Timer ti = new Timer();
	private TimerThread timerThread;
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
	
	public void setUp(int rt) {
		set_time = rt;
		elapsed_time = 0L;
		timerThread = new TimerThread(rt);
	}
	
	public void start() throws StartErrorException {
		if (!is_paused && !is_running) {
			timerThread.start();
			System.out.println("Timer started.");
			is_running = true;
		} else {
			throw new StartErrorException();
		}
		
	}
	
	public void pause() throws PauseErrorException {
		if (is_running && !is_paused) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			System.out.println("paused.");
			is_paused = true;
			is_running = false;
		} else {
			throw new PauseErrorException();
		}
		
	}
	
	public boolean restart() throws RestartErrorException {
		if (is_paused && !is_running) {
			timerThread = new TimerThread(set_time, elapsed_time);
			timerThread.start();
			System.out.println("restarted.");
			is_paused = false;
			is_running = true;
			return true;
		} else {
			throw new RestartErrorException();
		}
	}
	
	public boolean terminate() throws TerminateErrorException {
		if (is_paused && !is_running) {
			System.out.println("terminated.");
			is_paused = false;
			return true;
		} else if (is_running && !is_paused) {
			timerThread.interrupt();
			System.out.println("terminated.");
			is_running = false;
			return true;
		} else {
			throw new TerminateErrorException();
		}			
	}
	
	public boolean increaseTime(int it) throws TimeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			timerThread = new TimerThread(set_time, elapsed_time);
			timerThread.start();
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else {
			throw new TimeException();
		}		
	}
	
	public boolean decreaseTime(int dt) throws TimeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			timerThread = new TimerThread(set_time, elapsed_time);
			timerThread.start();
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else {
			throw new TimeException();
		}	
	}
	public void show() {
		timerThread.showTime();
	}
}