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
	H;
	
	public static GridType getType(int t) {
		return GridType.values()[t];
	}
}
