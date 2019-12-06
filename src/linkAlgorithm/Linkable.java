package linkAlgorithm;

public interface Linkable {
	public boolean canPassThrough();
	public boolean isSelected();
	public void select();
	public void deselect();
	public void cancel();
	public boolean sameType(Linkable another);
}
