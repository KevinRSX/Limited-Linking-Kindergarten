package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import timer.*;
import javax.swing.JProgressBar;

class testTimer {
	private Timer ti;
	class StoppableStub implements Stoppable{
		public boolean stop() {
			return true;
		}
	}
	private StoppableStub sp = new StoppableStub();
	private JProgressBar jpb = new JProgressBar();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testGetInstance() {
		Timer t = Timer.getInstance();
	}
	
	@Test
	void testClear() {
		ti = Timer.getInstance();
		ti.clear();
	}
	
	@Test
	void testGetStatus01() {
		ti = Timer.getInstance();
		assertEquals(false, ti.is_running());
		ti.clear();
	}
	
	@Test
	void testGetStatus02() {
		ti = Timer.getInstance();
		assertEquals(false, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testSetUp() {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 50, 1, 1);
		ti.clear();
	}
	
	@Test
	void testStart01() throws TimerStartErrorException{
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		assertThrows(TimerStartErrorException.class, () -> {
			ti.start();
		});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.clear();
	}
	
	@Test
	void testStart02() throws TimerStartErrorException{
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		assertEquals(true, ti.is_running());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.clear();
	}
	
	@Test
	void testPause01() throws TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		assertThrows(TimerPauseErrorException.class, () -> {
			ti.pause();
		});
		ti.clear();
	}
	
	@Test
	void testPause02() throws TimerStartErrorException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		ti.pause();
		assertEquals(true, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testStart03() throws TimerStartErrorException, TimerPauseErrorException{
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		ti.pause();
		assertThrows(TimerStartErrorException.class, () -> {
			ti.start();
		});
		ti.clear();
	}
	
	@Test
	void testRestart01() throws TimerStartErrorException, TimerPauseErrorException, TimerRestartErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		ti.pause();
		ti.restart();
		assertEquals(true, ti.is_running());
		ti.clear();
	}
	
	@Test
	void testRestart03() throws TimerStartErrorException, TimerRestartErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		assertThrows(TimerRestartErrorException.class, () -> {
			ti.restart();
		});
		ti.clear();
	}
	
	@Test
	void testCancel01() throws TimerStartErrorException, TimerPauseErrorException, TimerTerminateErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		ti.pause();
		ti.cancel();
		assertEquals(false, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testCancel02() throws TimerStartErrorException, TimerTerminateErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		ti.cancel();
		assertEquals(false, ti.is_running());
		ti.clear();
	}
	
	@Test
	void testCancel03() throws TimerTerminateErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		assertThrows(TimerTerminateErrorException.class, () -> {
			ti.cancel();
		});
		ti.clear();
	}
	
	@Test
	void testIncrease01() throws TimerStartErrorException, TimerChangeException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 10, 10);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, ti.increaseTime());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.clear();
	}
	
	@Test
	void testIncrease02() throws TimerStartErrorException, TimerChangeException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 5, 1, 1);
		ti.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, ti.increaseTime());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.clear();
	}
	
	@Test
	void testIncrease03() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 5, 1, 1);
		ti.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.pause();
		assertEquals(true, ti.increaseTime());
		assertEquals(true, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testIncrease04() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 3, 10, 10);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.pause();
		assertEquals(true, ti.increaseTime());
		assertEquals(true, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testIncrease05() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 3, 1, 1);
		assertThrows(TimerChangeException.class, () -> {
			ti.increaseTime();
		});
		ti.clear();
	}
	
	@Test
	void testDecrease01() throws TimerStartErrorException, TimerChangeException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 10, 10);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, ti.decreaseTime());
		ti.clear();
	}
	
	@Test
	void testDecrease02() throws TimerStartErrorException, TimerChangeException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 5, 1, 1);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, ti.decreaseTime());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.clear();
	}
	
	@Test
	void testDecrease03() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 5, 1, 1);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.pause();
		assertEquals(true, ti.decreaseTime());
		assertEquals(true, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testDecrease04() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 10, 10);
		ti.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ti.pause();
		assertEquals(true, ti.decreaseTime());
		assertEquals(true, ti.is_paused());
		ti.clear();
	}
	
	@Test
	void testDecrease05() throws TimerStartErrorException, TimerChangeException, TimerPauseErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 3, 1, 1);
		assertThrows(TimerChangeException.class, () -> {
			ti.decreaseTime();
		});
		ti.clear();
	}
	
	@Test
	void testGetRemaining() throws TimerStartErrorException {
		ti = Timer.getInstance();
		ti.setUp(sp, jpb, 2, 1, 1);
		ti.start();
		try {
			Thread.sleep(2100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(0, ti.getRemainingTime());
		ti.clear();
	}
}
