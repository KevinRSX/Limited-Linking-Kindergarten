package score;

public class Record implements Comparable{
	private String name;
	private int score;
	private String time;
	
	public Record(String str, String str2, String str3) {
		name = str;
		score = Integer.parseInt(str2);
		time = str3;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public String getTime() {
		return time;
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Record other = (Record) arg0;
		if(this.score < other.score) {
			return 1;
		} else if (this.score > other.score) {
			return -1;
		}
		return 0;
	}
	

}


