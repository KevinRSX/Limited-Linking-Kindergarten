package basic;

public enum GridType {
	NONE,
	A,
	B,
	C,
	D,
	E,
	F,
	G,
	H,
	CANCELLED;

	public static GridType getType(int t) {
		return GridType.values()[t];
	}
	
	public String toString()
	{
		switch(this)
		{
		case A:
			return "A";
		case B:
			return "B";
		case C:
			return "C";
		case D:
			return "D";
		case E:
			return "E";
		case F:
			return "F";
		case G:
			return "G";
		case H:
			return "H";
		case CANCELLED:
			return " ";
		default:
			return "*";
		}
	}
}
