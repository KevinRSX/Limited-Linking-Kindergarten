package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JProgressBar;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import test.testTimer.StoppableStub;
import timer.Stoppable;
import timer.TimerThread;

class testTimerThread {
	class StoppableStub implements Stoppable{
		public boolean stop() {
			return true;
		}
	}
	private StoppableStub sp = new StoppableStub();
	private JProgressBar jpb = new JProgressBar();
	
	@Test
	public void testConstructor01() {
		TimerThread tt = new TimerThread(jpb, sp, 10);
	}
	
	@Test
	public void testConstructor02() {
		TimerThread tt = new TimerThread(jpb, sp, 10, 5);
	}
	
	@Test
	public void testRun01() {
		TimerThread tt = new TimerThread(jpb, sp, 5);
		tt.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRun02() {
		TimerThread tt = new TimerThread(jpb, sp, 3);
		tt.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tt.interrupt();
	}
	
	@Test
	public void testElapsedTime() {
		TimerThread tt = new TimerThread(jpb, sp, 3);
		tt.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long et = tt.getElapsedTime();
	}
}
