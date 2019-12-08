package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import score.ScoreBoard;

class testScoreBoard {
	private ScoreBoard sb = ScoreBoard.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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

	@Test
	void testScoreBoard_01() {	
		assertEquals("File exists", sb.readScoreBoard("data/test1"));
		assertEquals("Inputing a new filepath", 1, sb.getlistSize());
		
		sb.getScores("data/test1");
		assertEquals(1,sb.getNum());
	}
	@Test
	void testScoreBoard_02() {	
		assertEquals("File exists", sb.readScoreBoard("data/test2"));
		assertEquals("Inputing a new filepath", 14, sb.getlistSize());
		sb.getScores("data/test2");
		assertEquals(11,sb.getNum());
	}
	@Test
	void testScoreBoard_03() {	
		assertEquals("Cannot find this file!", sb.readScoreBoard("data/test3"));
		assertEquals("Inputing a new filepath", 0, sb.getlistSize());
		sb.getScores("data/test3");
		assertEquals(0,sb.getNum());
	}

}
