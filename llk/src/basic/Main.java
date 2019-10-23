package basic;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
			Game game;
			try {
				game = new Game(8,500);
				game.start();
			
			Scanner sc = new Scanner(System.in); 
			String in;
			in = sc.next();
			while(!in.equals("t") ) {
				sc.nextLine();
				try {
					switch (in) { 
					case "p":
						game.pause();
						break;
					case "r":
						game.restart();
						break;
					case "t":
						game.terminate();
						break;
					case "c":
						String s=sc.nextLine();
						int x1,y1,x2,y2;
						x1=s.charAt(0)-'0';
						y1=s.charAt(2)-'0';
						x2=s.charAt(4)-'0';
						y2=s.charAt(6)-'0';
						game.cancelGrids(x1, y1, x2, y2);
						game.showBoard();
						game.showTime();
						break;
					case "sb":
						game.showBoard();		
						break;
					case "st":
						game.showTime();
						break;
					default:
						System.out.println("Please input correct command!");
					}
				} catch (PauseErrorException e) {
					System.out.println(e.getMessage());
				} catch (RestartErrorException e) {
					System.out.println(e.getMessage());
				} catch (TerminateErrorException e) {
					System.out.println(e.getMessage());
				} catch (OutOfBoundException e) {
					System.out.println(e.getMessage());
				} catch (CannotCancelException e) {
					System.out.println(e.getMessage());
				}
				in = sc.next();
			}
			sc.close();
			} catch (WrongGameSizeException e1) {
				System.out.println(e1.getMessage());
			} catch (StartErrorException e1) {
				System.out.println(e1.getMessage());
			}
	}
}
