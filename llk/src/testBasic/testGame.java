package testBasic;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import basic.Game;

class testGame {

	@Test
	void test() throws Exception { //test Game start.
		Game game=Game.getInstance();
		game.start(10, 100);
		
	}

}
