package basic;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
			String intro_str = "Introduction: press:\n"
					+"p to pause.\n"
					+ "r to resume (when paused).\n"
					+ "st to show time.\n"
					+ "sb to show board.\n"
					+ "sc to show all commands.\n"
					+"e to quit.\n"
					+ "c to cancel operation, after enter c you should enter the coordinate of grid in the form of x1 y1 x2 y2";
			Scanner sc = new Scanner(System.in); 	
			System.out.println(intro_str);
			System.out.println("Please first input the game size.");
			boolean flag=false;
			while(!flag) {
				try {
					char temp = sc.next().charAt(0);
					int size=temp-'0';
					Game game = new Game(size, 5);
					game.start();
					flag=true;
					String in;		
					while(!game.isStopped()) {
						in = sc.next();
						sc.nextLine();
						try {
							switch (in) { 
							case "p":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}									
								game.pause();
								break;
							case "r":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								game.resume();
								break;
							case "e":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								game.terminate();
								break;
							case "c":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								String s=sc.nextLine();
								int x1,y1,x2,y2;
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								x1=s.charAt(0)-'0';
								y1=s.charAt(2)-'0';
								x2=s.charAt(4)-'0';
								y2=s.charAt(6)-'0';
								game.cancelGrids(x1, y1, x2, y2);
								game.showTime();
								break;
							case "sb":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								game.showBoard();		
								break;
							case "sc":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}
								System.out.println(intro_str);
								break;
							case "st":
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}			
								game.showTime();
								break;
							default:
								if (game.isStopped()) {
									throw new GameHasEndedException();
								}
								System.out.println("Please input correct command!");
								System.out.println(intro_str);
							}
						} catch (TimerPauseErrorException e) {
							System.out.println(e.getMessage());
						} catch (TimerRestartErrorException e) {
							System.out.println(e.getMessage());
						} catch (TimerTerminateErrorException e) {
							System.out.println(e.getMessage());
						} catch (OutOfBoundException e) {
							System.out.println(e.getMessage());
						} catch (CannotCancelException e) {
							System.out.println(e.getMessage());
						} catch (GameHasEndedException e) {
							System.out.println(e.getMessage());
						} 
					}
					sc.close();
				} catch (WrongGameSizeException e1) {
					System.out.println(e1.getMessage());
				} catch (TimerStartErrorException e1) {
					System.out.println(e1.getMessage());
				} catch (OutOfBoundException e1) {
					System.out.println(e1.getMessage());
				}
			}
	}
}
