package basic;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in); 	
			System.out.println("Introduction\n"
					+"p for pause.\n"
					+ "r for resume (when paused).\n"
					+ "st for show time.\n"
					+ "sb for show board.\n"
					+"t for quit.\n"
					+ "c for cancel operation, after enter c you should enter the coordinate of grid in the form of x1 y1 x2 y2");
			System.out.println("Please first input the game size.");
			boolean flag=false;
			while(!flag) {
				try {
					char temp = sc.next().charAt(0);
					int size=temp-'0';
					Game game = new Game(size, 500);
					game.start();
					flag=true;
					boolean runningFlag = true;
					String in;		
					while(runningFlag) {
						in = sc.next();
						sc.nextLine();
						try {
							switch (in) { 
							case "p":
								game.pause();
								break;
							case "r":
								game.resume();
								break;
							case "t":
								game.terminate();
								runningFlag = false;
								break;
							case "c":
								String s=sc.nextLine();
								int x1,y1,x2,y2;
								x1=s.charAt(0)-'0';
								y1=s.charAt(2)-'0';
								x2=s.charAt(4)-'0';
								y2=s.charAt(6)-'0';
								game.cancelGrids(x1, y1, x2, y2);
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
						}
					}
					sc.close();
				} catch (WrongGameSizeException e1) {
					System.out.println(e1.getMessage());
				} catch (TimerStartErrorException e1) {
					System.out.println(e1.getMessage());
				}
			}
	}
}
