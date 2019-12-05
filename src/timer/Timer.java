package timer;

public class Timer implements Stoppable{
	private static Timer ti = new Timer();
	private TimerThread timerThread;
	private Object obj; // the object that one would like to change.
	private int set_time;
	private int bonus_time;
	private int pun_time;
	private long elapsed_time;
	private boolean is_paused;
	private boolean is_running;
	private Stoppable sp;
	 

	private Timer() {
		set_time = bonus_time = 0;
		elapsed_time = 0L;
		is_running = is_paused = false;
	}
	
	public static Timer getInstance(){
		return ti;
	}
	
	public void setUp(Stoppable sp, Object obj, int rt, int bonus, int punish) {
		set_time = rt;
		bonus_time = bonus;
		pun_time = punish;
		this.obj = obj;
		this.sp = (Stoppable)sp;
		elapsed_time = 0L;
		timerThread = new TimerThread(obj, ti, rt);
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
			timerThread = new TimerThread(obj, ti, set_time, elapsed_time);
			timerThread.start();
			System.out.println("restarted.");
			is_paused = false;
			is_running = true;
			return true;
		} else {
			throw new TimerRestartErrorException();
		}
	}
	
	public boolean cancel() throws TimerTerminateErrorException {
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
	
	public boolean increaseTime() throws TimerChangeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (bonus_time * 1000 > elapsed_time) ? 0 : elapsed_time - bonus_time*1000;
			timerThread = new TimerThread(obj, ti, set_time, elapsed_time);
			timerThread.start();
			System.out.println("added " + bonus_time + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (bonus_time * 1000 > elapsed_time) ? 0 : elapsed_time - bonus_time*1000;
			System.out.println("added " + bonus_time + " seconds to timer.");
			return true;
		} else {
			throw new TimerChangeException();
		}		
	}
	
	public boolean decreaseTime() throws TimerChangeException {
		if (is_running) {
			elapsed_time += timerThread.getElapsedTime();
			timerThread.interrupt();
			elapsed_time = (pun_time * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + pun_time*1000;
			timerThread = new TimerThread(obj, ti, set_time, elapsed_time);
			timerThread.start();
			System.out.println("subtracted " + pun_time + " seconds to timer.");
			return true;
		} else if (is_paused) {
			elapsed_time = (pun_time * 1000 > (set_time * 1000 - elapsed_time)) ? set_time * 1000 : elapsed_time + pun_time*1000;
			System.out.println("subtracted " + pun_time + " seconds to timer.");
			return true;
		} else {
			throw new TimerChangeException();
		}	
	}
	
	public int getRemainingTime() {
		return set_time - (int)(elapsed_time / 1000);
	}
	
	@Override
	public boolean stop() {
		elapsed_time = set_time * 1000;
		is_paused = false;
		is_running = false;
		sp.stop();
		return true;
	}
}