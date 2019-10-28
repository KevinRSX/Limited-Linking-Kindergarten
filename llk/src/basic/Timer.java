package basic;

public class Timer implements Stoppable{
	private static Timer ti = new Timer();
	private TimerThread timerThread;
	private int set_time;
	private int bonus_time;
	private long elapsed_time;
	private boolean is_paused;
	private boolean is_running;
	private Stoppable sp;
	
	// TODO 1. Testing of timer
	// TODO 2. Stoppable implementation
	// TODO 3. Debugging (if possible)
	// TODO 4. GUI version of timer.

	private Timer() {
		set_time = bonus_time = 0;
		elapsed_time = 0L;
		is_running = is_paused = false;
	}
	
	public static Timer getInstance(){
		return ti;
	}
	
	public void setUp(int rt, Stoppable s) {
		set_time = rt;
		elapsed_time = 0L;
		timerThread = new TimerThread(rt, ti);
		sp = s;
	}
	
	public void start() throws TimerStartErrorException {
		if (!is_paused && !is_running) {
			timerThread.start();
			System.out.println("Timer started.");
			is_running = true;
		} else {
			throw new TimerStartErrorException();
		}
	}
	
	public void pause() throws TimerPauseErrorException {
		if (is_running && !is_paused) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			System.out.println("paused.");
			is_paused = true;
			is_running = false;
		} else {
			throw new TimerPauseErrorException();
		}
	}
	
	public boolean restart() throws TimerRestartErrorException {
		if (is_paused && !is_running) {
			timerThread = new TimerThread(set_time, elapsed_time, ti);
			timerThread.start();
			System.out.println("restarted.");
			is_paused = false;
			is_running = true;
			return true;
		} else {
			throw new TimerRestartErrorException();
		}
	}
	
	public boolean terminate() throws TimerTerminateErrorException {
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
			throw new TimerTerminateErrorException();
		}			
	}
	
	public boolean increaseTime(int it) throws TimerChangeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			timerThread = new TimerThread(set_time, elapsed_time, ti);
			timerThread.start();
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (it * 1000 > elapsed_time) ? 0 : elapsed_time - it*1000;
			System.out.println("added " + it + " seconds to timer.");
			return true;
		} else {
			throw new TimerChangeException();
		}		
	}
	
	public boolean decreaseTime(int dt) throws TimerChangeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			timerThread = new TimerThread(set_time, elapsed_time, ti);
			timerThread.start();
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (dt * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + dt*1000;
			System.out.println("subtracted " + dt + " seconds to timer.");
			return true;
		} else {
			throw new TimerChangeException();
		}	
	}
	
	public void showTime() {
		timerThread.showTime();
	}
	
	public boolean stop() {
		elapsed_time = timerThread.getElapsedTime();
		is_paused = false;
		is_running = false;
		sp.stop();
		return true;
	}
}