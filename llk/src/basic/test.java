package basic;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
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
	}
}
