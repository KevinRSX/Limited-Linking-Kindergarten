package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import score.ScoreBoard;

class testScoreBoard {
	private static ScoreBoard sb = ScoreBoard.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sb.refresh();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	// loop coverage
	@Test
	void testScoreBoard_01() {	
		assertEquals("File exists", sb.readScoreBoard("data/test1"));
		assertEquals("Inputing a new filepath", 1, sb.getlistSize());
		
		sb.getScores("data/test1");
		System.out.println(sb.getNum());
		assertEquals(1,sb.getNum());
	}
	@Test
	void testScoreBoard_02() {	
		assertEquals("File exists", sb.readScoreBoard("data/test2"));
		assertEquals("Inputing a new filepath", 11, sb.getlistSize());
		sb.getScores("data/test2");
		assertEquals(11,sb.getNum());
	}
	@Test
	void testScoreBoard_03() {	
		assertEquals("Cannot find this file!", sb.readScoreBoard("data/test3"));
		assertEquals("Inputing a new filepath", 0, sb.getlistSize());
		sb.getScores("data/test3");
		System.out.println(sb.getNum());
		assertEquals(0,sb.getNum());
	}
	
	@Test
	void testScoreBoard_04() {
		sb.writeScoreBoard("data/test4", "test_user", 1500);
		assertEquals("File exists", sb.readScoreBoard("data/test4"));
		sb.getScores("data/test4");
		System.out.println(sb.getNum());
		assertEquals(1,sb.getNum());
		File f= new File("data/test4");
		f.delete();
		assertEquals("Cannot find this file!", sb.readScoreBoard("data/test4"));
	}
	
	@Test
	void testScoreBoard_05() {
		sb.writeScoreBoard("test@test.com + http://www.google.com", "test_user", 1500);
		assertEquals("Cannot find this file!", sb.readScoreBoard("data/test4"));
		
	}

}
