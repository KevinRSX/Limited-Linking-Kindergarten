package basic;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {

			try {
				Timer t = Timer.getInstance();
				t.setUp(100);
				t.start();
				Scanner sc = new Scanner(System.in); 
				char in;
				in = sc.next().charAt(0);
				while(in != 'e') {
					switch (in) { 
					case 'p':
						t.pause();
						break;
					case 'r':
						t.restart();
						break;
					case 't':
						t.terminate();
						break;
					case 'i':
						int to_increase = sc.nextInt();
						t.increaseTime(to_increase);
						break;
					case 'd':
						int to_decrease = sc.nextInt();
						t.decreaseTime(to_decrease);
						break;
					}
					in = sc.next().charAt(0);
				}
				sc.close();
			} catch (StartErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PauseErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RestartErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TerminateErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
